package com.eda.tp6;

import java.util.NoSuchElementException;

public class Fila<T> implements FilaInterface<T> {

 	private Nodo primero;
	private Nodo ultimo;
    private int cantidad = 0;

	@Override
	public boolean esFilaVacia() {
		return primero == null;
	}

	@Override
	public void enfila(T obj)
	{
		Nodo nodo = new Nodo(obj);

		if (this.esFilaVacia())
		{
			primero = nodo;
			ultimo = nodo;
		}
		else
		{
			ultimo.setSiguiente(nodo);
			ultimo = nodo;
		}

		cantidad++;
	}

	@Override
	public void defila() throws NoSuchElementException
	{

		if(this.esFilaVacia())
		{
			throw new NoSuchElementException();
		}

		primero = primero.getSiguiente();

		//Si ademas es el ultimo elemento, cuando lo saco borro la referencia a ultimo.
		//Pareceria innecesario, pero si no borro la referencia de ultimo, entonces
		//van a haber instancias donde el garbage collector no pueda borrar algun elemento.
		//Sin embargo esto no va a alterar el funcionamiento de la clase.

		if (tamaño() == 1)
		{
			ultimo = null;
		}

		cantidad--;
	}

	@Override
	public T frente() throws NoSuchElementException {

		if (this.esFilaVacia())
		{
			throw new NoSuchElementException();
		}

		return (T) primero.getObjeto();
	}

    public int tamaño()
    {
        return this.cantidad;
    }

    public void mostrar()
    {
        Nodo aux = this.primero;
        int cant = 0; //Para darle un formato mas comodo.

        while(aux != null)
        {
            //Utilizo un ternary para darle formato con cant
            System.out.print(aux.getObjeto().toString() + (cant + 1 % 10 == 0 ? "\n" : " - " ));

            //Una vez que he mostrado mi nodo, avanzo al siguiente.
            aux = aux.getSiguiente();
            cant++;
        }
        System.out.println();
    }


    private class Nodo<T>
    {
        T objeto;
        Nodo siguiente = null;

        Nodo(T obj)
        {
            this.objeto = obj;
        }

        public T getObjeto() {
            return objeto;
        }

        public Nodo getSiguiente()
        {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }
    }
}
