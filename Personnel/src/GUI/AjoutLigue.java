package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import personnel.GestionPersonnel;
import personnel.SauvegardeImpossible;

public class AjoutLigue extends JFrame {

	GestionPersonnel gestionPersonnel;
	
	public AjoutLigue() throws SauvegardeImpossible{
		 gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		setSize(1000, 650);
        ajouteruneligue();
        setResizable(false); 
        setLocationRelativeTo(null);
	}
	
	public void Addligue() {
		
    }

	public void ajouteruneligue() {
		
		
            JPanel adde = new JPanel();	
            adde.setLayout(null); 
		    JButton leave = new javax.swing.JButton();
	        JLabel jLabel2 = new javax.swing.JLabel();
	        JTextField jTextField1 = new javax.swing.JTextField();
	        JButton jButton2 = new javax.swing.JButton();

	        setPreferredSize(new java.awt.Dimension(510, 300));
	        setLayout(null);


	        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
	        jLabel2.setText("NOM DE LA LIGUE");
	        add(jLabel2);
	        jLabel2.setBounds(40, 10, 160, 50);

	        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
	        jTextField1.setForeground(new java.awt.Color(56, 56, 56));
	        jTextField1.setToolTipText("");
	        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
	        jTextField1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            
	            }
	        });
	        add(jTextField1);
	        jTextField1.setBounds(40, 60, 420, 40);

	        jButton2.setBackground(new java.awt.Color(202, 178, 92));
	        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
	        jButton2.setText("AJOUTER LA LIGUE");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	String name = jTextField1.getText();
	            	
					try {
						gestionPersonnel.addLigue(name);
						AjoutNotif.Reussi();
						dispose();
					} catch (SauvegardeImpossible e) {
						AjoutNotif.Echoue();
						
					}	            }
	        });
	        add(jButton2);
	        jButton2.setBounds(100, 120, 290, 40);
	        
	        getContentPane().add(adde);
	        setVisible(true);
	        pack();
		
	}
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            try {
					new AjoutLigue();
				} catch (SauvegardeImpossible e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
	    }
}
