package ir.maktab.api.service.dao;




import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDao implements Dao{

    private static String url = "jdbc:mysql://localhost:3306/library?user=root&password=";
    protected static Connection connection = null;

    public AbstractDao() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url);

    }

}
