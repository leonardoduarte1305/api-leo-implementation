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
	private Long id;

	@Column
	private String name;

	@Column
	private String telephone;

	@Column(length = 14, unique = true)
	private String cpf;

	@Column(unique = true, name = "email")
	private String username;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	private Date registerDate = Calendar.getInstance().getTime();

	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "id")
	private Department department;

	@ManyToOne
	private Profile profile;

	private void setCpf(String cpf) {
		if (cpf == null || !cpf.matches("d{3}\\.d{3}\\.d{3}\\-d{2}")) {
			throw new IllegalArgumentException("Invalid CPF.");
		}
		this.cpf = cpf;
	}

	private void setTelephone(String telephone) {
		if (telephone == null || !telephone.matches("d{2}\\ d{4,5}\\-d{4}")) {
			throw new IllegalArgumentException("Numero invalido.");
		}
		this.telephone = telephone;
	}

	private void setUsername(String username) {
		if (username == null || !username.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			throw new IllegalArgumentException("Email invalido.");
		}
		this.username = username;
	}

	@Override
	public String toString() {
		return "Colaborador [id_employee=" + id + ", name=" + name + ", department=" + department + "]";
	}
}
