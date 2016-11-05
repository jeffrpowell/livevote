package com.jeffrpowell.livevote.sockets;

import com.jeffrpowell.livevote.JsonUtils;
import com.jeffrpowell.livevote.Survey;
import com.jeffrpowell.livevote.SurveyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/survey")
public class SurveyEndpoint{
	private final SurveyHandler surveyHandler;
	private final Map<String, Session> sessions;
	
	public SurveyEndpoint()
	{
		this.surveyHandler = SurveyHandler.getInstance();
		this.sessions = Collections.synchronizedMap(new HashMap<String, Session>());
	}
	
	@OnOpen
	public void onOpen(Session session)
	{
		sessions.put(session.getId(), session);
		session.getAsyncRemote().sendText(JsonUtils.objectToJson(surveyHandler.getOptions()));
	}
	
	@OnClose
	public void onClose(Session session)
	{
		sessions.remove(session.getId());
	}
	
	@OnMessage
	public void onMessage(Session session, String msg, boolean last)
	{
		System.out.println("Message: " + msg);
		Set<Survey.Options> votes = JsonUtils.parseVotes(msg).stream()
			.map(Survey.Options::fromText)
			.collect(Collectors.toSet());
		surveyHandler.castVote(votes);
	}
}
