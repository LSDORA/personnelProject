package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import GUI.PersonnelGUI;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class AffichL extends JFrame {

	GestionPersonnel gestionPersonnel;
	private Ligue ligue;
	
	public AffichL(Employe employe, Ligue ligue) throws SauvegardeImpossible{
		 gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		setSize(1000, 650);
		this.ligue = ligue;
        affich(employe,ligue);
        setResizable(false); 
        setLocationRelativeTo(null);
	}
	
	

	public void affich(Employe employe, Ligue ligue) {
	    getContentPane().removeAll();

	    JPanel aff = new JPanel();
	    aff.setLayout(null);

	    JLabel jLabel2 = new JLabel();
	    JScrollPane jScrollPane1 = new JScrollPane();
	    JTable jTable1 = new JTable();

	    setPreferredSize(new Dimension(600, 550));
	    aff.setLayout(null);

	    jLabel2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
	    jLabel2.setText("EMPLOYER DE LA LIGUE"+ligue.getNom());
	    aff.add(jLabel2);
	    jLabel2.setBounds(50, 20, 270, 50);

	    // Récupère la liste des employés de la ligue spécifique
	    List<Employe> employesList = new ArrayList<>(ligue.getEmployes());

	    // Définit les noms des colonnes et les données pour le modèle de tableau
	    String[] columnNames = {"NOM", "PRENOM", "MAIL"};
	    Object[][] data = new Object[employesList.size()][3];

	    // Remplit les données avec les informations des employés
	    for (int i = 0; i < employesList.size(); i++) {
	        Employe emp = employesList.get(i);
	        data[i][0] = emp.getNom();
	        data[i][1] = emp.getPrenom();
	        data[i][2] = emp.getMail();
	    }

	    // Crée un modèle de tableau par défaut avec les données spécifiques
	    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
	    jTable1.setModel(tableModel);

	    jScrollPane1.setViewportView(jTable1);
	    aff.add(jScrollPane1);
	    jScrollPane1.setBounds(50, 82, 510, 440);

	    getContentPane().add(aff);
	    setVisible(true);
	    pack();
	}

	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        });
	    }
}
