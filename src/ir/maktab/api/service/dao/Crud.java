package ir.maktab.api.service.dao;


import ir.maktab.api.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface Crud<T extends Entity, I> {

    void create(T entity) throws SQLException;

    T read(I id) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(I id) throws SQLException;

    //    Entity[] readAll() throws SQLException;
    List<T> readAll() throws SQLException;

    void empty() throws SQLException;
}
