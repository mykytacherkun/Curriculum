package com.company.model;

public class Group {

	private int id;
	private String code;
	private String name;
	private int year;
	private int curriculum_id;
	private int department_id;
	private int specialty_id;
	private Department department;
	private Curriculum curriculum;
	private Specialty specialty;
	public Group(int id, String code, String name, int year, int curriculum_id, int department_id, int specialty_id) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.year = year;
		this.curriculum_id = curriculum_id;
		this.department_id = department_id;
		this.specialty_id = specialty_id;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public int getCurriculum_id() {
		return curriculum_id;
	}
	public void setCurriculum_id(int curriculum_id) {
		this.curriculum_id = curriculum_id;
	}
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public int getSpecialty_id() {
		return specialty_id;
	}
	public void setSpecialty_id(int specialty_id) {
		this.specialty_id = specialty_id;
	}
	public Specialty getSpecialty() {
		return specialty;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
}
