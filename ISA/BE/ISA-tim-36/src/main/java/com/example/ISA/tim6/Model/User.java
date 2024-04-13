package com.example.ISA.tim6.Model;

import com.example.ISA.tim6.ENUMS.Gender;
import com.example.ISA.tim6.ENUMS.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "verified")
    private Boolean verified;
    
    @Column(name = "points")
    private int points;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "addressId")
    private long addressId;

    //For medical administrator only
    @Column(name = "centerId")
    private long centerId;

    //For regular users only
    @Column(name = "questionnaireId")
    private long questionnaireId;

    //For regular users only
    @Column(name = "lastSuccesfullAppointment")
    private Date lastSuccesfullAppointment;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "jmbg")
    private String jmbg;

    @Column(name = "job")
    private String job;

    @Column(name = "companyName")
    private String companyName;
        
    @Column(name = "street")
    private String street;
    
    @Column(name = "number")
    private String number;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "country")
    private String country;

	@Column(name = "changed_initial_password")
	private boolean changedInitialPassword;

	@JsonIgnore
	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
	)
	private List<Complaint> complaints;

	@JsonIgnore
	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
	)
	private List<Appointment> appointments;

    public User()
    {

    }

    //FOR REGULAR USERS
    public User( String name, String surname, String email, Role role, String password, int points)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.points = points;
		this.role = role;
		this.password = password;
    }

    public User(String username, String password, long id, String name, String surname, String email, int points)
    {
		this.username = username;
		this.password = password;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.points = points;
    }
       
    
  //UPDATE KORISNIK
    public User(String name, String surname, String email, String password, Boolean verified,
			int points, Role role, Gender gender, String phoneNumber, String jmbg, String job, String companyName) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.verified = verified;
		this.points = points;
		this.role = Role.KORISNIK;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.jmbg = jmbg;
		this.job = job;
		this.companyName = companyName;
	}
    
    //CREATE NEW KORISNIK
    public User(String username, String name, String surname, String email, String password,
			int points, Role role, Gender gender, String phoneNumber, String jmbg, String job,
			String companyName, String number, String street, String city, String country) {
		super();
		this.username=username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.verified = false;
		this.points = points;
		this.role = Role.KORISNIK;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.jmbg = jmbg;
		this.job = job;
		this.companyName = companyName;
		this.number = number;
		this.street = street;
		this.city = city;
		this.country = country;
	}


	//FOR ADMINS
    public User(String name, String surname, String email, Role role, String password)  //OVAJ JE ZA ADMIN MED OSOBLJA
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.password = password;
    }



	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getCenterId() {
		return centerId;
	}

	public void setCenterId(long centerId) {
		this.centerId = centerId;
	}

	public long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Date getLastSuccesfullAppointment() {
		return lastSuccesfullAppointment;
	}

	public void setLastSuccesfullAppointment(Date lastSuccesfullAppointment) {
		this.lastSuccesfullAppointment = lastSuccesfullAppointment;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCounty(String country) {
		this.country = country;
	}

	public boolean isChangedInitialPassword() {
		return changedInitialPassword;
	}

	public void setChangedInitialPassword(boolean changedInitialPassword) {
		this.changedInitialPassword = changedInitialPassword;
	}

	public void addComplaint(Complaint complaint) {
		if (!complaints.contains(complaint)) {
			complaints.add(complaint);
			complaint.setUser(this);
		}
	}

	public void addAppointment(Appointment appointment) {
		if (!appointments.contains(appointment)) {
			appointments.add(appointment);
			appointment.setUser(this);
		}
	}

}
