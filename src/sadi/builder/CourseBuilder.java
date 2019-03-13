package sadi.builder;

import sadi.course.Course;

public final class CourseBuilder {
    // Builder of Course
    private String courseId;
    private String courseName;
    private int credits;
    private String availability;

    public CourseBuilder(String courseId, String courseName, int credits, String availability) {
        this.courseId = courseId;
        this.courseName =  courseName;
        this.credits = credits;
        this.availability = availability;
    }

    public Course build() {
        return new Course(courseId, courseName, credits,availability);
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
