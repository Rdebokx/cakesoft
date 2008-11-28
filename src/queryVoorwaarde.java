
public class queryVoorwaarde
{
	private Object left;
	private Object right;
	private String operator;
	
	public queryVoorwaarde(Object left, String operator, Object right)
	{
		this.left=left;
		this.right=right;
		this.operator=operator;
	}
	
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
