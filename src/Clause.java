package theoremprover;

import java.util.ArrayList;
import java.util.HashSet;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

/**
 *
 * @author Diego Sempreboni.
 *
 * Esame: Ragionamento Automatico.
 *
 * Progetto: Implementazione di un dimostratore di teoremi per la risoluzione
 * ordinata.
 *
 * VR370412.
 */
public class Clause {

    private final ArrayList<Literal> literals;
    private final int arity;
    private final int id_Clause;
    private final ArrayList<StepProof> history;
    private int weight = 0;

    /**
     * Constructor of Clause.
     *
     * @param stringa The first line of file.
     * @param id_Clause The current number of new clause.
     * @throws RecognitionException
     */
    public Clause(String stringa, int id_Clause) throws RecognitionException {
        ANTLRStringStream input = new ANTLRStringStream(stringa);
        FOLLexer simpleLexer = new FOLLexer(input);
        CommonTokenStream token = new CommonTokenStream(simpleLexer);
        FOLParser parser = new FOLParser(token);
        CommonTree tree = (CommonTree) parser.clause().getTree();
        this.literals = new ArrayList<>();
        this.history = new ArrayList<>();
        generateClause(tree);
        this.arity = literals.size();
        this.id_Clause = id_Clause;
        calculateWeight();
    }

    /**
     * Constructor of Clause.
     *
     * @param array An ArrayList of literals.
     * @param id_Clause The current number of new clause.
     */
    public Clause(ArrayList<Literal> array, int id_Clause) {
        this.id_Clause = id_Clause;
        this.literals = new ArrayList<>();
        this.history = new ArrayList<>();
        this.literals.addAll(array);
        this.arity = literals.size();
        calculateWeight();
    }

    /**
     * Method for generate the clause from a tree.
     *
     * @param tree The tree of clause.
     */
    private void generateClause(CommonTree tree) {

        switch (tree.getType()) {
            case FOLParser.NOT:
            case FOLParser.PREDICATE:
                Literal l = new Literal(tree);
                literals.add(l);
                break;
            case FOLParser.OR:
                generateClause((CommonTree) tree.getChild(0));
                generateClause((CommonTree) tree.getChild(1));
                break;
        }
    }

    /**
     * Method to get the arity of clause.
     *
     * @return An integer, the arity.
     */
    public int getArity() {
        return arity;
    }

    /**
     * Method to get the id of the clause.
     *
     * @return An integer, the id of clause.
     */
    public int getId_Clause() {
        return id_Clause;
    }

    /**
     * Method to return the literals of clause.
     *
     * @return An ArrayList of literals.
     */
    public ArrayList<Literal> getLiterals() {
        return literals;
    }

    /**
     * Method toString.
     *
     * @return A string with the clause.
     */
    @Override
    public String toString() {
        String ap = "";
        for (Literal a : literals) {
            ap = ap + a.toString() + "|";
        }
        if (ap.equals("")) {
            return "□";
        }
        return ap.substring(0, ap.length() - 1);
    }

    /**
     * Method to clone the literals into clause.
     *
     * @return A new ArrayList with a clone of original literals.
     */
    private ArrayList<Literal> cloneLiteral() {
        ArrayList<Literal> newArray = new ArrayList<>();
        for (Literal l : this.literals) {
            newArray.add(l.clone());
        }
        return newArray;
    }

    /**
     * Method for the calculus of the Weight of the clause.
     */
    public void calculateWeight() {
        this.weight = 0;
        for (Literal l : literals) {
            this.weight = this.weight + l.getWeight();
        }
    }

    /**
     * Method to set a weight for the clause.
     *
     * @param x The new weight
     */
    public void setClauseWeight(int x) {
        this.weight = x;
    }

    /**
     * Method toString for all the couples into the clause.
     *
     * @param array An ArrayList of couples.
     * @return A String with the couples.
     */
    private static String toStringCouples(ArrayList<Unifier> array) {
        if (!array.isEmpty()) {
            String s = "{ ";
            for (Unifier c : array) {
                s = s + c.toString();
            }
            if (!array.isEmpty()) {
                s = s.substring(0, s.length() - 2);
            }
            return s + " }";
        }else
            return "";
    }

