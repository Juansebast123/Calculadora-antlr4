**Juan Sebastian Gonzalez Alvarez**

El presente documento describe el diseño de una calculadora aritmética desarrollada utilizando ANTLR (en Java y Python)
La calculadora es capaz de interpretar y evaluar expresiones matemáticas que incluyen operaciones básicas (+, -, *, /), funciones trigonométricas (sin, cos, tan), manejo de variables y precedencia de operadores.

**Explicacion Archivos**

Gramatica (LabeledExpr.g4)
- Se definen las expresiones aritmeticas se tokeniza.

EvalVisitor
- Crea metodos vacios como: visitAddSub, visit MulDiv, visitFunction, visitAssign, visitPrintExpr, visitInt, visitFloat, etc...
- Evalua cada nodo segun la funcion que debe hacer.
- Retorna los resultados.

Lexer (Lo genera antlr4)
- Lee el texto de entrada y lo divide en tokens para su entendimiento.

Parser (Lo genera antlr4)
- Toma los tokens generados por el lexer y los ordena segun la gramatica.
- Crea un arbol que representa las posibles combinaciones de los tokens.

Calc
- Lee la entrada, la pasa al lexer, pasa los tokens al parser, usa el EvalVisitor, imprime el resultado en consola.
