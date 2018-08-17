package ir.maktab.api.service.dao;


import ir.maktab.api.entity.Person;
import ir.maktab.api.entity.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDao extends AbstractDao {

    public TeacherDao() throws Exception {
    }

    @Override
    public void create(Person Person) throws SQLException {

        Teacher teacher = (Teacher) Person;
        String sql = "INSERT INTO teacher(fname,lname,style) VALUES(?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, teacher.getFname());
        ps.setString(2, teacher.getLname());
        ps.setString(3, teacher.getStyle());
        ps.executeUpdate();

    }

    @Override
    public Person read(int id) throws SQLException {
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
    public void update(Person Person) throws SQLException {
        Teacher writerEntity = (Teacher) Person;
        String sql = "UPDATE teacher SET fname=?,lname=?,style=? WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, writerEntity.getFname());
        ps.setString(2, writerEntity.getLname());
        ps.setString(3, writerEntity.getStyle());
        ps.setInt(4, writerEntity.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM teacher WHERE id=?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public Person[] readAll() throws SQLException {
        String sql = "SELECT * FROM teacher";
        Teacher[] teachers = null;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.last();// move to last row
        teachers = new Teacher[rs.getRow()]; // get row count
        rs.beforeFirst(); // move to first row
        int i = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            String fname = rs.getString("fname");
            String lname = rs.getString("lname");
            String style = rs.getString("style");
            teachers[i] = new Teacher(id, fname, lname, style);
            i++;
        }
        return teachers;
    }

    @Override
    public void empty() throws SQLException {
        String sql = "DELETE FROM teacher";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate();
    }
}
