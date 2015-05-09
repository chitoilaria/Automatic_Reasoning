package theoremprover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import static theoremprover.TheoremProver.SOS;
import static theoremprover.TheoremProver.clauses;
import static theoremprover.TheoremProver.count_ratio;
import static theoremprover.TheoremProver.eliminated;
import static theoremprover.TheoremProver.history;
import static theoremprover.TheoremProver.limit;
import static theoremprover.TheoremProver.precendeceChoice;
import static theoremprover.TheoremProver.timeForIA;
import static theoremprover.TheoremProver.timeForEA;
import static theoremprover.TheoremProver.toContract;
import static theoremprover.TheoremProver.use_k;
import static theoremprover.TheoremProver.use_old;
import static theoremprover.TheoremProver.cancel;

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
public class ProverThread extends SwingWorker {

    private final TheoremProver frame;
    private final String algorithm;

    /**
     * Constructor of ProverThread.
     *
     * @param frame The original frame of TheoremProver.
     * @param algorithm The algorithm choosen.
     */
    public ProverThread(TheoremProver frame, String algorithm) {
        this.frame = frame;
        this.algorithm = algorithm;
    }

    /**
     * Method doInBackground of SwingWorker
     *
     * @return An Object.
     * @throws Exception
     */
    @Override
    protected Object doInBackground() throws Exception {
        frame.runProver.setEnabled(false);
        cancel = false;
        switch (algorithm) {
            case "E":
                givenClauseE();
                break;
            case "Otter":
                givenClauseOtter();
                break;
        }
        return null;
    }

