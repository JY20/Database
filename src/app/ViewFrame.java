package app;

// Import Libraries
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ViewFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	static JButton buttonBack = new JButton("Back");// The back button
	static ArrayList<String> dataAll;// All the data from the text file
	static String[] data; // The 
	public static Container c = new Container(); // The container for the view frame 
	public static JPanel panelInfo = new JPanel(); // Panel for the data or information 

	public ViewFrame(String title) {
		super(title);
		c = getContentPane(); // Set the container to the frame content frame
	}

	public void refresh() { 
		panelInfo = new JPanel(); // Set all the variables to blank
		buttonBack = new JButton("Back");
		dataAll = new ArrayList<>();
		JLabel[][] dataL;
		JScrollPane sTextArea;
		c = new Container();
		c = getContentPane();
		data = new String[] { "ID", "Name", "Age", "Period 1", "Period 2", "Period 3", "Period 4",
		"Period 5" };
		try {// Takes the data from the database to store it in the dataAll
			FileReader viewR = new FileReader("Database");
			BufferedReader viewFile = new BufferedReader(viewR);
			String temps = viewFile.readLine();
			while (temps != null) {
				dataAll.add(temps);
				temps = viewFile.readLine();
			}
			viewR.close();
		} catch (IOException io) {
		}// Set the grid layout
		dataL = new JLabel[dataAll.size() + 1][8];
		GridLayout viewgrid = new GridLayout(dataAll.size() + 2, 8, 1, 1);
		panelInfo.setLayout(viewgrid);
		//Add the information to grid layout  
		for (int i = 0; i < dataAll.size(); i++) {
			if (i == 0) {
			} else {
				data = dataAll.get(i - 1).split(",", 9);
			}
			for (int j = 0; j < 8; j++) {
				dataL[i][j] = new JLabel(data[j], SwingConstants.CENTER);

				dataL[i][j].setFont(dataL[i][j].getFont().deriveFont(15.0f));
				panelInfo.add(dataL[i][j]);
			}
		}
		// Add the scroll bar and the back button to the panel
		sTextArea = new JScrollPane(panelInfo);
		sTextArea.setCorner(JScrollPane.UPPER_RIGHT_CORNER, buttonBack);
		sTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sTextArea.setColumnHeaderView(buttonBack);
		c.add(sTextArea);

		buttonBack.addActionListener(new ActionListener() {// when the button is pressed close view frame and open the main frame
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.viewFrame.setVisible(false);
				DatabaseGUI.frame.setVisible(true);
			}
		});
	}
}
