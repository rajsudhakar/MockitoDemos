package com.bookapp.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)

class CourseTest {
Course course;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		course = new Course();
	}

	@AfterEach
	void tearDown() throws Exception {
		course = null;
	}
	@Spy
	Course courses;
	
	@Mock
	Course mcourse;

	@Test
	void testShowCourses() {
		// the original method is called
		List<String> courses = course.showCourses();
		System.out.println(courses);
		
	}
	@Test
	void testShowCoursesMock() {
		// call the proxy method using mock object and return some data
		when(mcourse.showCourses()).thenReturn(Arrays.asList("java"));
		List<String> courses = mcourse.showCourses();
		System.out.println(courses);
	}

}
