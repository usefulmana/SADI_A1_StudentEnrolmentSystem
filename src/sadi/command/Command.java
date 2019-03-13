package sadi.command;

import sadi.course.Course;

public interface Command {
    public void executeEnrol(Course course, String semester);
    public void undoEnrol(Course course, String semester);
    public void executeDrop(Course course, String semester);
    public void undoDrop(Course course, String semester);
}
