/** 
 * Klasse Jury, beschrijft een Jurylid.
 * @author Groep 11
 */

public class Jury extends Lid {
	private int jury_id;
	
	/**
	 * Constructor voor Jury zonder parameters.
	 */
	public Jury()
	{
		super();
		jury_id = -1; 
	}

	/**
	 * Constructor voor Jury met alle parameters; jury_id, naam, lid_id, wachtwoord en hoofdbeheerder.
	 * @param jury_id			int
	 * @param naam				String
	 * @param lid_id			int
	 * @param wachtwoord		String
	 * @param hoofdbeheerder	boolean
	 */
	public Jury(int jury_id, String naam, int lid_id, String wachtwoord, boolean hoofdbeheerder)
	{
		super(naam, lid_id, wachtwoord, hoofdbeheerder);
		this.jury_id = jury_id;
	}

	/**
	 * Constructor voor Jury met Lid-object als parameter.
	 * @param lid
	 */
	public Jury(Lid lid)
	{
		//TODO: Kan iemand dit valideren, Leendert bijvoorbeeld?
		super(lid.getNaam(), lid.getLid_id(), lid.getWachtwoord(), lid.isHoofdbeheerder());
		jury_id = -1;
	}

	/**
	 * Methode setJury_id, stelt het gegeven id in.
	 * @param jury_id	intger
	 */
	public void setJury_id(int jury_id)
	{
		this.jury_id = jury_id;
	}

	/**
	 * Methode getJury_id, geeft jury_id terug.
	 * @return	integer jury_id.
	 */
	public int getJury_id()
	{
		return jury_id;
	}

	/**
	 * Methode equals, controleert of twee objecten van hetzelfde type zijn en gelijke ids hebben.
	 * @return		boolean, true desda type en ID gelijk.
	 */
	public boolean equals(Object other)
	{
		boolean output = false;
		if(other instanceof Jury)
		{
			Jury juryLid = (Jury)other;
			if (this.jury_id == juryLid.getJury_id())
			{
				output = true;
			}
		}
		return output;
	}
	
}
