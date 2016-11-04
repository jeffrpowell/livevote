package com.jeffrpowell.livevote.sockets;

import com.google.gson.annotations.Expose;
import com.jeffrpowell.livevote.Survey;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SurveyResultsMessage {
	@Expose private final List<SurveyResult> results;

	public SurveyResultsMessage(Map<Survey.Options, Integer> results){
		this.results = results.entrySet().stream()
			.map(entry -> new SurveyResult(entry.getKey().text, entry.getValue()))
			.collect(Collectors.toList());
	}
	
	public Map<Survey.Options, Integer> getResults()
	{
		return results.stream()
			.collect(Collectors.toMap(
				result -> Survey.Options.fromText(result.option), 
				result -> result.votes
			));
	}
	
	private static class SurveyResult
	{
		@Expose private final String option;
		@Expose private final Integer votes;

		public SurveyResult(String option, Integer votes){
			this.option = option;
			this.votes = votes;
		}
	}
}
