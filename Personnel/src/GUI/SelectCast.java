package GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import GUI.PersonnelGUI.ButtonEditor;
import GUI.PersonnelGUI.ButtonRenderer;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class SelectCast extends JFrame {

	GestionPersonnel gestionPersonnel;
	Ligue ligue;
	Employe employe;
	
	public SelectCast(Ligue ligue) throws SauvegardeImpossible{
		 gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		setSize(1000, 650);
		ajouterunemploye(ligue);
        setResizable(false); 
        setLocationRelativeTo(null);
	}
	

	public void ajouterunemploye(Ligue ligue) {
		
		
            JPanel cast = new JPanel();	
            cast.setLayout(null); 
		 
	        JLabel jLabel2 = new javax.swing.JLabel();
	        JTextField jTextField1 = new javax.swing.JTextField();
	        JButton jButton2 = new javax.swing.JButton();

	        setPreferredSize(new java.awt.Dimension(400, 550));
	        setLayout(null);

	        Set<Ligue> liguesSet = gestionPersonnel.getLigues();
	        // Récupération des données de gestionPersonnel.getLigues()
	        List<Ligue> liguesList = new ArrayList<>(liguesSet);

	        // Création du modèle de tableau avec les données récupérées
	        String[] columnNames = {"Nom de la ligue", "Action"};
	        Object[][] data = new Object[liguesList.size()][2];

	        for (int i = 0; i < liguesList.size(); i++) {
	            Ligue ligue1 = liguesList.get(i);
	            data[i][0] = ligue1.getNom();
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
	        cast.add(liguesScrollPane);
	        liguesScrollPane.setBounds(300, 150, 410, 330);
	        
	        getContentPane().add(cast);
	        setVisible(true);
	        pack();
		
	}
	   class ButtonRenderer extends JButton implements TableCellRenderer {
	        public ButtonRenderer() {
	            setOpaque(true);
	        }

	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            setText((value == null) ? "" : value.toString());
	            return this;
	        }
	    }

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
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        	
	        });
	    }
}
