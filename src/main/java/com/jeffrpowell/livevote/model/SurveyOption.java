package com.jeffrpowell.livevote.model;

import com.google.gson.annotations.Expose;
import java.util.Objects;

public class SurveyOption {
	@Expose private final String url;
	@Expose private final String prompt;
	@Expose private final String abbreviation;

	public SurveyOption(String url, String prompt, String abbreviation)
	{
		this.url = url;
		this.prompt = prompt;
		this.abbreviation = abbreviation;
	}

	public String getUrl()
	{
		return url;
	}

	public String getPrompt()
	{
		return prompt;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 59 * hash + Objects.hashCode(this.url);
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final SurveyOption other = (SurveyOption) obj;
		return Objects.equals(this.url, other.url);
	}

	public String getAbbreviation()
	{
		return abbreviation;
	}
	
	
}
