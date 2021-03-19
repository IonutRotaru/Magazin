package start;

import java.util.ArrayList;

import bll.Canceled_bll;
import bll.Client_bll;
import bll.Order_bll;
import bll.Product_bll;
import model.Canceled_order;
import model.Client;
import model.Order;
import model.Product;
/**
 * Aceasta clasa se ocupa cu instructiunile de inserare, stergere si update. In functie de tabelul unde se lucreaza, se folosesc metodele necesare.
 * @author Ionut
 *
 */
public class Generator {
	
	private int cod_t,cod_i,cantitate;
	String nume_c,nume_p,adresa;
	double pret;
	int id=0;
	
	public Generator (int c_t,int c_i,int cant,double p,String n_c,String n_p,String adr)
	{
		cod_t=c_t;
		cod_i=c_i;
		cantitate=cant;
		nume_c=n_c;
		nume_p=n_p;
		adresa=adr;
		pret=p;
	}
	/**
	 * gaseste tabelul care trebuie modificat
	 */
	public void pornire()
	{
		if(cod_t==1)
			tabClient();
		
		else if(cod_t==2)
			tabProduct();
		else if(cod_t==3)
			tabOrder();
		
	}
	/**
	 * modificari in tabelul Client (inserari,stergeri)
	 */
	public void  tabClient()
	{
		Client_bll cc = new Client_bll();
		
		if(cod_i==1)
		{
			Client c=new Client(nume_c,adresa);
			
			cc.insertClient(c);
		}
		
		if(cod_i==2)
		{
			Client c=new Client(nume_c,adresa);		
			cc.deleteClient(c);
			ArrayList<Order> o=new ArrayList<Order>();
			 o=Order_bll.findOrderByName(nume_c);
			
			 Order_bll oo=new Order_bll();
			 Canceled_bll co =new Canceled_bll();
			
			for(Order order:o)
			{
				oo.deleteOrder(order);
				
				co.insertOrder(order, "client sters");
			}
				
		}
		
	}
	/**
	 * modificari in tabelul Product (inserari,stergeri)
	 */
	public void  tabProduct()
	{
		Product_bll pp = new Product_bll();
		
		if(cod_i==1)
		{
			Product p=new Product(nume_p,cantitate,pret);
			Product find = pp.findProductByName(nume_p);
				if(find==null)
						{
						
						pp.insertProduct(p);
						
						}
			else 
			{	
				if(find.getPret()==pret)
					pp.updateProduct(new Product(nume_p,cantitate+find.getCantitate(),pret));
				else 
					pp.insertProduct(p);
			}
		}
		
		
		if(cod_i==2)
		{	
			Product p=new Product(nume_p,cantitate,pret);		
			pp.deleteProduct(p);
		}
	}
	/**
	 * modificari in tabelul orders (inserari,stergeri)
	 */
	public void  tabOrder()
	{
		
		Order_bll oo = new Order_bll();
		Product_bll pp = new Product_bll();
		
		Product p=pp.findProductByName(nume_p);
		
		if(p!=null && p.getCantitate()>=cantitate)
			{
			Order o=new Order(id,nume_c,nume_p,cantitate);
			oo.insertOrder(o);
			
			p.setCantitate(p.getCantitate()-cantitate);
			pp.updateProduct(p);
			
			}
		else {
			Canceled_bll co = new Canceled_bll();
			co.insertOrder(new Order(id,nume_c,nume_p,cantitate), "stock insuficient");
		}
		
	
		
	}
	/**
	 * 
	 * @param i id-ul comenzii
	 */
	public void setId(int i)
	{
		id=i;
	}

}
