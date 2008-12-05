/** 
 * Klasse Lid, beschrijft een Lid.
 * @author Groep 11
 */

public class Lid {
	private String naam;
	private int lid_id;
	private String wachtwoord;
	private boolean hoofdbeheer;
	
	/**
	 * Constructor voor Lid, stelt waarden in op -1, false en null zover mogelijk.
	 */
	public Lid(){
		this.naam = "";
		this.lid_id = -1;
		this.wachtwoord = null;
		this.hoofdbeheer = false;
	}
	
	/**
	 * Constructor voor Lid, gegeven alle eigenschappen van het lid.
	 * @param naam				De naam van het Lid
	 * @param lid_id			Het lid_id van het lid
	 * @param wachtwoord		Het wachtwoord, behorende bij dit lid.
	 * @param hoofdbeheer		boolean die aangeeft of dit lid hoofdbeheerder is of niet
	 */
	public Lid(String naam, int lid_id, String wachtwoord, boolean hoofdbeheer)
	{
		this.naam = naam;
		this.lid_id = lid_id;
		this.wachtwoord = wachtwoord;
		this.hoofdbeheer = hoofdbeheer;
	}
	
	/**
	 * Methode setNaam stelt de naam in van het lid
	 * @param naam		Bevat de naam van het lid
	 */
	public void setNaam(String naam)
	{
		this.naam = naam;
	}
	
	/**
	 * Methode setLid_id stelt lid_id in.
	 * @param lid_id	bevat het "nieuwe" lid_id
	 */
	public void setLid_id(int lid_id)
	{
		this.lid_id = lid_id;
	}
	
	/**
	 * Methode setWachtwoord stelt het wachtwoord in.
	 * @param wachtwoord	Bevat het nieuwe wachtwoord
	 */
	public void setWachtwoord(String wachtwoord)
	{
		this.wachtwoord = wachtwoord;
	}
	
	/**
	 * Methode setHoofdbeheer, stelt in of iemand het hoofdbeheer heeft of niet.
	 * @param hoofdbeheer	true als dit lid de hoofdbeheerder is, anders false
	 */
	public void setHoofdbeheer(boolean hoofdbeheer)
	{
		this.hoofdbeheer = hoofdbeheer;
	}
	
	/**
	 * Methode getNaam geeft de naam van het lid terug.
	 * @return		Bevat de naam van het lid
	 */
	public String getNaam()
	{
		return naam;
	}
	
	/**
	 * Methode getLid_id geeft het lid_id terug.
	 * @return	geeft het lid_id terug, als int
	 */
	public int getLid_id()
	{
		return lid_id;
	}
	
	/**
	 * Methode getWachtwoord, geeft het wachtwoord terug.
	 * @return	Bevat het wachtwoord, als String
	 */
	public String getWachtwoord()
	{
		return wachtwoord;
	}
	
	/**
	 * Methode isHoofdbeheer, geeft true terug als dit lid de hoofdbeheerder is, andes false.
	 * @return	geeft true terug als dit lid de hoofdbeheerder is.
	 */
	public boolean isHoofdbeheer()
	{
		return hoofdbeheer;
	}
	
	/**
	 * Methode equals, geeft true desda het opgegeven object ook van het type Lid is
	 *  en het zelfde lid_id heeft.
	 * @return	geeft true als het opgegeven object hetzelfde lid bevat.
	 */
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
