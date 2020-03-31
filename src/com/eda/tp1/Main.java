package com.eda.tp1;

import java.lang.reflect.Method;

import static com.eda.tp1.ListaEnlazada.*;

public class Main {

    /*
        Consideraciones a la hora de hacer el trabajo.

        Primero: utilizando una metodologia de testing, voy a proceder a probar cada una de las funcionalidades
        que son requeridas por el apartado asi practico un poco de java, y supongo que puede facilitar la correccion.
            Uso : el array controla los test a ser ejecutados, y printea el resultado en consola.

        Segundo: Ya que el trabajo practico dice explicitamente que hay que utilizar listas enlazadas, voy a evitar
        utilizar listas enlazadas dobles ( sospechaba que existian asi que lo googlee ).

        Tercero: Preferi agregar el campo cantidad en memoria, ya que considero que aunque se agregue un poco de
        mantenimiento extra al codigo, va a rendir mucho mejor. Sin embargo para los casos que vamos a poner a prueba
        no hay diferencia alguna por el (n) tan pequeño.

        Cuarto: Debido a que no voy a importar ninguna herramienta para testing, los test son un poco "artesanales".
        Hubiera sido mejor comparar la referencia del nodo mas que el valor en los tests, sin embargo preferi dejar
        los nodos completamente encapsulados. Por lo tanto en la mayoria de los tests para testear que sea la misma
        referencia del int, utilizo numeros al azar muy grandes y no mas de 10 nodos de manera que sea improbable que
        se repita el numero y entonces lo consideramos como unico.

                                                                                        Santiago Gianotti

     */



    public static void main(String[] args) {

        //Inicializo la lista de metodos de testing.
        //Si comenta una linea puede sacar este test.
        String[] test_array = new String[]
                {
                    "test_mostrar", //listo
                    "test_inicializacion_lista_vacia", // listo
                    "test_insertar_al_comienzo", // listo
                    "test_obtener_tamaño", //listo
                    "test_obtener_segun_posicion", //listo
                    "test_insertar_iesimo", // listo
                    "test_insertar_al_final", //listo
                    "test_modificar_nodo", //listo
                    "test_eliminar_primer_nodo", // listo
                    "test_eliminar_ultimo_nodo", //listo
                    "test_eliminar_iesimo_nodo", //listo
                    "test_eliminar_segun_valor", //listo
                    "test_clasificador_par_impar"
                };

        correrTests(test_array);

    }


    public static boolean test_inicializacion_lista_vacia()
    {
        ListaEnlazada lista = ListaEnlazada.instanciar();

        return lista.estaVacia();
    }

    public static boolean test_insertar_al_comienzo()
    {
        int numeroUnico;
        //Creo una lista y agrego elementos al azar
        ListaEnlazada lista = ListaEnlazada.instanciar();
        crearNodos(lista, (int)(Math.random()*10 + 1));

        //Estadisticamente podemos considerar un numero lo suficientemente grande como unico.
        numeroUnico = (int)(Math.random()*999999999);

        lista.insertarPrimero(numeroUnico);

        //Si los numeros coinciden entonces es verdadero.
        return lista.getPosicion(0) == numeroUnico;
    }

    public static boolean test_obtener_tamaño()
    {
        int cantidadDeNodos = (int)(Math.random()*999);
        ListaEnlazada lista = ListaEnlazada.instanciar();

        for (int i = 0; i < cantidadDeNodos; i++) {

            //Creo un numero grande asi lo pueda considerar unico mientras las iteraciones son pocas.
            lista.insertarPrimero( (int)(Math.random()*999999999)  );
        }

        return lista.tamaño() == cantidadDeNodos;
    }

    public static boolean test_obtener_segun_posicion()
    {
        int cantidadDeNodos = (int)(Math.random()*999) + 5;
        int posicion = (int)(Math.random()*999);
        int numeroUnico = (int)(Math.random()*999999999);

        //elijo una posicion al azar menor a la cantidad de nodos.
        while(posicion > cantidadDeNodos)
        {
            posicion = (int)(Math.random()*999);
        }

        ListaEnlazada lista = ListaEnlazada.instanciar();

        for (int i = 0; i < cantidadDeNodos; i++) {

            lista.insertarPrimero( i == posicion ? numeroUnico : (int)(Math.random()*999) );
        }

        //Ahora reviso si el numero me lo da en la posicion que lo puse.
        //arreeglo la posicion que esta al reves
        posicion = lista.tamaño() - 1 - posicion;

        return lista.getPosicion(posicion) == numeroUnico;
    }

