import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class ProgrammaController extends JFrame implements ActionListener
{
	private Lid ingelogdLid=null;
	private Wedstrijd actieveWedstrijd=null;
	
	private JPanel actiefPanel=null;
	//private JFrame actiefScherm;
	
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
		setTitle("CakeSoft");
		setSize(750,500);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
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
		
		//this.actiefScherm=new Scherm_Login(this);
		this.openLogin();
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(this.actiefPanel instanceof Panel_Login)
		{
			Panel_Login panel=(Panel_Login)this.actiefPanel;
			if(e.getSource()==panel.getLoginKnop() || e.getSource()==panel.getLidnr_veld() || e.getSource()==panel.getPass_veld())
				this.actieLogin();
		}
		if(this.actiefPanel instanceof Panel_Hoofdscherm)
		{
			Panel_Hoofdscherm panel=(Panel_Hoofdscherm)this.actiefPanel;
			if(e.getSource() == panel.getBekijkWedstrijd_knop())
				this.actieBekijkWedstrijd();
			else if(e.getSource() == panel.getNieuwWedstrijd_knop() && this.ingelogdLid.isHoofdbeheer())
				this.openWedstrijdNieuw();
			else if(e.getSource() == panel.getVerwijderBestelling_knop())
				this.actieVerwijderBestelling();
			else if(e.getSource() == panel.getLoguit_knop())
				this.actieLoguit();
		}
		if(this.actiefPanel instanceof Panel_Wedstrijd)
		{
			Panel_Wedstrijd panel=(Panel_Wedstrijd)this.actiefPanel;
			
			if(e.getSource() == panel.getBekijkDeelnemer_knop())
				this.actieBekijkDeelnemer();
			if(e.getSource() == panel.getInschrijven_knop() && this.actieveWedstrijd.isInschrijvingOpen())
				this.openInschrijven();
			if(e.getSource() == panel.getTerug_knop())
				this.actieTerugNaarHoofdscherm();
			if(e.getSource()== panel.getSluitWedstrijd_knop())
				this.actieSluitWedstrijd();
		}
		if(this.actiefPanel instanceof Panel_Inschrijven)
		{
			Panel_Inschrijven panel=(Panel_Inschrijven)this.actiefPanel;
			
			if(e.getSource() == panel.getInschrijf_knop())
				this.actieInschrijvingVerzenden();
			if(e.getSource() == panel.getTerug_knop())
				this.actieTerugNaarWedstrijd();	
		}
		if(this.actiefPanel instanceof Panel_WedstrijdKlaar)
		{
			Panel_WedstrijdKlaar panel=(Panel_WedstrijdKlaar)this.actiefPanel;
			
			if(e.getSource() == panel.getBekijkDeelnemer_knop())
				this.actieBekijkDeelnemer();
			if(e.getSource() == panel.getTerug_knop())
				this.actieTerugNaarHoofdscherm();
			if(e.getSource()== panel.getBestel_knop() || e.getSource()== panel.getBestellen_veld())
				this.actieBestel();
			if(e.getSource()== panel.getJurylid_drop())
				panel.updateBeoordeling();
		}
		if(this.actiefPanel instanceof Panel_WedstrijdNieuw)
		{
			Panel_WedstrijdNieuw panel=(Panel_WedstrijdNieuw)this.actiefPanel;
			
			if(e.getSource()==panel.getMaak_knop())
				this.actieMaakWedstrijd();
			if(e.getSource()==panel.getTerug_knop())
				this.actieTerugNaarHoofdscherm();
		}
	}
	
	public void sluitActiefPanel()
	{
		if(this.actiefPanel==null)
			return;
		this.remove(this.actiefPanel);
		this.actiefPanel.removeAll();
		
		this.actiefPanel=null;
	}
	
	public void openLogin()
	{
		this.sluitActiefPanel();
		
		Panel_Login panel=new Panel_Login();
		panel.setBounds(75,50,600,600);
		panel.getLoginKnop().addActionListener(this);
		panel.getLidnr_veld().addActionListener(this);
		panel.getPass_veld().addActionListener(this);
		
		this.actiefPanel=panel;
		this.add(panel);
		this.actiefPanel.revalidate();
		repaint();
	}
	
	public void openOverzicht()
	{
		this.sluitActiefPanel();
		
		Panel_Hoofdscherm panel=new Panel_Hoofdscherm(this.ingelogdLid.isHoofdbeheer());
		panel.setBounds(0,0,1000,1000);
		
		ArrayList<Wedstrijd> wedstrijden=this.bWedstrijd.getAlleWedstrijden();
		ArrayList<Bestelling> bestellingen_binnenkomend=this.bBestelling.getBestellingenInkomend(this.ingelogdLid);
		ArrayList<Bestelling> bestellingen_uitgaand=this.bBestelling.getBestellingenUitgaand(this.ingelogdLid);
		
		
		panel.setWedstrijden(wedstrijden);
		panel.setBestellingInkomend(bestellingen_binnenkomend);
		panel.setBestellingUitgaand(bestellingen_uitgaand);
		
		
		panel.getBekijkWedstrijd_knop().addActionListener(this);
		panel.getNieuwWedstrijd_knop().addActionListener(this);
		panel.getVerwijderBestelling_knop().addActionListener(this);
		panel.getLoguit_knop().addActionListener(this);
		
		this.actiefPanel=panel;
		this.add(panel);
		this.actiefPanel.revalidate();
		repaint();
	}
	
	public void openWedstrijd()
	{
		this.sluitActiefPanel();
		
		
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
		
		//toon wedstrijd voordat hij is gesloten
		if(this.actieveWedstrijd.isInschrijvingOpen() || this.actieveWedstrijd.isBeoordelingOpen())
		{
			ArrayList<Deelnemer> deelnemers=this.bDeelnemer.getDeelnemers(this.actieveWedstrijd);
			if(deelnemers.size()>=12)
				isDeelnemer=true;//maakt het onmogelijk nog in te schrijven
			
			boolean toonSluitKnop=(this.ingelogdLid.isHoofdbeheer() && this.actieveWedstrijd.isBeoordelingOpen());
			
			Panel_Wedstrijd panel=new Panel_Wedstrijd(this.actieveWedstrijd,isJury,isDeelnemer,toonSluitKnop);
			panel.setBounds(0,0,1000,1000);
			panel.setDeelnemers(deelnemers);
			
			panel.getBekijkDeelnemer_knop().addActionListener(this);
			if(this.actieveWedstrijd.isInschrijvingOpen() && !isJury && !isDeelnemer)
				panel.getInschrijven_knop().addActionListener(this);
			panel.getTerug_knop().addActionListener(this);
			if(toonSluitKnop)
				panel.getSluitWedstrijd_knop().addActionListener(this);
			
			this.actiefPanel=panel;
			this.add(panel);
			this.actiefPanel.revalidate();
			repaint();
		}
		else//toon wedstrijd nadat hij is gesloten
		{
			ArrayList<Deelnemer> deelnemers=this.bDeelnemer.getDeelnemers(this.actieveWedstrijd);
			this.actieveWedstrijd.setWinnaar(null);
			if(this.actieveWedstrijd.getWinnaar_lid_id()>0)
				this.actieveWedstrijd.setWinnaar(this.bLid.getLidVanId(this.actieveWedstrijd.getWinnaar_lid_id()));
			
			Panel_WedstrijdKlaar panel=new Panel_WedstrijdKlaar(this.actieveWedstrijd);
			panel.setBounds(0,0,1000,1000);
			
			panel.setDeelnemers(deelnemers);
			
			panel.getBekijkDeelnemer_knop().addActionListener(this);
			panel.getTerug_knop().addActionListener(this);
			panel.getBestel_knop().addActionListener(this);
			panel.getJurylid_drop().addActionListener(this);
			panel.getBestellen_veld().addActionListener(this);
			
			this.actiefPanel=panel;
			this.add(panel);
			this.actiefPanel.revalidate();
			repaint();
		}
		
		
		
	}
	
	public void openWedstrijdNieuw()
	{
		this.sluitActiefPanel();
		
		Panel_WedstrijdNieuw panel=new Panel_WedstrijdNieuw();
		panel.setBounds(0,0,1000,1000);
		
		panel.getMaak_knop().addActionListener(this);
		panel.getTerug_knop().addActionListener(this);
		
		this.actiefPanel=panel;
		this.add(panel);
		this.actiefPanel.revalidate();
		repaint();
	}
	
	public void openInschrijven()
	{
		this.sluitActiefPanel();
		
		Panel_Inschrijven panel=new Panel_Inschrijven(this.actieveWedstrijd);
		panel.setBounds(0,0,1000,1000);
		
		panel.getInschrijf_knop().addActionListener(this);
		panel.getTerug_knop().addActionListener(this);
		
		this.actiefPanel=panel;
		this.add(panel);
		this.actiefPanel.revalidate();
		repaint();
		
	}