    /**
     * Method that implements the Resolution.
     *
     * @param a The first clause.
     * @param b The second clause.
     * @param type The type of ordering.
     * @return The clause resolved.
     * @throws ClauseOutOfBoundsException
     */
    static Clause resolution(Clause a, Clause b, String type) throws ClauseOutOfBoundsException {
        ArrayList<Literal> literalA = a.cloneLiteral();
        ArrayList<Literal> literalB = b.cloneLiteral();
        ArrayList<Unifier> unifier;

        ArrayList<String> variableA = a.getVariables();
        variableA.retainAll(b.getVariables());

        for (String s : variableA) {
            for (Literal lc : literalA) {
                for (Argument currentArgument : lc.getSubArgs()) {
                    Argument oldName = new Argument(s, false, false, true, 0, null);
                    int number = newVariableInteger(a, s);
                    if (!TheoremProver.weights.isEmpty()) {
                        boolean find = false;
                        int weigholds = searchWeight(s);
                        int weighnews = searchWeight(s + number);
                        if (weighnews == -1 && weigholds != -1) {
                            TheoremProver.weights.add(new Burden((s + number), weigholds));
                        }
                    }
                    Argument newName = new Argument(s + newVariableInteger(a, s), false, false, true, 0, null);
                    lc.getSubArgs().set(lc.getSubArgs().indexOf(currentArgument), sostitution(currentArgument, oldName, newName));
                }
            }
        }
        for (Literal a1 : literalA) {
            for (Literal b1 : literalB) {
                if (a1.getName().equals(b1.getName()) && (a1.isNot() != b1.isNot())) {
                    unifier = unification(a1, b1);
                    if (unifier != null) {
                        if (!TheoremProver.sortingRules.isEmpty()) {
                            Literal a1x = a1.clone();
                            Literal b1x = b1.clone();
                            ArrayList<Literal> literalAx = cloneLiteral(literalA);
                            ArrayList<Literal> literalBx = cloneLiteral(literalB);
                            literalAx.remove(a1x);
                            literalBx.remove(b1x);
                            a1x = sostitution(a1x, unifier);
                            b1x = sostitution(b1x, unifier);

                            for (int i = 0; i < literalAx.size(); i++) {
                                Literal tmp = literalAx.get(i);
                                literalAx.remove(tmp);
                                literalAx.add(i, sostitution(tmp, unifier));
                            }

                            for (int i = 0; i < literalBx.size(); i++) {
                                Literal tmp = literalBx.get(i);
                                literalBx.remove(tmp);
                                literalBx.add(i, sostitution(tmp, unifier));
                            }

                            if (a1x.isMax(literalAx, type) && b1x.isMax(literalBx, type)) {
                                literalA.remove(a1);
                                literalA.addAll(literalB);
                                literalA.remove(b1);
                                Clause newClause = new Clause(literalA, TheoremProver.countClause++);
                                Utilities.checkLimitClauses();
                                newClause = sostitution(newClause, unifier);
                                newClause.history.add(new StepProof("Resolution", newClause.getId_Clause(), a.getId_Clause(), b.getId_Clause(), toStringCouples(unifier)));
                                return newClause;
                            } else {
                                continue;
                            }

                        } else {
                            literalA.remove(a1);
                            literalA.addAll(literalB);
                            literalA.remove(b1);
                            Clause newClause = new Clause(literalA, TheoremProver.countClause++);
                            Utilities.checkLimitClauses();
                            newClause = sostitution(newClause, unifier);
                            newClause.history.add(new StepProof("Resolution", newClause.getId_Clause(), a.getId_Clause(), b.getId_Clause(), toStringCouples(unifier)));
                            return newClause;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    private static ArrayList<Literal> cloneLiteral(ArrayList<Literal> array) {
        ArrayList<Literal> wen = new ArrayList<>();
        for (Literal x : array) {
            wen.add(x.clone());
        }
        return wen;
    }

    /**
     * Method that implements the Resolution for the Simplification.
     *
     * @param a The first clause.
     * @param b The second clause.
     * @param type The type of ordering.
     * @return The clause resolved.
     * @throws ClauseOutOfBoundsException
     */
    private static Clause resolutionForSimplification(Clause a, Clause b) throws ClauseOutOfBoundsException {
        ArrayList<Literal> literalA = a.cloneLiteral();
        ArrayList<Literal> literalB = b.cloneLiteral();
        ArrayList<Unifier> unifier;

        ArrayList<String> variableA = a.getVariables();
        variableA.retainAll(b.getVariables());

        for (String s : variableA) {
            for (Literal lc : literalA) {
                for (Argument currentArgument : lc.getSubArgs()) {
                    Argument oldName = new Argument(s, false, false, true, 0, null);
                    int number = newVariableInteger(a, s);
                    if (!TheoremProver.weights.isEmpty()) {
                        boolean find = false;
                        int weigholds = searchWeight(s);
                        int weighnews = searchWeight(s + number);
                        if (weighnews == -1 && weigholds != -1) {
                            TheoremProver.weights.add(new Burden((s + number), weigholds));
                        }
                    }
                    Argument newName = new Argument(s + newVariableInteger(a, s), false, false, true, 0, null);
                    lc.getSubArgs().set(lc.getSubArgs().indexOf(currentArgument), sostitution(currentArgument, oldName, newName));
                }
            }
        }

        for (Literal a1 : literalA) {
            for (Literal b1 : literalB) {
                if (a1.getName().equals(b1.getName()) && (a1.isNot() != b1.isNot())) {
                    unifier = unification(a1, b1);
                    if (unifier != null) {
                        if (sostitution(a1, unifier).equalsLit(b1)) {
                            literalA.remove(a1);
                            literalA.addAll(literalB);
                            literalA.remove(b1);
                            Clause newClause = new Clause(literalA, TheoremProver.countClause++);
                            Utilities.checkLimitClauses();
                            return newClause;
                        } else {
                            return null;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that implements the Unification.
     *
     * @param a The first literal.
     * @param b The second literal.
     * @return The unifiers.
     */
    static ArrayList<Unifier> unification(Literal a, Literal b) {

        ArrayList<Unifier> array = new ArrayList<>();

        if (a.getName().equals(b.getName())) {
            for (int i = 0; i < b.getSubArgs().size(); i++) {
                Unifier c = new Unifier(a.getSubArgs().get(i), b.getSubArgs().get(i));
                array.add(c);
            }

            while (true) {
                normalize(array);
                if (!conflict(array)) {
                    array = null;
                    break;
                }
                if (cancellation(array)) {
                    continue;
                }
                if (controlocc(array)) {
                    array = null;
                    break;
                }
                if (decomposition(array)) {
                    continue;
                }
                if (elimination(array)) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return array;
    }

    /**
     * Method that implements the Factorization.
     *
     * @param c The clause to factorize.
     * @param type The type of ordering.
     * @return The clause factored.
     * @throws ClauseOutOfBoundsException
     */
    static Clause factorization(Clause c, String type) throws ClauseOutOfBoundsException {
        ArrayList<Unifier> unifier;
        for (int i = 0; i < c.getArity(); i++) {
            for (int j = i + 1; j < c.getArity(); j++) {
                Literal tmp1 = c.getLiterals().get(i);
                Literal tmp2 = c.getLiterals().get(j);
                if (tmp1.getName().equals(tmp2.getName()) && tmp1.isNot() == tmp2.isNot()) {
                    unifier = unification(tmp1, tmp2);
                    if (unifier != null) {
                        if (!TheoremProver.sortingRules.isEmpty()) {
                            Literal a1x = tmp1.clone();
                            ArrayList<Literal> literalAx = c.cloneLiteral();
                            literalAx.remove(a1x);
                            a1x = sostitution(a1x, unifier);

                            for (int w = 0; w < literalAx.size(); w++) {
                                Literal tmp = literalAx.get(w);
                                literalAx.remove(tmp);
                                literalAx.add(w, sostitution(tmp, unifier));
                            }

                            if (a1x.isMax(literalAx, type)) {
                                ArrayList<Literal> tmp = (ArrayList<Literal>) c.cloneLiteral();
                                tmp.remove(tmp2);
                                Clause newClause = new Clause(tmp, TheoremProver.countClause++);
                                Utilities.checkLimitClauses();
                                newClause = sostitution(newClause, unifier);
                                newClause.history.add(new StepProof("Factorization", newClause.getId_Clause(), c.getId_Clause(), -1, toStringCouples(unifier)));
                                return newClause;

                            } else {
                                return null;
                            }
                        } else {
                            ArrayList<Literal> tmp = (ArrayList<Literal>) c.cloneLiteral();
                            tmp.remove(tmp2);
                            Clause newClause = new Clause(tmp, TheoremProver.countClause++);
                            Utilities.checkLimitClauses();
                            newClause = sostitution(newClause, unifier);
                            newClause.history.add(new StepProof("Factorization", newClause.getId_Clause(), c.getId_Clause(), -1, toStringCouples(unifier)));
                            return newClause;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method that implements the Simplification Of Clauses.
     *
     * @param a The first clause.
     * @param b The second clause.
     * @return The clause simplified.
     * @throws ClauseOutOfBoundsException
     */
    static Clause simplificationOfClauses(Clause a, Clause b) throws ClauseOutOfBoundsException {
        if (a.getArity() == 1) {
            Clause c = resolutionForSimplification(a, b);
            if (c != null) {
                Clause subsunt = subsumption(c, b);
                if (subsunt == null) {
                    TheoremProver.countClause--;
                    return null;
                }
                subsunt.history.add(new StepProof("Simplification_Clauses", subsunt.getId_Clause(), a.getId_Clause(), b.getId_Clause(), ""));
                return subsunt;
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * Method that implements the elimination of Tautologies.
     *
     * @param c Clause to test.
     * @return The Clause for the elimination;
     */
    static Clause eliminationOfTautologies(Clause c) {
        for (int i = 0; i < c.getArity(); i++) {
            Literal l1 = c.getLiterals().get(i);
            for (int j = i + 1; j < c.getArity(); j++) {
                Literal l2 = c.getLiterals().get(j);
                if (l1.equalsLit(l2) && l1.isNot() != l2.isNot()) {
                    c.history.add(new StepProof("Elimination_Tautologies", c.getId_Clause(), -1, -1, ""));
                    TheoremProver.history.add(c);
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * Method that implements the Subsumption.
     *
     * @param c The first clause.
     * @param d The second clause to subsume.
     * @return The clause that subsumes(c).
     */
    static Clause subsumption(Clause c, Clause d) {

        ArrayList<Unifier> tot = new ArrayList<>();
        ArrayList<Literal> newLc = new ArrayList<>();
        ArrayList<Literal> newLd = new ArrayList<>();
        boolean find = false;

        for (Literal ac : c.getLiterals()) {
            for (Literal ad : d.getLiterals()) {

                if (ac.getName().equals(ad.getName()) && ac.isNot() == ad.isNot()) {
                    for (Literal l : newLc) {
                        if (l.equals(ac)) {
                            find = true;
                            break;
                        }
                    }
                    if (!find) {
                        newLc.add(ac.clone());
                    }
                    find = false;
                    for (Literal l : newLd) {
                        if (l.equals(ad)) {
                            find = false;
                            break;
                        }
                    }
                    if (!find) {
                        newLd.add(ad.clone());
                    }
                }
            }
        }

        if (newLc.size() == c.getArity()) {
            for (int i = 0; i < newLc.size(); i++) {
                ArrayList<Unifier> co = unification(newLc.get(i), newLd.get(i));
                if (co != null) {
                    tot.addAll(co);
                }
            }
            for (int i = 0; i < newLc.size(); i++) {
                if (!(sostitution(newLc.get(i), tot).equals(newLd.get(i)))) {
                    return null;
                }
            }
            if (TheoremProver.clauses.contains(d)) {
                d.history.add(new StepProof("Subsumption", d.getId_Clause(), c.getId_Clause(), -1, ""));
                TheoremProver.history.add(d);
                TheoremProver.clauses.remove(d);
                return c;
            } else if (TheoremProver.SOS.contains(d)) {
                d.history.add(new StepProof("Subsumption", d.getId_Clause(), c.getId_Clause(), -1, ""));
                TheoremProver.history.add(d);
                TheoremProver.SOS.remove(d);
                return c;
            } else if (TheoremProver.toContract.contains(d)) {
                d.history.add(new StepProof("Subsumption", d.getId_Clause(), c.getId_Clause(), -1, ""));
                TheoremProver.history.add(d);
                TheoremProver.toContract.remove(d);
                TheoremProver.eliminated = true;
                return c;
            }
        }
        return null;
    }

    /**
     * Method for normalize the Couples array.
     *
     * @param c The ArrayList to normalized.
     */
    private static void normalize(ArrayList<Unifier> c) {
        for (Unifier cc : c) {
            if (!(cc.getA1().isVariable()) && (cc.getA2().isVariable())) {
                Argument tmp = cc.getA1();
                cc.setA1(cc.getA2());
                cc.setA2(tmp);
            }
        }
    }

    /**
     * Method that implements the cancellation of unification.
     *
     * @param c The ArrayList of unifiers.
     * @return if one, or plus couples were removed.
     */
    private static boolean cancellation(ArrayList<Unifier> c) {
        for (Unifier cc : c) {
            if (!(cc.getA1().isFunction()) && !(cc.getA2().isFunction())) {
                if (cc.getA1().getName().equals(cc.getA2().getName())) {
                    c.remove(cc);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that implements the elimination of unification.
     *
     * @param c The ArrayList of unifiers.
     * @return if one unifier were eliminated.
     */
    private static boolean elimination(ArrayList<Unifier> c) {
        for (Unifier cc : c) {
            if (cc.getA2().isConstant() && !(cc.isMark())) {
                Argument var = cc.getA1().clone();
                Argument constant = cc.getA2().clone();
                sostitution(c, var, constant);
                cancellation(c);
                Unifier nec = new Unifier(var, constant);
                nec.mark();
                c.add(nec);
                return true;
            }
        }
        for (Unifier cc : c) {
            if (cc.getA2().isFunction() && !(cc.isMark())) {
                Argument var = cc.getA1().clone();
                Argument function = cc.getA2().clone();
                sostitution(c, var, function);
                Unifier nec = new Unifier(var, function);
                nec.mark();
                c.add(nec);
                return true;
            }
        }
        for (Unifier cc : c) {
            if (cc.getA2().isVariable() && !(cc.isMark())) {
                Argument var = cc.getA1().clone();
                Argument var2 = cc.getA2().clone();
                sostitution(c, var, var2);
                cancellation(c);
                Unifier nec = new Unifier(var, var2);
                nec.mark();
                c.add(nec);
                return true;
            }
        }
        return false;
    }

    /**
     * Method that implements the sostitution of unification.
     *
     * @param c The ArrayList of unifiers.
     * @param var The Argument to be replaced.
     * @param object The new argument.
     */
    private static void sostitution(ArrayList<Unifier> c, Argument var, Argument object) {
        for (Unifier cc : c) {
            if (cc.getA1().haveOccurences(var.getName())) {
                if (!(cc.getA1().isFunction())) {
                    cc.setA1(object.clone());
                    if (cc.getA1().isVariable()) {
                        Argument temp = cc.getA1();
                        cc.setA1(cc.getA2());
                        cc.setA2(temp);
                    }
                    continue;
                } else {
                    for (Argument q : cc.getA1().getSub_Args()) {
                        cc.getA1().getSub_Args().set(cc.getA1().getSub_Args().indexOf(q), sostitution(q, var, object));
                        continue;
                    }
                }
            }
            if (cc.getA2().haveOccurences(var.getName())) {
                if (!(cc.getA2().isFunction())) {
                    cc.setA2(object.clone());
                    continue;
                } else {
                    for (Argument q : cc.getA2().getSub_Args()) {
                        cc.getA2().getSub_Args().set(cc.getA2().getSub_Args().indexOf(q), sostitution(q, var, object));
                        continue;
                    }
                }
            }
        }

    }

    /**
     * Method that implements the sostitution of unification.
     *
     * @param q The argument to be searched.
     * @param var The Argument to be replaced.
     * @param object The new argument.
     */
    private static Argument sostitution(Argument q, Argument var, Argument object) {
        if (q.getName().equals(var.getName())) {
            q = object.clone();
        } else {
            if (q.isFunction()) {
                for (Argument qq : q.getSub_Args()) {
                    q.getSub_Args().set(q.getSub_Args().indexOf(qq), sostitution(qq, var, object));
                }
            }
        }
        q.calculateWeight();
        return q;
    }

    /**
     * Method that implements the sostitution of unification.
     *
     * @param c The clause to be searched.
     * @param arrayCouple The new arguments, in forms of unifiers.
     * @return The clause replaced.
     */
    private static Clause sostitution(Clause c, ArrayList<Unifier> arrayCouple) {
        for (Unifier nc : arrayCouple) {
            for (Literal lc : c.getLiterals()) {
                for (Argument ac : lc.getSubArgs()) {
                    lc.getSubArgs().set(lc.getSubArgs().indexOf(ac), sostitution(ac, nc.getA1(), nc.getA2()));
                }
            }
        }
        c.calculateWeight();
        return c;
    }

    /**
     * Method that implements the sostitution of unification.
     *
     * @param l The literal to be searched.
     * @param arrayCouple The new arguments, in forms of unifiers.
     * @return The literal replaced.
     */
    private static Literal sostitution(Literal l, ArrayList<Unifier> arrayCouple) {
        for (Unifier nc : arrayCouple) {
            for (Argument ac : l.getSubArgs()) {
                l.getSubArgs().set(l.getSubArgs().indexOf(ac), sostitution(ac, nc.getA1(), nc.getA2()));
            }
        }
        return l;
    }

    /**
     * Method that implements the Control of Occurrences.
     *
     * @param c ArrayList of couples.
     * @return True, if the clause have occurences of Argument into c.
     */
    private static boolean controlocc(ArrayList<Unifier> c) {
        for (Unifier cc : c) {
            if (!(cc.getA1().isFunction() && cc.getA2().isFunction())) {
                if (!(cc.getA1().isFunction()) && cc.getA2().haveOccurences(cc.getA1().getName())) {
                    return true;
                }
                if (!(cc.getA2().isFunction()) && cc.getA1().haveOccurences(cc.getA2().getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int newVariableInteger(Clause a, String s) {
        int n = 1;
        while (true) {
            if (!a.getVariables().contains(s + n)) {
                return n;
            }
            n++;
        }
    }

    /**
     * Method that implements the Decomposition of unification.
     *
     * @param c ArrayList of couples.
     * @return True, if the unifier is decomposed, false otherwise.
     */
    private static boolean decomposition(ArrayList<Unifier> c) {
        for (Unifier cc : c) {
            if (cc.getA1().isFunction() && cc.getA2().isFunction() && cc.getA1().getName().equals(cc.getA2().getName())) {
                c.remove(cc);
                ArrayList<Argument> aa1 = cc.getA1().getSub_Args();
                ArrayList<Argument> aa2 = cc.getA2().getSub_Args();
                for (int i = 0; i < cc.getA1().getFun_arity(); i++) {
                    Unifier newCouple = new Unifier(aa1.get(i), aa2.get(i));
                    c.add(newCouple);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Method that implements the Decomposition of unification.
     *
     * @param c ArrayList of couples.
     * @return True, if there is a conflict, false otherwise.
     */
    private static boolean conflict(ArrayList<Unifier> c) {
        for (Unifier cc : c) {
            if (!(cc.getA1().getName().equals(cc.getA2().getName())) && !(cc.getA1().isVariable()) && !(cc.getA2().isVariable())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if che clause is bottom.
     *
     * @return True if the clause is bottom, false otherwise.
     */
    public boolean isBottom() {
        return literals.isEmpty();
    }

    /**
     * Method to get all variables of the clause.
     *
     * @return An ArrayList with the variables.
     */
    public ArrayList<String> getVariables() {
        ArrayList<String> ret = new ArrayList<>();
        for (Literal a : this.getLiterals()) {
            ret.addAll(a.getVariables());
        }
        HashSet hs = new HashSet();
        hs.addAll(ret);
        ret.clear();
        ret.addAll(hs);
        return ret;
    }

    /**
     * Method to create the proof of algorithm.
     *
     * @return An ArrayList with the proof.
     */
    public ArrayList<StepProof> proof() {
        ArrayList<StepProof> prova = new ArrayList<>();
        for (StepProof c : history) {
            if (!c.getOperation().equals("Subsumption")) {
                prova.add(c);
                for (Clause x : TheoremProver.history) {
                    if (x.getId_Clause() == c.getFirst()) {
                        prova.addAll(x.proof());
                        break;
                    }
                }
                if (c.getSecond() != -1) {
                    for (Clause x : TheoremProver.history) {
                        if (x.getId_Clause() == c.getSecond()) {
                            prova.addAll(x.proof());
                            break;
                        }
                    }
                }
            }
        }
        return prova;
    }

    /**
     * Method to get the weight of clause.
     *
     * @return An integer, with the weight.
     */
    public int getWeight() {
        calculateWeight();
        return this.weight;
    }

    private static int searchWeight(String s) {
        for (Burden ws : TheoremProver.weights) {
            if (ws.getName().equals(s)) {
                return ws.getWeight();
            }
        }
        return -1;
    }
}
