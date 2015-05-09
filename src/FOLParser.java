package theoremprover;

// $ANTLR 3.5 FOL.g 2014-01-27 16:23:51

import org.antlr.runtime.*;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class FOLParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "C", "CONSTANT", "DIGIT", "FUNCTION", 
		"LPAR", "NOT", "OR", "PREDICATE", "RPAR", "VARIABLE", "WS"
	};
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

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public FOLParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public FOLParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return FOLParser.tokenNames; }
	@Override public String getGrammarFileName() { return "FOL.g"; }


	    @Override
	    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
	        String hdr = getErrorHeader(e);
	        String msg = getErrorMessage(e, tokenNames);
	        throw new RuntimeException(hdr + ":" + msg);
	    }


	public static class atom_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "atom"
	// FOL.g:47:1: atom : PREDICATE ^ LPAR ! atom_content ( C ! atom_content )* RPAR !;
	public final FOLParser.atom_return atom() throws RecognitionException {
		FOLParser.atom_return retval = new FOLParser.atom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PREDICATE1=null;
		Token LPAR2=null;
		Token C4=null;
		Token RPAR6=null;
		ParserRuleReturnScope atom_content3 =null;
		ParserRuleReturnScope atom_content5 =null;

		CommonTree PREDICATE1_tree=null;
		CommonTree LPAR2_tree=null;
		CommonTree C4_tree=null;
		CommonTree RPAR6_tree=null;

		try {
			// FOL.g:48:2: ( PREDICATE ^ LPAR ! atom_content ( C ! atom_content )* RPAR !)
			// FOL.g:48:4: PREDICATE ^ LPAR ! atom_content ( C ! atom_content )* RPAR !
			{
			PREDICATE1=(Token)match(input,PREDICATE,FOLLOW_PREDICATE_in_atom112); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			PREDICATE1_tree = (CommonTree)adaptor.create(PREDICATE1);
			root_0 = (CommonTree)adaptor.becomeRoot(PREDICATE1_tree, root_0);
			}

			LPAR2=(Token)match(input,LPAR,FOLLOW_LPAR_in_atom115); if (state.failed) return retval;
			pushFollow(FOLLOW_atom_content_in_atom118);
			atom_content3=atom_content();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, atom_content3.getTree());

			// FOL.g:48:34: ( C ! atom_content )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==C) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// FOL.g:48:35: C ! atom_content
					{
					C4=(Token)match(input,C,FOLLOW_C_in_atom121); if (state.failed) return retval;
					pushFollow(FOLLOW_atom_content_in_atom124);
					atom_content5=atom_content();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atom_content5.getTree());

					}
					break;

				default :
					break loop1;
				}
			}

			RPAR6=(Token)match(input,RPAR,FOLLOW_RPAR_in_atom128); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    // ANTLR does not generate its normal rule try/catch
		    catch(RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"


	public static class literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "literal"
	// FOL.g:50:1: literal : ( atom ^| NOT ^ atom );
	public final FOLParser.literal_return literal() throws RecognitionException {
		FOLParser.literal_return retval = new FOLParser.literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token NOT8=null;
		ParserRuleReturnScope atom7 =null;
		ParserRuleReturnScope atom9 =null;

		CommonTree NOT8_tree=null;

		try {
			// FOL.g:51:2: ( atom ^| NOT ^ atom )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==PREDICATE) ) {
				alt2=1;
			}
			else if ( (LA2_0==NOT) ) {
				alt2=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// FOL.g:51:4: atom ^
					{
					pushFollow(FOLLOW_atom_in_literal138);
					atom7=atom();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(atom7.getTree(), root_0);
					}
					break;
				case 2 :
					// FOL.g:51:12: NOT ^ atom
					{
					NOT8=(Token)match(input,NOT,FOLLOW_NOT_in_literal143); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NOT8_tree = (CommonTree)adaptor.create(NOT8);
					root_0 = (CommonTree)adaptor.becomeRoot(NOT8_tree, root_0);
					}

					pushFollow(FOLLOW_atom_in_literal146);
					atom9=atom();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atom9.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    // ANTLR does not generate its normal rule try/catch
		    catch(RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "literal"


	public static class atom_content_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "atom_content"
	// FOL.g:53:1: atom_content : ( VARIABLE ^| CONSTANT ^| function ^);
	public final FOLParser.atom_content_return atom_content() throws RecognitionException {
		FOLParser.atom_content_return retval = new FOLParser.atom_content_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token VARIABLE10=null;
		Token CONSTANT11=null;
		ParserRuleReturnScope function12 =null;

		CommonTree VARIABLE10_tree=null;
		CommonTree CONSTANT11_tree=null;

		try {
			// FOL.g:54:2: ( VARIABLE ^| CONSTANT ^| function ^)
			int alt3=3;
			switch ( input.LA(1) ) {
			case VARIABLE:
				{
				alt3=1;
				}
				break;
			case CONSTANT:
				{
				alt3=2;
				}
				break;
			case FUNCTION:
				{
				alt3=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}
			switch (alt3) {
				case 1 :
					// FOL.g:54:4: VARIABLE ^
					{
					VARIABLE10=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_atom_content155); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					VARIABLE10_tree = (CommonTree)adaptor.create(VARIABLE10);
					root_0 = (CommonTree)adaptor.becomeRoot(VARIABLE10_tree, root_0);
					}

					}
					break;
				case 2 :
					// FOL.g:55:4: CONSTANT ^
					{
					CONSTANT11=(Token)match(input,CONSTANT,FOLLOW_CONSTANT_in_atom_content161); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CONSTANT11_tree = (CommonTree)adaptor.create(CONSTANT11);
					root_0 = (CommonTree)adaptor.becomeRoot(CONSTANT11_tree, root_0);
					}

					}
					break;
				case 3 :
					// FOL.g:56:4: function ^
					{
					pushFollow(FOLLOW_function_in_atom_content168);
					function12=function();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(function12.getTree(), root_0);
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    // ANTLR does not generate its normal rule try/catch
		    catch(RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom_content"


	public static class function_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "function"
	// FOL.g:58:1: function : FUNCTION ^ LPAR ! atom_content ( C ! atom_content )* RPAR !;
	public final FOLParser.function_return function() throws RecognitionException {
		FOLParser.function_return retval = new FOLParser.function_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token FUNCTION13=null;
		Token LPAR14=null;
		Token C16=null;
		Token RPAR18=null;
		ParserRuleReturnScope atom_content15 =null;
		ParserRuleReturnScope atom_content17 =null;

		CommonTree FUNCTION13_tree=null;
		CommonTree LPAR14_tree=null;
		CommonTree C16_tree=null;
		CommonTree RPAR18_tree=null;

		try {
			// FOL.g:59:2: ( FUNCTION ^ LPAR ! atom_content ( C ! atom_content )* RPAR !)
			// FOL.g:59:4: FUNCTION ^ LPAR ! atom_content ( C ! atom_content )* RPAR !
			{
			FUNCTION13=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_function178); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			FUNCTION13_tree = (CommonTree)adaptor.create(FUNCTION13);
			root_0 = (CommonTree)adaptor.becomeRoot(FUNCTION13_tree, root_0);
			}

			LPAR14=(Token)match(input,LPAR,FOLLOW_LPAR_in_function181); if (state.failed) return retval;
			pushFollow(FOLLOW_atom_content_in_function184);
			atom_content15=atom_content();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, atom_content15.getTree());

			// FOL.g:59:33: ( C ! atom_content )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==C) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// FOL.g:59:34: C ! atom_content
					{
					C16=(Token)match(input,C,FOLLOW_C_in_function187); if (state.failed) return retval;
					pushFollow(FOLLOW_atom_content_in_function190);
					atom_content17=atom_content();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atom_content17.getTree());

					}
					break;

				default :
					break loop4;
				}
			}

			RPAR18=(Token)match(input,RPAR,FOLLOW_RPAR_in_function194); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    // ANTLR does not generate its normal rule try/catch
		    catch(RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "function"


	public static class clause_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "clause"
	// FOL.g:61:1: clause : literal ^ ( OR ^ literal )* ;
	public final FOLParser.clause_return clause() throws RecognitionException {
		FOLParser.clause_return retval = new FOLParser.clause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OR20=null;
		ParserRuleReturnScope literal19 =null;
		ParserRuleReturnScope literal21 =null;

		CommonTree OR20_tree=null;

		try {
			// FOL.g:62:2: ( literal ^ ( OR ^ literal )* )
			// FOL.g:62:4: literal ^ ( OR ^ literal )*
			{
			pushFollow(FOLLOW_literal_in_clause204);
			literal19=literal();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(literal19.getTree(), root_0);
			// FOL.g:62:13: ( OR ^ literal )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==OR) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// FOL.g:62:14: OR ^ literal
					{
					OR20=(Token)match(input,OR,FOLLOW_OR_in_clause208); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					OR20_tree = (CommonTree)adaptor.create(OR20);
					root_0 = (CommonTree)adaptor.becomeRoot(OR20_tree, root_0);
					}

					pushFollow(FOLLOW_literal_in_clause211);
					literal21=literal();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, literal21.getTree());

					}
					break;

				default :
					break loop5;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    // ANTLR does not generate its normal rule try/catch
		    catch(RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "clause"

	// Delegated rules



	public static final BitSet FOLLOW_PREDICATE_in_atom112 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_LPAR_in_atom115 = new BitSet(new long[]{0x00000000000020A0L});
	public static final BitSet FOLLOW_atom_content_in_atom118 = new BitSet(new long[]{0x0000000000001010L});
	public static final BitSet FOLLOW_C_in_atom121 = new BitSet(new long[]{0x00000000000020A0L});
	public static final BitSet FOLLOW_atom_content_in_atom124 = new BitSet(new long[]{0x0000000000001010L});
	public static final BitSet FOLLOW_RPAR_in_atom128 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atom_in_literal138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_literal143 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_atom_in_literal146 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLE_in_atom_content155 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CONSTANT_in_atom_content161 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_in_atom_content168 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FUNCTION_in_function178 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_LPAR_in_function181 = new BitSet(new long[]{0x00000000000020A0L});
	public static final BitSet FOLLOW_atom_content_in_function184 = new BitSet(new long[]{0x0000000000001010L});
	public static final BitSet FOLLOW_C_in_function187 = new BitSet(new long[]{0x00000000000020A0L});
	public static final BitSet FOLLOW_atom_content_in_function190 = new BitSet(new long[]{0x0000000000001010L});
	public static final BitSet FOLLOW_RPAR_in_function194 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_literal_in_clause204 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_OR_in_clause208 = new BitSet(new long[]{0x0000000000000A00L});
	public static final BitSet FOLLOW_literal_in_clause211 = new BitSet(new long[]{0x0000000000000402L});
}
