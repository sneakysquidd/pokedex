package pokemon.view;

import javax.swing.SpringLayout;
import javax.swing.*;

import pokemon.controller.PokedexController;

public class PokedexPanel extends JPanel
{
	private SpringLayout appLayout;
	private PokedexController appController;
	private JTextField WigglytuffText;
	private JLabel WifflytuffLabel;
	private JTextField DewgongText;
	private JLabel DewgongLabel;
	private JTextField DrugongText;
	private JLabel DrugongLabel;
	private JTextField PoliwhirlText;
	private JLabel PoliwhirlLabel;
	private JTextField ZangooseText;
	private JLabel ZangooseLabel;
	private JTextField QuagsireText;
	private JLabel QuagsireLabel;
	
	public PokedexPanel(PokedexController appController)
	{
		super();
		
		this.appController = appController;
		appLayout = new SpringLayout();
		
		
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
	
	private void setupListeners()
	{
		
	}
}
