package com.eda.tp6;

public interface FilaInterface<T> {

	boolean esFilaVacia();
	void enfila(T obj);
	void defila();
	T frente();

}
