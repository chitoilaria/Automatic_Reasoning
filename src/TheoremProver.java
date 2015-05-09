package theoremprover;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.concurrent.CancellationException;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
public class TheoremProver extends JFrame {

    final int NUMBER_OF_CLAUSES = 10000;
    /**
     * Buffer for the file.
     */
    private BufferedReader br;
    /**
     * Variable for the management of the file read.
     */
    private File file;
    /**
     * Support string for the transfer of the lines in the buffer.
     */
    private String strLine;
    /**
     * Menu bar for the program.
     */
    private final JMenuBar menuBar = new JMenuBar();
    /**
     * Menu element.
     */
    private final JMenu fileMenu;
    /**
     * Scroll right low.
     */
    private final JScrollPane scrolldxbottom;
    /**
     * Table that contains data about individual formulas examined.
     */
    private final JTable table;
    /**
     * Model for the first table.
     */
    final DefaultTableModel model;
    /**
     * Array that identifies the strings attached to the columns for the first
     * table.
     */
    private final String[] columnNames;
    /**
     * Multidimensional array that identifies the data in the first table.
     */
    private String[][] data;
    /**
     * JTextArea of print.
     */
    final JTextArea jtdx;
    /**
     * ScrollPane for JTextArea.
     */
    private final JScrollPane sbrTextdx;
    /**
     * JTextArea of print.
     */
    final JTextArea jtsx;
    /**
     * ScrollPane for JTextArea.
     */
    private final JScrollPane sbrTextsx;
    /**
     * Group for the choice of the weight.
     */
    private ButtonGroup group1 = new ButtonGroup();
    /**
     * Group for the choice of the algorithm.
     */
    private ButtonGroup group2 = new ButtonGroup();
    /**
     * Group for the choice of the sort.
     */
    private ButtonGroup group3 = new ButtonGroup();
    /**
     * Group for the limit.
     */
    private ButtonGroup group4 = new ButtonGroup();
    /**
     * JCheckBox Pick Ratio.
     */
    private final JCheckBoxMenuItem cbMenuItem1;
    /**
     * JCheckBox Oldest.
     */
    private final JCheckBoxMenuItem cbMenuItem2;
    /**
     * JCheckBox Lightest.
     */
    private final JCheckBoxMenuItem cbMenuItem3;
    /**
     * JCheckBox Otter.
     */
    private final JCheckBoxMenuItem cbMenuItem4;
    /**
     * JCheckBox E.
     */
    private final JCheckBoxMenuItem cbMenuItem5;
    /**
     * JCheckBox Lessicographical.
     */
    private final JCheckBoxMenuItem cbMenuItem6;
    /**
     * JCheckBox Multiset.
     */
    private final JCheckBoxMenuItem cbMenuItem7;
    /**
     * JCheckBox Multiset.
     */
    private final JCheckBoxMenuItem cbMenuItem8;
    /**
     * JMenuItem openFile.
     */
    private final JMenuItem openFile;
    /**
     * JMenuItem closeFile.
     */
    static JMenuItem closeFile;
    /**
     * JMenuItem settingMenu.
     */
    private final JMenuItem settingMenu;
    /**
     * JMenuItem runMenu.
     */
    private final JMenuItem runMenu;
    /**
     * JMenuItem stopExecution.
     */
    final JMenuItem stopExecution;
    /**
     * JMenuItem runProver.
     */
    final JMenuItem runProver;
    /**
     * JMenuItem exit.
     */
    private final JMenuItem exit;
    /**
     * Counter for the number of clauses.
     */
    static int countClause;
    /**
     * Array for SOS (Da verificare).
     */
    static ArrayList<Clause> SOS;
    /**
     * Array for CLAUSE (Verificate).
     */
    static ArrayList<Clause> clauses;
    /**
     * Temporary Array for contract clauses.
     */
    static ArrayList<Clause> toContract;
    /**
     * Array for the reconstruction of the history.
     */
    static ArrayList<Clause> history;
    /**
     * Array for the precedence rules.
     */
    static ArrayList<String> sortingRules;
    /**
     * Array for the weights.
     */
    static ArrayList<Burden> weights;
    /**
     * Integer for the diversification of the input.
     */
    private int section;
    /**
     * Integer for the round of Pick Ratio.
     */
    static int count_ratio;
    /**
     * Maximum value of the Pick Ratio.
     */
    static int use_k;
    /**
     * Boolean to the choice of use of the older clause.
     */
    static boolean use_old;
    /**
     * Choice of the ordering to UI.
     */
    static String precendeceChoice;
    /**
     * Choice of the algorithm to IU.
     */
    private String algorithmChoice;
    /**
     * Checking if the file is corrupt or wrong.
     */
    private boolean brokenFile = false;
    /**
     * Checking if one operation modify the toContract Array.
     */
    static boolean eliminated = false;
    /**
     * Integer for the limit max of clauses.
     */
    static int limit;
    /**
     * Checking if the limit is set.
     */
    private boolean limitCheck = false;
    /**
     * Checking if EOT or S have cancel.
     */
    static boolean cancel;
    /**
     * My Frame.
     */
    static JFrame mioFrame;
    /**
     * Task for the algorithms.
     */
    ProverThread worker;
    /**
     * JProgressBar.
     */
    final JProgressBar progressBar;
    /**
     * Initial time for the algorithm.
     */
    static long timeForIA;
    /**
     * Final time for the algorithm.
     */
    static long timeForEA;
    /**
     * Encoding for writing files.
     */
    private static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * Constructor of TheoremProver.
     */
    public TheoremProver() {

        setTitle("Theorem Prover");
        setLocation(100, 50);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("/resources/246226.jpg");
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/resources/246226.jpg"))));

        menuBar.add(Box.createRigidArea(new Dimension(0, 35)));
        setJMenuBar(menuBar);
        fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("Helvetica", Font.PLAIN, 18));
        settingMenu = new JMenu("Setting");
        settingMenu.setFont(new Font("Helvetica", Font.PLAIN, 18));
        runMenu = new JMenu("Run");
        runMenu.setFont(new Font("Helvetica", Font.PLAIN, 18));
        openFile = new JMenuItem("Load file...");
        openFile.setFont(new Font("Helvetica", Font.PLAIN, 18));
        closeFile = new JMenuItem("Close");
        closeFile.setFont(new Font("Helvetica", Font.PLAIN, 18));
        closeFile.setEnabled(false);
        exit = new JMenuItem("Exit");
        exit.setFont(new Font("Helvetica", Font.PLAIN, 18));
        fileMenu.add(openFile);
        fileMenu.add(closeFile);
        fileMenu.add(exit);
        runProver = new JMenuItem("Run prover");
        runProver.setFont(new Font("Helvetica", Font.PLAIN, 18));
        runProver.setEnabled(false);

