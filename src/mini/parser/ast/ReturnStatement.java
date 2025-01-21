package mini.parser.ast;

import mini.parser.ast.expr.IExpr;

public record ReturnStatement(IExpr value) implements Statement {
}
