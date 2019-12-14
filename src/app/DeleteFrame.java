package app;

//Import the libraries
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static int ylength = 500; // The length of the frame
	public static ArrayList<String> data = new ArrayList<>(); // The data from the database
	public static String[] emptyData = new String[DatabaseGUI.newInfo.length];

	public DeleteFrame(String title) {
		super(title);

		for (int i = 0; i < emptyData.length; i++) {
			emptyData[i] = " ";
		}

		JButton buttonDelete = new JButton("Delete"); // Button to delete the data
		JButton buttonBack = new JButton("Back");// Button to go back to the main frame
		JTextField text = new JTextField("ID"); // The area where the user enter the id
		text.setFont(new Font(Font.DIALOG, Font.BOLD, 15));// Change the font
		Container c = getContentPane();// Declare the container
		JPanel Bpanel = new JPanel();// The panel for the buttons and text box

		Bpanel.setLayout(new GridBagLayout()); // Using bag grid layout to make different grid size
		GridBagConstraints overall = new GridBagConstraints();

		overall.fill = GridBagConstraints.HORIZONTAL; // Add the back button to the bag grid layout
		overall.weightx = 0.5;
		overall.ipady = (int) (ylength * 0.3);
		overall.gridx = 0;
		overall.gridy = 0;
		Bpanel.add(buttonBack, overall);

		overall.fill = GridBagConstraints.HORIZONTAL; // Add the delete button to the bag grid layout
		overall.weightx = 0.5;
		overall.ipady = (int) (ylength * 0.3);
		overall.gridx = 1;
		overall.gridy = 0;
		Bpanel.add(buttonDelete, overall);

		overall.fill = GridBagConstraints.HORIZONTAL; // Add the text box to the bag grid layout
		overall.ipady = (int) (ylength * 0.7);
		overall.weightx = 0.0;
		overall.gridwidth = 2;
		overall.gridx = 0;
		overall.gridy = 1;
		Bpanel.add(text, overall);

		c.add(Bpanel); // Add all the components to the frame

		buttonBack.addActionListener(new ActionListener() { // When back button is pressed it returns to main frame
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.deleteFrame.setVisible(false);
				DatabaseGUI.frame.setVisible(true);
			}
		});

		buttonDelete.addActionListener(new ActionListener() { // When delete button is pressed it deletes the data
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String input = text.getText();
					read();
					if (input.length() <= 5) {
						try {
							int tempInt = Integer.parseInt(input);
							if (search(tempInt) == true) {
								write();
								MainFrame.deleteFrame.setVisible(false);
								DatabaseGUI.frame.setVisible(true);
							} else {
								
								text.setText("Input not vaild please try again");;
							}
						} catch (Exception E) {
							text.setText("Input not vaild please try again");
						}
					} else {
						text.setText("Input not vaild please try again");
					}
				} catch (IOException E) {
					System.out.println("**************");
				}
			}
		});

	}

	public static void read() throws IOException { // Read all the data from the file
		FileReader fr = new FileReader("Database");
		BufferedReader BrDF = new BufferedReader(fr);
		data = new ArrayList<>();
		String temps = BrDF.readLine();
		while (temps != null) {
			data.add(temps);
			temps = BrDF.readLine();
		}
		BrDF.close();
	}

	public static boolean search(int id) { // Search through the data to make sure the user inputs in a valid id
		String temps;

		DatabaseGUI.database.updateRow(id, emptyData);
		for (int i = 0; i < data.size(); i++) {
			temps = data.get(i).substring(0, 5);
			int tempInt = Integer.parseInt(temps);
			if (tempInt == id) {
				temps = Integer.toString(id);
				int digits = 5 - temps.length();
				for (int k = 0; k < digits; k++) {
					temps = "0" + temps;
				}
				for (int k = 0; k < 12; k++) {
					temps = temps + ", ";
				}
				data.set(i, temps + ",");
				i = data.size();

				return true;
			}
		}
		return false;
	}

	public static void write() throws IOException { // Rewrite the the new data with the delete/changed line
		FileWriter fw = new FileWriter("Database", false);
		BufferedWriter BwDF = new BufferedWriter(fw);
		for (int i = 0; i < data.size(); i++) {
			if (i == (data.size() - 1)) {
				BwDF.write(data.get(i));
			} else {
				BwDF.write(data.get(i) + "\n");
			}
		}
		BwDF.close();

	}
}
