import java.awt.event.*;

public class ProgrammaController
{
	private Lid ingelogdLid=null;
	
	private Scherm_Login loginS;
	private Scherm_foutmelding foutS;
	private Scherm_Overzicht overzichtS;
	
	private beheerLid bLid;
	private Database db;
	
	public ProgrammaController()
	{
		this.db=new Database("","","");
		this.bLid=new beheerLid(this.db);
		
		this.loginS=new Scherm_Login(this);
	}
	
	public void openOverzicht()
	{
		this.overzichtS=new Scherm_overzicht(this);
		//set gegevens als wedstrijden, bestellingen
	}
	
	public void actieLogin()
	{
		Lid ingevuldLid=this.loginS.getLid();
		
		Lid ingelogdLid=this.bLid.getLidDoorLogin(ingevuldLid);
		if(ingelogdLid==null)
		{
			//foutieve login
			
			this.foutS=new Scherm_foutmelding(this,"U hebt een verkeerd lid id of wachtwoord ingevoerd.");
		}
		else
		{
			//succesvolle login
			
			this.ingelogdLid=ingelogdLid;
			
			this.loginS.dispose();
			this.openOverzicht();
		}
		
	}
	
	public void actieLoguit()
	{
		this.ingelogdLid=null;
		
		this.overzichtS.dispose();
		this.loginS=new Scherm_Login(this);
	}
}