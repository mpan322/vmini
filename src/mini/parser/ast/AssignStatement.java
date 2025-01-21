package mini.parser.ast;

import mini.parser.ast.expr.IExpr;

public record AssignStatement(String ident, IExpr value) implements Statement {
}
