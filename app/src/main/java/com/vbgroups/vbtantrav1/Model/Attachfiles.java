package com.vbgroups.vbtantrav1.Model;

public class Attachfiles {

    public String name;
    public String url;
    public String id;
    public String plantname;
    public String plantcity;

    public Attachfiles(){

    }
    public Attachfiles(String name, String plantname, String plantcity, String id, String url) {
        this.name = name;
        this.plantname = plantname;
        this.plantcity = plantcity;
        this.id = id;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPlantname() {
        return plantname;
    }

    public void setPlantname(String plantname) {
        this.plantname = plantname;
    }

    public String getPlantcity() {
        return plantcity;
    }

    public void setPlantcity(String plantcity) {
        this.plantcity = plantcity;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
