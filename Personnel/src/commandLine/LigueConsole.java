package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import commandLineMenus.List;
import commandLineMenus.Menu;
import commandLineMenus.Option;

import personnel.*;

public class LigueConsole 
{
	private GestionPersonnel gestionPersonnel;
	private EmployeConsole employeConsole;

	public LigueConsole(GestionPersonnel gestionPersonnel, EmployeConsole employeConsole)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.employeConsole = employeConsole;
	}

	Menu menuLigues()
	{
		Menu menu = new Menu("Gérer les ligues", "l");
		menu.add(afficherLigues());
		menu.add(ajouterLigue());
		menu.add(selectionnerLigue());
		menu.addBack("q");
		return menu;
	}

	private Option afficherLigues()
	{
		return new Option("Afficher les ligues", "l", () -> {System.out.println(gestionPersonnel.getLigues());});
	}

	private Option afficher(final Ligue ligue)
	{
		return new Option("Afficher la ligue", "l", 
				() -> 
				{
					System.out.println(ligue);
					System.out.println("administrée par " + ligue.getAdministrateur());
				}
		);
	}
	private Option afficherEmployes(final Ligue ligue)
	{
		return new Option("Afficher les employes", "l", () -> {System.out.println(ligue.getEmployes());});
	}

	private Option ajouterLigue()
	{
		return new Option("Ajouter une ligue", "a", () -> 
		{
			try
			{
				gestionPersonnel.addLigue(getString("nom : "));
			}
			catch(SauvegardeImpossible exception)
			{
				System.err.println("Impossible de sauvegarder cette ligue");
			}
		});
	}
	
	private Menu editerLigue(Ligue ligue)
	{
		Menu menu = new Menu("Editer " + ligue.getNom());
		menu.add(afficher(ligue));
		menu.add(gererEmployes(ligue));
		menu.add(changerNom(ligue));
		menu.add(supprimer(ligue));
		menu.addBack("q");
		return menu;
	}

	private Option changerNom(final Ligue ligue) 
	{
		return new Option("Renommer", "r", 
				() -> {try {
					ligue.setNom(getString("Nouveau nom : "));
					
				} catch (SauvegardeImpossible e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}});
	}

	private List<Ligue> selectionnerLigue()
	{
		return new List<Ligue>("Sélectionner une ligue", "e", 
				() -> new ArrayList<>(gestionPersonnel.getLigues()),
				(element) -> editerLigue(element)
				);
	}
	public LocalDate getInt(String message) {
		return null;
	    
	}

	
	private Option ajouterEmploye(final Ligue ligue) {
	    return new Option("ajouter un employé", "a", () -> {
	        String nom = getString("nom : ");
	        String prenom = getString("prenom : ");
	        String mail = getString("mail : ");
	        String password = getString("password : ");

	        try {
	            String dateArriveeStr = getString("date d'arrivée (YYYY-MM-DD) : ");
	            LocalDate dateArrivee = LocalDate.parse(dateArriveeStr);

	            String dateDepartStr = getString("date de départ (YYYY-MM-DD) : ");
	            LocalDate dateDepart = LocalDate.parse(dateDepartStr);
	            if (dateDepart.isBefore(dateArrivee)) {
	                System.err.println("Erreur : La date de départ ne peut pas être avant la date d'arrivée.");
	            } else {
	                ligue.addEmploye(nom, prenom, mail, password, dateArrivee, dateDepart);
	            }
	        } catch (DateTimeParseException e) {
	        	  System.err.println("Erreur de format de date : Utilisez le format YYYY-MM-DD.");
	        }
	    });
	}

	private Menu  gererEmployes(Ligue ligue)
	{
		Menu menu = new Menu("Gérer les employés de " + ligue.getNom(), "e");
		menu.add(afficherEmployes(ligue));
		menu.add(ajouterEmploye(ligue));
		menu.add(selectionEmploye(ligue));
	
		menu.addBack("q");
		return menu;
	}


	private List<Employe> changerAdministrateur(final Ligue ligue)
	{
	    return new List<>("Changer l'administrateur", "c", 
	            () -> new ArrayList<>(ligue.getEmployes()),
	            (index, employe) -> {
	                ligue.setAdministrateur(employe);
	                System.out.println("Le nouvel administrateur est : " + employe.getNom());
	            }
	    );
	}	
	


	private List<Employe> selectionEmploye(final Ligue ligue)
	{
		return new List<>("Selectionner un employer", "e", 
				() -> new ArrayList<>(ligue.getEmployes()),
				employeConsole.selectEmploye()
				);
	}
	
	private Option supprimer(Ligue ligue)
	{
		return new Option("Supprimer", "d", () -> {try {
			ligue.remove();
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
	}
	
}
