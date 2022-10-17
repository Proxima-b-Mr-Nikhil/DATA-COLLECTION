package com.vbgroups.vbtantrav1.Model;

public class Plantdetails {
    private String Plantcity;
    private String Plantname;
    private String Planttype;
    private String Plantvisitdate;
    private String Purposeofvisit;
    private String Refname;
    private String Refphone;
    private String id;
    private String latitude;
    private String longitude;

    public Plantdetails(String plantcity, String plantname, String planttype, String plantvisitdate, String purposeofvisit, String refname, String refphone, String id, String latitude, String longitude) {
       this.Plantcity = plantcity;
       this.Plantname = plantname;
        this.Planttype = planttype;
        this.Plantvisitdate = plantvisitdate;
        this.Purposeofvisit = purposeofvisit;
        this.Refname = refname;
        this.Refphone = refphone;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Plantdetails(){

    }

    public String getPlantcity() {
        return Plantcity;
    }

    public void setPlantcity(String plantcity) {
        Plantcity = plantcity;
    }

    public String getPlantname() {
        return Plantname;
    }

    public void setPlantname(String plantname) {
        Plantname = plantname;
    }

    public String getPlanttype() {
        return Planttype;
    }

    public void setPlanttype(String planttype) {
        Planttype = planttype;
    }

    public String getPlantvisitdate() {
        return Plantvisitdate;
    }

    public void setPlantvisitdate(String plantvisitdate) {
        Plantvisitdate = plantvisitdate;
    }

    public String getPurposeofvisit() {
        return Purposeofvisit;
    }

    public void setPurposeofvisit(String purposeofvisit) {
        Purposeofvisit = purposeofvisit;
    }

    public String getRefname() {
        return Refname;
    }

    public void setRefname(String refname) {
        Refname = refname;
    }

    public String getRefphone() {
        return Refphone;
    }

    public void setRefphone(String refphone) {
        Refphone = refphone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
