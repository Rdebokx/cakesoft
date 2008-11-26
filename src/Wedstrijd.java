import java.text.SimpleDateFormat;

/** 
 * Klasse Wedstrijd, beschrijft een Wedstrijd-object met de nodige eigenschappen
 * @author Groep 11
 */

public class Wedstrijd {
	int wedstrijd_id;
	SimpleDateFormat datum;
	String locatie;
	boolean inschrijvingOpen;
	boolean beoordelingOpen;
	
	/**
	 * Constructor voor Wedstrijd, maakt een nieuwe Wedstrijd aan
	 */
	public Wedstrijd()
	{
		wedstrijd_id = -1;
		locatie = "";
	}
	
	/**
	 * Constructor voor Wedstrijd, maakt een nieuwe Wedstrijd aan met gegeven id, datum, locatie, inschrijvingOpen en beoordelingOpen
	 */
	public Wedstrijd(int wedstrijd_id, java.text.SimpleDateFormat datum, String locatie, boolean inschrijvingOpen, boolean beoordelingOpen)
	{
		this.wedstrijd_id = wedstrijd_id;
		this.datum = datum;
		this.locatie = locatie;
		this.inschrijvingOpen = inschrijvingOpen;
		this.beoordelingOpen = beoordelingOpen;
	}

	/**
	 * Constructor voor Wedstrijd, maakt een nieuwe Wedstrijd aan met gegeven datum, locatie, inschrijvingOpen en beoordelingOpen
	 * @param datum				SimpleDateFormat
	 * @param locatie			String
	 * @param inschrijvingOpen	boolean
	 * @param beoordelingOpen	boolean		
	 */
	public Wedstrijd(SimpleDateFormat datum, String locatie, boolean inschrijvingOpen, boolean beoordelingOpen)
	{
		this.wedstrijd_id = -1;
		this.datum = datum;
		this.locatie = locatie;
		this.inschrijvingOpen = inschrijvingOpen;
		this.beoordelingOpen = beoordelingOpen;
	}

	/**
	 * Methode setWedstrijd_id, stelt het gegeven id in voor de wedstrijd.
	 * @param wedstrijd_id	Het in te stellen wedstrijd_id.
	 * @return				Wedstrijd_id is ingesteld.
	 */
	public void setWedstrijd_id(int wedstrijd_id)
	{
		this.wedstrijd_id = wedstrijd_id;
	}
	
	/**
	 * Methode setDatum, stelt datum in op het gegeven datum-object
	 * @param datum		In te stellen datum-object
	 */
	public void setDatum(SimpleDateFormat datum)
	{
		this.datum = datum;
	}
	
	/**
	 * Methode setLocatie, stelt de locatie in op de gegeven String.
	 * @param locatie	String met de locatie, puur voor eindgebruikers.
	 * @return			Locatie is ingesteld voor de Wedstrijd.
	 */
	public void setLocatie(String locatie)
	{
		this.locatie = locatie;
	}

	public void setInschrijvingOpen(boolean inschrijvingOpen)
	{
		this.inschrijvingOpen = inschrijvingOpen;
	}

	/**
	 * Methode setBeoordelingOpen, stelt beoordelingOpen in op de gegeven boolean waarde.
	 * @param beoordelingOpen	Boolean
	 * @return					beoordelingOpen is ingesteld.
	 */
	public void setBeoordelingOpen(boolean beoordelingOpen)
	{
		this.beoordelingOpen = beoordelingOpen;
	}
	
	/**
	 * Methode getWedstrijd_id, geeft wedstrijd_id terug.
	 * @return					Integer wedstrijd_id
	 */
	public int getWedstrijd_id()
	{
		return wedstrijd_id;
	}
	
	/**
	 * Methode getDatum, geeft de ingestelde datum terug. 
	 * @return		Datum datum, geeft het datum object terug.
	 */
	public SimpleDateFormat getDatum()
	{
		return datum;
	}
	
	/**
	 * Methode getLocatie, geeft locatie terug.
	 * @return		String getLocatie
	 */
	public String getLocatie()
	{
		return locatie;
	}

	/**
	 * Methode isInschrijvingOpen, geeft inschrijvingOpen terug.
	 * @return		Boolean inschrijvingOpen
	 */
	public boolean isInschrijvingOpen()
	{
		return inschrijvingOpen;
	}
	
	/**
	 * Methode isBeoordelingOpen, geeft beoordelingOpen terug.
	 * @return		Boolean beoordelingOpen
	 */
	public boolean isBeoordelingOpen()
	{
		return beoordelingOpen;
	} 
	
	/**
	 * Methode toString, geeft String-representatie terug. 
	 * @return	String, object in vorm 'locatie, YYYY-MM-DD'
	 **/
	public String toString()
	{
		return this.getLocatie() + ", " + datum.format("YYYY-MM-DD");
	}

	/**
	 * Methode equals, controleert of het andere object van het type Wedstrijd is en hetzelfde ID heeft
	 * @param	other		Het te controleren Other object.
	 * @return	boolean		True desda Wedstrijd_id's zijn gelijk en beide van type Wedstrijd. 
	 */
	public boolean equals(Object other)
	{
		boolean output = false;
		Wedstrijd wedstrijd = (Wedstrijd)other;
		
		if (other instanceof Wedstrijd)
		{
			if (wedstrijd.getWedstrijd_id() == this.getWedstrijd_id())
			{
				output = true;
			}
		}
		return output;
	}
}