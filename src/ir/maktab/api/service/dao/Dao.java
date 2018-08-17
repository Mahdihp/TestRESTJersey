package ir.maktab.api.service.dao;


import ir.maktab.api.entity.Person;

import java.sql.SQLException;

public interface Dao {

    void create(Person Person) throws SQLException;

    Person read(int id) throws SQLException;

    void update(Person Person) throws SQLException;

    void delete(int id) throws SQLException;

    Person[] readAll() throws SQLException;

    void empty() throws SQLException;
}
