SADI – 2019A – Assignment I
Author: Nguyen Le Bao Anh
Student ID: s3616128
Created on: 28/02/19
Last updated: 14/03/19
Github repo: https://github.com/usefulmana/SADI_A1_EnrolmentApp

    I. Requirements
	Build a Java console application that fulfills the following requirements:
    • Will add/update/delete student enrolments
    • Ask for students’ ID, courses, and smester that students need to enroll.
    • Print enrolment details of each student
    • Print enrollment for all students for 1 semester.
    • Use at least 10 design patterns. The following patterns must be used:
        - Singleton
        - Builder
        - Visitor
        - Chain of responsibilities
        - Command
    II. Design Patterns Usage
    1. Singleton
       Classes: CourseListA, CourseListB, CourseListC, StudentList
       Singleton pattern is used to ensure a class has only one instance, and provide a global point of access to it.
       In this case, each of the listed classes above only need one instance. For example, I only need one list of students in the school.
       Any additional instance will just be a waste of resources, and increases chance of confusion while coding.

    2. Builder
       Classes: CourseBuilder
       The Builder design pattern is used to build a complex object step by step and the final step (build()) will return the object.
       In this case, I used this pattern to build different courses from the Course class which has 3 main attributes: courseID, courseName, credits.
               
    3. Chain of Responsibilities
       Interface: Chain
       Classes: CourseListA, CourseListB, CourseListC
       I used this pattern to quickly add Course objects to appropriate classes. Some courses are available in multiple semesters, and some does not. To ensure good filtering,
       the list of all cources are passed through CourseListA, then CourseListB, and finally CourseListC. Each of the aforementioned classes has different responsibilities. For example,
       CourseListA can only contains courses that are available in semester A.

    4. Iterator
       Interface: MyIterator
       Classes: StudentList
       This pattern is used to access elements of a collection object sequentially. I used this pattern to go through all the student objects contained in the StudentList class.

    5. Observer
       Class: Student(Observer), StudentEnrollment(Observable)
       This design pattern establishes communication between objects: observer and observable. If the state of the observable objects changes, the observers
       will be notified immediately.
       In this assignment, the observers are the Student objects and the observerbles are their respective StudentEnrollment objects. Actions such as enroll() and drop()
       will trigger a confirmation message to the Student objects that their enrollment details have been changed.

    6. Flyweight
       Class: StudentEnrollment
       The main purpose of the flyweight pattern is to prevent unnecessary additional creation of objects to save memory.
       Additionally, in my case, I also used this pattern as a quick way to store and search for any StudentEnrollment object that is associated with a particular Student object. 
       I created a Map with String studentID as key, and a corresponding StudenEnrollment object as value (see printAllEnrolment() method).
 
    7. Command
       Interface: Command
       Class: StudentEnrollment
       The command pattern is used to encapsulate all information needed to perform an action. In my case, I used it to encapsulate the enrol() and drop() methods in StudentEnrolment Class.
       As for the undo functionality, there is no point to save previous state in a separate class. For example, the "undo" of enrol() is drop() and vice versa. 
       

    8. Factory
       Class: StudentFactory
       I used the factory method over the normal constructors to make my code cleaner and less decoupled   

    9. Visitor
       Interface: Visitor, Visitable
       Class: StudentEnrollmentVisitor(Visitor), StudentEnrollment(Visitable)
       This pattern represents an operation to be performed on the elements of an object structure. You do not need to change the current existing class if this pattern is used.
       In this case, I used it to provide the user the ability to drop all current enrollments of a student at once. 

    10. Template Method
       Class: StudentEnrollmentManager(abstract)
       This pattern is used to create a method stub and deferring some of the steps of implementation to the subclasses.
       In my case, the template method is the update() method. It consists of drop() and enrol() methods. Regardless of how drop() and enrol() are implementated,
       the update process will always involve dropping a course and enroll in another course in its place.