        stopExecution = new JMenuItem("Stop execution");
        stopExecution.setFont(new Font("Helvetica", Font.PLAIN, 18));
        stopExecution.setEnabled(false);
        runMenu.add(runProver);
        runMenu.add(stopExecution);

        menuBar.add(fileMenu);
        menuBar.add(settingMenu);
        menuBar.add(runMenu);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBounds(0, 606, 800, 35);
        progressBar.setString("");
        add(progressBar);
        /**
         * First group for the weight.
         */
        JLabel j1 = new JLabel("Weight:");
        j1.setFont(new Font("Helvetica", Font.PLAIN, 18));
        settingMenu.add(j1);
        group1 = new ButtonGroup();
        cbMenuItem1 = new JCheckBoxMenuItem("Pick Ratio");
        cbMenuItem1.setFont(new Font("Helvetica", Font.PLAIN, 18));
        cbMenuItem2 = new JCheckBoxMenuItem("Oldest");
        cbMenuItem2.setFont(new Font("Helvetica", Font.PLAIN, 18));
        cbMenuItem3 = new JCheckBoxMenuItem("Lightest");
        cbMenuItem3.setFont(new Font("Helvetica", Font.PLAIN, 18));
        group1.add(cbMenuItem1);
        group1.add(cbMenuItem2);
        group1.add(cbMenuItem3);
        settingMenu.add(cbMenuItem1);
        settingMenu.add(cbMenuItem2);
        settingMenu.add(cbMenuItem3);
        /**
         * Insert the separator.
         */
        settingMenu.add(new JSeparator(SwingConstants.HORIZONTAL));
        /**
         * Second group for the algorithm.
         */
        JLabel j2 = new JLabel("Algorithm:");
        j2.setFont(new Font("Helvetica", Font.PLAIN, 18));
        settingMenu.add(j2);
        group2 = new ButtonGroup();
        cbMenuItem4 = new JCheckBoxMenuItem("Otter");
        cbMenuItem4.setFont(new Font("Helvetica", Font.PLAIN, 18));
        cbMenuItem5 = new JCheckBoxMenuItem("E");
        cbMenuItem5.setFont(new Font("Helvetica", Font.PLAIN, 18));
        group2.add(cbMenuItem4);
        group2.add(cbMenuItem5);
        settingMenu.add(cbMenuItem4);
        settingMenu.add(cbMenuItem5);
        /**
         * Insert the separator.
         */
        settingMenu.add(new JSeparator(SwingConstants.HORIZONTAL));
        /**
         * Third group for the order.
         */
        JLabel j3 = new JLabel("Precedence:");
        j3.setFont(new Font("Helvetica", Font.PLAIN, 18));
        settingMenu.add(j3);
        group3 = new ButtonGroup();
        cbMenuItem6 = new JCheckBoxMenuItem("Multiset");
        cbMenuItem6.setFont(new Font("Helvetica", Font.PLAIN, 18));
        cbMenuItem7 = new JCheckBoxMenuItem("Lexicographical");
        cbMenuItem7.setFont(new Font("Helvetica", Font.PLAIN, 18));
        group3.add(cbMenuItem6);
        group3.add(cbMenuItem7);
        settingMenu.add(cbMenuItem6);
        settingMenu.add(cbMenuItem7);
        /**
         * Insert the separator.
         */
        settingMenu.add(new JSeparator(SwingConstants.HORIZONTAL));
        /**
         * Four group for the limit.
         */
        JLabel j4 = new JLabel("Limit Clauses:");
        j4.setFont(new Font("Helvetica", Font.PLAIN, 18));
        settingMenu.add(j4);
        cbMenuItem8 = new JCheckBoxMenuItem("Use Limit");
        cbMenuItem8.setFont(new Font("Helvetica", Font.PLAIN, 18));
        group4.add(cbMenuItem8);
        settingMenu.add(cbMenuItem8);
        /**
         * JTextAreadx.
         */
        jtdx = new JTextArea("");
        jtdx.setEditable(false);
        jtdx.setEnabled(false);
        jtdx.setDisabledTextColor(Color.BLACK);
        jtdx.setLineWrap(true);
        jtdx.setMargin(new Insets(20, 20, 20, 20));
        jtdx.setFont(new Font("Helvetica", Font.PLAIN, 18));
        sbrTextdx = new JScrollPane(jtdx);
        sbrTextdx.setBounds(400, 260, 400, 346);
        sbrTextdx.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sbrTextdx.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sbrTextdx.setVisible(false);
        add(sbrTextdx);

