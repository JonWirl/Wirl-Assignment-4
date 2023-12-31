package com.coderscampus.assignment4;

import java.io.IOException;

public class FileProcessorApplication {

	public static void main(String[] args) throws IOException {
		try {
			StudentService studentService = new StudentService();
			studentService.createStudent();
			System.out.println(".csv files created");
		} finally {}
		

	}
}
