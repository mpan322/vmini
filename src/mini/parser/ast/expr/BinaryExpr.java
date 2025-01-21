package mini.parser.ast.expr;

public record BinaryExpr(BinaryExprType tag, IExpr left, IExpr right) implements IExpr {
}
