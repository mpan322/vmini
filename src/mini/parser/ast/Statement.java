package mini.parser.ast;

public sealed interface Statement permits ReturnStatement, LetStatement, AssignStatement {
}
