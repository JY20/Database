package app;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ViewFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public ViewFrame (String title){
		super(title);
		ArrayList<String>[] data = DatabaseGUI.database.getDatabase();
		JButton buttonBack = new JButton("Back");
		JButton buttonNextP= new JButton("Next Page");
		int size = 10;
		JLabel[] dataL = new JLabel[size];
		int page = 1;

		//ata = DatabaseGUI.database.database;
	    GridLayout viewgrid = new GridLayout(size,7, 30, 1);
	    setLayout(viewgrid);
	    Container c = getContentPane();
	    c.add(buttonBack);
	    for (int i = 0; i < page; i++) {
			for(int j = 0; j < 7; j++) {
				dataL[j] = new JLabel("tw",SwingConstants.CENTER);
				c.add(dataL[j]);
			}
		}
		c.add(buttonNextP);
		
		buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.viewFrame.setVisible(false);
                DatabaseGUI.frame.setVisible(true);
            }
        });
		buttonNextP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.viewFrame.setVisible(false);
                DatabaseGUI.frame.setVisible(true);
            }
        });

	}
}
