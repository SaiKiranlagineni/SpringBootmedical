package com.example.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Clinic 
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long clinicId;
	    private String name;
	    private String address1;
	    private String phoneNumber;
	    private Double latitude;
	    private Double longitude;
	    private Boolean appointmentsAvailable;
	    
	    @Column(unique = true)
	    private String governmentRecognisedPin;

		public Long getClinicId() {
			return clinicId;
		}

		public void setClinicId(Long clinicId) {
			this.clinicId = clinicId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public Double getLatitude() {
			return latitude;
		}

		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}

		public Double getLongitude() {
			return longitude;
		}

		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

		public Boolean getAppointmentsAvailable() {
			return appointmentsAvailable;
		}

		public void setAppointmentsAvailable(Boolean appointmentsAvailable) {
			this.appointmentsAvailable = appointmentsAvailable;
		}

		public String getGovernmentRecognisedPin() {
			return governmentRecognisedPin;
		}

		public void setGovernmentRecognisedPin(String governmentRecognisedPin) {
			this.governmentRecognisedPin = governmentRecognisedPin;
		}

		public Clinic(Long clinicId, String name, String address1, String phoneNumber, Double latitude, Double longitude,
				Boolean appointmentsAvailable, String governmentRecognisedPin) {
			super();
			this.clinicId = clinicId;
			this.name = name;
			this.address1 = address1;
			this.phoneNumber = phoneNumber;
			this.latitude = latitude;
			this.longitude = longitude;
			this.appointmentsAvailable = appointmentsAvailable;
			this.governmentRecognisedPin = governmentRecognisedPin;
		}
		public Clinic() {
			
		}
	     
}
