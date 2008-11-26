import java.awt.event.*;

public class ProgrammaController
{
	private Lid ingelogdLid=null;
	private Scherm_Login loginS;
	private Scherm_Overzicht overzichtS;
	private beheerLid bLid;
	private Database db;
	
	public ProgrammaController()
	{
		this.db=new Database("","","");
		this.bLid=new beheerLid(this.db);
		
		this.loginS=new Scherm_Login(this);
	}
	
	
	public void actieLogin()
	{
		this.ingelogdLid=this.loginS.getLid();
		
		
		
		this.loginS.dispose();
		this.overzichtS=new Scherm_Overzicht(this);
	}
	
	public void actieLoguit()
	{
		this.ingelogdLid=null;
		
		this.overzichtS.dispose();
		this.loginS=new Scherm_Login(this);
	}
}
