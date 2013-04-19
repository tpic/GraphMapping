package modele;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
   		JLabel titre = new JLabel("Bienvenue dans la magnifique application de Génie Logiciel!");
  		JLabel proposition = new JLabel("Que voulez vous faire ?");
  		JButton boutonRechercheSimple = new JButton("Recherche simple");
  		boutonRechercheSimple.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c1 = (CardLayout)(listeEcran.getLayout());
				c1.show(listeEcran, ECRANRECHERCHESIMPLE);
			}
  		});
		JButton boutonRechercheComplexe = new JButton("Recherche complexe");
		boutonRechercheComplexe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c2 = (CardLayout)(listeEcran.getLayout());
				c2.show(listeEcran, ECRANRECHERCHECOMPLEXE);
			}
  		});
    	general.add(titre);
   		general.add(proposition);
   		general.add(boutonRechercheSimple, BorderLayout.CENTER);
   		general.add(boutonRechercheComplexe, BorderLayout.CENTER);
  			
  		// Ecran de recherche simple
    	JPanel rechercheSimple = new JPanel();
    	JButton retour = new JButton("Retour au menu");
    	rechercheSimple.add(retour, BorderLayout.PAGE_START);
    	JLabel qRech = new JLabel("Veuillez entrer ce que vous recherchez :");
    	JTextField aRech = new JTextField();
    	rechercheSimple.add(qRech, BorderLayout.CENTER);
    	rechercheSimple.add(aRech, BorderLayout.CENTER);
    	JButton lancerRechS = new JButton("Rechercher!");
    	rechercheSimple.add(lancerRechS, BorderLayout.CENTER);
    	
    	// Ecran de recherche complexe
    	JPanel rechercheComplexe = new JPanel();
    	rechercheComplexe.add(retour, BorderLayout.PAGE_START);
    	
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
		
		
		/*
		JPanel pane = new JPanel();
		pane.setBackground(Color.WHITE);
		
		boutonRechercheSimple.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		*/
	}
}
