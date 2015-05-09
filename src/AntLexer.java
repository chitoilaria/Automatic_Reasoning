package theoremprover;

// $ANTLR 3.5 Ant.g 2014-01-27 16:29:24

import org.antlr.runtime.*;

@SuppressWarnings("all")
public class AntLexer extends Lexer {
	public static final int EOF=-1;
	public static final int CONSTANT=4;
	public static final int DIGIT=5;
	public static final int FUNCTION=6;
	public static final int GT=7;
	public static final int PREDICATE=8;
	public static final int VARIABLE=9;
	public static final int WS=10;

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

	public AntLexer() {} 
	public AntLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public AntLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "Ant.g"; }

	// $ANTLR start "GT"
	public final void mGT() throws RecognitionException {
		try {
			int _type = GT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Ant.g:16:4: ( '>' )
			// Ant.g:16:6: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GT"

	// $ANTLR start "CONSTANT"
	public final void mCONSTANT() throws RecognitionException {
		try {
			int _type = CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Ant.g:57:9: ( ( 'a' .. 'e' | 'i' .. 't' ) ( DIGIT )* | ( DIGIT )* )
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
					// Ant.g:57:11: ( 'a' .. 'e' | 'i' .. 't' ) ( DIGIT )*
					{
					if ( (input.LA(1) >= 'a' && input.LA(1) <= 'e')||(input.LA(1) >= 'i' && input.LA(1) <= 't') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// Ant.g:57:32: ( DIGIT )*
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// Ant.g:
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
					// Ant.g:57:41: ( DIGIT )*
					{
					// Ant.g:57:41: ( DIGIT )*
					loop2:
					while (true) {
						int alt2=2;
						int LA2_0 = input.LA(1);
						if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
							alt2=1;
						}

						switch (alt2) {
						case 1 :
							// Ant.g:
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
			// Ant.g:59:9: ( 'u' .. 'z' ( DIGIT )* )
			// Ant.g:59:11: 'u' .. 'z' ( DIGIT )*
			{
			matchRange('u','z'); 
			// Ant.g:59:19: ( DIGIT )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// Ant.g:
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
			// Ant.g:61:10: ( 'A' .. 'Z' ( DIGIT )* )
			// Ant.g:61:13: 'A' .. 'Z' ( DIGIT )*
			{
			matchRange('A','Z'); 
			// Ant.g:61:21: ( DIGIT )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// Ant.g:
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
			// Ant.g:63:9: ( 'f' .. 'h' ( DIGIT )* )
			// Ant.g:63:11: 'f' .. 'h' ( DIGIT )*
			{
			matchRange('f','h'); 
			// Ant.g:63:19: ( DIGIT )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// Ant.g:
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
			// Ant.g:65:15: ( '0' .. '9' )
			// Ant.g:
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
			// Ant.g:67:3: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+ )
			// Ant.g:67:5: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
			{
			// Ant.g:67:5: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )+
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
					// Ant.g:
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
		// Ant.g:1:8: ( GT | CONSTANT | VARIABLE | PREDICATE | FUNCTION | WS )
		int alt8=6;
		switch ( input.LA(1) ) {
		case '>':
			{
			alt8=1;
			}
			break;
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			{
			alt8=3;
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
			alt8=4;
			}
			break;
		case 'f':
		case 'g':
		case 'h':
			{
			alt8=5;
			}
			break;
		case '\t':
		case '\n':
		case '\f':
		case '\r':
		case ' ':
			{
			alt8=6;
			}
			break;
		default:
			alt8=2;
		}
		switch (alt8) {
			case 1 :
				// Ant.g:1:10: GT
				{
				mGT(); 

				}
				break;
			case 2 :
				// Ant.g:1:13: CONSTANT
				{
				mCONSTANT(); 

				}
				break;
			case 3 :
				// Ant.g:1:22: VARIABLE
				{
				mVARIABLE(); 

				}
				break;
			case 4 :
				// Ant.g:1:31: PREDICATE
				{
				mPREDICATE(); 

				}
				break;
			case 5 :
				// Ant.g:1:41: FUNCTION
				{
				mFUNCTION(); 

				}
				break;
			case 6 :
				// Ant.g:1:50: WS
				{
				mWS(); 

				}
				break;

		}
	}



}
