package sadi.enrollment;

import sadi.course.Course;

import java.util.List;

public abstract class StudentEnrollmentManager {
    public abstract void enrol(Course course, String semester);
    public abstract void drop(Course course, String semester);
    public final void update(Course oldcourse, Course newCourse, String semester)
    {   /* Template method */
        drop(oldcourse,semester);
        enrol(newCourse,semester);
    }
    public abstract List<Course> getCourses(String semester);
    public abstract List<Course> getAll();
}