        /**
         * JTextAreasx.
         */
        jtsx = new JTextArea("");
        jtsx.setEditable(false);
        jtsx.setEnabled(false);
        jtsx.setDisabledTextColor(Color.BLACK);
        jtsx.setLineWrap(true);
        jtsx.setMargin(new Insets(20, 20, 20, 20));
        jtsx.setFont(new Font("Helvetica", Font.PLAIN, 18));
        sbrTextsx = new JScrollPane(jtsx);
        sbrTextsx.setBounds(0, 260, 400, 346);
        sbrTextsx.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sbrTextsx.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sbrTextsx.setVisible(false);
        add(sbrTextsx);

        /**
         * JTable.
         */
        columnNames = new String[]{"N", "CLAUSE", "WEIGHT"};
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setEnabled(false);
        DefaultTableCellRenderer header = new DefaultTableCellRenderer();
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        header.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel tmodel = table.getColumnModel();
        tmodel.getColumn(0).setHeaderRenderer(header);
        tmodel.getColumn(1).setHeaderRenderer(header);
        tmodel.getColumn(2).setHeaderRenderer(header);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(280);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.setFont(new Font("Helvetica", Font.BOLD, 14));
        scrolldxbottom = new JScrollPane(table);
        scrolldxbottom.setBounds(0, 0, 800, 260);
        scrolldxbottom.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrolldxbottom.setVisible(false);
        add(scrolldxbottom);

