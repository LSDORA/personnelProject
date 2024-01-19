package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import commandLineMenus.List;
import personnel.*;

class testLigue 
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	 @Test
	    void createLigue() throws SauvegardeImpossible {
	        GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	        Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
	        assertEquals("Fléchettes", ligue.getNom());
	        
	    }
	 @Test
	 void removeLigue() throws SauvegardeImpossible {
	     GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	     Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
	     ligue.remove();

	     ligue.getAdministrateur();
	     String actualLiguesAsString = gestionPersonnel.getLigues().toString();

	     assertEquals("[]", actualLiguesAsString);
	 }
    @Test
	 void testChangerAdministrateur() {
	        try {
	            GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	            Ligue ligue = new Ligue(gestionPersonnel, "Admin");

	            Employe newAdmin = ligue.addEmploye("New", "Admin", "newadmin@mail.com", "password", LocalDate.now(), LocalDate.now().plusMonths(6));
                
	            assertEquals(gestionPersonnel.getRoot(), ligue.getAdministrateur());
	          
	            ligue.setAdministrateur(newAdmin);
	            

	            assertEquals(newAdmin, ligue.getAdministrateur());

	        } catch (SauvegardeImpossible e) {
	            fail("Exception thrown: " + e.getMessage());
	        }
	    }
    @Test
	 void testSupprimerAdministrateur() {
	        try {
	            GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	            Ligue ligue = new Ligue(gestionPersonnel, "Admin");


	            Employe newAdmin = ligue.addEmploye("New", "Admin", "newadmin@mail.com", "password", LocalDate.now(), LocalDate.now().plusMonths(6));
               
	          
	            ligue.setAdministrateur(newAdmin);
	            assertEquals(newAdmin, ligue.getAdministrateur());
	            
	            newAdmin.remove();
	            String actualEmployeAsString = ligue.getEmployes().toString();
	            
	            // Comparer la liste avec une liste vide
	            assertEquals("[]",actualEmployeAsString);

	      

	        } catch (SauvegardeImpossible e) {
	            fail("Exception thrown: " + e.getMessage());
	        }
	    }
}

