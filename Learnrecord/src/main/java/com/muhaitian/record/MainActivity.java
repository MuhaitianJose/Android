package com.muhaitian.record;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.muhaitian.record.adapter.MainAdapter;
import com.muhaitian.record.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener,
        ListView.OnItemLongClickListener, ListView.OnItemSelectedListener {
private final String TAG = "MainActivity";
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
        Intent intent = new Intent();
        ComponentName name = new ComponentName("com.muhaitian.record","com.muhaitian.record.LocalSocketActivity");
        intent.setComponent(name);
        mItem.setStartIntent(intent);
        list.add(list.size(), mItem);

        mItem = new Item();
        mItem.setName("AIDL Seting");
        Intent inten = new Intent();
        ComponentName mm = new ComponentName("com.muhaitian.record","com.muhaitian.record.AidlTestActivity");
        inten.setComponent(mm);
        mItem.setStartIntent(inten);
        list.add(list.size(), mItem);

        MainContentView = (ListView) findViewById(R.id.LearnRecord);
        MainViewAdapter = new MainAdapter(getApplicationContext(), list);
        MainContentView.setAdapter(MainViewAdapter);

        MainViewAdapter.setmOnItemClickListener(this);

//        MainContentView.setOnItemClickListener(this);
//        MainContentView.setOnItemLongClickListener(this);
//        MainContentView.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick: position=="+position);
        Item mItem = list.get(position);
        Log.d(TAG, "onItemClick: getStartIntent=="+mItem.getStartIntent());
        startActivity(mItem.getStartIntent());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemLongClick: ");
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected: ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "onNothingSelected: ");
    }
}
