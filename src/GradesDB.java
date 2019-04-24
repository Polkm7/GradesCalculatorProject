import java.io.File;
import java.util.HashSet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GradesDB {
	private Workbook workbook;
	
	//Constructor sets Workbook class variable with passed String
	GradesDB(String excell) throws Exception {
		workbook = WorkbookFactory.create(new File(excell));
	}
	
	public int getNumStudents() {
		//Return the number of rows in the "StudentsInfo" sheet - 1 (the first row contains header information)
		return workbook.getSheet("StudentsInfo").getPhysicalNumberOfRows() - 1;
	}
	
	public int getNumAssignments() {
		int numAssignments = 0;
		
		Sheet individualGradesSheet = workbook.getSheet("IndividualGrades");
		
		//The first row contains header information we'll use to determine the number of assignments
		Row firstRow = individualGradesSheet.getRow(0);
		
		//Loop through cells of the first row and increment the counter if a cell contains "ASSIGNMENT" String
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
		
		//The first row contains header information we'll use to determine the number of projects
		Row firstRow = individualContribsSheet.getRow(0);
		
		//Loop through cells of the first row and increment the counter if a cell contains "PROJECT" String
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
		
		//This row contains header information about subsequent rows
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
					student.setName(cell.getStringCellValue());
					continue;
				}
				
				//Set the students ID if that is the correct corresponding cell
				if(cell.getColumnIndex() == idCellIndex) {
					student.setId(Integer.toString((int) cell.getNumericCellValue()));
					continue;
				}
			}
			
			studentsHashSet.add(student);
		}
		
		return studentsHashSet;
	}
	
	public Student getStudentByName(String name) {
		Student student = new Student();
		
		Sheet studentsInfoSheet = workbook.getSheet("StudentsInfo");
		
		//This row contains header information about subsequent rows
		Row firstRow = studentsInfoSheet.getRow(0);
		
		//Use these to determine if cells in subsequent rows describe a name or ID
		int nameCellIndex = 0;
		int idCellIndex = 0;
		
		//Loop through the first row and determine the index of cells with name and ID data
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
			//Set the student info if it is has the correct name
			if(studentsInfoSheet.getRow(i).getCell(nameCellIndex).getStringCellValue().equals(name)) {
				student.setName(name);
				student.setId(Integer.toString((int) studentsInfoSheet.getRow(i).getCell(idCellIndex).getNumericCellValue()));
				
				break;
			}
		}
		
		//Now we get attendance data
		
		Sheet attendanceSheet = workbook.getSheet("Attendance");
		
		//Loop through rows starting at row of index 1 to avoid the first row (used above)
		for(int i = 1; i < attendanceSheet.getPhysicalNumberOfRows(); i++) {
			//Set the student attendance if it is has the correct name
			if(attendanceSheet.getRow(i).getCell(0).getStringCellValue().equals(name)) {
				student.setAttendance((int) attendanceSheet.getRow(i).getCell(1).getNumericCellValue());
				break;
			}
		}
		
		return student;
	}
	
	public Student getStudentByID(String id) {
		Student student = new Student();
		
		Sheet studentsInfoSheet = workbook.getSheet("StudentsInfo");
		
		//This row contains information about subsequent rows
		Row firstRow = studentsInfoSheet.getRow(0);
		
		//Use these to determine if cells in subsequent rows describe a name or ID
		int nameCellIndex = 0;
		int idCellIndex = 0;
		
		//Loop through the first row and determine the index of cells with name and ID data
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
			//Set the student info if it is has the correct name
			if(Integer.valueOf(id) == (int) studentsInfoSheet.getRow(i).getCell(idCellIndex).getNumericCellValue()) {
				student.setName(studentsInfoSheet.getRow(i).getCell(nameCellIndex).getStringCellValue());
				student.setId(id);
				
				break;
			}
		}
		
		return student;
	}
}
