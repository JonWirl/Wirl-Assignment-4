package com.coderscampus.assignment4;

public class Student {
	private Integer studentID;
	private String studentName;
	private String course;
	private Integer grade;
	
	public Student(Integer studentID, String studentName, String course, Integer grade) {
		this.studentID = studentID;
		this.studentName = studentName;
		this.course = course;
		this.grade = grade;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public String getCourseLetterPart() {
		return this.course.replaceAll("\\d", "");
	}
	

}
