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
public class Burden {

    String name;
    int weight;

    /**
     * Constructor of Burden.
     *
     * @param name The name of element.
     * @param weight The weight of the element.
     */
    public Burden(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     * Method to return the name of weight.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the weight of the element.
     *
     * @return An integer, the weight.
     */
    public int getWeight() {
        return weight;
    }
}
