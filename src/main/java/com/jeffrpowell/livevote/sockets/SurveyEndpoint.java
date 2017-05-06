package com.jeffrpowell.livevote.sockets;

import com.jeffrpowell.livevote.JsonUtils;
import com.jeffrpowell.livevote.SurveyHandler;
import com.jeffrpowell.livevote.model.SurveyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		session.getAsyncRemote().sendText(JsonUtils.objectToJson(new ArrayList<>(surveyHandler.getOptions())));
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
		List<SurveyOption> votes = JsonUtils.parseVotes(msg);
		surveyHandler.castVote(votes);
	}
}
