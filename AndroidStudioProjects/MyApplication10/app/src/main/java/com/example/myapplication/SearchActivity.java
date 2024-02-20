package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.list_view);

        // Set up the ListView with some sample data

        List<String> items = Arrays.asList("@drawable/ampel", "@drawable/ampel", "@drawable/ampel",
                "@drawable/ampel", "@drawable/ampel", "@drawable/ampel", "@drawable/ampel", "@drawable/ampel", "@drawable/ampel",
                "@drawable/ampel", "@drawable/ampel", "@drawable/ampel", "@drawable/ampel", "@drawable/ampel", "@drawable/ampel", "@drawable/ampel", "@drawable/ampel",
                "Tangerine", "Watermelon");
        List<String> totals = Arrays.asList("Apple", "Banana", "Cherry", "Dfate", "Dff", "Ds", "Kiwi", "Lemon", "Mango",
                "Orange", "Papaya", "Peach", "Pear", "Pineapple", "Plum", "Raspberry", "Strawberry",
                "Tangerine", "Watermelon");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_plant_search, R.id.text, items);



        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        int count = items.size();
        for(int i = 0; i < count; i++) {
            map = new HashMap<String, String>();
            map.put("name", items.get(i));
            map.put("total", totals.get(i));
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.list_item_plant_search, new String[]{"name", "total"}, new int[]{R.id.img_petunia, R.id.text2});




        listView.setAdapter(adapter);


        setupSearchView();
    }
    private void filter(String query) {
        SimpleAdapter adapter = (SimpleAdapter) listView.getAdapter();
        adapter.getFilter().filter(query);
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }
}