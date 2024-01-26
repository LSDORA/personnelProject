package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static commandLineMenus.rendering.examples.util.InOut.getInt;


import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.Employe;


public class EmployeConsole 
{
	private LigueConsole ligueConsole;
	private Option afficher(final Employe employe)
	{
		return new Option("Afficher la date d'arriver de l'employé", "l", () -> {System.out.println(employe.getdatearrive());});
	}

	ListOption<Employe> selectEmploye()
	{
		return (employe) -> selectEmploye(employe);		
	}
	
	Option editerEmploye(Employe employe)
	{
		Menu menu = new Menu("Editer le compte " + employe.getNom(), "c");
		menu.add(afficher(employe));
		menu.add(changerNom(employe));
		menu.add(changerPrenom(employe));
		menu.add(changerMail(employe));
		menu.add(changerPassword(employe));
		menu.add(changerDateArrive(employe));
		menu.add(changerDateDepart(employe));
		menu.addBack("q");
		return menu;
	}

	Option selectEmploye(Employe employe)
	{
		Menu menu = new Menu("sélectinoner le compte " + employe.getNom(), "c");
	    menu.add(supprimeEmploye(employe));
		menu.add(editerEmploye(employe));			
		menu.addBack("q");
		return menu;
			
	}
	
	private Option supprimeEmploye(final Employe employe) {
		return new Option("Supprimer le compte " + employe.getNom(), "m", 
				() -> {employe.remove();}
			);
	}

	private Option changerNom(final Employe employe)
	{
		return new Option("Changer le nom", "n", 
				() -> {employe.setNom(getString("Nouveau nom : "));}
			);
	}
	
	private Option changerPrenom(final Employe employe)
	{
		return new Option("Changer le prénom", "p", () -> {employe.setPrenom(getString("Nouveau prénom : "));});
	}
	private Option changerDateArrive(final Employe employe) {
	    return new Option("Changer la date d'arrivée", "y", () -> {
	        String nouvelleDateArriveeStr = getString("Nouvelle date d'arrivée (YYYY-MM-DD) : ");
	        try {
	            LocalDate nouvelleDateArrivee = LocalDate.parse(nouvelleDateArriveeStr);
	            employe.setdatearrive(nouvelleDateArrivee);
	        } catch (DateTimeParseException e) {
	        	System.err.println("Erreur de format de date : Utilisez le format YYYY-MM-DD.");
	        }
	    });
	}

	private Option changerDateDepart(final Employe employe) {
	    return new Option("Changer la date de départ", "z", () -> {
	        String nouvelleDateDepartStr = getString("Nouvelle date de départ (YYYY-MM-DD) : ");
	        try {
	            LocalDate nouvelleDateDepart = LocalDate.parse(nouvelleDateDepartStr);
	            employe.setdatedepart(nouvelleDateDepart);
	        } catch (DateTimeParseException e) {
	        	System.err.println("Erreur de format de date : Utilisez le format YYYY-MM-DD.");
	        }
	    });
	}

	private Option changerMail(final Employe employe)
	{
		return new Option("Changer le mail", "e", () -> {employe.setMail(getString("Nouveau mail : "));});
	}
	
	private Option changerPassword(final Employe employe)
	{
		return new Option("Changer le password", "x", () -> {employe.setPassword(getString("Nouveau password : "));});
	}
	

}
