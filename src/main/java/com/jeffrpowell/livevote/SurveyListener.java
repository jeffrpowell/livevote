package com.jeffrpowell.livevote;

import com.jeffrpowell.livevote.model.SurveyOption;
import java.util.Map;

public interface SurveyListener{
	public void updateResults(Map<SurveyOption, Integer> results);
}
