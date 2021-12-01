package com.company.model;

public class Curriculum {

	private int id;
	private String name;
	private int year;
	private String file_url;
	private String approvement_url;
	private int specialty_id;
	private Specialty specialty;
	public Curriculum(int id, String name, int year, String file_url, String approvement_url, int specialty_id) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.file_url = file_url;
		this.approvement_url = approvement_url;
		this.specialty_id = specialty_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String getApprovement_url() {
		return approvement_url;
	}
	public void setApprovement_url(String approvement_url) {
		this.approvement_url = approvement_url;
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
