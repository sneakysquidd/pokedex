package pokemon.controller;

import java.util.ArrayList;
import java.io.*;
import javax.swing.JOptionPane;

import pokemon.model.Dewgong;
import pokemon.model.Drugong;
import pokemon.model.Pokemon;
import pokemon.model.Poliwhirl;
import pokemon.model.Quagsire;
import pokemon.model.Wigglytuff;
import pokemon.model.Zangoose;
import pokemon.view.PokedexFrame;

public class PokedexController 
{
	private PokedexFrame appFrame;
	private ArrayList<Pokemon> pokemonList;
	private String saveFile = "backup.pokemon";
	
	public PokedexController()
	{
		pokemonList = new ArrayList<Pokemon>();
		addPokemon();
		appFrame = new PokedexFrame(this);
	}
	
//	public PokedexFrame getFrame()
//	{
//		return PokedexFrame;
//	}
	
	private void addPokemon()
	{
		pokemonList.add(new Dewgong());
		pokemonList.add(new Drugong());
		pokemonList.add(new Poliwhirl());
		pokemonList.add(new Quagsire());
		pokemonList.add(new Wigglytuff());
		pokemonList.add(new Zangoose());
	}
	
	public void start() 
	{
		
	}
	
	public ArrayList<Pokemon> getPokemonList()
	{
		return pokemonList;
	}
	
	public void updatePokemon(int index, String [] data)
	{
		if (data.length == 5)
		{
			Pokemon current = pokemonList.get(index);
			current.setAttackPoints(Integer.parseInt(data[0]));
			current.setEnhancementModifier(Double.parseDouble(data[2]));
			current.setHealthPoints(Integer.parseInt(data[2]));
			current.setName(data[3]);
			current.setCanEvolve(Boolean.parseBoolean(data[4]));
		}
	}
	
	public String[] buildPokedexText()
	{
		String [] names = new String [pokemonList.size()];
		
		for(int index = 0; index < pokemonList.size(); index++)
		{
			names[index] = pokemonList.get(index).getName();
		}
		return names;
	}
	
	public boolean isInt(String maybeInt)
	{
		boolean isValid = false;
				
		try
		{
			Integer.parseInt(maybeInt);
			isValid = true;
		}
		catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(null, "You need to type in a number :/");
		}
		return isValid;
	}
	
	public boolean isDouble(String mightbeDouble)
	{
		boolean isValid = false;
		
		try
		{
			Double.parseDouble(mightbeDouble);
			isValid = true;
		}
		catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(null,  "Type in a decimal value aka a number with a . in it");
		}
		
		return isValid;
	

}

	public void savePokedex()
	{
		try
		{
			FileOutputStream saveStream = new FileOutputStream(saveFile);
			ObjectOutputStream output = new ObjectOutputStream(saveStream);
			output.writeObject(pokemonList);
			output.close();
			saveStream.close();
		}
		catch(IOException error)
		{
			JOptionPane.showMessageDialog(appFrame, error.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void loadPokedex()
	{
		try
		{
			ArrayList<Pokemon> saved = new ArrayList<Pokemon>();
			FileInputStream inputStream = new FileInputStream(saveFile);
			ObjectInputStream input = new ObjectInputStream(inputStream);
			saved = (ArrayList<Pokemon>) input.readObject();
			input.close();
			inputStream.close();
			pokemonList = saved;
		}
		catch(IOException error)
		{
			JOptionPane.showMessageDialog(appFrame, "No Save File", "Loading Pokemon", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (ClassNotFoundException pokemonError)
		{
			JOptionPane.showMessageDialog(appFrame, pokemonError.getMessage(), "Type Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
