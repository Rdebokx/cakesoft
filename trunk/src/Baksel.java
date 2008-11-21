/**
 * Baksel, beschrijft een baksel met zijn eigenschappen e.d. Suggesties voor betere comments zijn welkom op 0900-GOEDIDEE, 10 c.p.o.b.
 * @author Groep 11
 */
public class Baksel {

	int baksel_id;
	String ingredienten;
	String recept;
	String naam;
	String categorie;
	double prijs;

	/**
	 * Constructor voor Baksel zonder parameters. 
	 */
	public Baksel()
	{
		this.baksel_id = -1;
		this.ingredienten = null;
		this.recept = null;
		this.naam = null;
		this.categorie = null;
		this.prijs = -1;
	}

	/**
	 * Constructor voor Baksel met alle parameters.
	 * @param baksel_id
	 * @param ingredienten
	 * @param recept
	 * @param naam
	 * @param categorie
	 * @param prijs
	 */
	public Baksel(int baksel_id, String ingredienten, String recept, String naam, String categorie, double prijs) {
		this.baksel_id = baksel_id;
		this.ingredienten = ingredienten;
		this.recept = recept;
		this.naam = naam;
		this.categorie = categorie;
		this.prijs = prijs;
	}

	/**
	 * Constructor voor Baksel met alle parameters op baksel_id na.
	 * @param ingredienten
	 * @param recept
	 * @param naam
	 * @param categorie
	 * @param prijs
	 */
	public Baksel(String ingredienten, String recept, String naam, String categorie, double prijs)
	{
		this.ingredienten = ingredienten;
		this.recept = recept;
		this.naam = naam;
		this.categorie = categorie;
		this.prijs = prijs;
	}

	/**
	 * @return the baksel_id
	 */
	public int getBaksel_id() {
		return baksel_id;
	}

	/**
	 * @return the ingredienten
	 */
	public String getIngredienten() {
		return ingredienten;
	}

	/**
	 * @return the recept
	 */
	public String getRecept() {
		return recept;
	}

	/**
	 * @return the naam
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @return the prijs
	 */
	public double getPrijs() {
		return prijs;
	}

	/**
	 * @param baksel_id the baksel_id to set
	 */
	public void setBaksel_id(int baksel_id) {
		this.baksel_id = baksel_id;
	}

	/**
	 * @param ingredienten the ingredienten to set
	 */
	public void setIngredienten(String ingredienten) {
		this.ingredienten = ingredienten;
	}

	/**
	 * @param recept the recept to set
	 */
	public void setRecept(String recept) {
		this.recept = recept;
	}

	/**
	 * @param naam the naam to set
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @param prijs the prijs to set
	 */
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
}
