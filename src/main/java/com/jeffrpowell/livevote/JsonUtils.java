package com.jeffrpowell.livevote;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jeffrpowell.livevote.sockets.SurveyResultsMessage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

public class JsonUtils {
	private JsonUtils(){}
	public static Map<String, Object> parseMessage(String msgJson)
	{
		Gson gson = new Gson();
		return gson.fromJson(msgJson, new TypeToken<Map<String, Object>>(){}.getType());
	}
	
	public static SurveyResultsMessage parseVotes(String msgJson)
	{
		Gson gson = new Gson();
		return gson.fromJson(msgJson, new TypeToken<SurveyResultsMessage>(){}.getType());
	}
	
	public static <E> String exposedObjectToJson(E obj)
	{
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(obj);
	}
	
	public static <E> String objectToJson(E obj)
	{
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	public static String urlEncode(String str)
	{
		try
		{
			String spaceEscape = "REPLACEWITHSPACE";
			str = str.replace(" ", spaceEscape); //the following encoder mapped " " to "+" instead of "%20"
			str = URLEncoder.encode(str, Charset.defaultCharset().name());
			return str.replace(spaceEscape, "%20");
		}
		catch(UnsupportedEncodingException e){return str;}
	}
}
