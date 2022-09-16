package com.i2i.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class CreateConnection {

        private static Connection connection = null;
        private static String URL = "jdbc:mysql://localhost:3306/sanjai";
        private static String USERNAME = "root";
        private static String PASSWORD = "Sanjai@1999";  
        private static CreateConnection createConnection;

        private CreateConnection() {
            try{
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public static Connection getInstance() throws SQLException {
            if(connection == null || connection.isClosed() ) {
               createConnection = new CreateConnection();
            }

            return connection;
        }
}