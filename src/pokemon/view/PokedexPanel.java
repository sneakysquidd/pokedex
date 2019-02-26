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
		
		private JButton saveButton;
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
			appLayout.putConstraint(SpringLayout.EAST, numberField, -53, SpringLayout.EAST, this);
			nameField = new JTextField("My pokename");
			appLayout.putConstraint(SpringLayout.EAST, nameField, 0, SpringLayout.EAST, numberField);
			evolveField = new JTextField("false");
			appLayout.putConstraint(SpringLayout.EAST, evolveField, 0, SpringLayout.EAST, numberField);
			attackField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.NORTH, nameField, 6, SpringLayout.SOUTH, attackField);
			appLayout.putConstraint(SpringLayout.WEST, attackField, 1, SpringLayout.WEST, numberField);
			appLayout.putConstraint(SpringLayout.EAST, attackField, 0, SpringLayout.EAST, numberField);
			enhancementField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.SOUTH, evolveField, -6, SpringLayout.NORTH, enhancementField);
			appLayout.putConstraint(SpringLayout.NORTH, enhancementField, 230, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.EAST, enhancementField, 0, SpringLayout.EAST, numberField);
			healthField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.WEST, healthField, 0, SpringLayout.WEST, numberField);
			appLayout.putConstraint(SpringLayout.EAST, healthField, -53, SpringLayout.EAST, this);
			nameLabel = new JLabel("my name is");
			appLayout.putConstraint(SpringLayout.WEST, nameField, 26, SpringLayout.EAST, nameLabel);
			appLayout.putConstraint(SpringLayout.EAST, nameLabel, -208, SpringLayout.EAST, this);
			appLayout.putConstraint(SpringLayout.NORTH, nameLabel, 306, SpringLayout.NORTH, this);
			healthLabel = new JLabel("This pokemans health is");
			appLayout.putConstraint(SpringLayout.NORTH, healthField, -5, SpringLayout.NORTH, healthLabel);
			appLayout.putConstraint(SpringLayout.WEST, healthLabel, 0, SpringLayout.WEST, nameLabel);
			numberLabel = new JLabel("This pokmans number is");
			appLayout.putConstraint(SpringLayout.SOUTH, healthLabel, -35, SpringLayout.SOUTH, numberLabel);
			appLayout.putConstraint(SpringLayout.WEST, numberField, 30, SpringLayout.EAST, numberLabel);
			appLayout.putConstraint(SpringLayout.NORTH, numberField, -5, SpringLayout.NORTH, numberLabel);
			appLayout.putConstraint(SpringLayout.WEST, numberLabel, 0, SpringLayout.WEST, healthLabel);
			evolveLabel = new JLabel("this pokeman can evolve");
			appLayout.putConstraint(SpringLayout.SOUTH, evolveLabel, -35, SpringLayout.SOUTH, enhancementField);
			appLayout.putConstraint(SpringLayout.SOUTH, numberLabel, -35, SpringLayout.SOUTH, evolveLabel);
			appLayout.putConstraint(SpringLayout.WEST, evolveLabel, 0, SpringLayout.WEST, healthLabel);
			attackLabel = new JLabel("this pokemans attack is");
			appLayout.putConstraint(SpringLayout.NORTH, attackField, -5, SpringLayout.NORTH, attackLabel);
			appLayout.putConstraint(SpringLayout.SOUTH, attackLabel, -35, SpringLayout.SOUTH, nameLabel);
			appLayout.putConstraint(SpringLayout.WEST, attackLabel, 0, SpringLayout.WEST, nameLabel);
			enhanceLabel = new JLabel("this pokeman can be enhanced");
			appLayout.putConstraint(SpringLayout.SOUTH, enhanceLabel, -35, SpringLayout.SOUTH, attackField);
			appLayout.putConstraint(SpringLayout.WEST, enhanceLabel, 0, SpringLayout.WEST, nameLabel);
			imageLabel = new JLabel("Pokemon goes here", new ImageIcon(PokedexPanel.class.getResource("/pokemon/view/images/Wigglytuff.png")), JLabel.CENTER);
			changeButton = new JButton("Click here to change the pokevalues");
			appLayout.putConstraint(SpringLayout.NORTH, changeButton, 10, SpringLayout.SOUTH, imageLabel);
			appLayout.putConstraint(SpringLayout.WEST, changeButton, 80, SpringLayout.WEST, this);
			pokedexDropdown = new JComboBox<String>();
			appLayout.putConstraint(SpringLayout.NORTH, pokedexDropdown, 30, SpringLayout.NORTH, changeButton);
			appLayout.putConstraint(SpringLayout.WEST, pokedexDropdown, 140, SpringLayout.WEST, this);
			appLayout.putConstraint(SpringLayout.EAST, pokedexDropdown, -531, SpringLayout.EAST, this);
			saveButton = new JButton("Save Pokedex");
			appLayout.putConstraint(SpringLayout.NORTH, saveButton, 3, SpringLayout.SOUTH, pokedexDropdown);
			appLayout.putConstraint(SpringLayout.WEST, saveButton, 0, SpringLayout.WEST, pokedexDropdown);
			appLayout.putConstraint(SpringLayout.EAST, saveButton, 129, SpringLayout.WEST, pokedexDropdown);
			
			
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
		
		private void updateFields(int index)
		{
			String [] data = appController.getPokeData(index);
			
			attackField.setText(data[0]);
			enhancementField.setText(data[1]);
			healthField.setText(data[2]);
			nameField.setText(data[3]);
			evolveField.setText(data[4]);
			numberField.setText(data[5]);
		}
		
		private void setupLayout()
		{
			
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
						updateFields(pokedexDropdown.getSelectedIndex());
						changeImageDisplay(name);
					}
			});
			
			saveButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent click)
						{
							appController.savePokedex();
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
			this.add(changeButton);
			this.add(saveButton);
			
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