package start;

import model.Client;
/**
 * In aceasta clasa se decodifica instructiunea din linia fisierului de intrare: se extrag datele clientului, produsului sau a comenzii.
 * @author Ionut
 *
 */
public class Instructiune {

	String nume_c;
	String nume_p;
	String adresa;
	int cantitate;
	int cod_instructiune;
	int cod_tabel;
	double pret;
	
	public Instructiune() {};
	/**
	 * Fiecare cuvant este identificat cu o instructiune, un tabel sau o informatie despre obiecte.
	 * @param words o linie din fisierul de intrare
	 */
	public void find(String[] words)
	{		
		cantitate=0;
		cod_instructiune=0;
		cod_tabel=0;
		pret=0;
		
		 for (String word : words)
	        {	if(word.contentEquals("Insert"))
	        		cod_instructiune=1;
	        if(word.contentEquals("Delete"))
        		cod_instructiune=2;
	      
	        if(word.contentEquals("client:") || word.contentEquals("client"))
	        	cod_tabel=1;
	      
	        if(word.contentEquals("product:") || word.contentEquals("product"))
     		cod_tabel=2;
	       
	        if(word.contentEquals("Order:") ||word.contentEquals("order"))
	     		cod_tabel=3;
	        
	        if(word.contentEquals("Report"))
	     		cod_instructiune = -1;
	        	
	  
	        }
		
		 if(cod_tabel==1 && cod_instructiune!=-1)
		 {
			 String[] a=getNume_1(words);
			 nume_c=a[0];
			 adresa=a[1];
		 }
		 
		 if(cod_tabel==2 && cod_instructiune!=-1)
		 {
			 String[] a=getNume_2(words);
			 nume_p=a[0];
			 cantitate=Integer.parseInt(a[1]);
			 pret = Double.parseDouble(a[2]);
		 }
		 
		 if(cod_tabel==3 && cod_instructiune!=-1)
		 {
			 String[] a=getNume_3(words);
			 nume_c=a[0];
			 nume_p=a[1];
			 cantitate = Integer.parseInt(a[2]);
		 }
		 
	}
	/**
	 * Se extrag detaliile despre client.
	 * @param words linia din fisierul de intrare
	 * @return numele si adresa clientului
	 */
	public String[] getNume_1(String[] words)
	{
		String[] rez=new String[4];
		rez[0]=words[2]+" "+words[3].substring(0,words[3].length()-1);
		rez[1]=words[4];
		return rez;
	}
	/**
	 * Se extrag detaliile despre produs.
	 * @param words o linie din fisierul de intrare
	 * @return numele, cantitatea si pretul produsului
	 */
	public String[] getNume_2(String[] words)
	{String[] rez=new String[4];
		if(cod_instructiune==1)
		{
		
		rez[0]=words[2].substring(0,words[2].indexOf(','));
		rez[1]=words[3].substring(0,words[3].length()-1);
		rez[2]=words[4];
		
		}
		else {
			rez[0]=words[2];
			rez[1]="-1";
			rez[2]="-1";
		}
			
			return rez;
	}
	/**
	 * Se extrag detaliile despre comanda.
	 * @param words o linie din fisierul de intrare
	 * @return numele clientului, produsul comandat si cantitatea
	 */
	public String[] getNume_3(String[] words)
	{
		String[] rez=new String[4];
		rez[0]=words[1]+" "+words[2].substring(0,words[2].length()-1);
		rez[1]=words[3].substring(0,words[3].length()-1);
		rez[2]=words[4];
		return rez;
	}
	
	public int getTab()
	{
		return cod_tabel;
	}
	public int getInstr()
	{
		return cod_instructiune;
	}
	public int getCant()
	{
		return cantitate;
	}
	public double getPret()
	{
		return pret;
	}
	public String getNumeC()
	{
		return nume_c;
	}
	public String getNumep()
	{
		return nume_p;
	}
	public String getAdr()
	{
		return adresa;
	}
	public String toString()
	{
		return "cod_instr:"+ cod_instructiune+" cod_tabel:"+cod_tabel+" nume_client:"+nume_c+" adresa:"+adresa+" nume_produs:"+nume_p+" cantitate:"+cantitate+" pret:"+pret;
	}
	
}
