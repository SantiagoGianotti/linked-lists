package com.eda.tp8;

import java.util.ArrayList;
import java.util.List;

public class CodigoMorse implements Comparable<CodigoMorse>{
	char valor;
	String codigo;

	CodigoMorse(char valor, String codigo) {
		this.valor = valor;
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return codigo;
	}

	//devuelve una tabla con las equivalencias.
	public static List<CodigoMorse> tabla()
	{
		ArrayList<CodigoMorse> lista = new ArrayList<>();

		lista.add(new CodigoMorse(' ', ""));
		lista.add(new CodigoMorse('a', "._"));
		lista.add(new CodigoMorse('b', "_..."));
		lista.add(new CodigoMorse('c', "_._."));
		lista.add(new CodigoMorse('d', "_.."));
		lista.add(new CodigoMorse('e', "."));
		lista.add(new CodigoMorse('f', ".._."));
		lista.add(new CodigoMorse('g', "__."));
		lista.add(new CodigoMorse('h', "...."));
		lista.add(new CodigoMorse('i', ".."));
		lista.add(new CodigoMorse('j', ".___"));
		lista.add(new CodigoMorse('k', "_._"));
		lista.add(new CodigoMorse('l', "._.."));
		lista.add(new CodigoMorse('m', "__"));
		lista.add(new CodigoMorse('n', "_."));
		lista.add(new CodigoMorse('o', "___"));
		lista.add(new CodigoMorse('p', ".__."));
		lista.add(new CodigoMorse('q', "__._"));
		lista.add(new CodigoMorse('r', "._."));
		lista.add(new CodigoMorse('s', "..."));
		lista.add(new CodigoMorse('t', "_"));
		lista.add(new CodigoMorse('u', ".._"));
		lista.add(new CodigoMorse('v', "..._"));
		lista.add(new CodigoMorse('w', ".__"));
		lista.add(new CodigoMorse('x', "_.._"));
		lista.add(new CodigoMorse('y', "_.__"));
		lista.add(new CodigoMorse('z', "__.."));
		lista.add(new CodigoMorse('0', "_____"));
		lista.add(new CodigoMorse('1', ".____"));
		lista.add(new CodigoMorse('2', "..___"));
		lista.add(new CodigoMorse('3', "...__"));
		lista.add(new CodigoMorse('4', "...._"));
		lista.add(new CodigoMorse('5', "....."));
		lista.add(new CodigoMorse('6', "_...."));
		lista.add(new CodigoMorse('7', "__..."));
		lista.add(new CodigoMorse('8', "___.."));
		lista.add(new CodigoMorse('9', "____."));
		lista.add(new CodigoMorse('+', "._._."));
		lista.add(new CodigoMorse('=', "_..._"));
		lista.add(new CodigoMorse('/', "_.._."));
		lista.add(new CodigoMorse(' ', "..__")); //relleno
		lista.add(new CodigoMorse(' ', "._._")); //relleno
		lista.add(new CodigoMorse(' ', "___.")); //relleno
		lista.add(new CodigoMorse(' ', "____")); //relleno

		return lista;
	}

	public char getValor() {
		return valor;
	}

	public String getCodigo() {
		return codigo;
	}

	@Override
	public int compareTo(CodigoMorse codigoMorse) {


		return codigo.compareTo(codigoMorse.codigo);
	}
}
