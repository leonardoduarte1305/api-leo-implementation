package dev.leoduarte.api.leo.app.persistence.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employee_id;

	@Column
	private String name;

	@Column
	private String telephone;

	@Column(length = 14, unique = true)
	private String cpf;

	@Email
	@Column(unique = true, name = "email")
	private String username;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	private Date registerDate = Calendar.getInstance().getTime();

	private String password;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	@Override
	public String toString() {
		return "Employee [id_employee=" + employee_id + ", name=" + name +
				", department=" + department.getDepartment_id() + "]";
	}
}
