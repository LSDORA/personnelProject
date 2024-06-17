package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import personnel.*;

public class JDBC implements Passerelle 
{
	Connection connection;


	public JDBC()
	{
		try
		{
			Class.forName(Credentials.getDriverClassName());
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public GestionPersonnel getGestionPersonnel() throws SauvegardeImpossible {

	    GestionPersonnel gestionPersonnel = new GestionPersonnel();

	    try {
	        // Récupération du root
	        String recupRoot = "SELECT * FROM employe WHERE root = 1";
	        Statement instructionRoot = connection.createStatement();
	        ResultSet resultRoot = instructionRoot.executeQuery(recupRoot);

	        if (resultRoot.next()) { 
	            String nom = resultRoot.getString("nom");
	            String prenom = resultRoot.getString("prenom");
	            String mail = resultRoot.getString("mail");
	            String password = resultRoot.getString("password");
	            LocalDate dateA = resultRoot.getDate("date_arrive").toLocalDate();
	            LocalDate dateD = resultRoot.getDate("date_depart").toLocalDate();
	            int id = resultRoot.getInt("ID_EMPLOYE");

	            gestionPersonnel.addRoot(nom, prenom, mail, password, dateA, dateD, id);
	        }
	        resultRoot.close();
	        instructionRoot.close();

	        // Récupération des ligues
	        String requeteLigues = "SELECT * FROM ligue";
	        Statement instructionLigues = connection.createStatement();
	        ResultSet resultLigues = instructionLigues.executeQuery(requeteLigues);

	        while (resultLigues.next()) {
	            int idLigue = resultLigues.getInt("id_ligue");
	            String nomLigue = resultLigues.getString("nom");
	            System.out.println("Récupération de la ligue : " + nomLigue + " (ID: " + idLigue + ")");

	            Ligue ligue = gestionPersonnel.addLigue(idLigue, nomLigue);

	            // Récupération des employés pour la ligue
	            String requeteEmployes = "SELECT * FROM employe WHERE ligue = " + idLigue;
	            Statement instructionEmployes = connection.createStatement();
	            ResultSet resultEmployes = instructionEmployes.executeQuery(requeteEmployes);

	            while (resultEmployes.next()) {
	                String nomEmploye = resultEmployes.getString("nom");
	                String prenomEmploye = resultEmployes.getString("prenom");
	                String mailEmploye = resultEmployes.getString("mail");
	                String passwordEmploye = resultEmployes.getString("password");
	                LocalDate dateArrive = resultEmployes.getDate("date_arrive").toLocalDate();
	                LocalDate dateDepart = resultEmployes.getDate("date_depart").toLocalDate();
	                int id = resultEmployes.getInt("ID_EMPLOYE");

	                Employe employe = ligue.addEmploye(nomEmploye, prenomEmploye, mailEmploye, passwordEmploye, dateArrive, dateDepart, id);
	                System.out.println("    Employé ajouté : " + nomEmploye + " " + prenomEmploye);
	            }
                     
	            // Vérification de l'administrateur de la ligue
	            String requeteAdmin = "SELECT ADMIN_LIGUE FROM ligue WHERE id_ligue =" + idLigue;
	            Statement instructionAdmin = connection.createStatement();
	            ResultSet resultAdmin = instructionAdmin.executeQuery(requeteAdmin);

	            if (resultAdmin.next()) {
	                int isAdmin = resultAdmin.getInt("ADMIN_LIGUE");
	                for (Employe employe : ligue.getEmployes()) {
	                    if (employe.getId() == isAdmin) {
	                        ligue.setAdministrateur(employe);
	                        System.out.println("    Administrateur de la ligue : " + employe.getNom() + " " + employe.getPrenom());
	                        System.out.println("Test"+employe.getLigue().getAdministrateur());
	                        break;
	                    }
	                }
	            }

	            resultEmployes.close();
	            instructionEmployes.close();
	            resultAdmin.close();
	            instructionAdmin.close();
	        }

	        resultLigues.close();
	        instructionLigues.close();
	    } catch (SQLException e) {
	        System.out.println(e);
	    }
	    return gestionPersonnel;
	}





	@Override
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible 
	{
		close();
	}
	
	public void close() throws SauvegardeImpossible
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	
	@Override
	public int insert(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("insert into ligue (nom) values(?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	
	@Override
	public int update(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE ligue SET nom = ? WHERE id_ligue = ?");
			instruction.setString(1, ligue.getNom());
			instruction.setInt(2, ligue.getId());
			instruction.executeUpdate();
			return ligue.getId();

		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	
	private boolean employeExists(String email) throws SQLException {
	    PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM employe WHERE mail = ?");
	    stmt.setString(1, email);
	    ResultSet rs = stmt.executeQuery();
	    rs.next();
	    int count = rs.getInt(1);
	    return count > 0;
	}
	
	@Override
	public int insert(Employe employe) throws SauvegardeImpossible {
	    try {
	    	
	    	if (employeExists(employe.getMail())) {
	    		return 0;
	        }
	    	
	        PreparedStatement instruction;
	        if (employe.getLigue() != null) {
	            instruction = connection.prepareStatement("INSERT INTO employe (nom, prenom, mail, password, ligue, date_arrive, date_depart) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	            instruction.setString(1, employe.getNom());
	            instruction.setString(2, employe.getPrenom());
	            instruction.setString(3, employe.getMail());
	            instruction.setString(4, employe.getPassword());
	            instruction.setInt(5, employe.getLigue().getId());
	            instruction.setDate(6, java.sql.Date.valueOf(employe.getdatearrive()));
	            instruction.setDate(7, java.sql.Date.valueOf(employe.getdatedepart()));
	        } else {
	            instruction = connection.prepareStatement("INSERT INTO employe (nom, prenom, mail, password, date_arrive, date_depart) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	            instruction.setString(1, employe.getNom());
	            instruction.setString(2, employe.getPrenom());
	            instruction.setString(3, employe.getMail());
	            instruction.setString(4, employe.getPassword());
	            instruction.setDate(5, java.sql.Date.valueOf(employe.getdatearrive()));
	            instruction.setDate(6, java.sql.Date.valueOf(employe.getdatedepart()));
	        }
	     
	        instruction.executeUpdate(); // Execute the update after setting all parameters

	        ResultSet id = instruction.getGeneratedKeys();
	        id.next();
	        return id.getInt(1);
	    } catch (SQLException exception) {
	        exception.printStackTrace();
	        throw new SauvegardeImpossible(exception);
	    }
	}

	@Override
	public int update(Employe employe) throws SauvegardeImpossible {
		
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE employe SET nom = ?, prenom = ?, mail = ?, password = ?, date_arrive = ?, date_depart = ? WHERE ID_EMPLOYE = ?");
			instruction.setString(1, employe.getNom());
	        instruction.setString(2, employe.getPrenom());
	        instruction.setString(3, employe.getMail());
	        instruction.setString(4, employe.getPassword());
	        instruction.setDate(5, java.sql.Date.valueOf(employe.getdatearrive()));
	        instruction.setDate(6, java.sql.Date.valueOf(employe.getdatedepart()));
	        instruction.setInt(7,employe.getId());
			instruction.executeUpdate();
			
			return employe.getId();

		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}	
	}

	@Override
	public int remove(Ligue ligue) throws SauvegardeImpossible {
	    try {
	        PreparedStatement deleteEmployees;
	        deleteEmployees = connection.prepareStatement("DELETE FROM employe WHERE ligue = ?");
	        deleteEmployees.setInt(1, ligue.getId());
	        deleteEmployees.executeUpdate();
	        
	        PreparedStatement deleteLeague;
	        deleteLeague = connection.prepareStatement("DELETE FROM ligue WHERE nom = ? AND id_ligue = ?");
	        deleteLeague.setString(1, ligue.getNom());
	        deleteLeague.setInt(2, ligue.getId());
	        deleteLeague.executeUpdate();
	        
	        return ligue.getId();
	    } catch (SQLException exception) {
	        exception.printStackTrace();
	        throw new SauvegardeImpossible(exception);
	    }       
	}

	@Override
	public int remove(Employe employe) throws SauvegardeImpossible {

		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM employe WHERE nom = ? AND ID_EMPLOYE = ?");
			instruction.setString(1, employe.getNom());
			instruction.setInt(2, employe.getId());
			instruction.executeUpdate();
			return employe.getId();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}

	}


}
