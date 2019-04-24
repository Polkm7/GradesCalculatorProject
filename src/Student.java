/**
 * Student Class
 * Usage: Create Student Object with the following attributes:
 *  Name
 *  Id
 *  Email
 *  Attendance
 * */

public class Student {
    private String name;
    private String id;
    private String email;
    private int attendance;

    public String getName(){
        return name;
    }
    
    public String setName(String newName){
        return this.name = newName;
    }

    public String getId(){
        return id;
    }
    
    public String setId(String newId){
        return this.id = newId;
    }

    public String getEmail(){
        return email;
    }
    
    public String setEmail(String newEmail){
        return this.email=newEmail;
    }

    public int getAttendance(){
        return attendance;
    }
    
    public int setAttendance(int newAttendance){
        return this.attendance = newAttendance;
    }

    Student(){

    }

    Student(String studentName,String studentId,String studentEmail,int studentAttendence){
        this.name = studentName;
        this.id = studentId;
        this.email = studentEmail;
        this.attendance = studentAttendence;
    }
}
