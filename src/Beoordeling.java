/** 
 * Klasse Beoordeling, bevat de beoordeling welke bij een Baksel hoort.
 * @author Groep 11
 */

public class Beoordeling {
	private int beoordeling_id;
	private String commentaar;
	private int kwaliteit;
	private double prijs;
	private int calo;
	private int smaak;
	private Jury jury;
	
	/**
	 * Constructor voor Beoordeling, geen parameters.
	 */
	public Beoordeling()
	{
		this.beoordeling_id = -1;
		this.calo = -1;
		this.commentaar = null;
		this.kwaliteit = -1;
		this.prijs = -1;
		this.smaak = -1;
		//TODO: Wat doen met Jury?
		this.jury = null;
	}
	
	/**
	 * Constructor voor Beoordeling, alle parameters.
	 * @param beoordeling_id	
	 * @param commentaar
	 * @param kwaliteit
	 * @param prijs
	 * @param calo
	 * @param smaak
	 */
	public Beoordeling(int beoordeling_id, String commentaar, int kwaliteit, double prijs, int calo, int smaak)
	{
		this.beoordeling_id = beoordeling_id;
		this.calo = calo;
		this.commentaar = commentaar;
		this.kwaliteit = kwaliteit;
		this.prijs = prijs;
		this.smaak = smaak;
	}
	
	/**
	 * Constructor voor Beoordelng, alle parameters op beoordeling_id na.
	 * @param commentaar
	 * @param kwaliteit
	 * @param prijs
	 * @param calo
	 * @param smaak
	 */
	public Beoordeling(String commentaar, int kwaliteit, double prijs, int calo, int smaak)
	{
		this.calo = calo;
		this.commentaar = commentaar;
		this.kwaliteit = kwaliteit;
		this.prijs = prijs;
		this.smaak = smaak;
	}

	/*
	 * Get & Set Methoden, commentaar is redelijk overbodig.
	 */

	public void setBeoordeling_id(int beoordeling_id)
	{
		this.beoordeling_id = beoordeling_id;
	}
	
	public void setCommentaar(String commentaar)
	{
		this.commentaar = commentaar;
	}
	
	public void setKwaliteit(int kwaliteit)
	{
		this.kwaliteit = kwaliteit;
	}
	
	public void setPrijs(double prijs)
	{
		this.prijs = prijs;
	}
	
	public void setCalo(int calo)
	{
		this.calo = calo;
	}
	
	public void setSmaak(int smaak)
	{
		this.smaak = smaak;
	}
	
	public int getBeoordeling_id()
	{
		return beoordeling_id;
	}
	
	public String getCommentaar()
	{
		return commentaar;
	}
	
	public int getKwaliteit()
	{
		return kwaliteit;
	}
	
	public double getPrijs()
	{
		return prijs;
	}
	
	public int getCalo()
	{
		return calo;
	}
	
	public int getSmaak()
	{
		return smaak;
	}
	
	public void setJury(Jury jury)
	{
		this.jury = jury;
	}
	
	public Jury getJury()
	{
		return jury;
	}
}
