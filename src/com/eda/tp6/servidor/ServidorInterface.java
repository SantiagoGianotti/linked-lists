package com.eda.tp6.servidor;

import com.eda.tp6.utils.TipoPeticion;

public interface ServidorInterface {
	boolean ingresaPeticion();
	boolean esUsuarioRegistrado();
	Peticion nuevaPeticion (TipoPeticion tipo);
	void procesar();

}
