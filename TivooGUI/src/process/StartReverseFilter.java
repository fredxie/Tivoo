package process;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 *  Filter Events in the reverse order of event start time
 */
public class StartReverseFilter implements Filter {

	private String myCommandName = "startReverseOrder";

	public String getCommandName() {

		return myCommandName;
	}

	public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters,
			ArrayList<CalendarEvent> events) {

		ReverseOrderFilter myFilter = new ReverseOrderFilter();
		ArrayList<CalendarEvent> eventsToReturn = events;
		Collections.sort(eventsToReturn, myFilter);

		return eventsToReturn;
	}

	private class ReverseOrderFilter implements Comparator<CalendarEvent> {

		public int compare(CalendarEvent o1, CalendarEvent o2) {

			int test = o1.getMyStartTime().compareTo(o2.getMyStartTime());
			if (test > 0)
				return -1;
			else if (test < 0)
				return 1;
			else
				return 0;
		}

	}

}
