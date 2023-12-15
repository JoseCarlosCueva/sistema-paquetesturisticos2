package com.uth.hn.service;

import java.io.File;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGenerator {
	
	//INDICA DONDE ESTÁ GUARDADO EL REPORTE EN PDF FINAL
			private String reportPath;
			
			//SIRVE PARA GENERAR Y LLENAR EL REPORTE EN PDF
			public boolean generarReportePDF(String nombreReporte, Map<String, Object> parametros, JRDataSource datasource) {
				boolean reporteGenerado = false;
				try {
					//CARGANDO ARCHIVO JASPER EN MEMORIA
					JasperReport reporte = (JasperReport)JRLoader.loadObjectFromFile(obtenerUbicacionReporte(nombreReporte+".jasper"));
					//OBJETO QUE SIRVE PARA LLENAR EL REPORTE CON LA INFORMACIÓN DINÁMICA
					JasperPrint impresora = JasperFillManager.fillReport(reporte, parametros, datasource);
					String rutaPDF = generarUbicacionReporte() + nombreReporte + ".pdf";
					reportPath = rutaPDF;
					//SE GUARDA EL REPORTE EN LA UBICACIÓN GENERADA
					JasperExportManager.exportReportToPdfFile(impresora, rutaPDF);
					reporteGenerado = true;
				}catch(Exception ex) {
					ex.printStackTrace();
					reporteGenerado = false;
				}
				
				
				return reporteGenerado;
			}
			
			//GENERA UNA UBICACIÓN EN MI MAQUINA DONDE VA A GUARDAR EL ARCHIVO GENERAADO
			private String generarUbicacionReporte() {
				String path = null;
				try {
					//SE CREA COMO TEMPORAL PARA QUE EL SO LO BORRE CUANDO CONSIDERE NECESARIO
					//NO NECESITO ESPECIFICAR LA CARPETA EXACTA, EL SO DECIDE
					path = File.createTempFile("temp", ".pdf").getAbsolutePath();
				}catch(Exception e) {
					e.printStackTrace();
				}
				return path;
			}
			
			//OBTIENE LA UBICACIÓN DEL ARCHIVO .JASPERR
			private String obtenerUbicacionReporte(String filename) {
				String path = null;
				try {
					path = ResourceUtils.getFile("classpath:"+filename).getAbsolutePath();
				}catch(Exception e) {
					e.printStackTrace();
				}
				return path;
			}

			//NOS PERMITE CONSULTAR LA UBICACIÓN DEL REPORTE Y VERLO EN PANTALLA
			public String getReportPath() {
				return reportPath;
			}
}
