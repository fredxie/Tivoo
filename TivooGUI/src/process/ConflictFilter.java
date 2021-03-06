package process;

import input.CalendarEvent;
import java.util.ArrayList;

import org.joda.time.DateTime;

/*
 *  Filter Events by sorting out events with conflict time frame
 */
public class ConflictFilter implements Filter {
	private String myCommandName = "conflict";

	public String getCommandName() {
		return myCommandName;
	}

	// private String myKeyword;

	public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters,
			ArrayList<CalendarEvent> myEvents) {
		ArrayList<CalendarEvent> myEventsCopy = new ArrayList<CalendarEvent>();

		for (CalendarEvent currentEvent : myEvents) {
			DateTime startDate = currentEvent.getMyStartTime();
			DateTime endDate = currentEvent.getMyEndTime();

			for (CalendarEvent compareEvent : myEvents) {
				if (!compareEvent.equals(currentEvent)) {
					if (compareEvent.isInTimeFrame(startDate, endDate))
						if (!myEventsCopy.contains(compareEvent))
							myEventsCopy.add(compareEvent);
					if (!myEventsCopy.contains(currentEvent))
						myEventsCopy.add(currentEvent);
				}
			}
		}
		return myEventsCopy;
	}

}
