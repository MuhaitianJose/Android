package com.muhaitian.record;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.muhaitian.record.adapter.MainAdapter;
import com.muhaitian.record.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener,
        ListView.OnItemLongClickListener, ListView.OnItemSelectedListener {

    private ListView MainContentView;
    private MainAdapter MainViewAdapter;
    private List<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initContent();
    }

    private void initContent() {
        list = new ArrayList<>();
        Item mItem = new Item();
        mItem.setName("LocalSocket");
        list.add(list.size(), mItem);
        MainContentView = (ListView) findViewById(R.id.LearnRecord);
        MainViewAdapter = new MainAdapter(getApplicationContext(), list);
        MainContentView.setAdapter(MainViewAdapter);

        MainContentView.setOnItemClickListener(this);
        MainContentView.setOnItemLongClickListener(this);
        MainContentView.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
