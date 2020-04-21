package com.eda.tp3;

import java.lang.reflect.Method;
import java.util.Vector;

public class Main {

    /*
        Santiago Gianotti
     */



    public static void main(String[] args)
    {

        String[] test_array = new String[]
                {
                    "test_numero_pertenece", //listo
                    "test_suma_pares", //listo
                    "test_busqueda_binaria_no_hay_contiguo_falla",
                    "test_busqueda_binaria_estan_contiguos",
                    "test_busqueda_binaria_estan_contiguos_muchos",
                    "test_busqueda_binaria_no_estan_contiguos_muchos",
                };

        correrTests(test_array);

    }

    public static boolean test_numero_pertenece()
    {
        int numero;
        Boolean el_numero_esta_presente, el_num_esta_ausente;
        int digito = (int)(Math.random()*9);


        //Verifico 500 veces que coincidan los resultados
        for (int i = 0; i < 500; i++)
        {
            numero = (int)(Math.random()*99999);

            el_numero_esta_presente = String.valueOf(numero).contains(String.valueOf(digito));
            el_num_esta_ausente = Tp3Helper.digitoAusente(numero, (char)digito);

            if (el_numero_esta_presente == el_num_esta_ausente )
            {
                return false;
            }
        }

        return true;
    }
    public static boolean test_suma_pares()
    {
        ListaEnlazada lista = ListaEnlazada.instanciar();
        int numero, acumulador = 0;

        //Voy a agregar dinamicamente nodos y hago la suma desde afuera.

        for (int i = 0; i < 1000; i++) {

            numero = (int)(Math.random()*99);

            if (numero % 2 == 0 )
            {
                acumulador+= numero;
            };

            lista.insertarPrimero(numero);
        }
        int suma = lista.sumatoriaPares();
        return acumulador == suma;
    }

    public static boolean test_busqueda_binaria_no_hay_contiguo_falla()
    {
        boolean resultado;

        Vector miVector = new Vector();

        for (int i = 0; i < 50; i++) {
            miVector.add((int)(Math.random()*999));
        }


        miVector.add(5);
        miVector.add(7);
        miVector.add(10);

        return Tp3Helper.buscaPar(miVector, 5, 10) == false;
    }

public static boolean test_busqueda_binaria_estan_contiguos()
    {
        boolean resultado;

        Vector miVector = new Vector();

        for (int i = 0; i < 50; i++) {

            miVector.add((int)(Math.random()*999) + 15);
        }

        miVector.add(5);
        miVector.add(10);

        return Tp3Helper.buscaPar(miVector, 5, 10);
    }

public static boolean test_busqueda_binaria_estan_contiguos_muchos()
    {
        boolean resultado;

        Vector miVector = new Vector();

        for (int i = 0; i < 50; i++) {

            miVector.add((int)(Math.random()*999) + 15);
        }

        miVector.add(5);
        miVector.add(5);
        miVector.add(5);
        miVector.add(5);
        miVector.add(10);
        miVector.add(10);
        miVector.add(10);

        return Tp3Helper.buscaPar(miVector, 5, 10);
    }

public static boolean test_busqueda_binaria_no_estan_contiguos_muchos()
    {
        boolean resultado;

        Vector miVector = new Vector();

        for (int i = 0; i < 50; i++) {

            miVector.add((int)(Math.random()*999));
        }

        miVector.add(5);
        miVector.add(5);
        miVector.add(5);
        miVector.add(5);
        miVector.add(7);
        miVector.add(10);
        miVector.add(10);
        miVector.add(10);

        resultado = Tp3Helper.buscaPar(miVector, 5, 10);
        return Tp3Helper.buscaPar(miVector, 5, 10) == false;
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
