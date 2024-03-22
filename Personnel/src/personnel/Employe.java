package personnel;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Employé d'une ligue hébergée par la M2L. Certains peuvent 
 * être administrateurs des employés de leur ligue.
 * Un seul employé, rattaché à aucune ligue, est le root.
 * Il est impossible d'instancier directement un employé, 
 * il faut passer la méthode {@link Ligue#addEmploye addEmploye}.
 */

public class Employe implements Serializable, Comparable<Employe>
{
	private static final long serialVersionUID = 4795721718037994734L;
	private String nom, prenom, password, mail;
	private Ligue ligue;
	private int id; 
	private GestionPersonnel gestionPersonnel;
	private LocalDate date_arrive;
    private LocalDate date_depart;
	
     Employe(GestionPersonnel gestionPersonnel, Ligue ligue,
    		String nom, String prenom, String mail, String password, LocalDate date_arrive, LocalDate date_depart) throws SauvegardeImpossible
	{
    
    	this(gestionPersonnel, ligue, nom, prenom, mail, password,date_arrive, date_depart,-1);
		this.id = gestionPersonnel.insert(this); 
    	
	}
    
     // ajout de IDto
     Employe(GestionPersonnel gestionPersonnel, Ligue ligue,
    		String nom, String prenom, String mail, String password, LocalDate date_arrive, LocalDate date_depart,int id) {
        this.gestionPersonnel = gestionPersonnel;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.mail = mail;
        this.ligue = ligue;
        this.date_arrive = date_arrive;
        this.date_depart = date_depart;
     // ajout de ID
    }
	/**
	 * Retourne vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @return vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @param ligue la ligue pour laquelle on souhaite vérifier si this 
	 * est l'admininstrateur.
	 * @throws SauvegardeImpossible 
	 */
     
     
	public boolean estAdmin(Ligue ligue)
	{
		return ligue.getAdministrateur() == this;
	}

    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
  
	/**
	 * Retourne vrai ssi l'employé est le root.
	 * @return vrai ssi l'employé est le root.
	 */
	
	public boolean estRoot()
	{
		return gestionPersonnel.getRoot() == this;
	}
	
	/**
	 * Retourne le nom de l'employé.
	 * @return le nom de l'employé. 
	 */
	
	public String getNom()
	{
		return nom;
	}

	/**
	 * Change le nom de l'employé.
	 * @param nom le nouveau nom.
	 * @throws SauvegardeImpossible 
	 */
	
	public void setNom(String nom) 
	{
		this.nom = nom;
		try {
			gestionPersonnel.update(this);
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Retourne le prénom de l'employé.
	 * @return le prénom de l'employé.
	 */
	
	public String getPrenom()
	{
		return prenom;
	}
	
	/**
	 * Change le prénom de l'employé.
	 * @param prenom le nouveau prénom de l'employé. 
	 * @throws SauvegardeImpossible 
	 */

	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
		try {
			gestionPersonnel.update(this);
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Retourne le mail de l'employé.
	 * @return le mail de l'employé.
	 */
	
	public String getMail()
	{
		return mail;
	}
	
	/**
	 * Change le mail de l'employé.
	 * @param mail le nouveau mail de l'employé.
	 * @throws SauvegardeImpossible 
	 */

	public void setMail(String mail) 
	{
		this.mail = mail;
		try {
			gestionPersonnel.update(this);
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Retourne vrai ssi le password passé en paramètre est bien celui
	 * de l'employé.
	 * @return vrai ssi le password passé en paramètre est bien celui
	 * de l'employé.
	 * @param password le password auquel comparer celui de l'employé.
	 */
	
	public boolean checkPassword(String password)
	{
		return this.password.equals(password);
	}

	/**
	 * Change le password de l'employé.
	 * @param password le nouveau password de l'employé. 
	 * @throws SauvegardeImpossible 
	 */
	
	public void setPassword(String password) 
	{
		this.password= password;
		try {
			gestionPersonnel.update(this);
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Retourne la ligue à laquelle l'employé est affecté.
	 * @return la ligue à laquelle l'employé est affecté.
	 */
	
	public Ligue getLigue()
	{
		return ligue;
	}
	
	public void setdatedepart(LocalDate date_depart) {
	    try {
	        int resultatComparaison = date_depart.compareTo(date_arrive);

	        if (resultatComparaison <= 0) {
	            throw new IllegalArgumentException("La date d'arrivée doit être postérieure à la date de départ.");
	        }

	        this.date_depart = date_depart;
	        try {
				gestionPersonnel.update(this);
			} catch (SauvegardeImpossible e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public LocalDate getdatedepart() {
		return date_depart;
	}
	
	public void setdatearrive(LocalDate date_arrive) {
	
		this.date_arrive= date_arrive;
		try {
			gestionPersonnel.update(this);
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public LocalDate getdatearrive() {
		return date_arrive;
	}

	/**
	 * Supprime l'employé. Si celui-ci est un administrateur, le root
	 * récupère les droits d'administration sur sa ligue.
	 * @throws SauvegardeImpossible 
	 */
	
	
	public void remove() throws SauvegardeImpossible
	{
		Employe root = gestionPersonnel.getRoot();
		if (this != root)
		{
			if (estAdmin(getLigue()))
				getLigue().setAdministrateur(root);
			gestionPersonnel.remove(this);
		}
		else
			throw new ImpossibleDeSupprimerRoot();
	}

	@Override
	public int compareTo(Employe autre)
	{
		int cmp = getNom().compareTo(autre.getNom());
		if (cmp != 0)
			return cmp;
		return getPrenom().compareTo(autre.getPrenom());
	}
	
	@Override
	public String toString()
	{
		String res = nom + " " + prenom + " " + mail + " " + date_arrive + " " + date_depart + " (";
		if (estRoot())
			res += "super-utilisateur";
		else
			res += ligue.toString();
		return res + ")";
	}
	
	
}
