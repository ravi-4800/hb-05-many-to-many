package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			
			// create a course
			Course theCourse = new Course("Learn Java - In a better way");
			
			// save the course... and leverage the cascade all:-)
			System.out.println("\nSaving the course...");
			session.save(theCourse);
			System.out.println("Saved course: " + theCourse);
			
			// create students
			Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@gmail.com");
			
			// add students to course
			theCourse.addStudent(tempStudent1);
			theCourse.addStudent(tempStudent2);
			
			// save the students
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students: " + theCourse.getStudents());
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}
