package com.example.wtf_weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherModalClass> weatherModalClassArrayList;

    public WeatherAdapter(Context context, ArrayList<WeatherModalClass> weatherModalClassArrayList) {
        this.context = context;
        this.weatherModalClassArrayList = weatherModalClassArrayList;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        WeatherModalClass modal = weatherModalClassArrayList.get(position);
        holder.weather_temprature.setText(modal.getTemp()+"°C");
        Picasso.get().load("http:".concat(modal.getIcon())).into(holder.weather_condition);
        holder.weather_windSpeed.setText(modal.getWindSpeed()+"Km/h");
        SimpleDateFormat ip = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat op = new SimpleDateFormat("hh-mm aa");
        try {
            Date t = ip.parse(modal.getTime());
            holder.weather_time.setText(op.format(t));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weatherModalClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView weather_time,weather_temprature,weather_windSpeed;
        private ImageView weather_condition;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weather_time = itemView.findViewById(R.id.weather_time);
            weather_temprature = itemView.findViewById(R.id.weather_temprature);
            weather_windSpeed = itemView.findViewById(R.id.weather_windSpeed);
            weather_condition = itemView.findViewById(R.id.weather_condition);

        }
    }
}
