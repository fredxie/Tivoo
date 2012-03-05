package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


public class Display extends JFrame{
	protected JEditorPane pane;
	
	public Display(String url){
	    pane = new JEditorPane();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pane, BorderLayout.CENTER);
        pane.setEditable(false);
        pane.setPreferredSize(new Dimension(800,600));
        pane.addHyperlinkListener(new LinkFollower());

		this.add(new JScrollPane(pane));
        pack();
        try {
			pane.setPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
        setVisible(true);
	  	 
	}
	
	private void makePageDisplay() {
        // displays the web page
        pane = new JEditorPane();
        pane.setBounds(0,400, 400, 300);
        // allow editor to respond to link-clicks/mouse-overs
        pane.setEditable(false);
        pane.addHyperlinkListener(new LinkFollower());
		this.add(pane);   
	}
	
    private class LinkFollower implements HyperlinkListener {
        public void hyperlinkUpdate (HyperlinkEvent evt) {
            // user clicked a link, load it and show it
            if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            	update(evt.getURL().toString());
            }
        }
    }
        
    private void update (String url) {
    	try {
			pane.setPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
