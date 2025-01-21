package mini.parser.ast.expr;

public record UnaryExpr(UnaryExprType tag, IExpr child) implements IExpr {
}
