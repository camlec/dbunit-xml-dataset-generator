import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String DB_URL = "localhost";
    private static final int DB_PORT = 3306;
    private static final String DB_NAME = "dbname";
    private static final String DB_USER = "dbuser";
    private static final String DB_PWD = "dbpwd";
    private static final String JDBC_URL = "jdbc:mysql://" + DB_URL + ":" + DB_PORT + "/" + DB_NAME;


    public static void main(String[] args) throws Exception {
        Class.forName(DRIVER);
        Connection jdbcConnection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PWD);
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        QueryDataSet dataSet = new QueryDataSet(connection);
        dataSet.addTable("table_1", "SELECT * FROM table_1 where id = 1");
        dataSet.addTable("table_2", "SELECT * FROM table_2");

        FlatXmlDataSet.write(dataSet, new FileOutputStream("dataset.xml"));

        jdbcConnection.close();
    }
}