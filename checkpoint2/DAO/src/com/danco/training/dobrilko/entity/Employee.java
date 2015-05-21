package com.danco.training.dobrilko.entity;

import java.util.Date;

import com.danco.training.dobrilko.primarykey.PKHolder;

public class Employee implements PKHolder<Integer> {
	private Integer id;
	private String firstName;
	private String lastName;
	private Boolean gender;
	private Date birthDate;
	private String title;

	public Employee() {

	}

	public Employee(Integer id, String firstName, String lastName,
			Boolean gender, Date birthDate, String title) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.title = title;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
