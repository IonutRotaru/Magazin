package connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


/**
 * Crearea unei conexiuni cu baza de date
 * @author Ionut
 *
 */
public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "botosani123";
	
	private static ConnectionFactory singleInstance = new ConnectionFactory();
	
	ConnectionFactory()
	{
		try {
			Class.forName(DRIVER);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * creare conexiune
	 * @return conexiunea la baza de date
	 */
	private Connection createConnection()
	{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL,USER,PASS);
			
		}
		catch(SQLException e){
			System.out.println("ERROR:Unable to Connect to Database");
		}
		return connection;
	}
	/**
	 * returnare conexiune
	 * @return conexiunea labaza de date
	 */
	public static Connection getConnection()
	{
		return singleInstance.createConnection();
	}
	public static void close(Connection connection)
	{
		if(connection != null)
		{
			try {
				connection.close();
			}
			catch (SQLException e)
			{
				/*Ignore*/
			}
		}
	}
	/**
	 * inchidere conexiune
	 * @param statement statementul
	 */
	public static void close(Statement statement)
	{
		if(statement != null)
		{
			try {
				statement.close();
			}
			catch(SQLException e)
			{
				/*Ignore*/
			}
		}
	}
	
	public static void close(ResultSet resultSet)
	{
		if(resultSet !=null)
		{try {
			resultSet.close();
		}
		catch(SQLException e)
		{
			/*Ignore*/
		}
	}}
}
