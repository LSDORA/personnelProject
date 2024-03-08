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
	public GestionPersonnel getGestionPersonnel() {
	    GestionPersonnel gestionPersonnel = new GestionPersonnel();
	    try {
	     
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

	            
	            while (resultEmployes.next()) {
	                String nomEmploye = resultEmployes.getString("nom");
	                String prenomEmploye = resultEmployes.getString("prenom");
	                String mailEmploye = resultEmployes.getString("mail");
	                String passwordEmploye = resultEmployes.getString("password");
	                LocalDate dateArrive = resultEmployes.getDate("date_arrive").toLocalDate();
	                LocalDate dateDepart = resultEmployes.getDate("date_depart").toLocalDate();

	                ligue.addEmploye(nomEmploye, prenomEmploye, mailEmploye, passwordEmploye, dateArrive, dateDepart);
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
	public int insert(Employe employe) throws SauvegardeImpossible {
	    try {
	        PreparedStatement instruction;
	        instruction = connection.prepareStatement("INSERT INTO employe (nom, prenom, mail, password, ligue, date_arrive, date_depart) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        instruction.setString(1, employe.getNom());
	        instruction.setString(2, employe.getPrenom());
	        instruction.setString(3, employe.getMail());
	        instruction.setString(4, employe.getPassword());
	        instruction.setInt(5, employe.getLigue().getId());
	        instruction.setDate(6, java.sql.Date.valueOf(employe.getdatearrive()));
	        instruction.setDate(7, java.sql.Date.valueOf(employe.getdatedepart()));
	        
	        instruction.executeUpdate();
	        ResultSet id = instruction.getGeneratedKeys();
	        id.next();
	        return id.getInt(1);
	    } catch (SQLException exception) {
	        exception.printStackTrace();
	        throw new SauvegardeImpossible(exception);
	    }
	}

}
