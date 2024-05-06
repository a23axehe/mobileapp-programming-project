package com.example.project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements Json_Task.JsonTaskListener {
    private MyAdapter adapter;
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a23axehe";
    private final ArrayList<Country> countries = new ArrayList<>();

    private void getJson() {
        //new Json_File(this, this).execute(JSON_URL);
        new Json_Task(this).execute(JSON_URL);
    }
    public ArrayList<Country> parseJson(String json) {

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("ID");
                String name = jsonObject.getString("name");
                String type = jsonObject.getString("type");
                String location = jsonObject.getString("location");
                int size = jsonObject.getInt("size");
                int cost = jsonObject.getInt("cost");
                JSONObject auxData = jsonObject.getJSONObject("auxdata");
                String wiki = auxData.getString("wiki");
                String imgUrl = auxData.optString("img", "");
                Country country = new Country(id, name, type, location, size, cost, wiki, imgUrl);
                countries.add(country);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countries;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, countries, new MyAdapter.OnClickListener() {
            @Override
            public void onClick(Country country) {
                Toast.makeText(MainActivity.this, country.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView view = findViewById(R.id.recyclerview1);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
        getJson();
    }

    @Override
    public void onPostExecute(String json) {
        countries.clear();
        countries.addAll(parseJson(json));
        adapter.notifyDataSetChanged();
    }
}
