


/* --------------------------Usercode Section------------------------ */
package challenge;
import java_cup.runtime.*;

%%

/* -----------------Options and Declarations Section----------------- */

/*
   The name of the class JFlex will create will be Lexer.
   Will write the code to the file Lexer.java.
*/
%class Lexer

/*
  The current line number can be accessed with the variable yyline
  and the current column number with the variable yycolumn.
*/
%line
%column

/*
   Will switch to a CUP compatibility mode to interface with a CUP
   generated parser.
*/
%cup


%{

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}





/* White space is a line terminator, space, tab, or line feed. */
WhiteSpace     = [ \t\f]
letter          = [A-Za-z]
digit           = [0-9]
identifier = ({letter}|{digit}) ({letter}|{digit})*
member = \"({letter}|{digit}) ({letter}|{digit})*\"
dec_int_lit = 0 | [1-9][0-9]*



%%


<YYINITIAL> {


    "GET"                { return symbol(sym.GET); }
    "SET"                { return symbol(sym.SET); }
    "seconds"                { return symbol(sym.SECONDS); }
    "DEL"                { return symbol(sym.DEL); }
    "DBSIZE"                { return symbol(sym.DBSIZE); }
    "INCR"                { return symbol(sym.INCR); }
    "ZADD"                { return symbol(sym.ZADD); }
    "ZCARD"                { return symbol(sym.ZCARD); }
    "ZRANK"                { return symbol(sym.ZRANK); }
    "ZRANGE"                { return symbol(sym.ZRANGE); }


    {dec_int_lit}      { return symbol(sym.NUMBER, new Integer(yytext())); }

    {member}            { return symbol(sym.MEMBER, yytext()); }


    {identifier}     { return symbol(sym.VALUE, yytext()); }

    /* Don't do anything if whitespace is found */
    {WhiteSpace}       { /* just skip what was found, do nothing */ }

}


[^]                    { throw new Error("Illegal character <"+yytext()+">"); }
