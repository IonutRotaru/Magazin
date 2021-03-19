package model;

/**
 * Model pentru client.
 * @author Ionut
 *
 */
public class Client {

	
	private String name;
	private String address;
	
	
	public Client(String n,String a)
	{
		
		name=n;
		address=a;
		
	}
	public Client()
	{}
	/**
	 * se returneaza numele clientului
	 * @return numele clientului
	 */
	public String getName()
	{
		return name;
		
	}
	/**
	 * se returneaza adresa clientului
	 * @return adresa clientului
	 */
	public String getAddress()
	{
		return address;
		
	}
	
	public String toString()
	{
		String a="";
		return a+name+" "+address;
	}
	
}
