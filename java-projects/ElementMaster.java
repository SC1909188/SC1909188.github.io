import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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



    private final String[] atomicMasses = {
        "1.008", "4.0026",
        "6.94", "9.0122",
        "10.81", "12.011",
        "14.007", "15.999",
        "18.998", "20.180",
        "22.990", "24.305",
        "26.982", "28.085",
        "30.974", "32.06",
        "35.45", "39.948",
        "39.098", "40.078",
        "44.956", "47.867",
        "50.942", "51.996",
        "54.938", "55.845",
        "58.933", "58.693",
        "63.546", "65.38",
        "69.723", "72.630",
        "74.922", "78.971",
        "79.904", "83.798",
        "85.468", "87.62",
        "88.906", "91.224",
        "92.906", "95.95",
        "[98]", "101.07",
        "102.91", "106.42",
        "107.87", "112.41",
        "114.82", "118.71",
        "121.76", "127.60",
        "126.90", "131.29",
        "132.91", "137.33",
        "138.91", "140.12",
        "140.91", "144.24",
        "[145]", "150.36",
        "151.96", "157.25",
        "158.93", "162.50",
        "164.93", "167.26",
        "168.93", "173.05",
        "174.97", "178.49",
        "180.95", "183.84",
        "186.21", "190.23",
        "192.22", "195.08",
        "196.97", "200.59",
        "204.38", "207.2",
        "208.98", "[209]",
        "[210]", "[222]",
        "[223]", "[226]",
        "[227]", "232.04",
        "231.04", "238.03",
        "[237]", "[244]",
        "[243]", "[247]",
        "[247]", "[251]",
        "[252]", "[257]",
        "[258]", "[259]",
        "[266]", "[267]",
        "[268]", "[269]",
        "[270]", "[277]",
        "[278]", "[281]",
        "[282]", "[285]",
        "[286]", "[289]",
        "[290]", "[293]",
        "[294]", "[294]"
    };

    private final String[] discoveryFacts = {
        "Isolated in 1766 by Henry Cavendish. Antoine Lavoisier later gave it the name hydrogen.", "Its spectrum was identified in sunlight in 1868, before William Ramsay isolated helium on Earth in 1895.",
        "Identified in a mineral in 1817 by Johan August Arfwedson.", "Recognized in beryl by Louis-Nicolas Vauquelin in 1798; the metal was isolated in 1828.",
        "Isolated independently in 1808 by Humphry Davy and by French chemists Joseph Louis Gay-Lussac and Louis-Jacques Thénard.", "Known since antiquity in charcoal, soot, and diamond; it has no single discoverer.",
        "Isolated by Daniel Rutherford in 1772; other early chemists independently studied the gas.", "Studied by Carl Wilhelm Scheele and Joseph Priestley in the 1770s; Lavoisier explained its role in combustion.",
        "Henri Moissan isolated it in 1886 after many earlier attempts to separate this highly reactive element.", "Discovered in liquefied air by William Ramsay and Morris Travers in 1898.",
        "Isolated by Humphry Davy in 1807 using electrolysis.", "Joseph Black recognized magnesia as a distinct substance in 1755; Humphry Davy isolated the metal in 1808.",
        "Hans Christian Ørsted produced impure aluminum in 1825; Friedrich Wöhler improved its isolation in 1827.", "Isolated by Jöns Jacob Berzelius in 1824.",
        "Discovered by Hennig Brand in 1669 during experiments with urine.", "Known since antiquity; elemental sulfur can be found near volcanic areas.",
        "Carl Wilhelm Scheele produced chlorine gas in 1774; Humphry Davy established it as an element in 1810.", "Discovered from air by Lord Rayleigh and William Ramsay in 1894.",
        "Isolated by Humphry Davy in 1807 using electrolysis.", "Prepared in impure form by Humphry Davy in 1808 using electrolysis.",
        "Discovered by Lars Fredrik Nilson in 1879.", "Identified by William Gregor in 1791 in mineral sands; Martin Heinrich Klaproth named it titanium.",
        "Andrés Manuel del Río identified it in 1801; the discovery was recognized after Nils Sefström rediscovered it in 1830.", "Discovered by Louis Nicolas Vauquelin in 1797.",
        "Isolated by Johan Gottlieb Gahn in 1774.", "Known and worked since antiquity.",
        "Recognized as a distinct metal by Georg Brandt in the 1730s.", "Identified by Axel Fredrik Cronstedt in 1751.",
        "Known since antiquity and widely used in tools, ornaments, and alloys.", "Used in brass since ancient times; Andreas Marggraf isolated the metal in 1746.",
        "Discovered spectroscopically by Paul-Émile Lecoq de Boisbaudran in 1875.", "Discovered by Clemens Winkler in 1886, confirming a prediction made from the periodic table.",
        "Known since ancient times; its compounds were used long before modern chemistry.", "Discovered by Jöns Jacob Berzelius and Johan Gottlieb Gahn in 1817.",
        "Discovered by Antoine Jérôme Balard in 1826.", "Discovered by William Ramsay and Morris Travers in 1898.",
        "Discovered spectroscopically by Robert Bunsen and Gustav Kirchhoff in 1861.", "Strontian mineral was identified in the late 18th century; Humphry Davy isolated the metal in 1808.",
        "Discovered by Johan Gadolin in 1794.", "Discovered by Martin Heinrich Klaproth in 1789.",
        "Discovered by Charles Hatchett in 1801; its relationship to tantalum was clarified later.", "Identified by Carl Wilhelm Scheele in 1778 and isolated by the Elhuyar brothers in 1781.",
        "First prepared artificially in 1937 by Carlo Perrier and Emilio Segrè.", "Discovered by Karl Ernst Claus in 1844.",
        "Discovered by William Hyde Wollaston in 1803.", "Discovered by William Hyde Wollaston in 1803.",
        "Known since prehistoric times; one of the earliest metals used by humans.", "Identified by Friedrich Stromeyer in 1817.",
        "Discovered spectroscopically by Ferdinand Reich and Hieronymus Richter in 1863.", "Known since antiquity; used in bronze and other alloys.",
        "Known since antiquity and used in alloys and compounds.", "Identified by Franz-Joseph Müller von Reichenstein in 1782.",
        "Discovered by Bernard Courtois in 1811 while processing seaweed ash.", "Discovered by William Ramsay and Morris Travers in 1898.",
        "Discovered spectroscopically by Robert Bunsen and Gustav Kirchhoff in 1860.", "Barium compounds were studied in the 18th century; Humphry Davy isolated the metal in 1808.",
        "Discovered by Carl Gustaf Mosander in 1839.", "Discovered independently by Martin Heinrich Klaproth, Jöns Jacob Berzelius, and Wilhelm Hisinger in 1803.",
        "Separated from didymium by Carl Auer von Welsbach in 1885.", "Separated from didymium by Carl Auer von Welsbach in 1885.",
        "Identified in nuclear fission products in 1945 by Jacob Marinsky, Lawrence Glendenin, and Charles Coryell.", "Discovered by Paul-Émile Lecoq de Boisbaudran in 1879.",
        "Identified by Eugène-Anatole Demarçay in 1901.", "Discovered by Jean Charles Galissard de Marignac in 1880.",
        "Identified by Carl Gustaf Mosander in 1843.", "Discovered by Paul-Émile Lecoq de Boisbaudran in 1886.",
        "Discovered by Marc Delafontaine and Jacques-Louis Soret in 1878.", "Discovered by Carl Gustaf Mosander in 1843.",
        "Discovered by Per Teodor Cleve in 1879.", "Discovered by Jean Charles Galissard de Marignac in 1878.",
        "Discovered independently by Georges Urbain and Carl Auer von Welsbach in 1907.", "Predicted from periodic trends and discovered by Dirk Coster and George de Hevesy in 1923.",
        "Discovered by Anders Gustaf Ekeberg in 1802.", "Isolated by the Elhuyar brothers in 1783.",
        "Discovered by Walter Noddack, Ida Tacke, and Otto Berg in 1925.", "Discovered by Smithson Tennant in 1803.",
        "Discovered by Smithson Tennant in 1803.", "Known in South American ores before modern European chemistry; studied widely in the 18th century.",
        "Known since antiquity and valued for its resistance to corrosion and tarnishing.", "Known since antiquity; early uses included extraction processes and instruments.",
        "Discovered spectroscopically by William Crookes in 1861.", "Known since ancient times and mined for thousands of years.",
        "Recognized as distinct from lead and tin by Claude François Geoffroy in 1753.", "Discovered by Marie and Pierre Curie in 1898.",
        "First produced in 1940 by Dale Corson, Kenneth MacKenzie, and Emilio Segrè.", "Identified by Friedrich Ernst Dorn in 1900 during studies of radium decay.",
        "Discovered by Marguerite Perey in 1939 while studying actinium decay.", "Discovered by Marie and Pierre Curie in 1898.",
        "Discovered by André-Louis Debierne in 1899.", "Discovered by Jöns Jacob Berzelius in 1829.",
        "Identified by Kasimir Fajans and Oswald Göhring in 1913; later isolated by Otto Hahn and Lise Meitner.", "Discovered by Martin Heinrich Klaproth in 1789.",
        "First synthesized by Edwin McMillan and Philip Abelson in 1940.", "First synthesized by Glenn Seaborg's team in 1940.",
        "First identified by Glenn Seaborg and colleagues in 1944.", "First synthesized by Glenn Seaborg's team in 1944.",
        "Synthesized at Berkeley in 1949 by a team led by Stanley Thompson.", "Synthesized at Berkeley in 1950 by a team led by Stanley Thompson.",
        "Identified in debris from a thermonuclear test in 1952.", "Identified in thermonuclear-test debris in 1952.",
        "Synthesized by Albert Ghiorso's team at Berkeley in 1955.", "Reported by teams at Berkeley and Dubna in 1958; early results were disputed.",
        "Synthesized at Berkeley in 1961.", "First reported by teams at Dubna and Berkeley in the 1960s; discovery credit was disputed.",
        "Produced by research teams at Dubna and Berkeley in the late 1960s and 1970s; naming was disputed.", "Synthesized at Berkeley in 1974 by a team including Albert Ghiorso.",
        "Synthesized at GSI Darmstadt in 1981.", "Synthesized at GSI Darmstadt in 1984.",
        "Synthesized at GSI Darmstadt in 1982.", "Synthesized at GSI Darmstadt in 1994.",
        "Synthesized at GSI Darmstadt in 1994.", "First reported by GSI Darmstadt in 1996.",
        "First convincingly produced by the RIKEN team in Japan in 2004.", "Produced by the Dubna–Livermore collaboration beginning in 1998.",
        "First produced by the Dubna–Livermore collaboration in 2003.", "Produced by the Dubna–Livermore collaboration beginning in 2000.",
        "First reported by an international collaboration in 2010.", "Produced in experiments at Dubna with U.S. collaborators; early results were published later and subsequently confirmed."
    };

    private final String[] elementUses = {
        "Used in fuel cells, ammonia production, refining, and as a fuel in some rockets. Hydrogen atoms are central to water and almost all organic molecules.", "Used to fill balloons and airships, cool superconducting magnets, and provide a controlled atmosphere for some processes. It is very unreactive.",
        "Used in rechargeable lithium-ion batteries, specialty glass, ceramics, and some medicines. Lithium compounds require careful handling.", "Used in lightweight aerospace alloys, X-ray windows, and beryllium-copper tools. Beryllium dust is toxic, so industrial use requires strict controls.",
        "Used in borosilicate glass, fiberglass, detergents, and semiconductor materials. Boron compounds also help plants grow in small amounts.", "Found in every known form of life; graphite is used in pencils and batteries, diamond in cutting tools, and carbon fiber in strong lightweight parts.",
        "Makes up about 78% of Earth's atmosphere. Nitrogen is used to make ammonia and fertilizers, provide inert atmospheres, and cool materials as liquid nitrogen.", "Used in medical oxygen, steel production, water treatment, and many chemical processes. Ozone is another form of oxygen.",
        "Used to make fluoropolymers, special glass, and some medicines and toothpaste ingredients. Fluorine gas is extremely reactive and hazardous.", "Used in illuminated signs, lasers, and some high-voltage indicators. Neon glows reddish-orange in a discharge tube.",
        "Used in table salt, chemical manufacturing, soap production, and sodium-vapor lamps. Pure sodium metal reacts strongly with water.", "Used in lightweight alloys, fireworks, antacids, and materials that reduce glare. Magnesium burns with an intense white light.",
        "Used in drink cans, aircraft, power lines, construction, and packaging because it is light and forms a protective oxide layer.", "Used in computer chips, solar cells, glass, and silicone-based materials. Silicon is the main semiconductor in most modern electronics.",
        "Used in fertilizers, safety matches, detergents, and biological molecules such as DNA and ATP.", "Used to make sulfuric acid, fertilizers, rubber, and some medicines. Sulfur is also important in proteins.",
        "Used to disinfect drinking water and pools and to make PVC plastic and other chemicals. Chlorine gas is toxic at high concentrations.", "Used in welding, lighting, and protective atmospheres for manufacturing. Argon is the most abundant noble gas in Earth's atmosphere.",
        "Used in fertilizers, soaps, glass, and chemical production. Potassium ions are essential for nerve and muscle function.", "Used in cement, plaster, metals, and dietary supplements. Calcium compounds form bones, teeth, and limestone.",
        "Used in aerospace alloys, high-strength bicycle frames, and specialized equipment. Scandium is relatively rare and expensive.", "Used in aircraft, medical implants, chemical equipment, and sporting goods due to its strength and corrosion resistance.",
        "Used in strong steel alloys, tools, pigments, and some rechargeable batteries. Vanadium can strengthen steel.", "Used in stainless steel, chrome plating, pigments, and some batteries. Chromium compounds differ greatly in toxicity.",
        "Used in steelmaking, batteries, and industrial chemicals. Manganese is also a trace nutrient for living things.", "Used in buildings, vehicles, tools, and machinery; iron is the main ingredient in steel and a key part of hemoglobin.",
        "Used in rechargeable batteries, superalloys, magnets, and catalysts. Cobalt compounds give some glass and ceramics a blue color.", "Used in stainless steel, coins, catalysts, and rechargeable batteries. Nickel can trigger skin allergies in some people.",
        "Used in electrical wiring, motors, plumbing, coins, and electronics because it conducts electricity well.", "Used to galvanize steel against rust, in die-casting alloys, and in batteries. Zinc is a required trace nutrient.",
        "Used in semiconductors, LEDs, solar panels, and high-temperature thermometers. Gallium can melt in a warm hand.", "Used in fiber optics, infrared optics, and semiconductor research. Its chemistry helped confirm predictions of the periodic table.",
        "Used in some semiconductors, specialty glass, and research. Many arsenic compounds are poisonous, so exposure must be controlled.", "Used in glassmaking, pigments, and some photovoltaic technologies. Selenium is essential in tiny amounts but toxic in excess.",
        "Used in flame retardants, water-treatment chemicals, and medicines. Bromine is a reddish-brown liquid at room temperature.", "Used in photographic flashes historically, high-performance lighting, and as a protective gas in some applications.",
        "Used in atomic clocks, vacuum tubes, and research. Rubidium is highly reactive and must be kept away from water and air.", "Used in fireworks and signal flares to produce a red color, in magnets, and in some medical imaging compounds.",
        "Used in laser crystals, phosphors, and research. Yttrium compounds are important in some red phosphors and ceramics.", "Used in nuclear reactors, chemical equipment, and corrosion-resistant alloys. Zirconium is useful where resistance to corrosion matters.",
        "Used in superconducting magnets, steel alloys, and specialized electronics. Niobium can form superconducting compounds.", "Used in strong alloys, catalysts, and lubricants. Molybdenum helps steel retain strength at high temperatures.",
        "Used mainly in scientific research; its radioactivity makes routine consumer uses impractical.", "Used in catalysts, electrical contacts, and specialized alloys because it resists corrosion.",
        "Used in catalytic converters, chemical catalysts, and laboratory equipment.", "Used in catalytic converters, electronics, jewelry, and chemical processes. Palladium can absorb large amounts of hydrogen.",
        "Used in jewelry, mirrors, electronics, and antimicrobial coatings. Silver has the highest electrical conductivity of any element.", "Used in rechargeable batteries, protective coatings, and nuclear-control applications. Cadmium and many compounds are toxic.",
        "Used in touchscreens, semiconductors, and low-melting alloys. Indium tin oxide helps transparent screens conduct electricity.", "Used in solder, protective coatings, and bronze. Many modern products use lead-free tin-based solder.",
        "Used in flame-retardant materials, alloys, and some semiconductor devices. Antimony compounds can be toxic.", "Used in solar panels, thermoelectric devices, and specialty alloys. Tellurium is relatively rare in Earth's crust.",
        "Used in antiseptics, iodized salt, and medical imaging compounds. The thyroid gland needs small amounts of iodine.", "Used in flash lamps, ion propulsion research, and specialized light sources. Xenon is a heavy noble gas.",
        "Used in atomic clocks, photoelectric cells, and vacuum tubes. Cesium reacts violently with water.", "Used in drilling fluids, glass, paint, and medical imaging compounds. Soluble barium salts can be toxic, while barium sulfate is used in X-ray imaging.",
        "Used in camera lenses, catalysts, and specialty glass. Lanthanum compounds can improve optical properties.", "Used in catalytic converters, glass polishing, lighter flints, and some alloys.",
        "Used in aircraft alloys, magnets, and research. Praseodymium compounds can color glass and ceramics.", "Used in powerful permanent magnets, lasers, and glass coloring. Neodymium magnets are common in speakers and motors.",
        "Used only in specialized research because all its isotopes are radioactive and scarce.", "Used in magnets, lasers, and nuclear research. Samarium-cobalt magnets work at relatively high temperatures.",
        "Used in phosphors, lasers, and some nuclear control materials. Europium compounds are known for red and blue phosphorescence.", "Used in strong permanent magnets, medical imaging research, and nuclear technology.",
        "Used in magnets, solid-state devices, and research into advanced materials.", "Used in permanent magnets, lasers, and research. Dysprosium can help magnets perform at higher temperatures.",
        "Used in lasers, specialty glass, and research. Holmium has a very high magnetic moment.", "Used in fiber-optic amplifiers, lasers, and research. Erbium helps amplify signals in optical communication systems.",
        "Used in lasers and research; it has no major everyday application because it is rare.", "Used in specialty magnets, phosphors, and research. Ytterbium also has applications in some atomic-clock studies.",
        "Used in laser crystals, catalysts, and scientific research.", "Used in superalloys for jet engines, plasma equipment, and research.",
        "Used in electronics, capacitors, corrosion-resistant equipment, and aerospace alloys.", "Used in light-bulb filaments historically, heavy alloys, cutting tools, and electrical contacts. Tungsten has an extremely high melting point.",
        "Used in jet-engine superalloys, catalysts, and wear-resistant coatings.", "Used in pen tips, electrical contacts, and hard alloys. Osmium is one of the densest naturally occurring elements.",
        "Used in spark plugs, electrical contacts, and specialist alloys because it resists wear and corrosion.", "Used in catalytic converters, laboratory equipment, and corrosion-resistant coatings.",
        "Used in jewelry, coins, electronics, and dentistry. Gold resists corrosion and conducts electricity well.", "Used historically in thermometers, switches, and some lamps; many uses have been phased down due to toxicity.",
        "Used in specialty glass, electronics, and research. Thallium compounds are highly toxic.", "Used in batteries, radiation shielding, and some specialized alloys; many uses have been reduced because lead is toxic.",
        "Used in lead-free solders, medicines such as bismuth subsalicylate, and cosmetics. Bismuth is relatively low in toxicity compared with lead.", "Used only in research and a few specialized devices because it is radioactive and rare.",
        "Used only in scientific research because it is extremely rare and radioactive.", "Used in scientific research; radon is a radioactive gas that can accumulate in buildings and poses a health risk.",
        "Used only in research; francium is extremely rare and highly radioactive.", "Used in medical and industrial radiation sources under controlled conditions. Radium is highly radioactive and hazardous.",
        "Used in scientific research; it has no significant routine commercial use because it is radioactive and rare.", "Used in thorium-based research, specialized alloys, and nuclear science. Thorium compounds have been used in some optical materials.",
        "Used in nuclear research; its chemistry is studied to understand actinides and nuclear fuel cycles.", "Used as nuclear fuel in reactors and in research; depleted uranium has specialized industrial uses with strict controls.",
        "Used primarily in scientific research and as a precursor in the production of heavier elements.", "Used in nuclear research and in some specialized reactor contexts; it is radioactive and requires strict controls.",
        "Used mainly in nuclear research and to create heavier elements.", "Used in nuclear research and the synthesis of heavier elements.",
        "Used only in scientific research; only minute amounts can be produced.", "Used only in research; californium-252 is a strong neutron source used in specialized analysis and reactor start-up.",
        "Used only in scientific research because it is highly radioactive and available in tiny quantities.", "Used only in scientific research; its isotopes are produced atom by atom or in tiny amounts.",
        "Used only in scientific research due to its short-lived radioactive isotopes.", "Used only in scientific research; it has no routine commercial applications.",
        "Used only in research; it is produced atom by atom and decays rapidly.", "Used only in research; extremely short-lived and available in tiny quantities.",
        "Used only in research; its properties are inferred from a very small number of atoms.", "Used only in research; extremely radioactive and difficult to produce.",
        "Used only in research to investigate superheavy-element chemistry.", "Used only in research; extremely short-lived and produced atom by atom.",
        "Used only in research; too few atoms have been made for everyday applications.", "Used only in research; its chemistry and physical properties are still being investigated.",
        "Used only in research; it is extremely short-lived and highly radioactive.", "Used only in research; it is extremely short-lived and highly radioactive.",
        "Used only in research; its chemical behavior is studied one atom at a time.", "Used only in research; its expected noble-gas behavior is difficult to measure directly.",
        "Used only in research; extremely short-lived and produced in particle-accelerator experiments.", "Used only in research; its chemistry and physical properties remain largely uncertain.",
        "Used only in research; tennessine is extremely radioactive, short-lived, and produced atom by atom in accelerator experiments.", "Used only in research; oganesson is extremely short-lived, and its physical and chemical properties are largely unknown."
    };

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
    private int wrongAnswerCount;
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
        JLabel rangeHint = wrappedLabel("The FROM and TO values are used when Test Collection is set to Custom range.", 10, MUTED);
        card.add(rangeHint);
        card.add(Box.createVerticalStrut(10));
        setChoice.addActionListener(e -> updateRangeControls());
        updateRangeControls();

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
        JLabel note = label("Click any element tile to open its full profile", 10, TEAL, Font.BOLD);
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
        wrongAnswerCount = 0;
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
            showWrongAnswerImage();
        }
        answered = true;
        answerField.setEnabled(false);
        submitButton.setText(questionIndex == quizNumbers.size() - 1 ? "SEE MY RESULTS  →" : "NEXT QUESTION  →");
        submitButton.setBackground(BLUE);
        scoreLabel.setText("SCORE  " + score);
        progressBar.setValue((int) ((questionIndex + 1) * 100.0 / quizNumbers.size()));
    }

    private void showWrongAnswerImage() {
        wrongAnswerCount++;

        // The first three wrong answers use the three pictures in the order supplied.
        int pictureNumber = Math.min(wrongAnswerCount, 3);
        String[] effects = {
            "🔥  FLAMES!  🔥",
            "⚡  THUNDER!  ⚡",
            "🌩️  THUNDER AND LIGHTNING!  ⚡"
        };
        String[] fileNames = {
            "wrong-answer-1.jpg",
            "wrong-answer-2.jpg",
            "wrong-answer-3.jpg"
        };

        ImageIcon originalIcon = loadWrongAnswerImage(fileNames[pictureNumber - 1]);
        JDialog dialog = new JDialog(this, "Wrong Answer!", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(NAVY);
        panel.setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel effectLabel = new JLabel(effects[pictureNumber - 1], SwingConstants.CENTER);
        effectLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        effectLabel.setForeground(pictureNumber == 1 ? new Color(255, 116, 50) :
            pictureNumber == 2 ? new Color(110, 190, 255) : new Color(190, 170, 255));
        panel.add(effectLabel, BorderLayout.NORTH);

        JLabel pictureLabel = new JLabel("", SwingConstants.CENTER);
        if (originalIcon != null) {
            Image scaled = originalIcon.getImage().getScaledInstance(520, 360, Image.SCALE_SMOOTH);
            pictureLabel.setIcon(new ImageIcon(scaled));
        } else {
            pictureLabel.setText("Picture not found: " + fileNames[pictureNumber - 1]);
            pictureLabel.setForeground(WHITE);
        }
        panel.add(pictureLabel, BorderLayout.CENTER);

        String message = wrongAnswerCount <= 3
            ? "Wrong answer #" + wrongAnswerCount + " — keep going!"
            : "Another miss! You've got this — try the next one.";
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setForeground(MUTED);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(messageLabel, BorderLayout.SOUTH);

        JButton closeButton = actionButton("BACK TO QUIZ", TEAL, NAVY);
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonHolder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonHolder.setOpaque(false);
        buttonHolder.add(closeButton);
        JPanel bottom = new JPanel(new BorderLayout(0, 8));
        bottom.setOpaque(false);
        bottom.add(messageLabel, BorderLayout.NORTH);
        bottom.add(buttonHolder, BorderLayout.SOUTH);
        panel.remove(messageLabel);
        panel.add(bottom, BorderLayout.SOUTH);

        dialog.setContentPane(panel);
        dialog.setSize(590, 500);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private ImageIcon loadWrongAnswerImage(String fileName) {
        // Works when running from the folder containing the Java file and image files.
        File imageFile = new File(fileName);
        if (!imageFile.isFile()) {
            imageFile = new File("java-projects", fileName);
        }
        if (imageFile.isFile()) {
            return new ImageIcon(imageFile.getAbsolutePath());
        }
        java.net.URL resource = getClass().getResource("/" + fileName);
        if (resource != null) {
            return new ImageIcon(resource);
        }
        return null;
    }

    private void finishQuiz() {
        int total = quizNumbers.size();
        double percent = total == 0 ? 0 : score * 100.0 / total;
        resultScoreLabel.setText(score + " / " + total);
        resultMessageLabel.setText(String.format("%.0f%% correct  •  %s", percent,
            percent >= 90 ? "Amazing work!" : percent >= 70 ? "Great progress!" : "Keep practicing — you're building it."));
        screens.show(screenHolder, "RESULTS");
    }

    private void showElementDetails(int z) {
        JDialog dialog = new JDialog(this, names[z - 1] + " | Element Profile", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(860, 740);
        dialog.setMinimumSize(new Dimension(760, 640));
        dialog.setLocationRelativeTo(this);

        JPanel root = new GradientPanel(NAVY, new Color(19, 31, 67));
        root.setLayout(new BorderLayout(0, 14));
        root.setBorder(new EmptyBorder(18, 20, 18, 20));

        JPanel hero = new CardPanel(CARD, 20);
        hero.setLayout(new BorderLayout(16, 0));
        hero.setBorder(new EmptyBorder(14, 16, 14, 16));

        CardPanel badge = new CardPanel(familyColor(z), 16);
        badge.setLayout(new BoxLayout(badge, BoxLayout.Y_AXIS));
        badge.setPreferredSize(new Dimension(100, 104));
        JLabel badgeNumber = label("ATOMIC NO. " + z, 9, Color.WHITE, Font.BOLD);
        badgeNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel badgeSymbol = label(symbols[z - 1], 40, Color.WHITE, Font.BOLD);
        badgeSymbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel badgeMass = label(atomicMasses[z - 1], 10, Color.WHITE, Font.PLAIN);
        badgeMass.setAlignmentX(Component.CENTER_ALIGNMENT);
        badge.add(Box.createVerticalGlue());
        badge.add(badgeNumber);
        badge.add(Box.createVerticalStrut(1));
        badge.add(badgeSymbol);
        badge.add(Box.createVerticalStrut(1));
        badge.add(badgeMass);
        badge.add(Box.createVerticalGlue());
        hero.add(badge, BorderLayout.WEST);

        JPanel identity = new JPanel();
        identity.setOpaque(false);
        identity.setLayout(new BoxLayout(identity, BoxLayout.Y_AXIS));
        JLabel title = label(names[z - 1], 28, WHITE, Font.BOLD);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel subtitle = label(getCategory(z).toUpperCase() + "  •  " + getGroupLabel(z), 11, TEAL, Font.BOLD);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel description = wrappedLabel("Explore this element's identity, electron structure, discovery story, uses, and key chemistry notes.", 12, MUTED);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);
        identity.add(Box.createVerticalGlue());
        identity.add(title);
        identity.add(Box.createVerticalStrut(5));
        identity.add(subtitle);
        identity.add(Box.createVerticalStrut(10));
        identity.add(description);
        identity.add(Box.createVerticalGlue());
        hero.add(identity, BorderLayout.CENTER);
        root.add(hero, BorderLayout.NORTH);

        JPanel detailGrid = new JPanel(new GridLayout(0, 2, 12, 12));
        detailGrid.setOpaque(false);
        detailGrid.add(detailCard("AT A GLANCE", 
            "Atomic number: " + z + "\n" +
            "Chemical symbol: " + symbols[z - 1] + "\n" +
            "Standard atomic mass: " + atomicMasses[z - 1] + " u\n" +
            "Element family: " + getCategory(z) + "\n" +
            "Periodic-table position: " + getGroupLabel(z) + ", period " + getPeriod(z) + "\n" +
            "Block: " + getBlock(z) + "\n" +
            "State near room temperature: " + getState(z)));

        detailGrid.add(detailCard("VALENCE ELECTRONS & SHELLS",
            "Valence-electron guide: " + getValenceInfo(z) + "\n" +
            "Electrons by shell: " + getShellDistribution(z) + "\n" +
            "Orbital configuration: " + getElectronConfiguration(z) + "\n\n" +
            "Important: transition metals and inner-transition elements can use more than their outermost-shell electrons in bonding. The simple valence count is a learning guide, not a complete description of every chemical state."));

        detailGrid.add(detailCard("DISCOVERY & HISTORY", discoveryFacts[z - 1]));
        detailGrid.add(detailCard("USES & WHY IT MATTERS", elementUses[z - 1]));
        detailGrid.add(detailCard("OCCURRENCE & ISOTOPES", getOccurrenceInfo(z) + "\n\n" + getIsotopeInfo(z)));
        detailGrid.add(detailCard("CHEMISTRY NOTES", getChemistryNote(z)));

        JScrollPane scroll = new JScrollPane(detailGrid);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(18);
        scroll.getVerticalScrollBar().setBackground(NAVY_LIGHT);
        scroll.getVerticalScrollBar().setForeground(TEAL);
        root.add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout(10, 0));
        footer.setOpaque(false);
        JLabel note = label("Tap another tile to explore another element.", 11, MUTED, Font.PLAIN);
        footer.add(note, BorderLayout.WEST);
        JButton close = actionButton("DONE  ✓", TEAL, NAVY);
        close.addActionListener(e -> dialog.dispose());
        footer.add(close, BorderLayout.EAST);
        root.add(footer, BorderLayout.SOUTH);

        dialog.setContentPane(root);
        dialog.setVisible(true);
    }

    private JPanel detailCard(String title, String body) {
        CardPanel card = new CardPanel(CARD, 16);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(14, 15, 14, 15));
        JLabel heading = label(title, 11, TEAL, Font.BOLD);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(heading);
        card.add(Box.createVerticalStrut(9));
        JTextArea text = new JTextArea(body);
        text.setFont(new Font("SansSerif", Font.PLAIN, 12));
        text.setForeground(WHITE);
        text.setBackground(CARD);
        text.setOpaque(false);
        text.setEditable(false);
        text.setFocusable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(BorderFactory.createEmptyBorder());
        text.setColumns(27);
        int lines = Math.max(3, Math.min(13, (body.length() / 43) + 2));
        text.setRows(lines);
        text.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(text);
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        return card;
    }

    private int getPeriod(int z) {
        if (z <= 2) return 1;
        if (z <= 10) return 2;
        if (z <= 18) return 3;
        if (z <= 36) return 4;
        if (z <= 54) return 5;
        if (z <= 86) return 6;
        return 7;
    }

    private String getGroupLabel(int z) {
        if (z >= 58 && z <= 71) return "Lanthanide series";
        if (z >= 90 && z <= 103) return "Actinide series";
        int group = z == 57 || z == 89 ? 3 : positionFor(z)[1] + 1;
        return "Group " + group;
    }

    private String getBlock(int z) {
        if ((z >= 58 && z <= 71) || (z >= 90 && z <= 103)) return "f-block";
        int group = z == 57 || z == 89 ? 3 : positionFor(z)[1] + 1;
        if (group <= 2) return "s-block";
        if (group <= 12) return "d-block";
        return "p-block";
    }

    private String getCategory(int z) {
        if (z == 1) return "Nonmetal";
        if (z == 2 || z == 10 || z == 18 || z == 36 || z == 54 || z == 86 || z == 118) return "Noble gas";
        if (z == 3 || z == 11 || z == 19 || z == 37 || z == 55 || z == 87) return "Alkali metal";
        if (z == 4 || z == 12 || z == 20 || z == 38 || z == 56 || z == 88) return "Alkaline earth metal";
        if (z >= 57 && z <= 71) return "Lanthanide";
        if (z >= 89 && z <= 103) return "Actinide";
        if ((z >= 21 && z <= 30) || (z >= 39 && z <= 48) || (z >= 72 && z <= 80) || (z >= 104 && z <= 112)) return "Transition metal";
        if (z == 9 || z == 17 || z == 35 || z == 53 || z == 85 || z == 117) return "Halogen";
        if (z == 5 || z == 14 || z == 32 || z == 33 || z == 51 || z == 52 || z == 84) return "Metalloid (classification may vary)";
        if (z == 6 || z == 7 || z == 8 || z == 15 || z == 16 || z == 34) return "Other nonmetal";
        if (z == 13 || z == 31 || z == 49 || z == 50 || z == 81 || z == 82 || z == 83 || z == 113 || z == 114 || z == 115 || z == 116) return "Post-transition metal / predicted metal";
        if (z == 85) return "Halogen";
        return "Metal or predicted metal";
    }

    private String getState(int z) {
        if (z >= 104) return "Not directly established; predicted properties";
        if (z == 1 || z == 2 || z == 7 || z == 8 || z == 9 || z == 10 || z == 17 || z == 18 || z == 36 || z == 54 || z == 86) return "Gas";
        if (z == 35 || z == 80) return "Liquid";
        if (z == 118) return "Unknown; often predicted to be a solid";
        return "Solid";
    }

    private String getValenceInfo(int z) {
        if (z == 2) return "2 (full first shell)";
        if (z >= 58 && z <= 71) return "Usually 2–3 outer-shell electrons; f-electrons can also affect bonding";
        if (z >= 90 && z <= 103) return "Variable; outer s, d, and f electrons may participate";
        if ((z >= 21 && z <= 30) || (z >= 39 && z <= 48) || (z >= 72 && z <= 80) || (z >= 104 && z <= 112)) return "Variable; d-electrons may also participate in bonding";
        if (z == 57 || z == 89) return "Usually 2–3; bonding can involve d-electrons";
        int group = positionFor(z)[1] + 1;
        if (group == 1) return "1";
        if (group == 2) return "2";
        if (group >= 13 && group <= 18) return String.valueOf(group - 10);
        return "Variable; depends on the compound and bonding state";
    }

    private String getElectronConfiguration(int z) {
        // Aufbau order gives a useful introductory model. Correct several well-known exceptions.
        int[] shells = {1,2,2,3,3,4,3,4,5,4,5,6,4,5,6,7,5,6,7};
        String[] orbitals = {"s","s","p","s","p","s","d","p","s","d","p","s","f","d","p","s","f","d","p"};
        int[] capacities = {2,2,6,2,6,2,10,6,2,10,6,2,14,10,6,2,14,10,6};
        int[] filled = new int[capacities.length];
        int remaining = z;
        for (int i = 0; i < capacities.length && remaining > 0; i++) {
            filled[i] = Math.min(capacities[i], remaining);
            remaining -= filled[i];
        }
        // Known ground-state exceptions (subshell indices follow the arrays above).
        int[] fixes = configurationFix(z);
        if (fixes != null) filled = fixes;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < filled.length; i++) {
            if (filled[i] > 0) {
                if (result.length() > 0) result.append(' ');
                result.append(shells[i]).append(orbitals[i]).append(toSuperscript(filled[i]));
            }
        }
        return result.toString();
    }

    private int[] configurationFix(int z) {
        int[] shellsCap = {2,2,6,2,6,2,10,6,2,10,6,2,14,10,6,2,14,10,6};
        int[] filled = new int[shellsCap.length];
        int left = z;
        for (int i = 0; i < shellsCap.length && left > 0; i++) {
            filled[i] = Math.min(shellsCap[i], left);
            left -= filled[i];
        }
        switch (z) {
            case 24: filled[5]=1; filled[6]=5; break; // Cr
            case 29: filled[5]=1; filled[6]=10; break; // Cu
            case 41: filled[8]=1; filled[9]=4; break; // Nb
            case 42: filled[8]=1; filled[9]=5; break; // Mo
            case 44: filled[8]=1; filled[9]=7; break; // Ru
            case 45: filled[8]=1; filled[9]=8; break; // Rh
            case 46: filled[8]=0; filled[9]=10; break; // Pd
            case 47: filled[8]=1; filled[9]=10; break; // Ag
            case 57: filled[12]=0; filled[13]=1; break; // La
            case 58: filled[12]=1; filled[13]=1; break; // Ce
            case 64: filled[12]=7; filled[13]=1; break; // Gd
            case 78: filled[11]=1; filled[13]=9; break; // Pt
            case 79: filled[11]=1; filled[13]=10; break; // Au
            case 89: filled[16]=0; filled[17]=1; break; // Ac
            case 90: filled[16]=0; filled[17]=2; break; // Th
            case 91: filled[16]=2; filled[17]=1; break; // Pa
            case 92: filled[16]=3; filled[17]=1; break; // U
            case 93: filled[16]=4; filled[17]=1; break; // Np
            case 96: filled[16]=7; filled[17]=1; break; // Cm
            case 103: filled[17]=0; filled[18]=1; break; // Lr
            default: return null;
        }
        return filled;
    }

    private String toSuperscript(int value) {
        String digits = String.valueOf(value);
        String from = "0123456789";
        String to = "⁰¹²³⁴⁵⁶⁷⁸⁹";
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < digits.length(); i++) out.append(to.charAt(from.indexOf(digits.charAt(i))));
        return out.toString();
    }

    private String getShellDistribution(int z) {
        int[] shells = {1,2,2,3,3,4,3,4,5,4,5,6,4,5,6,7,5,6,7};
        int[] capacities = {2,2,6,2,6,2,10,6,2,10,6,2,14,10,6,2,14,10,6};
        int[] filled = new int[capacities.length];
        int remaining = z;
        for (int i = 0; i < capacities.length && remaining > 0; i++) {
            filled[i] = Math.min(capacities[i], remaining);
            remaining -= filled[i];
        }
        int[] fixes = configurationFix(z);
        if (fixes != null) filled = fixes;
        int[] byShell = new int[8];
        for (int i = 0; i < filled.length; i++) byShell[shells[i]] += filled[i];
        StringBuilder result = new StringBuilder();
        for (int n = 1; n <= 7; n++) {
            if (byShell[n] > 0) {
                if (result.length() > 0) result.append(", ");
                result.append(byShell[n]);
            }
        }
        return result.toString();
    }

    private String getOccurrenceInfo(int z) {
        if (z >= 95) return "This element is made artificially in nuclear or particle-accelerator experiments. Only extremely small amounts are available.";
        if (z >= 93) return "This is a transuranium element. It can be produced artificially; tiny traces may occur in special natural or reactor environments.";
        if (z == 43 || z == 61) return "Technetium and promethium have no stable isotopes and occur naturally only in very small traces; most samples are produced artificially.";
        if (z >= 84) return "This element is radioactive. Some heavier elements occur naturally in decay chains or in trace amounts, while others are mostly produced artificially.";
        if (z <= 2) return "Found throughout the universe; hydrogen is especially abundant, and helium is common in stars and some natural gas deposits.";
        if (z <= 10) return "Occurs naturally in Earth's atmosphere, water, rocks, or minerals, and also has important roles in stars and space chemistry.";
        if (z <= 92) return "Occurs in Earth's crust, oceans, atmosphere, or minerals. Its abundance varies widely; some elements are common, while others are rare.";
        return "Occurrence is limited and depends on radioactive decay and human-made production.";
    }

    private String getIsotopeInfo(int z) {
        if (z == 43 || z == 61 || z >= 83) return "Isotope note: no stable isotopes are known. Different isotopes contain the same number of protons but different numbers of neutrons.";
        return "Isotope note: at least one stable isotope is known. The atomic mass shown above is a standard average (or a representative isotope mass for elements without a stable isotope). Isotopes of an element differ in neutron count.";
    }

    private String getChemistryNote(int z) {
        String category = getCategory(z);
        String state = getState(z);
        String text = "Family: " + category + ". It shares patterns with elements in related parts of the periodic table, but its exact behavior depends on temperature, pressure, chemical partners, and oxidation state.\n" +
            "Physical state near room temperature: " + state + ".\n";
        if (z >= 104) text += "Because only a few atoms have been made and they decay quickly, many properties are predictions rather than measurements.\n";
        if (z == 9 || z == 17 || z == 35 || z == 53 || z == 85 || z == 117) text += "Halogens commonly form salts with metals and often gain one electron in simple ionic compounds.\n";
        if (z == 2 || z == 10 || z == 18 || z == 36 || z == 54 || z == 86 || z == 118) text += "Noble gases have full outer electron shells in the simple model, so they are usually much less reactive than nearby elements.\n";
        if (z == 5 || z == 14 || z == 32 || z == 33 || z == 51 || z == 52) text += "Metalloids show a mixture of metallic and nonmetallic properties and are important in semiconductor or specialty materials science.\n";
        if (z >= 57 && z <= 71) text += "Lanthanides are often grouped as rare-earth elements and commonly have similar +3 chemistry.\n";
        if (z >= 89 && z <= 103) text += "Actinides are radioactive; many show several oxidation states and are important in nuclear science.\n";
        return text;
    }

    private void styleCombo(JComboBox<String> combo) {
        // Force Swing's basic combo box UI so Windows' native theme cannot
        // paint the closed field white while the text is white.
        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrow = new JButton("▾");
                arrow.setFont(new Font("SansSerif", Font.BOLD, 14));
                arrow.setForeground(TEAL);
                arrow.setBackground(CARD_LIGHT);
                arrow.setOpaque(true);
                arrow.setFocusPainted(false);
                arrow.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                arrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                return arrow;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(CARD_LIGHT);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });

        combo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        combo.setForeground(WHITE);
        combo.setBackground(CARD_LIGHT);
        combo.setOpaque(true);
        combo.setFocusable(true);
        combo.setEditable(false);
        combo.setLightWeightPopupEnabled(true);
        combo.setMaximumRowCount(12);
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        combo.setPreferredSize(new Dimension(300, 44));
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(69, 91, 139), 1, true),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)));

        // Style both the selected value shown before opening and the popup rows.
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel item = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
                item.setOpaque(true);
                item.setFont(new Font("SansSerif", Font.PLAIN, 14));
                item.setBorder(new EmptyBorder(8, 10, 8, 10));
                if (isSelected && index >= 0) {
                    item.setBackground(TEAL);
                    item.setForeground(NAVY);
                } else {
                    item.setBackground(CARD_LIGHT);
                    item.setForeground(WHITE);
                }
                return item;
            }
        });

        // A dark popup with readable white options, including when Windows uses
        // a different system theme.
        combo.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        combo.setToolTipText("Choose a quiz collection or question style");
    }

    private void updateRangeControls() {
        if (startNumber == null || endNumber == null || setChoice == null) return;
        boolean customRange = "Custom range".equals(setChoice.getSelectedItem());
        startNumber.setEnabled(customRange);
        endNumber.setEnabled(customRange);
        startNumber.setToolTipText(customRange ? "First atomic number to include" : "Choose Custom range to edit this value");
        endNumber.setToolTipText(customRange ? "Last atomic number to include" : "Choose Custom range to edit this value");
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
        private final javax.swing.border.Border normalBorder = BorderFactory.createLineBorder(new Color(255, 255, 255, 65), 1);
        private final javax.swing.border.Border hoverBorder = BorderFactory.createLineBorder(WHITE, 2);

        MiniTile(int atomicNumber) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(familyColor(atomicNumber));
            setBorder(normalBorder);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setFocusable(true);
            setToolTipText("Click to explore " + names[atomicNumber - 1] + " (" + symbols[atomicNumber - 1] + ")");
            JLabel number = new JLabel(String.valueOf(atomicNumber));
            number.setFont(new Font("SansSerif", Font.PLAIN, 7));
            number.setForeground(new Color(255, 255, 255, 230));
            number.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel symbol = new JLabel(symbols[atomicNumber - 1]);
            symbol.setFont(new Font("SansSerif", Font.BOLD, 10));
            symbol.setForeground(Color.WHITE);
            symbol.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(Box.createVerticalGlue());
            add(number);
            add(symbol);
            add(Box.createVerticalGlue());

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBorder(hoverBorder);
                    repaint();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    setBorder(normalBorder);
                    repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) showElementDetails(atomicNumber);
                }
            });
            getAccessibleContext().setAccessibleName(names[atomicNumber - 1] + ", atomic number " + atomicNumber);
            getAccessibleContext().setAccessibleDescription("Open details about " + names[atomicNumber - 1]);
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
            // Keep native Swing controls readable on the dark app theme.
            UIManager.put("ComboBox.background", CARD_LIGHT);
            UIManager.put("ComboBox.foreground", WHITE);
            UIManager.put("ComboBox.selectionBackground", TEAL);
            UIManager.put("ComboBox.selectionForeground", NAVY);
            UIManager.put("List.background", CARD_LIGHT);
            UIManager.put("List.foreground", WHITE);
            UIManager.put("List.selectionBackground", TEAL);
            UIManager.put("List.selectionForeground", NAVY);
            ElementMaster app = new ElementMaster();
            app.setVisible(true);
        });
    }
}
