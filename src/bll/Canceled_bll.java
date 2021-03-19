package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import dao.Canceled_dao;
import dao.Order_dao;
import model.Canceled_order;
import model.Order;
/**
 * Clasa pentru interactiunea cu baza de date.
 * @author Ionut
 *
 */
public class Canceled_bll {

	
	public Canceled_bll()
	{
		
	}

	
/**
 * se insereaza ocomanda noua
 * @param order comanda anulata 
 * @param reason motivul anularii
 * @return validator
 */
	public String insertOrder(Order order,String reason) {
	
		return Canceled_dao.insert(order,reason);
	}
	/**
	 * se sterge comanda dorita
	 * @param o comanda anulata care trebuie stearsa
	 * @return validator
	 */
public String deleteOrder(Order o) {
		
		return Canceled_dao.delete(o);
	}

/** 
 * se genereaza toate comenzile anulate
 * @return lista cu toate comenzile anulate
 */
public ArrayList<Canceled_order>findAll()
{
	return Canceled_dao.findAll();
}

	
}
