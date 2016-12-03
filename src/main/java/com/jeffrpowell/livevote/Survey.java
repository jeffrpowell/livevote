package com.jeffrpowell.livevote;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class Survey {
	public static enum Options {
		SERVE("How can I learn to serve more effectively in the Church?"),
		LEADER ("How can I become a better leader?"),
		TEACHER("How can I become a better teacher?"),
		SHARE("What are effective ways to share the gospel with others?"),
		SYMBOLS("How can I understand the symbols used to teach about the Second Coming?"),
		PREPARE("What can I learn from the scriptures to help me prepare for the Second Coming?"),
		TESTIMONY("How can I share my testimony that Jesus Christ is the Son of God?")
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
