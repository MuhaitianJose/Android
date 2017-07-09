package com.muhaitian.record.entity;

import android.content.Intent;

/**
 * Created by wangkang001 on 2017/7/8.
 *
 * 记录功能的类，用于Adapter.
 *
 */

public class Item {
    private String Name;
    private String Description;
    private Intent StartIntent;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Intent getStartIntent() {
        return StartIntent;
    }

    public void setStartIntent(Intent startIntent) {
        StartIntent = startIntent;
    }
}
