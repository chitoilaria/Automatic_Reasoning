grammar Ant;

options {
language=Java;
output=AST;
ASTLabelType=CommonTree;
rewrite=true;
backtrack=true;
}

tokens {
	GT = '>';
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

order
	: element^ GT^ order
	| element^ GT^ element;

element
	: CONSTANT^
	| VARIABLE^
	| PREDICATE^
	| FUNCTION^;
	
/*-------------------------------------------------
 * LEXER RULES
 *------------------------------------------------*/
CONSTANT:	('a'..'e' | 'i'..'t')(DIGIT)*|(DIGIT)*;

VARIABLE:	'u'..'z'(DIGIT)*;

PREDICATE: 	'A'..'Z'(DIGIT)*;

FUNCTION: 'f'..'h'(DIGIT)*;

fragment DIGIT: '0'..'9';

WS: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+    { $channel = HIDDEN; } ;
