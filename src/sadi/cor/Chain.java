package sadi.cor;

import sadi.course.Course;

import java.util.List;

public interface Chain {
    public void setNextChain(Chain nextChain);
    public void addCourse(List<Course> courses);
}