//
// Acties voor Scherm_login
//	
	
	public void actieLogin()
	{
		if(!(this.actiefPanel instanceof Panel_Login))
			return;//zou niet moeten kunnen
		
		Lid ingevuldLid=((Panel_Login)this.actiefPanel).getLid();
		
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
		if(!(this.actiefPanel instanceof Panel_Hoofdscherm))
			return;
		Panel_Hoofdscherm panel=(Panel_Hoofdscherm)this.actiefPanel;
		
		Wedstrijd wedstrijd=panel.getGeselecteerdeWedstrijd();
		
		if(wedstrijd==null)
			new Scherm_foutmelding("U moet eerst een wedstrijd uit de lijst selecteren.");
		else
		{
			this.actieveWedstrijd=wedstrijd;
			this.openWedstrijd();
		}
		
	}
	
	public void actieVerwijderBestelling()
	{
		if(!(this.actiefPanel instanceof Panel_Hoofdscherm))
			return;
		
		Panel_Hoofdscherm panel=(Panel_Hoofdscherm)this.actiefPanel;
		
		Bestelling bestelling=panel.getGeselecteerdeInBestelling();
		if(bestelling==null)
			new Scherm_foutmelding("U moet eerst een bestelling uit de lijst selecteren.");
		else
		{
			this.bBestelling.verwijderBestelling(bestelling);
			
			ArrayList<Bestelling> bestellingen_binnenkomend=this.bBestelling.getBestellingenInkomend(this.ingelogdLid);
			panel.setBestellingInkomend(bestellingen_binnenkomend);
			
			new Scherm_foutmelding("Deze ontvangen bestelling is nu verwijderd.","Bestelling verwijderen");
		}
	}

	public void actieLoguit()
	{
		this.ingelogdLid=null;
		this.openLogin();
	}

