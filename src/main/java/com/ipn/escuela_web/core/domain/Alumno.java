package com.ipn.escuela_web.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
	
	private Long idAlumno;
	private String nombre;
	private String paterno;
	private String aterno;
	private String email;
	

}
