package model;
/**
 * Model pentru comanda.
 * @author Ionut
 *
 */
public class Order {

	public String name;
	public String product_name;
	public int cantitate;
	int id;
	
	public Order(int i,String n,String pn,int c)
	{	id=i;
		name=n;
		product_name=pn;
		cantitate = c;
	}
	/**
	 * se returneaza numele clientului
	 * @return numele clientului
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * se returneaza numele produsului
	 * @return numele produsului
	 */
	public String getProduct()
	{
		return product_name;
	}
	/**
	 * se returneaza cantitatea comandata
	 * @return cantitatea comandata
	 */
	public int getCantitate()
	{
		return cantitate;
	}
	/**
	 * se returneaza id-ul comenzii
	 * @return id-ul comenzii
	 */
	public int getId()
	{
		return id;
	}
	
	public String toString()
	{
		return ""+name+" "+product_name+" "+ cantitate;
	}
	
	
	
}
