package sadi.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private String courseId;
    private String courseName;
    private int credits;
    private String availability;
    private static List<Course> courseList = new ArrayList<>();

    public Course(String courseId, String courseName, int credits, String availability) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.availability = availability;
        courseList.add(this);
    }

    public static List<Course> getCourseList() {
        return courseList;
    }

    public static void setCourseList(List<Course> courseList) {
        Course.courseList = courseList;
    }


    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getCourseId(), course.getCourseId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseId());
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                '}';
    }
}
