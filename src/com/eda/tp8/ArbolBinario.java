package com.eda.tp8;

public class ArbolBinario<T> implements ArbolBinarioInterface<T>{
	ArbolBinario derecho = null;
	ArbolBinario izquierdo = null;
	T raiz;

	public ArbolBinario(ArbolBinario izquierdo, T raiz, ArbolBinario derecho)
	{
		this.raiz = raiz;
		this.izquierdo = izquierdo;
		this.derecho = derecho;
	}

	@Override
	public boolean esVacio() {
		return raiz != null;
	}

	@Override
	public boolean pertenece(T obj) {
		return obj.equals(raiz);
	}

	@Override
	public ArbolBinario izquierdo() {
		return izquierdo;
	}

	@Override
	public ArbolBinario derecho() {
		return derecho;
	}

	@Override
	public T raiz() {
		return raiz;
	}

	public void preorden()
	{
		System.out.println(raiz);

		if( izquierdo != null )
		{
			izquierdo.preorden();
		}

		if( derecho != null )
		{
			derecho.preorden();
		}
	}

	public void orden()
	{

		if( izquierdo != null )
		{
			izquierdo.orden();
		}

		System.out.println(raiz);

		if( derecho != null )
		{
			derecho.orden();
		}
	}

	public void postorden()
	{
		if( izquierdo != null )
		{
			izquierdo.postorden();
		}

		if( derecho != null )
		{
			derecho.postorden();
		}

		System.out.println(raiz);
	}
}
