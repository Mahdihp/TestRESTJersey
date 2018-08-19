package ir.maktab.api.service.dao;


import ir.maktab.api.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCrud extends Dao<Student, Integer> {

    public StudentCrud() throws Exception {
    }

    @Override
    public void create(Student student) throws SQLException {
        String sql = "INSERT INTO student(fname,lname,dept,teacher_id) VALUES(?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, student.getFname());
        ps.setString(2, student.getLname());
        ps.setString(3, student.getDept());
        ps.setInt(4, student.getTeacherId());
        ps.executeUpdate();
    }

    @Override
    public Student read(Integer id) throws SQLException {
        String sql = "SELECT * FROM student WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int idStudent = rs.getInt("id");
        String fname = rs.getString("fname");
        String lname = rs.getString("lname");
        String dept = rs.getString("dept");
        int teacher_id = rs.getInt("teacher_id");
        return new Student(idStudent, fname, lname, dept, teacher_id);
    }

    @Override
    public void update(Student student) throws SQLException {
        String sql = "UPDATE student SET fname=?,lname=?,dept=?,teacher_id=? WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, student.getFname());
        ps.setString(2, student.getLname());
        ps.setString(3, student.getDept());
        ps.setInt(4, student.getTeacherId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM student WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Student> readAll() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String fname = rs.getString("fname");
            String lname = rs.getString("lname");
            String dept = rs.getString("dept");
            int teacher_id = rs.getInt("teacher_id");
            studentList.add(new Student(id, fname, lname, dept, teacher_id));
        }
        return studentList;
    }

    @Override
    public void empty() throws SQLException {
        String sql = "DELETE FROM student";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate();
    }
}