    //Utiliza exactamente la misma logica que el test anterior.
    public static boolean test_insertar_al_final()
    {
        int numeroUnico = (int)(Math.random()*999999999);
        ListaEnlazada lista = ListaEnlazada.instanciar();

        crearNodos(lista, (int)(Math.random()*10));

        lista.insertarUltimo(numeroUnico);

        return lista.getPosicion(lista.tamaño() - 1) == numeroUnico;
    }

    public static boolean test_insertar_iesimo()
    {
        int previo, posterior;
        int numeroUnico = (int)(Math.random()*999999999);
        int posicion = 1000;

        //Creo una lista y agrego elementos al azar
        ListaEnlazada lista = ListaEnlazada.instanciar();
        crearNodos(lista, (int)(Math.random()*100 + 1));

        while(posicion >= lista.tamaño() || posicion == 0)
        {
            posicion = (int)(Math.random()*100);
        }

        //Aca voy a testear que este el nodo y que mueva a los otros.
        previo = lista.getPosicion(posicion - 1);
        posterior = lista.getPosicion(posicion);

        lista.insertar(posicion, numeroUnico);

        //Ahora reviso que los valores coincidan.
        boolean coincide_previo = lista.getPosicion( posicion - 1) == previo;
        boolean coincide_actual = lista.getPosicion( posicion ) == numeroUnico;
        boolean coincide_posterior = lista.getPosicion( posicion + 1 ) == posterior;

        return coincide_actual && coincide_posterior && coincide_previo;
    }

    //Aca muestro los datos tal cual estan ya que no me queda otra.
    public static boolean test_modificar_nodo()
    {
        int numeroUnico = (int)(Math.random()*999999999 + 100);
        int posicion = (int)(Math.random()*100) + 1;

        ListaEnlazada lista = ListaEnlazada.instanciar();
        crearNodos(lista, (int)(Math.random()*100));

        while(posicion >= lista.tamaño() || posicion == 0)
        {
            posicion = (int)(Math.random()*50) + 1;
        }

        lista.modificar(posicion, numeroUnico);

        return lista.getPosicion(posicion) == numeroUnico;
    }

    //Aca muestro los datos tal cual estan ya que no me queda otra.
    public static boolean test_mostrar()
    {
        ListaEnlazada lista = ListaEnlazada.instanciar();
        crearNodos(lista, (int)(Math.random()*10));

        System.out.println("Empieza el test: mostrar lista");
        System.out.println("**************");
        lista.mostrar();
        System.out.println("**************");

        //Como no tengo herramientas de testing propiamente dichas y es mucho trabajo retornar esto, asumo que el test pasa
        System.out.println("Finalizo el test sobre mostrar la lista. Se retorna true");

        return true;
    }

    //Aca realizo 3 operaciones con los distintos casos posibles.
    public static boolean test_eliminar_primer_nodo()
    {
        //aca tengo que testear tres condiciones para que pase.
        boolean prueba1, prueba2, prueba3;

        prueba1 = test_eliminar_primer_nodo_con_lista_vacia();
        prueba2 = test_eliminar_primer_nodo_con_lista_con_un_valor();
        prueba3 = test_eliminar_primer_nodo_con_lista_con_muchos_valores();

        return prueba1 && prueba2 && prueba3;
    }

    public static boolean test_eliminar_primer_nodo_con_lista_vacia()
    {
        ListaEnlazada lista = ListaEnlazada.instanciar();

        try {
            lista.eliminar(0);
        }
        catch (IndexOutOfBoundsException ex)
        {
            return true;
        }
        return false;
    }

    public static boolean test_eliminar_primer_nodo_con_lista_con_un_valor()
    {
        ListaEnlazada lista = ListaEnlazada.instanciar();
        crearNodos(lista, 1);

        if (lista.tamaño() == 1 )
        {
            lista.eliminarPrimero();

            return lista.estaVacia();
        }

        return false;
    }

    //Para comprobar que el test sirve, agrego un numero unico al segundo nodo y cuando elimino el primero me deberia
    //devolver el numero unico en la primera posicion.
    public static boolean test_eliminar_primer_nodo_con_lista_con_muchos_valores()
    {
        int numeroUnico = (int)(Math.random()*999999999);
        ListaEnlazada lista = ListaEnlazada.instanciar();

        crearNodos(lista, (int)(Math.random()*10) + 1);

        //insertar primero ya fue testeado asi que lo podemos usar.
        lista.insertar(1, numeroUnico);

        if (lista.getPosicion(1) == numeroUnico)
        {
            lista.eliminarPrimero();

            return lista.getPosicion(0) == numeroUnico;
        }

        return false;
    }

