package com.example.demo.trySpring;


import lombok.Data;

//lombok.Dataを使うとgetter&setterが自動生成されるはずがされない。
//HelloRepositoryでDBから検索されたデータ「id,name,age」を、各フィールドに入れる。
@Data
public class Employee {
	
	private int employeeId;
	private String employeeName;
	private int age;
	
	/* GET && SET */
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
