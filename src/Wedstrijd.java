import java.util.Calendar;

/** 
 * Klasse Wedstrijd, beschrijft een Wedstrijd-object met de nodige eigenschappen
 * @author Groep 11
 */

public class Wedstrijd {
	int wedstrijd_id = -1;
	Calendar datum;
	String locatie;
	boolean inschrijvingOpen;
	boolean beoordelingOpen;
	
	/**
	 * Constructor voor Wedstrijd, maakt een nieuwe Wedstrijd aan
	 */
	public Wedstrijd()
	{
		new Wedstrijd();
	}
	
	/**
	 * Constructor voor Wedstrijd, maakt een nieuwe Wedstrijd aan met gegeven id, datum, locatie, inschrijvingOpen en beoordelingOpen
	 */
	public Wedstrijd(int wedstrijd_id, Calendar datum, String locatie, boolean inschrijvingOpen, boolean beoordelingOpen)
	{
		
	}
	
	/**
	 * Constructor voor Wedstrijd, maakt een nieuwe Wedstrijd aan met gegeven datum, locatie, inschrijvingOpen en beoordelingOpen
	 */
	public Wedstrijd(Calendar datum, String locatie, boolean inschrijvingOpen, boolean beoordelingOpen)
	{
		
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
	 * @param datum		In te stellen 
	 */
	public void setDatum(Calendar datum)
	{
		this.datum = datum;
	}
	Post: datum is ingesteld

	public void setLocatie(String locatie)
	Post: heeft de locatie ingesteld

	public void setInschrijvingOpen(boolean inschrijvingOpen)
	Post: heeft inschrijvingOpen ingesteld op true of false

	public void setBeoordelingOpen(boolean beoordelingOpen)
	Post: heeft beoordelingOpen ingesteld

	public int getWedstrijd_id()
	Post: geeft de wedstrijd_id terug

	public Calendar getDatum()
	Post: geeft de datum van de wedstrijd terug

}