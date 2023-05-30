package com.example.wtf_weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ProgressBar loading;
    private TextView cityName, temprature, condition;
    private TextInputEditText input;
    private ImageView background, search_icon, weather_icon;
    private RecyclerView weather_RV;

    private ArrayList<WeatherModalClass> weatherModalClassArrayList;
    private WeatherAdapter weatherAdapter;
    private LocationManager locationManager;
    private int PERMISSION_CODE = 1;
    private String citiName;

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);

        loading = findViewById(R.id.loading);
        cityName = findViewById(R.id.cityName);
        temprature = findViewById(R.id.temprature);
        condition = findViewById(R.id.condition);
        input = findViewById(R.id.input);
        background = findViewById(R.id.background);
        search_icon = findViewById(R.id.search_icon);
        weather_icon = findViewById(R.id.weather_icon);
        weather_RV = findViewById(R.id.weather_RV);

        weatherModalClassArrayList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(this, weatherModalClassArrayList);

        weather_RV.setAdapter(weatherAdapter);

        locationManager = (LocationManager) getSystemService(Context.LOCALE_SERVICE);

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_CODE);
//            return;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        citiName = getCityName(location.getLongitude(),location.getLatitude()); //CALLING TO GET CITY NAME....
        getWeatherInfo(citiName);

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = input.getText().toString();
                if(city.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter City Name", Toast.LENGTH_SHORT).show();
                } else {
                    cityName.setText(citiName);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_CODE) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Successful....\nPermission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error....\nPlease provide permission", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private String getCityName(double longtiude, double latitude) {
        String cityName = "Not Found";
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(latitude,longtiude,10);

            for(Address adr : addresses) {
                if(adr!=null) {
                    String city = adr.getLocality();
                    if(city != null && !city.equals("")) {
                        cityName = city;
                    } else {
                        Log.d("TAG","CITY NOT FOUND");
                        Toast.makeText(this, "City not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return cityName;
    }

    private void getWeatherInfo(String citiName) {
        String url = "http://api.weatherapi.com/v1/forecast.json?key=f7ca625e4dd24cf5988172556232905&q=" + cityName + "&days=1&aqi=no&alerts=no\n";
        cityName.setText(citiName);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loading.setVisibility(View.GONE);
                weatherModalClassArrayList.clear();

                try {
                    String temp_rature = response.getJSONObject("current").getString("temp_c");
                    temprature.setText(temp_rature+"°C");
                    int isDay = response.getJSONObject("current").getInt("isDay");
                    String con_dition = response.getJSONObject("current").getJSONObject("condition").getString("text");
                    String con_ditionIcon = response.getJSONObject("current").getJSONObject("condition").getString("icon");
                    Picasso.get().load("http:".concat(con_ditionIcon)).into(weather_icon);
                    condition.setText(con_dition);

                    //Morning
                    if(isDay == 1) Picasso.get().load("https://www.google.com/url?sa=i&url=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fmorning-sky&psig=AOvVaw2k2L35ILFcd4_xvs8Vt14y&ust=1685555305675000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCMj8r_zMnf8CFQAAAAAdAAAAABAQ").into(background);

                    //Night
                    else Picasso.get().load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FNight_sky&psig=AOvVaw1gxNsdtBlRtodZtF50jM0L&ust=1685555360192000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCOjboJbNnf8CFQAAAAAdAAAAABAE").into(background);

                    JSONObject forecastObj = response.getJSONObject("forecast");
                    JSONObject forecastO = forecastObj.getJSONArray("forecastday").getJSONObject(0);
                    JSONArray hourArray = forecastO.getJSONArray("hour");

                    for(int i = 0; i < hourArray.length(); i++) {
                        JSONObject hourObj = hourArray.getJSONObject(i);
                        String time = hourObj.getString("time");
                        String temper = hourObj.getString("temp_c");
                        String img = hourObj.getJSONObject("condition").getString("icon");
                        String wind = hourObj.getString("wind_kph");

                        weatherModalClassArrayList.add(new WeatherModalClass(time,temper,img,wind));
                    }
                    weatherAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Not a valid City...", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}