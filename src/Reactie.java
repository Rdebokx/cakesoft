/**
 * Klasse Reactie, beschrijft een Reactie-object met eigenschappen.
 * @author Groep 11
 */
public class Reactie {
	private int reactie_id;
	private int lid_id;
	private String bericht;
	private Lid schrijver;
	
	public Reactie()
	{
		this.reactie_id = -1;
		this.bericht = null;
	}

	/**
	 * Constructor voor Reactie met parameters.
	 * @param reactie_id
	 * @param bericht
	 */
	public Reactie(int reactie_id, int lid_id, String bericht) {
		this.reactie_id = reactie_id;
		this.bericht = bericht;
		this.lid_id = lid_id;
	}

	/**
	 * Constructor voor Reactie zonder parameter reactie_id.
	 * @param bericht
	 */
	public Reactie(String bericht)
	{
		this.reactie_id = -1;
		this.bericht = bericht;
	}

	/**
	 * @return the reactie_id
	 */
	public int getReactie_id() {
		return reactie_id;
	}

	/**
	 * @return the bericht
	 */
	public String getBericht() {
		return bericht;
	}

	/**
	 * 
	 * @return de lid_id
	 */
	public int getLid_id()
	{
		return lid_id;
	}
	
	public Lid getSchrijver()
	{
		return this.schrijver;
	}
	
	/**
	 * @param reactie_id the reactie_id to set
	 */
	public void setReactie_id(int reactie_id) {
		this.reactie_id = reactie_id;
	}

	/**
	 * @param bericht the bericht to set
	 */
	public void setBericht(String bericht) {
		this.bericht = bericht;
	}
	
	/**
	 * Deze set het Lid_id
	 * @param lid_id 	bevat het id van het lid
	 */
	public void setLid_id(int lid_id){
		this.lid_id = lid_id;
	}
	
	public void setSchrijver(Lid schrijver)
	{
		this.schrijver=schrijver;
	}
}
