package com.example.forecastapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.forecastapplication.Forecast.Forecast;
import com.example.forecastapplication.Forecast.Weather;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ForecastActivity extends AppCompatActivity {
    int intTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        final TextView txtName = findViewById(R.id.txtName);
        final TextView txtMaxTemp = findViewById(R.id.txtMaxTemp);
        final TextView txtMinTemp = findViewById(R.id.txtMinTemp);
        final TextView txtDescription = findViewById(R.id.txtDescription);

        final EditText edtCity = findViewById(R.id.edtCity);

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String City = edtCity.getText().toString();
                txtName.setText(City);
                String address = "http://api.openweathermap.org/data/2.5/weather?q="+City+"&APPID=fc07a5eb13ffef88beb315454111762c";
                AsyncHttpClient Client = new AsyncHttpClient();
                Client.get(address, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Gson gson = new Gson();
                        Forecast cast = gson.fromJson(response.toString(),Forecast.class);

                        Double tempMax = cast.getMain().getTempMax();
                        double TempMax = Double.valueOf(tempMax)-273;
                        long Y = Math.round(TempMax);
                        txtMaxTemp.setText(Y+"℃");

                        Double tempMin = cast.getMain().getTempMin();
                        double TempMin = Double.valueOf(tempMin)-273;
                        long Z = Math.round(TempMin);
                        txtMinTemp.setText("/"+Z+"℃");

                        try {
                            JSONObject object = new JSONObject(response.toString());
                            JSONArray array = new JSONArray(object.getString("weather"));
                            JSONObject weather = array.getJSONObject(0);
                            String description = weather.getString("description");
                            txtDescription.setText(description);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RecyclerView recycler = findViewById(R.id.recycler);
                        List<String> list = new ArrayList<>();
                        list.add(City);
                        RecyclerAdapter adapter = new RecyclerAdapter(list,edtCity,this);
                        recycler.setAdapter(adapter);
                        recycler.setLayoutManager(new LinearLayoutManager(ForecastActivity.this,RecyclerView.VERTICAL,false));




                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });

            }
        });




    }
}
