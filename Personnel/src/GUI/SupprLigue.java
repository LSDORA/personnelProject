package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import GUI.PersonnelGUI;

import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class SupprLigue extends JFrame {

	GestionPersonnel gestionPersonnel;
	private Ligue ligue;
	
	public SupprLigue(Ligue ligue) throws SauvegardeImpossible{
		 gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		setSize(1000, 650);
		this.ligue = ligue;
        supprimer(ligue);
        setResizable(false); 
        setLocationRelativeTo(null);
	}
	
	

	public void supprimer(Ligue ligue) {
		
		
            JPanel suppr = new JPanel();	
            suppr.setLayout(null); 
		  JButton leave = new javax.swing.JButton();
	        JLabel jLabel2 = new javax.swing.JLabel();
	        JTextField jTextField1 = new javax.swing.JTextField();
	        JButton jButton2 = new javax.swing.JButton();

	        setPreferredSize(new java.awt.Dimension(510, 210));
	        setLayout(null);


	        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
	        jLabel2.setText("ETES VOUS SURE DE VOULOIR SUPPRIMER CETTE LIGUE");
	        add(jLabel2);
	        jLabel2.setBounds(10, 30, 480, 50);

	        jButton2.setBackground(new java.awt.Color(153, 0, 0));
	        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
	        jButton2.setForeground(new java.awt.Color(255, 255, 255));
	        jButton2.setText("OUI");
	        jButton2.setBorder(null);
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	              try {
					ligue.remove();
					dispose();
				
				} catch (SauvegardeImpossible e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            }
	        });
	        add(jButton2);
	        jButton2.setBounds(100, 90, 290, 40);

	        
	        getContentPane().add(suppr);
	        setVisible(true);
	        pack();
		
	}
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        });
	    }
}
