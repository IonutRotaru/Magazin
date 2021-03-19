package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import dao.Client_dao;
import model.Client;
/**
 * Clasa pentru interactiunea cu baza de date.
 * @author Ionut
 *
 */
public class Client_bll {

	
	public Client_bll()
	{
		
	}
	/**
	 * se cauta clientii cu numele dat
	 * @param name numele clientului cautat
	 * @return clientul cautat
	 */
	

	public Client findClientById(String name) {
		Client c = Client_dao.findByname(name);
		if (c == null) {
			throw new NoSuchElementException("The client with name =" + name + " was not found!");
		}
		return c;
	}
	/**
	 * inserare client
	 * @param client clientul care trebuie inserat
	 * @return validator
	 */
	public String insertClient(Client client) {
	
		return Client_dao.insert(client);
	}
	/**
	 * stergere client
	 * @param client clientulcare trebuie sters
	 * @return validator
	 */
	public String deleteClient(Client client) {
		
		return Client_dao.delete(client);
	}
	/**
	 * se genereaza toti clientii din baza de date
	 * @return lista cu toti clientii
	 */
public ArrayList<Client> findAll() {
		
		return Client_dao.findAll();
	}
	
}
