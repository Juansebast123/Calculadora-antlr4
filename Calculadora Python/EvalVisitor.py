import math
from LabeledExprVisitor import LabeledExprVisitor
from LabeledExprParser import LabeledExprParser

class EvalVisitor(LabeledExprVisitor):
    def __init__(self):
        self.memory = {}
        self.useDegrees = True  # True = grados, False = radianes

    def visitAssign(self, ctx):
        id = ctx.ID().getText()
        value = self.visit(ctx.expr())
        self.memory[id] = value
        return value

    def visitPrintExpr(self, ctx):
        value = self.visit(ctx.expr())
        print(value)
        return 0.0

    def visitInt(self, ctx):
        return float(ctx.INT().getText())

    def visitFloat(self, ctx):
        return float(ctx.FLOAT().getText())

    def visitId(self, ctx):
        id = ctx.ID().getText()
        return self.memory.get(id, 0.0)

    def visitMulDiv(self, ctx):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == LabeledExprParser.MUL:
            return left * right
        else:
            return left / right

    def visitAddSub(self, ctx):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == LabeledExprParser.ADD:
            return left + right
        else:
            return left - right

    def visitParens(self, ctx):
        return self.visit(ctx.expr())

    def visitFunction(self, ctx):
        value = self.visit(ctx.expr())
        if value is None:
            return 0.0

        arg = value
        func = ctx.func.text

        if self.useDegrees and func in ["Sin", "Cos", "Tan"]:
            arg = math.radians(arg)

        if func == "Sin":
            return math.sin(arg)
        elif func == "Cos":
            return math.cos(arg)
        elif func == "Tan":
            return math.tan(arg)
        elif func == "Sqrt":
            return math.sqrt(arg)
        elif func == "Ln":
            return math.log(arg)
        elif func == "Log":
            return math.log10(arg)
        else:
            return 0.0

    def visitFactorial(self, ctx):
        n = int(self.visit(ctx.expr()))
        return float(self.factorial(n))

    def factorial(self, n):
        if n <= 1:
            return 1
        return n * self.factorial(n - 1)
