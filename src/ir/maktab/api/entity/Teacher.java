package ir.maktab.api.entity;

import com.google.gson.Gson;

public class Teacher extends Person{

    private String fname;
    private String lname;
    private String style;

    public Teacher(String fname, String lname, String style) {
        this.fname = fname;
        this.lname = lname;
        this.style = style;
    }

    public String toJson(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }
    public Teacher(int id, String fname, String lname, String style) {
        super(id);
        this.fname = fname;
        this.lname = lname;
        this.style = style;
    }

    public Teacher() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}
