package com.jeffrpowell.livevote;

import java.util.Map;

public interface SurveyListener{
	public void updateResults(Map<Survey.Options, Integer> results);
}
