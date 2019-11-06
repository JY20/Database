package app;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ViewFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public ViewFrame (String title) {
		super(title);
		JPanel panelInfo = new JPanel();
		JButton buttonBack = new JButton("Back");
		JButton buttonNextP= new JButton("Next");
		JButton buttonPreviousP= new JButton("Previous");
		int size = 10;
		ArrayList <String>dataAll = new ArrayList<>();
		String[] data = new String []{"ID", "Name", "Age", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", ""};
		JLabel[][] dataL = new JLabel[size][8];
		JLabel [] L = null;
		int page = 1;
		try {
		FileReader viewR = new FileReader("Database");
		BufferedReader viewFile = new BufferedReader(viewR);
		String temps = viewFile.readLine();
		while(temps != null) {
			dataAll.add(temps);
			temps = viewFile.readLine();
		}
		viewR.close();
		} catch(IOException io) {
			
		}
	    GridLayout viewgrid = new GridLayout(size+2, 8, 10, 1);
	    panelInfo.setLayout(viewgrid);
	    Container c = getContentPane();
	    for(int i = 0; i < 8; i++) {
	    	dataL[0][i] = new JLabel(data[i],SwingConstants.CENTER);
    		dataL[0][i].setFont(dataL[0][i].getFont ().deriveFont (12.0f));
    		panelInfo.add(dataL[0][i]);
	    }
	    for (int i = (page*10)-10; i < page*size; i++) {
	    	data = dataAll.get(i).split(",", 9);
	    	for(int j = 0; j < 8; j++) {
	    		dataL[i][j] = new JLabel(data[j],SwingConstants.CENTER);
	    		dataL[i][j].setFont(dataL[i][j].getFont ().deriveFont (12.0f));
	    		panelInfo.add(dataL[i][j]);
	    	}
		}
	    for (int i = 0; i < 8; i++) {
	    	if(i == 1) {
	    		panelInfo.add(buttonBack);
	    	} else if (i == 5) {
	    		panelInfo.add(buttonPreviousP);
	    	} else if (i == 6) {
	    		panelInfo.add(buttonNextP);
	    	}else {
	    		dataL[0][0] = new JLabel("",SwingConstants.CENTER);
	    		panelInfo.add(dataL[0][0]);
	    	}
	    }
	    c.add(panelInfo);
		
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
		buttonPreviousP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.viewFrame.setVisible(false);
                DatabaseGUI.frame.setVisible(true);
            }
        });
	}
}
