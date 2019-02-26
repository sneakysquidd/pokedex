package pokemon.view;

import java.awt.Color;
	import java.awt.Dimension;
	import javax.swing.*;

	import pokemon.controller.PokedexController;
	
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;


	public class PokedexPanel extends JPanel
	{

		private SpringLayout appLayout;
		private PokedexController appController;
		
		private JButton changeButton;
		private JComboBox pokedexDropdown;
		
		private JTextField numberField;
		private JTextField nameField;
		private JTextField evolveField;
		private JTextField attackField;
		private JTextField enhancementField;
		private JTextField healthField;
		
		private JLabel numberLabel;
		private JLabel nameLabel;
		private JLabel evolveLabel;
		private JLabel attackLabel;
		private JLabel enhanceLabel;
		private JLabel healthLabel;
		private JLabel imageLabel;
		
		private ImageIcon pokemonIcon;
		
		public PokedexPanel(PokedexController appController)
		{
			super();
			this.appController = appController;
			this.appLayout = new SpringLayout();
			
			this.pokemonIcon = new ImageIcon(getClass().getResource("/pokemon/view/images/pokeball.png"));
			
			numberField = new JTextField("0");
			nameField = new JTextField("My pokename");
			evolveField = new JTextField("false");
			attackField = new JTextField("0");
			enhancementField = new JTextField("0");
			healthField = new JTextField("0");
			nameLabel = new JLabel("my name is");
			healthLabel = new JLabel("This pokemans health is");
			numberLabel = new JLabel("This pokmans number is");
			evolveLabel = new JLabel("this pokeman can evolve");
			attackLabel = new JLabel("this pokemans attack is");
			enhanceLabel = new JLabel("this pokeman can be enhanced");
			imageLabel = new JLabel("Pokemon goes here", new ImageIcon(PokedexPanel.class.getResource("/pokemon/view/images/Wigglytuff.png")), JLabel.CENTER);
			changeButton = new JButton("Click here to change the pokevalues");
			pokedexDropdown = new JComboBox<String>();
			
			
			setupDropdown();
			setupPanel();
			setupLayout();
			setupListeners();	
			
		}
		
		private void setupDropdown()
		{
			DefaultComboBoxModel<String> temp = new DefaultComboBoxModel<String>(appController.buildPokedexText());
			pokedexDropdown.setModel(temp);
			
		}
		
		
		private void setupLayout()
		{
			appLayout.putConstraint(SpringLayout.EAST, evolveField, 0, SpringLayout.EAST, attackField);
			appLayout.putConstraint(SpringLayout.EAST, attackField, -54, SpringLayout.EAST, this);
			appLayout.putConstraint(SpringLayout.SOUTH, enhancementField, -350, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, attackField, 14, SpringLayout.SOUTH, enhancementField);
			appLayout.putConstraint(SpringLayout.WEST, enhancementField, 0, SpringLayout.WEST, attackField);
			appLayout.putConstraint(SpringLayout.WEST, healthField, 0, SpringLayout.WEST, numberField);
			appLayout.putConstraint(SpringLayout.SOUTH, healthField, -15, SpringLayout.NORTH, numberField);
			appLayout.putConstraint(SpringLayout.NORTH, numberLabel, 130, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, numberField, -5, SpringLayout.NORTH, numberLabel);
			appLayout.putConstraint(SpringLayout.NORTH, pokedexDropdown, 242, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.WEST, pokedexDropdown, 80, SpringLayout.WEST, this);
			appLayout.putConstraint(SpringLayout.SOUTH, nameField, -273, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.EAST, nameField, -28, SpringLayout.EAST, this);
			appLayout.putConstraint(SpringLayout.EAST, numberField, -69, SpringLayout.EAST, this);
			appLayout.putConstraint(SpringLayout.SOUTH, enhanceLabel, -355, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, attackLabel, 24, SpringLayout.SOUTH, enhanceLabel);
			appLayout.putConstraint(SpringLayout.SOUTH, evolveLabel, -27, SpringLayout.NORTH, enhanceLabel);
			appLayout.putConstraint(SpringLayout.EAST, attackLabel, -179, SpringLayout.EAST, this);
			appLayout.putConstraint(SpringLayout.WEST, enhanceLabel, 0, SpringLayout.WEST, attackLabel);
			appLayout.putConstraint(SpringLayout.EAST, numberLabel, 0, SpringLayout.EAST, evolveLabel);
			appLayout.putConstraint(SpringLayout.NORTH, evolveField, -5, SpringLayout.NORTH, evolveLabel);
			appLayout.putConstraint(SpringLayout.EAST, evolveLabel, -169, SpringLayout.EAST, this);
			appLayout.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.NORTH, nameField);
			appLayout.putConstraint(SpringLayout.EAST, nameLabel, -56, SpringLayout.WEST, nameField);
		}
		
		private void setupListeners()
		{
			changeButton.addActionListener(new ActionListener()
					
			{
				public void actionPerformed(ActionEvent click)
				{
					sendDataToController();
				}
			});
			
			
			pokedexDropdown.addActionListener(new ActionListener()
			{	
					public void actionPerformed(ActionEvent click)
					{
						String name = pokedexDropdown.getSelectedItem().toString();
						changeImageDisplay(name);
					}
			});
		}
		
		private void setupScrollPane()
		{
			
		}
		
		private void setupPanel()
		{
			this.setLayout(appLayout);
			this.setPreferredSize(new Dimension(800, 600));
			this.setBackground(Color.GRAY);
			
			this.add(numberField);
			this.add(nameField);
			this.add(evolveField);
			this.add(attackField);
			this.add(enhancementField);
			this.add(healthField);
			this.add(healthLabel);
			this.add(nameLabel);
			this.add(numberLabel);
			this.add(evolveLabel);
			this.add(attackLabel);
			this.add(enhanceLabel);
			this.add(imageLabel);
			this.add(pokedexDropdown);
			
			imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
			imageLabel.setHorizontalTextPosition(JLabel.CENTER);
		}
		
		private void sendDataToController()
		{
			int index = pokedexDropdown.getSelectedIndex();
			
			if(appController.isInt(attackField.getText()) && appController.isDouble(enhancementField.getText()) && appController.isInt(healthField.getText()))
			{
				String [] data = new String[5];
				
				appController.updatePokemon(index, data);
			}
		}
		
		private void changeImageDisplay(String name)
		{
			String path = "/pokemon/view/images/";
			String defaultName = "ultraball";
			String extension = ".png";
			try
			{
				pokemonIcon = new ImageIcon(getClass().getResource(path + name.toLowerCase() + extension));
			}
			catch (NullPointerException missingFile)
			{
				pokemonIcon = new ImageIcon(getClass().getResource(path + defaultName + extension));
			}
			imageLabel.setIcon(pokemonIcon);
			repaint();
			
		}
		
}