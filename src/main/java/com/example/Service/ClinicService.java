package com.example.Service;

import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Entity.Clinic;
import com.example.Entity.ClinicInventory;
import com.example.Entity.Medication;
import com.example.Repository.ClinicInventoryRepository;
import com.example.Repository.ClinicRepository;
import com.example.Repository.MedicationsReposiory;

@Service
public class ClinicService

{
	@Autowired
	private ClinicRepository clinicRepository;

	@Autowired
	private ClinicInventoryRepository clinicInventoryRepository;

	@Autowired
	private MedicationsReposiory medicationRepository;

	@SuppressWarnings("unlikely-arg-type")
	public String processExcelFile(MultipartFile file) throws IOException {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowsCount = sheet.getPhysicalNumberOfRows();
			if (rowsCount < 2) {
				return "No data found in this file.";
			} else {
				Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false).skip(1);
				List<Row> listOfRows = rowStream.collect(Collectors.toList());
				List<String  > medications=new ArrayList<>();
				medications = medicationRepository.getAllMedicationNames();
				int rowNumber = 0;
				for (Row row : listOfRows) {
					rowNumber++;
					Clinic clinic = new Clinic();
					ClinicInventory clinicInventory=new ClinicInventory();
//				StringBuilder errorMessages=new StringBuilder();
//				governmentRecognisedPin
					if (null != row.getCell(0) && null != row.getCell(0).getStringCellValue()
							&& "" != row.getCell(0).getStringCellValue().trim()) {
						clinic.setGovernmentRecognisedPin(row.getCell(0).getStringCellValue());

					}
					if (null != row.getCell(1) && 0 != row.getCell(1).getNumericCellValue()) {
						clinic.setLatitude(row.getCell(1).getNumericCellValue());

					}
					if (null != row.getCell(2) && 0 != row.getCell(2).getNumericCellValue()) {
						clinic.setLongitude(row.getCell(2).getNumericCellValue());

					}
					if (null != row.getCell(3) && null != row.getCell(3).getStringCellValue()
							&& "" != row.getCell(3).getStringCellValue().trim()) {
//						medications.setMedicationName(row.getCell(3).getStringCellValue());
						if (medications != null) {
							if (!medications.contains(row.getCell(3).getStringCellValue())) {
								return "Sorry your record medication name is wrong at " + rowNumber;
							}
						}
					}
					if (null != row.getCell(4) && 0 != row.getCell(4).getNumericCellValue()) {
						clinicInventory.setQuantityAvailable((int) row.getCell(4).getNumericCellValue());

					}
					if (null != row.getCell(5) && null != row.getCell(5).getStringCellValue()
							&& "" != row.getCell(5).getStringCellValue().trim()) {
						clinic.setName(row.getCell(5).getStringCellValue());

					}
					if (null != row.getCell(6) && null != row.getCell(6).getStringCellValue()
							&& "" != row.getCell(6).getStringCellValue().trim()) {
						clinic.setPhoneNumber(row.getCell(6).getStringCellValue());

					}
					try {
					if (row.getCell(7).getBooleanCellValue()) {
						clinic.setAppointmentsAvailable(true);

					}
					}catch(Exception e) {
						clinic.setAppointmentsAvailable(false);
					}
					if(clinic.getName() != null) {
						clinicRepository.save(clinic);
					clinicInventory.setClinic(clinic);
					
					Medication medication= medicationRepository.findByMedicationNmae(row.getCell(3).getStringCellValue());
					clinicInventory.setMedication(medication);
					clinicInventoryRepository.save(clinicInventory);}
				}
			}
			return "Success";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getCause());
			return e.getMessage();
		}
	}

}