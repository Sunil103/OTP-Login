package com.example.docamida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Search extends AppCompatActivity {
    SearchView search;
    ListView myList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search=(SearchView)findViewById(R.id.search);
        myList=(ListView)findViewById(R.id.list);
    }
    protected Map<Integer, String> getParams()  {
        final Map<Integer, String> params = new HashMap<>();
        params.put(1, "Adigas");
        params.put(2, "Arya");


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Iterator myVeryOwnIterator = params.keySet().iterator();
                while(myVeryOwnIterator.hasNext()) {
                    String key=(String)myVeryOwnIterator.next();
                    String value=(String)params.get(key);
                    Toast.makeText(Search.this, "Key: "+key+" Value: "+value, Toast.LENGTH_LONG).show();
                }
                adapter.getFilter().filter(s);
            return false;
            }
        });

        return params;
    }
}
