package sadi.person;

import sadi.iterator.MyIterator;

import java.util.ArrayList;
import java.util.List;

public class StudentList implements MyIterator {
    // StudentList object store all student object
    private static StudentList INSTANCE = new StudentList();
    private static List<Student> studentList = new ArrayList<>();
    private int item=0;

    private StudentList(){}

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static Student searchStudent(String studentID)
    {  // Search for a student using ID
        for (int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getId().equals(studentID))
            {
                return studentList.get(i);
            }
        }
        return null;
    }

    public static void printStudentInfo(){
        // Displaying relevant info of all students
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i).getId() + "         " + studentList.get(i).getName());
        }
    }

    public static StudentList getINSTANCE() {
        return INSTANCE;
    }

    public void addStudent(Student student)
    {
        studentList.add(student);
    }

    public void removeStudent(Student student)
    {
        studentList.remove(student);
    }

    /* Iterator*/
    @Override
    public boolean hasNext() {
        if(item >= studentList.size())
        {
            item = 0;
            return false;
        }
        return true;
    }

    @Override
    public Student next() {
        return studentList.get(item++);
    }

}
