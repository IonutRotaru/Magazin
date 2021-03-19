package start;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.xdevapi.Client;

import bll.Canceled_bll;
import bll.Client_bll;
import bll.Order_bll;
import bll.Product_bll;
import model.Product;
/**
 * Se genereaza rapoartele pdf ale tabelelor dorite.
 * @author Ionut
 *
 */
public class Generator_report {

	int tabel;
	int index=1;
	
	
	public Generator_report(int t,int index)
	{
		tabel = t;
		this.index=index;
	}
	/**
	 * se identifica tabelul care trebuie afisat in pdf
	 * @throws FileNotFoundException eroare
	 * @throws DocumentException eroare
	 * 
	 */
	public void pornire() throws FileNotFoundException, DocumentException
	{
		if(tabel==1)
			generareClient();
		else if(tabel==2)
			generareProduct();
		else if(tabel==3)
			generareOrders();
	}
	/**
	 * Se creeaza documentul pdf pentru clienti. Dupa ce sunt cautati in baza de date, sunt adaugati in fisier.
	 * @throws DocumentException eroare
	 * @throws FileNotFoundException eroare
	 */
	public void generareClient() throws DocumentException, FileNotFoundException
	{
		Document document =new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Report"+index+".pdf"));
		 index++;
		document.open();
		 
		PdfPTable table = new PdfPTable(2);
		addTableHeader(table,"name","address",null,null,2);
		
		Client_bll c =new Client_bll();
		
		ArrayList<model.Client> list=c.findAll();
		
		for(model.Client client:list)
		addRows(table,client.getName(),client.getAddress(),null,null,2);
		
		 
		document.add(table);
		document.close();
	}
	/**
	 * Se creeaza un pdf cu comenzi. Comenzile din baza de date sunt inserate in fisier.
	 * @throws DocumentException eroare
	 * @throws FileNotFoundException eroare
	 */
	public void generareOrders() throws DocumentException, FileNotFoundException
	{
		Document document =new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Report"+index+".pdf"));
		 index++;
		document.open();
		 
		PdfPTable table = new PdfPTable(4);
		addTableHeader(table,"id","name","product","quantity",4);
		
		Order_bll o =new Order_bll();
		
		ArrayList<model.Order> list=o.findAll();
		
		for(model.Order order:list)
		addRows(table,Integer.toString(order.getId()),order.getName(),order.getProduct(),Integer.toString(order.getCantitate()),4);
		
		 
		document.add(table);
		document.close();
	}
	/**
	 * Se creeaza un pdf cu produsele din depozit. Produsele din baza de date sunt transmise in fisierul pdf.
	 * @throws DocumentException eroare
	 * @throws FileNotFoundException eroare
	 */
	public void generareProduct() throws DocumentException, FileNotFoundException
	{
		Document document =new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Report"+index+".pdf"));
		 index++;
		document.open();
		 
		PdfPTable table = new PdfPTable(3);
		addTableHeader(table,"name","quantity","price",null,3);
		
		Product_bll p =new Product_bll();
		
		ArrayList<model.Product> list=p.findAll();
		
		for(model.Product product:list)
		addRows(table,product.getName(),Integer.toString(product.getCantitate()),Double.toString(product.getPret()),null,3);
		
		 
		document.add(table);
		document.close();
	}
	
	private void addTableHeader(PdfPTable table,String s1,String s2,String s3,String s4,int nr_col) {
	   
		if(nr_col==3) {
		Stream.of(s1, s2,s3)
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });}
		if(nr_col==2) {
			Stream.of(s1, s2)
		      .forEach(columnTitle -> {
		        PdfPCell header = new PdfPCell();
		        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        header.setBorderWidth(2);
		        header.setPhrase(new Phrase(columnTitle));
		        table.addCell(header);
		    });}
		if(nr_col==4) {
			Stream.of(s1, s2,s3,s4)
		      .forEach(columnTitle -> {
		        PdfPCell header = new PdfPCell();
		        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        header.setBorderWidth(2);
		        header.setPhrase(new Phrase(columnTitle));
		        table.addCell(header);
		    });}
	}
	
	private void addRows(PdfPTable table,String s1,String s2,String s3,String s4,int nr_cells) {
	   switch (nr_cells)
	   {
	   case 2:table.addCell(s1);
	    	table.addCell(s2);
	    	break;
	   case 3 :table.addCell(s1);table.addCell(s2);table.addCell(s3);break;
	   case 4 :table.addCell(s1);table.addCell(s2);table.addCell(s3);table.addCell(s4);
	   }
	}
	/**
	 * Se genereaza tabelul 4: comenzile anulate. Din baza de date se scot informatiile si se pun in fisierul pdf.
	 * @throws FileNotFoundException eroare
	 * @throws DocumentException eroare
	 */
	public void generateT4() throws FileNotFoundException, DocumentException
	{
		Document document =new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Report_comenzi_anulate.pdf"));
		 index++;
		document.open();
		 
		PdfPTable table = new PdfPTable(2);
		addTableHeader(table,"id","reason",null,null,2);
		
		Canceled_bll p =new Canceled_bll();
		
		ArrayList<model.Canceled_order> list=p.findAll();
		
		for(model.Canceled_order order:list)
		addRows(table,Integer.toString(order.getId()),order.getReason(),null,null,2);
		
		 
		document.add(table);
		document.close();
		
	}
	/**
	 * Se genereaza un pdf cu factura comenzii. Se insereaza un mesaj cu detaliile comenzii si pretul total.
	 * @param client clientul
	 * @param produs produssul
	 * @param cantitate cantitatea
	 * @param pret pretul
	 * @throws FileNotFoundException eroare
	 * @throws DocumentException eroare
	 */
	public void generateBill(String client,String produs,int cantitate,double pret) throws FileNotFoundException, DocumentException
	{
		
		Document document =new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Bill"+index+".pdf"));
		 index++;
		 Product_bll a=new Product_bll();
		 Product p =  a.findProductByName(produs);
		
		 
		document.open();
		Chunk chunk;
		if(p.getCantitate()>=cantitate)
		
			 chunk = new Chunk("Clientul "+client+" a cumparat "+cantitate+" "+produs+" la pretul de "+p.getPret()+" lei. Cost total: "+cantitate*p.getPret());
		else 
			chunk = new Chunk("Clientul "+client+":nu sunt suficiente produse ("+produs+")!" );
		
		document.add(chunk);
		 
		
		document.close();
	}	
}
