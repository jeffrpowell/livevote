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
	private static final Map<String, Session> SESSIONS = Collections.synchronizedMap(new HashMap<String, Session>());
	
	public ResultsEndpoint()
	{
		this.surveyHandler = SurveyHandler.getInstance();
		surveyHandler.registerSurveyListener(this);
	}
	
	@OnOpen
	public void onOpen(Session session)
	{
		SESSIONS.put(session.getId(), session);
		surveyHandler.newSessionConnected();
	}
	
	@OnClose
	public void onClose(Session session)
	{
		SESSIONS.remove(session.getId());
	}
	
	@OnMessage
	public void onMessage(Session session, String msg, boolean last)
	{
		surveyHandler.resetSurvey();
	}

	@Override
	public void updateResults(Map<Survey.Options, Integer> results){
		SurveyResultsMessage resultMessage = new SurveyResultsMessage(results);
		for (Session session : SESSIONS.values()){
			session.getAsyncRemote().sendText(JsonUtils.exposedObjectToJson(resultMessage));
		}
	}
}
