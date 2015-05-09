grammar FOL;

options {
language=Java;
output=AST;
ASTLabelType=CommonTree;
rewrite=true;
backtrack=true;
}

tokens {
	LPAR = '(';
	RPAR = ')';
	NOT = '-';
	OR = '|';
	C = ',';
}

@rulecatch {
    // ANTLR does not generate its normal rule try/catch
    catch(RecognitionException e) {
        throw e;
    }
}

@parser::members {
    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        throw new RuntimeException(hdr + ":" + msg);
    }
}

@lexer::members {
    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        throw new RuntimeException(hdr + ":" + msg);
    }
}

/*-------------------------------------------------
 * PARSER RULES
 *------------------------------------------------*/
atom
	: PREDICATE^ LPAR! atom_content (C! atom_content)* RPAR!;

literal
	: atom^ | NOT^ atom;

atom_content
	: VARIABLE^
	| CONSTANT^ 
	| function^;

function
	: FUNCTION^ LPAR! atom_content (C! atom_content)* RPAR!;

clause
	: literal^ (OR^ literal)*;

/*-------------------------------------------------
 * LEXER RULES
 *------------------------------------------------*/
CONSTANT:	('a'..'e' | 'i'..'t')(DIGIT)*|(DIGIT)*;

VARIABLE:	'u'..'z'(DIGIT)*;

PREDICATE: 	'A'..'Z'(DIGIT)*;

FUNCTION: 'f'..'h'(DIGIT)*;

fragment DIGIT: '0'..'9';

WS: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+    { $channel = HIDDEN; } ;

