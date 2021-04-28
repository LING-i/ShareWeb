package com.eg.code.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//返回图表的数据
public class Data implements Serializable {

    private List<String> categories;
    private List<Integer> data;

    public Data() {
    }

    public Data(List<String> categories, List<Integer> data) {
        this.categories = categories;
        this.data = data;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "data{" +
                "categories=" + categories +
                ", data=" + data +
                '}';
    }
}
