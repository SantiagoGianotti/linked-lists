package com.eda.tp6.servidor;

import com.eda.tp6.Fila;
import com.eda.tp6.utils.ServerParamsSingleton;
import com.eda.tp6.utils.TipoPeticion;

import static com.eda.tp6.utils.TipoPeticion.COMUN;

public class Servidor implements ServidorInterface{
	ReporteServidor reporte = new ReporteServidor();
	ServerParamsSingleton parametros = ServerParamsSingleton.getInstance();
	Fila<Peticion> filaPrioritaria = new Fila<>();
	Fila<Peticion> filaComun = new Fila<>();
	int tiempoTotal, tiempoRestanteParaProcesarPeticion, tiempoActual = 0;

	public Servidor(int tiempoTotal)
	{
		this.tiempoTotal =  tiempoTotal;
	}

	@Override
	public boolean ingresaPeticion()
	{
		return parametros.probabilidadIngresa();
	}

	@Override
	public boolean esUsuarioRegistrado()
	{
		return parametros.probabilidadUsuarioRegistrado();
	}

	@Override
	public Peticion nuevaPeticion(TipoPeticion tipo)
	{
		return new Peticion(tiempoActual, tipo);
	}

	@Override
	public void procesar() throws UnsupportedOperationException
	{
		Peticion miPeticion;
		Peticion peticionProcesada = null;
		Fila<Peticion> filaActual; //una pequeña referencia para simplificar el codigo y los reportes.

		limpiarCache(); //Asi el servidor puede volveer a correr y no contamino el codigo

		while(tiempoActual < tiempoTotal)
		{

			//Creo entradas segun las probabilidadess especificadas en el singleton.
			if(ingresaPeticion())
			{

				miPeticion = esUsuarioRegistrado()?
						nuevaPeticion(TipoPeticion.PRIORITARIA) :
						nuevaPeticion(COMUN);

				//Decido que fila empujar segun el enum que defini. Lo planteo asi por temas de mantenimiento
				switch (miPeticion.getTipo())
				{
					case PRIORITARIA:
						filaActual = filaPrioritaria;
						break;
					case COMUN:
						filaActual = filaComun;
						break;
					default:
						throw new UnsupportedOperationException();
				}

				//Agrego a la fila correspondiente la peticion.
				filaActual.enfila(miPeticion);

				//Guardo el tamaño maximo de fila cada vez que cambia.
				reporte.tamañoFila(filaActual, miPeticion.getTipo());
			}

			//Si hay una peticion espero a que termine.
			if(tiempoRestanteParaProcesarPeticion > 0)
			{
				tiempoRestanteParaProcesarPeticion--;
			}
			else
			{

				//Genero un reporte si acabo de terminar de atender una peticion.
				if (peticionProcesada != null)
				{
					reporte.atendida(peticionProcesada);
					reporte.espera(peticionProcesada, tiempoActual);
				}

				//reviso que hayan peticiones nuevas y las proceso.
				if(!filaPrioritaria.esFilaVacia())
				{
					//Asigno el tiempo que va a demorar para procesar
					peticionProcesada = filaPrioritaria.frente();
					tiempoRestanteParaProcesarPeticion = peticionProcesada.getTiempoProceso();
					filaPrioritaria.defila();

				}
				else if(!filaComun.esFilaVacia())
				{
					peticionProcesada = filaComun.frente();
					tiempoRestanteParaProcesarPeticion = peticionProcesada.getTiempoProceso();
					filaComun.defila();
				}
				else
				{
					reporte.estaInactivo();
				}


			}

			//Avanzo el contador.
			tiempoActual++;
		}

		reporte.comunesInatendidas(filaComun.tamaño());
		reporte.prioritariasInatendidas(filaPrioritaria.tamaño());
		reporte.mostrar();
	}

	private void limpiarCache()
	{
		reporte = new ReporteServidor();
		filaPrioritaria = new Fila<>();
		filaComun = new Fila<>();
		tiempoRestanteParaProcesarPeticion = 0;
		tiempoActual = 0;
	}
}
