package com.example.networktest;

/**
 * Created by zl on 19-10-24.
 */
public class App {
    //使用GSON对JOSN解析
    private String id;
    private String version;
    private String name;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "App{" +
                "id='" + id + '\'' +
                ", version='" + version + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
