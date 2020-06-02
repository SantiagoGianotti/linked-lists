package com.eda.tp8;

import java.rmi.UnexpectedException;
import java.util.Collections;
import java.util.List;

public class ArbolMorse {

	public static ArbolBinario<Character> instanciar()
	{
		ArbolBinario<Character> arbol;
		List<CodigoMorse> lista = CodigoMorse.tabla();

		Collections.sort(lista, CodigoMorse::compareTo);

		return arbolRecursivo(lista, "");
	}

	private static ArbolBinario<Character> arbolRecursivo(List<CodigoMorse> lista, String cadena)
	{
		CodigoMorse actual = lista.get(0);

		if( cadena.length() == 5)
		{
			if(!cadena.equals(actual.codigo))
			{
				return null;
			}

			lista.remove(0);
			return new ArbolBinario( null, actual.valor,  null);
		}

		lista.remove(0);
		return new ArbolBinario( arbolRecursivo(lista,cadena+"." ), actual.valor , arbolRecursivo(lista, cadena+"_"));
	}

	public static String traducirFrase(String cadena, ArbolBinario<Character> arbol) throws UnexpectedException
	{
		String[] array = cadena.split(" ");

		for (String str : array) {
			cadena += traducir(str, arbol);
		}

		return cadena;
	}

	private static char traducir(String cadena, ArbolBinario<Character> arbol) throws UnexpectedException
	{

		if (cadena.length() == 0)
		{
			return arbol.raiz();
		}

		switch (cadena.charAt(0))
		{
			case '.':
			case '*':
				return traducir(cadena.substring(1), arbol.izquierdo());
			case '-':
			case '_':
				return traducir(cadena.substring(1), arbol.derecho());
			default:
				throw new UnexpectedException("Se esperaban puntos, rayas o espacios.");
		}
	}
}
