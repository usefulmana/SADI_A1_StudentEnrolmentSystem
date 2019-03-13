package sadi.course;

import sadi.cor.Chain;

import java.util.ArrayList;
import java.util.List;

public class CourseListC implements CourseList, Chain {
    private String semester = "C";
    private static List<Course> courseList = new ArrayList<>();
    private static CourseListC INSTANCE = new CourseListC();
    private Chain nextInChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void addCourse(List<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getAvailability().contains("C")) {
                courseList.add(courses.get(i));
            }
        }
    }

    private CourseListC(){}

    public static CourseListC getINSTANCE() {
        return INSTANCE;
    }

    public List<Course> getCourseList() {
        return courseList;
    }



    public static void printCourseList() {
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(courseList.get(i).getCourseId() + " - " + courseList.get(i).getCourseName()+ " - " +
                    courseList.get(i).getCourseId());
        }
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
    public void removeCourse(List<Course> courses, String courseID) {
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getAvailability().contains(this.semester) && courses.get(i).getAvailability().equals(courseID))
            {
                courseList.remove(courses.get(i));
            }
        }
    }


}
