/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.StudentMngSys.view.controller;

import java.awt.Dimension;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperDesignViewer;

/**
 * FXML Controller class
 *
 * @author oshan
 */
public class JasperReportViewerController implements Initializable {

    
    @FXML
    AnchorPane pnMain;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void setView(JasperPrint print){
        
        
        
        SwingNode node=new SwingNode();
        
        JRViewer view = new JRViewer(print);
        view.setZoomRatio((float) 0.54);
        view.setPreferredSize(new Dimension(print.getPageWidth(), print.getPageHeight()));
        view.requestFocusInWindow();
        view.repaint();
        view.revalidate();
        JPanel pnl=new JPanel();
        pnl.add(view);
        pnl.setSize(print.getPageWidth(), print.getPageHeight());
        node.setContent(pnl);
        
        node.requestFocus();
        
        ((Stage)(pnMain.getScene().getWindow())).getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/resources/icons/LogoIcon.png")));
        //((Stage)(pnMain.getScene().getWindow())).initStyle(StageStyle.UNDECORATED);
       
        System.out.println("Width : " + print.getPageWidth());
        System.err.println("Height : "+ print.getPageHeight());
        pnMain.getChildren().add(node);
    }
    
}
