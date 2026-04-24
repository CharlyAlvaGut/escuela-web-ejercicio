package com.ipn.escuela_web.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
	
	private Long idMateria;
	private String nombre;
	private Integer creditos;
	private String descripcion;
	private boolean activo;

}