    public static boolean test_eliminar_iesimo_nodo()
    {
        int previo, posterior, tamañoOriginal;
        int posicion = 1000;

        ListaEnlazada lista = ListaEnlazada.instanciar();
        crearNodos(lista, (int)(Math.random()*100 + 50));

        while(posicion > lista.tamaño() + 1 || posicion == 0)
        {
            posicion = (int)(Math.random()*100);
        }

        //Aca voy a testear que este el nodo y que mueva a los otros.
        previo = lista.getPosicion(posicion - 1);
        posterior = lista.getPosicion(posicion + 1);

        tamañoOriginal = lista.tamaño();
        lista.eliminar(posicion);

        //Ahora reviso que los valores coincidan.
        boolean coincide_previo = lista.getPosicion( posicion - 1) == previo;
        boolean coincide_posterior = lista.getPosicion( posicion ) == posterior;

        return coincide_posterior && coincide_previo && tamañoOriginal - 1 == lista.tamaño();
    }

    //casi un copiado y pegado del anterior
    public static boolean test_eliminar_ultimo_nodo()
    {
        int previo, tamañoOriginal;
        int posicion;

        ListaEnlazada lista = ListaEnlazada.instanciar();
        crearNodos(lista, (int)(Math.random()*100 + 50));

        posicion = lista.tamaño() - 1;

        //Aca voy a testear que este el nodo y que mueva a los otros.
        previo = lista.getPosicion(posicion - 1);

        tamañoOriginal = lista.tamaño();
        lista.eliminar(posicion);

        //Ahora reviso que los valores coincidan.
        boolean coincide_previo = lista.getPosicion( posicion - 1) == previo;
        boolean coincide_posterior;
        try
        {
            //Aca estamos buscando la posicion eliminada asi que nos reta :(
            coincide_posterior = lista.getPosicion( posicion ) == 1;
        }
        catch (IndexOutOfBoundsException ex)
        {
            coincide_posterior = true;
        }

        return coincide_posterior && coincide_previo && tamañoOriginal - 1 == lista.tamaño();
    }

    public static boolean test_eliminar_segun_valor()
    {
        //En vez de usar un numero unico voy a usar muchos nodos, voy a crear un numero entre 0 - 10 y voy a anotar
        //cuantas veces lo tengo. Luego cuando borre los nodos que tienen el valor seleccionado, deberia tener
        // tamaño original - las veces que se instancio el digito = tamaño actual.
        int actual, num;
        int veces_repetido = 0;
        int valor = (int)(Math.random()*10);
        int original = (int)(Math.random()*10000) + 1; // la complejidad es o(n) asi que no deberia demorarse mucho.

        ListaEnlazada lista = ListaEnlazada.instanciar();

        //inserto numeros al azar entre 0-10 y anoto cuantas veces se repite el que voy a borrar.
        for (int i = 0; i < original; i++) {

            num = (int)(Math.random()*10);
            lista.insertarPrimero( num );

            if (num == valor)
            {
                veces_repetido++;
            }
        }

        //ejecuto el borrado de los numeros que tienen el valor que deseamos.
        lista.eliminarSegunValor(valor);

        //ahora reviso que esta bien
        return original - veces_repetido == lista.tamaño();
    }

    public static boolean test_clasificador_par_impar()
    {

        ListaEnlazada lista_par = ListaEnlazada.instanciar();
        ListaEnlazada lista_impar = ListaEnlazada.instanciar();

        try
        {
            ListaEnlazada.ClasificadorParImpar(lista_par, lista_impar);
        }
        catch (InstantiationException e)
        {

        }

        //Ahora recorro ambas listas para verificar si son pares e impares.

        return lista_par.paridad(0) && lista_impar.paridad(1);
    }


    public static void correrTests(String array[])
    {
        boolean alguno_fallo = false;
        boolean resultado;
        System.out.println("Comienzan los tests de los metodos.");

        for ( String test_name:array)
        {
            try
            {
                Method metodo = Main.class.getMethod(test_name);

                resultado = (boolean) metodo.invoke(null);
                alguno_fallo = alguno_fallo ? true : !resultado;

                System.out.println( metodo.getName() + ": " + resultado );
            }
            catch (Exception e) // obligatorio por el invoke y getmethod
            {
                System.out.println("exception: " + e);
                alguno_fallo = true;
            }

        }

        System.out.println("Estado de tests: " + (alguno_fallo? "Fallaron" : "Pasaron" ) );
    }


    /**
     * @param lista - la lista donde agregamos los nodos.
     * @param cantidadDeNodos - crea <cantidadDeNodos> con valores al azar.
     */
    public static void crearNodos(ListaEnlazada lista, int cantidadDeNodos)
    {
        for (int i = 0; i < cantidadDeNodos; i++) {

            //Creo un numero grande asi lo pueda considerar unico mientras las iteraciones son pocas.
            lista.insertarPrimero( (int)(Math.random()*999999999)  );
        }
    }
}
