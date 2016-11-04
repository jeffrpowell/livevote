package com.jeffrpowell.livevote;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class Survey {
	public static enum Options {
		ONE("1"), TWO("2"), THREE("3");
		public final String text;
		
		Options(String text)
		{
			this.text = text;
		}
		
		public static Options fromText(String text)
		{
			for (Options option : values()){
				if (option.text.equals(text))
				{
					return option;
				}
			}
			return null;
		}
	}
	
	private final Map<Options, Integer> results;

	public Survey(){
		results = new EnumMap(Options.class);
		for (Options option : Options.values()){
			results.put(option, 0);
		}
	}
	
	public void castVote(Set<Options> votes)
	{
		for (Survey.Options vote : votes){
			results.put(vote, results.get(vote) + 1);
		}
	}
	
	public Map<Options, Integer> getResults()
	{
		return results;
	}
}
