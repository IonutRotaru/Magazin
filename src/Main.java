import model.Client;
import model.Order;
import model.Product;
import start.Generator;
import start.Generator_report;
import start.Instructiune;

import java.io.File;
import java.util.Scanner;

import javax.swing.text.Document;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bll.Client_bll;
import dao.Client_dao;
import dao.Order_dao;
import dao.Product_dao;
public class Main {

	public static void main(String[] args) throws Exception {
		
		int count=1;
		File myObj = new File(args[0]);
		
		int id=1,index_pdf=1;
	      
		Scanner myReader = new Scanner(myObj);
	     
	      String [] words =null;
	     
	      while (myReader.hasNextLine()) {
	       
	    	  String data = myReader.nextLine();
	       
	    	  words = data. split(" ", 10);
	        
	        Instructiune i=new Instructiune();
	      
	        i.find(words);
	       
	        if(i.getInstr()!=-1)
	        {
	      
	        Generator g= new Generator(i.getTab(),i.getInstr(),i.getCant(),i.getPret(),i.getNumeC(),i.getNumep(),i.getAdr());
        	g.setId(id++);
	        g.pornire();
	        
	        Generator_report a=new Generator_report(i.getTab(),index_pdf);
	        
	        if(i.getTab()==3)
	        		{a.generateBill(i.getNumeC(),i.getNumep(),i.getCant(),i.getPret());
	        		index_pdf++;
	        		}
	        
	        }
	        else 
	        {
	        	Generator_report  a= new Generator_report(i.getTab(),index_pdf);
	        	index_pdf++;
	        	a.pornire();
	        }
	        System.out.println("linia:"+count);
	        count++;
	        
	      	}
	      Generator_report b=new Generator_report(0,0);
	      b.generateT4();
		// TODO Auto-generated method stub
		
	     
	
	}}


