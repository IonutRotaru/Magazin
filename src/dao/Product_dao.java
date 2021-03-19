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
import model.Client;
import model.Product;
/**
 * 
 * @author Ionut
 *
 */
public class Product_dao {

	protected static final Logger LOGGER = Logger.getLogger(Client_dao.class.getName());
	private static final String insertStatementString = "INSERT INTO product (nume,cantitate,pret)"
			+ " VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM product where nume = ?";
	private final static String findAllStatementString = "SELECT * FROM product";
	private final static String delStatementString = "DELETE FROM product WHERE nume = ?";
	private static final String updateStatementString = "UPDATE product SET cantitate = ? WHERE nume = ?";
/**
 * 
 * @param name numele produsului cautat
 * @return produsul cautat
 */
	public static Product findByname(String name) {
	
		Product toReturn = null;
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
			int cantitate = rs.getInt("cantitate");
			double pret = rs.getDouble("pret");
			
			 toReturn = new Product(nume, cantitate,pret);
			}			
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	/**
	 * 
	 * @param product produsul adaugat
	 * @return validator
	 */
	public static String insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		String inserted = "no";
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1,  product.getName());
			insertStatement.setInt(2, product.getCantitate());
			insertStatement.setDouble(3, product.getPret());
			
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
	 * 
	 * @param product produsul care trebuie sters
	 * @return validator
	 */
	public static String delete(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement delStatement = null;
		String del = "Produs sters";
		try {
			delStatement = dbConnection.prepareStatement(delStatementString);
			delStatement.setString(1,  product.getName());
			
			
			delStatement.executeUpdate();

			
			
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(delStatement);
			ConnectionFactory.close(dbConnection);
		}
		return del;
	}
	/**
	 * 
	 * @param product produsul care trebuie modificat
	 * @return validator
	 */
	public static String update(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		String inserted = "no";
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString,Statement.RETURN_GENERATED_KEYS);
			
			updateStatement.setInt(1, product.getCantitate());
			updateStatement.setString(2,  product.getName());
			
			updateStatement.executeUpdate();

			ResultSet rs = updateStatement.getGeneratedKeys();
			if (rs.next()) {
				inserted = rs.getString(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClienttDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return inserted;
	}
	/** 
	 * 
	 * @return lista cu toate produsele din depozit
	 */
	public static ArrayList<Product> findAll() {
		
		ArrayList<Product> toReturn = new ArrayList<Product>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findAllStatementString);
			
			rs = findStatement.executeQuery();
			
			while(rs.next()) 
			{
			String nume = rs.getString("nume");			
			int cantitate = rs.getInt("cantitate");
			double pret = rs.getDouble("pret");
			Product p=new Product(nume, cantitate,pret);
			 if(p!=null) 
				 toReturn.add(p); 
			}			
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	
}
