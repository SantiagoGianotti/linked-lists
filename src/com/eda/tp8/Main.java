package com.eda.tp8;

public class Main {


	//Queria hacer la implementacion del arbol de una manera un poco distinta de la del profesor en la consulta.
	//Trate de armar el arbol utilizando una funcion recursiva.

	public static void main(String[] args) {

		ArbolBinario arbol = ArbolMorse.instanciar();

		try {
			System.out.println(ArbolMorse.traducirFrase("... --- ...", arbol));
		}
		catch (Exception e)
		{
			System.out.println("excepcion");
		}

	}

}
