package pokemon.view;

import javax.swing.JFrame;

import pokemon.view.PokedexPanel;
import pokemon.controller.PokedexController;

public class PokedexFrame extends JFrame
{
	private PokedexController appController;
	private PokedexPanel appPanel;
	
	public PokedexFrame(PokedexController appController)
	{
		super();
		
		this.appController = appController;
		this.appPanel = new PokedexPanel(appController);
	
		setupFrame();
	}
	
	public void setupFrame()
	{
		this.setContentPane(appPanel);
		this.setSize(1000, 800);
		this.setTitle("Pokedex");
		this.setResizable(false);
		this.setVisible(true);
	}
}
