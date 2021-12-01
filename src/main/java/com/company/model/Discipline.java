package com.company.model;

public class Discipline {

	private int id;
	private String code;
	private String name;
	private String short_name;
	public Discipline(int id, String code, String name, String short_name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.short_name = short_name;
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
}
