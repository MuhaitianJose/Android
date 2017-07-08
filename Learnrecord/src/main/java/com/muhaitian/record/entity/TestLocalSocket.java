package com.muhaitian.record.entity;

import java.io.Serializable;

/**
 * Created by wangkang001 on 2017/7/8.
 */

public class TestLocalSocket implements Serializable{
    private static final long serialVersionUID = 2373691669223946637l;
    private String Name;
    private String Description;

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
}
