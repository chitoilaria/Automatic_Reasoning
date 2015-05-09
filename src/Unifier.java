package theoremprover;

import java.util.Objects;

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
public class Unifier {

    private Argument a1;
    private Argument a2;
    private boolean mark;

    /**
     * Constructor of Unifier.
     *
     * @param a1 the first argument
     * @param a2 the second argument
     */
    public Unifier(Argument a1, Argument a2) {
        this.a1 = a1;
        this.a2 = a2;
        this.mark = false;
    }

    /**
     * Method to set the first argument.
     *
     * @param a1 the argument to set.
     */
    public void setA1(Argument a1) {
        this.a1 = a1;
    }

    /**
     * Method to set the second argument.
     *
     * @param a2 the argument to set.
     */
    public void setA2(Argument a2) {
        this.a2 = a2;
    }

    /**
     * Method to return if the unifier has already been considered.
     *
     * @return True if the unifier has already been considered, false otherwise.
     */
    public boolean isMark() {
        return mark;
    }

    /**
     * Method to mark a unifier.
     */
    public void mark() {
        this.mark = true;
    }

    /**
     * Method to get the first argument.
     *
     * @return the first argument.
     */
    public Argument getA1() {
        return a1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.a1);
        hash = 89 * hash + Objects.hashCode(this.a2);
        return hash;
    }

    /**
     * Method to get the second argument.
     *
     * @return the second argument.
     */
    public Argument getA2() {
        return a2;
    }

    /**
     * Method toString.
     *
     * @return String with the unifier.
     */
    @Override
    public String toString() {
        return a1.toString() + "←" + a2.toString() + ", ";
    }
}
