package com.vbgroups.vbtantrav1.Model;

public class User {
    private  boolean istyping;
    private String id;
    private String name;
    private String phone;
    private String duration;
    private String imageurl;
    private String status;
    private String chatstatus;
    private String email;
    private String editprofile;
    private String job;
    private  String password;

    public User(boolean istyping,String id, String name, String imageurl, String duration, String phone, String status,String chatstatus, String email, String editprofile, String job, String password) {

        this.istyping=istyping;
        this.id = id;
        this.name = name;
        this.imageurl = imageurl;
        this.status = status;
        this.chatstatus = chatstatus;
        this.phone = phone;
        this.duration = duration;
        this.email=email;
        this.editprofile=editprofile;
        this.job=job;
        this.password=password;
    }

    public User() {
    }

    public boolean isIstyping() {
        return istyping;
    }

    public void setIstyping(boolean istyping) {
        this.istyping = istyping;
    }

    public String getChatstatus() {
        return chatstatus;
    }

    public void setChatstatus(String chatstatus) {
        this.chatstatus = chatstatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEditprofile() {
        return editprofile;
    }

    public void setEditprofile(String editprofile) {
        this.editprofile = editprofile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
