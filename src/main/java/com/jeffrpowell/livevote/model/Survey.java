package com.jeffrpowell.livevote.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Survey
{
	private final Map<SurveyOption, Integer> results;

	public Survey(SurveyOption... options)
	{
		this.results = Arrays.stream(options)
			.collect(Collectors.toMap(
				option -> option,
				option -> 0
			));
	}

	public Set<SurveyOption> getOptions()
	{
		return results.keySet();
	}
	
	public void castVote(List<SurveyOption> options)
	{
		for (SurveyOption option : options)
		{
			if (results.containsKey(option))
			{
				results.put(option, results.get(option) + 1);
			}
		}
	}
	
	public Map<SurveyOption, Integer> getResults()
	{
		return results;
	}
}
