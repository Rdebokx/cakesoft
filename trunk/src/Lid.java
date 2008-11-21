/** 
 * Klasse Lid, beschrijft een Lid.
 * @author Groep 11
 */

public class Lid {
	String naam;
	int lid_id;
	String wachtwoord;
	boolean hoofdbeheerder;
	
	/**
	 * Constructor voor Lid, stelt waarden in op -1, false en null zover mogelijk.
	 */
	public Lid(){
		this.naam = "";
		this.lid_id = -1;
		this.wachtwoord = null;
		this.hoofdbeheerder = false;
	}
	
	
	public Lid(String naam, int lid_id, String wachtwoord, boolean hoofdbeheerder)
	{
		this.naam = naam;
		this.lid_id = lid_id;
		this.wachtwoord = wachtwoord;
		this.hoofdbeheerder = hoofdbeheerder;
	}
	
	public void setNaam(String naam)
	{
		this.naam = naam;
	}
	//post: heeft naam ingesteld
	
	public void setLid_id(int lid_id)
	{
		this.lid_id = lid_id;
	}
	//post: heeft lid_id ingesteld
	
	public void setWachtwoord(String wachtwoord)
	{
		this.wachtwoord = wachtwoord;
	}
	//post: heeft wachtwoord ingesteld
	
	public void setHoofdbeheerder(boolean hoofdbeheerder)
	{
		this.hoofdbeheerder = hoofdbeheerder;
	}
	//post: heeft hoofdbeheerder ingesteld op true of false
	
	public String getNaam()
	{
		return naam;
	}
	//post: geeft naam terug
	
	public int getLid_id()
	{
		return lid_id;
	}
	//post: geeft lid_id terug
	
	public String getWachtwoord()
	{
		return wachtwoord;
	}
	////post: geeft wachtwoord teruyg
	
	public boolean isHoofdbeheerder()
	{
		return hoofdbeheerder;
	}
	////post: geeft true dsd hoofdbeheerder true is
	
	public boolean equals(Object other)
	{
		boolean output = false;
		if(other instanceof Lid)
		{
			Lid lid = (Lid)other;
			if (this.lid_id == lid.getLid_id())
			{
				output = true;
			}
		}
		return output;
	}
}
