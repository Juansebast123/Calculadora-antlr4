import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Calc {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromStream(System.in);
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog();

        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
    }
}
