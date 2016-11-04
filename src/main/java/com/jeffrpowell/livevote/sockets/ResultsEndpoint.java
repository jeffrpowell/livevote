package com.jeffrpowell.livevote.sockets;

import com.jeffrpowell.livevote.JsonUtils;
import com.jeffrpowell.livevote.Survey;
import com.jeffrpowell.livevote.SurveyHandler;
import com.jeffrpowell.livevote.SurveyListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/results")
public class ResultsEndpoint implements SurveyListener{
	private final SurveyHandler surveyHandler;
	private final Map<String, Session> sessions;
	
	public ResultsEndpoint()
	{
		this.surveyHandler = SurveyHandler.getInstance();
		surveyHandler.registerSurveyListener(this);
		this.sessions = Collections.synchronizedMap(new HashMap<String, Session>());
	}
	
	@OnOpen
	public void onOpen(Session session)
	{
		sessions.put(session.getId(), session);
	}
	
	@OnClose
	public void onClose(Session session)
	{
		sessions.remove(session.getId());
	}
	
	@OnMessage
	public void onMessage(Session session, String msg, boolean last)
	{
		surveyHandler.resetSurvey();
	}

	@Override
	public void updateResults(Map<Survey.Options, Integer> results){
		SurveyResultsMessage resultsMessage = new SurveyResultsMessage(results);
		for (Session session : sessions.values()){
			session.getAsyncRemote().sendText(JsonUtils.exposedObjectToJson(resultsMessage));
		}
	}
}
