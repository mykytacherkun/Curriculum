package com.company.model;

public class Department {

	private int id;
	private String code;
	private String name;
	private String short_name;
	private int faculty_id;
	private Faculty faculty;
	public Department(int id, String code, String name, String short_name, int faculty_id) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.short_name = short_name;
		this.faculty_id = faculty_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public int getFaculty_id() {
		return faculty_id;
	}
	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
}
