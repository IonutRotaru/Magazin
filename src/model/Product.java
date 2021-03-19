package model;
/**
 * Model pentru produs
 * @author Ionut
 *
 */
public class Product {

	
	private String nume;
	private int cantitate;
	private double pret;
	
	public Product (String n,int c,double d)
	{
		nume= n;
		cantitate = c;
		pret = d;
	}
	/**
	 * se seteaza cantitatea de produse din depozit
	 * @param i cantitatea produselor din depozit
	 */
	public void setCantitate(int i)
	{
		cantitate=i;
	}
	/**
	 * se seteaza pretul produsului
	 * @param p pretul produsului
	 */
	public void setPret(double p)
	{
		pret = p;
	}
	/**
	 * se returneaza numele produsului
	 * @return numele produsului
	 */
	public String getName()
	{
		return nume;
	}
	/**
	 * se returneaza cantitatea ramasa in depozit
	 * @return cantitatea din depozit
	 */
	public int getCantitate()
	{
		return cantitate;
		
	}
	/**
	 * se returneaza pretul produsului
	 * @return pretul produsului
	 */
	public double getPret()
	{
		return pret;
	}
	
	public String toString()
	{
		return ""+nume+" "+cantitate+" "+pret;
	}
}
