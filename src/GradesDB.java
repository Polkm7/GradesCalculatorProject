import java.io.File;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GradesDB {
	private Workbook workbook;
	
	GradesDB(String excell) throws Exception {
		workbook = WorkbookFactory.create(new File(excell));
	}
	
	public int getNumStudents()
	{
		return workbook.getSheet("StudentsInfo").getPhysicalNumberOfRows() - 1;
	}
	
	public int getNumAssignments() {
		int numAssignments = 0;
		
		Sheet individualGradesSheet = workbook.getSheet("IndividualGrades");
		
		Row firstRow = individualGradesSheet.getRow(0);
		
		for(Cell cell : firstRow) {
			if(cell.getStringCellValue().contains("ASSIGNMENT")) {
				numAssignments++;
			}
		}
		
		return numAssignments;
	}
	
	public int getNumProjects() {
		int numProjects = 0;
		
		Sheet individualContribsSheet = workbook.getSheet("IndividualContribs");
		
		Row firstRow = individualContribsSheet.getRow(0);
		
		for(Cell cell : firstRow) {
			if(cell.getStringCellValue().contains("PROJECT")) {
				numProjects++;
			}
		}
		
		return numProjects;
	}
	
	public HashSet<Student> getStudents() {
		HashSet<Student> studentsHashSet = new HashSet<Student>();
		
		Sheet studentsInfoSheet = workbook.getSheet("StudentsInfo");
		
		//This row contains information about subsequent rows
		Row firstRow = studentsInfoSheet.getRow(0);
		
		//Use these to determine if cells in subsequent rows describe a name or ID
		int nameCellIndex = 0;
		int idCellIndex = 0;
		
		//Loop through the first row and determine the indecies of cells with name and ID data
		for(int i = 0; i < firstRow.getPhysicalNumberOfCells(); i++) {
			switch(firstRow.getCell(i).getStringCellValue()) {
				case "NAME":
					nameCellIndex = i;
					break;
				case "ID":
					idCellIndex = i;
					break;
			}
		}
		
		//Loop through rows starting at row of index 1 to avoid the first row (used above)
		for(int i = 1; i < studentsInfoSheet.getPhysicalNumberOfRows(); i++) {
			Student student = new Student();
			
			//Loop through cells in the row
			for(Cell cell : studentsInfoSheet.getRow(i)) {
				//Set the students name if that is the correct corresponding cell
				if(cell.getColumnIndex() == nameCellIndex) {
					//Requires complete Student class
					//student.setName(cell.getStringCellValue());
					continue;
				}
				
				//Set the students ID if that is the correct corresponding cell
				if(cell.getColumnIndex() == idCellIndex) {
					//Requires complete Student class
					//student.setID(cell.getStringCellValue());
					continue;
				}
			}
			
			studentsHashSet.add(student);
		}
		
		return studentsHashSet;
	}
	
	public Student getStudentByName(String name) {
		Sheet studentsInfoSheet = workbook.getSheet("StudentsInfo");
		
		//This row contains information about subsequent rows
		Row firstRow = studentsInfoSheet.getRow(0);
		
		//Use these to determine if cells in subsequent rows describe a name or ID
		int nameCellIndex = 0;
		int idCellIndex = 0;
		
		//Loop through the first row and determine the index of cells with name data
		for(int i = 0; i < firstRow.getPhysicalNumberOfCells(); i++) {
			switch(firstRow.getCell(i).getStringCellValue()) {
				case "NAME":
					nameCellIndex = i;
					break;
				case "ID":
					idCellIndex = i;
					break;
			}
		}
		
		//Loop through rows starting at row of index 1 to avoid the first row (used above)
		for(int i = 1; i < studentsInfoSheet.getPhysicalNumberOfRows(); i++) {
			if(studentsInfoSheet.getRow(i).getCell(nameCellIndex).getStringCellValue().equals(name)) {
				//Requires complete Student class
				//return new Student(name, studentsInfoSheet.getRow(i).getCell(idCellIndex).getStringCellValue());
			}
		}
		
		return null;
	}
	
	public Student getStudentByID(String id) {
		Sheet studentsInfoSheet = workbook.getSheet("StudentsInfo");
		
		//This row contains information about subsequent rows
		Row firstRow = studentsInfoSheet.getRow(0);
		
		//Use these to determine if cells in subsequent rows describe a name or ID
		int nameCellIndex = 0;
		int idCellIndex = 0;
		
		//Loop through the first row and determine the index of cells with name data
		for(int i = 0; i < firstRow.getPhysicalNumberOfCells(); i++) {
			switch(firstRow.getCell(i).getStringCellValue()) {
				case "NAME":
					nameCellIndex = i;
					break;
				case "ID":
					idCellIndex = i;
					break;
			}
		}
		
		//Loop through rows starting at row of index 1 to avoid the first row (used above)
		for(int i = 1; i < studentsInfoSheet.getPhysicalNumberOfRows(); i++) {
			if(studentsInfoSheet.getRow(i).getCell(idCellIndex).getStringCellValue().equals(id)) {
				//Requires complete Student class
				//return new Student(studentsInfoSheet.getRow(i).getCell(nameCellIndex).getStringCellValue(), id);
			}
		}
		
		return null;
	}
}
