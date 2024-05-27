package com.codegnan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.entity.Doctor;
import com.codegnan.entity.Patient;
import com.codegnan.exception.InvalidDoctorIdException;
import com.codegnan.service.DoctorService;
import com.codegnan.service.PatientService;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {
	DoctorService doctorService;
	PatientService patientService;

	@Autowired
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	@Autowired
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public DoctorController(DoctorService doctorService, PatientService patientService) {
		super();
		this.doctorService = doctorService;
		this.patientService = patientService;
	}

	public DoctorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Retrieve a specific doctor
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) throws InvalidDoctorIdException {
		Doctor doctor = doctorService.findDoctorById(id);
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<>(doctor, HttpStatus.FOUND);
		return responseEntity;
	}

	// Retrieve all Doctors
	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = doctorService.findAllDoctors();
		ResponseEntity<List<Doctor>> responseEntity = new ResponseEntity<>(doctors, HttpStatus.OK);
		return responseEntity;
	}

	// Add a new Doctor
	@PostMapping
	public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
		Doctor savedDoctor = doctorService.saveDoctor(doctor);
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<Doctor>(savedDoctor, HttpStatus.ACCEPTED);
		return responseEntity;
	}

	// Edit a specific Doctor details
	@PutMapping("/{id}")
	public ResponseEntity<Doctor> editDoctor(@PathVariable("id") int id, @RequestBody Doctor doctor)
			throws InvalidDoctorIdException {
		if (id != doctor.getId()) {
			throw new InvalidDoctorIdException("No doctor exists with the id : " + id);
		}
		Doctor editedDoctor = doctorService.editDoctor(doctor);
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<>(editedDoctor, HttpStatus.ACCEPTED);
		return responseEntity;
	}

	// Delete a specific doctor
	@DeleteMapping("/{id}")
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") int id) throws InvalidDoctorIdException {
		Doctor doctor = doctorService.deleteDoctor(id);
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<>(doctor, HttpStatus.ACCEPTED);
		return responseEntity;
	}

	// Retrieve all patients associated with a specific doctor
	@GetMapping("/{id}/patients")
	public ResponseEntity<List<Patient>> getPatientsByDoctor(@PathVariable int id) throws InvalidDoctorIdException {
		Doctor doctor = doctorService.findDoctorById(id);
		List<Patient> patients = patientService.findAllPatientsByDoctor(doctor);
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}
}
