package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import GUI.AjoutLigue;
import commandLine.EmployeConsole;
import commandLine.LigueConsole;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;
import commandLine.PersonnelConsole;

public class PersonnelGUI extends JFrame {
    PersonnelConsole personnelConsole;
	private GestionPersonnel gestionPersonnel;
	private EmployeConsole employeConsole;
	private Employe employe;
	private LigueConsole ligueConsole;
	

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;

    public PersonnelGUI() throws SauvegardeImpossible {
    	 gestionPersonnel = GestionPersonnel.getGestionPersonnel();
         employeConsole = new EmployeConsole();
         ligueConsole = new LigueConsole(gestionPersonnel, employeConsole);
         
        setTitle("Gestion du personnel des ligues");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setResizable(false); 
        afficherPagePassword();
    }

    
    private void afficherPagePassword() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setMaximumSize(new java.awt.Dimension(1000, 650));
        setMinimumSize(new java.awt.Dimension(1000, 650));
        setPreferredSize(new java.awt.Dimension(1000, 650));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("LOGIN");
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 650));
        jPanel1.setName("LOGIN"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 650));
        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\aymer\\Downloads\\Rectangle 7.png")); // NOI18N
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setMaximumSize(new java.awt.Dimension(500, 650));
        jLabel1.setMinimumSize(new java.awt.Dimension(500, 650));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(500, 650));
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 500, 650);

        jPanel2.setBackground(new java.awt.Color(56, 56, 56));
        jPanel2.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText("identifiant :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(242, 242, 242));
        jLabel4.setText("Mot de passe :");

        jTextField1.setBackground(new java.awt.Color(56, 56, 56));
        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setToolTipText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(56, 56, 56));
        jTextField2.setForeground(new java.awt.Color(204, 204, 204));
        jTextField2.setToolTipText("");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(202, 178, 92));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 14));
        jButton1.setText("CONNEXION");

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = jTextField2.getText();
                String email = jTextField1.getText();
                

                Employe employe = verifierIdentifiants(email, password);
                if (employe != null) {
                    System.out.println("Le mot de passe est bon");
                    // Ajoutez ici le code pour afficher les fonctionnalités
                    jPanel1.setVisible(false);
                    afficheRootPanel(employe);

                } else {
                    System.out.println("Le mot de passe est incorrect");
                    // Ajoutez ici le code pour gérer le cas où le mot de passe est incorrect
                }
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(84, 84, 84)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(114, 114, 114)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(500, 230, 500, 420);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(202, 178, 92));
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\aymer\\Downloads\\MAISON DES LIGUES (3).png")); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(530, 100, 430, 81);

        getContentPane().add(jPanel1);
        setVisible(true);
        pack();

    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {

    }
    
    private void compte(Employe employe,Ligue ligue) {
    	getContentPane().removeAll();
    	JPanel compte = new JPanel();
    	
    	 JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
         JTable jTable1 = new javax.swing.JTable();
         jLabel1 = new javax.swing.JLabel();
         jLabel3 = new javax.swing.JLabel();
         jLabel4 = new javax.swing.JLabel();
         jButton3 = new javax.swing.JButton();
         jButton1 = new javax.swing.JButton();
         jButton2 = new javax.swing.JButton();
         jLabel5 = new javax.swing.JLabel();

         setPreferredSize(new java.awt.Dimension(1000, 550));
         setLayout(null);


         Set<Ligue> liguesSet = gestionPersonnel.getLigues();
         List<Ligue> liguesList = new ArrayList<>();
         List<Employe> employesList = new ArrayList<>();

         for (Ligue ligue1 : liguesSet) {
             for (Employe employe1 : ligue1.getEmployes()) {
                 liguesList.add(ligue1);
                 employesList.add(employe1);
             }
         }

         String[] columnNames = {"Nom", "Prenom", "Mail", "Action"};
         Object[][] data = new Object[employesList.size()][3];

         for (int i = 0; i < employesList.size(); i++) {
             Employe employe1 = employesList.get(i);
             data[i][0] = employe1.getNom();
             data[i][1] = employe1.getPrenom();
             data[i][2] = employe1.getMail();
             
         }
         
         jTable1.setFocusable(false);
         jTable1.setGridColor(new java.awt.Color(204, 204, 0));
         jTable1.setRequestFocusEnabled(false);
         jTable1.setRowSelectionAllowed(false);
         jTable1.setVerifyInputWhenFocusTarget(false);
         jScrollPane1.setViewportView(jTable1);

         add(jScrollPane1);
         jScrollPane1.setBounds(50, 150, 640, 340);

         jLabel1.setBackground(new java.awt.Color(56, 56, 56));
         jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
         jLabel1.setForeground(new java.awt.Color(202, 178, 92));
         jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
         jLabel1.setText("ADMIN DE LA LIGUE : "+ligue.getAdministrateur().getNom()+" "+ligue.getAdministrateur().getPrenom()+" "+ligue.getAdministrateur().getMail());
         jLabel1.setOpaque(true);
         add(jLabel1);
         jLabel1.setBounds(0, 0, 860, 70);

         jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
         jLabel3.setText("MEMBRE DE MA LIGUE :");
         add(jLabel3);
         jLabel3.setBounds(50, 90, 450, 50);

         jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
         jLabel4.setText("CHANGER SON MOT DE PASSE");
         add(jLabel4);
         jLabel4.setBounds(710, 170, 280, 50);

         jButton3.setBackground(new java.awt.Color(56, 56, 56));
         jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
         jButton3.setForeground(new java.awt.Color(202, 178, 92));
         jButton3.setText("QUITTER");
         jButton3.setBorder(null);
         jButton3.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                afficheRootPanel(employe);
             }
         });
         add(jButton3);
         jButton3.setBounds(853, 0, 150, 70);

         jButton1.setBackground(new java.awt.Color(202, 178, 92));
         jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
         jButton1.setText("CHANGER");
         jButton1.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                
             }
         });
         add(jButton1);
         jButton1.setBounds(720, 230, 250, 40);

         jButton2.setBackground(new java.awt.Color(202, 178, 92));
         jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
         jButton2.setText("CHANGER");
         jButton2.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 
             }
         });
         add(jButton2);
         jButton2.setBounds(720, 380, 250, 40);

         jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
         jLabel5.setText("CHANGER SON ADRESSE MAIL");
         add(jLabel5);
         jLabel5.setBounds(710, 320, 280, 50);
    
    	
    	getContentPane().add(compte);
    	revalidate();
    	pack();
    	
    	
    }

    private void afficheRootPanel(Employe employe) {
        getContentPane().removeAll(); // Supprime tous les composants existants

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(null); // Utilisation d'un layout null pour positionner précisément les composants

        
        JButton consulterButton = new JButton("CONSULTER SON COMPTE");
        consulterButton.setBackground(new Color(56, 56, 56));
        consulterButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        consulterButton.setForeground(new Color(202, 178, 92));
        consulterButton.setBounds(0, 0, 260, 71);
        consulterButton.setBorder(null);
        consulterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           compte(employe,employe.getLigue());           }
        });
        rootPanel.add(consulterButton);

        JButton retourButton = new JButton("RETOUR");
        retourButton.setBackground(new Color(204, 0, 0));
        retourButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        retourButton.setForeground(Color.WHITE);
        retourButton.setBounds(790, 0, 220, 71);
        retourButton.setBorder(null);
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherPagePassword(); // Redirection vers la page de connexion
                rootPanel.setVisible(false); // Cacher le rootPanel après redirection
            }
        });
        rootPanel.add(retourButton);

        JButton gererButton = new JButton("GERER LES LIGUES");
        gererButton.setBackground(new Color(56, 56, 56));
        gererButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        gererButton.setForeground(new Color(202, 178, 92));
        gererButton.setBounds(260, 0, 270, 71);
        gererButton.setBorder(null);
        gererButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	afficheRootPanell(employe);
            	rootPanel.setVisible(false);
            }
        });
        rootPanel.add(gererButton);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("C:\\Users\\aymer\\Downloads\\des-sports 1.png")); // Spécifiez votre chemin d'image correct ici
        imageLabel.setBounds(340, 180, 320, 300);
        rootPanel.add(imageLabel);

        JButton gererRootButton = new JButton("GERER LE ROOT");
        gererRootButton.setBackground(new Color(56, 56, 56));
        gererRootButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        gererRootButton.setForeground(new Color(202, 178, 92));
        gererRootButton.setBorder(null);
        gererRootButton.setBounds(530, 0, 260, 71);
        gererRootButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Employe employu = gestionPersonnel.getRoot();
				employereditroot(employu); 
            }
        });
        
     
        rootPanel.add(gererRootButton);

        getContentPane().add(rootPanel);
        revalidate(); // Revalider le contenu du frame
        repaint(); // Redessiner le frame
    }

    private void afficheRootPanell(Employe employe) {
        getContentPane().removeAll(); // Supprime tous les composants existants

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(null); // Utilisation d'un layout null pour positionner précisément les composants

        JButton AfficheLigue = new JButton("AFFICHER LES LIGUES");
        AfficheLigue.setBackground(new java.awt.Color(56, 56, 56));
        AfficheLigue.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        AfficheLigue.setForeground(new java.awt.Color(202, 178, 92));
        AfficheLigue.setBorder(null);
        AfficheLigue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	afficheAllLigue();
            	rootPanel.setVisible(false);
            }
        });
        rootPanel.add(AfficheLigue);
        AfficheLigue.setBounds(180, 0, 200, 71);
   

        JButton Retour = new JButton("RETOUR");
        Retour.setBackground(new java.awt.Color(204, 0, 0));
        Retour.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Retour.setForeground(new java.awt.Color(255, 255, 255));
        Retour.setBorder(null);
        Retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               afficheRootPanel(employe);
               rootPanel.setVisible(false);
            }
        });
        rootPanel.add(Retour);
        Retour.setBounds(790, 0, 220, 71);
        
        JButton Addligue = new JButton("AJOUTER UNE LIGUE");
        Addligue.setBackground(new java.awt.Color(56, 56, 56));
        Addligue.setBorder(null);
        Addligue.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        Addligue.setForeground(new java.awt.Color(202, 178, 92));
        Addligue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	  AjoutLigue addLigueFrame;
				try {
					addLigueFrame = new AjoutLigue();
					 addLigueFrame.setVisible(true);
				} catch (SauvegardeImpossible e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	 
            }
        });
        rootPanel.add(Addligue);
        Addligue.setBounds(380, 0, 210, 71);
        
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\aymer\\Downloads\\des-sports 1.png")); // NOI18N
        rootPanel.add(imageLabel);
        imageLabel.setBounds(340, 180, 320, 300);

        JButton Selectligue = new JButton("SELECTIONNER UNE LIGUE");
        Selectligue.setBorder(null);
        Selectligue.setBackground(new java.awt.Color(56, 56, 56));
        Selectligue.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        Selectligue.setForeground(new java.awt.Color(202, 178, 92));
        rootPanel.add(Selectligue);
        Selectligue.setBounds(590, 0, 200, 71);
        Selectligue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 
					selectLigue();
					rootPanel.setVisible(true);
				
            	 
            }
        });
        
        JLabel Tittle = new JLabel();
        Tittle.setBackground(new java.awt.Color(56, 56, 56));
        Tittle.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); 
        Tittle.setForeground(new java.awt.Color(202, 178, 92));
        Tittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tittle.setText("GERER LES LIGUES");
        rootPanel.add(Tittle);
        Tittle.setBounds(0, 0, 180, 70);


        getContentPane().add(rootPanel);
        revalidate(); // Revalider le contenu du frame
        repaint(); // Redessiner le frame
    }
 
    private void afficheAllLigue() {
        getContentPane().removeAll(); // Supprime tous les composants existants

        JPanel liguePanel = new JPanel();
        liguePanel.setLayout(null); // Utilisation d'un layout null pour positionner précisément les composants

        JButton Quitter = new JButton("QUITTER");
        Quitter.setBackground(new java.awt.Color(56, 56, 56));
        Quitter.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Quitter.setForeground(new java.awt.Color(202, 178, 92));
        Quitter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liguePanel.setVisible(false);
                afficheRootPanell(employe);
            }
        });
        liguePanel.add(Quitter);
        Quitter.setBounds(-7, 0, 1000, 71);

        Set<Ligue> liguesSet = gestionPersonnel.getLigues();
        // Récupération des données de gestionPersonnel.getLigues()
        List<Ligue> liguesList = new ArrayList<>(liguesSet);

        // Création du modèle de tableau avec les données récupérées
        String[] columnNames = {"Nom de la ligue", "admin"};
        Object[][] data = new Object[liguesList.size()][2];

        for (int i = 0; i < liguesList.size(); i++) {
            Ligue ligue = liguesList.get(i);
            data[i][0] = ligue.getNom(); // Assurez-vous d'adapter cette partie en fonction de votre classe Ligue
            data[i][1] = ligue.getAdministrateur().getNom(); // Assurez-vous d'adapter cette partie en fonction de votre classe Ligue
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rendre toutes les cellules non éditables
            }
        };


        JTable ligueTable = new JTable(data, columnNames);
        JScrollPane liguesScrollPane = new JScrollPane(ligueTable);
        liguePanel.add(liguesScrollPane);
        liguesScrollPane.setBounds(300, 150, 410, 330);

        getContentPane().add(liguePanel);
        revalidate(); // Revalider le contenu du frame
        repaint(); // Redessiner le frame
    }
    
    private void selectLigue() {
        getContentPane().removeAll(); // Supprime tous les composants existants

        JPanel liguePanel = new JPanel();
        liguePanel.setLayout(null); // Utilisation d'un layout null pour positionner précisément les composants

        JButton Quitter = new JButton("QUITTER");
        Quitter.setBackground(new java.awt.Color(56, 56, 56));
        Quitter.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Quitter.setForeground(new java.awt.Color(202, 178, 92));
        Quitter.setBorder(null);
        Quitter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liguePanel.setVisible(false);
                afficheRootPanell(employe);
            }
        });
        liguePanel.add(Quitter);
        Quitter.setBounds(-7, 0, 1000, 71);

        Set<Ligue> liguesSet = gestionPersonnel.getLigues();
        // Récupération des données de gestionPersonnel.getLigues()
        List<Ligue> liguesList = new ArrayList<>(liguesSet);

        // Création du modèle de tableau avec les données récupérées
        String[] columnNames = {"Nom de la ligue", "Action"};
        Object[][] data = new Object[liguesList.size()][2];

        for (int i = 0; i < liguesList.size(); i++) {
            Ligue ligue = liguesList.get(i);
            data[i][0] = ligue.getNom();
            data[i][1] = "Séléctionner la ligue"; // Ce texte sera remplacé par le bouton
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Rendre les cellules de la colonne 1 éditables pour permettre l'utilisation de boutons
            }
        };

        JTable ligueTable = new JTable(tableModel);

        // Personnaliser le rendu des cellules pour afficher des boutons
        ligueTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        ligueTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), liguesList));

        JScrollPane liguesScrollPane = new JScrollPane(ligueTable);
        liguePanel.add(liguesScrollPane);
        liguesScrollPane.setBounds(300, 150, 410, 330);

        getContentPane().add(liguePanel);
        revalidate(); // Revalider le contenu du frame
        repaint(); // Redessiner le frame
    }

    // Classe pour rendre les boutons dans les cellules du tableau

    // Classe pour éditer les cellules du tableau en tant que boutons
    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isPushed;
        private List<Ligue> liguesList;

        public ButtonEditor(JCheckBox checkBox, List<Ligue> liguesList) {
            super(checkBox);
            this.liguesList = liguesList;
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    // Action à effectuer lorsque le bouton est cliqué
                    Ligue ligue = liguesList.get(row);
                    liguePanel(ligue);
                    
                }
            });
            return button;
        }

        public Object getCellEditorValue() {
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new java.awt.Color(202, 178, 92));
            setFont(new java.awt.Font("Trebuchet MS", 0, 14));
            setBorder(null);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditore extends DefaultCellEditor {
        private String label;
        private boolean isPushed;
        private List<Employe> employesList;
        private List<Ligue> liguesList;

        public ButtonEditore(JCheckBox checkBox, List<Ligue> liguesList, List<Employe> employesList) {
            super(checkBox);
            this.liguesList = liguesList;
            this.employesList = employesList;
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            JButton button = new JButton(label);
            button.setBackground(new java.awt.Color(56, 56, 56));
            button.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
            button.setForeground(new java.awt.Color(202, 178, 92));
       
            button.setBorder(null);
            button.addActionListener(e -> {
                fireEditingStopped();
                // Action à effectuer lorsque le bouton est cliqué
                Employe employe = employesList.get(row);
                Ligue ligue = liguesList.get(row);
                unemploye(ligue, employe);
            });
            return button;
        }

        public Object getCellEditorValue() {
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    private void selectEmp(Ligue ligue) {
        getContentPane().removeAll();

        JPanel selemp = new JPanel();
        JButton jButton1;
        JLabel jLabel2;
        JScrollPane jScrollPane1;
        JTable ligueTable;

     
        selemp.setLayout(null);

        jButton1 = new JButton();
        jButton1.setBorder(null);
        jButton1.setBackground(new Color(56, 56, 56));
        jButton1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        jButton1.setForeground(new Color(202, 178, 92));
        jButton1.setText("QUITTER");
        jButton1.setHorizontalAlignment(SwingConstants.RIGHT);
        jButton1.addActionListener(evt -> {
            liguePanel(ligue);
            selemp.setVisible(false);
        });
        selemp.add(jButton1);
        jButton1.setBounds(-7, 0, 1010, 71);

        jLabel2 = new JLabel();
        jLabel2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        jLabel2.setText("SELECTIONNER UN EMPLOYER");
        selemp.add(jLabel2);
        jLabel2.setBounds(170, 100, 450, 50);

        Set<Ligue> liguesSet = gestionPersonnel.getLigues();
        List<Ligue> liguesList = new ArrayList<>();
        List<Employe> employesList = new ArrayList<>();

        for (Ligue ligue1 : liguesSet) {
            for (Employe employe : ligue1.getEmployes()) {
                liguesList.add(ligue1);
                employesList.add(employe);
            }
        }

        String[] columnNames = {"Nom", "Prenom", "Mail", "Action"};
        Object[][] data = new Object[employesList.size()][4];

        for (int i = 0; i < employesList.size(); i++) {
            Employe employe = employesList.get(i);
            data[i][0] = employe.getNom();
            data[i][1] = employe.getPrenom();
            data[i][2] = employe.getMail();
            data[i][3] = "Sélectionner l'employé"; // Texte pour le bouton
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Rendre éditable seulement la colonne des boutons
            }
        };

        ligueTable = new JTable(tableModel);
        ligueTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        ligueTable.getColumn("Action").setCellEditor(new ButtonEditore(new JCheckBox(), liguesList, employesList));

        jScrollPane1 = new JScrollPane(ligueTable);
        selemp.add(jScrollPane1);
        jScrollPane1.setBounds(60, 172, 890, 340);

        getContentPane().add(selemp);
        revalidate();
        repaint();
    }


    
    private void liguePanel(Ligue ligue) {
        getContentPane().removeAll(); // Supprime tous les composants existants

        JPanel liguePanelde = new JPanel();
        liguePanelde.setLayout(null); // Utilisation d'un layout null pour positionner précisément les composants

        JButton jButton3 = new JButton("QUITTER");
        jButton3.setBackground(new java.awt.Color(56, 56, 56));
        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(202, 178, 92));
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    selectLigue();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue: " + e.getMessage());
                }
            }
        });
        liguePanelde.add(jButton3);
        jButton3.setBounds(883, 0, 120, 70);

        JButton jButton4 = new JButton("AFFICHER LA LIGUE");
        jButton4.setBackground(new java.awt.Color(56, 56, 56));
        jButton4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(202, 178, 92));
        jButton4.setBorder(null);
        liguePanelde.add(jButton4);
        jButton4.setBounds(100, 0, 200, 70);

        JButton jButton5 = new JButton("RENOMMER");
        jButton5.setBackground(new java.awt.Color(56, 56, 56));
        jButton5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(202, 178, 92));
        jButton5.setBorder(null);
        liguePanelde.add(jButton5);
        jButton5.setBounds(500, 0, 200, 70);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                	 RenameGUI renLigueFrame = new RenameGUI(ligue);
                     renLigueFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue: " + e.getMessage());
                }
            }
        });

        JButton jButton6 = new JButton("SUPPRIMER");
        jButton6.setBackground(new java.awt.Color(56, 56, 56));
        jButton6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(202, 178, 92));
        jButton6.setBorder(null);
        liguePanelde.add(jButton6);
        jButton6.setBounds(700, 0, 190, 70);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                	 SupprLigue supprLigueFrame = new SupprLigue(ligue);
                     supprLigueFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue: " + e.getMessage());
                }
            }
        });

        JLabel jLabel1 = new JLabel(ligue.getNom());
        jLabel1.setBackground(new java.awt.Color(56, 56, 56));
        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(202, 178, 92));
        jLabel1.setOpaque(true);
        liguePanelde.add(jLabel1);
        jLabel1.setBounds(0, 0, 100, 70);

        JLabel jLabel2 = new JLabel(new javax.swing.ImageIcon("C:\\Users\\aymer\\Downloads\\des-sports 1.png"));
        liguePanelde.add(jLabel2);
        jLabel2.setBounds(340, 180, 320, 300);

        JButton jButton7 = new JButton("GERER LES EMPLOYES");
        jButton7.setBackground(new java.awt.Color(56, 56, 56));
        jButton7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(202, 178, 92));
        jButton7.setBorder(null);
        liguePanelde.add(jButton7);
        jButton7.setBounds(300, 0, 200, 70);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                	 employePanel(ligue);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue: " + e.getMessage());
                }
            }
        });

        getContentPane().add(liguePanelde);
        revalidate(); // Revalider le contenu du frame
        repaint(); // Redessiner le frame
    }
    
    private void employePanel(Ligue ligue) {
        getContentPane().removeAll(); // Supprime tous les composants existants

        JPanel employeP = new JPanel();
        employeP.setLayout(null); // Utilisation d'un layout null pour positionner précisément les composants

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1000, 550));
        setMinimumSize(new java.awt.Dimension(1000, 550));
        setPreferredSize(new java.awt.Dimension(1000, 550));
        employeP.setLayout(null);

        jButton1.setBackground(new java.awt.Color(56, 56, 56));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(202, 178, 92));
        jButton1.setText("QUITTER");
        jButton1.setPreferredSize(new java.awt.Dimension(200, 28));
        employeP.add(jButton1);
        jButton1.setBounds(780, 0, 260, 71);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                	 liguePanel(ligue);
                	 employeP.setVisible(false);
                	 
                	
                	 
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue: " + e.getMessage());
                }
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\aymer\\Downloads\\des-sports 1.png")); // NOI18N
        employeP.add(jLabel2);
        jLabel2.setBounds(340, 180, 320, 300);

        jButton4.setBackground(new java.awt.Color(56, 56, 56));
        jButton4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(202, 178, 92));
        jButton4.setText("AFFICHER LES EMPLOYE");
        jButton4.setPreferredSize(new java.awt.Dimension(200, 28));
        employeP.add(jButton4);
        jButton4.setBounds(0, 0, 260, 71);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
              
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue: " + e.getMessage());
                }
            }
        });

        jButton5.setBackground(new java.awt.Color(56, 56, 56));
        jButton5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(202, 178, 92));
        jButton5.setText("AJOUTER UN EMPLOYE");
        jButton5.setPreferredSize(new java.awt.Dimension(200, 28));
        employeP.add(jButton5);
        jButton5.setBounds(260, 0, 260, 71);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                
                	 AjoutEmployer ajout = new AjoutEmployer(ligue);
                     ajout.setVisible(true);
                	 
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue: " + e.getMessage());
                }
            }
        });

        jButton6.setBackground(new java.awt.Color(56, 56, 56));
        jButton6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(202, 178, 92));
        jButton6.setText("SELECTIONNER UN EMPLOYER");
        jButton6.setPreferredSize(new java.awt.Dimension(200, 28));
        employeP.add(jButton6);
        jButton6.setBounds(520, 0, 260, 71);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                
                	 selectEmp(ligue);
                     employeP.setVisible(false);
                	 
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue: " + e.getMessage());
                }
            }
        });

        
       
        
        getContentPane().add(employeP);
        revalidate(); // Revalider le contenu du frame
        repaint(); // Redessiner le frame
    }

    public void unemploye(Ligue ligue,Employe employe){
    	getContentPane().removeAll();
    	
    	JPanel unemployepanel = new JPanel();
    	unemployepanel.setLayout(null);
    	
    	   jButton3 = new javax.swing.JButton();
           jButton4 = new javax.swing.JButton();
           jButton6 = new javax.swing.JButton();
           jLabel1 = new javax.swing.JLabel();
           jLabel2 = new javax.swing.JLabel();
           JButton jButton7 = new javax.swing.JButton();

           
           unemployepanel.setLayout(null);

           jButton3.setBackground(new java.awt.Color(56, 56, 56));
           jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
           jButton3.setForeground(new java.awt.Color(202, 178, 92));
           jButton3.setText("QUITTER");
           jButton3.setBorder(null);
           jButton3.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                          selectEmp(ligue);
               }
           });
           unemployepanel.add(jButton3);
           jButton3.setBounds(863, 0, 140, 70);

           jButton4.setBackground(new java.awt.Color(56, 56, 56));
           jButton4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
           jButton4.setForeground(new java.awt.Color(202, 178, 92));
           jButton4.setText("PROMOUVOIR ADMIN");
           jButton4.setBorder(null);
           jButton4.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
            	   try {
						Upgrade up = new Upgrade(employe,ligue);
						up.setVisible(true);
					} catch (SauvegardeImpossible e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
               }
           });
           unemployepanel.add(jButton4);
           jButton4.setBounds(180, 0, 230, 70);

           jButton6.setBackground(new java.awt.Color(56, 56, 56));
           jButton6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
           jButton6.setForeground(new java.awt.Color(202, 178, 92));
           jButton6.setText("SUPPRIMER");
           jButton6.setBorder(null);
           jButton6.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                         try {
							SupprEmp supemp = new SupprEmp(employe);
							supemp.setVisible(true);
						} catch (SauvegardeImpossible e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
               }
           });
           unemployepanel.add(jButton6);
           jButton6.setBounds(640, 0, 230, 70);

           jLabel1.setBackground(new java.awt.Color(56, 56, 56));
           jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
           jLabel1.setForeground(new java.awt.Color(202, 178, 92));
           jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
           jLabel1.setText(employe.getNom()+" "+employe.getPrenom());
           jLabel1.setOpaque(true);
           unemployepanel.add(jLabel1);
           jLabel1.setBounds(0, 0, 180, 70);

           jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\aymer\\Downloads\\des-sports 1.png")); // NOI18N
           unemployepanel.add(jLabel2);
           jLabel2.setBounds(340, 180, 320, 300);

           jButton7.setBackground(new java.awt.Color(56, 56, 56));
           jButton7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
           jButton7.setForeground(new java.awt.Color(202, 178, 92));
           jButton7.setText("EDITER");
           jButton7.setBorder(null);
           jButton7.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                     EditEmploye edit;
					try {
						edit = new EditEmploye(employe);
						edit.setVisible(true);
					} catch (SauvegardeImpossible e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                     
               }
           });
           unemployepanel.add(jButton7);
           jButton7.setBounds(410, 0, 230, 70);
    	
    	getContentPane().add(unemployepanel);
    	revalidate();
    	pack();
    }
    	
    private void employereditroot(Employe root) {
    	getContentPane().removeAll(); 

    	JPanel EmployeEditPanel = new JPanel();
    	EmployeEditPanel.setLayout(null); 
    	
    	JButton Quitter = new JButton("QUITTER");
    	Quitter.setBackground(new java.awt.Color(56, 56, 56));
    	Quitter.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
    	Quitter.setForeground(new java.awt.Color(202, 178, 92));
        
    	Quitter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    	Quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              EmployeEditPanel.setVisible(false);
              afficheRootPanel(employe);
            }
        });
        EmployeEditPanel.add(Quitter);
        Quitter.setBounds(-7, 0, 1000, 71);
    	
        JLabel PrenomTittle = new JLabel(employe.getPrenom());
        PrenomTittle.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        EmployeEditPanel.add(PrenomTittle);
        PrenomTittle.setBounds(240, 100, 160, 50);

        JLabel MailTittle = new JLabel(employe.getMail());
        MailTittle.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        EmployeEditPanel.add(MailTittle);
        MailTittle.setBounds(470, 100, 270, 50);

        JLabel NomTittle = new JLabel(employe.getNom());
        NomTittle.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        EmployeEditPanel.add(NomTittle);
        NomTittle.setBounds(60, 100, 160, 50);
    
        JTextField EnterName = new JTextField("Entrez le nom");
        EnterName.setBackground(new java.awt.Color(204, 204, 204));
        EnterName.setForeground(new java.awt.Color(56, 56, 56));
        EnterName.setToolTipText("");
        EnterName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
        EnterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        EmployeEditPanel.add(EnterName);
        EnterName.setBounds(240, 240, 310, 40);
        
        JButton ChangeName = new JButton("CHANGER LE NOM");
        ChangeName.setBackground(new java.awt.Color(202, 178, 92));
        ChangeName.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        
        ChangeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
         
            }
        });
        EmployeEditPanel.add(ChangeName);
        ChangeName.setBounds(550, 240, 190, 40);
        
        JButton ChangePrename = new JButton("CHANGER LE PRENOM");
        ChangePrename.setBackground(new java.awt.Color(202, 178, 92));
        ChangePrename.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        ChangePrename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            
            }
        });
        EmployeEditPanel.add(ChangePrename);
        ChangePrename.setBounds(550, 170, 190, 40);
        
        JTextField EnterPrename = new JTextField("Entrez le prénom");
        EnterPrename.setBackground(new java.awt.Color(204, 204, 204));
        EnterPrename.setForeground(new java.awt.Color(56, 56, 56));
       
        EnterPrename.setToolTipText("");
        EnterPrename.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
        EnterPrename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        EmployeEditPanel.add(EnterPrename);
        EnterPrename.setBounds(240, 170, 310, 40);
        
        JTextField EnterMDP = new JTextField("Entrez le prénom");
        EnterMDP.setBackground(new java.awt.Color(204, 204, 204));
        EnterMDP.setForeground(new java.awt.Color(56, 56, 56));
        EnterMDP.setText("Entrez le mot de passe");
        EnterMDP.setToolTipText("");
        EnterMDP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
        EnterMDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
          
            }
        });
        EmployeEditPanel.add(EnterMDP);
        EnterMDP.setBounds(240, 310, 310, 40);

        JButton ChangeDATED = new JButton("CHANGER LA DATE DE DEPART");
        ChangeDATED.setBackground(new java.awt.Color(202, 178, 92));
        ChangeDATED.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        ChangeDATED.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        EmployeEditPanel.add(ChangeDATED);
        ChangeDATED.setBounds(550, 440, 190, 40);

        JTextField EnterDateA = new JTextField("Entrez la date d'arrive");
        EnterDateA.setBackground(new java.awt.Color(204, 204, 204));
        EnterDateA.setForeground(new java.awt.Color(56, 56, 56));
        EnterDateA.setToolTipText("");
        EnterDateA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(56, 56, 56), 1, true));
        EnterDateA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
           
            }
        });
        
        JButton ChangeMDP = new JButton("CHANGER LE MDP");
        ChangeMDP.setBackground(new java.awt.Color(202, 178, 92));
        ChangeMDP.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        ChangeMDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        add(ChangeMDP);
        ChangeMDP.setBounds(550, 310, 190, 40);
        
        EmployeEditPanel.add(EnterDateA);
        EnterDateA.setBounds(240, 380, 310, 40);

        JButton ChangeDATEA = new JButton("CHANGER LA DATE D'ARRIVE");
        ChangeDATEA.setBackground(new java.awt.Color(202, 178, 92));
        ChangeDATEA.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        ChangeDATEA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               
            }
        });
        EmployeEditPanel.add(ChangeDATEA);
        ChangeDATEA.setBounds(550, 380, 190, 40);

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
        EnterDateD.setBounds(240, 440, 310, 40);
        

    	getContentPane().add(EmployeEditPanel);
        revalidate(); // Revalider le contenu du frame
        repaint(); // Redessiner le frame
    }
    

    private JTextField JTextField(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	
    public Employe verifierIdentifiants(String email, String motDePasse) {
        System.out.println("Début de la vérification des identifiants");

        for (Ligue ligue : gestionPersonnel.getLigues()) {
            System.out.println("Vérification de la ligue : " + ligue.getNom());
            for (Employe employe : ligue.getEmployes()) {
                System.out.println("Vérification de l'employé : " + employe.getMail());
                if (employe.getMail().equals(email)) {
                    System.out.println("Email correspond : " + email);
                    if (employe.getPassword().equals(motDePasse)) {
                        System.out.println("Mot de passe correct pour : " + email);
                        return employe;
                    } else {
                        System.out.println("Mot de passe incorrect pour : " + email);
                    }
                }
            }
        }
        System.out.println("Aucun employé trouvé avec les identifiants fournis.");
        return null; // Retourne null si les identifiants sont incorrects
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PersonnelGUI();
                } catch (SauvegardeImpossible e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
