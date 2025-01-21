package mini;

import mini.parser.MiniParser;
import mini.parser.ParseException;
import mini.parser.ast.FunDecl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        MiniParser parser = new MiniParser(new FileReader(args[0]));
        List<FunDecl> prog = parser.Prog();

        Compiler compiler = new Compiler(prog);

        PrintWriter writer = new PrintWriter(System.out);
        compiler.compile(writer);
        writer.close();
    }
}
