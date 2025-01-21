package mini.parser.ast.expr;

public sealed interface IExpr permits BinaryExpr, IdentExpr, NumExpr, UnaryExpr {
}
