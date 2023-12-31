package com.coderscampus.assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class StudentService {
	
//	Methods
	
	private String determineCoursePrefix(String course) {
		return course.split("\\s+")[0];
	}
	
//	Changing course prefixes to 1, 2, 3 for file names
	
	private int determineCourseNumber(String coursePrefix) {
		switch (coursePrefix) {
		case "COMPSCI":
			return 1;
		case "APMTH":
			return 2;
		case "STAT":
			return 3;
		default:
			return 0;
		}
	}

	private Student[] filterStudentsByCourse(Student[] students, String coursePrefix) {
		Student[] filteredStudents = new Student[students.length];
		int count = 0;
		for (Student student : students) {
			if (coursePrefix.equals(determineCoursePrefix(student.getCourse()))) {
				filteredStudents[count++] = student;
			}
		}
		return Arrays.copyOf(filteredStudents, count);
	}

	private void sortStudentsByGrade(Student[] students) {
		Arrays.sort(students, Comparator.comparing(Student::getGrade).reversed());
	}

	private int countStudents(Student[] students) {
		int count = 0;
		for (Student student : students) {
			if (student != null) {
				count++;
			}
		}
		return count;
	}

//	File reader and writer
	
	public Student[] createStudent() throws IOException {
		Student[] students = new Student[100];

		try (BufferedReader br = new BufferedReader(new FileReader("student-master-list.csv"))) {

//			to skip the header line
			br.readLine();

			String line;
			Integer count = 0;

			while ((line = br.readLine()) != null && count < students.length) {
				String[] values = line.split(",");

				Integer studentID = Integer.parseInt(values[0]);
				String studentName = values[1];
				String course = values[2];
				Integer grade = Integer.parseInt(values[3]);

				Student student = new Student(studentID, studentName, course, grade);
				students[count++] = student;

//				Verifying the everything is being read into the student array
//				System.out.println(Arrays.toString(students));

			}
		}
		
//		Comparator for sorting students by course

		Arrays.sort(students, Comparator.comparing(Student::getCourse).thenComparing(Student::getGrade).reversed());

		for (String coursePrefix : Arrays.asList("COMPSCI", "APMTH", "STAT")) {
			Student[] courseStudents = filterStudentsByCourse(students, coursePrefix);
			courseStudents = Arrays.copyOf(courseStudents, countStudents(courseStudents));

			sortStudentsByGrade(courseStudents);

			String fileName = "course" + determineCourseNumber(coursePrefix) + ".csv";
			try (FileWriter fw = new FileWriter(fileName, true)) {
				for (Student currentStudent : courseStudents) {
					fw.append(currentStudent.getStudentID().toString()).append(",");
					fw.append(currentStudent.getStudentName()).append(",");
					fw.append(currentStudent.getCourse()).append(",");
					fw.append(currentStudent.getGrade().toString()).append("\n");
				}
			}
		}

		return students;

	}
	


	
}
