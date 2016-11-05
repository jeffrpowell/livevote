package com.jeffrpowell.livevote.sockets;

import com.google.gson.annotations.Expose;
import com.jeffrpowell.livevote.Survey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SurveyResultsMessage {
	private final List<SurveyResult> results;
	@Expose private final List<Survey.Options> options;
	@Expose private final List<Integer> votes;

	public SurveyResultsMessage(Map<Survey.Options, Integer> results){
		this.results = results.entrySet().stream()
			.map(entry -> new SurveyResult(entry.getKey().text, entry.getValue()))
			.collect(Collectors.toList());
		this.options = new ArrayList<>();
		this.votes = new ArrayList<>();
		for (Map.Entry<Survey.Options, Integer> entry : results.entrySet()){
			options.add(entry.getKey());
			votes.add(entry.getValue());
		}
	}
	
	public Map<Survey.Options, Integer> getResults()
	{
		return results.stream()
			.collect(Collectors.toMap(
				result -> Survey.Options.fromText(result.option), 
				result -> result.vote
			));
	}
	
	private static class SurveyResult
	{
		@Expose private final String option;
		@Expose private final Integer vote;

		public SurveyResult(String option, Integer votes){
			this.option = option;
			this.vote = votes;
		}
	}
}
