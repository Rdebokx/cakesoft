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
		String hulpvar=Integer.toString(7812);
		this.db=new Database("jdbc:mysql://130.161.47.78/cakesoft","cakesoft_team","hjka"+hulpvar);
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
		
	}
	
	public void openWedstrijd()
	{
		this.actiefScherm.dispose();
		
		//update de inschrijvingOpen en beoordelingOpen van deze wedstrijd
		Date nu=new java.util.Date();
		if(this.actieveWedstrijd.getDatum().after(nu))//wedstrijd is nog niet begonnen 
		{
			if(!this.actieveWedstrijd.isInschrijvingOpen() || this.actieveWedstrijd.isBeoordelingOpen())
			{
				//wedstrijd is fout ingesteld
				this.actieveWedstrijd.setInschrijvingOpen(true);
				this.actieveWedstrijd.setBeoordelingOpen(false);
				this.bWedstrijd.updateWedstrijd(this.actieveWedstrijd);
			}
		}
		else
		{
			if(this.actieveWedstrijd.isInschrijvingOpen())//verander de wedstrijd status als nodig
			{
				this.actieveWedstrijd.setInschrijvingOpen(false);
				this.actieveWedstrijd.setBeoordelingOpen(true);
				this.bWedstrijd.updateWedstrijd(this.actieveWedstrijd);
			}
		}
		
		boolean isJury=(this.bJury.getJuryVanWedstrijd(this.actieveWedstrijd,this.ingelogdLid)!=null);
		boolean isDeelnemer=(this.bDeelnemer.getDeelnemerVanWedstrijd(this.ingelogdLid,this.actieveWedstrijd)!=null);
		
		//open wedstrijd voordat hij is gesloten
		if(this.actieveWedstrijd.isInschrijvingOpen() || this.actieveWedstrijd.isBeoordelingOpen())
		{
			this.actiefScherm=new Scherm_Wedstrijd(this,this.actieveWedstrijd,isJury,isDeelnemer);
			ArrayList<Deelnemer> deelnemers=this.bDeelnemer.getDeelnemers(this.actieveWedstrijd);
			
			((Scherm_Wedstrijd)this.actiefScherm).setDeelnemers(deelnemers);
		}
		else//open wedstrijd nadat hij is gesloten
		{
			ArrayList<Deelnemer> deelnemers=this.bDeelnemer.getDeelnemers(this.actieveWedstrijd);
			this.actieveWedstrijd.setWinnaar(null);
			if(this.actieveWedstrijd.getWinnaar_lid_id()>0)
				this.actieveWedstrijd.setWinnaar(this.bLid.getLidVanId(this.actieveWedstrijd.getWinnaar_lid_id()));
			
			this.actiefScherm=new Scherm_WedstrijdKlaar(this,this.actieveWedstrijd);
			((Scherm_WedstrijdKlaar)this.actiefScherm).setDeelnemers(deelnemers);
			
		}
		
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
			
			new Scherm_foutmelding("U hebt een verkeerd lidnummer of wachtwoord ingevoerd.");
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
		if(!(this.actiefScherm instanceof Scherm_Hoofdscherm))
			return;
		Bestelling bestelling=((Scherm_Hoofdscherm)this.actiefScherm).getGeselecteerdeInBestelling();
		if(bestelling==null)
			new Scherm_foutmelding("U moet eerst een bestelling uit de lijst selecteren.");
		else
		{
			this.bBestelling.verwijderBestelling(bestelling);
			
			ArrayList<Bestelling> bestellingen_binnenkomend=this.bBestelling.getBestellingenInkomend(this.ingelogdLid);
			((Scherm_Hoofdscherm)this.actiefScherm).setBestellingInkomend(bestellingen_binnenkomend);
			
			new Scherm_foutmelding("Deze ontvangen bestelling is nu verwijderd.","Bestelling verwijderen");
		}
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
		if(this.actiefScherm instanceof Scherm_Wedstrijd)
		{
			Deelnemer deelnemer=((Scherm_Wedstrijd)this.actiefScherm).getGeselecteerdeDeelnemer();
			if(deelnemer==null)
				new Scherm_foutmelding("U moet eerst een deelnemer uit de lijst selecteren.");
			else
			{
				((Scherm_Wedstrijd)this.actiefScherm).toonDeelnemer(deelnemer);
			}
		}
		if(this.actiefScherm instanceof Scherm_WedstrijdKlaar)
		{
			Deelnemer deelnemer=((Scherm_WedstrijdKlaar)this.actiefScherm).getGeselecteerdeDeelnemer();
			if(deelnemer==null)
				new Scherm_foutmelding("U moet eerst een deelnemer uit de lijst selecteren.");
			else
			{
				ArrayList<Beoordeling> beoordelingen=this.bBeoordeling.getBeoordelingenVanBaksel(deelnemer.getBaksel());
				Beoordeling gemiddelde=new Beoordeling(0,"",0,0,0,0);
				for(Beoordeling beoordeling:beoordelingen)
				{
					gemiddelde.setCalo(gemiddelde.getCalo()+beoordeling.getCalo());
					gemiddelde.setPrijs(gemiddelde.getPrijs()+beoordeling.getPrijs());
					gemiddelde.setKwaliteit(gemiddelde.getKwaliteit()+beoordeling.getKwaliteit());
					gemiddelde.setSmaak(gemiddelde.getSmaak()+beoordeling.getSmaak());
				}
				if(beoordelingen.size()>0)
				{
					gemiddelde.setCalo((int)Math.round(((double)gemiddelde.getCalo())/((double)beoordelingen.size())));
					gemiddelde.setPrijs((int)Math.round(((double)gemiddelde.getPrijs())/((double)beoordelingen.size())));
					gemiddelde.setKwaliteit((int)Math.round(((double)gemiddelde.getKwaliteit())/((double)beoordelingen.size())));
					gemiddelde.setSmaak((int)Math.round(((double)gemiddelde.getSmaak())/((double)beoordelingen.size())));
				}
				
				((Scherm_WedstrijdKlaar)this.actiefScherm).toonDeelnemer(deelnemer);
				((Scherm_WedstrijdKlaar)this.actiefScherm).setBeoordelingen(beoordelingen,gemiddelde);
			}
		}
	}
	
	public void actieBestel()
	{
		if(!(this.actiefScherm instanceof Scherm_WedstrijdKlaar))
			return;
		Bestelling bestelling=((Scherm_WedstrijdKlaar)this.actiefScherm).getBestelling();
		if(bestelling==null)
			new Scherm_foutmelding("U hebt geen geldig aantal ingevuld om te bestellen.");
		else
		{
			if(bestelling.getAantal()<1)
				new Scherm_foutmelding("U kunt niet minder dan 1 stuk van dit baksel bestellen.");
			else
			{
				bestelling.setLid_besteller(this.ingelogdLid);
				this.bBestelling.voegBestellingToe(bestelling);
				new Scherm_foutmelding("Uw bestelling voor "+bestelling.getAantal()+" stuk"+(bestelling.getAantal()==1?"":"s")+" van dit baksel is geplaatst.","Nieuwe bestelling");
			}
		}
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
