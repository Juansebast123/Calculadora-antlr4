import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Double> {
    Map<String, Double> memory = new HashMap<>();
    boolean useDegrees = true; // true = grados, false = radianes

    @Override
    public Double visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        double value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Double visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        Double value = visit(ctx.expr());
        System.out.println(value);
        return 0.0;
    }

    @Override
    public Double visitInt(LabeledExprParser.IntContext ctx) {
        return Double.valueOf(ctx.INT().getText());
    }

    @Override
    public Double visitFloat(LabeledExprParser.FloatContext ctx) {
        return Double.valueOf(ctx.FLOAT().getText());
    }

    @Override
    public Double visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) return memory.get(id);
        return 0.0;
    }

    @Override
    public Double visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.MUL) return left * right;
        return left / right;
    }

    @Override
    public Double visitAddSub(LabeledExprParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.ADD) return left + right;
        return left - right;
    }

    @Override
    public Double visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitFunction(LabeledExprParser.FunctionContext ctx) {
        Double value = visit(ctx.expr());
        if (value == null) return 0.0;

        double arg = value;
        if (useDegrees && (ctx.func.getText().equals("Sin") ||
                           ctx.func.getText().equals("Cos") ||
                           ctx.func.getText().equals("Tan"))) {
            arg = Math.toRadians(arg);
        }

        switch (ctx.func.getText()) {
            case "Sin": return Math.sin(arg);
            case "Cos": return Math.cos(arg);
            case "Tan": return Math.tan(arg);
            case "Sqrt": return Math.sqrt(arg);
            case "Ln": return Math.log(arg);
            case "Log": return Math.log10(arg);
            default: return 0.0;
        }
    }

    @Override
    public Double visitFactorial(LabeledExprParser.FactorialContext ctx) {
        int n = visit(ctx.expr()).intValue();
        return (double) factorial(n);
    }

    private long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
}
