package com.eda.tp5;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Vector;

public class Main {

    /*
        Santiago Gianotti
     */



    public static void main(String[] args)
    {

        String[] test_array = new String[]
                {
                        "test_instanciar",
                        "test_push",
                        "test_push_pop",
                        "test_push2_pop1",
                        "test_push2_top",
                        "test_push3_top",
                        "test_top_empty_throws_exception",
                        "test_pop_empty_throws_exception"

                };

        correrTests(test_array);

    }

    public static boolean test_instanciar()
    {
        PilaClass<Character> pila = new PilaClass<Character>();

        return pila.esPilaVacia();
    }

    public static boolean test_push()
    {
        PilaClass<Character> pila = new PilaClass<Character>();

        pila.push('a');

        return !pila.esPilaVacia();
    }

    public static boolean test_pop_empty_throws_exception()
    {
        PilaClass<Character> pila = new PilaClass<Character>();

        try {
            pila.pop();
        } catch (EmptyStackException e)
        {
            return true;
        }
        return false;
    }

    public static boolean test_top_empty_throws_exception()
    {
        PilaClass<Character> pila = new PilaClass<Character>();

        try {
            pila.top();
        } catch (EmptyStackException e)
        {
            return true;
        }
        return false;
    }

        public static boolean test_push_pop()
    {
        PilaClass<Character> pila = new PilaClass<Character>();

        pila.push('a');
        pila.pop();

        return pila.esPilaVacia();
    }

    public static boolean test_push2_pop1()
    {
        PilaClass<Character> pila = new PilaClass<Character>();

        pila.push('a');
        pila.push('b');
        pila.pop();

        return pila.tamaño() == 1;
    }

    public static boolean test_push2_top()
    {
        PilaClass<Character> pila = new PilaClass<Character>();

        pila.push('a');
        pila.push('b');

        return pila.top() == 'b';
    }

    public static boolean test_push3_top()
    {
        PilaClass<Character> pila = new PilaClass<Character>();

        pila.push('a');
        pila.push('b');
        pila.push('c');
        pila.pop();

        return pila.top() == 'b';
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
