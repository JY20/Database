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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ViewFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public ViewFrame (String title) {
		super(title);
		JPanel panelInfo = new JPanel();
		JButton buttonBack = new JButton("Back");
		int size = 10;
		ArrayList <String>dataAll = new ArrayList<>();
		String[] data = new String []{"ID", "Name", "Age", "Period 1", "Period 2", "Period 3", "Period 4", "Period 5"};
		JLabel[][] dataL;
		JLabel [] L = null;
	    JTextArea textArea = new JTextArea(20, 20);  
	    JScrollPane sTextArea;
	    
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
		dataL = new JLabel[dataAll.size()][8];
		
	    GridLayout viewgrid = new GridLayout(dataAll.size(), 8, 10, 1);
	    panelInfo.setLayout(viewgrid);
	    
	    Container c = getContentPane();
	    /*for(int i = 0; i < 9; i++) {
	    	dataL[0][i] = new JLabel(data[i],SwingConstants.CENTER);
    		dataL[0][i].setFont(dataL[0][i].getFont ().deriveFont (12.0f));
    		panelInfo.add(dataL[0][i]);
	    }*/
	    for (int i = 0; i < dataAll.size(); i++) {
	    	data = dataAll.get(i).split(",", 9);
	    	for(int j = 0; j < 8; j++) {
	    		dataL[i][j] = new JLabel(data[j],SwingConstants.CENTER);
	    		dataL[i][j].setFont(dataL[i][j].getFont ().deriveFont (12.0f));
	    		panelInfo.add(dataL[i][j]);
	    	}
		}
	    /*for (int i = 0; i < 8; i++) {
	    	if(i == 1) {
	    		panelInfo.add(buttonBack);
	    	}else {
	    		dataL[0][0] = new JLabel("",SwingConstants.CENTER);
	    		panelInfo.add(dataL[0][0]);
	    	}
	    }*/
	    sTextArea = new JScrollPane(panelInfo); 
	    sTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
	    c.add(sTextArea);
	    
		buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.viewFrame.setVisible(false);
                DatabaseGUI.frame.setVisible(true);
            }
        });
	}

}
