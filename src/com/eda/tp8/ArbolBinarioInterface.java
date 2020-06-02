package com.eda.tp8;

public interface ArbolBinarioInterface<T> {

	boolean esVacio();
	boolean pertenece(T obj);
	ArbolBinario izquierdo();
	ArbolBinario derecho();
	T raiz();

}
