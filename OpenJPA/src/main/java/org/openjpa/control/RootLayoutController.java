package org.openjpa.control;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
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
//         try
//         {
//            JasperReport jasperReport = null;
//            JasperPrint jasperPrint = null;
//            JasperDesign jasperDesign = null;
//            Map parameters = new HashMap();
//            jasperDesign = JRXmlLoader.load("E:\\Katsuki\\jasper\\src\\main\\java\\ListaPersonas.jrxml");
//            jasperReport = JasperCompileManager.compileReport(jasperDesign);
//            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JRBeanCollectionDataSource(Datos.generarDatosPersona()));
//            JasperExportManager.exportReportToPdfFile(jasperPrint,"target\\ListaPersonas.pdf");
//            JasperViewer.viewReport(jasperPrint);
//            
//        } catch (Exception ex){
//            System.out.println("EXCEPTION: "+ ex);
//        }  
    }
}
