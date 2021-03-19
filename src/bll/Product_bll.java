package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import dao.Client_dao;
import dao.Product_dao;
import model.Client;
import model.Product;
/**
 * Clasa pentru interactiunea cu baza de date.
 * @author Ionut
 *
 */
public class Product_bll {

	
	public Product_bll()
	{
		
	}
	/**
	 * se cauta un produs dupa nume
	 * @param name numele produsului cautat
	 * @return produsul cautat
	 */
	public Product findProductByName(String name) {
		Product c = Product_dao.findByname(name);
		
		return c;
	}
	/**
	 * se introduce un produs in depozit
	 * @param p produsul care trebuie inserat
	 * @return validator
	 */
	public String insertProduct(Product p) {
	
		return Product_dao.insert(p);
	}
	/**
	 * se sterge produsul dorit
	 * @param p produsul care trebuie sters
	 * @return validator
	 */
	public String deleteProduct(Product p) {
		
		return Product_dao.delete(p);
	}
	/**
	 * se modifica stockul unui produs
	 * @param p produsul care trebuie modificat
	 * @return validator
	 */
public String updateProduct(Product p) {
		
		return Product_dao.update(p);
	}
/**
 * 
 * @return lista cu toate produsele dindepozit
 */
public ArrayList<Product> findAll()
{
	return Product_dao.findAll();
}
	
}
