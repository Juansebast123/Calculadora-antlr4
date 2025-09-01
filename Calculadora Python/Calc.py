import sys
from antlr4 import *
from LabeledExprLexer import LabeledExprLexer
from LabeledExprParser import LabeledExprParser
from EvalVisitor import EvalVisitor

def main():
    input_stream = InputStream(sys.stdin.read())
    lexer = LabeledExprLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = LabeledExprParser(stream)
    tree = parser.prog()

    eval = EvalVisitor()
    eval.visit(tree)

if __name__ == '__main__':
    main()
