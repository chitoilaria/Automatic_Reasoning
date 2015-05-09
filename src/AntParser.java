package theoremprover;

// $ANTLR 3.5 Ant.g 2014-01-27 16:29:23

import org.antlr.runtime.*;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class AntParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "CONSTANT", "DIGIT", "FUNCTION", 
		"GT", "PREDICATE", "VARIABLE", "WS"
	};
	public static final int EOF=-1;
	public static final int CONSTANT=4;
	public static final int DIGIT=5;
	public static final int FUNCTION=6;
	public static final int GT=7;
	public static final int PREDICATE=8;
	public static final int VARIABLE=9;
	public static final int WS=10;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public AntParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public AntParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return AntParser.tokenNames; }
	@Override public String getGrammarFileName() { return "Ant.g"; }


	    @Override
	    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
	        String hdr = getErrorHeader(e);
	        String msg = getErrorMessage(e, tokenNames);
	        throw new RuntimeException(hdr + ":" + msg);
	    }


	public static class order_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "order"
	// Ant.g:44:1: order : ( element ^ GT ^ order | element ^ GT ^ element );
	public final AntParser.order_return order() throws RecognitionException {
		AntParser.order_return retval = new AntParser.order_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token GT2=null;
		Token GT5=null;
		ParserRuleReturnScope element1 =null;
		ParserRuleReturnScope order3 =null;
		ParserRuleReturnScope element4 =null;
		ParserRuleReturnScope element6 =null;

		CommonTree GT2_tree=null;
		CommonTree GT5_tree=null;

		try {
			// Ant.g:45:2: ( element ^ GT ^ order | element ^ GT ^ element )
			int alt1=2;
			switch ( input.LA(1) ) {
			case CONSTANT:
				{
				int LA1_1 = input.LA(2);
				if ( (LA1_1==GT) ) {
					switch ( input.LA(3) ) {
					case CONSTANT:
						{
						int LA1_6 = input.LA(4);
						if ( (LA1_6==GT) ) {
							alt1=1;
						}
						else if ( (LA1_6==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case VARIABLE:
						{
						int LA1_7 = input.LA(4);
						if ( (LA1_7==GT) ) {
							alt1=1;
						}
						else if ( (LA1_7==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 7, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case PREDICATE:
						{
						int LA1_8 = input.LA(4);
						if ( (LA1_8==GT) ) {
							alt1=1;
						}
						else if ( (LA1_8==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 8, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case FUNCTION:
						{
						int LA1_9 = input.LA(4);
						if ( (LA1_9==GT) ) {
							alt1=1;
						}
						else if ( (LA1_9==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 9, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case VARIABLE:
				{
				int LA1_2 = input.LA(2);
				if ( (LA1_2==GT) ) {
					switch ( input.LA(3) ) {
					case CONSTANT:
						{
						int LA1_6 = input.LA(4);
						if ( (LA1_6==GT) ) {
							alt1=1;
						}
						else if ( (LA1_6==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case VARIABLE:
						{
						int LA1_7 = input.LA(4);
						if ( (LA1_7==GT) ) {
							alt1=1;
						}
						else if ( (LA1_7==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 7, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case PREDICATE:
						{
						int LA1_8 = input.LA(4);
						if ( (LA1_8==GT) ) {
							alt1=1;
						}
						else if ( (LA1_8==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 8, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case FUNCTION:
						{
						int LA1_9 = input.LA(4);
						if ( (LA1_9==GT) ) {
							alt1=1;
						}
						else if ( (LA1_9==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 9, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case PREDICATE:
				{
				int LA1_3 = input.LA(2);
				if ( (LA1_3==GT) ) {
					switch ( input.LA(3) ) {
					case CONSTANT:
						{
						int LA1_6 = input.LA(4);
						if ( (LA1_6==GT) ) {
							alt1=1;
						}
						else if ( (LA1_6==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case VARIABLE:
						{
						int LA1_7 = input.LA(4);
						if ( (LA1_7==GT) ) {
							alt1=1;
						}
						else if ( (LA1_7==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 7, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case PREDICATE:
						{
						int LA1_8 = input.LA(4);
						if ( (LA1_8==GT) ) {
							alt1=1;
						}
						else if ( (LA1_8==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 8, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case FUNCTION:
						{
						int LA1_9 = input.LA(4);
						if ( (LA1_9==GT) ) {
							alt1=1;
						}
						else if ( (LA1_9==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 9, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FUNCTION:
				{
				int LA1_4 = input.LA(2);
				if ( (LA1_4==GT) ) {
					switch ( input.LA(3) ) {
					case CONSTANT:
						{
						int LA1_6 = input.LA(4);
						if ( (LA1_6==GT) ) {
							alt1=1;
						}
						else if ( (LA1_6==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case VARIABLE:
						{
						int LA1_7 = input.LA(4);
						if ( (LA1_7==GT) ) {
							alt1=1;
						}
						else if ( (LA1_7==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 7, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case PREDICATE:
						{
						int LA1_8 = input.LA(4);
						if ( (LA1_8==GT) ) {
							alt1=1;
						}
						else if ( (LA1_8==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 8, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case FUNCTION:
						{
						int LA1_9 = input.LA(4);
						if ( (LA1_9==GT) ) {
							alt1=1;
						}
						else if ( (LA1_9==EOF) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 9, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// Ant.g:45:4: element ^ GT ^ order
					{
					pushFollow(FOLLOW_element_in_order81);
					element1=element();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(element1.getTree(), root_0);
					GT2=(Token)match(input,GT,FOLLOW_GT_in_order84); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GT2_tree = (CommonTree)adaptor.create(GT2);
					root_0 = (CommonTree)adaptor.becomeRoot(GT2_tree, root_0);
					}

					pushFollow(FOLLOW_order_in_order87);
					order3=order();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, order3.getTree());

					}
					break;
				case 2 :
					// Ant.g:46:4: element ^ GT ^ element
					{
					pushFollow(FOLLOW_element_in_order92);
					element4=element();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(element4.getTree(), root_0);
					GT5=(Token)match(input,GT,FOLLOW_GT_in_order95); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GT5_tree = (CommonTree)adaptor.create(GT5);
					root_0 = (CommonTree)adaptor.becomeRoot(GT5_tree, root_0);
					}

					pushFollow(FOLLOW_element_in_order98);
					element6=element();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, element6.getTree());

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
	// $ANTLR end "order"


	public static class element_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "element"
	// Ant.g:48:1: element : ( CONSTANT ^| VARIABLE ^| PREDICATE ^| FUNCTION ^);
	public final AntParser.element_return element() throws RecognitionException {
		AntParser.element_return retval = new AntParser.element_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CONSTANT7=null;
		Token VARIABLE8=null;
		Token PREDICATE9=null;
		Token FUNCTION10=null;

		CommonTree CONSTANT7_tree=null;
		CommonTree VARIABLE8_tree=null;
		CommonTree PREDICATE9_tree=null;
		CommonTree FUNCTION10_tree=null;

		try {
			// Ant.g:49:2: ( CONSTANT ^| VARIABLE ^| PREDICATE ^| FUNCTION ^)
			int alt2=4;
			switch ( input.LA(1) ) {
			case CONSTANT:
				{
				alt2=1;
				}
				break;
			case VARIABLE:
				{
				alt2=2;
				}
				break;
			case PREDICATE:
				{
				alt2=3;
				}
				break;
			case FUNCTION:
				{
				alt2=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// Ant.g:49:4: CONSTANT ^
					{
					CONSTANT7=(Token)match(input,CONSTANT,FOLLOW_CONSTANT_in_element107); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CONSTANT7_tree = (CommonTree)adaptor.create(CONSTANT7);
					root_0 = (CommonTree)adaptor.becomeRoot(CONSTANT7_tree, root_0);
					}

					}
					break;
				case 2 :
					// Ant.g:50:4: VARIABLE ^
					{
					VARIABLE8=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_element113); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					VARIABLE8_tree = (CommonTree)adaptor.create(VARIABLE8);
					root_0 = (CommonTree)adaptor.becomeRoot(VARIABLE8_tree, root_0);
					}

					}
					break;
				case 3 :
					// Ant.g:51:4: PREDICATE ^
					{
					PREDICATE9=(Token)match(input,PREDICATE,FOLLOW_PREDICATE_in_element119); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PREDICATE9_tree = (CommonTree)adaptor.create(PREDICATE9);
					root_0 = (CommonTree)adaptor.becomeRoot(PREDICATE9_tree, root_0);
					}

					}
					break;
				case 4 :
					// Ant.g:52:4: FUNCTION ^
					{
					FUNCTION10=(Token)match(input,FUNCTION,FOLLOW_FUNCTION_in_element125); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FUNCTION10_tree = (CommonTree)adaptor.create(FUNCTION10);
					root_0 = (CommonTree)adaptor.becomeRoot(FUNCTION10_tree, root_0);
					}

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
	// $ANTLR end "element"

	// Delegated rules



	public static final BitSet FOLLOW_element_in_order81 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_GT_in_order84 = new BitSet(new long[]{0x0000000000000350L});
	public static final BitSet FOLLOW_order_in_order87 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_element_in_order92 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_GT_in_order95 = new BitSet(new long[]{0x0000000000000350L});
	public static final BitSet FOLLOW_element_in_order98 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CONSTANT_in_element107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLE_in_element113 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PREDICATE_in_element119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FUNCTION_in_element125 = new BitSet(new long[]{0x0000000000000002L});
}
