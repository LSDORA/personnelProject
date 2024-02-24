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
	        // Récupération des informations sur les ligues
	        String requeteLigues = "SELECT * FROM ligue";
	        Statement instructionLigues = connection.createStatement();
	        ResultSet resultLigues = instructionLigues.executeQuery(requeteLigues);

	        // Parcours des ligues
	        while (resultLigues.next()) {
	            int idLigue = resultLigues.getInt("id_ligue");
	            String nomLigue = resultLigues.getString("nom");
	            Ligue ligue = gestionPersonnel.addLigue(idLigue, nomLigue);

	            // Récupération des informations sur les employés associés à chaque ligue
	            String requeteEmployes = "SELECT * FROM employe WHERE ligue = " + idLigue;
	            Statement instructionEmployes = connection.createStatement();
	            ResultSet resultEmployes = instructionEmployes.executeQuery(requeteEmployes);

	            // Parcours des employés associés à la ligue actuelle
	            while (resultEmployes.next()) {
	                String nomEmploye = resultEmployes.getString("nom");
	                String prenomEmploye = resultEmployes.getString("prenom");
	                String mailEmploye = resultEmployes.getString("mail");
	                String passwordEmploye = resultEmployes.getString("password");
	                LocalDate dateArrive = resultEmployes.getDate("date_arrive").toLocalDate();
	                LocalDate dateDepart = resultEmployes.getDate("date_depart").toLocalDate();

	                ligue.addEmploye(nomEmploye, prenomEmploye, mailEmploye, passwordEmploye, dateArrive, dateDepart);
	            }
	            // Fermeture du ResultSet et du Statement pour les employés
	            resultEmployes.close();
	            instructionEmployes.close();
	        }
	        // Fermeture du ResultSet et du Statement pour les ligues
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
}
