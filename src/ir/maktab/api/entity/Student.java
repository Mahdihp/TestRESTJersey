package ir.maktab.api.entity;

public class Student extends Entity {

    private String fname;
    private String lname;
    private String dept;
    private int teacherId;

    public Student(String fname, String lname, String dept, int teacherId) {
        this.fname = fname;
        this.lname = lname;
        this.dept = dept;
        this.teacherId = teacherId;
    }

    public Student(int id, String fname, String lname, String dept, int teacherId) {
        super(id);
        this.fname = fname;
        this.lname = lname;
        this.dept = dept;
        this.teacherId = teacherId;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
