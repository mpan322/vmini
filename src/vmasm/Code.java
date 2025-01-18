package vmasm;

import vmasm.parser.operand.Operand;

import java.util.List;

public record Code(List<Integer> ops, List<Operand> data) {
}
