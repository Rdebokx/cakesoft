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
	 * @param naam				String
	 * @param lid_id			int
	 * @param wachtwoord		String
	 * @param hoofdbeheer	boolean
	 */
	public Lid(String naam, int lid_id, String wachtwoord, boolean hoofdbeheer)
	{
		this.naam = naam;
		this.lid_id = lid_id;
		this.wachtwoord = wachtwoord;
		this.hoofdbeheer = hoofdbeheer;
	}
	
	/**
	 * Methode setNaam, stelt naam in
	 * @param naam		String
	 */
	public void setNaam(String naam)
	{
		this.naam = naam;
	}
	
	/**
	 * Methode setLid_id, stelt lid_id in.
	 * @param lid_id
	 */
	public void setLid_id(int lid_id)
	{
		this.lid_id = lid_id;
	}
	
	/**
	 * Methode setWachtwoord, stelt wachtwoord in.
	 * @param wachtwoord	String
	 */
	public void setWachtwoord(String wachtwoord)
	{
		this.wachtwoord = wachtwoord;
	}
	
	/**
	 * Methode setHoofdbeheer, stelt hoofdbeheer in.
	 * @param hoofdbeheer	boolean
	 */
	public void setHoofdbeheer(boolean hoofdbeheer)
	{
		this.hoofdbeheer = hoofdbeheer;
	}
	
	/**
	 * Methode getNaam, geeft naam terug
	 * @return		String
	 */
	public String getNaam()
	{
		return naam;
	}
	
	/**
	 * Methode getLid_id, geeft lid_id terug.
	 * @return	int
	 */
	public int getLid_id()
	{
		return lid_id;
	}
	
	/**
	 * Methode getWachtwoord, geeft wachtwoord terug.
	 * @return	String
	 */
	public String getWachtwoord()
	{
		return wachtwoord;
	}
	
	/**
	 * Methode isHoofdbeheer, geeft boolean waarde terug.
	 * @return boolean
	 */
	public boolean isHoofdbeheer()
	{
		return hoofdbeheer;
	}
	
	/**
	 * Methode equals, geeft true desda object is van klasse Lid en heeft gelijk lid_id
	 * @return	boolean
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
