package ir.maktab.api;

import ir.maktab.api.entity.Person;
import ir.maktab.api.entity.Teacher;
import ir.maktab.api.entity.User;
import ir.maktab.api.service.dao.TeacherDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Target;
import java.sql.SQLException;

@Path("/teacher")
public class Teachers {

    TeacherDao teacherDao = null;

    public Teachers() {
        try {
            teacherDao=new TeacherDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GET
    @Path("/getteacher")
    @Produces(MediaType.APPLICATION_JSON)
    public Person[] getTeacher() {
        try {
            return teacherDao.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(Teacher teacher) {
        try {
            teacherDao.create(teacher);
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
            teacherDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Successfully Delete.";
    }

    @POST
    @Path("/update")
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Teacher teacher){
        try {
            teacherDao.update(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Successfully Update.";
    }

}
