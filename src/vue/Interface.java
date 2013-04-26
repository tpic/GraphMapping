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
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import modele.Sens;
import modele.TypeRecherche;
import modele.TypeUnicite;

public class Interface {
	final static String ECRANGENERAL = "General";
	final static String ECRANRECHERCHESIMPLE = "RechSimple";
	final static String ECRANRECHERCHECOMPLEXE = "RechComplexe";
	final static String ECRANAFFICHAGE = "AffichGraph";

	public void addComponentToPane(Container pane, final Graph g) {
		final JPanel listeEcran = new JPanel(new CardLayout());
		// Ecran général
		JPanel general = new JPanel();
		general.setBackground(Color.WHITE);
		general.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel titre = new JLabel("Bienvenue dans l'application de Génie Logiciel!");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = c.gridy = 0;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 15, 0, 0);
		general.add(titre, c);
		JLabel proposition = new JLabel("Que voulez vous faire ?");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 15, 0, 0);
		general.add(proposition, c);
		JButton boutonRechercheSimple = new JButton("Recherche simple");
		boutonRechercheSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c1 = (CardLayout) (listeEcran.getLayout());
				c1.show(listeEcran, ECRANRECHERCHESIMPLE);
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 15, 10, 10);
		general.add(boutonRechercheSimple, c);
		JButton boutonRechercheComplexe = new JButton("Recherche complexe");
		boutonRechercheComplexe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c2 = (CardLayout) (listeEcran.getLayout());
				c2.show(listeEcran, ECRANRECHERCHECOMPLEXE);
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 15, 10, 10);
		general.add(boutonRechercheComplexe, c);
		JButton boutonAffichageGraphe = new JButton("Afficher graphe");
		boutonAffichageGraphe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c5 = (CardLayout) (listeEcran.getLayout());
				c5.show(listeEcran, ECRANAFFICHAGE);
			}
		});
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(10, 15, 10, 10);
		general.add(boutonAffichageGraphe, c);

		// Ecran de recherche simple
		JPanel rechercheSimple = new JPanel();
		rechercheSimple.setLayout(new GridBagLayout());
		JButton retour = new JButton("Retour au menu");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = c.gridy = 0;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 15, 0, 0);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c3 = (CardLayout) (listeEcran.getLayout());
				c3.show(listeEcran, ECRANGENERAL);
			}
		});
		rechercheSimple.add(retour, c);
		JLabel qRech = new JLabel("Veuillez sélectionner ce que vous recherchez :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 15, 0, 0);
		rechercheSimple.add(qRech, c);
		final Choice aRech = new Choice();
		for (Noeud n : g.getGraph())
			aRech.addItem(n.getName());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		rechercheSimple.add(aRech, c);
		final JTextArea contenu = new JTextArea("Résultat : ");
		JScrollPane contenuScroll = new JScrollPane(contenu);
		contenu.setEditable(false);
		JButton lancerRechS = new JButton("Rechercher!");
		lancerRechS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Ajout du code pour la recherche manuelle
				new RechercheManuelle().executeSearchInterface(g, aRech.getSelectedItem(), contenu);
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 0, 20);
		rechercheSimple.add(lancerRechS, c);
		contenuScroll.setPreferredSize(new Dimension(800, 500));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 15, 10, 10);
		rechercheSimple.add(contenu, c);

		// Ecran de recherche complexe
		JPanel rechercheComplexe = new JPanel();
		rechercheComplexe.setLayout(new GridBagLayout());
		JButton retour2 = new JButton("Retour au menu");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = c.gridy = 0;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 15, 0, 0);
		retour2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c4 = (CardLayout) (listeEcran.getLayout());
				c4.show(listeEcran, ECRANGENERAL);
			}
		});
		rechercheComplexe.add(retour2, c);
		JLabel qRech2 = new JLabel("Veuillez sélectionner ce que vous recherchez :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 15, 0, 0);
		rechercheComplexe.add(qRech2, c);
		final Choice aRech2 = new Choice();
		for (Noeud n : g.getGraph())
			aRech2.addItem(n.getName());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		rechercheComplexe.add(aRech2, c);
		final JPanel ensembleOptions = new JPanel();
		ensembleOptions.setLayout(new GridBagLayout());
		JLabel lien1 = new JLabel("Lien 1 :");
		final Choice liste1 = new Choice();
		JLabel sens1 = new JLabel("Sens du lien 1 :");
		final Choice listeSens1 = new Choice();
		final JCheckBox filtreN2 = new JCheckBox("2 liens", false);
		JLabel lien2 = new JLabel("Lien 2 :");
		final Choice liste2 = new Choice();
		JLabel sens2 = new JLabel("Sens du lien 2 :");
		final Choice listeSens2 = new Choice();
		final JCheckBox filtreN3 = new JCheckBox("3 liens", false);
		JLabel lien3 = new JLabel("Lien 3 :");
		final Choice liste3 = new Choice();
		JLabel sens3 = new JLabel("Sens du lien 3 :");
		final Choice listeSens3 = new Choice();
		final JCheckBox filtreN4 = new JCheckBox("4 liens", false);
		JLabel lien4 = new JLabel("Lien 4 :");
		final Choice liste4 = new Choice();
		JLabel sens4 = new JLabel("Sens du lien 4 :");
		final Choice listeSens4 = new Choice();
		ArrayList<String> listRelation = g.getRelationsGraph();
		for (String s : listRelation) {
			liste1.addItem(s);
			liste2.addItem(s);
			liste3.addItem(s);
			liste4.addItem(s);
		}
		listeSens1.addItem("Entrant");
		listeSens1.addItem("Sortant");
		listeSens1.addItem("Les 2");
		listeSens2.addItem("Entrant");
		listeSens2.addItem("Sortant");
		listeSens2.addItem("Les 2");
		listeSens3.addItem("Entrant");
		listeSens3.addItem("Sortant");
		listeSens3.addItem("Les 2");
		listeSens4.addItem("Entrant");
		listeSens4.addItem("Sortant");
		listeSens4.addItem("Les 2");
		c.gridx = 1;
		c.gridy = 0;
		ensembleOptions.add(lien1, c);
		c.gridx = 2;
		c.gridy = 0;
		ensembleOptions.add(liste1, c);
		c.gridx = 3;
		c.gridy = 0;
		ensembleOptions.add(sens1, c);
		c.gridx = 4;
		c.gridy = 0;
		ensembleOptions.add(listeSens1, c);
		c.gridx = 0;
		c.gridy = 1;
		ensembleOptions.add(filtreN2, c);
		c.gridx = 1;
		c.gridy = 1;
		ensembleOptions.add(lien2, c);
		c.gridx = 2;
		c.gridy = 1;
		ensembleOptions.add(liste2, c);
		c.gridx = 3;
		c.gridy = 1;
		ensembleOptions.add(sens2, c);
		c.gridx = 4;
		c.gridy = 1;
		ensembleOptions.add(listeSens2, c);
		c.gridx = 0;
		c.gridy = 2;
		ensembleOptions.add(filtreN3, c);
		c.gridx = 1;
		c.gridy = 2;
		ensembleOptions.add(lien3, c);
		c.gridx = 2;
		c.gridy = 2;
		ensembleOptions.add(liste3, c);
		c.gridx = 3;
		c.gridy = 2;
		ensembleOptions.add(sens3, c);
		c.gridx = 4;
		c.gridy = 2;
		ensembleOptions.add(listeSens3, c);
		c.gridx = 0;
		c.gridy = 3;
		ensembleOptions.add(filtreN4, c);
		c.gridx = 1;
		c.gridy = 3;
		ensembleOptions.add(lien4, c);
		c.gridx = 2;
		c.gridy = 3;
		ensembleOptions.add(liste4, c);
		c.gridx = 3;
		c.gridy = 3;
		ensembleOptions.add(sens4, c);
		c.gridx = 4;
		c.gridy = 3;
		ensembleOptions.add(listeSens4, c);
		JLabel parcoursTexte = new JLabel("Parcours :");
		c.gridx = 5;
		c.gridy = 0;
		ensembleOptions.add(parcoursTexte, c);
		final Choice parcours = new Choice();
		parcours.addItem("Profondeur");
		parcours.addItem("Largeur");
		c.gridx = 6;
		c.gridy = 0;
		ensembleOptions.add(parcours, c);
		JLabel uniciteTexte = new JLabel("Unicité :");
		c.gridx = 5;
		c.gridy = 1;
		ensembleOptions.add(uniciteTexte, c);
		final Choice unicite = new Choice();
		unicite.addItem("Noeud global");
		unicite.addItem("Relation globale");
		c.gridx = 6;
		c.gridy = 1;
		ensembleOptions.add(unicite, c);
		JLabel niveauTexte = new JLabel("Niveau de parcours :");
		c.gridx = 5;
		c.gridy = 2;
		ensembleOptions.add(niveauTexte, c);
		final JTextField niveau = new JTextField();
		niveau.setText("" + Recherche.ALL_GRAPH);
		niveau.setPreferredSize(new Dimension(30, 28));
		niveau.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (niveau.getText().length() >= 1) {
					niveau.setFocusable(false);
					niveau.setFocusable(true);
				}
			}

			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
			}
		});
		c.gridx = 6;
		c.gridy = 2;
		ensembleOptions.add(niveau, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		rechercheComplexe.add(ensembleOptions, c);
		final JTextArea contenu2 = new JTextArea();
		JScrollPane contenuScroll2 = new JScrollPane(contenu2);
		contenu2.setEditable(false);
		JButton lancerRechC = new JButton("Rechercher!");
		lancerRechC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Ajout du code pour la recherche avec paramètres
				TypeRecherche trecherche;
				if (parcours.getSelectedItem().equals("largeur"))
					trecherche = TypeRecherche.LARGEUR;
				else
					trecherche = TypeRecherche.PROFONDEUR;
				TypeUnicite tunicite;
				if (unicite.getSelectedItem().equals("Noeud Global"))
					tunicite = TypeUnicite.NOEUD_GLOBAL;
				else
					tunicite = TypeUnicite.RELATION_GLOBAL;
				ArrayList<Filtre> listeFiltre = new ArrayList<Filtre>();
				if (listeSens1.getSelectedItem().equals("Entrant")) {
					Filtre f1 = new Filtre(liste1.getSelectedItem(), Sens.IN);
					listeFiltre.add(f1);
				} else {
					if (listeSens1.getSelectedItem().equals("Sortant")) {
						Filtre f1 = new Filtre(liste1.getSelectedItem(), Sens.OUT);
						listeFiltre.add(f1);
					} else {
						Filtre f1 = new Filtre(liste1.getSelectedItem(), Sens.INOUT);
						listeFiltre.add(f1);
					}
				}
				if (filtreN2.isSelected()) {
					if (listeSens2.getSelectedItem().equals("Entrant")) {
						Filtre f2 = new Filtre(liste2.getSelectedItem(), Sens.IN);
						listeFiltre.add(f2);
					} else {
						if (listeSens2.getSelectedItem().equals("Sortant")) {
							Filtre f2 = new Filtre(liste2.getSelectedItem(), Sens.OUT);
							listeFiltre.add(f2);
						} else {
							Filtre f2 = new Filtre(liste2.getSelectedItem(), Sens.INOUT);
							listeFiltre.add(f2);
						}
					}
				}
				if (filtreN3.isSelected()) {
					if (listeSens3.getSelectedItem().equals("Entrant")) {
						Filtre f3 = new Filtre(liste3.getSelectedItem(), Sens.IN);
						listeFiltre.add(f3);
					} else {
						if (listeSens3.getSelectedItem().equals("Sortant")) {
							Filtre f3 = new Filtre(liste3.getSelectedItem(), Sens.OUT);
							listeFiltre.add(f3);
						} else {
							Filtre f3 = new Filtre(liste3.getSelectedItem(), Sens.INOUT);
							listeFiltre.add(f3);
						}
					}
				}
				if (filtreN4.isSelected()) {
					if (listeSens4.getSelectedItem().equals("Entrant")) {
						Filtre f4 = new Filtre(liste4.getSelectedItem(), Sens.IN);
						listeFiltre.add(f4);
					} else {
						if (listeSens4.getSelectedItem().equals("Sortant")) {
							Filtre f4 = new Filtre(liste4.getSelectedItem(), Sens.OUT);
							listeFiltre.add(f4);
						} else {
							Filtre f4 = new Filtre(liste4.getSelectedItem(), Sens.INOUT);
							listeFiltre.add(f4);
						}
					}
				}
				int niv;
				try {
					niv = Integer.parseInt(niveau.getText());
				} catch (Exception e) {
					niv = 0;
				}
				ArrayList<Noeud> result = Recherche.recherche(g, aRech2.getSelectedItem(),
						listeFiltre, niv, trecherche, tunicite);
				if (result == null)
					contenu2.setText("La recherche n'a pas pu aboutir : aucun résultat.");
				else {
					StringBuffer res = new StringBuffer();
					for (Noeud n : result) {
						res.append(n.getName() + "\n");
					}
					contenu2.setText(res.toString());
				}
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		rechercheComplexe.add(lancerRechC, c);
		contenuScroll2.setPreferredSize(new Dimension(800, 500));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 15, 10, 10);
		rechercheComplexe.add(contenu2, c);

		// Ecran affichage
		JPanel affichageGraphe = new JPanel();
		affichageGraphe.setLayout(new GridBagLayout());
		JButton retour3 = new JButton("Retour au menu");
		retour3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c4 = (CardLayout) (listeEcran.getLayout());
				c4.show(listeEcran, ECRANGENERAL);
			}
		});
		c.gridx = c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(10, 15, 0, 0);
		affichageGraphe.add(retour3, c);
		JLabel affichTexte = new JLabel("Vous pouvez visualiser le graphe en cliquant : ");
		final JTextArea contenu3 = new JTextArea();
		JScrollPane contenuScroll3 = new JScrollPane(contenu3);
		JButton affichBouton = new JButton("Ici!");
		affichBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String grapheTab = "";
				for (Noeud n : g.getGraph())
					grapheTab += n.toString() + "\n";
				contenu3.setText(grapheTab);
			}
		});
		contenu3.setEditable(false);
		contenuScroll3.setPreferredSize(new Dimension(800, 500));
		c.gridx = 0;
		c.gridy = 1;
		affichageGraphe.add(affichTexte, c);
		c.gridx = 1;
		c.gridy = 1;
		affichageGraphe.add(affichBouton, c);
		c.gridx = 0;
		c.gridy = 2;
		affichageGraphe.add(contenuScroll3, c);

		listeEcran.add(general, ECRANGENERAL);
		listeEcran.add(rechercheSimple, ECRANRECHERCHESIMPLE);
		listeEcran.add(rechercheComplexe, ECRANRECHERCHECOMPLEXE);
		listeEcran.add(affichageGraphe, ECRANAFFICHAGE);

		pane.add(listeEcran, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		final Graph g = new Graph();
		final JFrame frame = new JFrame("Graphe réseau social");
		JMenuBar menu = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		JMenuItem fermer = new JMenuItem("Fermer l'application");
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		fichier.addSeparator();
		fichier.add(fermer);
		menu.add(fichier);
		frame.setJMenuBar(menu);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String nomFichier = "";
		JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText("Choisir le fichier de données");
		while (nomFichier == "") {
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				nomFichier = chooser.getSelectedFile().getName();
			}
		}
		Parser p = new Parser(g);
		try {
			p.verifFichier(nomFichier);
		} catch (IOException e) {
			System.out.println("Erreur lors du chargement du fichier");
			System.out.println(e.getMessage());
			System.exit(0);
		}
		Interface appli = new Interface();
		appli.addComponentToPane(frame.getContentPane(), g);
		frame.pack();
		frame.setVisible(true);
	}
}
