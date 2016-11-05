package com.jeffrpowell.livevote;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class Survey {
	public static enum Options {
		SPIRITUAL("How can I become spriritually self-reliant?"),
		ANSWERS("How can I find answers to my own gospel questions?"),
		FAITH("How can I overcome doubt with faith?"),
		DECISIONS("How can I learn to make my own decisions?"),
		GOALS("How can setting goals help me become self-reliant?"),
		FINANCIAL("How can I prepare to be financially self-reliant?"),
		WITNESS("How can I stand as a witness of God?"),
		;
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
