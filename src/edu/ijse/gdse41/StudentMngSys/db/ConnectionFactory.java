/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oshan
 */
public class ConnectionFactory {
    private static ConnectionFactory dbConnection;
    private Connection connection;
    
    private ConnectionFactory(){
        Properties dbPro=new Properties();
        File dbFile=new File("dbSettings/settings.properties");
        try {
            FileReader dbFileReader=new FileReader(dbFile);
            dbPro.load(dbFileReader);
            String setDB=String.format("jdbc:mysql://%s/%s", dbPro.getProperty("ip"),dbPro.getProperty("database"));
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(setDB,dbPro.getProperty("username"),dbPro.getProperty("password"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @return the dbConnection
     */
    public static ConnectionFactory getInstance(){
        if(dbConnection==null){
            dbConnection=new ConnectionFactory();
        }
        return dbConnection;
    }
    
}
