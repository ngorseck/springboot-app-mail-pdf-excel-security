package sn.isi.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import sn.isi.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public class ExcelUserListReportView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"user_list.xls\"");
		
		@SuppressWarnings("unused")
		List<User> list = (List<User>) model.get("usersList");
		Sheet sheet = workbook.createSheet("liste des users");
		//Entete de ligne
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("NOM");
		header.createCell(1).setCellValue("PRENOM");
		header.createCell(2).setCellValue("EMAIL");
		header.createCell(3).setCellValue("ETAT COMPTE");
		int rowNumber = 1;
		for(User user : list) {
			Row row = sheet.createRow(rowNumber++);
			row.createCell(0).setCellValue(user.getNom());
			row.createCell(1).setCellValue(user.getPrenom());
			row.createCell(2).setCellValue(user.getEmail());
			row.createCell(3).setCellValue(user.getEtat());
		}
	}

}
