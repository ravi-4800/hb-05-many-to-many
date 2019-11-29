package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndReviewsDemo {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			
			// create a course
			Course theCourse = new Course("Learn Java - In a better way");
			
			// add some reviews
			theCourse.addReview(new Review("Great course...loved it!"));
			theCourse.addReview(new Review("Cool course, job well done"));
			theCourse.addReview(new Review("what a dumb course, you're an idiot!"));
			
			// save the course... and leverage the cascade all:-)
			session.save(theCourse);
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}
