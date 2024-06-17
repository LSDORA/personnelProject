package commandLine;

import java.util.Scanner;
import personnel.*;
import commandLineMenus.*;
import interfaceGraphique.PersonnelInterface;

import static commandLineMenus.rendering.examples.util.InOut.*;

public class PersonnelConsole
{
	private GestionPersonnel gestionPersonnel;
	LigueConsole ligueConsole;
	EmployeConsole employeConsole;
	
	public PersonnelConsole(GestionPersonnel gestionPersonnel)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.employeConsole = new EmployeConsole();
		this.ligueConsole = new LigueConsole(gestionPersonnel, employeConsole);
	}
	
	public void start()
	{
		menuPrincipal().start();
	}
	
	private Menu menuPrincipal()
	{
		Menu menu = new Menu("Gestion du personnel des ligues");
		menu.add(employeConsole.editerEmploye(gestionPersonnel.getRoot()));
		menu.add(ligueConsole.menuLigues());
		menu.add(menuQuitter());
		return menu;
	}

	private Menu menuQuitter()
	{
		Menu menu = new Menu("Quitter", "q");
		menu.add(quitterEtEnregistrer());
		menu.add(quitterSansEnregistrer());
		menu.addBack("r");
		return menu;
	}
	
	private Option quitterEtEnregistrer()
	{
		return new Option("Quitter et enregistrer", "q", 
				() -> 
				{
					try
					{
						gestionPersonnel.sauvegarder();
						Action.QUIT.optionSelected();
					} 
					catch (SauvegardeImpossible e)
					{
						System.out.println("Impossible d'effectuer la sauvegarde");
					}
				}
			);
	}
	
	private Option quitterSansEnregistrer()
	{
		return new Option("Quitter sans enregistrer", "a", Action.QUIT);
	}
	
	private boolean verifiePassword()
	{
		boolean ok = gestionPersonnel.getRoot().checkPassword(getString("password : "));
		if (!ok)
			System.out.println("Password incorrect.");
		return ok;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenue dans MDL application !");
        System.out.println("Veuillez choisir le mode de lancement :");
        System.out.println("1. Mode ligne de commande");
        System.out.println("2. Mode graphique");
        System.out.print("Entrez 1 ou 2 : ");

        int choix = sc.nextInt();

        if (choix == 1) {
            // Lancer le mode ligne de commande
            System.out.println("Mode ligne de commande sélectionné !\n");
            // Ajoutez ici la logique pour le mode ligne de commande
            
            PersonnelConsole personnelConsole;
    		
    		try {
    			personnelConsole = new PersonnelConsole(GestionPersonnel.getGestionPersonnel());
    			if (personnelConsole.verifiePassword())
    				personnelConsole.start();
    		} catch (SauvegardeImpossible e) {
    			System.out.println("Impossible de se connecter à la base de donnée");
    			e.printStackTrace();
    		}
            
        } else if (choix == 2) {
            // Lancer le mode graphique
            System.out.println("Mode graphique sélectionné !\n");
            // Ajoutez ici la logique pour le mode graphique
            
            PersonnelInterface.main(args);
            
        } else {
            System.out.println("Choix invalide.\n Redémarer l'app et choisir l'option 1 Veuillez entrer 1 ou 2.");
        }
		/*PersonnelConsole personnelConsole;
		
		try {
			personnelConsole = new PersonnelConsole(GestionPersonnel.getGestionPersonnel());
			if (personnelConsole.verifiePassword())
				personnelConsole.start();
		} catch (SauvegardeImpossible e) {
			System.out.println("Impossible de se connecter à la base de donnée");
			e.printStackTrace();
		}*/
		
	}
}
