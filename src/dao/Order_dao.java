package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Order;

import connection.ConnectionFactory;

/**
 * 
 * @author Ionut
 *
 */
public class Order_dao {

	
	protected static final Logger LOGGER = Logger.getLogger(Client_dao.class.getName());
	private static final String insertStatementString = "INSERT INTO orders (id,nume,produs,cantitate)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM orders where nume = ?";
	private final static String findAllStatementString = "SELECT * FROM orders";
	private final static String delStatementString = "DELETE FROM orders WHERE nume = ?";
/**
 * 
 * @param name numele clientului din comanda
 * @return lista cu comenzile clientului
 */
	public static ArrayList<Order> findByname(String name) {
	
		ArrayList<Order> toReturn = new ArrayList<Order>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, name);
			
			rs = findStatement.executeQuery();
			while(rs.next())
			{
			String nume = rs.getString("nume");
			int id = rs.getInt("id");
			int cantitate = rs.getInt("cantitate");
			String produs = rs.getString("produs");
			Order o=new Order(id,nume,produs,cantitate);
			 toReturn.add(o);
			}
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	/**
	 * 
	 * @param order comanda clientului
	 * @return validator
	 */
	public static String insert(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		String inserted = "no";
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, order.getId());
			insertStatement.setString(2,  order.getName());
			insertStatement.setString(3, order.getProduct());
			insertStatement.setInt(4, order.getCantitate());
			
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				inserted = rs.getString(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return inserted;
	}
	/**
	 * 
	 * @param order comanda care trebuie stearsa
	 * @return validator
	 */
	public static String delete(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement delStatement = null;
		String inserted = null;
		try {
			delStatement = dbConnection.prepareStatement(delStatementString);
			delStatement.setString(1,  order.getName());
			
		
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
	 * 
	 * @return lista cu toate comenzile
	 */
	public static ArrayList<Order> findAll() {
		
		ArrayList<Order> toReturn = new ArrayList<Order>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			
			
			rs = findStatement.executeQuery();
			while(rs.next())
			{
			String nume = rs.getString("nume");
			int id = rs.getInt("id");
			int cantitate = rs.getInt("cantitate");
			String produs = rs.getString("produs");
			Order o =new Order(id,nume,produs,cantitate);
			 toReturn.add(o);
			}
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	
}
