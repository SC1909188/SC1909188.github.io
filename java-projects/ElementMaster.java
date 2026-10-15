import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ElementMaster extends JFrame {
    private final String[] names = ("Hydrogen Helium Lithium Beryllium Boron Carbon Nitrogen Oxygen Fluorine Neon " +
        "Sodium Magnesium Aluminum Silicon Phosphorus Sulfur Chlorine Argon Potassium Calcium " +
        "Scandium Titanium Vanadium Chromium Manganese Iron Cobalt Nickel Copper Zinc " +
        "Gallium Germanium Arsenic Selenium Bromine Krypton Rubidium Strontium Yttrium Zirconium " +
        "Niobium Molybdenum Technetium Ruthenium Rhodium Palladium Silver Cadmium Indium Tin " +
        "Antimony Tellurium Iodine Xenon Cesium Barium Lanthanum Cerium Praseodymium Neodymium " +
        "Promethium Samarium Europium Gadolinium Terbium Dysprosium Holmium Erbium Thulium Ytterbium " +
        "Lutetium Hafnium Tantalum Tungsten Rhenium Osmium Iridium Platinum Gold Mercury " +
        "Thallium Lead Bismuth Polonium Astatine Radon Francium Radium Actinium Thorium " +
        "Protactinium Uranium Neptunium Plutonium Americium Curium Berkelium Californium Einsteinium Fermium " +
        "Mendelevium Nobelium Lawrencium Rutherfordium Dubnium Seaborgium Bohrium Hassium Meitnerium Darmstadtium Roentgenium Copernicium Nihonium Flerovium Moscovium Livermorium Tennessine Oganesson").split(" ");
    private final String[] symbols = ("H He Li Be B C N O F Ne Na Mg Al Si P S Cl Ar K Ca " +
        "Sc Ti V Cr Mn Fe Co Ni Cu Zn Ga Ge As Se Br Kr Rb Sr Y Zr Nb Mo Tc Ru Rh Pd Ag Cd In Sn " +
        "Sb Te I Xe Cs Ba La Ce Pr Nd Pm Sm Eu Gd Tb Dy Ho Er Tm Yb Lu Hf Ta W Re Os Ir Pt Au Hg " +
        "Tl Pb Bi Po At Rn Fr Ra Ac Th Pa U Np Pu Am Cm Bk Cf Es Fm Md No Lr Rf Db Sg Bh Hs Mt Ds Rg Cn " +
        "Nh Fl Mc Lv Ts Og").split(" ");

    private final String[] testSets = {
        "Custom range", "Elements 1-20", "Elements 1-40", "Elements 2-40", "All 118 elements",
        "Group 1 - Alkali metals", "Group 2 - Alkaline earth metals", "Transition metals",
        "Group 13 - Boron group", "Group 14 - Carbon group", "Group 15 - Nitrogen group",
        "Group 16 - Oxygen group", "Group 17 - Halogens", "Group 18 - Noble gases",
        "Lanthanides", "Actinides", "Metalloids", "Nonmetals", "Gases at room temperature"
    };
    private final String[] questionTypes = {
        "Guess the symbol", "Guess the element from its symbol", "Guess the atomic number", "Mixed quiz"
    };
    private JComboBox<String> setChoice;
    private JComboBox<String> typeChoice;
    private JSpinner startNumber;
    private JSpinner endNumber;
    private JCheckBox shuffleBox;
    private JPanel mainPanel;
    private JPanel quizPanel;
    private JLabel questionLabel;
    private JLabel progressLabel;
    private JLabel feedbackLabel;
    private JTextField answerField;
    private JButton submitButton;
    private List<Integer> quizNumbers;
    private int questionIndex, score;
    private boolean answered;
    private String actualType;
    private final Random random = new Random();

    public ElementMaster() {
        setTitle("Element Master");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 560);
        setLocationRelativeTo(null);
        buildMenu();
        showMenu();
    }

    private void buildMenu() {
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("ELEMENT MASTER");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel subtitle = new JLabel("Periodic Table Training Center");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(subtitle);
        mainPanel.add(Box.createVerticalStrut(28));

        mainPanel.add(new JLabel("Choose a group or element range:"));
        setChoice = new JComboBox<>(testSets);
        setChoice.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        mainPanel.add(setChoice);
        mainPanel.add(Box.createVerticalStrut(16));

        mainPanel.add(new JLabel("Choose the question type:"));
        typeChoice = new JComboBox<>(questionTypes);
        typeChoice.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        mainPanel.add(typeChoice);
        mainPanel.add(Box.createVerticalStrut(16));

        JPanel rangePanel = new JPanel(new GridLayout(2, 2, 12, 6));
        rangePanel.add(new JLabel("Custom starting atomic number (1-118):"));
        rangePanel.add(new JLabel("Custom ending atomic number (1-118):"));
        startNumber = new JSpinner(new SpinnerNumberModel(1, 1, 118, 1));
        endNumber = new JSpinner(new SpinnerNumberModel(32, 1, 118, 1));
        rangePanel.add(startNumber);
        rangePanel.add(endNumber);
        mainPanel.add(rangePanel);
        mainPanel.add(Box.createVerticalStrut(10));

        shuffleBox = new JCheckBox("Shuffle the questions", true);
        shuffleBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(shuffleBox);
        mainPanel.add(Box.createVerticalStrut(18));

        JButton startButton = new JButton("Start Quiz");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> startQuiz());
        mainPanel.add(startButton);

        JLabel note = new JLabel("Custom range example: choose Custom range, then enter 1 and 32.");
        note.setFont(new Font("Arial", Font.ITALIC, 12));
        note.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(16));
        mainPanel.add(note);
    }

    private void showMenu() {
        setContentPane(mainPanel);
        revalidate();
        repaint();
    }

    private void startQuiz() {
        String selectedSet = (String) setChoice.getSelectedItem();
        int start = (Integer) startNumber.getValue();
        int end = (Integer) endNumber.getValue();

        if ("Custom range".equals(selectedSet) && start > end) {
            JOptionPane.showMessageDialog(this, "The starting number must be smaller than or equal to the ending number.");
            return;
        }

        quizNumbers = getNumbersForSet(selectedSet, start, end);
        if (quizNumbers == null || quizNumbers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no elements in that test.");
            return;
        }
        if (shuffleBox.isSelected()) {
            Collections.shuffle(quizNumbers);
        }
        questionIndex = 0;
        score = 0;
        answered = false;
        buildQuizScreen();
        showQuestion();
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
            case "Elements 1-20": return range(1, 20);
            case "Elements 1-40": return range(1, 40);
            case "Elements 2-40": return range(2, 40);
            case "All 118 elements": return range(1, 118);
            case "Group 1 - Alkali metals": return fromArray(3, 11, 19, 37, 55, 87);
            case "Group 2 - Alkaline earth metals": return fromArray(4, 12, 20, 38, 56, 88);
            case "Transition metals": return fromArray(
                21,22,23,24,25,26,27,28,29,30, 39,40,41,42,43,44,45,46,47,48,
                72,73,74,75,76,77,78,79,80, 104,105,106,107,108,109,110,111,112);
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

    private void buildQuizScreen() {
        quizPanel = new JPanel(new BorderLayout(15, 15));
        quizPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        progressLabel = new JLabel();
        progressLabel.setFont(new Font("Arial", Font.BOLD, 15));
        quizPanel.add(progressLabel, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        questionLabel = new JLabel("Question goes here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(Box.createVerticalGlue());
        center.add(questionLabel);
        center.add(Box.createVerticalStrut(25));

        answerField = new JTextField();
        answerField.setFont(new Font("Arial", Font.PLAIN, 20));
        answerField.setMaximumSize(new Dimension(350, 42));
        answerField.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(answerField);
        center.add(Box.createVerticalStrut(18));

        feedbackLabel = new JLabel(" ");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 16));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(feedbackLabel);
        center.add(Box.createVerticalGlue());
        quizPanel.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        submitButton = new JButton("Check Answer");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.addActionListener(e -> submitOrNext());
        answerField.addActionListener(e -> submitOrNext());
        JButton menuButton = new JButton("Return to Menu");
        menuButton.addActionListener(e -> showMenu());
        bottom.add(submitButton);
        bottom.add(menuButton);
        quizPanel.add(bottom, BorderLayout.SOUTH);
    }

    private void showQuestion() {
        answered = false;
        answerField.setText("");
        answerField.setEnabled(true);
        submitButton.setText("Check Answer");
        feedbackLabel.setText(" ");
        feedbackLabel.setForeground(Color.BLACK);

        int atomicNumber = quizNumbers.get(questionIndex);
        String selectedType = (String) typeChoice.getSelectedItem();
        if ("Mixed quiz".equals(selectedType)) {
            String[] choices = {"Guess the symbol", "Guess the element from its symbol", "Guess the atomic number"};
            actualType = choices[random.nextInt(choices.length)];
        } else {
            actualType = selectedType;
        }

        if ("Guess the symbol".equals(actualType)) {
            questionLabel.setText("What is the symbol for " + names[atomicNumber - 1] + "?");
        } else if ("Guess the element from its symbol".equals(actualType)) {
            questionLabel.setText("Which element has the symbol " + symbols[atomicNumber - 1] + "?");
        } else {
            questionLabel.setText("What is the atomic number of " + names[atomicNumber - 1] + "?");
        }
        progressLabel.setText("Question " + (questionIndex + 1) + " of " + quizNumbers.size() + "     |     Score: " + score);
        setContentPane(quizPanel);
        revalidate();
        repaint();
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
            if (questionIndex >= quizNumbers.size()) {
                finishQuiz();
            } else {
                showQuestion();
            }
            return;
        }

        String answer = answerField.getText().trim();
        if (answer.isEmpty()) {
            feedbackLabel.setText("Type an answer first.");
            feedbackLabel.setForeground(Color.RED);
            return;
        }

        int atomicNumber = quizNumbers.get(questionIndex);
        String correctAnswer = getCorrectAnswer(atomicNumber);
        if (answer.equalsIgnoreCase(correctAnswer)) {
            score++;
            feedbackLabel.setText("Correct!");
            feedbackLabel.setForeground(new Color(0, 130, 40));
        } else {
            feedbackLabel.setText("Not quite. Correct answer: " + correctAnswer);
            feedbackLabel.setForeground(Color.RED);
        }
        answered = true;
        answerField.setEnabled(false);
        submitButton.setText(questionIndex == quizNumbers.size() - 1 ? "Finish Quiz" : "Next Question");
        progressLabel.setText("Question " + (questionIndex + 1) + " of " + quizNumbers.size() + "     |     Score: " + score);
    }

    private void finishQuiz() {
        int total = quizNumbers.size();
        double percent = total == 0 ? 0 : (score * 100.0 / total);
        String message = "Quiz complete!\n\nYour score: " + score + " out of " + total +
            " (" + String.format("%.1f", percent) + "%)";
        JOptionPane.showMessageDialog(this, message, "Quiz Results", JOptionPane.INFORMATION_MESSAGE);
        showMenu();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElementMaster app = new ElementMaster();
            app.setVisible(true);
        });
    }
}
