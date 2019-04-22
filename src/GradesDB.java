import java.io.File;
import java.util.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GradesDB {
	private Workbook workbook;
	
	GradesDB(String excell) throws Exception {
		workbook = WorkbookFactory.create(new File(excell));
	}
	
	public int getNumStudents() {
		return workbook.getSheet("StudentsInfo").getPhysicalNumberOfRows();
	}
	
	public int getNumAssignments() {
		//Dummy return
		return 0;
	}
	
	public int getNumProjects() {
		//Dummy return
		return 0;
	}
	
	public HashSet<Student> getStudents() {
		//Dummy return
		return null;
	}
	
	public Student getStudentByName(String name) {
		//Dummy return
		return null;
	}
	
	public Student getStudentByID(String id) {
		//Dummy return
		return null;
	}
}
