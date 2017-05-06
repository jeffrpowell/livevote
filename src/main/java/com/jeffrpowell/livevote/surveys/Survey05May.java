package com.jeffrpowell.livevote.surveys;

import com.jeffrpowell.livevote.model.Survey;
import com.jeffrpowell.livevote.model.SurveyOption;

public class Survey05May extends Survey{

	public Survey05May()
	{
		super(
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/prophets-revelation/living?lang=eng",
				"What can I learn from living prophets and apostles?",
				"Living"
			),
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/prophets-revelation/scripture?lang=eng",
				"How can I improve my scripture study?",
				"Study"
			),
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/prophets-revelation/reverent?lang=eng",
				"How does reverence help me receive revelation?",
				"Reverence"
			),
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/prophets-revelation/testimony?lang=eng",
				"What does it mean to bear testimony?",
				"Testimony"
			),
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/prophets-revelation/more?lang=eng",
				"What can I learn from President Monson about following the Spirit?",
				"Spirit"
			)
		);
	}

}
