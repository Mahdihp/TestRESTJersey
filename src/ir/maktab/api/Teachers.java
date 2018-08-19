package ir.maktab.api;

import ir.maktab.api.entity.Entity;
import ir.maktab.api.entity.MessageBody;
import ir.maktab.api.entity.Teacher;
import ir.maktab.api.service.dao.TeacherDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/teacher")
public class Teachers {

    TeacherDao teacherDao = null;

    public Teachers() {
        try {
            teacherDao = new TeacherDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/teachersname")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Teacher> getPersonByName(@QueryParam("name") String name) {
        try {
            return teacherDao.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/teachers2")
    @Produces(MediaType.APPLICATION_JSON)
    public Entity getPersonByIdQuery(@QueryParam("id") int id) {
        try {
            return teacherDao.read(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/teachers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entity getPersonById(@PathParam("id") int id) {
        try {
            return teacherDao.read(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Teacher> getTeacher() {
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
        return new MessageBody(200, "Success Insert").toJson();
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") int id) {
        try {
            teacherDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new MessageBody(200, "Success Insert").toJson();
    }

    @POST
    @Path("/update")
    @Produces({MediaType.TEXT_PLAIN})
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Teacher teacher) {
        try {
            teacherDao.update(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new MessageBody(200, "Success Insert").toJson();
    }

}
