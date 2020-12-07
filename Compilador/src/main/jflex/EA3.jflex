package Analizadores;
import java_cup.runtime.*;

%%      /* Directivas */
%public
%class AnalizadorLexico
%cupsym Simbolos
%cup
%column
%line
%ignorecase
%unicode

        /* Codigo del usuario en java */

%{
    private Symbol symbol(int type) {
//        System.out.println("[LEX] TOKEN < " + Simbolos.terminalNames[type] + " > : " + yytext());
        return new Symbol(type, yyline, yycolumn, yytext());
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

        /* Expresiones regulares */

DIGITO = [0-9]
LETRA = [a-zA-Z]

CARACTERES_ESPECIALES = [\@\$\_\~\\\,\%]
CARACTERES_NO_PERMITIDOS = [#\^\*\(\)\-\+\=\|\/\?\>\<\!\;\.\:\'\"\[\]\{\}]
CADENA = ({CARACTERES_ESPECIALES}|{CARACTERES_NO_PERMITIDOS}|{DIGITO}|{LETRA}|" "|"\t")

CTE = {DIGITO}+
CTE_S = "\""{CADENA}*"\""

ID = {LETRA}({LETRA}|{DIGITO})*

%%      /* Regla lexicas */

<YYINITIAL> "WRITE"	    { return symbol(Simbolos.write); }
<YYINITIAL> "PROMR"		{ return symbol(Simbolos.promr); }
<YYINITIAL> "READ"	    { return symbol(Simbolos.read); }


<YYINITIAL> "="	        { return symbol(Simbolos.asigna); }
<YYINITIAL> "("	        { return symbol(Simbolos.para); }
<YYINITIAL> ")"	        { return symbol(Simbolos.parc); }
<YYINITIAL> "["	        { return symbol(Simbolos.ca); }
<YYINITIAL> "]"	        { return symbol(Simbolos.cc); }
<YYINITIAL> ","	        { return symbol(Simbolos.coma); }
<YYINITIAL> ";"			{ return symbol(Simbolos.pyc); }

<YYINITIAL> {ID}        { return symbol(Simbolos.id); }

<YYINITIAL> {CTE} {
            Integer value = Integer.parseInt(yytext());
            if(value >= 0) return symbol(Simbolos.cte);
            else throw new Error("La constante <" + yytext() + "> esta fuera del limite de los enteros");
      }

<YYINITIAL> {CTE_S} {
            String value = yytext();
            if(value.length() <= 128) return symbol(Simbolos.cte_s);
            else throw new Error("La constante <" + yytext() + "> esta fuera del limite de los string");
      }


[\ \t\r\n\f]              {/* Ignora espacios en blanco */}


[^]    { throw new Error("Error: Caracter invalido " + yytext() + " " + (yyline + 1) + ":" + (yycolumn + 1) ); }