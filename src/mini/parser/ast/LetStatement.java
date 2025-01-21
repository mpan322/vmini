package mini.parser.ast;

import mini.parser.ast.expr.IExpr;

public record LetStatement(String ident, IExpr value) implements Statement {
}
