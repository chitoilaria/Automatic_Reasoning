package theoremprover;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
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
public class Literal {

    private String name;
    private boolean isNot = false;
    private final int arity;
    private final ArrayList<Argument> subArgs;
    private int weight = 0;

    /**
     * Constructor of literal.
     *
     * @param tree The tree for the literal.
     */
    public Literal(CommonTree tree) {
        this.subArgs = new ArrayList<>();
        generateLiteral(tree);
        this.arity = this.subArgs.size();
        calculateWeight();

    }

    /**
     * Constructor of literal.
     *
     * @param name the name of literal.
     * @param isNot if the literal is negated.
     * @param arity the arity of the literal.
     * @param subArgs the sub args of the literal.
     */
    public Literal(String name, boolean isNot, int arity, ArrayList<Argument> subArgs) {
        this.name = name;
        this.isNot = isNot;
        this.arity = arity;
        this.subArgs = subArgs;
        calculateWeight();
    }

    /**
     * Method for the calculus of the Weight of the literal.
     */
    public void calculateWeight() {
        this.weight = 0;
        if (!TheoremProver.weights.isEmpty()) {
            for (Burden x : TheoremProver.weights) {
                if (x.getName().equals(this.name)) {
                    this.weight += x.getWeight();
                    break;
                }
            }
            for (Argument x : this.subArgs) {
                this.weight += x.getWeight();
            }
        } else {
            this.weight=1;
            for (Argument x : this.subArgs) {
                this.weight += x.getWeight();
            }
        }
    }

    /**
     * Method for generate the literal from a tree.
     *
     * @param tree The tree of literal.
     */
    private void generateLiteral(CommonTree tree) {

        switch (tree.getType()) {
            case FOLParser.NOT:
                this.isNot = true;
                generateLiteral((CommonTree) tree.getChild(0));
                break;
            case FOLParser.PREDICATE:
                this.name = tree.getText();
                for (int i = 0; i < tree.getChildCount(); i++) {
                    Argument a = new Argument((CommonTree) tree.getChild(i));
                    subArgs.add(a);
                }
        }
    }

    /**
     * Method to get the name of literal.
     *
     * @return String, the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to return if the literal is negated.
     *
     * @return True if the literal is negated, false otherwise.
     */
    public boolean isNot() {
        return isNot;
    }

    /**
     * Method to get the arity of literal.
     *
     * @return An integer, the arity.
     */
    public int getArity() {
        return arity;
    }

    /**
     * Method to return the sub args of the literal.
     *
     * @return An ArrayList with the sub args.
     */
    public ArrayList<Argument> getSubArgs() {
        return subArgs;
    }

    /**
     * Method to get the weight of the literal.
     *
     * @return An integer with the weight.
     */
    public int getWeight() {
        calculateWeight();
        return this.weight;
    }

    /**
     * Method toString.
     *
     * @return A string with the literal.
     */
    @Override
    public String toString() {
        String ap = "";
        for (Argument a : subArgs) {
            ap = ap + "," + a.toString();
        }
        if (isNot) {
            return "-" + this.getName() + "(" + ap.substring(1, ap.length()) + ")";
        } else {
            return this.getName() + "(" + ap.substring(1, ap.length()) + ")";
        }
    }

    /**
     * Method clone for literals.
     *
     * @return A literal cloned.
     */
    @Override
    public Literal clone() {
        ArrayList<Argument> clo = new ArrayList<>();
        for (Argument a : this.subArgs) {
            clo.add(a.clone());
        }
        return new Literal(this.name, this.isNot, this.arity, clo);
    }

