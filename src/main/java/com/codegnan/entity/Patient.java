package com.codegnan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="patient_main")
public class Patient{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String name;
	String gender;
	int age;
	double weight;
	String disease;
	@ManyToOne
	@JoinColumn(name="doctor_id")
	Doctor doctor;
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient( String name, String gender, int age, String disease, Doctor doctor) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.disease = disease;
		this.doctor = doctor;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	@Override
	public String toString() {
		return "Patients [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", disease=" + disease+", doctor= "+doctor+" ]";
	}
	
	
	
}