//
//	 Acties voor Scherm_Wedstrijd(Klaar)
//		
	
	public void actieBekijkDeelnemer()
	{
		if(this.actiefPanel instanceof Panel_Wedstrijd)
		{
			Panel_Wedstrijd panel=(Panel_Wedstrijd)this.actiefPanel;
			
			Deelnemer deelnemer=panel.getGeselecteerdeDeelnemer();
			if(deelnemer==null)
			{
				new Scherm_foutmelding("U moet eerst een deelnemer uit de lijst selecteren.");
			}
			else
			{
				panel.toonDeelnemer(deelnemer);
			}
		}
		if(this.actiefPanel instanceof Panel_WedstrijdKlaar)
		{
			Panel_WedstrijdKlaar panel=(Panel_WedstrijdKlaar)this.actiefPanel;
			
			Deelnemer deelnemer=panel.getGeselecteerdeDeelnemer();
			if(deelnemer==null)
			{
				new Scherm_foutmelding("U moet eerst een deelnemer uit de lijst selecteren.");
			}
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
				
				panel.toonDeelnemer(deelnemer);
				panel.setBeoordelingen(beoordelingen,gemiddelde);
				this.repaint();
				this.actiefPanel.revalidate();
			}
		}
	}
	
	public void actieBestel()
	{
		if(!(this.actiefPanel instanceof Panel_WedstrijdKlaar))
			return;
		
		Panel_WedstrijdKlaar panel=(Panel_WedstrijdKlaar)this.actiefPanel;
		
		Bestelling bestelling=panel.getBestelling();
		if(bestelling==null)
		{
			new Scherm_foutmelding("U hebt geen geldig aantal ingevuld om te bestellen.");
		}
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
	
	public void actieSluitWedstrijd()
	{
		if(!(this.actiefPanel instanceof Panel_Wedstrijd))
			return;
		
		//Panel_Wedstrijd panel=(Panel_Wedstrijd)this.actiefPanel;
		
		this.actieveWedstrijd.setBeoordelingOpen(false);
		
		double smaak,prijs,kwaliteit,calo,teller;
		ArrayList<Beoordeling> beoordelingen;
		
		//bereken winnaar
		ArrayList<Deelnemer> deelnemers=this.bDeelnemer.getDeelnemers(this.actieveWedstrijd);
		for(Deelnemer deelnemer:deelnemers)
		{
			beoordelingen=this.bBeoordeling.getBeoordelingenVanBaksel(deelnemer.getBaksel());
			teller=0;
			smaak=0;
			prijs=0;
			kwaliteit=0;
			calo=0;
			for(Beoordeling beoordeling:beoordelingen)
			{
				teller+=1;
				smaak+=beoordeling.getSmaak();
				prijs+=beoordeling.getPrijs();
				kwaliteit+=beoordeling.getKwaliteit();
				calo+=beoordeling.getCalo();
			}
			if(teller>0)
			{
				smaak=smaak/teller;
				prijs=prijs/teller;
				kwaliteit=kwaliteit/teller;
				calo=calo/teller;
			}
			deelnemer.setPunten(smaak+prijs+kwaliteit+calo);
		}
		
		//sorteer nu en geef plaatsen
		int i,j;
		Deelnemer temp_deelnemer;
		//zet om in een normaal array
		Deelnemer[] temp_deelnemers = new Deelnemer[deelnemers.size()];
		for(i=0;i<deelnemers.size();i++)
			temp_deelnemers[i]=deelnemers.get(i);
		
		//bubble sorten
		boolean actie=true;
		for(i=0;i<temp_deelnemers.length;i++)
		{
			for(j=0;(j<temp_deelnemers.length-1 && actie);j++)
			{
				actie=false;
				if(temp_deelnemers[j+1].getPunten()>temp_deelnemers[j].getPunten())
				{
					temp_deelnemer=temp_deelnemers[j];
					temp_deelnemers[j]=temp_deelnemers[j+1];
					temp_deelnemers[j+1]=temp_deelnemer;
					actie=true;
				}
			}
		}
		
		for(i=0;i<temp_deelnemers.length;i++)
		{
			temp_deelnemers[i].setPlaats(i+1);
			this.bDeelnemer.updateDeelnemer(temp_deelnemers[i]);
		}
		
		if(temp_deelnemers.length>0)
			this.actieveWedstrijd.setWinnaar_lid_id(temp_deelnemers[0].getLid_id());
		else
			this.actieveWedstrijd.setWinnaar_lid_id(0);
		this.bWedstrijd.updateWedstrijd(this.actieveWedstrijd);
		
		this.openWedstrijd();
		
		new Scherm_foutmelding("U hebt deze wedstrijd nu gesloten.","Wedstrijd sluiten");
	}
	
//
//	Acties voor Scherm_WedstrijdNieuw
//
	
	public void actieMaakWedstrijd()
	{
		if(!(this.actiefPanel instanceof Panel_WedstrijdNieuw))
			return;
		
		Panel_WedstrijdNieuw panel=(Panel_WedstrijdNieuw)this.actiefPanel;
		
		Wedstrijd wedstrijd=panel.getWedstrijd();
		if(wedstrijd==null)
			return;//geen geldige invoer
		
		String jury_naam,namen;
		ArrayList<Lid> mogelijkheden;
		ArrayList<Jury> juryleden=new ArrayList<Jury>();
		int i,j;
		
		//loop door juryleden heen
		for(i=1;i<=3;i++)
		{
			//vraag naam op van het scherm
			jury_naam=panel.getJuryNaam(i);
			
			if(jury_naam.equals("") || jury_naam==null)
			{
				new Scherm_foutmelding("U hebt geen naam ingevuld voor jurylid "+i+".");
				return;
			}
			
			//kijk wie allemaal bedoeld zou kunnen zijn
			mogelijkheden=this.bLid.zoekOpNaam(jury_naam);
			if(mogelijkheden.size()==0)//lid niet gevonden
			{
				new Scherm_foutmelding("U hebt geen geldige naam opgegeven voor jurylid "+i+".");
				return;
			}
			//niet exact de goede naam ingevuld.
			if(!mogelijkheden.get(0).getNaam().toLowerCase().equals(jury_naam.toLowerCase()))
			{
				namen="";
				for(j=0;j<Math.min(5,mogelijkheden.size());j++)//toon hooguit 5 opties
					namen+="\n"+mogelijkheden.get(j).getNaam();
				
				new Scherm_foutmelding("U hebt geen geldige naam opgegeven voor jurylid "+i+".\n"+(mogelijkheden.size()==1?"Een naam:":"Namen")+" die veel op '"+jury_naam+"' lijk"+(mogelijkheden.size()==1?"t is:":"en zijn:")+namen);
				return;
			}
			//hier is er maar 1 mogelijkheid, voeg dus toe als jurylid
			juryleden.add(new Jury(mogelijkheden.get(0)));
		}
		
		wedstrijd.setBeoordelingOpen(false);
		wedstrijd.setInschrijvingOpen(true);
		wedstrijd.setWinnaar_lid_id(0);
		
		this.bWedstrijd.voegWedstrijdToe(wedstrijd);
		
		for(Jury jury:juryleden)
			this.bJury.voegJuryToe(jury,wedstrijd);
		
		this.openOverzicht();
		new Scherm_foutmelding("Deze nieuwe wedstrijd is toegevoegd.","Nieuwe Wedstrijd");
	}
	
//
//	Acties voor Scherm_Inscrijven
//
	
	public void actieInschrijvingVerzenden()
	{
		if(!(this.actiefPanel instanceof Panel_Inschrijven))
			return;
		
		Panel_Inschrijven panel=(Panel_Inschrijven)this.actiefPanel;
		
		Baksel baksel=panel.getBaksel();
		if(baksel!=null)
		{
			this.bBaksel.voegBakselToe(baksel);
			
			Deelnemer deelnemer=new Deelnemer(this.ingelogdLid);
			deelnemer.setBaksel(baksel);
			this.bDeelnemer.voegDeelnemerToe(deelnemer, this.actieveWedstrijd);
			this.openWedstrijd();
			new Scherm_foutmelding("U bent nu ingeschreven voor deze wedstrijd.","Inschrijven");
		}
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
		this.openWedstrijd();
	}
}
