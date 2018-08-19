package ir.maktab.api;

import ir.maktab.api.entity.Entity;
import ir.maktab.api.entity.Student;
import ir.maktab.api.entity.Teacher;
import ir.maktab.api.service.dao.StudentCrud;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/student")
public class Students {

    StudentCrud studentDao = null;

    public Students() {
        try {
            studentDao =new StudentCrud();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/teachers2")
    @Produces(MediaType.APPLICATION_JSON)
    public Entity getPersonByIdQuery(@QueryParam("id")int id){
        try {
            return studentDao.read(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/teachers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entity getPersonById(@PathParam("id")int id){
        try {
            return studentDao.read(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/getstudent")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudent() {
        try {
            return studentDao.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(Student student) {
        try {
            studentDao.create(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Successfully Insert.";
    }
    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id")int id){
        try {
            studentDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Successfully Delete.";
    }

    @POST
    @Path("/update")
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Student student){
        try {
            studentDao.update(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Successfully Update.";
    }

}
