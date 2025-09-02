**Juan Sebastian Gonzalez Alvarez**

El documento describe el diseño de una calculadora aritmetica realizada utilizando ANTLR4 (en Java y Python).
La calculadora es capaz de interpretar y evaluar expresiones matematicas que incluyen operaciones basicas (+, -, *, /), funciones trigonometricas (sin, cos, tan), manejo de variables y precedencia de operadores.

**Explicacion Archivos**

Gramatica (LabeledExpr.g4)
- Se definen las expresiones aritmeticas.

EvalVisitor
- Crea metodos vacios como: visitAddSub, visit MulDiv, visitFunction, visitAssign, visitPrintExpr, visitInt, visitFloat, etc...
- Evalua cada nodo segun la funcion que debe hacer.
- Retorna los resultados.

  Metodos:
  - visitInt: lee los numeros enteros.
  - visitFloat: lee numeros decimales.
  - visitAddSub: suma o resta.
  - visitMulDiv: multiplica o divide.
  - visitFunction: evalua sin(x), cos(x), etc. usando math en Python.
  - visitAssign: guarda variables en un diccionario (memory).
  - visitId: devuelve el valor de una variable.
  - visitFactorial: calcula factorial.

Lexer (Lo genera antlr4)
- Lee el texto de entrada y lo divide en tokens para su entendimiento.

Parser (Lo genera antlr4)
- Toma los tokens generados por el lexer y los ordena segun la gramatica.
- Crea un arbol que representa las posibles combinaciones de los tokens.

Calc
- Lee la entrada, la pasa al lexer, pasa los tokens al parser, usa el EvalVisitor, imprime el resultado en consola.
