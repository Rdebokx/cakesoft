import java.util.*;
import javax.swing.*;

public class ProgrammaController
{
	private Lid ingelogdLid=null;
	private Wedstrijd actieveWedstrijd=null;
	
	private JFrame actiefScherm;
	
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
		
		
		this.actiefScherm=new Scherm_Login(this);
	}
	
	public void openOverzicht()
	{
		this.actiefScherm.dispose();
		this.actiefScherm=new Scherm_Hoofdscherm(this,this.ingelogdLid.isHoofdbeheer());
		ArrayList<Wedstrijd> wedstrijden=this.bWedstrijd.getAlleWedstrijden();
		ArrayList<Bestelling> bestellingen_binnenkomend=this.bBestelling.getBestellingenInkomend(this.ingelogdLid);
		ArrayList<Bestelling> bestellingen_uitgaand=this.bBestelling.getBestellingenUitgaand(this.ingelogdLid);
		
		((Scherm_Hoofdscherm)this.actiefScherm).setWedstrijden(wedstrijden);
		((Scherm_Hoofdscherm)this.actiefScherm).setBestellingInkomend(bestellingen_binnenkomend);
		((Scherm_Hoofdscherm)this.actiefScherm).setBestellingUitgaand(bestellingen_uitgaand);
		
		//set gegevens als wedstrijden, bestellingen
	}
	
	public void openWedstrijd()
	{
		this.actiefScherm.dispose();
		this.actiefScherm=new Scherm_Wedstrijd(this,this.actieveWedstrijd);
		
		ArrayList<Deelnemer> deelnemers=this.bDeelnemer.getDeelnemers(this.actieveWedstrijd);
		((Scherm_Wedstrijd)this.actiefScherm).setDeelnemers(deelnemers);
		
		//stel wat dingen in
	}

//
// Acties voor Scherm_login
//	
	
	public void actieLogin()
	{
		if(!(this.actiefScherm instanceof Scherm_Login))
			return;//zou niet moeten kunnen
		Lid ingevuldLid=((Scherm_Login)this.actiefScherm).getLid();
		
		Lid ingelogdLid=this.bLid.getLidDoorLogin(ingevuldLid);
		if(ingelogdLid==null)
		{
			//foutieve login
			
			new Scherm_foutmelding("U hebt een verkeerd lid id of wachtwoord ingevoerd.");
		}
		else
		{
			//succesvolle login
			
			this.ingelogdLid=ingelogdLid;
			this.openOverzicht();
		}
		
	}
	

//
//	 Acties voor Scherm_Hoofdscherm
//		
	
	public void actieBekijkWedstrijd()
	{
		if(!(this.actiefScherm instanceof Scherm_Hoofdscherm))
			return;
		Wedstrijd wedstrijd=((Scherm_Hoofdscherm)this.actiefScherm).getGeselecteerdeWedstrijd();
		if(wedstrijd==null)
			new Scherm_foutmelding("U moet eerst een wedstrijd uit de lijst selecteren.");
		else
		{
			this.actieveWedstrijd=wedstrijd;
			this.openWedstrijd();
		}
		
	}

	public void actieNieuwWedstrijd()
	{
		this.actiefScherm.dispose();
		this.actiefScherm=new Scherm_WedstrijdNieuw(this);
	}
	
	public void actieVerwijderBestelling()
	{
		
	}

	public void actieLoguit()
	{
		this.ingelogdLid=null;
		
		this.actiefScherm.dispose();
		this.actiefScherm=new Scherm_Login(this);
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
	public void actieTerugNaarHoofdscherm()
	{
		this.openOverzicht();
	}
	
	
	public void actieTerugNaarWedstrijd()
	{
		//
	}
}
