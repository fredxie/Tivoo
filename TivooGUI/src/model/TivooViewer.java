package model;

import input.CalendarEvent;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.joda.time.DateTime;

import output.Output;
import output.OutputFactory;
import process.Process;


/*
 * Establish the main choice button window and response to user's clicking. Call Dialogue class
 * if input window is needed.
 */
public class TivooViewer extends JFrame implements ActionListener {
	protected ArrayList<File> files = new ArrayList<File>();
	protected TivooModel myModel;
	protected String myTitle;

	protected JButton loadfile;
	protected JButton reset;
	protected JButton quit;

	protected JButton keyword;// process
	protected JButton removeKeyword;
	protected JButton timeFrame;
	protected JButton conflict;
	protected JButton classSpecific;

	protected JButton endTime;// sort
	protected JButton endTimeReverse;
	protected JButton startTime;
	protected JButton startTimeReverse;
	protected JButton nameOrder;
	protected JButton nameOrderReverse;
		
	protected JButton dayOutput;// output
	protected JButton weekOutput;
	protected JButton monthOutput;
	protected JButton sortOutput;
	
	protected JButton view;//view page

	protected JLabel inputLabel;
	protected JLabel filterLabel;
	protected JLabel sortLabel;
	protected JLabel outputLabel;
	protected JLabel viewerLabel;
	
	protected Container panel;
	protected Container window;
	

	public final static int[] datebefore = new int[3];
	public final static int[] dateafter = new int[3];

	protected static final JFileChooser ourChooser = new JFileChooser(
			"./Resources");

	public TivooViewer(String title, TivooModel model)
			throws NoSuchAlgorithmException, IOException, ParseException {
		this.setModel(model);
		this.setTitle(title);
		this.setSize(500, 600);
		this.setVisible(true);
		this.setLayout(null);

		panel = this.getContentPane();
		panel.setLayout(null);
		myTitle = title;
		
		makeButtons();
		makeLabels();
	}

	private void makeLabels() {

		inputLabel = new JLabel("INPUT");
		inputLabel.setBounds(10, 0, 150, 50);
		panel.add(inputLabel);

		filterLabel = new JLabel("FILTER (PROCESS OPTIONS)");
		filterLabel.setBounds(10, 70, 200, 50);
		panel.add(filterLabel);

		sortLabel = new JLabel("SORT (PROCESS OPTIONS)");
		sortLabel.setBounds(10, 210, 200, 50);
		panel.add(sortLabel);
		
		outputLabel = new JLabel("OUTPUT");
		outputLabel.setBounds(10, 330, 150, 50);
		panel.add(outputLabel);
	}

