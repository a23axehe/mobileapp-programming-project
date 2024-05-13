package com.example.project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements Json_Task.JsonTaskListener {
    private MyAdapter adapter;
    private final String JSON_FILE = "Countries.json";
    private final ArrayList<Country> countries = new ArrayList<>();

    private void getJson() {
        new Json_File(this, this).execute(JSON_FILE);
    }
    public ArrayList<Country> parseJson(String json) {

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("ID");
                String name = jsonObject.getString("name");
                String type = jsonObject.getString("type");
                String company = jsonObject.getString("company");
                String location = jsonObject.getString("location");
                String category = jsonObject.getString("category");
                int size = jsonObject.getInt("size");
                int cost = jsonObject.getInt("cost");
                Country country = new Country(id, name, type, company, location, category, size, cost);
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

        recyclerView.setAdapter(adapter);
        getJson();


        Button secondActivityButton = findViewById(R.id.button);
        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the second activity
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPostExecute(String json) {
        countries.clear();
        parseJson(json);
        adapter.notifyDataSetChanged();
    }

}
