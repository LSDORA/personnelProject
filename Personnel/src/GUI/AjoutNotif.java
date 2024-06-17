package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import personnel.Employe;

public class AjoutNotif extends JFrame {

    public AjoutNotif() {
    }

    private static void createNotification(String message) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(408, 80));
        frame.setUndecorated(true); // Supprime les bordures de la fenêtre

        // Crée le JPanel et le JLabel
        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel();

        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setMaximumSize(new Dimension(400, 80));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jLabel1.setBackground(new Color(56, 56, 56));
        jLabel1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        jLabel1.setForeground(new Color(56, 56, 56));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText(message);

        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jLabel1, BorderLayout.CENTER);

        frame.getContentPane().add(jPanel1);
        frame.pack();

        // Obtient la taille de l'écran et positionne la fenêtre en haut à droite
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() - frame.getWidth());
        int y = 0; // Alignement en haut

        frame.setLocation(x, y);

        // Rend la fenêtre de notification toujours au premier plan
        frame.setAlwaysOnTop(true);

        // Affiche la notification
        frame.setVisible(true);

        // Crée un Timer pour fermer la notification après quelques secondes
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        frame.dispose();
                    }
                });
            }
        }, 1500); // Ferme la notification après 1,5 secondes
    }

    public static void Reussi() {
        createNotification("La ligue a bien été ajoutée!");
    }
    public static void Date() {
        createNotification("La date d'arrivée doit être postérieure à la date de départ.");
    }
    
    public static void EModif() {
        createNotification("Information de l'employer modifier avec succès!");
    }

    public static void Echoue() {
        createNotification("La ligue n'a pas été ajoutée!");
    }
    public static void AEEchoue() {
    	createNotification("l'employe n'a pas été ajoutée!");
    }
    public static void AEReussi() {
        createNotification("l'employe a été ajoutée!");
    }
    public static void SReussi(Employe employe) {
        createNotification(employe.getPrenom()+" "+employe.getNom()+"a bien été supprimer!");
    }

    public static void SEchoue(Employe employe) {
        createNotification(employe.getPrenom()+" "+employe.getNom()+" n'a pas été supprimer!");
    }
    
    public static void Format() {
        createNotification("Format de date invalide : YYYY-MM-DD");
    }

    public static void main(String[] args) {
        // Appels de test pour démontrer les notifications
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Reussi();
              
                Echoue();
            }
        });
    }
}
