package org.openjpa.control;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.openjpa.OpenJPA;

public class RootLayoutController {

    private OpenJPA mainApp;    
    public void setMainApp(OpenJPA mainApp) 
    {
        this.mainApp = mainApp;
    }       
    
    @FXML
    private void Alumnos() {
        mainApp.MostrarVentana("Alumnos");
    }
    
    @FXML
    private void Carreras() {
        mainApp.MostrarVentana("Carrera");
    }
    
    @FXML
    private void Semestres() {
        mainApp.MostrarVentana("Semestres");
    }
    
    @FXML
    private void Asignaturas() {
        mainApp.MostrarVentana("Asignaturas");
    }
    
    @FXML
    private void Inscripciones() {
        mainApp.MostrarVentana("Alumnos");
    }
    
     @FXML
    private void Pagos() {
        mainApp.MostrarVentana("Pago");
    }
        
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
//            mainApp.loadPersonDataFromFile(file);
        }
    }

    @FXML
    private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
//			mainApp.savePersonDataToFile(file);
		}
	}

    @FXML
    private void handleExit() {
        System.exit(0);
    }
    @FXML
    public void Reportes()
    {        
        String url = "jdbc:mariadb://localhost:3306/dbcuetillojpa";
        String usuario = "root";
        String contraseña = "";
        try{  
            Connection connection = DriverManager.getConnection(url, usuario, contraseña);
            try{
            JasperReport jasperReport = null;
            JasperPrint jasperPrint = null;
            JasperDesign jasperDesign = null;            
            jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/org/openjpa/control/ReportePagos.jrxml"));
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, null,connection);
            JasperExportManager.exportReportToPdfFile(jasperPrint,"reportesListaPersonas.pdf");
            JasperViewer view=new JasperViewer(jasperPrint,false);
            //view.setDefaultCloseOperation(1);
            view.setVisible(true);
            
            } catch (Exception ex){
                System.out.println("EXCEPTION: "+ ex);
            }
        }catch(SQLException e){
            System.out.println("error en la conección"+e);
        }
    }
}
