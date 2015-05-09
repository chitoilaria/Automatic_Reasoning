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
public class Argument{

    private String name;
    private boolean isFunction = false;
    private boolean isConstant = false;
    private boolean isVariable = false;
    private int fun_arity = 0;
    private ArrayList<Argument> sub_Args;
    private int weight = 0;

    /**
     * Constructor for Argument.
     *
     * @param tree Tree.
     */
    public Argument(CommonTree tree) {
        generateArgument(tree);
        calculateWeight();
    }

    /**
     * Constructor for Argument.
     *
     * @param name of the argument.
     * @param isFunction if is a function.
     * @param isConstant if is a constant.
     * @param isVariable if is a variable.
     * @param fun_arity arity if the argument is a function.
     * @param sub_Args sub arguments if the argument is a function.
     */
    public Argument(String name, boolean isFunction, boolean isConstant, boolean isVariable, int fun_arity, ArrayList<Argument> sub_Args) {
        this.name = name;
        this.isConstant = isConstant;
        this.isFunction = isFunction;
        this.isVariable = isVariable;
        this.fun_arity = fun_arity;
        if (this.isFunction) {
            this.sub_Args = (ArrayList<Argument>) sub_Args.clone();
        }
        calculateWeight();
    }

    /**
     * Method for get the name.
     *
     * @return String with name of Argument.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to determine if is a function.
     *
     * @return True if is a function, false otherwise.
     */
    public boolean isFunction() {
        return isFunction;
    }

    /**
     * Method to determine if is a constant.
     *
     * @return True if is a constant, false otherwise.
     */
    public boolean isConstant() {
        return isConstant;
    }

    /**
     * Method to determine if is a variable.
     *
     * @return True if is a constant, false otherwise.
     */
    public boolean isVariable() {
        return isVariable;
    }

    /**
     * Method to return the arity of the argument.
     *
     * @return An integer, the arity.
     */
    public int getFun_arity() {
        return fun_arity;
    }

    /**
     * Method to return the current weight.
     *
     * @return An integer, the weight.
     */
    public int getWeight() {
        calculateWeight();
        return this.weight;
    }

    /**
     * Method to return the sub arguments.
     *
     * @return the ArrayList of sub arguments.
     */
    public ArrayList<Argument> getSub_Args() {
        return sub_Args;
    }

    /**
     * Method that calculates the weights.
     */
    public void calculateWeight() {
        if (!TheoremProver.weights.isEmpty()) {
            this.weight = 0;
            for (Burden x : TheoremProver.weights) {
                if (x.getName().equals(this.name)) {
                    this.weight += x.getWeight();
                    break;
                }
            }
            if (this.isFunction) {
                for (Argument x : this.sub_Args) {
                    this.weight += x.getWeight();
                }
            }
        } else {
            if (this.isFunction) {
                this.weight = 1;
                for (Argument x : this.sub_Args) {
                    this.weight += x.getWeight();
                }
            } else {
                this.weight = 1;
            }
        }
    }

    /**
     * Method to read the Tree and generate the Arguments.
     *
     * @param tree Tree with the arguments.
     */
    private void generateArgument(CommonTree tree) {
        this.name = tree.getText();
        switch (tree.getType()) {
            case FOLParser.VARIABLE:
                this.isVariable = true;
                break;
            case FOLParser.CONSTANT:
                this.isConstant = true;
                break;
            case FOLParser.FUNCTION:
                this.sub_Args = new ArrayList<>();
                this.isFunction = true;
                for (int i = 0; i < tree.getChildCount(); i++) {
                    Argument a = new Argument((CommonTree) tree.getChild(i));
                    sub_Args.add(a);
                }
                this.fun_arity = sub_Args.size();
                break;
        }
    }

