package com.eda.tp1;

public class ListaEnlazada
{
    private Nodo primero;
    private int cantidad = 0;

    private ListaEnlazada()
    {
    }

    public boolean estaVacia()
    {
        return primero == null;
    }

    public void insertar(int posicion, int valor) throws IndexOutOfBoundsException
    {
        this.cantidad++;
        Nodo anterior;
        Nodo nuevo = new Nodo(valor);

        if (posicion == 0)
        {
            nuevo.setSiguiente(this.tamaño() > 1? this.primero : null);
            this.primero = nuevo;

        }
        else
        {
            anterior = this.get(posicion - 1);
            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevo);
        }
    }

    public void insertarPrimero(int numero)  throws IndexOutOfBoundsException
    {
        this.insertar(0, numero);
    }

    public void insertarUltimo(int numero)  throws IndexOutOfBoundsException
    {
        this.insertar(this.tamaño(), numero);
    }

    public int tamaño()
    {
        return this.cantidad;
    }

    public void eliminar(int posicion) throws IndexOutOfBoundsException
    {
        Nodo anterior;

        if (this.cantidad == 0)
        {
            throw new IndexOutOfBoundsException();
        }

        if (posicion == 0)
        {
            this.primero = this.primero != null ? this.primero.getSiguiente() : null;
        }
        else
        {
            anterior = this.get(posicion - 1);

            anterior.setSiguiente( anterior.getSiguiente().getSiguiente() );
        }
        this.cantidad--;
    }

    public void eliminarPrimero()  throws IndexOutOfBoundsException
    {
        this.eliminar(0);
    }

    public void eliminarUltimo()  throws IndexOutOfBoundsException
    {
        this.eliminar(this.tamaño());
    }

    public void eliminarSegunValor(int valor)
    {
        Nodo actual = this.primero;
        Nodo anterior = null;

        while (actual != null)
        {
            if (actual.getNumero() == valor)
            {
                this.cantidad--;

                if (actual == this.primero)
                {
                    this.primero = actual.getSiguiente();
                }
                else
                {
                    anterior.setSiguiente(actual.getSiguiente());
                }
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }
    }

    public void modificar(int posicion, int valor)
    {
        Nodo actual = this.get(posicion);
        actual.setNumero(valor);
    }

    public void mostrarCantidad()
    {
        System.out.println(this.tamaño());
    }

    private Nodo get(int posicion) throws IndexOutOfBoundsException
    {
        //Primero voy a verificar que la posicion no sea mayor a la cantidad.
        if (posicion >= this.tamaño()|| posicion < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        Nodo actual = this.primero;

        for (int i = 0; i < posicion; i++)
        {
            actual = actual.getSiguiente();
        }

        return actual;
    }

    public int getPosicion(int posicion) throws IndexOutOfBoundsException
    {
        return this.get(posicion).numero;
    }

    public void mostrar()
    {
        Nodo aux = this.primero;
        int cant = 0; //Para darle un formato mas comodo.

        while(aux != null)
        {
            //Utilizo un ternary para darle formato con cant
            System.out.print(aux.getNumero() + (cant + 1 % 10 == 0 ? "\n" : " - " ));

            //Una vez que he mostrado mi nodo, avanzo al siguiente.
            aux = aux.getSiguiente();
            cant++;
        }
        System.out.println();
    }

    public static ListaEnlazada instanciar()
    {
        return new ListaEnlazada();
    }

    public static void ClasificadorParImpar(ListaEnlazada par, ListaEnlazada impar) throws InstantiationException
    {
        int cantidad = (int)(Math.random()*1000);
        int numero_al_azar;

        if (!par.estaVacia() && !impar.estaVacia())
        {
            throw new InstantiationException();
        }

        for (int i = 0; i < cantidad ; i++)
        {
            numero_al_azar = (int)(Math.random()*100000);

            if ( numero_al_azar % 2 == 0)
            {
                par.insertarPrimero(numero_al_azar);
            }
            else
            {
                impar.insertarPrimero(numero_al_azar);
            }
        }
    }


    public boolean paridad(int mod)
    {
        Nodo nodo = this.primero;

        while( nodo != null)
        {
            if ( nodo.getNumero() % 2 != mod)
            {
                return false;
            }

            nodo = nodo.getSiguiente();
        }

        return true;
    }

    class Nodo
    {
        int numero;
        Nodo siguiente = null;

        Nodo(int num)
        {
            this.numero = num;
        }

        public int getNumero()
        {
            return numero;
        }

        public void setNumero(int numero)
        {
            this.numero = numero;
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
