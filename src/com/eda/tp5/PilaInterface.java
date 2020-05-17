package com.eda.tp5;

public interface PilaInterface<T> {
    /*
        java es un lenguaje orientado a objetos, por lo que andar pasando la referencia
        no tiene sentido. Entonces defino las operaciones directamente sobre la instancia
        de la pila.

         PILAVACIA: -> PILA
         PUSH: PILA x item -> PILA
         POP: PILA -> PILA
         TOP: PILA -> item ∪ {indefinido}
         ESPILAVACIA : PILA -> Bool
     */
    void push(T objToPush);
    void pop();
    T top();
    boolean esPilaVacia();

}
