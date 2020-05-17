package com.eda.tp5;
import java.util.EmptyStackException;

public class PilaClass<T> implements PilaInterface<T>
{
    private Nodo tope;
    private int cantidad = 0;


    @Override
    public void push(T objToPush) {

        //Creo un nodo con el objeto a empujar, y le agrego la referencia del tope actual.
        Nodo nodo = new Nodo(objToPush);
        nodo.setSiguiente(this.tope);


        //Agrego el nodo nuevo a la pila haciendo que el tope lo referencie. Tambien aumento el contador.
        this.tope = nodo;
        cantidad++;
    }

    @Override
    public void pop() throws EmptyStackException {

        //Checkeo que la pila no este vacia, sino excepcion.
        if (this.esPilaVacia())
        {
            throw new EmptyStackException();
        }

        this.tope = this.tope.getSiguiente();
        this.cantidad--;
    }

    //De las diapositivas usamos top solo paraa sacar el objeto asiu que la pila no cambia.
    @Override
    public T top() {
        if (this.esPilaVacia())
        {
            throw new EmptyStackException();
        }

        return (T) this.tope.getObjeto();
    }

    @Override
    public boolean esPilaVacia() {
        return tope == null;
    }

    public int tamaño()
    {
        return this.cantidad;
    }

    public void mostrar()
    {
        Nodo aux = this.tope;
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
