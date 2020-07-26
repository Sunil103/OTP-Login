package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class Chennai extends AppCompatActivity {
    SearchView search;
    ListView myList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangalore);

        search=(SearchView)findViewById(R.id.search);
        myList=(ListView)findViewById(R.id.list);
        list=new ArrayList<String>();
        list.add("Amma Hotel");
        list.add("Amrutha hotel");
        list.add("Shakthi Sagar");
        list.add("Shashidhar Veg");
        list.add("Rajini mahal");
        list.add("Radika Military Hotel");


        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        myList.setAdapter(adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });



    }
}
