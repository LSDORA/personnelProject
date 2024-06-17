package GUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

public class EditEmploye extends JFrame {

	GestionPersonnel gestionPersonnel;
	Ligue ligue;
	Employe employe;
	
	public EditEmploye(Employe employe) throws SauvegardeImpossible{
		 gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		setSize(1000, 650);
		editunemploye(employe);
        setResizable(false); 
        setLocationRelativeTo(null);
	}
	

	public void editunemploye(Employe employe) {
		
		
       
            JPanel EmployeEditPanel = new JPanel();
        	EmployeEditPanel.setLayout(null); 
        	
        	EmployeEditPanel.setPreferredSize(new java.awt.Dimension(1000, 450));
	        
        	 JLabel NomTittle = new JLabel(employe.getNom());
             NomTittle.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
             EmployeEditPanel.add(NomTittle);
             NomTittle.setBounds(60, 44, 160, 50);
        	
            JLabel PrenomTittle = new JLabel(employe.getPrenom());
            PrenomTittle.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
            EmployeEditPanel.add(PrenomTittle);
            PrenomTittle.setBounds(260, 44, 160, 50);

            JLabel MailTittle = new JLabel(employe.getMail());
            MailTittle.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
            EmployeEditPanel.add(MailTittle);
            MailTittle.setBounds(440, 44, 200, 50);
            
            JLabel DATittle = new JLabel(employe.getdatearrive().toString());
            DATittle.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
            EmployeEditPanel.add(DATittle);
            DATittle.setBounds(660, 44, 160, 50);
            
            JLabel DETittle = new JLabel(employe.getdatedepart().toString());
            DETittle.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
            EmployeEditPanel.add(DETittle);
            DETittle.setBounds(840, 44, 160, 50);

           
        
            JTextField EnterName = new JTextField("Entrez le nom");
            EnterName.setBackground(new java.awt.Color(204, 204, 204));
            EnterName.setForeground(new java.awt.Color(56, 56, 56));
            EnterName.setToolTipText("");
            EnterName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
            EnterName.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                }
            });
            EmployeEditPanel.add(EnterName);
            EnterName.setBounds(240, 184, 310, 40);
            
            JButton ChangeName = new JButton("CHANGER LE NOM");
            ChangeName.setBackground(new java.awt.Color(202, 178, 92));
            ChangeName.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
            
            ChangeName.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	 String na = EnterName.getText();
                     employe.setNom(na);
                }
            });
            EmployeEditPanel.add(ChangeName);
            ChangeName.setBounds(550, 184, 190, 40);
            
            
             JTextField EnterPrename = new JTextField("Entrez le prénom");
            EnterPrename.setBackground(new java.awt.Color(204, 204, 204));
            EnterPrename.setForeground(new java.awt.Color(56, 56, 56));
           
            EnterPrename.setToolTipText("");
            EnterPrename.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
            EnterPrename.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                  
                }
            });
            EmployeEditPanel.add(EnterPrename);
            EnterPrename.setBounds(240, 114, 310, 40);
            
            
            
            JButton ChangePrename = new JButton("CHANGER LE PRENOM");
            ChangePrename.setBackground(new java.awt.Color(202, 178, 92));
            ChangePrename.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
            ChangePrename.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	 String pna = EnterPrename.getText();
                     employe.setPrenom(pna);
                }
            });
            EmployeEditPanel.add(ChangePrename);
            ChangePrename.setBounds(550, 114, 190, 40);
            
           
            JTextField EnterMail = new JTextField("Entrez le mail");
            EnterMail.setBackground(new java.awt.Color(204, 204, 204));
            EnterMail.setForeground(new java.awt.Color(56, 56, 56));
            EnterMail.setText("Entrez le mail");
            EnterMail.setToolTipText("");
            EnterMail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
            EnterMail.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
              
                }
            });
            EmployeEditPanel.add(EnterMail);
            EnterMail.setBounds(240, 254, 310, 40);

          
            JTextField EnterDateA = new JTextField("Entrez la date d'arrive");
            EnterDateA.setBackground(new java.awt.Color(204, 204, 204));
            EnterDateA.setForeground(new java.awt.Color(56, 56, 56));
            EnterDateA.setToolTipText("");
            EnterDateA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
            EnterDateA.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
               
                }
            });
            
            JButton ChangeMail = new JButton("CHANGER LE MAIL");
            ChangeMail.setBackground(new java.awt.Color(202, 178, 92));
            ChangeMail.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
            ChangeMail.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	 String ma = EnterMail.getText();
                     employe.setMail(ma);
                     AjoutNotif.EModif();
                }
            });
            add(ChangeMail);
            ChangeMail.setBounds(550, 254, 190, 40);
            
            EmployeEditPanel.add(EnterDateA);
            EnterDateA.setBounds(240, 324, 310, 40);
            JTextField EnterDateD = new JTextField("Entrez la date de départ");
            EnterDateD.setBackground(new java.awt.Color(204, 204, 204));
            EnterDateD.setForeground(new java.awt.Color(56, 56, 56));
            EnterDateD.setText("Entrez la date de départ");
            EnterDateD.setToolTipText("");
            EnterDateD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
            EnterDateD.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    
                }
            });
            EmployeEditPanel.add(EnterDateD);
            EnterDateD.setBounds(240, 394, 310, 40);
            
            
            JButton ChangeDATED = new JButton("CHANGER LA DATE DE DEPART");
            ChangeDATED.setBackground(new java.awt.Color(202, 178, 92));
            ChangeDATED.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
            ChangeDATED.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	String dateStr = EnterDateD.getText();
                    try {
                        // Définir le format de la date selon votre besoin
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date = LocalDate.parse(dateStr, formatter);
                        // Actions à effectuer avec la date convertie
                        employe.setdatedepart(date);
                        AjoutNotif.EModif();
                    } catch (DateTimeParseException e) {
                        // Gérer le cas où la chaîne de caractères ne peut pas être convertie en LocalDate
                    	AjoutNotif.Format();
                    }
                }
            });
            EmployeEditPanel.add(ChangeDATED);
            ChangeDATED.setBounds(550, 394, 190, 40);
            
            
            

            JButton ChangeDATEA = new JButton("CHANGER LA DATE D'ARRIVE");
            ChangeDATEA.setBackground(new java.awt.Color(202, 178, 92));
            ChangeDATEA.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
            ChangeDATEA.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	String dateStre = EnterDateA.getText();
                    try {
                        // Définir le format de la date selon votre besoin
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate datee = LocalDate.parse(dateStre, formatter);
                        // Actions à effectuer avec la date convertie
                        employe.setdatearrive(datee);
                        AjoutNotif.EModif();
                    } catch (DateTimeParseException e) {
                        // Gérer le cas où la chaîne de caractères ne peut pas être convertie en LocalDate
                    	AjoutNotif.Format();
                    }
                }
                
            });
            EmployeEditPanel.add(ChangeDATEA);
            ChangeDATEA.setBounds(550, 324, 190, 40);

            
	        
	        getContentPane().add(EmployeEditPanel);
	        setVisible(true);
	        pack();
		
	}
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        	
	        });
	    }
}
