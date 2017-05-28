package com.jeffrpowell.livevote.surveys;

import com.jeffrpowell.livevote.model.Survey;
import com.jeffrpowell.livevote.model.SurveyOption;

public class Survey06June extends Survey{

	public Survey06June()
	{
		super(
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/priesthood-keys/councils?lang=eng",
				"How can I participate effectively in councils in the Church?",
				"Councils"
			),
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/priesthood-keys/together?lang=eng",
				"How do women and priesthood holders work together to build the kingdom of God?",
				"Together"
			),
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/priesthood-keys/counsel?lang=eng",
				"Why is it important to follow the counsel given by priesthood leaders?",
				"Counsel"
			),
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/priesthood-keys/learn?lang=eng",
				"How can using scripture study skills help me learn more about the priesthood?",
				"Learn"
			),
			new SurveyOption(
				"https://www.lds.org/youth/learn/ss/priesthood-keys/teach?lang=eng",
				"How can I use stories to teach others about the priesthood?",
				"Teach"
			)
		);
	}

}
