package model;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.joda.time.DateTime;
import process.Process;



public class Dialogue extends JFrame implements ActionListener{
	
	protected String keyword;
	protected Container panel;
	protected JButton done;
	protected JTextField text1;
	protected JTextField text2;
	protected Model myModel;
	protected String myType;

	protected int[] startTime = new int[3];
	protected int[] endTime = new int[3];
	
	public Dialogue(Model model,String type){ 
	   this.setModel(model);
	   this.setType(type);
	   this.setTitle("Filter Window");
  	   this.setSize(400,200); 
  	   this.setVisible(true); 
  	   this.setLayout(null);
  	   panel = this.getContentPane();
	   panel.setLayout(null);
  	   makeLabel();
  	   makeText();
  	   makeButton();
    }

	private void setType(String myType) {
		this.myType = myType;
	}
	
	private void setModel(Model model) {
		this.myModel = model;
	}

	public String getKeywords(){
		return keyword;
	}

	private void makeLabel() {
		if(myType.equals("keyword")){
		JLabel label = new JLabel("Please input keyword: ");
		label.setBounds(20, 10, 400, 20);
		panel.add(label);
		}
		else if(myType.equals("removeKeyword")){
			JLabel label = new JLabel("Please input removing keyword: ");
			label.setBounds(20, 10, 400, 20);
			panel.add(label);
			}
		else if(myType.equals("timeFrame")){
			JLabel label1 = new JLabel("Please type in Start Time: yyyy-mm-dd ");
			label1.setBounds(20, 10, 400, 20);
			panel.add(label1);
			
			JLabel label2 = new JLabel("Please type in End Time: yyyy-mm-dd ");
			label2.setBounds(20, 80, 400, 20);
			panel.add(label2);
		}
		else if(myType.equals("classSpecific")){
			JLabel label1 = new JLabel("Please type in actor/actress name (TV available only): ");
			label1.setBounds(20, 10, 400, 20);
			panel.add(label1);
		}
	}

	private void makeText() {
	    text1 = new JTextField("");
	    text1.setBounds(20, 40, 200, 30);
	    panel.add(text1);
	    
		text2 = new JTextField("");
	    text2.setBounds(20,100, 200, 30);
		panel.add(text2);
	}
	
	private void makeButton() {
		done = new JButton("Done");
		done.setBounds(250,100,100,30);
		done.addActionListener(this);
		panel.add(done);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == done){	 
			if(myType.equals("keyword")){
			try {
				filterKeyword("keyword");
			} catch (Exception e1) {
				e1.printStackTrace();
				}
			}
			else if(myType.equals("removeKeyword")){
			try {
				filterKeyword("removeKeyword");
			} catch (Exception e1) {
				e1.printStackTrace();
				}
			}
			else if (myType.equals("timeFrame")){
			try {
				filterTime();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			} 
			else if (myType.equals("classSpecific")){
		    try {
				filterClass();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			dispose();
		}
	}
	
	private void filterKeyword(String str) throws Exception {
		Process processor = new Process();
		ArrayList<Object> l = new ArrayList<Object>();
		l.add(text1.getText());
		myModel.setCalendar(processor.filter(str, l, myModel.getCalendar()));
	}   

	private void filterTime() throws Exception {
		String[] st =text1.getText().split("-");
		String[] et =text2.getText().split("-");
		startTime[0] = Integer.parseInt(st[0]); 
		startTime[1] = Integer.parseInt(st[1])-1; 
		startTime[2] = Integer.parseInt(st[2]); 
		endTime[0] = Integer.parseInt(et[0]); 
		endTime[1] = Integer.parseInt(et[1])-1; 
		endTime[2] = Integer.parseInt(et[2]); 
		
		Process processor = new Process();
		ArrayList<Object> l = new ArrayList<Object>();
		l.add(new DateTime(startTime[0], startTime[1], startTime[2], 0, 0));
		l.add(new DateTime(endTime[0], endTime[1], endTime[2], 0, 0));
		myModel.setCalendar(processor.filter("timeFrame", l, myModel.getCalendar()));
	}
    

	private void filterClass() throws Exception {
		Process processor = new Process();
		ArrayList<Object> l = new ArrayList<Object>();
		l.add(text1.getText());
		myModel.setCalendar(processor.filter("classSpecific", l, myModel.getCalendar()));

	}

}
