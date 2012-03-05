package model;

import input.CalendarEvent;
import input.Parser;
import input.ParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom.Element;
import org.jdom.JDOMException;


public class Model {
    
	private ArrayList<CalendarEvent> myCalendar;
	
	public void setCalendar(ArrayList<CalendarEvent> cal){
		this.myCalendar = cal;
	}
	
	public ArrayList<CalendarEvent> getCalendar(){
		return myCalendar;
	}
	
	
	public ArrayList<CalendarEvent> parse(ArrayList<File> files) {
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();	
		for (File inputXml : files) {
			try {
				ParserFactory factory = new ParserFactory();
				Parser parser = factory.getParser(inputXml);
				Element rootNode = factory.getMyRoot();

				list = parser.parse(rootNode);
			
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
