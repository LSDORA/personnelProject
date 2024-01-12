package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import personnel.*;

class TestAjouterEmploye {

    GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();

    @Test
    void createLigue() throws SauvegardeImpossible {
        Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
        assertEquals("Fléchettes", ligue.getNom());
    }

    @Test
    void addEmploye() throws SauvegardeImpossible {
        Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
        Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.now());
        assertEquals(employe, ligue.getEmployes().first());
    }
    
    @Test
    void addEmploye1() throws SauvegardeImpossible {
        Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
        Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.of(20222,12,01));
        assertEquals(employe, ligue.getEmployes().first());
    }
}
