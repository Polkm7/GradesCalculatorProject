/**
 * Student Class
 * Usage: Create Student Object with the following attributes:
 *  Name
 *  ID
 *  Email
 *  Attendance
 * */

import java.util.*;

public class Student {

    private String name;
    private int id;
    private String email;
    private int attendance;

    public String getName(){
        return name;
    }
    public String setName(String newName){
        return this.name = newName;
    }

    public int getID(){
        return id;
    }
    public int setID(int newID){
        return this.id = newID;
    }

    public String getEmail(){
        return email;
    }
    public String setEmail(String newEmail){
        return this.email=newEmail;
    }

    public int getAttendence(){
        return attendance;
    }
    public int setAttendence(int newAttendence){
        return this.attendance = newAttendence;
    }

    Student(){

    }

    Student(String studentName,int studentID,String studentEmail,int studentAttendence){
        this.name = studentName;
        this.id = studentID;
        this.email=studentEmail;
        this.attendance = studentAttendence;
    }
}
