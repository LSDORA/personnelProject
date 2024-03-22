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
	    	String recupRoot = "SELECT * FROM employe WHERE root = 1 ";
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

	    	    gestionPersonnel.addRoot(nom, prenom, mail, password, dateA, dateD,id);
	    	}
	    	
	    	
	        String requeteLigues = "SELECT * FROM ligue";
	        Statement instructionLigues = connection.createStatement();
	        ResultSet resultLigues = instructionLigues.executeQuery(requeteLigues);

	       
	        while (resultLigues.next()) {
	            int idLigue = resultLigues.getInt("id_ligue");
	            String nomLigue = resultLigues.getString("nom");
	            Ligue ligue = gestionPersonnel.addLigue(idLigue, nomLigue);

	            
	            String requeteEmployes = "SELECT * FROM employe WHERE ligue = " + idLigue;
	            Statement instructionEmployes = connection.createStatement();
	            ResultSet resultEmployes = instructionEmployes.executeQuery(requeteEmployes);
                String requeteAdmin = "SELECT ADMIN_LIGUE FROM ligue WHERE id_ligue =" + idLigue;
                Statement instructionAdmin = connection.createStatement();
	            ResultSet resultAdmin = instructionAdmin.executeQuery(requeteAdmin);
	            
	            while (resultEmployes.next()) {
	                String nomEmploye = resultEmployes.getString("nom");
	                String prenomEmploye = resultEmployes.getString("prenom");
	                String mailEmploye = resultEmployes.getString("mail");
	                String passwordEmploye = resultEmployes.getString("password");
	                LocalDate dateArrive = resultEmployes.getDate("date_arrive").toLocalDate();
	                LocalDate dateDepart = resultEmployes.getDate("date_depart").toLocalDate();
	                int id = resultEmployes.getInt("ID_EMPLOYE");

	                ligue.addEmploye(nomEmploye, prenomEmploye, mailEmploye, passwordEmploye, dateArrive, dateDepart);
	                
	                if (resultAdmin.next()) {
	                    // Assuming ADMIN_LIGUE is a boolean field indicating whether the employee is an admin of the league
	                    int isAdmin = resultAdmin.getInt("ADMIN_LIGUE");
	                    if (id == isAdmin) {
	                    	 Employe administrateur =  ligue.addEmploye(nomEmploye, prenomEmploye, mailEmploye, passwordEmploye, dateArrive, dateDepart);
	                        ligue.setAdministrateur(administrateur);
	                    }
	                }
	            }

	         
	            
	            resultEmployes.close();
	            instructionEmployes.close();
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
	@Override
	public int insert(Employe employe) throws SauvegardeImpossible {
	    try {
	    	
	  
	        PreparedStatement instruction;
	        if (employe.getLigue() != null) {
	            instruction = connection.prepareStatement("INSERT INTO employe (nom, prenom, mail, password, ligue, date_arrive, date_depart) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	            instruction.setInt(5, employe.getLigue().getId());
	        } else {
	            instruction = connection.prepareStatement("INSERT INTO employe (nom, prenom, mail, password, date_arrive, date_depart) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        }
	        
	        instruction.setString(1, employe.getNom());
	        instruction.setString(2, employe.getPrenom());
	        instruction.setString(3, employe.getMail());
	        instruction.setString(4, employe.getPassword());
	        instruction.setDate(5, java.sql.Date.valueOf(employe.getdatearrive()));
	        instruction.setDate(6, java.sql.Date.valueOf(employe.getdatedepart()));

	        instruction.executeUpdate();
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
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM ligue WHERE nom = ? AND id_ligue = ?");
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

	@Override
	public int remove(Employe employe) throws SauvegardeImpossible {

		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM employe WHERE nom = ?");
			instruction.setString(1, employe.getNom());
			instruction.executeUpdate();
			return employe.getId();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}

	}


}
