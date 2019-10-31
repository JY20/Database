package app;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ViewFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public ViewFrame (String title){
		super(title);
		ArrayList<String>[] data;
		JButton buttonBack = new JButton("Back");

		//ata = DatabaseGUI.database.database;
	    GridLayout viewgrid = new GridLayout(5,5, 30, 10);
	    setLayout(viewgrid);
	        
	    Container c = getContentPane();
		c.add(buttonBack);
		buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.viewFrame.setVisible(false);
                DatabaseGUI.frame.setVisible(true);
            }
        });

	}
}
