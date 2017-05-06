package com.jeffrpowell.livevote.sockets;

import com.google.gson.annotations.Expose;
import com.jeffrpowell.livevote.model.SurveyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SurveyResultsMessage {
	@Expose private final List<SurveyOption> options;
	@Expose private final List<Integer> votes;

	public SurveyResultsMessage(Map<SurveyOption, Integer> results){
		this.options = new ArrayList<>();
		this.votes = new ArrayList<>();
		for (Map.Entry<SurveyOption, Integer> entry : results.entrySet()){
			options.add(entry.getKey());
			votes.add(entry.getValue());
		}
	}
}