    /**
     * Method done of SwingWorker
     */
    @Override
    protected void done() {
        try {
            get();
        } catch (final InterruptedException ex) {
        } catch (final ExecutionException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof UnsatException) {
                timeForEA = (long) ((System.nanoTime() - timeForIA) / Math.pow(10, 6));
                frame.progressBar.setIndeterminate(false);
                frame.stopExecution.setEnabled(false);
                frame.closeFile.setEnabled(true);
                frame.jtsx.append("Result: Unsatisfiable    ⊥\n\n");
                frame.jtsx.append("Execution time: " + timeForEA + "ms");
                frame.jtdx.append("Proof Reconstruction:\n");
                makeHistory();
                proof();
                printHistory();
                Utilities.setJOptionPane();
                int reply = JOptionPane.showConfirmDialog(null, "Do you want to save an execution report?", "Report", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    frame.saveDialog();
                }
            } else if (cause instanceof ClauseOutOfBoundsException) {
                timeForEA = (long) ((System.nanoTime() - timeForIA) / Math.pow(10, 6));
                Utilities.setJOptionPane();
                frame.closeFile.setEnabled(true);
                frame.jtsx.append("Result: Limit exceeded\n\n");
                frame.jtsx.append("Execution time: " + timeForEA + "ms");
                frame.stopExecution.setEnabled(false);
                frame.progressBar.setIndeterminate(false);
                JOptionPane.showMessageDialog(null, "Clause limit of " + limit + " exceeded");
            } else if (cause instanceof SatException) {
                timeForEA = (long) ((System.nanoTime() - timeForIA) / Math.pow(10, 6));
                frame.closeFile.setEnabled(true);
                frame.jtsx.append("Result:\n\n");
                frame.jtsx.append("Satisfiable\n\n");
                frame.jtsx.append("Execution time: " + timeForEA + "ms");
                frame.progressBar.setIndeterminate(false);
            }
        }
    }

    /**
     * Algorithm of the Given Clause (E)
     *
     * @throws theoremprover.UnsatException
     * @throws theoremprover.ClauseOutOfBoundsException
     * @throws theoremprover.SatException
     */
    public void givenClauseE() throws UnsatException, ClauseOutOfBoundsException, SatException {
        timeForIA = System.nanoTime();
        while (!SOS.isEmpty()) {
            if (isCancelled()) {
                break;
            }
            toContract.clear();
            Clause fhi = chooseClause();
            Clause fhiOne = forwardContractionClauses(fhi);
            if (fhiOne == null) {
                if (cancel) {
                    SOS.remove(fhi);
                    cancel = false;
                    continue;
                }
                fhiOne = fhi;
            } else if (fhiOne.isBottom()) {
                history.add(fhiOne);
                throw new UnsatException();
            }
            backwardContractionClauses(fhiOne);

            Clause factor = Clause.factorization(fhiOne, precendeceChoice);
            if (factor != null) {
                toContract.add(factor);
            }

            Clause fhiTwo;
            for (Clause toResolution : clauses) {
                fhiTwo = Clause.resolution(fhiOne, toResolution, precendeceChoice);
                if (fhiTwo != null) {
                    if (fhiTwo.isBottom()) {
                        history.add(fhiTwo);
                        throw new UnsatException();
                    } else {
                        toContract.add(fhiTwo);
                    }
                }
            }

            clauses.add(fhiOne);

            for (int i = 0; i < toContract.size(); i++) {
                Clause contr = toContract.get(i);
                Clause contracted = forwardContractionClauses(contr);
                if (eliminated) {
                    i--;
                    eliminated = false;
                }
                if (cancel) {
                    SOS.remove(fhi);
                    cancel = false;
                    continue;
                }
                if (contracted != null) {
                    if (contracted.isBottom()) {
                        SOS.remove(fhi);
                        history.add(contracted);
                        throw new UnsatException();
                    }
                    SOS.add(contracted);
                }
            }
            SOS.remove(fhi);
        }
        if (!isCancelled()) {
            throw new SatException();
        }
    }

    /**
     * Algorithm of the Given Clause (OTTER)
     *
     * @throws theoremprover.ClauseOutOfBoundsException
     * @throws UnsatException
     * @throws SatException
     */
    public void givenClauseOtter() throws ClauseOutOfBoundsException, UnsatException, SatException {
        timeForIA = System.nanoTime();
        while (!SOS.isEmpty()) {
            if (isCancelled()) {
                break;
            }
            toContract.clear();
            Clause fhi = chooseClause();
            Clause factor = Clause.factorization(fhi, precendeceChoice);
            if (factor != null) {
                toContract.add(factor);
            }

            Clause fhiOne = null;
            for (Clause toResolution : clauses) {
                fhiOne = Clause.resolution(fhi, toResolution, precendeceChoice);
                if (fhiOne != null) {
                    if (fhiOne.isBottom()) {
                        history.add(fhiOne);
                        throw new UnsatException();
                    } else {
                        toContract.add(fhiOne);
                    }
                }
            }
            SOS.remove(fhi);
            clauses.add(fhi);

            while (!toContract.isEmpty()) {
                Clause c = toContract.get(0);
                Clause newC = forwardContractionSOS(c);
                if (cancel) {
                    toContract.remove(c);
                    cancel = false;
                    continue;
                }
                Clause newC2 = null;

                if (newC != null) {
                    if (newC.isBottom()) {
                        history.add(newC);
                        throw new UnsatException();
                    } else {
                        newC2 = forwardContractionClauses(newC);
                        if (cancel) {
                            toContract.remove(c);
                            cancel = false;
                            continue;
                        }
                    }
                } else {
                    newC2 = forwardContractionClauses(c);
                    if (cancel) {
                        toContract.remove(c);
                        cancel = false;
                        continue;
                    }
                }

                if (newC2 != null) {
                    if (newC2.isBottom()) {
                        history.add(newC2);
                        throw new UnsatException();
                    } else {
                        backwardContractionSOS(newC2);
                        backwardContractionClauses(newC2);
                        SOS.add(newC2);
                    }
                } else if (newC != null) {
                    backwardContractionSOS(newC);
                    backwardContractionClauses(newC);
                    SOS.add(newC);
                } else {
                    backwardContractionSOS(c);
                    backwardContractionClauses(c);
                    SOS.add(c);
                }
                toContract.remove(c);
            }
        }
        if (!isCancelled()) {
            throw new SatException();
        }
    }

    /**
     * Method that performs the forwardContractionSOS in CLAUSE.
     *
     * @param fhi The clause to contract.
     * @throws UnsatException
     * @throws LimitExceededException
     */
    private Clause forwardContractionClauses(Clause fhi) throws UnsatException, ClauseOutOfBoundsException {
        if (Clause.eliminationOfTautologies(fhi) != null) {
            cancel = true;
            return null;
        }
        for (Clause c : clauses) {
            if (Clause.subsumption(c, fhi) != null) {
                cancel = true;
                return null;
            }
            Clause toReturn = Clause.simplificationOfClauses(c, fhi);
            if (toReturn != null) {
                if (toReturn.isBottom()) {
                    history.add(fhi);
                    history.add(toReturn);
                    throw new UnsatException();
                }
                fhi = toReturn;
            }
        }
        return fhi;
    }

    /**
     * Method that performs the backwardContraction in CLAUSE.
     *
     * @param fhi The clause for the contract.
     * @throws UnsatException
     * @throws LimitExceededException
     */
    private void backwardContractionClauses(Clause fhi) throws UnsatException, ClauseOutOfBoundsException {
        for (int i = 0; i < clauses.size(); i++) {
            Clause d = clauses.get(i);

            if (Clause.eliminationOfTautologies(d) != null) {
                i--;
                clauses.remove(d);
                continue;
            }

            if (Clause.subsumption(fhi, d) != null) {
                i--;
                continue;
            }

            Clause toReturn = Clause.simplificationOfClauses(fhi, d);
            if (toReturn != null) {
                if (toReturn.isBottom()) {
                    history.add(fhi);
                    history.add(toReturn);
                    throw new UnsatException();
                }
                i--;
                toContract.add(toReturn);
            }
        }
    }

    /**
     * Method that performs the forwardContractionSOS in SOS.
     *
     * @param fhi The clause to contract.
     * @throws UnsatException
     * @throws LimitExceededException
     */
    private Clause forwardContractionSOS(Clause fhi) throws UnsatException, ClauseOutOfBoundsException {
        if (Clause.eliminationOfTautologies(fhi) != null) {
            cancel = true;
            return null;
        }
        for (Clause c : SOS) {
            if (Clause.subsumption(c, fhi) != null) {
                cancel = true;
                return null;
            }
            Clause toReturn = Clause.simplificationOfClauses(c, fhi);
            if (toReturn != null) {
                if (toReturn.isBottom()) {
                    history.add(fhi);
                    history.add(toReturn);
                    throw new UnsatException();
                }
                fhi = toReturn;
            }
        }
        return fhi;
    }

    /**
     * Method that performs the backwardContraction in SOS.
     *
     * @param fhi The clause for the contract.
     * @throws UnsatException
     * @throws LimitExceededException
     */
    private void backwardContractionSOS(Clause fhi) throws UnsatException, ClauseOutOfBoundsException {
        for (int i = 0; i < SOS.size(); i++) {
            Clause d = SOS.get(i);

            if (Clause.eliminationOfTautologies(d) != null) {
                i--;
                SOS.remove(d);
                continue;
            }

            if (Clause.subsumption(fhi, d) != null) {
                i--;
                continue;
            }

            Clause toReturn = Clause.simplificationOfClauses(fhi, d);
            if (toReturn != null) {
                if (toReturn.isBottom()) {
                    history.add(fhi);
                    history.add(toReturn);
                    throw new UnsatException();
                }
                i--;
                TheoremProver.toContract.add(toReturn);
            }
        }
    }

    /**
     * Method for the selection of the clause.
     *
     * @return The clause selected.
     */
    private Clause chooseClause() {
        Clause c;
        if (use_k != 0) {
            if (count_ratio <= use_k) {
                c = searchLightest();
                count_ratio++;
            } else {
                c = searchOldest();
                count_ratio = 0;
            }
            return c;
        } else if (use_old) {
            c = searchOldest();
            return c;
        } else {
            c = searchLightest();
            return c;
        }
    }

    /**
     * Method for search the Oldest clause into SOS.
     *
     * @return The Clause.
     */
    private Clause searchOldest() {
        Clause ret = SOS.get(0);
        for (Clause c : SOS) {
            if (c.getId_Clause() < ret.getId_Clause()) {
                ret = c;
            }
        }
        return ret;
    }

    /**
     * Method for search the Lightest clause into SOS.
     *
     * @return The Clause.
     */
    private Clause searchLightest() {
        Clause ret = SOS.get(0);
        for (Clause c : SOS) {
            if (c.getWeight() < ret.getWeight()) {
                ret = c;
            }
        }
        return ret;

    }

    /**
     * Method for the reconstruction of proof.
     */
    private void proof() {
        Clause c = history.get(history.size() - 1);
        ArrayList<StepProof> prova = c.proof();
        for (StepProof d : prova) {
            frame.jtdx.append("\n" + d.toString());
        }
    }

    /**
     * Create the history.
     */
    private void makeHistory() {
        history.addAll(clauses);
        history.addAll(SOS);
        history.addAll(toContract);
        HashSet hs = new HashSet();
        hs.addAll(history);
        history.clear();
        history.addAll(hs);
        Collections.sort(history, new ClauseComparatorOldest());
    }

    /**
     * Print the history into the table.
     */
    private void printHistory() {
        frame.model.setRowCount(0);
        Object[] x = new Object[3];
        x[0] = "";
        x[1] = "CLAUSE";
        x[2] = "";
        frame.model.addRow(x);

        for (Clause c : history) {
            Object[] da = new Object[3];
            da[0] = c.getId_Clause();
            da[1] = c.toString();
            da[2] = c.getWeight();
            frame.model.addRow(da);

        }
    }

    /**
     * class to override the method Comparator.
     */
    class ClauseComparatorOldest implements Comparator {

        /**
         * Method compare.
         *
         * @param p1 First object for the compare.
         * @param p2 Second object for the compare.
         * @return The result of the comparison
         */
        @Override
        public int compare(Object p1, Object p2) {

            int idClause1 = ((Clause) p1).getId_Clause();
            int idClause2 = ((Clause) p2).getId_Clause();

            if (idClause1 > idClause2) {
                return 1;
            } else if (idClause1 < idClause2) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
