/**
 * Klasse Reactie, beschrijft een Reactie-object met eigenschappen.
 * @author Groep 11
 */
public class Reactie {
	int reactie_id;
	String bericht;

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
	public Reactie(int reactie_id, String bericht) {
		this.reactie_id = reactie_id;
		this.bericht = bericht;
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
}
