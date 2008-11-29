/** 
 * Klasse Deelnemer, uitbreiding op Lid, beschrijft een Deelnemer aan Wedstrijd.
 * @author Groep 11
 */
public class Deelnemer extends Lid {
	private int deelnemer_id;
	private Baksel baksel;
	private int plaats;
	
	
	/**
	 * Constructor voor Deelnemer, maakt een nieuw Deelnemer object aan, zonder parameters.
	 */
	public Deelnemer()
	{
		this.deelnemer_id = -1;
		this.baksel = null;
		this.plaats = -1;
	}
	
	/**
	 * Constructor voor Deelnemer, maakt een nieuw Deelnemer object aan, met alle parameters.
	 */
	public Deelnemer(int deelnemer_id, String naam, int lid_id, String wachtwoord, boolean hoofdbeheer)
	{
		super(naam, lid_id, wachtwoord, hoofdbeheer);
		this.deelnemer_id = deelnemer_id;
		this.baksel = null;
		this.plaats = -1;
	} 
		
	/**
	 * Constructor voor Deelnemer, maakt een nieuw Deelnemer object aan, Lid als parameter.
	 */
	public Deelnemer(Lid lid)
	{
		super(lid.getNaam(), lid.getLid_id(), lid.getWachtwoord(), lid.isHoofdbeheer());
	}
		
	/**
	 * Methode equals, checkt of twee objecten van type Deelnemer zijn en hetzelfde ID hebben.
	 */
	public boolean equals(Object other)
	{
		boolean output = false;
		if(other instanceof Deelnemer)
		{
			Deelnemer deelnemer = (Deelnemer)other;
			if (this.deelnemer_id == deelnemer.getDeelnemer_id())
			{
				output = true;
			}
		}
		return output;
	}

	/**
	 * @return deelnemer_id
	 */
	public int getDeelnemer_id() {
		return deelnemer_id;
	}

	/**
	 * @param deelnemer_id deelnemer_id to set
	 */
	public void setDeelnemer_id(int deelnemer_id) {
		this.deelnemer_id = deelnemer_id;
	}

	/**
	 * @return baksel
	 */
	public Baksel getBaksel() {
		return baksel;
	}

	/**
	 * @param baksel baksel to set
	 */
	public void setBaksel(Baksel baksel) {
		this.baksel = baksel;
	}

	/**
	 * @return plaats
	 */
	public int getPlaats() {
		return plaats;
	}

	/**
	 * @param plaats Om in te stellen
	 */
	public void setPlaats(int plaats) {
		this.plaats = plaats;
	}
	
	public String toString()
	{
		return this.getNaam();
	}
}

