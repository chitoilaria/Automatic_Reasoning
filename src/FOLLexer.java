package theoremprover;

// $ANTLR 3.5 FOL.g 2014-01-27 16:23:51

import org.antlr.runtime.*;

@SuppressWarnings("all")
public class FOLLexer extends Lexer {
	public static final int EOF=-1;
	public static final int C=4;
	public static final int CONSTANT=5;
	public static final int DIGIT=6;
	public static final int FUNCTION=7;
	public static final int LPAR=8;
	public static final int NOT=9;
	public static final int OR=10;
	public static final int PREDICATE=11;
	public static final int RPAR=12;
	public static final int VARIABLE=13;
	public static final int WS=14;

	    @Override
	    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
	        String hdr = getErrorHeader(e);
	        String msg = getErrorMessage(e, tokenNames);
	        throw new RuntimeException(hdr + ":" + msg);
	    }


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public FOLLexer() {} 
	public FOLLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public FOLLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "FOL.g"; }

	// $ANTLR start "C"
	public final void mC() throws RecognitionException {
		try {
			int _type = C;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:16:3: ( ',' )
			// FOL.g:16:5: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "C"

	// $ANTLR start "LPAR"
	public final void mLPAR() throws RecognitionException {
		try {
			int _type = LPAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:17:6: ( '(' )
			// FOL.g:17:8: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LPAR"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:18:5: ( '-' )
			// FOL.g:18:7: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:19:4: ( '|' )
			// FOL.g:19:6: '|'
			{
			match('|'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "RPAR"
	public final void mRPAR() throws RecognitionException {
		try {
			int _type = RPAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:20:6: ( ')' )
			// FOL.g:20:8: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RPAR"

	// $ANTLR start "CONSTANT"
	public final void mCONSTANT() throws RecognitionException {
		try {
			int _type = CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:67:9: ( ( 'a' .. 'e' | 'i' .. 't' ) ( DIGIT )* | ( DIGIT )* )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( ((LA3_0 >= 'a' && LA3_0 <= 'e')||(LA3_0 >= 'i' && LA3_0 <= 't')) ) {
				alt3=1;
			}

			else {
				alt3=2;
			}

			switch (alt3) {
				case 1 :
					// FOL.g:67:11: ( 'a' .. 'e' | 'i' .. 't' ) ( DIGIT )*
					{
					if ( (input.LA(1) >= 'a' && input.LA(1) <= 'e')||(input.LA(1) >= 'i' && input.LA(1) <= 't') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// FOL.g:67:32: ( DIGIT )*
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// FOL.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop1;
						}
					}

					}
					break;
				case 2 :
					// FOL.g:67:41: ( DIGIT )*
					{
					// FOL.g:67:41: ( DIGIT )*
					loop2:
					while (true) {
						int alt2=2;
						int LA2_0 = input.LA(1);
						if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
							alt2=1;
						}

						switch (alt2) {
						case 1 :
							// FOL.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop2;
						}
					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONSTANT"

	// $ANTLR start "VARIABLE"
	public final void mVARIABLE() throws RecognitionException {
		try {
			int _type = VARIABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:69:9: ( 'u' .. 'z' ( DIGIT )* )
			// FOL.g:69:11: 'u' .. 'z' ( DIGIT )*
			{
			matchRange('u','z'); 
			// FOL.g:69:19: ( DIGIT )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// FOL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop4;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VARIABLE"

	// $ANTLR start "PREDICATE"
	public final void mPREDICATE() throws RecognitionException {
		try {
			int _type = PREDICATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:71:10: ( 'A' .. 'Z' ( DIGIT )* )
			// FOL.g:71:13: 'A' .. 'Z' ( DIGIT )*
			{
			matchRange('A','Z'); 
			// FOL.g:71:21: ( DIGIT )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// FOL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop5;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PREDICATE"

	// $ANTLR start "FUNCTION"
	public final void mFUNCTION() throws RecognitionException {
		try {
			int _type = FUNCTION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:73:9: ( 'f' .. 'h' ( DIGIT )* )
			// FOL.g:73:11: 'f' .. 'h' ( DIGIT )*
			{
			matchRange('f','h'); 
			// FOL.g:73:19: ( DIGIT )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// FOL.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop6;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FUNCTION"

	// $ANTLR start "DIGIT"
	public final void mDIGIT() throws RecognitionException {
		try {
			// FOL.g:75:15: ( '0' .. '9' )
			// FOL.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIGIT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// FOL.g:77:3: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
			// FOL.g:77:5: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
			{
			// FOL.g:77:5: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
			int cnt7=0;
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( ((LA7_0 >= '\t' && LA7_0 <= '\n')||(LA7_0 >= '\f' && LA7_0 <= '\r')||LA7_0==' ') ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// FOL.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt7 >= 1 ) break loop7;
					EarlyExitException eee = new EarlyExitException(7, input);
					throw eee;
				}
				cnt7++;
			}

			 _channel = HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// FOL.g:1:8: ( C | LPAR | NOT | OR | RPAR | CONSTANT | VARIABLE | PREDICATE | FUNCTION | WS )
		int alt8=10;
		switch ( input.LA(1) ) {
		case ',':
			{
			alt8=1;
			}
			break;
		case '(':
			{
			alt8=2;
			}
			break;
		case '-':
			{
			alt8=3;
			}
			break;
		case '|':
			{
			alt8=4;
			}
			break;
		case ')':
			{
			alt8=5;
			}
			break;
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			{
			alt8=7;
			}
			break;
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
			{
			alt8=8;
			}
			break;
		case 'f':
		case 'g':
		case 'h':
			{
			alt8=9;
			}
			break;
		case '\t':
		case '\n':
		case '\f':
		case '\r':
		case ' ':
			{
			alt8=10;
			}
			break;
		default:
			alt8=6;
		}
		switch (alt8) {
			case 1 :
				// FOL.g:1:10: C
				{
				mC(); 

				}
				break;
			case 2 :
				// FOL.g:1:12: LPAR
				{
				mLPAR(); 

				}
				break;
			case 3 :
				// FOL.g:1:17: NOT
				{
				mNOT(); 

				}
				break;
			case 4 :
				// FOL.g:1:21: OR
				{
				mOR(); 

				}
				break;
			case 5 :
				// FOL.g:1:24: RPAR
				{
				mRPAR(); 

				}
				break;
			case 6 :
				// FOL.g:1:29: CONSTANT
				{
				mCONSTANT(); 

				}
				break;
			case 7 :
				// FOL.g:1:38: VARIABLE
				{
				mVARIABLE(); 

				}
				break;
			case 8 :
				// FOL.g:1:47: PREDICATE
				{
				mPREDICATE(); 

				}
				break;
			case 9 :
				// FOL.g:1:57: FUNCTION
				{
				mFUNCTION(); 

				}
				break;
			case 10 :
				// FOL.g:1:66: WS
				{
				mWS(); 

				}
				break;

		}
	}



}
