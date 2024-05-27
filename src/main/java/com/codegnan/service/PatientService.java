package com.codegnan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.repo.PatientRepo;
import com.codegnan.entity.Doctor;
import com.codegnan.entity.Patient;
import com.codegnan.exception.InvalidPatientIdException;
@Service
public class PatientService {
	PatientRepo patientRepo;
	
	public PatientService(PatientRepo patinetRepo) {
		super();
		this.patientRepo = patinetRepo;
	}
	public PatientService() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public void setPatinetRepo(PatientRepo patinetRepo) {
		this.patientRepo = patinetRepo;
	}
	public Patient savePatient(Patient patient) {
		return patientRepo.save(patient);
	}
	public Patient findPatientById(int id) throws InvalidPatientIdException{
		Optional<Patient> optPatient = patientRepo.findById(id);
		if(!(optPatient.isPresent())) {
			throw new InvalidPatientIdException("Patient with id : "+id+" not exist");
		}
		return optPatient.get();
	}
	public List<Patient> findAllPatients(){
		return patientRepo.findAll();
	}
	public Patient editPatient(Patient patient) throws InvalidPatientIdException {
		findPatientById(patient.getId());
		return patientRepo.save(patient);
	}
	public List<Patient> findAllPatientsByDoctor(Doctor doctor){
		return patientRepo.findAllPatientsByDoctor(doctor);
	}
	public Patient deletePatient(int id) throws InvalidPatientIdException {
		Patient patient = findPatientById(id);
		patientRepo.delete(patient);
		return patient;
	}
	
}
