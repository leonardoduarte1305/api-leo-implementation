package dev.leoduarte.api.leo.app.persistence.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column
	protected String name;

	@Column(unique = true)
	protected String telephone;

	@ManyToOne
	@JoinColumn(name = "id")
	protected Department department;

	@Column(length = 14, unique = true)
	protected String cpf;

	@Column(unique = true)
	protected String email;

	@Temporal(TemporalType.DATE)
	protected Date birthDate;

	@Enumerated(value = EnumType.STRING)
	protected String gender;

	protected Date registerDate = Calendar.getInstance().getTime();

}
