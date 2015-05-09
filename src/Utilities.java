package theoremprover;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

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
public class Utilities {

    /**
     * Empty constructor.
     */
    private Utilities() {
    }

    /**
     * Method for set OptionPane graphics.
     */
    static void setJOptionPane() {
        UIManager.put(
                "OptionPane.messageFont",
                new FontUIResource(new Font("Verdana", Font.PLAIN, 18))
        );
        UIManager.put(
                "OptionPane.buttonFont",
                new FontUIResource(new Font("Verdana", Font.PLAIN, 18))
        );
        UIManager.put(
                "OptionPane.font",
                new FontUIResource(new Font("Verdana", Font.PLAIN, 18))
        );
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
    }

    /**
     * Method for change all font for the graphics.
     * @param f FontUIResource of the project.
     */
    static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    /**
     * Method for change all font for the graphics.
     */
    static void setGraphics() {
        Utilities.setUIFont(new javax.swing.plaf.FontUIResource("Helvetica", Font.PLAIN, 18));
    }
    /**
     * Method for launch an exception if the number of clauses exceeded a limit.
     * @throws LimitExceededException
     */
    static void checkLimitClauses() throws ClauseOutOfBoundsException{
        if (TheoremProver.countClause > TheoremProver.limit)
            throw new ClauseOutOfBoundsException();
    }

}
