package com.eda.tp6.servidor;

import com.eda.tp6.utils.TipoPeticion;

public class Peticion {
	private int tiempoProceso, createdAt;
	private TipoPeticion tipo;

	protected Peticion(int createdAt, TipoPeticion tipo) {
		this.tiempoProceso = (int)(Math.random()*300) + 50;
		this.createdAt = createdAt;
		this.tipo = tipo;
	}

	public TipoPeticion getTipo() {
		return tipo;
	}

	public int getCreatedAt() {
		return createdAt;
	}

	public int getTiempoProceso() {
		return tiempoProceso;
	}
}
