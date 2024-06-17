package GUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class AjoutEmployer extends JFrame {

	GestionPersonnel gestionPersonnel;
	Ligue ligue;
	Employe employe;
	
	public AjoutEmployer(Ligue ligue) throws SauvegardeImpossible{
		 gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		setSize(1000, 650);
		ajouterunemploye(ligue);
        setResizable(false); 
        setLocationRelativeTo(null);
	}
	

	public void ajouterunemploye(Ligue ligue) {
		
		
            JPanel addem = new JPanel();	
            addem.setLayout(null); 
		 
	        JLabel jLabel2 = new javax.swing.JLabel();
	        JTextField jTextField1 = new javax.swing.JTextField();
	        JButton jButton2 = new javax.swing.JButton();

	        JButton jButton1 = new javax.swing.JButton();
	        JTextField jTextField2 = new javax.swing.JTextField();
	        JLabel jLabel3 = new javax.swing.JLabel();
	        JTextField jTextField3 = new javax.swing.JTextField();
	        JLabel jLabel4 = new javax.swing.JLabel();
	        JTextField jTextField4 = new javax.swing.JTextField();
	        JLabel jLabel5 = new javax.swing.JLabel();
	        JTextField jTextField5 = new javax.swing.JTextField();
	        JLabel jLabel6 = new javax.swing.JLabel();
	        JTextField jTextField6 = new javax.swing.JTextField();
	        JLabel jLabel7 = new javax.swing.JLabel();

	        setPreferredSize(new java.awt.Dimension(700, 400));
	        addem.setLayout(null);

	        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
	        jLabel2.setText("NOM");
	        addem.add(jLabel2);
	        jLabel2.setBounds(40, 20, 190, 50);

	        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
	        jTextField1.setForeground(new java.awt.Color(56, 56, 56));
	        jTextField1.setToolTipText("");
	        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
	        jTextField1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	             
	            }
	        });
	        addem.add(jTextField1);
	        jTextField1.setBounds(40, 70, 190, 30);

	        jButton2.setBackground(new java.awt.Color(202, 178, 92));
	        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
	        jButton2.setText("CREER L'EMPLOYE");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	String nom = jTextField1.getText();
	            	String prenom = jTextField2.getText();
	            	String mail = jTextField3.getText();
	            	String mdp = jTextField4.getText();
	            	String dd = jTextField5.getText();
	            	String da = jTextField6.getText();
	            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            	LocalDate ddeb =LocalDate.parse(dd, formatter);
	            	LocalDate darr =LocalDate.parse(da, formatter);
	            	
	            	try {
						ligue.addEmploye(nom, prenom, mail, mdp, darr, ddeb);
						AjoutNotif.AEReussi();
						dispose();
					} catch (SauvegardeImpossible e) {
						
						AjoutNotif.AEEchoue();
					} catch (IllegalArgumentException e) {
						AjoutNotif.Date();
					}
	            }
	        });
	        addem.add(jButton2);
	        jButton2.setBounds(200, 230, 290, 40);

	        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
	        jTextField2.setForeground(new java.awt.Color(56, 56, 56));
	        jTextField2.setToolTipText("");
	        jTextField2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
	        jTextField2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	         
	            }
	        });
	        addem.add(jTextField2);
	        jTextField2.setBounds(250, 70, 190, 30);

	        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
	        jLabel3.setText("PRENOM");
	        addem.add(jLabel3);
	        jLabel3.setBounds(250, 20, 190, 50);

	        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
	        jTextField3.setForeground(new java.awt.Color(56, 56, 56));
	        jTextField3.setToolTipText("");
	        jTextField3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
	        jTextField3.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	           
	            }
	        });
	        addem.add(jTextField3);
	        jTextField3.setBounds(460, 70, 190, 30);

	        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
	        jLabel4.setText("MAIL");
	        addem.add(jLabel4);
	        jLabel4.setBounds(460, 20, 190, 50);

	        jTextField4.setBackground(new java.awt.Color(204, 204, 204));
	        jTextField4.setForeground(new java.awt.Color(56, 56, 56));
	        jTextField4.setToolTipText("");
	        jTextField4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
	        jTextField4.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                
	            }
	        });
	        addem.add(jTextField4);
	        jTextField4.setBounds(40, 180, 190, 30);

	        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
	        jLabel5.setText("MOT DE PASSE");
	        addem.add(jLabel5);
	        jLabel5.setBounds(40, 130, 190, 50);

	        jTextField5.setBackground(new java.awt.Color(204, 204, 204));
	        jTextField5.setForeground(new java.awt.Color(56, 56, 56));
	        jTextField5.setText("YYYY-MM-DD");
	        jTextField5.setToolTipText("");
	        jTextField5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
	        jTextField5.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	           
	            }
	        });
	        addem.add(jTextField5);
	        jTextField5.setBounds(250, 180, 190, 30);

	        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
	        jLabel6.setText("DATE DE DEPART");
	        addem.add(jLabel6);
	        jLabel6.setBounds(250, 130, 190, 50);

	        jTextField6.setBackground(new java.awt.Color(204, 204, 204));
	        jTextField6.setForeground(new java.awt.Color(56, 56, 56));
	        jTextField6.setText("YYYY-MM-DD");
	        jTextField6.setToolTipText("");
	        jTextField6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
	        jTextField6.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            
	            }
	        });
	        addem.add(jTextField6);
	        jTextField6.setBounds(460, 180, 190, 30);

	        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
	        jLabel7.setText("DATE D'ARRIVEE");
	        addem.add(jLabel7);
	        jLabel7.setBounds(460, 130, 190, 50);
	        
	        getContentPane().add(addem);
	        setVisible(true);
	        pack();
		
	}
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        	
	        });
	    }
}
