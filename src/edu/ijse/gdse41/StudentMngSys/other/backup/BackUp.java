/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.other.backup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oshan
 */
public class BackUp {
    
    private String dbName,dbUserName,dbPassword;

    public BackUp() {
        Properties dbPro=new Properties();
        File dbFile=new File("dbSettings/settings.properties");
       
        FileReader dbFileReader;
        try {
            dbFileReader = new FileReader(dbFile);
            dbPro.load(dbFileReader);
            dbName=dbPro.getProperty("database");
            dbUserName=dbPro.getProperty("username");
            dbPassword=dbPro.getProperty("password");
//            System.out.println(dbName);
//            System.out.println(dbUserName);
//            System.out.println(dbPassword);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BackUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BackUp.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    public boolean backupDB(String path) throws IOException, InterruptedException {
        path=path+"\\backup.sql";

//        System.out.println(path);
//        System.out.println("A");
        String executeCmd=String.format("mysqldump -u %s -p%s --add-drop-database -B %s -r %s",dbUserName,dbPassword,dbName,path);
//        String executeCmd=String.format("mysqldump -u root -proot --add-drop-database -B sem1Pro -r %s",path);
//        System.out.println("B");
        Process runtimeProcess;
//        System.out.println("C");
        runtimeProcess=Runtime.getRuntime().exec(executeCmd);
//        System.out.println("D");
        int isCompleted=runtimeProcess.waitFor();
//        System.out.println("CC");
        if(isCompleted==0){
//            System.err.println("E");
            return true;
        }
//        System.out.println("F");
        return false;
    }
    
    public boolean restoreDB(String source) throws IOException, InterruptedException{
        String[] executeCmd = new String[]{"mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName,"-e", " source "+source};  
        Process runtimeProcess;  
        runtimeProcess = Runtime.getRuntime().exec(executeCmd);  
        int processComplete = runtimeProcess.waitFor();  
        if (processComplete == 0) {  
//            System.out.println("Backup restored successfully");  
            return true;   
        }
            return false;  
    }  
    
}
