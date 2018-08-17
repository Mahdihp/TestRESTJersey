package ir.maktab.api.service.dao;


import ir.maktab.api.entity.Person;
import ir.maktab.api.entity.Student;
import ir.maktab.api.entity.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao extends AbstractDao {

    public StudentDao() throws Exception {
    }

    @Override
    public void create(Person Person) throws SQLException {
        Student student = (Student) Person;
        String sql = "INSERT INTO student(fname,lname,dept,teacher_id) VALUES(?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, student.getFname());
        ps.setString(2, student.getLname());
        ps.setString(3, student.getDept());
        ps.setInt(4, student.getTeacherId());
        ps.executeUpdate();
    }

    @Override
    public Person read(int id) throws SQLException {
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
    public void update(Person Person) throws SQLException {
        Student student = (Student) Person;
        String sql = "UPDATE student SET fname=?,lname=?,dept=?,teacher_id=? WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, student.getFname());
        ps.setString(2, student.getLname());
        ps.setString(3, student.getDept());
        ps.setInt(4, student.getTeacherId());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM student WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public Person[] readAll() throws SQLException {
        Student[] students = null;
        String sql = "SELECT * FROM student";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.last();// move to last row
        students = new Student[rs.getRow()]; // get row count
        rs.beforeFirst(); // move to first row
        int i = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            String fname = rs.getString("fname");
            String lname = rs.getString("lname");
            String dept = rs.getString("dept");
            int teacher_id = rs.getInt("teacher_id");
            students[i] = new Student(id, fname, lname, dept, teacher_id);
            i++;
        }
        return students;
    }

    @Override
    public void empty() throws SQLException {
        String sql = "DELETE FROM student";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate();
    }
}
