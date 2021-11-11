package com.example.taka24;

import java.io.Serializable;

public class Data implements Serializable {

    public String category_name;

    public Data(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
