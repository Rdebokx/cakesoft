import java.util.*;

public class ProgrammaController
{
	private Lid ingelogdLid=null;
	
	private Scherm_Login loginS;
	private Scherm_foutmelding foutS;
	private Scherm_Hoofdscherm hoofdschermS;
	
	private beheerLid bLid;
	private beheerWedstrijd bWedstrijd;
	private beheerBaksel bBaksel;
	private beheerBestelling bBestelling;
	private beheerDeelnemer bDeelnemer;
	private beheerReactie bReactie;
	private beheerBeoordeling bBeoordeling;
	private beheerJury bJury;
	
	private Database db;
	
	public ProgrammaController()
	{
		
		this.db=new Database("jdbc:mysql://130.161.47.78/cakesoft","cakesoft_team","hjka7812");
		this.bBaksel=new beheerBaksel(this.db);
		this.bBeoordeling=new beheerBeoordeling(this.db);
		this.bBestelling=new beheerBestelling(this.db);
		this.bDeelnemer=new beheerDeelnemer(this.db);
		this.bJury=new beheerJury(this.db);
		this.bLid=new beheerLid(this.db);
		this.bReactie=new beheerReactie(this.db);
		this.bWedstrijd=new beheerWedstrijd(this.db);
		
		
		this.loginS=new Scherm_Login(this);
	}
	
	public void openOverzicht()
	{
		this.hoofdschermS=new Scherm_Hoofdscherm(this,this.ingelogdLid.isHoofdbeheer());
		ArrayList<Wedstrijd> wedstrijden=this.bWedstrijd.getAlleWedstrijden();
		ArrayList<Bestelling> bestellingen_binnenkomend=this.bBestelling.getBestellingenInkomend(this.ingelogdLid);
		ArrayList<Bestelling> bestellingen_uitgaand=this.bBestelling.getBestellingenUitgaand(this.ingelogdLid);
		
		this.hoofdschermS.setWedstrijden(wedstrijden);
		this.hoofdschermS.setBestellingInkomend(bestellingen_binnenkomend);
		this.hoofdschermS.setBestellingUitgaand(bestellingen_uitgaand);
		
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
//	Acties voor Scherm_WedstrijdNieuw
//
	
	public void actieMaak()
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
