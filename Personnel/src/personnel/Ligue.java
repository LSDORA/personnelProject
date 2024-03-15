package personnel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Représente une ligue. Chaque ligue est reliée à une liste
 * d'employés dont un administrateur. Comme il n'est pas possible
 * de créer un employé sans l'affecter à une ligue, le root est 
 * l'administrateur de la ligue jusqu'à ce qu'un administrateur 
 * lui ait été affecté avec la fonction {@link #setAdministrateur}.
 */

public class Ligue implements Serializable, Comparable<Ligue>
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String nom;
	private SortedSet<Employe> employes;
	private Employe administrateur;
	private GestionPersonnel gestionPersonnel;
	private static Ligue ligue = null;
	
	/**
	 * Crée une ligue.
	 * @param nom le nom de la ligue.
	 */

	
	Ligue(GestionPersonnel gestionPersonnel, String nom) throws SauvegardeImpossible
	{
		this(gestionPersonnel, -1, nom);
		this.id = gestionPersonnel.insert(this); 
	}

	Ligue(GestionPersonnel gestionPersonnel, int id, String nom)
	{
		this.nom = nom;
		employes = new TreeSet<>();
		this.gestionPersonnel = gestionPersonnel;
		administrateur = gestionPersonnel.getRoot();
		this.id = id;
	}

	/**
	 * Retourne le nom de la ligue.
	 * @return le nom de la ligue.
	 */
    public int getId(){
    	return id;
    }
	public String getNom()
	{
		return nom;
	}

	/**
	 * Change le nom.
	 * @param nom le nouveau nom de la ligue.
	 */

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	/**
	 * Retourne l'administrateur de la ligue.
	 * @return l'administrateur de la ligue.
	 */
	
	public Employe getAdministrateur()
	{
		return administrateur;
	}

	/**
	 * Fait de administrateur l'administrateur de la ligue.
	 * Lève DroitsInsuffisants si l'administrateur n'est pas 
	 * un employé de la ligue ou le root. Révoque les droits de l'ancien 
	 * administrateur.
	 * @param administrateur le nouvel administrateur de la ligue.
	 */
	
	public void setAdministrateur(Employe administrateur)
	{
		Employe root = gestionPersonnel.getRoot();
		if (administrateur != root && administrateur.getLigue() != this)
			throw new DroitsInsuffisants();
		this.administrateur = administrateur;
	}

	/**
	 * Retourne les employés de la ligue.
	 * @return les employés de la ligue dans l'ordre alphabétique.
	 */
	
	public SortedSet<Employe> getEmployes()
	{
		return Collections.unmodifiableSortedSet(employes);
	}

	/**
	 * Ajoute un employé dans la ligue. Cette méthode 
	 * est le seul moyen de créer un employé.
	 * @param nom le nom de l'employé.
	 * @param prenom le prénom de l'employé.
	 * @param mail l'adresse mail de l'employé.
	 * @param password le password de l'employé.
	 * @return l'employé créé. 
	 */

	public Employe addEmploye(String nom, String prenom, String mail, String password, LocalDate date_arrive, LocalDate date_depart) {
		if (date_arrive == null && date_depart == null) {
		    // La méthode addEmploye a renvoyé null, ce qui signifie que les dates d'arrivée ou de départ étaient nulles
		    // Afficher les autres données de l'employé
			String passwordEmploye = password;
		    System.out.println("Nom: " + nom + ", Prénom: " + prenom + ", Mail: " + mail + ", Password: " + passwordEmploye);
		    return null;
		} else {
			 try {
			        if (date_arrive == null || date_depart == null) {
			            throw new IllegalArgumentException("Les dates d'arrivée et de départ ne peuvent pas être null.");
			        }
			        
			        if (date_depart.isBefore(date_arrive)) {
			            throw new DateTimeParseException("Erreur : La date de départ ne peut pas être avant la date d'arrivée.", date_depart.toString(), 0);
			        } else {
			            Employe employe = new Employe(this.gestionPersonnel, this, nom, prenom, mail, password, date_arrive, date_depart,id);
			            employes.add(employe);
			            return employe;
			        }
			    } catch (DateTimeParseException e) {
			        System.err.println("Erreur de format de date : Utilisez le format YYYY-MM-DD.");
			        return null;
			    } catch (IllegalArgumentException e) {
			        System.err.println(e.getMessage());
			        return null;
			    }
		}
	   
	}

	
	void remove(Employe employe)
	{
		employes.remove(employe);
	}
	
	/**
	 * Supprime la ligue, entraîne la suppression de tous les employés
	 * de la ligue.
	 */
	
	public void remove()
	{
		gestionPersonnel.remove(this);
	}
	

	@Override
	public int compareTo(Ligue autre)
	{
		return getNom().compareTo(autre.getNom());
	}
	
	@Override
	public String toString()
	{
		return nom;
	}
}