	protected void makeButtons() {
		loadfile = new JButton("Load File");
		loadfile.setBounds(50, 40, 120, 30);
		panel.add(loadfile);
		loadfile.addActionListener(this);
		
		reset = new JButton("Reset");
		reset.setBounds(190, 40, 120, 30);
		panel.add(reset);
		reset.addActionListener(this);

		quit = new JButton("Quit");
		quit.setBounds(330, 40, 120, 30);
		panel.add(quit);
		quit.addActionListener(this);

		keyword = new JButton("Keyword Filter");
		keyword.setBounds(50, 120, 120, 30);
		panel.add(keyword);
		keyword.addActionListener(this);
		
		removeKeyword = new JButton("Rmv Keyword Filter");
		removeKeyword.setBounds(190, 120, 120, 30);
		panel.add(removeKeyword);
		removeKeyword.addActionListener(this);

		timeFrame = new JButton("Time Filter");
		timeFrame.setBounds(330, 120, 120, 30);
		panel.add(timeFrame);
		timeFrame.addActionListener(this);
		
		classSpecific = new JButton("Class Filter");
		classSpecific.setBounds(50, 170, 120, 30);
		panel.add(classSpecific);
		classSpecific.addActionListener(this);
		
		conflict = new JButton("Conflict Filter");
		conflict.setBounds(190, 170, 120, 30);
		panel.add(conflict);
		conflict.addActionListener(this);
		
		startTime = new JButton("Start Time");
		startTime.setBounds(50, 250, 120, 30);
		panel.add(startTime);
		startTime.addActionListener(this);

		endTime = new JButton("End Time");
		endTime.setBounds(190, 250, 120, 30);
		panel.add(endTime);
		endTime.addActionListener(this);

		nameOrder = new JButton("Name Order");
		nameOrder.setBounds(330, 250, 120, 30);
		panel.add(nameOrder);
		nameOrder.addActionListener(this);

		startTimeReverse = new JButton("Start Time Reverse");
		startTimeReverse.setBounds(50, 300, 120, 30);
		panel.add(startTimeReverse);
		startTimeReverse.addActionListener(this);

		endTimeReverse = new JButton("End Time Reverse");
		endTimeReverse.setBounds(190, 300, 120, 30);
		panel.add(endTimeReverse);
		endTimeReverse.addActionListener(this);

		nameOrderReverse = new JButton("Name Reverse");
		nameOrderReverse.setBounds(330, 300, 120, 30);
		panel.add(nameOrderReverse);
		nameOrderReverse.addActionListener(this);
		
		dayOutput = new JButton("Day Output");
		dayOutput.setBounds(50, 370, 120, 30);
		panel.add(dayOutput);
		dayOutput.addActionListener(this);
		
		weekOutput = new JButton("Week Output");
		weekOutput.setBounds(190, 370, 120, 30);
		panel.add(weekOutput);
		weekOutput.addActionListener(this);
		
		monthOutput = new JButton("Month Output");
		monthOutput.setBounds(330, 370, 120, 30);
		panel.add(monthOutput);
		monthOutput.addActionListener(this);
		
		sortOutput = new JButton("Sort Output");
		sortOutput.setBounds(50, 420, 120, 30);
		panel.add(sortOutput);
		sortOutput.addActionListener(this);
		
		view = new JButton("HTML Display");
		view.setBounds(190, 420, 120, 30);
		panel.add(view);
		view.addActionListener(this);
	}
	
	private void setModel(TivooModel model) {
		myModel = model;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == reset) {
			files.clear();
			myModel = new TivooModel();
			System.out.print("reset done");
		}
		else if (e.getSource() == loadfile) {
			int retval = ourChooser.showOpenDialog(null);
			if (retval == JFileChooser.APPROVE_OPTION) {
				File file = ourChooser.getSelectedFile();
				files.add(file);
				myModel.setCalendar(myModel.parse(files));
			}
		}
		else if (e.getSource() == quit) {
			System.exit(0);
		}
		else if (e.getSource() == keyword) {
			Dialogue filterwindow = new Dialogue(myModel, "keyword");
		}
		else if (e.getSource() == removeKeyword) {
			Dialogue filterwindow = new Dialogue(myModel, "removeKeyword");
		}
		else if (e.getSource() == timeFrame) {
			Dialogue filterwindow = new Dialogue(myModel, "timeFrame");
		}		
		else if (e.getSource() == classSpecific){
			Dialogue filterwindow = new Dialogue(myModel, "classSpecific");
		}
		else if (e.getSource() == conflict){
			try {
				process("conflict");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == startTime){
			try {
				process("startInOrder");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == startTimeReverse){
			try {
				process("startReverseOrder");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == endTime){
			try {
				process("endInOrder");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == endTimeReverse){
			try {
				process("endReverseOrder");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == nameOrder){
			try {
				process("nameOrder");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == nameOrderReverse){
			try {
				process("nameReverseOrder");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}	
		else if (e.getSource() == dayOutput) {
			OutputFactory output = new OutputFactory(myModel.getCalendar());
				output.output("day");
		}
		else if (e.getSource() == weekOutput) {
			OutputFactory output = new OutputFactory(myModel.getCalendar());
				output.output("week");
		}
		else if (e.getSource() == monthOutput) {
			OutputFactory output = new OutputFactory(myModel.getCalendar());
				output.output("month");
		}
		else if (e.getSource() == sortOutput) {
			OutputFactory output = new OutputFactory(myModel.getCalendar());
				output.output("srot");
		}
		else if (e.getSource() == view){

			Display windowViewer = new Display("file:///C:/Users/fred/workspace/TivooGUI/Output/Output.html");
		}
	}

	private void process(String str) throws Exception {
		Process processor = new Process();
		myModel.setCalendar( processor.filter(str, new ArrayList<Object>(), myModel.getCalendar()));
	}
	
	
}
