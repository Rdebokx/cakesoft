/** 
 * Klasse Beoordeling, bevat de beoordeling welke bij een Baksel hoort.
 * @author Groep 11
 */

public class Beoordeling {
	int beoordeling_id;
	String commentaar;
	int kwaliteit;
	int prijs;
	int calo;
	int smaak;
	Jury jury;
	
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
	public Beoordeling(int beoordeling_id, String commentaar, int kwaliteit, int prijs, int calo, int smaak)
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
	public Beoordeling(String commentaar, int kwaliteit, int prijs, int calo, int smaak)
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
	//post;  heeft het commentaar ingesteld 
	
	public void setKwaliteit(int kwaliteit)
	{
		this.kwaliteit = kwaliteit;
	}
	//post;  heeft de kwaliteit ingesteld 
	
	public void setPrijs(int prijs)
	{
		this.prijs = prijs;
	}
	//post;  heeft de prijs ingesteld 
	
	public void setCalo(int calo)
	{
		this.calo = calo;
	}
	//post;  heeft de calorieën ingesteld 
	
	public void setSmaak(int smaak)
	{
		this.smaak = smaak;
	}
	//post;  heeft de smaak ingesteld 
	
	public int getBeoordeling_id()
	{
		return beoordeling_id;
	}
	//post;  geeft de beoordeling_id terug
	
	public String getCommentaar()
	{
		return commentaar;
	}
	//post;  geeft het commentaar terug
	
	public int getKwaliteit()
	{
		return kwaliteit;
	}
	//post;  geeft kwaliteit terug
	
	public int getPrijs()
	{
		return prijs;
	}
	//post;  geeft prijs terug
	
	public int getCalo()
	{
		return calo;
	}
	//post;  geeft calorieen terug
	
	public int getSmaak()
	{
		return smaak;
	}
	//post;  geeft smaak terug
	
	public void setJury(Jury jury)
	{
		this.jury = jury;
	}
	//post;  heeft jury ingesteld
	
	public Jury getJury()
	{
		return jury;
	}
	//post;  geeft jury object terug
}
