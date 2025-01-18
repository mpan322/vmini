### Grammar
prog ::= fun_decl*

fun_decl ::= FN IDENT LPRN params? RPRN body
params   ::= IDENT (COMMA IDENT)*
body     ::= RBR stmt* LBR

stmt       ::= (var_decl | var_assign | return) SEMICOLON
exprs      ::= expr (COMMA expr)*  
var_decl   ::= LET IDENT ASSIGN expr
var_assign ::= IDENT ASSIGN expr
return     ::= RETURN expr

expr ::= term ADD expr 
       | term SUB expr 
       | term MUL expr

term ::= term
       | SUB expr 
       | IDENT LPRN exprs? RPRN
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

