package model;
/**
 * Model pentru comenzi anulate.
 * @author Ionut
 *
 */
public class Canceled_order {
private int id;
private String reason;

public Canceled_order(int i,String r)
{
	id=i;
	reason=r;
}
/** 
 * se returneaza id-ul comenzii
 * @return id-ul comenzii anulate
 */
public int getId()
{
	return id;
}
/** 
 * se returneaza motivul anularii comenzii
 * @return motivul anularii comenzii
 */
public String getReason()
{
	return reason;
}
	
}