    /**
     * Method to search if the argument have occurences of another argument.
     *
     * @param s The name of argument to search.
     * @return True if have occurences, false otherwise.
     */
    public boolean haveOccurences(String s) {
        boolean done = false;
        if (this.name.equals(s)) {
            return true;
        } else if (this.isFunction()) {
            for (Argument a : sub_Args) {
                done = done || haveOccurrences(a, s);
            }
            return done;
        }
        return false;
    }

    /**
     * Method to search if an argument have occurences of another argument.
     *
     * @param a The first argument.
     * @param s The name of second argument.
     * @return True if have occurences, false otherwise.
     */
    private boolean haveOccurrences(Argument a, String s) {
        if (a.getName().equals(s)) {
            return true;
        } else if (a.isFunction()) {
            for (Argument x : a.getSub_Args()) {
                return haveOccurrences(x, s);
            }
        }
        return false;
    }

    /**
     * Method equals between arguments.
     *
     * @param x The second Object to equals. It's an Argument, sure.
     * @return True if both are equals, false otherwise.
     */
    @Override
    public boolean equals(Object x) {

        Argument a = (Argument) x;

        if (this.getName().equals(a.getName()) && this.getFun_arity() == a.getFun_arity() && this.isConstant == a.isConstant() && this.isFunction == a.isFunction() && this.isVariable == a.isVariable()) {
            if (this.isFunction) {
                for (int i = 0; i < this.getFun_arity(); i++) {
                    if (!(this.sub_Args.get(i).equals(a.getSub_Args().get(i)))) {
                        return false;
                    }
                }
                return true;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + (this.isFunction ? 1 : 0);
        hash = 59 * hash + (this.isConstant ? 1 : 0);
        hash = 59 * hash + (this.isVariable ? 1 : 0);
        hash = 59 * hash + this.fun_arity;
        hash = 59 * hash + Objects.hashCode(this.sub_Args);
        return hash;
    }

    /**
     * Method toString.
     *
     * @return The string with
     */
    @Override
    public String toString() {
        String ap = "";
        if (isFunction) {
            for (Argument a : sub_Args) {
                ap = ap + a.toString() + ",";
            }
            return this.name + "(" + ap.substring(0, ap.length() - 1) + ")";
        }
        return this.name;
    }

    /**
     * Method contains to search if an Argument is contained into this Argument.
     *
     * @param o The argument to search.
     * @return True if the argument is contained, false otherwise.
     */
    public boolean contains(Argument o) {
        if (this.isFunction()) {
            for (Argument p : this.sub_Args) {
                if (p.equals(o)) {
                    return true;
                } else if (p.isFunction()) {
                    return p.contains(o);
                }
            }
        }
        return false;
    }

    /**
     * Method for clone an Argument.
     *
     * @return the argument cloned.
     */
    @Override
    public Argument clone() {
        if (isFunction) {
            ArrayList<Argument> clo = new ArrayList<>();
            for (Argument a : this.sub_Args) {
                clo.add(a.clone());
            }
            return new Argument(this.name, this.isFunction, this.isConstant, this.isVariable, this.fun_arity, clo);
        } else {
            return new Argument(this.name, this.isFunction, this.isConstant, this.isVariable, this.fun_arity, null);
        }
    }

    /**
     * Method to get all the variables into this Argument.
     *
     * @return ArrayList with the variables of this argument.
     */
    public ArrayList<String> getVariables() {

        ArrayList<String> ret = new ArrayList<>();

        if (isVariable) {
            ret.add(this.name);
        } else if (isFunction) {
            for (Argument a : this.getSub_Args()) {
                ret.addAll(a.getVariables());
            }
        }
        HashSet hs = new HashSet();
        hs.addAll(ret);
        ret.clear();
        ret.addAll(hs);
        return ret;
    }

    /**
     * Method to compare with order two Arguments.
     *
     * @param a The second argument.
     * @param type The type of ordering.
     * @return True if the first is Greater than the second, false otherwise.
     */
    public boolean isGreater(Argument a, String type) {
        int posThis = TheoremProver.sortingRules.indexOf(this.getName());
        int posA = TheoremProver.sortingRules.indexOf(a.getName());

        if (!this.isFunction && !a.isFunction()) {
            if (posThis != -1 && posA != -1) {
                return posThis < posA;
            } else {
                return true;
            }
        } else if (this.isFunction && a.isFunction()) {
            if (!this.name.equals(a.getName())) {
                boolean ret = false;

                for (Argument x : this.sub_Args) {
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
                } else return posThis == -1 || posA == -1;
            } else {
                switch (type) {
                    case "Lexicographical":
                        for (int i = 0; i < this.sub_Args.size(); i++) {
                            if (this.sub_Args.get(i).equals(a.getSub_Args().get(i))) {
                                continue;
                            } else if (this.sub_Args.get(i).isGreater(a.getSub_Args().get(i), type)) {
                                for (int j = i + 1; j < a.sub_Args.size(); j++) {
                                    if (!this.isGreater(a.getSub_Args().get(j), type)) {
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
                        for (Argument q : this.sub_Args) {
                            multisetThis.add(q.clone());
                        }
                        ArrayList<Argument> multisetA = new ArrayList<>();
                        for (Argument w : a.getSub_Args()) {
                            multisetA.add(w.clone());
                        }
                        return isGreaterMultiSet(multisetThis, multisetA, type);
                    default:
                        return false;
                }
            }
        } else if (this.isFunction && !a.isFunction()) {

            boolean ret = false;

            if (this.contains(a)) {
                return true;
            }

            for (Argument x : this.sub_Args) {
                ret = x.isGreater(a, type) || x.equals(a);
                if (ret) {
                    return true;
                }
            }
            return false;
        } else {
            if (a.contains(this)) {
                return false;
            }
            if (posThis < posA) {
                boolean ret = false;
                for (Argument x : a.getSub_Args()) {
                    ret = this.isGreater(x, type);
                    if (!ret) {
                        return false;
                    }
                }
                return true;
            } else return posThis == -1 || posA == -1;
        }
    }

    /**
     * Method to apply Multiset ordering.
     *
     * @param multisetThis First ArrayList of arguments to apply multiset.
     * @param multisetA Second ArratList of arguments to apply multiset.
     * @param type The type of ordering.
     * @return True if the first is Greater than the second, false otherwise.
     */
    static boolean isGreaterMultiSet(ArrayList<Argument> multisetThis, ArrayList<Argument> multisetA, String type) {
        for (int i = 0; i < multisetThis.size(); i++) {
            Argument thi = multisetThis.get(i);
            int pos = -1;
            boolean rem = false;
            for (int j = 0; j < multisetA.size(); j++) {
                Argument a = multisetA.get(i);
                if (thi.isGreater(a, type)) {
                    multisetA.remove(a);
                    j--;
                } else if (thi.equals(a)) {
                    pos = j;
                } else {
                    rem = true;
                }
            }
            if (pos != -1) {
                multisetA.remove(pos);
                multisetThis.remove(thi);
                i--;
                continue;
            }
            if (rem) {
                multisetThis.remove(thi);
                i--;
            }
        }
        return !multisetThis.isEmpty();
    }

    /**
     * Method to compare this argument with a Literal.
     *
     * @param a The literal to compare.
     * @param type The type of ordering.
     * @return True if the first is Greater than the second, false otherwise.
     */
    public boolean isGreater(Literal a, String type) {
        int posThis = TheoremProver.sortingRules.indexOf(this.getName());
        int posA = TheoremProver.sortingRules.indexOf(a.getName());

        boolean ret = false;

        if (this.isFunction) {
            for (Argument x : this.sub_Args) {
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
            }else return posThis == -1 || posA == -1;
        } else {

            for (Argument x : a.getSubArgs()) {
                if (x.contains(this)) {
                    return false;
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
            }else return posThis == -1 || posA == -1;
        }
    }
}
