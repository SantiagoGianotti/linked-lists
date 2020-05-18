package com.eda.tp6.servidor;

import com.eda.tp6.Fila;
import com.eda.tp6.utils.TipoPeticion;

class ReporteServidor {
	private int totalPrioritariasAtendidas = 0;
	private int totalComunesAtendidas = 0;
	private int comunesInatendidas = 0;
	private int prioritariasInatendidas = 0;
	private int	esperaMaximaPrioridad = 0;
	private int esperaMaximaComun = 0;
	private int tiempoInactivo = 0;
	private int tamañoMaximoComun = 0;
	private int tamañoMaximoPrioritarias = 0;

	protected ReporteServidor() {}

	public void atendida(Peticion miPeticion)
	{
		switch (miPeticion.getTipo())
		{
			case PRIORITARIA:
					this.totalPrioritariasAtendidas++;
				break;

			case COMUN:
					this.totalComunesAtendidas++;
				break;
		}
	}

	public void comunesInatendidas(int comunesInatendidas) {
		this.comunesInatendidas = comunesInatendidas;
	}

	public void prioritariasInatendidas(int prioritariasInatendidas) {
		this.prioritariasInatendidas = prioritariasInatendidas;
	}

	public void espera(Peticion miPeticion, int tiempoActual)
	{
		int tiempoEspera = tiempoActual - miPeticion.getCreatedAt();

		switch (miPeticion.getTipo())
		{
			case PRIORITARIA:

					if(tiempoEspera > this.esperaMaximaPrioridad)
					{
						this.esperaMaximaPrioridad = tiempoEspera;
					}
				break;

			case COMUN:

					if( tiempoEspera
							> this.esperaMaximaComun)
					{
						this.esperaMaximaComun = tiempoEspera;
					}
				break;
		}
	}

	public void estaInactivo() {
		this.tiempoInactivo++;
	}

	public void tamañoFila(Fila miFila, TipoPeticion tipo)
	{
		switch (tipo)
		{
			case PRIORITARIA:

					if(miFila.tamaño() > this.tamañoMaximoPrioritarias)
					{
						this.tamañoMaximoPrioritarias = miFila.tamaño();
					}
				break;

			case COMUN:

					if( miFila.tamaño() > this.tamañoMaximoComun)
					{
						this.tamañoMaximoComun = miFila.tamaño();
					}
				break;
		}
	}

	public void mostrar()
	{
		System.out.println("#############################################################################");
		System.out.println("Total de solicitudes atendidas: " + Integer.toString(totalComunesAtendidas + totalPrioritariasAtendidas));
		System.out.println("Se atendieron: " + totalComunesAtendidas +" comunes y " + totalPrioritariasAtendidas + " prioritarias" );
		System.out.println("Quedaron sin atender: " + comunesInatendidas +" comunes y " + prioritariasInatendidas + " prioritarias" );
		System.out.println("______________________________________________________________________________");
		System.out.println("El tiempo maximo de espera para solicitudes con prioridad fue de :"+esperaMaximaPrioridad);
		System.out.println("El tiempo maximo de espera para solicitudes comunes fue de :"+esperaMaximaComun);
		System.out.println("El servidor estuvo inactivo: " + tiempoInactivo);
		System.out.println("La fila prioritaria llego al tamaño maximo de: " + tamañoMaximoPrioritarias);
		System.out.println("La fila comun llego al tamaño maximo de: " + tamañoMaximoComun);
		System.out.println("#############################################################################");
	}
}