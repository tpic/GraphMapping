package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Interface {
	final static String ECRANGENERAL = "General";
    final static String ECRANRECHERCHESIMPLE = "RechSimple";
    final static String ECRANRECHERCHECOMPLEXE = "RechComplexe";
    
    public void addComponentToPane(Container pane){
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
    	JTextField aRech = new JTextField();
    	aRech.setPreferredSize(new Dimension(130,28));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 1;
    	c.gridy = 1;
    	c.gridheight = GridBagConstraints.REMAINDER;
    	c.gridheight = 1;
    	rechercheSimple.add(aRech, c);
    	JButton lancerRechS = new JButton("Rechercher!");
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
    	rechercheSimple.add(affichage, c);
    	
    	// Ecran de recherche complexe
    	JPanel rechercheComplexe = new JPanel();
    	JButton retour2 = new JButton("Retour au menu");
    	retour2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c4 = (CardLayout)(listeEcran.getLayout());
				c4.show(listeEcran, ECRANGENERAL);
			}
  		});
    	rechercheComplexe.add(retour2, BorderLayout.PAGE_START);
    	
    	listeEcran.add(general, ECRANGENERAL);
    	listeEcran.add(rechercheSimple, ECRANRECHERCHESIMPLE);
    	listeEcran.add(rechercheComplexe, ECRANRECHERCHECOMPLEXE);
    	
    	pane.add(listeEcran, BorderLayout.CENTER);
    }
	
	public static void main (String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		JFrame frame = new JFrame("Graphe réseau social");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Interface appli = new Interface();
		appli.addComponentToPane(frame.getContentPane());
		
		frame.pack();
		frame.setVisible(true);
	}
}
