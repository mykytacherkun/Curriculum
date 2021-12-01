package com.company.model;

public class DisciplineCurriculum {

	private int discipline_id;
	private int curriculum_id;
	private int hours;
	private int audit_hours;
	private int lab_hours;
	private int lec_hours;
	private int practice_hours;
	private int independent_work_hours;
	private int credits;
	private boolean has_exam;
	private boolean has_credit;
	private String individual_task_type;
	private int semester;
	private String block;
	private String file_url;
	private Discipline discipline;
	private Curriculum curriculum;
	public Discipline getDiscipline() {
		return discipline;
	}
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public DisciplineCurriculum(int discipline_id, int curriculum_id, int hours, int audit_hours, int lab_hours,
			int lec_hours, int practice_hours, int independent_work_hours, int credits, boolean has_exam,
			boolean has_credit, String individual_task_type, int semester, String block, String file_url) {
		super();
		this.discipline_id = discipline_id;
		this.curriculum_id = curriculum_id;
		this.hours = hours;
		this.audit_hours = audit_hours;
		this.lab_hours = lab_hours;
		this.lec_hours = lec_hours;
		this.practice_hours = practice_hours;
		this.independent_work_hours = independent_work_hours;
		this.credits = credits;
		this.has_exam = has_exam;
		this.has_credit = has_credit;
		this.individual_task_type = individual_task_type;
		this.semester = semester;
		this.block = block;
		this.file_url = file_url;
	}
	public int getDiscipline_id() {
		return discipline_id;
	}
	public void setDiscipline_id(int discipline_id) {
		this.discipline_id = discipline_id;
	}
	public int getCurriculum_id() {
		return curriculum_id;
	}
	public void setCurriculum_id(int curriculum_id) {
		this.curriculum_id = curriculum_id;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getAudit_hours() {
		return audit_hours;
	}
	public void setAudit_hours(int audit_hours) {
		this.audit_hours = audit_hours;
	}
	public int getLab_hours() {
		return lab_hours;
	}
	public void setLab_hours(int lab_hours) {
		this.lab_hours = lab_hours;
	}
	public int getLec_hours() {
		return lec_hours;
	}
	public void setLec_hours(int lec_hours) {
		this.lec_hours = lec_hours;
	}
	public int getPractice_hours() {
		return practice_hours;
	}
	public void setPractice_hours(int practice_hours) {
		this.practice_hours = practice_hours;
	}
	public int getIndependent_work_hours() {
		return independent_work_hours;
	}
	public void setIndependent_work_hours(int independent_work_hours) {
		this.independent_work_hours = independent_work_hours;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public boolean getHas_exam() {
		return has_exam;
	}
	public void setHas_exam(boolean has_exam) {
		this.has_exam = has_exam;
	}
	public boolean getHas_credit() {
		return has_credit;
	}
	public void setHas_credit(boolean has_credit) {
		this.has_credit = has_credit;
	}
	public String getIndividual_task_type() {
		return individual_task_type;
	}
	public void setIndividual_task_type(String individual_task_type) {
		this.individual_task_type = individual_task_type;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String has_exam() {
		if(has_exam) return "Yes";
		else return "No";
	}
	public String has_credit() {
		if(has_credit) return "Yes";
		else return "No";
	}
}
