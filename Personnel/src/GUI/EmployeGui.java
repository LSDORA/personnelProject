package GUI;

import javax.swing.*;

import commandLine.EmployeConsole;
import commandLine.LigueConsole;
import commandLine.PersonnelConsole;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.SauvegardeImpossible;

public class EmployeGui extends JFrame {
	  PersonnelConsole personnelConsole;
		private GestionPersonnel gestionPersonnel;
		private EmployeConsole employeConsole;
		private LigueConsole ligueConsole;

	    private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JPanel jPanel2;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;


    private Employe employe;

    public EmployeGui(Employe root) throws SauvegardeImpossible {
    	 gestionPersonnel = GestionPersonnel.getGestionPersonnel();
         employeConsole = new EmployeConsole();
         ligueConsole = new LigueConsole(gestionPersonnel, employeConsole);
        this.employe = root;
        employereditroot();
    }

    public void employereditroot() {
        setTitle("Editer le compte " + employe.getNom());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create labels and fields
        JLabel nomLabel = new JLabel("Nom: ");
        JTextField nomField = new JTextField(employe.getNom(), 20);
        
        JLabel prenomLabel = new JLabel("Prénom: ");
        JTextField prenomField = new JTextField(employe.getPrenom(), 20);
        
        JLabel mailLabel = new JLabel("Mail: ");
        JTextField mailField = new JTextField(employe.getMail(), 20);
        
        JLabel dateArriveLabel = new JLabel("Date d'arrivée (YYYY-MM-DD): ");
        JTextField dateArriveField = new JTextField(employe.getdatearrive().toString(), 20);
        JLabel dateDepartLabel = new JLabel("Date de départ (YYYY-MM-DD): ");
        JTextField dateDepartField = new JTextField(employe.getdatedepart().toString(), 20);

        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Update employe object with new data
                employe.setNom(nomField.getText());
                employe.setPrenom(prenomField.getText());
                employe.setMail(mailField.getText());

                try {
                    LocalDate nouvelleDateArrivee = LocalDate.parse(dateArriveField.getText());
                    employe.setdatearrive(nouvelleDateArrivee);
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(EmployeGui.this, "Erreur de format de date d'arrivée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    LocalDate nouvelleDateDepart = LocalDate.parse(dateDepartField.getText());
                    employe.setdatedepart(nouvelleDateDepart);
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(EmployeGui.this, "Erreur de format de date de départ.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                // Handle save operation or notify changes
                dispose(); // Close the window after saving
            }
        });

        // Layout components using GroupLayout or another layout manager
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nomLabel)
                        .addComponent(prenomLabel)
                        .addComponent(mailLabel)
                        .addComponent(dateArriveLabel)
                        .addComponent(dateDepartLabel)
                        .addComponent(sauvegarderButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nomField)
                        .addComponent(prenomField)
                        .addComponent(mailField)
                        .addComponent(dateArriveField)
                        .addComponent(dateDepartField)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nomLabel)
                        .addComponent(nomField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(prenomLabel)
                        .addComponent(prenomField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mailLabel)
                        .addComponent(mailField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dateArriveLabel)
                        .addComponent(dateArriveField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dateDepartLabel)
                        .addComponent(dateDepartField))
                .addComponent(sauvegarderButton));

        pack(); // Pack components for better layout
        setLocationRelativeTo(null); // Center the window
    }

    // Example usage
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
                Employe root = gestionPersonnel.getRoot();
                EmployeGui gui = new EmployeGui(root);
                gui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
