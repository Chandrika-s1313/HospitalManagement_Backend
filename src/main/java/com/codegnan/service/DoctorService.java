package com.codegnan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.entity.Doctor;
import com.codegnan.exception.InvalidDoctorIdException;
import com.codegnan.repo.DoctorRepo;

@Service
public class DoctorService {
	DoctorRepo doctorRepo;
	public DoctorService() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorService(DoctorRepo doctorRepo) {
		super();
		this.doctorRepo = doctorRepo;
	}
	@Autowired
	public void setDoctorRepo(DoctorRepo doctorRepo) {
		this.doctorRepo = doctorRepo;
	}
	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepo.save(doctor);
	}
	public List<Doctor> findAllDoctors(){
		return doctorRepo.findAll();
	}
	public Doctor findDoctorById(int id) throws InvalidDoctorIdException{
		Optional<Doctor> optDoctor = doctorRepo.findById(id);
		if(!(optDoctor.isPresent())) {
			throw new InvalidDoctorIdException("No doctor exits with the id : "+id);
		}
		return optDoctor.get();
	}
	public Doctor editDoctor(Doctor doctor) throws InvalidDoctorIdException {
		Doctor foundDoctor = findDoctorById(doctor.getId());
		return doctorRepo.save(foundDoctor);
	}
	public Doctor deleteDoctor(int id) throws InvalidDoctorIdException{
		Doctor doctor = findDoctorById(id);
		doctorRepo.delete(doctor);
		return doctor;
	}
}
