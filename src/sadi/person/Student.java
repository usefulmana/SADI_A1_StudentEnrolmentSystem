package sadi.person;

import sadi.enrollment.StudentEnrollment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Student{
    private String id;
    private String name;
    private Date birthday;
    private StudentList studentList= StudentList.getINSTANCE();


    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        studentList.addStudent(this); // Automatically add Student object to StudentList object
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        // Parse string into Date object
        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            this.birthday = simpleDateFormat.parse(birthday);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    /*Observer pattern methods*/
    public void subscribe()
    {
        StudentEnrollment.getStudentSubscribers().add(this);
    }

    public void unsubscribe()
    {
        StudentEnrollment.getStudentSubscribers().remove(this);
    }


}