        setVisible(true);
        /**
         * Clear variable and other.
         */
        clearAll();
        /**
         * Prepare the graphics.
         */
        Utilities.setGraphics();
        /**
         * Limit for the clauses.
         */
        limit = NUMBER_OF_CLAUSES;
        /**
         * Action for the openFile.
         */
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    brokenFile = false;
                    openFile();
                } catch (IOException ex) {
                } catch (RecognitionException ex) {
                    Utilities.setJOptionPane();
                    JOptionPane.showMessageDialog(null, "INPUT FILE MALFORMED");
                    clearAll();
                }
            }
        });

        /**
         * Action for the new Pick.
         */
        cbMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                use_old = false;
                try {
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    InputStream input = classLoader.getResourceAsStream("/resources/K.png");
                    ImageIcon icon = new ImageIcon(getClass().getResource("/resources/K.png"));

                    Utilities.setJOptionPane();

                    use_k = (Integer.parseInt((String) JOptionPane.showInputDialog(null, "Please, insert the K value", "Request", JOptionPane.QUESTION_MESSAGE, icon, null, null)));
                    cbMenuItem1.setText("Pick Ratio     " + use_k);
                } catch (NumberFormatException ex) {
                    group1.clearSelection();
                }
                if ((cbMenuItem1.isSelected() || cbMenuItem2.isSelected() || cbMenuItem3.isSelected()) && (cbMenuItem4.isSelected() || cbMenuItem5.isSelected()) && (cbMenuItem6.isSelected() || cbMenuItem7.isSelected() || sortingRules.isEmpty())) {
                    runProver.setEnabled(true);
                }
            }
        });

        /**
         * Action for the older clause.
         */
        cbMenuItem2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        use_old = true;
                        use_k = 0;
                        cbMenuItem1.setText("Pick Ratio");
                        if ((cbMenuItem1.isSelected() || cbMenuItem2.isSelected() || cbMenuItem3.isSelected()) && (cbMenuItem4.isSelected() || cbMenuItem5.isSelected()) && (cbMenuItem6.isSelected() || cbMenuItem7.isSelected() || sortingRules.isEmpty())) {
                            if (!brokenFile && file != null) {
                                runProver.setEnabled(true);
                            }
                        }
                    }
                }
        );

        /**
         * Action for the lightest clause.
         */
        cbMenuItem3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        use_old = false;
                        use_k = 0;
                        cbMenuItem1.setText("Pick Ratio");
                        if ((cbMenuItem1.isSelected() || cbMenuItem2.isSelected() || cbMenuItem3.isSelected()) && (cbMenuItem4.isSelected() || cbMenuItem5.isSelected()) && (cbMenuItem6.isSelected() || cbMenuItem7.isSelected() || sortingRules.isEmpty())) {
                            if (!brokenFile && file != null) {
                                runProver.setEnabled(true);
                            }
                        }
                    }
                }
        );

        /**
         * Action for the Otter selection.
         */
        cbMenuItem4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        algorithmChoice = "Otter";
                        if ((cbMenuItem1.isSelected() || cbMenuItem2.isSelected() || cbMenuItem3.isSelected()) && (cbMenuItem4.isSelected() || cbMenuItem5.isSelected()) && (cbMenuItem6.isSelected() || cbMenuItem7.isSelected() || sortingRules.isEmpty())) {
                            if (!brokenFile && file != null) {
                                runProver.setEnabled(true);
                            }
                        }
                    }
                }
        );

        /**
         * Action for the E selection.
         */
        cbMenuItem5.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        algorithmChoice = "E";
                        if ((cbMenuItem1.isSelected() || cbMenuItem2.isSelected() || cbMenuItem3.isSelected()) && (cbMenuItem4.isSelected() || cbMenuItem5.isSelected()) && (cbMenuItem6.isSelected() || cbMenuItem7.isSelected() || sortingRules.isEmpty())) {
                            if (!brokenFile && file != null) {
                                runProver.setEnabled(true);
                            }
                        }
                    }
                }
        );

        /**
         * Action for the Lexicographical selection.
         */
        cbMenuItem6.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        precendeceChoice = "Lexicographical";
                        if ((cbMenuItem1.isSelected() || cbMenuItem2.isSelected() || cbMenuItem3.isSelected()) && (cbMenuItem4.isSelected() || cbMenuItem5.isSelected()) && (cbMenuItem6.isSelected() || cbMenuItem7.isSelected() || sortingRules.isEmpty())) {
                            if (!brokenFile && file != null) {
                                runProver.setEnabled(true);
                            }
                        }
                    }
                }
        );

        /**
         * Action for the Multiset selection.
         */
        cbMenuItem7.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        precendeceChoice = "Multiset";
                        if ((cbMenuItem1.isSelected() || cbMenuItem2.isSelected() || cbMenuItem3.isSelected()) && (cbMenuItem4.isSelected() || cbMenuItem5.isSelected()) && (cbMenuItem6.isSelected() || cbMenuItem7.isSelected() || sortingRules.isEmpty())) {
                            if (!brokenFile && file != null) {
                                runProver.setEnabled(true);
                            }
                        }
                    }
                }
        );

        /**
         * Action for the limit.
         */
        cbMenuItem8.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        if (limitCheck) {
                            limit = NUMBER_OF_CLAUSES;
                            group4.clearSelection();
                            cbMenuItem8.setText("Use Limit");
                        } else {
                            try {
                                Utilities.setJOptionPane();
                                limit = (Integer.parseInt((String) JOptionPane.showInputDialog(null, "Please, insert the limit for the number of clause ")));
                                cbMenuItem8.setText("Use Limit     " + limit);
                                limitCheck = true;
                            } catch (NumberFormatException ex) {
                                group4.clearSelection();
                                limitCheck = false;
                                limit = NUMBER_OF_CLAUSES;
                            }
                            if ((cbMenuItem1.isSelected() || cbMenuItem2.isSelected() || cbMenuItem3.isSelected()) && (cbMenuItem4.isSelected() || cbMenuItem5.isSelected()) && (cbMenuItem6.isSelected() || cbMenuItem7.isSelected() || sortingRules.isEmpty())) {
                                runProver.setEnabled(true);
                            }
                        }
                    }
                }
        );
        /**
         * Action for the runProver.
         */
        runProver.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        if ((cbMenuItem1.isSelected() || cbMenuItem2.isSelected() || cbMenuItem3.isSelected()) && (cbMenuItem4.isSelected() || cbMenuItem5.isSelected()) && (cbMenuItem6.isSelected() || cbMenuItem7.isSelected() || sortingRules.isEmpty()) && !brokenFile) {
                            worker = new ProverThread((TheoremProver) mioFrame, algorithmChoice);
                            stopExecution.setEnabled(true);
                            progressBar.setIndeterminate(true);
                            closeFile.setEnabled(false);
                            cbMenuItem1.setEnabled(false);
                            cbMenuItem2.setEnabled(false);
                            cbMenuItem3.setEnabled(false);
                            cbMenuItem4.setEnabled(false);
                            cbMenuItem5.setEnabled(false);
                            cbMenuItem6.setEnabled(false);
                            cbMenuItem7.setEnabled(false);
                            cbMenuItem8.setEnabled(false);
                            worker.execute();
                        }
                    }
                }
        );

        closeFile.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        openFile.setEnabled(true);
                        clearAll();
                    }
                }
        );
        stopExecution.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        try {
                            timeForEA = (long) ((System.nanoTime() - timeForIA) / Math.pow(10, 6));
                            worker.cancel(true);
                            worker = null;
                        } catch (CancellationException ex) {
                            stopExecution.setEnabled(false);
                            closeFile.setEnabled(true);
                            progressBar.setIndeterminate(false);
                            jtsx.append("Result: Execution canceled by user\n\n");
                            jtsx.append("Execution time: " + timeForEA + "ms");
                        }
                    }
                }
        );
        /**
         * Action for exit.
         */
        exit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e
                    ) {
                        clearAll();
                        System.exit(0);
                    }
                }
        );
    }

    /**
     * Method that open a file with a window.
     *
     * @throws IOException
     * @throws RecognitionException
     */
    private void openFile() throws IOException, RecognitionException {
        final JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(new FileFilter() {
            @Override
            public String getDescription() {
                return "TPF Documents (*.tpf)";
            }

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".tpf");
                }
            }
        });

        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            setTitle("Theorem Prover" + " - " + file.getName());
            try {
                br = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file)));
            } catch (FileNotFoundException ex) {
                Utilities.setJOptionPane();
                JOptionPane.showMessageDialog(null, "PROBLEM TO FIND THE FILE");
            }
        }

        if (file != null) {
            while ((strLine = br.readLine()) != null) {

                switch (strLine) {
                    case "begin(SOS):":
                        section = 1;
                        break;
                    case "begin(CLAUSE):":
                        section = 2;
                        break;
                    case "begin(ORDER):":
                        section = 3;
                        break;
                    case "begin(WEIGHT):":
                        section = 4;
                        break;
                    default:
                        if (section != 0 && section != 3 && section != 4) {
                            Clause cla = new Clause(strLine, countClause++);
                            if (section == 1) {
                                SOS.add(cla);
                            } else if (section == 2) {
                                clauses.add(cla);
                            }
                        } else if (section == 3) {
                            readOrder(strLine);
                        } else if (section == 4) {
                            try {
                                strLine = strLine.replaceAll(" ", "");
                                StringTokenizer s = new StringTokenizer(strLine, ",");
                                Burden x = new Burden(s.nextToken(), Integer.parseInt(s.nextToken()));
                                weights.add(x);
                            } catch (NoSuchElementException ex) {
                                Utilities.setJOptionPane();
                                JOptionPane.showMessageDialog(null, "ERROR IN WEIGHTS FORMAT.\nTHE PROGRAM WILL CONTINUE BY CALCULATING THE WEIGHTS \nBASED ON THE NUMBER OF SYMBOLS FOR CLAUSE. ");
                                weights.clear();
                            }
                        } else if (section != 1 || section != 2 || section != 3 || section != 4) {
                            brokenFile = true;
                            Utilities.setJOptionPane();
                            JOptionPane.showMessageDialog(null, "INPUT FILE MALFORMED");
                            return;
                        }
                }
            }
            if (!weights.isEmpty()) {
                for (Clause forp : SOS) {
                    forp.calculateWeight();
                }
                for (Clause forp : clauses) {
                    forp.calculateWeight();
                }
                jtsx.append("Weight:\n\n");
                for (Burden x : weights){
                    jtsx.append(x.getName() + " , "+x.getWeight()+"\n");
                }
                jtsx.append("\n");
            }
            if (sortingRules.isEmpty()) {
                group3.clearSelection();
                cbMenuItem6.setEnabled(false);
                cbMenuItem7.setEnabled(false);
            } else {
                cbMenuItem6.setEnabled(true);
                cbMenuItem7.setEnabled(true);
                jtsx.append("Sorting Rules:\n\n");
                for (String s : sortingRules) {
                    if (!(sortingRules.indexOf(s) + 1 >= sortingRules.size())) {
                        jtsx.append(s + " > ");
                    } else {
                        jtsx.append(s);
                    }
                }
                jtsx.append("\n\n");
            }

            br.close();

            if (!brokenFile) {
                openFile.setEnabled(false);
                cbMenuItem1.setEnabled(true);
                cbMenuItem2.setEnabled(true);
                cbMenuItem3.setEnabled(true);
                cbMenuItem4.setEnabled(true);
                cbMenuItem5.setEnabled(true);
                cbMenuItem8.setEnabled(true);
                closeFile.setEnabled(true);
                sbrTextdx.setVisible(true);
                sbrTextsx.setVisible(true);
                scrolldxbottom.setVisible(true);
                fillTable();
            }
        }
    }

    /**
     * Main for TheoremProver.
     *
     * @param args to input.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Utilities.setJOptionPane();
            JOptionPane.showMessageDialog(null, "ERROR AT LOOK AND FEEL");
        }
        Runnable init = new Runnable() {
            @Override
            public void run() {
                mioFrame = new TheoremProver();
                mioFrame.setSize(800, 700);
            }
        };
        SwingUtilities.invokeLater(init);
    }

    /**
     * Method readOrder.
     *
     * @param strLine String to be process.
     * @throws RecognitionException
     */
    private void readOrder(String strLine) throws RecognitionException {
        ANTLRStringStream input = new ANTLRStringStream(strLine);
        AntLexer simpleLexer = new AntLexer(input);
        CommonTokenStream token = new CommonTokenStream(simpleLexer);
        AntParser parser = new AntParser(token);
        CommonTree tree = (CommonTree) parser.order().getTree();
        readOrder(tree);
    }

    /**
     * Method readOrder.
     *
     * @param tree CommonTree for the recursion call.
     */
    private void readOrder(CommonTree tree) {
        sortingRules.add(tree.getChild(0).getText());
        if (tree.getChild(1).getType() == AntParser.GT) {
            readOrder((CommonTree) tree.getChild(1));
        } else {
            sortingRules.add(tree.getChild(1).getText());
        }
    }

    /**
     * Method for fill the table into UI.
     */
    private void fillTable() {

        Object[] x = new Object[3];
        x[0] = "";
        x[1] = "CLAUSE";
        x[2] = "";
        model.addRow(x);

        for (Clause c : clauses) {
            Object[] da = new Object[3];
            da[0] = c.getId_Clause();
            da[1] = c.toString();
            da[2] = c.getWeight();
            model.addRow(da);
        }
        x = new Object[3];
        x[0] = "";
        x[1] = "";
        x[2] = "";
        model.addRow(x);

        x = new Object[3];
        x[0] = "";
        x[1] = "SOS";
        x[2] = "";
        model.addRow(x);

        for (Clause c : SOS) {
            Object[] da = new Object[3];
            da[0] = c.getId_Clause();
            da[1] = c.toString();
            da[2] = c.getWeight();
            model.addRow(da);
        }
    }

    /**
     * Method for the clear all variable.
     */
    private void clearAll() {
        section = 0;
        limit = NUMBER_OF_CLAUSES;
        group1.clearSelection();
        group2.clearSelection();
        stopExecution.setEnabled(false);
        group3.clearSelection();
        group4.clearSelection();
        cbMenuItem1.setEnabled(false);
        cbMenuItem2.setEnabled(false);
        cbMenuItem3.setEnabled(false);
        cbMenuItem4.setEnabled(false);
        cbMenuItem5.setEnabled(false);
        cbMenuItem6.setEnabled(false);
        cbMenuItem7.setEnabled(false);
        cbMenuItem8.setEnabled(false);
        runProver.setEnabled(false);
        setTitle("Theorem Prover");
        cbMenuItem8.setText("Use Limit");
        cbMenuItem1.setText("Pick Ratio");
        countClause = 1;
        closeFile.setEnabled(false);
        SOS = new ArrayList<>();
        clauses = new ArrayList<>();
        sortingRules = new ArrayList<>();
        toContract = new ArrayList<>();
        history = new ArrayList<>();
        weights = new ArrayList<>();
        count_ratio = 0;
        use_k = 0;
        jtsx.setText("");
        use_old = false;
        sbrTextsx.setVisible(false);
        sbrTextdx.setVisible(false);
        scrolldxbottom.setVisible(false);
        brokenFile = false;
        eliminated = false;
        jtdx.setText("");
        model.setRowCount(0);
        timeForEA = 0;
        timeForIA = 0;
        strLine = "";
        precendeceChoice = "";
        algorithmChoice = "";
        limitCheck = false;
        cancel = false;
    }

    /**
     * Save proof into file.
     */
    public void saveDialog() {
        JFileChooser chooser = new JFileChooser();
        StringTokenizer s = new StringTokenizer(file.getName(), ".");
        chooser.setSelectedFile(new File(s.nextToken() + "_report"));
        chooser.setCurrentDirectory(new File(file.getPath()));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                Writer fw = new OutputStreamWriter(new FileOutputStream(chooser.getSelectedFile() + ".txt"), UTF8);
                fw.write("FILE: " + file.getName());
                fw.write("\n\n");
                fw.write("Settings:\n");
                fw.write("\n");
                if (cbMenuItem1.isSelected()) {
                    fw.write("Weight: " + cbMenuItem1.getText() + "\n");
                } else if (cbMenuItem2.isSelected()) {
                    fw.write("Weight: " + cbMenuItem2.getText() + "\n");
                } else {
                    fw.write("Weight: " + cbMenuItem3.getText() + "\n");
                }
                if (cbMenuItem4.isSelected()) {
                    fw.write("Algorithm: " + cbMenuItem4.getText() + "\n");
                } else {
                    fw.write("Algorithm: " + cbMenuItem5.getText() + "\n");
                }
                if (cbMenuItem6.isSelected()) {
                    fw.write("Precedence: " + cbMenuItem6.getText() + "extension \n");
                } else if (cbMenuItem7.isSelected()) {
                    fw.write("Precedence: " + cbMenuItem7.getText() + "extension \n");
                }
                if (cbMenuItem8.isSelected()) {
                    fw.write("Limit Clauses: " + cbMenuItem8.getText() + "\n");
                }
                fw.write("Number of clauses generated: " + (countClause - 1) + "\n");
                fw.write("\n");
                fw.write(jtsx.getText());
                fw.write("\n\n\n");
                fw.write(jtdx.getText());
                fw.flush();
                fw.close();
            } catch (IOException ex) {
                Utilities.setJOptionPane();
                JOptionPane.showMessageDialog(null, "PROBLEM TO SAVE THE FILE");
            }
        }
    }
}
