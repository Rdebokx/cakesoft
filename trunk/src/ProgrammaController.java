import java.awt.event.*;

public class ProgrammaController
{
	private ContainerScherm containerS;
	private Lid ingelogdLid=null;
	private loginPanel loginP;
	private HoofdPanel hoofdP;
	
	public ProgrammaController()
	{
		this.containerS=new ContainerScherm();
		
		this.laadLoginPanel();
	}
	
	public void laadLoginPanel()
	{
		this.loginP=new loginPanel(this,this.containerS);
		this.containerS.veranderPanel(this.loginP);
	}
	
	public void laadHoofdPanel()
	{
		this.hoofdP=new HoofdPanel(this,this.containerS);
		this.containerS.veranderPanel(this.hoofdP);
	}
	
	
	public void actieLogin()
	{
		this.ingelogdLid=this.loginP.getLid();
		this.laadHoofdPanel();
	}
	
	public void actieLoguit()
	{
		this.ingelogdLid=null;
		this.laadLoginPanel();
	}
}
