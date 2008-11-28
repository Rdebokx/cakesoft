/**
 * queryVoorwaarde, bevat de voorwaarden van een query aan de database.
 * @author Groep 11
 */
public class queryVoorwaarde
{
	private Object left;
	private Object right;
	private String operator;
	
	/**
	 * Maakt een voorwaarde aan voor een query, bevat een Kolomnaam, operator en waarde.
	 * @param left		Kolomnaam van de vergelijking.
	 * @param operator	Operator, opties zijn o.a. query.LIKE, query.KLEINER_DAN en query.GELIJK
	 * @param right		De te vergelijken waarde.
	 */
	public queryVoorwaarde(Object left, String operator, Object right)
	{
		this.left=left;
		this.right=right;
		this.operator=operator;
	}
	
	/**
	 * Methode toString, geeft een String-representatie terug van de voorwaarde in de vorm 'kolomnaam, operator, onderdeel'
	 */
	public String toString()
	{
		try
		{
			if(this.operator==query.EN || this.operator==query.OF)
				return "("+((queryVoorwaarde)this.left)+" " + (this.operator.equals(query.EN)?"AND":"OR") + " "+((queryVoorwaarde)this.right)+")";
			else
				return ((String)this.left) + this.operator + query.objectToString(this.right);
		}
		catch(Exception e)
		{
			return "";
		}
	}
}
