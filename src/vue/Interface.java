package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modele.Filtre;
import modele.Graph;
import modele.Noeud;
import modele.Parser;
import modele.Recherche;
import modele.RechercheManuelle;
import modele.TypeRecherche;
import modele.TypeUnicite;

public class Interface {
	final static String ECRANGENERAL = "General";
    final static String ECRANRECHERCHESIMPLE = "RechSimple";
    final static String ECRANRECHERCHECOMPLEXE = "RechComplexe";
    
    public void addComponentToPane(Container pane, final Graph g){
    	final JPanel listeEcran = new JPanel(new CardLayout());
    	// Ecran général
    	JPanel general = new JPanel();
    	general.setBackground(Color.WHITE);
    	general.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    
   		JLabel titre = new JLabel("Bienvenue dans la magnifique application de Génie Logiciel!");
   		c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = c.gridy = 0;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,15,0,0);
    	general.add(titre, c);
  		JLabel proposition = new JLabel("Que voulez vous faire ?");
  		c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 1;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,15,0,0);
    	general.add(proposition, c);
  		JButton boutonRechercheSimple = new JButton("Recherche simple");
  		boutonRechercheSimple.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c1 = (CardLayout)(listeEcran.getLayout());
				c1.show(listeEcran, ECRANRECHERCHESIMPLE);
			}
  		});
  		c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 2;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,15,10,10);
    	general.add(boutonRechercheSimple, c);
		JButton boutonRechercheComplexe = new JButton("Recherche complexe");
		boutonRechercheComplexe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c2 = (CardLayout)(listeEcran.getLayout());
				c2.show(listeEcran, ECRANRECHERCHECOMPLEXE);
			}
  		});
		c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 1;
    	c.gridy = 2;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,15,10,10);
   		general.add(boutonRechercheComplexe, c);
  			
  		// Ecran de recherche simple
    	JPanel rechercheSimple = new JPanel();
    	rechercheSimple.setLayout(new GridBagLayout());
    	JButton retour = new JButton("Retour au menu");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = c.gridy = 0;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,15,0,0);
    	retour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c3 = (CardLayout)(listeEcran.getLayout());
				c3.show(listeEcran, ECRANGENERAL);
			}
  		});
    	rechercheSimple.add(retour, c);
    	JLabel qRech = new JLabel("Veuillez entrer ce que vous recherchez :");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 1;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,15,0,0);
    	rechercheSimple.add(qRech, c);
    	final JTextField aRech = new JTextField();
    	aRech.setPreferredSize(new Dimension(130,28));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 1;
    	c.gridy = 1;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	rechercheSimple.add(aRech, c);
    	final JTextArea contenu = new JTextArea();
    	JButton lancerRechS = new JButton("Rechercher!");
    	lancerRechS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// Ajout du code pour la recherche manuelle
				new RechercheManuelle().executeSearch(g, aRech.getText());
				contenu.append("Résultat ????");
			}
  		});
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 2;
    	c.gridy = 1;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,10,0,20);
    	rechercheSimple.add(lancerRechS, c);
    	JScrollPane affichage = new JScrollPane();
    	affichage.setPreferredSize(new Dimension (800, 500));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 2;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,15,10,10);
    	affichage.add(contenu);
    	rechercheSimple.add(affichage, c);
    	
    	// Ecran de recherche complexe
    	JPanel rechercheComplexe = new JPanel();
    	rechercheComplexe.setLayout(new GridBagLayout());
    	JButton retour2 = new JButton("Retour au menu");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = c.gridy = 0;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,15,0,0);
    	retour2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c4 = (CardLayout)(listeEcran.getLayout());
				c4.show(listeEcran, ECRANGENERAL);
			}
  		});
    	rechercheComplexe.add(retour2, c);
    	JLabel qRech2 = new JLabel("Veuillez entrer ce que vous recherchez :");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 1;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	c.insets = new Insets(10,15,0,0);
    	rechercheComplexe.add(qRech2, c);
    	final JTextField aRech2 = new JTextField();
    	aRech2.setPreferredSize(new Dimension(130,28));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 1;
    	c.gridy = 1;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	rechercheComplexe.add(aRech2, c);
    	JPanel ensembleOptions = new JPanel();
    	JLabel parcoursTexte = new JLabel("Parcours :");
    	ensembleOptions.add(parcoursTexte);
    	final Choice parcours = new Choice();
    	parcours.addItem("Profondeur");
    	parcours.addItem("Largeur");
    	ensembleOptions.add(parcours);
    	JLabel uniciteTexte = new JLabel("Unicité :");
    	ensembleOptions.add(uniciteTexte);
    	final Choice unicite = new Choice();
    	unicite.addItem("Noeud global");
    	unicite.addItem("Relation globale");
    	ensembleOptions.add(unicite);
    	JLabel niveauTexte = new JLabel("Niveau de parcours :");
    	ensembleOptions.add(niveauTexte);
    	final JTextField niveau = new JTextField();
    	niveau.setPreferredSize(new Dimension(30,28));
    	niveau.addKeyListener(new KeyListener(){
    		public void keyTyped(KeyEvent e) {
				if(niveau.getText().length() >= 1) {
					niveau.setFocusable(false);
					niveau.setFocusable(true);
				}
			}
			public void keyPressed(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}
    	});
    	ensembleOptions.add(niveau);
    	JLabel liensTexte = new JLabel("Liens :");
    	ensembleOptions.add(liensTexte);
    	Choice liens = new Choice();
    	liens.addItem("Lien 1");
    	liens.addItem("Lien 2");
    	liens.addItem("Lien 3");
    	ensembleOptions.add(liens);
    	JLabel filtresTexte = new JLabel("Filtres :");
    	ensembleOptions.add(filtresTexte);
    	Choice filtres = new Choice();
    	filtres.addItem("Filtre 1");
    	filtres.addItem("Filtre 2");
    	filtres.addItem("Filtre 3");
    	ensembleOptions.add(filtres);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 2;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	rechercheComplexe.add(ensembleOptions, c);
    	final JTextArea contenu2 = new JTextArea();
    	JButton lancerRechC = new JButton("Rechercher!");
    	lancerRechC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// Ajout du code pour la recherche avec paramètres
				TypeRecherche trecherche;
				if (parcours.getSelectedItem().equals("largeur"))
					trecherche = TypeRecherche.LARGEUR;
				else trecherche = TypeRecherche.PROFONDEUR;
				TypeUnicite tunicite;
				if (unicite.getSelectedItem().equals("Noeud Global"))
					tunicite = TypeUnicite.NOEUD_GLOBAL;
				else tunicite = TypeUnicite.RELATION_GLOBAL;
				ArrayList<Noeud> result = new Recherche().recherche(g, aRech2.getText(), new ArrayList<Filtre>(), Integer.parseInt(niveau.getText()), trecherche, tunicite);
				contenu2.append(result.toString());
			}
  		});
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 1;
    	c.gridy = 2;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	rechercheComplexe.add(lancerRechC, c);
    	JScrollPane affichage2 = new JScrollPane();
    	affichage2.setPreferredSize(new Dimension (800, 500));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 3;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.insets = new Insets(10,15,10,10);
    	affichage2.add(contenu2);
    	rechercheComplexe.add(affichage2, c);
    	listeEcran.add(general, ECRANGENERAL);
    	listeEcran.add(rechercheSimple, ECRANRECHERCHESIMPLE);
    	listeEcran.add(rechercheComplexe, ECRANRECHERCHECOMPLEXE);
    	
    	pane.add(listeEcran, BorderLayout.CENTER);
    }
	
	public static void main (String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		final Graph g = new Graph();
		JFrame frame = new JFrame("Graphe réseau social");
		JMenuBar menu = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		JMenuItem ouvrir = new JMenuItem("Ouvrir");
		ouvrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String nomFichier = "";
				JFileChooser chooser = new JFileChooser();
				chooser.setApproveButtonText("Choisir le fichier de données");
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){	
					nomFichier = chooser.getSelectedFile().getName(); 
				}
				Parser p = new Parser(g);
				p.verifFichier(nomFichier);
			}
		});
		JMenuItem fermer = new JMenuItem("Fermer l'application");
		fichier.add(ouvrir);
		fichier.addSeparator();
		fichier.add(fermer);
		JMenu aide = new JMenu("Aide");
		JMenuItem guide = new JMenuItem("Guide de l'application");
		JMenuItem aPropos = new JMenuItem("A propos de");
		aide.add(guide);
		aide.add(aPropos);
		menu.add(fichier);
		menu.add(aide);
		frame.setJMenuBar(menu);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String nomFichier = "";
		JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText("Choisir le fichier de données");
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){	
			nomFichier = chooser.getSelectedFile().getName(); 
		}
		Parser p = new Parser(g);
		p.verifFichier(nomFichier);
		Interface appli = new Interface();
		appli.addComponentToPane(frame.getContentPane(), g);
		
		frame.pack();
		frame.setVisible(true);
	}
}
