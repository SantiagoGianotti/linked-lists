Consideraciones a la hora de hacer el trabajo.

*Primero: 
         utilizando una metodologia de testing, voy a proceder a probar cada una de las funcionalidades
         que son requeridas por el apartado asi practico un poco de java, y supongo que puede facilitar la correccion.
         Uso : el array controla los test a ser ejecutados, y printea el resultado en consola.
            
            
*Segundo: 
        Ya que el trabajo practico dice explicitamente que hay que utilizar listas enlazadas, voy a evitar
        utilizar listas enlazadas dobles ( sospechaba que existian asi que lo googlee ).
        
        
*Tercero:
        Preferi agregar el campo cantidad en memoria, ya que considero que aunque se agregue un poco de
        mantenimiento extra al codigo, va a rendir mucho mejor. Sin embargo para los casos que vamos a poner a prueba
        no hay diferencia alguna por el (n) tan pequeño.
        
        
*Cuarto: 
        Debido a que no voy a importar ninguna herramienta para testing, los test son un poco "artesanales".
        Hubiera sido mejor comparar la referencia del nodo mas que el valor en los tests, sin embargo preferi dejar
        los nodos completamente encapsulados. Por lo tanto en la mayoria de los tests para testear que sea la misma
        referencia del int, utilizo numeros al azar muy grandes y no mas de 10 nodos de manera que sea improbable que
        se repita el numero y entonces lo consideramos como unico.
        
        
Santiago Gianotti
