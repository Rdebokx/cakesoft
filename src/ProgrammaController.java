import java.awt.event.*;

public class ProgrammaController
{
	private Lid ingelogdLid=null;
	private LoginScherm loginS;
	private HoofdScherm hoofdS;
	
	public ProgrammaController()
	{
		this.loginS=new LoginScherm(this);
	}
	
	
	public void actieLogin()
	{
		this.ingelogdLid=this.loginS.getLid();
		this.loginS.dispose();
		this.hoofdS=new HoofdScherm(this);
	}
	
	public void actieLoguit()
	{
		this.ingelogdLid=null;
		
		this.hoofdS.dispose();
		this.loginS=new LoginScherm(this);
	}
}
