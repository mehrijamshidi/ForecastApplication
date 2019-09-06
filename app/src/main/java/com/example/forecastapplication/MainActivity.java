package com.example.forecastapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.forecastapplication.ForecastGson.ForecastGson;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtMaxTemp = findViewById(R.id.txtMaxTemp);
        final TextView txtMinTemp = findViewById(R.id.txtMinTemp);
        final TextView txtDescription = findViewById(R.id.txtDescription);

        final TextView txt1 = findViewById(R.id.txt1);
        final TextView txt11 = findViewById(R.id.txt11);
        final TextView txt2 = findViewById(R.id.txt2);
        final TextView txt22 = findViewById(R.id.txt22);
        final TextView txt3 = findViewById(R.id.txt3);
        final TextView txt33 = findViewById(R.id.txt33);
        final TextView txt4 = findViewById(R.id.txt4);
        final TextView txt44 = findViewById(R.id.txt44);
        final TextView txt5 = findViewById(R.id.txt5);
        final TextView txt55 = findViewById(R.id.txt55);

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout Drawer = findViewById(R.id.drawer);
                Drawer.openDrawer(Gravity.LEFT);
            }
        });

        Button btnForecast = findViewById(R.id.btnForecast);
        btnForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForecastActivity.class);
                startActivity(intent);
            }
        });

        String address = "http://api.openweathermap.org/data/2.5/forecast?q=Tehran&APPID=fc07a5eb13ffef88beb315454111762c";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                ForecastGson forecast = gson.fromJson(response.toString(),ForecastGson.class);

                try {
                    JSONObject object = new JSONObject(response.toString());

                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject list = array.getJSONObject(0);

                    JSONArray ary = new JSONArray(list.getString("weather"));
                    JSONObject weather = ary.getJSONObject(0);
                    String description = weather.getString("description");
                    txtDescription.setText(description);

                    JSONObject main = new JSONObject(list.getString("main"));
                    String maxtemp = main.getString("temp_max");
                    Double MAX = Double.valueOf(maxtemp)-273;
                    Long X = Math.round(MAX);
                    txtMaxTemp.setText(X+"℃");

                    String mintemp = main.getString("temp_min");
                    Double MIN = Double.valueOf(mintemp)-273;
                    Long Y = Math.round(MIN);
                    txtMinTemp.setText("/"+Y+"℃");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject list = array.getJSONObject(5);

                    String date = list.getString("dt_txt");
                    txt1.setText(date);

                    JSONObject main = new JSONObject(list.getString("main"));
                    String temp = main.getString("temp");
                    Double TEMP = Double.valueOf(temp)-273;
                    Long X = Math.round(TEMP);
                    txt11.setText(X+"℃");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject list = array.getJSONObject(13);

                    String date = list.getString("dt_txt");
                    txt2.setText(date);

                    JSONObject main = new JSONObject(list.getString("main"));
                    String temp = main.getString("temp");
                    Double TEMP = Double.valueOf(temp)-273;
                    Long X = Math.round(TEMP);
                    txt22.setText(X+"℃");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject list = array.getJSONObject(21);

                    String date = list.getString("dt_txt");
                    txt3.setText(date);

                    JSONObject main = new JSONObject(list.getString("main"));
                    String temp = main.getString("temp");
                    Double TEMP = Double.valueOf(temp)-273;
                    Long X = Math.round(TEMP);
                    txt33.setText(X+"℃");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject list = array.getJSONObject(29);

                    String date = list.getString("dt_txt");
                    txt4.setText(date);

                    JSONObject main = new JSONObject(list.getString("main"));
                    String temp = main.getString("temp");
                    Double TEMP = Double.valueOf(temp)-273;
                    Long X = Math.round(TEMP);
                    txt44.setText(X+"℃");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array = new JSONArray(object.getString("list"));
                    JSONObject list = array.getJSONObject(37);

                    String date = list.getString("dt_txt");
                    txt5.setText(date);

                    JSONObject main = new JSONObject(list.getString("main"));
                    String temp = main.getString("temp");
                    Double TEMP = Double.valueOf(temp)-273;
                    Long X = Math.round(TEMP);
                    txt55.setText(X+"℃");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
