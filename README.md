# ESIT ULL Grado de Informática
## DISEÑO Y ANÁLISIS DE ALGORITMOS. Implementación de algoritmos constructivos y búsquedas por entornos para el Max-Mean Dispersion Problem.
#### Realizada por Eduardo Escobar Alberto.

El programa implementado en este repositorio toma como punto de partida el artículo publicado en octubre de 1970 por **Martin Gardner** en la sección Juegos Matemáticos de la revista Scientific American, cuyo título es **The fantastic combinations of John Conway’s new solitaire game “life”**. A trave ́s de dicho art ́ıculo, se dio a conocer al gran pu ́blico el trabajo del matema ́tico brita ́nico John H. Conway.
El Juego de la Vida (del ingle ́s, Game of Life o simplemente Life) [2, 3] es el mejor ejemplo de un auto ́mata celular, un modelo matema ́tico para un sistema dina ́mico que evoluciona en pasos discretos. Desde un punto de vista teo ́rico, el intere ́s del juego de la vida procede de que es equivalente a una ma ́quina universal de Turing. El resultado de lo anterior es que todo lo que pueda computarse algor ́ıtmicamente, tambie ́n podra ́ computarse en el Juego de la Vida.
El Juego de la Vida es un “juego” solitario, es decir, la evolucio ́n del mismo depende del estado inicial de su universo, no existiendo ningu ́n otro tipo de interaccio ́n con el mismoma ́squelaespecificacio ́ndeeseestadoinicial(denominadosemilla).Eluniverso consiste en un tablero bidimensional de ce ́lulas o casillas, las cuales pueden tener dos posibles estados: viva (poblada) o muerta (no poblada). En cada paso, cada ce ́lula interactu ́a con sus ocho ce ́lulas vecinas, es decir, aquellas ce ́lulas horizontal, vertical y diagonalmente adyacentes, siguiendo un conjunto de reglas, las cuales se aplican de manera simulta ́nea a todas y cada una de las ce ́lulas del universo. En [4] puede interactuar con un Applet Java que ilustra el funcionamiento del Juego de la Vida.
##### **Formato de las intancias del problema**
Las instancias del problema se suministrarán en un fichero de texto con el siguiente formato: 
* **PRIMERA FILA:** Se encuentra el **número de vértices, n**. 
* **SIGUIENTES FILAS:** Se enumeran las **afinidades, d(i, j)**, entre los pares de vértices (se asume que las afinidades son simétricas, es decir, que d(i, j) = d( j, i), ∀i, j ∈ V. Además, d(i, i) = 0, ∀i ∈ V).

##### **Algoritmos implementados**
* Constructivo voraz.
* Destructivo voraz.
* GRASP.
* Método Multiarranque.
* Búsqueda por Entorno Variable.

**Las tablas y gráficas con los resultados se encuentran en el fichero Informe.pdf**
