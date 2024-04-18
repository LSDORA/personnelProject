package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import commandLineMenus.List;
import personnel.*;

class testLigue 
{
	GestionPersonnel gestionPersonnel; 
	private void setup() throws SauvegardeImpossible {
	gestionPersonnel= GestionPersonnel.getGestionPersonnel();
	}
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
	            Ligue ligue = gestionPersonnel.addLigue("Admin");

	            Employe newAdmin = ligue.addEmploye("New", "Admin", "newadmin@mail.com", "password", LocalDate.now(), LocalDate.now().plusMonths(6));
        
	            System.out.println(newAdmin.getLigue().getId());

	        } catch (SauvegardeImpossible e) {
	            fail("Exception thrown: " + e.getMessage());
	        }
	    }
}