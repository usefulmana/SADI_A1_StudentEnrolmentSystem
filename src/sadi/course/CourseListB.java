package sadi.course;

import sadi.cor.Chain;
import sadi.iterator.MyIterator;

import java.util.ArrayList;
import java.util.List;

public class CourseListB implements CourseList,Chain{
    private String semester = "B";
    private static List<Course> courseList = new ArrayList<>();
    private static CourseListB INSTANCE = new CourseListB();
    private Chain nextInChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void addCourse(List<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getAvailability().contains("B")) {
                courseList.add(courses.get(i));
            }
        }
        nextInChain.addCourse(courses);
    }

    private CourseListB(){}

    public static CourseListB getINSTANCE() {
        return INSTANCE;
    }

    public static Course courseSearch (String courseID){
        for (int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getCourseId().equals(courseID))
            {
                return courseList.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CourseListB{" +
                "courseList=" + courseList +
                '}';
    }


    public static void printCourseList() {
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(courseList.get(i).getCourseId() + " - " + courseList.get(i).getCourseName()+ " - " +
                    courseList.get(i).getCourseId());
        }
    }

    @Override
    public void removeCourse(List<Course> courses, String courseID) {
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getAvailability().contains(this.semester) && courses.get(i).getAvailability().equals(courseID))
            {
                courseList.remove(courses.get(i));
            }
        }
    }


    public List<Course> getCourseList() {
        return courseList;
    }
}
