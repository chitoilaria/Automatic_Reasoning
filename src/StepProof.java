package theoremprover;

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
public class StepProof {

    private final String operation;
    private final int result;
    private final int first;
    private final int second;
    private final String unifier;

    /**
     * Constructor of StepProof.
     *
     * @param operation Operation executed.
     * @param result Result of operation.
     * @param first The first element considered.
     * @param second The second element considered.
     * @param unifier The unifier of the operation.
     */
    public StepProof(String operation, int result, int first, int second, String unifier) {
        this.operation = operation;
        this.result = result;
        this.first = first;
        this.second = second;
        this.unifier = unifier;
    }

    /**
     * Method to get the operation about step.
     *
     * @return The name of operation.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Method to get the first element of the step.
     *
     * @return The number of clauses considered.
     */
    public int getFirst() {
        return first;
    }

    /**
     * Method to get the second element of the step.
     *
     * @return The number of clauses considered.
     */
    public int getSecond() {
        return second;
    }

    /**
     * Method toString.
     *
     * @return A String with the step.
     */
    @Override
    public String toString() {
        if (unifier.equals("")) {
            if (second != -1) {
                return operation + " : " + first + " ♦ " + second + " » " + result + "\n";
            } else {
                return operation + " : " + first + " » " + result + "\n";
            }
        } else {
            if (second != -1) {
                return operation + " : " + first + " ♦ " + second + " » " + result + "\n" + unifier + "\n";
            } else {
                return operation + " : " + first + " » " + result + "\n" + unifier + "\n";
            }
        }
    }

}
