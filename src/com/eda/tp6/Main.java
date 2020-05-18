package com.eda.tp6;

import com.eda.tp6.servidor.Servidor;
import com.eda.tp6.utils.ServerParamsSingleton;

import java.lang.reflect.Method;

public class Main {

    /*
        Notas:
                Decidi utilizar un singleton para almacenar la probabilidad de ingreso / usuario registrado
                por que asi separo un poco las responsabilidades, y puedo manejar las probabilidades de manera global.
                Hubiera preferido utilizar un closure para definir la logica interna del calculo de probabilidad
                pero almacenar una expresion lambda en una variable es un poco mas complejo que en otros lenguajes
                como javascript o dart, por lo que preferi una implementacion un poco mas sencilla.

                Algunas variables fueron renombradas de la guia de implementacion ya que prefiero tener
                variables mas descriptivas, por que aunque sean mas largas de leer, ayuda muchisimo con
                la carga cognitiva al entender que hace cada cosa. Soy conciente que las convenciones de nombres
                para java dice que hay que usar nombres cortos y concisos para las variables.

                Tambien podria haber definido el tiempo de la instancia en el singleton, pero preferi no hacerlo
                de manera que se puedan instanciar servidores con distintos tiempos a la vez.


        Santiago Gianotti
     */



    public static void main(String[] args)
    {
        ServerParamsSingleton singleton = ServerParamsSingleton.getInstance();
        Servidor miServidor = new Servidor(10000);
        miServidor.procesar();

        singleton.setProbIngreso(200);
        singleton.setProbRegistro(5);

        miServidor.procesar();

        miServidor = new Servidor(5000);
        miServidor.procesar();

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
}
