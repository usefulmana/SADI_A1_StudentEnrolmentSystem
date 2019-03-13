package sadi.person;

public class StudentFactory {
    // Simple Student Factory
    public static Student createStudent(String id,String name)
    {
        return new Student(id,name);
    }
}
