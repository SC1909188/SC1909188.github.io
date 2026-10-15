import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ElementMaster extends JFrame {
    private static final Color NAVY = new Color(10, 16, 35);
    private static final Color NAVY_LIGHT = new Color(20, 31, 62);
    private static final Color CARD = new Color(24, 35, 67);
    private static final Color CARD_LIGHT = new Color(34, 47, 83);
    private static final Color WHITE = new Color(242, 246, 255);
    private static final Color MUTED = new Color(170, 184, 213);
    private static final Color TEAL = new Color(63, 220, 200);
    private static final Color BLUE = new Color(96, 165, 250);
    private static final Color GREEN = new Color(74, 222, 128);
    private static final Color RED = new Color(251, 113, 133);

    private final String[] names = (
        "Hydrogen Helium Lithium Beryllium Boron Carbon Nitrogen Oxygen Fluorine Neon " +
        "Sodium Magnesium Aluminum Silicon Phosphorus Sulfur Chlorine Argon Potassium Calcium " +
        "Scandium Titanium Vanadium Chromium Manganese Iron Cobalt Nickel Copper Zinc " +
        "Gallium Germanium Arsenic Selenium Bromine Krypton Rubidium Strontium Yttrium Zirconium " +
        "Niobium Molybdenum Technetium Ruthenium Rhodium Palladium Silver Cadmium Indium Tin " +
        "Antimony Tellurium Iodine Xenon Cesium Barium Lanthanum Cerium Praseodymium Neodymium " +
        "Promethium Samarium Europium Gadolinium Terbium Dysprosium Holmium Erbium Thulium Ytterbium " +
        "Lutetium Hafnium Tantalum Tungsten Rhenium Osmium Iridium Platinum Gold Mercury " +
        "Thallium Lead Bismuth Polonium Astatine Radon Francium Radium Actinium Thorium " +
        "Protactinium Uranium Neptunium Plutonium Americium Curium Berkelium Californium Einsteinium Fermium " +
        "Mendelevium Nobelium Lawrencium Rutherfordium Dubnium Seaborgium Bohrium Hassium Meitnerium Darmstadtium " +
        "Roentgenium Copernicium Nihonium Flerovium Moscovium Livermorium Tennessine Oganesson"
    ).split(" ");

    private final String[] symbols = (
        "H He Li Be B C N O F Ne Na Mg Al Si P S Cl Ar K Ca " +
        "Sc Ti V Cr Mn Fe Co Ni Cu Zn Ga Ge As Se Br Kr Rb Sr Y Zr " +
        "Nb Mo Tc Ru Rh Pd Ag Cd In Sn Sb Te I Xe Cs Ba La Ce Pr Nd " +
        "Pm Sm Eu Gd Tb Dy Ho Er Tm Yb Lu Hf Ta W Re Os Ir Pt Au Hg " +
        "Tl Pb Bi Po At Rn Fr Ra Ac Th Pa U Np Pu Am Cm Bk Cf Es Fm " +
        "Md No Lr Rf Db Sg Bh Hs Mt Ds Rg Cn Nh Fl Mc Lv Ts Og"
    ).split(" ");

    private final String[] testSets = {
        "Custom range", "Elements 1-10", "Elements 1-20", "Elements 1-32",
        "Elements 1-40", "Elements 2-40", "Elements 41-80", "Elements 81-118", "All 118 elements",
        "Group 1 - Alkali metals", "Group 2 - Alkaline earth metals", "Transition metals",
        "Group 13 - Boron group", "Group 14 - Carbon group", "Group 15 - Nitrogen group",
        "Group 16 - Oxygen group", "Group 17 - Halogens", "Group 18 - Noble gases",
        "Lanthanides", "Actinides", "Metalloids", "Nonmetals", "Gases at room temperature"
    };
    private final String[] questionTypes = {
        "Guess the symbol", "Guess the element from its symbol", "Guess the atomic number", "Mixed quiz"
    };

    private final CardLayout screens = new CardLayout();
    private final JPanel screenHolder = new JPanel(screens);
    private JComboBox<String> setChoice;
    private JComboBox<String> typeChoice;
    private JSpinner startNumber;
    private JSpinner endNumber;
    private JCheckBox shuffleBox;
    private JLabel progressLabel;
    private JLabel scoreLabel;
    private JLabel questionLabel;
    private JLabel feedbackLabel;
    private JTextField answerField;
    private JButton submitButton;
    private ElementTile questionTile;
    private JProgressBar progressBar;
    private JPanel homeScreen;
    private JPanel quizScreen;
    private JPanel resultsScreen;
    private JLabel resultScoreLabel;
    private JLabel resultMessageLabel;

    private List<Integer> quizNumbers = new ArrayList<>();
    private int questionIndex;
    private int score;
    private boolean answered;
    private String actualType;
    private String lastSelectedSet = "Custom range";
    private String lastSelectedType = "Mixed quiz";
    private int lastStart = 1;
    private int lastEnd = 32;
    private final Random random = new Random();

    public ElementMaster() {
        setTitle("Element Master | Periodic Table Lab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 700));
        setSize(1180, 790);
        setLocationRelativeTo(null);
        setContentPane(screenHolder);

        homeScreen = buildHomeScreen();
        quizScreen = buildQuizScreen();
        resultsScreen = buildResultsScreen();
        screenHolder.add(homeScreen, "HOME");
        screenHolder.add(quizScreen, "QUIZ");
        screenHolder.add(resultsScreen, "RESULTS");
        screens.show(screenHolder, "HOME");
    }

    private JPanel buildHomeScreen() {
        GradientPanel root = new GradientPanel(NAVY, new Color(23, 35, 75));
        root.setLayout(new BorderLayout(20, 20));
        root.setBorder(new EmptyBorder(22, 26, 25, 26));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JPanel brand = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        brand.setOpaque(false);
        JLabel logo = new JLabel("✦");
        logo.setFont(new Font("SansSerif", Font.BOLD, 32));
        logo.setForeground(TEAL);
        JPanel brandText = new JPanel();
        brandText.setOpaque(false);
        brandText.setLayout(new BoxLayout(brandText, BoxLayout.Y_AXIS));
        JLabel brandName = label("ELEMENT MASTER", 18, WHITE, Font.BOLD);
        JLabel brandSub = label("PERIODIC TABLE LAB", 10, MUTED, Font.BOLD);
        brandText.add(brandName);
        brandText.add(Box.createVerticalStrut(2));
        brandText.add(brandSub);
        brand.add(logo);
        brand.add(brandText);
        JLabel countPill = pillLabel("118 ELEMENTS  •  ENDLESS PRACTICE", TEAL);
        header.add(brand, BorderLayout.WEST);
        header.add(countPill, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        JPanel columns = new JPanel(new GridLayout(1, 2, 20, 0));
        columns.setOpaque(false);
        columns.add(buildSetupCard());
        columns.add(buildTableCard());
        root.add(columns, BorderLayout.CENTER);

        JLabel footer = label("LEARN A LITTLE. REMEMBER A LOT.", 10, MUTED, Font.BOLD);
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        root.add(footer, BorderLayout.SOUTH);
        return root;
    }

    private JPanel buildSetupCard() {
        CardPanel card = new CardPanel(CARD, 24);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(25, 25, 24, 25));

        card.add(label("Your next challenge", 25, WHITE, Font.BOLD));
        card.add(Box.createVerticalStrut(7));
        card.add(wrappedLabel("Pick a topic, set your range, and build your periodic-table memory.", 13, MUTED));
        card.add(Box.createVerticalStrut(24));

        card.add(label("TEST COLLECTION", 10, TEAL, Font.BOLD));
        card.add(Box.createVerticalStrut(7));
        setChoice = new JComboBox<>(testSets);
        styleCombo(setChoice);
        card.add(setChoice);
        card.add(Box.createVerticalStrut(17));

        card.add(label("QUESTION STYLE", 10, TEAL, Font.BOLD));
        card.add(Box.createVerticalStrut(7));
        typeChoice = new JComboBox<>(questionTypes);
        typeChoice.setSelectedItem("Mixed quiz");
        styleCombo(typeChoice);
        card.add(typeChoice);
        card.add(Box.createVerticalStrut(19));

        JPanel rangeTitle = new JPanel(new BorderLayout());
        rangeTitle.setOpaque(false);
        rangeTitle.add(label("CUSTOM ATOMIC NUMBER RANGE", 10, TEAL, Font.BOLD), BorderLayout.WEST);
        rangeTitle.add(label("1–118", 10, MUTED, Font.PLAIN), BorderLayout.EAST);
        card.add(rangeTitle);
        card.add(Box.createVerticalStrut(8));

        JPanel rangePanel = new JPanel(new GridLayout(1, 2, 12, 0));
        rangePanel.setOpaque(false);
        startNumber = new JSpinner(new SpinnerNumberModel(1, 1, 118, 1));
        endNumber = new JSpinner(new SpinnerNumberModel(32, 1, 118, 1));
        rangePanel.add(spinnerCard("FROM", startNumber));
        rangePanel.add(spinnerCard("TO", endNumber));
        card.add(rangePanel);
        card.add(Box.createVerticalStrut(12));

        shuffleBox = new JCheckBox("Shuffle questions for a fresh challenge");
        shuffleBox.setSelected(true);
        shuffleBox.setOpaque(false);
        shuffleBox.setForeground(MUTED);
        shuffleBox.setFont(new Font("SansSerif", Font.PLAIN, 12));
        shuffleBox.setFocusPainted(false);
        shuffleBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(shuffleBox);
        card.add(Box.createVerticalStrut(20));

        JButton startButton = actionButton("START LEARNING  ➊", TEAL, NAVY);
        startButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        startButton.addActionListener(e -> startQuiz());
        card.add(startButton);
        card.add(Box.createVerticalStrut(11));
        JLabel hint = wrappedLabel("Tip: choose Custom range to practice exactly 1–32, 10–25, or any range you need.", 11, MUTED);
        card.add(hint);
        return card;
    }

    private JPanel spinnerCard(String caption, JSpinner spinner) {
        CardPanel panel = new CardPanel(CARD_LIGHT, 12);
        panel.setLayout(new BorderLayout(0, 5));
        panel.setBorder(new EmptyBorder(9, 12, 9, 12));
        panel.add(label(caption, 9, MUTED, Font.BOLD), BorderLayout.NORTH);
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField field = ((JSpinner.DefaultEditor) editor).getTextField();
            field.setFont(new Font("SansSerif", Font.BOLD, 20));
            field.setForeground(WHITE);
            field.setBackground(CARD_LIGHT);
            field.setCaretColor(TEAL);
            field.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        }
        spinner.setOpaque(false);
        spinner.setBorder(BorderFactory.createEmptyBorder());
        panel.add(spinner, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
        return panel;
    }

    private JPanel buildTableCard() {
        CardPanel card = new CardPanel(CARD, 24);
        card.setLayout(new BorderLayout(0, 14));
        card.setBorder(new EmptyBorder(24, 22, 22, 22));

        JPanel heading = new JPanel(new BorderLayout());
        heading.setOpaque(false);
        JPanel headingText = new JPanel();
        headingText.setOpaque(false);
        headingText.setLayout(new BoxLayout(headingText, BoxLayout.Y_AXIS));
        headingText.add(label("Explore the elements", 21, WHITE, Font.BOLD));
        headingText.add(Box.createVerticalStrut(5));
        headingText.add(label("118 building blocks. One smarter you.", 12, MUTED, Font.PLAIN));
        heading.add(headingText, BorderLayout.WEST);
        JLabel periodicIcon = new JLabel("▦");
        periodicIcon.setForeground(TEAL);
        periodicIcon.setFont(new Font("SansSerif", Font.BOLD, 32));
        heading.add(periodicIcon, BorderLayout.EAST);
        card.add(heading, BorderLayout.NORTH);

        JPanel tableWrap = new JPanel(new BorderLayout(0, 8));
        tableWrap.setOpaque(false);
        tableWrap.add(buildPeriodicTablePreview(), BorderLayout.CENTER);
        JLabel note = label("A mini periodic table — colored by family", 10, MUTED, Font.PLAIN);
        tableWrap.add(note, BorderLayout.SOUTH);
        card.add(tableWrap, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.add(label("QUICK FACTS", 10, TEAL, Font.BOLD));
        bottom.add(Box.createVerticalStrut(8));
        JPanel facts = new JPanel(new GridLayout(1, 3, 8, 0));
        facts.setOpaque(false);
        facts.add(factCard("118", "known elements", BLUE));
        facts.add(factCard("18", "periodic groups", new Color(196, 181, 253)));
        facts.add(factCard("∞", "ways to practice", TEAL));
        bottom.add(facts);
        bottom.add(Box.createVerticalStrut(13));
        bottom.add(wrappedLabel("Includes element families, gases, metalloids, custom ranges, and mixed question styles.", 11, MUTED));
        card.add(bottom, BorderLayout.SOUTH);
        return card;
    }

    private JPanel factCard(String number, String caption, Color color) {
        CardPanel panel = new CardPanel(CARD_LIGHT, 12);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 9, 10));
        JLabel n = label(number, 21, color, Font.BOLD);
        n.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel c = label(caption, 9, MUTED, Font.PLAIN);
        c.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(n);
        panel.add(Box.createVerticalStrut(3));
        panel.add(c);
        return panel;
    }

    private JPanel buildPeriodicTablePreview() {
        JPanel table = new JPanel(new GridLayout(9, 18, 3, 3));
        table.setOpaque(false);
        table.setPreferredSize(new Dimension(560, 300));
        int[][] positions = new int[9][18];
        for (int z = 1; z <= 118; z++) {
            int[] pos = positionFor(z);
            positions[pos[0]][pos[1]] = z;
        }
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 18; col++) {
                int z = positions[row][col];
                if (z == 0) {
                    JPanel blank = new JPanel();
                    blank.setOpaque(false);
                    table.add(blank);
                } else {
                    table.add(new MiniTile(z));
                }
            }
        }
        return table;
    }

    private int[] positionFor(int z) {
        if (z == 1) return new int[]{0, 0};
        if (z == 2) return new int[]{0, 17};
        if (z >= 3 && z <= 4) return new int[]{1, z - 3};
        if (z >= 5 && z <= 10) return new int[]{1, z + 7};
        if (z >= 11 && z <= 12) return new int[]{2, z - 11};
        if (z >= 13 && z <= 18) return new int[]{2, z - 1};
        if (z >= 19 && z <= 36) return new int[]{3, z - 19};
        if (z >= 37 && z <= 54) return new int[]{4, z - 37};
        if (z >= 55 && z <= 57) return new int[]{5, z - 55};
        if (z >= 58 && z <= 71) return new int[]{7, z - 58};
        if (z >= 72 && z <= 86) return new int[]{5, z - 69};
        if (z >= 87 && z <= 89) return new int[]{6, z - 87};
        if (z >= 90 && z <= 103) return new int[]{8, z - 90};
        return new int[]{6, z - 101};
    }

    private Color familyColor(int z) {
        if (z == 2 || z == 10 || z == 18 || z == 36 || z == 54 || z == 86 || z == 118) return new Color(64, 180, 133);
        if (z == 3 || z == 11 || z == 19 || z == 37 || z == 55 || z == 87) return new Color(229, 112, 125);
        if (z == 4 || z == 12 || z == 20 || z == 38 || z == 56 || z == 88) return new Color(231, 163, 76);
        if (z == 9 || z == 17 || z == 35 || z == 53 || z == 85 || z == 117) return new Color(214, 181, 79);
        if (z == 5 || z == 14 || z == 32 || z == 33 || z == 51 || z == 52 || z == 84) return new Color(164, 126, 230);
        if (z >= 57 && z <= 71) return new Color(76, 169, 205);
        if (z >= 89 && z <= 103) return new Color(155, 120, 217);
        if (isTransitionMetal(z)) return new Color(80, 133, 218);
        if (z == 1 || z == 6 || z == 7 || z == 8 || z == 15 || z == 16 || z == 34) return new Color(67, 183, 185);
        return new Color(100, 125, 163);
    }

    private boolean isTransitionMetal(int z) {
        return (z >= 21 && z <= 30) || (z >= 39 && z <= 48) || (z >= 72 && z <= 80) || (z >= 104 && z <= 112);
    }

    private JPanel buildQuizScreen() {
        GradientPanel root = new GradientPanel(NAVY, new Color(23, 35, 75));
        root.setLayout(new BorderLayout(18, 18));
        root.setBorder(new EmptyBorder(22, 28, 26, 28));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JPanel brand = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        brand.setOpaque(false);
        JLabel logo = label("✦", 28, TEAL, Font.BOLD);
        JLabel title = label("ELEMENT MASTER", 17, WHITE, Font.BOLD);
        brand.add(logo);
        brand.add(title);
        progressLabel = label("QUESTION 01 / 32", 12, MUTED, Font.BOLD);
        header.add(brand, BorderLayout.WEST);
        header.add(progressLabel, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        CardPanel card = new CardPanel(CARD, 24);
        card.setLayout(new BorderLayout(18, 18));
        card.setBorder(new EmptyBorder(24, 28, 24, 28));

        JPanel top = new JPanel(new BorderLayout(0, 9));
        top.setOpaque(false);
        JLabel eyebrow = label("YOUR QUESTION", 10, TEAL, Font.BOLD);
        top.add(eyebrow, BorderLayout.NORTH);
        questionLabel = label("What is the atomic number?", 25, WHITE, Font.BOLD);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        top.add(questionLabel, BorderLayout.CENTER);
        card.add(top, BorderLayout.NORTH);

        JPanel middle = new JPanel(new GridBagLayout());
        middle.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 18, 0);
        questionTile = new ElementTile();
        questionTile.setPreferredSize(new Dimension(220, 220));
        middle.add(questionTile, gbc);
        gbc.gridy++;
        answerField = new JTextField();
        answerField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        answerField.setForeground(WHITE);
        answerField.setBackground(CARD_LIGHT);
        answerField.setCaretColor(TEAL);
        answerField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(64, 83, 127), 1, true),
            new EmptyBorder(12, 15, 12, 15)));
        answerField.setPreferredSize(new Dimension(380, 48));
        answerField.addActionListener(e -> submitOrNext());
        middle.add(answerField, gbc);
        gbc.gridy++;
        feedbackLabel = label("Take your time — you've got this.", 13, MUTED, Font.PLAIN);
        middle.add(feedbackLabel, gbc);
        card.add(middle, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setOpaque(false);
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setBorderPainted(false);
        progressBar.setForeground(TEAL);
        progressBar.setBackground(CARD_LIGHT);
        progressBar.setPreferredSize(new Dimension(100, 8));
        progressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 8));
        south.add(progressBar);
        south.add(Box.createVerticalStrut(16));
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        buttonRow.setOpaque(false);
        submitButton = actionButton("CHECK ANSWER", TEAL, NAVY);
        submitButton.setPreferredSize(new Dimension(190, 45));
        submitButton.addActionListener(e -> submitOrNext());
        JButton menuButton = actionButton("←  MAIN MENU", CARD_LIGHT, WHITE);
        menuButton.setPreferredSize(new Dimension(170, 45));
        menuButton.addActionListener(e -> screens.show(screenHolder, "HOME"));
        buttonRow.add(submitButton);
        buttonRow.add(menuButton);
        south.add(buttonRow);
        card.add(south, BorderLayout.SOUTH);

        scoreLabel = label("SCORE  0", 12, TEAL, Font.BOLD);
        JPanel bottomBar = new JPanel(new BorderLayout());
        bottomBar.setOpaque(false);
        bottomBar.add(label("FOCUS MODE  •  ONE QUESTION AT A TIME", 10, MUTED, Font.BOLD), BorderLayout.WEST);
        bottomBar.add(scoreLabel, BorderLayout.EAST);

        root.add(card, BorderLayout.CENTER);
        root.add(bottomBar, BorderLayout.SOUTH);
        return root;
    }

    private JPanel buildResultsScreen() {
        GradientPanel root = new GradientPanel(NAVY, new Color(23, 35, 75));
        root.setLayout(new GridBagLayout());
        CardPanel card = new CardPanel(CARD, 26);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(35, 48, 35, 48));
        card.setPreferredSize(new Dimension(520, 420));

        JLabel icon = label("✦", 48, TEAL, Font.BOLD);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel title = label("QUIZ COMPLETE", 27, WHITE, Font.BOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultScoreLabel = label("0 / 0", 40, TEAL, Font.BOLD);
        resultScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultMessageLabel = label("Nice work! Every question is practice.", 13, MUTED, Font.PLAIN);
        resultMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(icon);
        card.add(Box.createVerticalStrut(8));
        card.add(title);
        card.add(Box.createVerticalStrut(18));
        card.add(resultScoreLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(resultMessageLabel);
        card.add(Box.createVerticalStrut(28));

        JButton retry = actionButton("PLAY AGAIN", TEAL, NAVY);
        retry.setAlignmentX(Component.CENTER_ALIGNMENT);
        retry.setMaximumSize(new Dimension(280, 46));
        retry.addActionListener(e -> startQuiz());
        JButton home = actionButton("CHOOSE ANOTHER TEST", CARD_LIGHT, WHITE);
        home.setAlignmentX(Component.CENTER_ALIGNMENT);
        home.setMaximumSize(new Dimension(280, 46));
        home.addActionListener(e -> screens.show(screenHolder, "HOME"));
        card.add(retry);
        card.add(Box.createVerticalStrut(10));
        card.add(home);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        root.add(card, gbc);
        return root;
    }

    private void startQuiz() {
        String selectedSet = (String) setChoice.getSelectedItem();
        String selectedType = (String) typeChoice.getSelectedItem();
        int start = (Integer) startNumber.getValue();
        int end = (Integer) endNumber.getValue();

        if ("Custom range".equals(selectedSet) && start > end) {
            JOptionPane.showMessageDialog(this, "The starting number must be less than or equal to the ending number.", "Check your range", JOptionPane.WARNING_MESSAGE);
            return;
        }

        quizNumbers = getNumbersForSet(selectedSet, start, end);
        if (quizNumbers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no elements in that test.", "No questions", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (shuffleBox.isSelected()) Collections.shuffle(quizNumbers);
        lastSelectedSet = selectedSet;
        lastSelectedType = selectedType;
        lastStart = start;
        lastEnd = end;
        questionIndex = 0;
        score = 0;
        showQuestion();
        screens.show(screenHolder, "QUIZ");
    }

    private List<Integer> range(int first, int last) {
        List<Integer> result = new ArrayList<>();
        for (int n = first; n <= last; n++) result.add(n);
        return result;
    }

    private List<Integer> fromArray(int... values) {
        List<Integer> result = new ArrayList<>();
        for (int value : values) result.add(value);
        return result;
    }

    private List<Integer> getNumbersForSet(String set, int start, int end) {
        if (set == null) return new ArrayList<>();
        switch (set) {
            case "Custom range": return range(start, end);
            case "Elements 1-10": return range(1, 10);
            case "Elements 1-20": return range(1, 20);
            case "Elements 1-32": return range(1, 32);
            case "Elements 1-40": return range(1, 40);
            case "Elements 2-40": return range(2, 40);
            case "Elements 41-80": return range(41, 80);
            case "Elements 81-118": return range(81, 118);
            case "All 118 elements": return range(1, 118);
            case "Group 1 - Alkali metals": return fromArray(3, 11, 19, 37, 55, 87);
            case "Group 2 - Alkaline earth metals": return fromArray(4, 12, 20, 38, 56, 88);
            case "Transition metals": return fromArray(21,22,23,24,25,26,27,28,29,30,39,40,41,42,43,44,45,46,47,48,72,73,74,75,76,77,78,79,80,104,105,106,107,108,109,110,111,112);
            case "Group 13 - Boron group": return fromArray(5, 13, 31, 49, 81, 113);
            case "Group 14 - Carbon group": return fromArray(6, 14, 32, 50, 82, 114);
            case "Group 15 - Nitrogen group": return fromArray(7, 15, 33, 51, 83, 115);
            case "Group 16 - Oxygen group": return fromArray(8, 16, 34, 52, 84, 116);
            case "Group 17 - Halogens": return fromArray(9, 17, 35, 53, 85, 117);
            case "Group 18 - Noble gases": return fromArray(2, 10, 18, 36, 54, 86, 118);
            case "Lanthanides": return range(57, 71);
            case "Actinides": return range(89, 103);
            case "Metalloids": return fromArray(5, 14, 32, 33, 51, 52, 84);
            case "Nonmetals": return fromArray(1, 2, 6, 7, 8, 9, 10, 15, 16, 17, 18, 34, 35, 36, 53, 54, 85, 86, 118);
            case "Gases at room temperature": return fromArray(1, 2, 7, 8, 9, 10, 17, 18, 36, 54, 86);
            default: return range(1, 20);
        }
    }

    private void showQuestion() {
        answered = false;
        answerField.setText("");
        answerField.setEnabled(true);
        submitButton.setText("CHECK ANSWER");
        submitButton.setBackground(TEAL);
        feedbackLabel.setText("Take your time — you've got this.");
        feedbackLabel.setForeground(MUTED);

        int atomicNumber = quizNumbers.get(questionIndex);
        String selectedType = lastSelectedType;
        if ("Mixed quiz".equals(selectedType)) {
            String[] choices = {"Guess the symbol", "Guess the element from its symbol", "Guess the atomic number"};
            actualType = choices[random.nextInt(choices.length)];
        } else {
            actualType = selectedType;
        }

        String name = names[atomicNumber - 1];
        String symbol = symbols[atomicNumber - 1];
        if ("Guess the symbol".equals(actualType)) {
            questionLabel.setText("What is the chemical symbol for " + name + "?");
            questionTile.setElement(String.valueOf(atomicNumber), "?", name.toUpperCase(), "symbol");
        } else if ("Guess the element from its symbol".equals(actualType)) {
            questionLabel.setText("Which element is represented by this symbol?");
            questionTile.setElement(String.valueOf(atomicNumber), symbol, "NAME THE ELEMENT", "name");
        } else {
            questionLabel.setText("What is the atomic number of " + name + "?");
            questionTile.setElement("PERIODIC TILE", symbol, name.toUpperCase(), "number");
        }

        progressLabel.setText(String.format("QUESTION %02d / %02d", questionIndex + 1, quizNumbers.size()));
        scoreLabel.setText("SCORE  " + score);
        progressBar.setValue((int) (questionIndex * 100.0 / quizNumbers.size()));
        screens.show(screenHolder, "QUIZ");
        answerField.requestFocusInWindow();
    }

    private String getCorrectAnswer(int atomicNumber) {
        if ("Guess the symbol".equals(actualType)) return symbols[atomicNumber - 1];
        if ("Guess the element from its symbol".equals(actualType)) return names[atomicNumber - 1];
        return String.valueOf(atomicNumber);
    }

    private void submitOrNext() {
        if (answered) {
            questionIndex++;
            if (questionIndex >= quizNumbers.size()) finishQuiz();
            else showQuestion();
            return;
        }

        String answer = answerField.getText().trim();
        if (answer.isEmpty()) {
            feedbackLabel.setText("Type an answer before checking.");
            feedbackLabel.setForeground(RED);
            return;
        }

        int atomicNumber = quizNumbers.get(questionIndex);
        String correctAnswer = getCorrectAnswer(atomicNumber);
        if (answer.equalsIgnoreCase(correctAnswer)) {
            score++;
            feedbackLabel.setText("✓  Correct! Nice work.");
            feedbackLabel.setForeground(GREEN);
        } else {
            feedbackLabel.setText("Not quite — the answer is " + correctAnswer + ".");
            feedbackLabel.setForeground(RED);
        }
        answered = true;
        answerField.setEnabled(false);
        submitButton.setText(questionIndex == quizNumbers.size() - 1 ? "SEE MY RESULTS  →" : "NEXT QUESTION  →");
        submitButton.setBackground(BLUE);
        scoreLabel.setText("SCORE  " + score);
        progressBar.setValue((int) ((questionIndex + 1) * 100.0 / quizNumbers.size()));
    }

    private void finishQuiz() {
        int total = quizNumbers.size();
        double percent = total == 0 ? 0 : score * 100.0 / total;
        resultScoreLabel.setText(score + " / " + total);
        resultMessageLabel.setText(String.format("%.0f%% correct  •  %s", percent,
            percent >= 90 ? "Amazing work!" : percent >= 70 ? "Great progress!" : "Keep practicing — you're building it."));
        screens.show(screenHolder, "RESULTS");
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        combo.setForeground(WHITE);
        combo.setBackground(CARD_LIGHT);
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        combo.setPreferredSize(new Dimension(300, 42));
        combo.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
    }

    private JButton actionButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(foreground);
        button.setBackground(background);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JLabel label(String text, int size, Color color, int style) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setForeground(color);
        return label;
    }

    private JLabel wrappedLabel(String text, int size, Color color) {
        JLabel label = new JLabel("<html><div style='width:100%;'>" + text + "</div></html>");
        label.setFont(new Font("SansSerif", Font.PLAIN, size));
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel pillLabel(String text, Color color) {
        JLabel pill = new JLabel("  " + text + "  ");
        pill.setFont(new Font("SansSerif", Font.BOLD, 10));
        pill.setForeground(color);
        pill.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(color.getRed(), color.getGreen(), color.getBlue(), 130), 1, true),
            new EmptyBorder(8, 10, 8, 10)));
        return pill;
    }

    private class MiniTile extends JPanel {
        MiniTile(int atomicNumber) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(familyColor(atomicNumber));
            setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 60), 1));
            setToolTipText(atomicNumber + " — " + names[atomicNumber - 1] + " (" + symbols[atomicNumber - 1] + ")");
            JLabel number = new JLabel(String.valueOf(atomicNumber));
            number.setFont(new Font("SansSerif", Font.PLAIN, 7));
            number.setForeground(new Color(255, 255, 255, 220));
            number.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel symbol = new JLabel(symbols[atomicNumber - 1]);
            symbol.setFont(new Font("SansSerif", Font.BOLD, 10));
            symbol.setForeground(Color.WHITE);
            symbol.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(Box.createVerticalGlue());
            add(number);
            add(symbol);
            add(Box.createVerticalGlue());
        }
    }

    private class ElementTile extends JPanel {
        private final JLabel topLabel = new JLabel("PERIODIC TILE", SwingConstants.CENTER);
        private final JLabel symbolLabel = new JLabel("F", SwingConstants.CENTER);
        private final JLabel nameLabel = new JLabel("FLUORINE", SwingConstants.CENTER);
        private String mode = "number";

        ElementTile() {
            setOpaque(false);
            setLayout(new BorderLayout(4, 4));
            setBorder(new EmptyBorder(18, 18, 18, 18));
            topLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
            topLabel.setForeground(TEAL);
            symbolLabel.setFont(new Font("SansSerif", Font.BOLD, 64));
            symbolLabel.setForeground(WHITE);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
            nameLabel.setForeground(MUTED);
            add(topLabel, BorderLayout.NORTH);
            add(symbolLabel, BorderLayout.CENTER);
            add(nameLabel, BorderLayout.SOUTH);
        }

        void setElement(String top, String symbol, String name, String displayMode) {
            topLabel.setText(top);
            symbolLabel.setText(symbol);
            nameLabel.setText(name);
            mode = displayMode;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gradient = new GradientPaint(0, 0, new Color(44, 65, 112), getWidth(), getHeight(), new Color(23, 38, 76));
            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 24, 24);
            g2.setColor(new Color(63, 220, 200, 130));
            g2.setStroke(new BasicStroke(1.4f));
            g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 24, 24);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class CardPanel extends JPanel {
        private final Color fill;
        private final int arc;
        CardPanel(Color fill, int arc) {
            this.fill = fill;
            this.arc = arc;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class GradientPanel extends JPanel {
        private final Color first;
        private final Color second;
        GradientPanel(Color first, Color second) {
            this.first = first;
            this.second = second;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setPaint(new GradientPaint(0, 0, first, getWidth(), getHeight(), second));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // The default Swing look and feel will be used if this fails.
            }
            ElementMaster app = new ElementMaster();
            app.setVisible(true);
        });
    }
}
