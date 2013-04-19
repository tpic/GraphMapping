package modele;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interface {

	
	public static void main (String[] args){
		JFrame frame = new JFrame("Graphe réseau social");
		//frame.setSize(700,900);
		JPanel pane = new JPanel();
		pane.setBackground(Color.WHITE);
		JLabel titre = new JLabel("Bienvenue dans la magnifique application de Génie Logiciel!");
		JLabel proposition = new JLabel("Que voulez vous faire ?");
		JButton boutonRechercheSimple = new JButton("Recherche simple");
		JButton boutonRechercheComplexe = new JButton("Recherche complexe");
		
		boutonRechercheSimple.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		
		pane.add(titre);
		pane.add(proposition);
		pane.add(boutonRechercheSimple);
		pane.add(boutonRechercheComplexe);
		frame.getContentPane().add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
