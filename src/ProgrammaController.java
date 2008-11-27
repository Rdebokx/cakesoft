import java.awt.event.*;

public class ProgrammaController
{
	private Lid ingelogdLid=null;
	
	private Scherm_Login loginS;
	private Scherm_foutmelding foutS;
	private Scherm_Hoofdscherm hoofdschermS;
	
	private beheerLid bLid;
	private beheerWedstrijd bWedstrijd;
	private Database db;
	
	public ProgrammaController()
	{
		this.db=new Database("","","");
		this.bLid=new beheerLid(this.db);
		this.bWedstrijd=new beheerWedstrijd(this.db);
		
		
		this.loginS=new Scherm_Login(this);
	}
	
	public void openOverzicht()
	{
		this.hoofdschermS=new Scherm_Hoofdscherm(this);
		//set gegevens als wedstrijden, bestellingen
	}


//
// Acties voor Scherm_login
//	
	
	public void actieLogin()
	{
		Lid ingevuldLid=this.loginS.getLid();
		
		Lid ingelogdLid=this.bLid.getLidDoorLogin(ingevuldLid);
		if(ingelogdLid==null)
		{
			//foutieve login
			
			this.foutS=new Scherm_foutmelding("U hebt een verkeerd lid id of wachtwoord ingevoerd.");
		}
		else
		{
			//succesvolle login
			
			this.ingelogdLid=ingelogdLid;
			
			this.loginS.dispose();
			this.openOverzicht();
		}
		
	}
	

//
//	 Acties voor Scherm_Hoofdscherm
//		
	
	public void actieBekijkWedstrijd()
	{
		
	}

	public void actieNieuwWedstrijd()
	{
		
	}
	
	public void actieVerwijderBestelling()
	{
		
	}

	public void actieLoguit()
	{
		this.ingelogdLid=null;
		
		this.hoofdschermS.dispose();
		this.loginS=new Scherm_Login(this);
	}

//
//	 Acties voor Scherm_Wedstrijd(Klaar)
//		
	
	public void actieBekijkDeelnemer()
	{
		
	}
	
	public void actieInschrijven()
	{
		
	}
	
	
//
//	Acties voor Scherm_Inscrijven
//
	
	public void actieInschrijvingVerzenden()
	{
		
	}

//
//	Repeterende Acties
//
	public void actieTerug()
	{
		
	}
}
