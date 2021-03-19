package dao;
import model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * Se stabileste legatura cu baza de date si se aplica inerogarile pe tabele.
 * @author ACER
 *
 */
public class Client_dao {
	
	protected static final Logger LOGGER = Logger.getLogger(Client_dao.class.getName());
	private static final String insertStatementString = "INSERT INTO client (nume,adresa)"
			+ " VALUES (?,?)";
	private final static String findStatementString = "SELECT * FROM client where nume = ?";
	private final static String findAllStatementString = "SELECT * FROM client";
	private final static String delStatementString = "DELETE FROM client WHERE nume = ? AND adresa = ?";
/**
 * Se aplica o interogarein tabelul Client.
 * @param name numele clientului cautat
 * @return clientul cautat
 */
	public static Client findByname(String name) {
	
		Client toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			rs.next();

			String nume = rs.getString("nume");
			String address = rs.getString("adresa");
			
			 toReturn = new Client(nume, address);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	/**
	 * Se aplica o interogare pentru inserare in tabelul Client.
	 * @param client clientulcare trebuie adaugat
	 * @return validator
	 */
	public static String insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		String inserted =null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1,  client.getName());
			insertStatement.setString(2, client.getAddress());
			
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			
			if (rs.next()) {
				inserted = rs.getString(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClienttDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return inserted;
	}
	/**
	 * Se aplicao interogarein tabelul Client pentru a se sterge date.
	 * @param client clientul care trebuie sters
	 * @return validator
	 */
	public static String delete(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement delStatement = null;
		String inserted = "Client sters";
		try {
			delStatement = dbConnection.prepareStatement(delStatementString);
			delStatement.setString(1,  client.getName());
			delStatement.setString(2,  client.getAddress());
			
			
			delStatement.executeUpdate();

			
			
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(delStatement);
			ConnectionFactory.close(dbConnection);
		}
		return inserted;
	}
	/**
	 * Se cauta toate datele din tabelul CLient.
	 * @return lista cu toti clientii din baza de date.
	 */
	public static ArrayList<Client> findAll() {
		
		ArrayList<Client>toReturn = new ArrayList<Client>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			
			rs = findStatement.executeQuery();
			while(rs.next())
			{
			String nume = rs.getString("nume");
			String address = rs.getString("adresa");
			Client c=new Client(nume,address);
			toReturn.add(c);
			}
			
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

}
