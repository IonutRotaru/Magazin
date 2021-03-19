package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import dao.Client_dao;
import dao.Order_dao;
import model.Client;
import model.Order;
/**
 * Clasa pentru interactiunea cu baza de date
 * @author Ionut
 *
 */
public class Order_bll {

	public Order_bll()
	{
		
	}

	public static ArrayList<Order> findOrderByName(String name) {
		ArrayList<Order> o = Order_dao.findByname(name);
		
		return o;
	}
/**
 * se insereaza o comanda in baza de date
 * @param order comanda care trebuie adaugata
 * @return validator
 */
	public String insertOrder(Order order) {
	
		return Order_dao.insert(order);
	}
	/**
	 * se sterge o anumita comanda
	 * @param o comanda care trebuie stearsa
	 * @return calidator
	 */
public  String deleteOrder(Order o) {
		
		return Order_dao.delete(o);
	}
/**
 * se genereaza toate comenzile
 * @return lista cu toate comenzile
 */
public ArrayList<Order>findAll()
{
	return Order_dao.findAll();
}

	
}
