package com.jeffrpowell.livevote;

import com.jeffrpowell.livevote.model.Survey;
import com.jeffrpowell.livevote.model.SurveyOption;
import com.jeffrpowell.livevote.surveys.Survey06June;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SurveyHandler {
	private Survey survey;
	private final List<SurveyListener> listeners;
	private static final SurveyHandler INSTANCE = new SurveyHandler();
	
	public static SurveyHandler getInstance()
	{
		return INSTANCE;
	}
	
	private SurveyHandler() {
	    survey = new Survey06June();
	    listeners = new ArrayList<>();
	}
	
	public void resetSurvey()
	{
	    survey = new Survey06June();
	    sendResults();
	}
	
	public void registerSurveyListener(SurveyListener listener)
	{
		listeners.add(listener);
	}
	
	public void newSessionConnected()
	{
		sendResults();
	}

	public Set<SurveyOption> getOptions()
	{
		return survey.getOptions();
	}

	public void castVote(List<SurveyOption> votes)
	{
		survey.castVote(votes);
		sendResults();
	}
	
	private void sendResults()
	{
		for (SurveyListener listener : listeners){
			listener.updateResults(survey.getResults());
		}
	}
}
