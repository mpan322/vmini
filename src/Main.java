import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import vmasm.Assembler;
import vmasm.Code;
import vmasm.Machine;
import vmasm.parser.ParseException;
import vmasm.parser.Parser;
import vmasm.parser.symbol.Symbol;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Parser parser = new Parser(new FileReader(args[0]));
        List<Symbol> i = parser.Prog();
        Code code = new Assembler().assemble(i);
        Machine machine = new Machine(code);
        machine.exec();
    }
}
