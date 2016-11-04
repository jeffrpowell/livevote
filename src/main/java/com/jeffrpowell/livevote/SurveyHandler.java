package com.jeffrpowell.livevote;

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
		survey = new Survey();
		listeners = new ArrayList<>();
	}
	
	public void resetSurvey()
	{
		survey = new Survey();
		sendResults();
	}
	
	public void registerSurveyListener(SurveyListener listener)
	{
		listeners.add(listener);
	}
	
	public void castVote(Set<Survey.Options> votes)
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
