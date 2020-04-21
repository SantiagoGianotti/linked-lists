package com.eda.tp3;

import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Vector;

public class Tp3Helper
{

    public static boolean digitoAusente(int cadena, char digito)
    {
        char ultimoDigito;

        //Planteo el caso base.
        if (cadena == 0)
        {
            return true;
        }

        ultimoDigito = (char)(cadena % 10);

        //Analizo el ultimo digito y si es igual al que estoy buscando, entonces salgo.
        if (digito == ultimoDigito)
        {
            return false;
        }

        //Como el digito no era el que estaba buscando entonces avanzo
        //(cadena / 10) es lo mismo que sacarle el ultimo digito a el numero.
        return digitoAusente((cadena / 10), digito);
    }

    public static boolean buscaPar(Vector<Integer> miVector, int x, int y)
    {
        int temp;

        //Primero ordeno el vector
        Collections.sort(miVector);

        //ahora que esta ordenada la coleccion utilizo una busqueda binaria.

        //Por condicion voy a hacer que X sea menor o igual a y, asi limito mas abajo las distintas combinaciones.
        if (x > y)
        {
            temp = y;
            y = x;
            x = temp;
        }

        return busquedaBinariaRecursiva(x, y, 0, miVector.size(), miVector);
    }


    private static boolean busquedaBinariaRecursiva(int buscoX, int buscoY, int lim_izq, int lim_der, Vector<Integer> miVector)
    {
        int posActual, numeroActual, numeroContiguo;
        Boolean pego_en_x;

        //Caso base me quede sin numeros.
        if (lim_izq == lim_der)
        {
            return false;
        }

        posActual = (lim_der + lim_izq)/2;
        numeroActual = miVector.get(posActual);

        pego_en_x = numeroActual == buscoX;

        //Si encontre x o y.
        if (pego_en_x || numeroActual == buscoY)
        {
            //reviso el numero contiguo y, como X no es mayor a Y, puedo hacer lo siguiente para obtener el numero contiguo
            numeroContiguo = miVector.get((pego_en_x? 1 : -1) + posActual);

            /*
                Analizo el numero continuo segun haya caido en x o en y.
                Luego si pego en x pero el numero contiguo es tambien x ( por que podria estar repetido )
                entonces llamo a la busqueda binaria de nuevo, ya que nada garantiza que no hay n veces x,
                y la forma mas rapida de descubrirlo vuelve a ser la busqueda binaria en ese caso.

             */
            if (pego_en_x)
            {
                if (numeroContiguo == buscoY )
                    return true;

                if (numeroContiguo == numeroActual)
                {
                    return busquedaBinariaRecursiva(buscoX, buscoY, posActual + 1, lim_der, miVector);
                }

            }
            else
            {
                if (numeroContiguo == buscoX)
                    return true;

                if ( numeroContiguo == numeroActual)
                {
                    return busquedaBinariaRecursiva(buscoX, buscoY, lim_izq,posActual - 1, miVector);
                }
            }

            //Si hasta aqui no salio entonces es por que el numero contiguo es distinto.
            return false;

        }


        //Yo se que si encuentro caigo en un valor que esta entre buscoX y buscoY
        //entonces no son continuos.
        if ( numeroActual > buscoX && numeroActual < buscoY )
        {
            return false;
        }

        if (buscoX > numeroActual)
        {
            return busquedaBinariaRecursiva(buscoX, buscoY, posActual + 1, lim_der, miVector);
        }
        else
        {
            return busquedaBinariaRecursiva(buscoX, buscoY, lim_izq,posActual - 1, miVector);
        }

    }

}
