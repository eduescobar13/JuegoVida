# ESIT ULL Grado de Informática
## PROGRAMACIÓN DE APLICACIONES INTERACTIVAS. Implementación en Java del Juego de la Vida.
#### Realizada por Eduardo Escobar Alberto.

##### **Contextualización**
El programa implementado en este repositorio toma como punto de partida el artículo publicado en octubre de 1970 por **Martin Gardner** en la sección Juegos Matemáticos de la revista Scientific American, cuyo título es **The fantastic combinations of John Conway’s new solitaire game “life”**. A través de dicho artículo, se dio a conocer al gran público el trabajo del matemático británico John H. Conway.

El Juego de la Vida (del inglés, Game of Life o simplemente Life) es el mejor ejemplo de un autómata celular, un modelo matemático para un sistema dinámico que evoluciona en pasos discretos. Desde un punto de vista teórico, el interés del juego de la vida procede de que es equivalente a una máquina universal de Turing. El resultado de lo anterior es que todo lo que pueda computarse algorítmicamente, también podrá computarse en el Juego de la Vida.

##### **Explicación**
El Juego de la Vida es un “juego” solitario, es decir, la evolución del mismo depende del estado inicial de su universo, no existiendo ningún otro tipo de interacción con el mismo más que la especificación de ese estado inicial (denominado semilla). El universo consiste en un **tablero bidimensional de células o casillas**, las cuales pueden tener dos posibles estados: 
* **VIVA (POBLADA).**
* **MUERTA (NO POBLADA).** 

En cada paso, cada célula interactúa con sus ocho células vecinas, es decir, aquellas células horizontal, vertical y diagonalmente adyacentes, siguiendo un conjunto de reglas, las cuales se aplican de manera simultánea a todas y cada una de las células del universo.

##### **Ejecución**
El programa debe invocarse de la siguiente manera:
    
    java JuegoVida numeroDePasos input.txt output.txt [debug]

* **numeroDePasos:** número de pasos que debe evolucionar la configuración inicial.
* **input.txt:** fichero de entrada especificando la configuración inicial del universo.
* **output.txt:** fichero de salida conteniendo la evolución del universo.
* **debug**: En caso de que se especifique este parámetro (es opcional), el contenido del fichero de salida deberá contener la evolución del universo en cada uno de los pasos partiendo de la configuración inicial. En caso contrario, el fichero de salida sólo contendrá los estados inicial y final del universo, es decir, tras haber ejecutado el número de pasos especificado por numberOfSteps.
