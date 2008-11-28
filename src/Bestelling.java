/**
 * Klasse Bestelling, beschrijft een Bestelling van een Baksel door een Lid.
 * @author Groep 11
 */
public class Bestelling {

	private int bestelling_id;
	private int aantal;
	private Baksel baksel;
	private Lid lid;

	/**
	 * Constructor voor Bestelling, geen parameters
	 */
	public Bestelling() {
		this.aantal = -1;
		this.bestelling_id = -1;
	}

	/**
	 * Constructor voor Bestelling, parameter aantal
	 * @param aantal
	 */
	public Bestelling(int aantal) {
		this.aantal = aantal;
		this.bestelling_id = -1;
	}

	/**
	 * Constructor voor Bestelling, parameter aantal en bestelling_id
	 * @param aantal
	 * @param bestelling_id
	 */
	public Bestelling(int aantal, int bestelling_id) {
		this.aantal = aantal;
		this.bestelling_id = bestelling_id;
	}
	
	/**
	 * Constructor voor Bestelling, parameter aantal, bestelling_id en baksel
	 * @param aantal
	 * @param bestelling_id
	 * @param baksel
	 */
	public Bestelling(int aantal, int bestelling_id, Baksel baksel) {
		this.aantal = aantal;
		this.bestelling_id = bestelling_id;
		this.baksel = baksel;
	}
	
	/**
	 * Constructor voor Bestelling, parameter aantal, bestelling_id, baksel en lid
	 * @param aantal
	 * @param bestelling_id
	 * @param baksel
	 * @param lid Bevat het lid dat de bestelling geplaatst heeft
	 */
	public Bestelling(int aantal, int bestelling_id, Baksel baksel, Lid lid) {
		this.aantal = aantal;
		this.bestelling_id = bestelling_id;
		this.baksel = baksel;
		this.lid = lid;
	}

	/**
	 * @return the bestelling_id
	 */
	public int getBestelling_id() {
		return bestelling_id;
	}

	/**
	 * @return the aantal
	 */
	public int getAantal() {
		return aantal;
	}

	/**
	 * @return the baksel
	 */
	public Baksel getBaksel() {
		return baksel;
	}
	
	/**
	 * @return the lid
	 */
	public Lid getLid() {
		return lid;
	}

	/**
	 * @param bestelling_id the bestelling_id to set
	 */
	public void setBestelling_id(int bestelling_id) {
		this.bestelling_id = bestelling_id;
	}

	/**
	 * @param aantal the aantal to set
	 */
	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	/**
	 * @param baksel the baksel to set
	 */
	public void setBaksel(Baksel baksel) {
		this.baksel = baksel;
	}
}