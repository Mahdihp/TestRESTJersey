package ir.maktab.api.service.dao;


import ir.maktab.api.entity.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao extends Dao<Teacher, Integer> {

    public TeacherDao() throws Exception {
    }

    public List<Teacher> findByName(String name)throws SQLException  {
        String sql = "SELECT * FROM teacher where fname LIKE ?";
        List<Teacher> teacherList = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,name);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String fname = rs.getString("fname");
            String lname = rs.getString("lname");
            String style = rs.getString("style");
            teacherList.add(new Teacher(id, fname, lname, style));
        }
        return teacherList;
    }

    @Override
    public void create(Teacher teacher) throws SQLException {

        String sql = "INSERT INTO teacher(fname,lname,style) VALUES(?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, teacher.getFname());
        ps.setString(2, teacher.getLname());
        ps.setString(3, teacher.getStyle());
        ps.executeUpdate();

    }


    @Override
    public Teacher read(Integer id) throws SQLException {
        String sql = "SELECT * FROM teacher WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int idWriter = rs.getInt("id");
        String fname = rs.getString("fname");
        String lname = rs.getString("lname");
        String style = rs.getString("style");
        return new Teacher(idWriter, fname, lname, style);
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        String sql = "UPDATE teacher SET fname=?,lname=?,style=? WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, teacher.getFname());
        ps.setString(2, teacher.getLname());
        ps.setString(3, teacher.getStyle());
        ps.setInt(4, teacher.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM teacher WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Teacher> readAll() throws SQLException {
        String sql = "SELECT * FROM teacher";
        List<Teacher> teacherList = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String fname = rs.getString("fname");
            String lname = rs.getString("lname");
            String style = rs.getString("style");
            teacherList.add(new Teacher(id, fname, lname, style));
        }
        return teacherList;
    }

    @Override
    public void empty() throws SQLException {
        String sql = "DELETE FROM teacher";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate();
    }
}
