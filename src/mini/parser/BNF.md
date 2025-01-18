### Grammar
prog ::= fun_decl*

fun_decl ::= FN IDENT LPRN params? RPRN body
params   ::= IDENT (COMMA IDENT)*
body     ::= RBR stmt* LBR

stmt       ::= (fun_call | var_decl | var_assign | return) SEMICOLON
fun_call   ::= IDENT LPRN exprs? RPRN 
exprs      ::= expr (COMMA expr)*  
var_decl   ::= LET IDENT ASSIGN expr
var_assign ::= IDENT ASSIGN expr
return     ::= RETURN expr

expr ::= expr ADD expr 
       | expr SUB expr 
       | expr MUL expr 
       | SUB expr 
       | NUM
       | IDENT

### Tokens
IDENT = [a-zA-Z]+
LPRN  = (
RPRN  = )
LBR   = {
RBR   = }
NUM   = [0-9]+
ADD   = +
SUB   = -
MUL   = *
FN    = fn
COMMA = ,
RETURN = return
SEMICOLON = ;

