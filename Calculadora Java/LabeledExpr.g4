grammar LabeledExpr;

prog:   stat+ ;

stat:   expr NEWLINE                # printExpr
    |   ID '=' expr NEWLINE         # assign
    |   NEWLINE                     # blank
    ;

expr:   expr op=('*'|'/') expr      # MulDiv
    |   expr op=('+'|'-') expr      # AddSub
    |   FLOAT                       # float
    |   INT                         # int
    |   ID                          # id
    |   '(' expr ')'                # parens
    |   func=ID '(' expr ')'        # function
    |   expr '!'                    # factorial
    ;

MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;

ID    : [a-zA-Z]+ ;
FLOAT : [0-9]+ '.' [0-9]+ ;
INT   : [0-9]+ ;
NEWLINE:'\r'? '\n' ;
WS    : [ \t]+ -> skip ;
    
