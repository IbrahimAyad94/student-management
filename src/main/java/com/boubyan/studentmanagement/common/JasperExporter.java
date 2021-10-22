package com.boubyan.studentmanagement.common;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class JasperExporter {

	@Autowired
	private DataSource dataSource;

	public void exportPdfToFile(String templatePath, String pdfPath, Map<String, Object> params) {
		JasperPrint print = fillReport(templatePath, params);
		try {
			JasperExportManager.exportReportToPdfFile(print, pdfPath);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private JasperPrint fillReport(String templatePath, Map<String, Object> params) {
		JasperPrint jasperPrint = null;
		Path path = Paths.get(templatePath);
		try {
			JasperReport template = (JasperReport) JRLoader.loadObject(Files.newInputStream(path));
			jasperPrint = JasperFillManager.fillReport(template, params, dataSource.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jasperPrint;
	}
}
