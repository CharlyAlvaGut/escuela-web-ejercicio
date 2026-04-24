package com.ipn.escuela_web.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "materias")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMateria;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column
	private Integer creditos;

	@Column(length = 255)
	private String descripcion;

	@Column
	private Boolean activo;

	public Materia() {
		super();
	}

	public Materia(Long idMateria, String nombre, Integer creditos, String descripcion, Boolean activo) {
		super();
		this.idMateria = idMateria;
		this.nombre = nombre;
		this.creditos = creditos;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}