package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Canceled_order;
import model.Order;
/**
 * Se stabileste legatura cu baza de date si se aplica inerogarile pe tabele.
 * @author Ionut
 *
 */
public class Canceled_dao {

	protected static final Logger LOGGER = Logger.getLogger(Client_dao.class.getName());
	private static final String insertStatementString = "INSERT INTO canceled_orders (id,reason)"
			+ " VALUES (?,?)";
	private final static String findAllStatementString = "SELECT * FROM canceled_orders";
	private final static String delStatementString = "DELETE FROM canceled_orders WHERE id=?";

	
	/**
	 * 
	 * @param order comanda care trebuie anulata
	 * @param reason motivulanularii comenzii
	 * @return validator
	 */
	public static String insert(Order order,String reason) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		String inserted = "no";
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, order.getId());
			insertStatement.setString(2,  reason);
			
			
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				inserted = rs.getString(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CanceledDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return inserted;
	}
	/**
	 * 
	 * @param order comanda cu id-ul care trebuie sters
	 * @return validator
	 */
	public static String delete(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement delStatement = null;
		String inserted = "Comanda stearsa";
		try {
			delStatement = dbConnection.prepareStatement(delStatementString);
			delStatement.setInt(1,  order.getId());
		
			delStatement.executeUpdate();
		
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CanceledDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(delStatement);
			ConnectionFactory.close(dbConnection);
		}
		return inserted;
	}
	/**
	 * 
	 * @return lista cu toate comenzile anulate
	 */
	public static ArrayList<Canceled_order> findAll() {
		
		ArrayList<Canceled_order> toReturn = new ArrayList<Canceled_order>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			
			
			rs = findStatement.executeQuery();
			while(rs.next())
			{
			int id = rs.getInt("id");
			
			String reason = rs.getString("reason");
			
			Canceled_order o =new Canceled_order(id,reason);
			 toReturn.add(o);
			}
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"CanceledDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	
}