    /**
     * Method equals between literals.
     *
     * @param a The second Object to equals. It's a Literal, sure.
     * @return True if both are equals, false otherwise.
     */
    @Override
    public boolean equals(Object a) {

        Literal x = (Literal) a;

        if (this.name.equals(x.getName()) && this.arity == x.getArity() && this.isNot() == x.isNot()) {
            for (int i = 0; i < this.arity; i++) {
                if (!(this.getSubArgs().get(i).equals(x.getSubArgs().get(i)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + (this.isNot ? 1 : 0);
        hash = 37 * hash + this.arity;
        return hash;
    }

    /**
     * Method equals between literals, but not check if have the same sign.
     *
     * @param l2 The second literal to equals.
     * @return True if both are equals, false otherwise.
     */
    public boolean equalsLit(Literal l2) {
        if (this.name.equals(l2.getName()) && this.arity == l2.getArity()) {
            for (int i = 0; i < this.arity; i++) {
                if (!(this.getSubArgs().get(i).equals(l2.getSubArgs().get(i)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Method to get all variables of the literal.
     *
     * @return An ArrayList with the variables.
     */
    public ArrayList<String> getVariables() {
        ArrayList<String> ret = new ArrayList<>();

        for (Argument a : this.getSubArgs()) {
            ret.addAll(a.getVariables());
        }
        HashSet hs = new HashSet();
        hs.addAll(ret);
        ret.clear();
        ret.addAll(hs);
        return ret;
    }

    /**
     * Method to compare with order two Literals.
     *
     * @param a The second literal.
     * @param type The type of ordering.
     * @return True if the first is Greater than the second, false otherwise.
     */
    public boolean isGreater(Literal a, String type) {
        int posThis = TheoremProver.sortingRules.indexOf(this.getName());
        int posA = TheoremProver.sortingRules.indexOf(a.getName());
        boolean ret = false;

        for (Argument x : this.subArgs) {
            ret = x.isGreater(a, type);
            if (ret) {
                return true;
            }
        }

        if (posThis < posA) {
            ret = false;
            for (Argument x : a.getSubArgs()) {
                ret = this.isGreater(x, type);
                if (!ret) {
                    return false;
                }
            }
            return true;
        } else if (posThis == -1 || posA == -1) {
            return true;
        }else if (this.name.equals(a.getName())) {
            switch (type) {
                case "Lexicographical":
                    for (int i = 0; i < this.subArgs.size(); i++) {
                        if (this.subArgs.get(i).equals(a.getSubArgs().get(i))) {
                            continue;
                        } else if (this.subArgs.get(i).isGreater(a.getSubArgs().get(i), type)) {
                            for (int j = i + 1; j < a.subArgs.size(); j++) {
                                if (!this.isGreater(a.getSubArgs().get(j), type)) {
                                    return false;
                                }
                            }
                            return true;
                        } else {
                            return false;
                        }
                    }
                    return false;
                case "Multiset":
                    ArrayList<Argument> multisetThis = new ArrayList<>();
                    for (Argument q : this.subArgs) {
                        multisetThis.add(q.clone());
                    }
                    ArrayList<Argument> multisetA = new ArrayList<>();
                    for (Argument w : a.getSubArgs()) {
                        multisetA.add(w.clone());
                    }

                    return Argument.isGreaterMultiSet(multisetThis, multisetA, type);
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     * Method to compare this literal with an argument.
     *
     * @param a The argument to compare.
     * @param type The type of ordering.
     * @return True if the first is Greater than the second, false otherwise.
     */
    public boolean isGreater(Argument a, String type) {
        int posThis = TheoremProver.sortingRules.indexOf(this.getName());
        int posA = TheoremProver.sortingRules.indexOf(a.getName());
        boolean ret = false;
        if (a.isFunction()) {
            for (Argument x : this.subArgs) {
                ret = x.isGreater(a, type) || x.equals(a);
                if (ret) {
                    return true;
                }
            }

            if (posThis < posA) {
                ret = false;
                for (Argument x : a.getSub_Args()) {
                    ret = this.isGreater(x, type);
                    if (!ret) {
                        return false;
                    }
                }
                return true;
            }else return posThis == -1 || posA == -1;
        } else {

            for (Argument x : this.subArgs) {
                ret = x.isGreater(a, type) || x.equals(a);
                if (ret) {
                    return true;
                }
            }

            if (posThis != -1 && posA != -1) {
                return posThis < posA;
            } else {
                return true;
            }
        }
    }

    /**
     * Method to compare two literals.
     *
     * @param literalAx
     * @param type Type of sorting.
     * @return True if the first literal is greatest than the second.
     */
    public boolean isMax(ArrayList<Literal> literalAx, String type) {
        for (Literal lk : literalAx) {
            if (!this.isGreater(lk, type)) {
                return false;
            }
        }
        return true;
    }
}
