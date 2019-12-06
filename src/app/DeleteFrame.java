package app;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

public class DeleteFrame  extends JFrame {
	private static final long serialVersionUID = 1L;
	public static int ylength = 500;
	public static ArrayList<String> data = new ArrayList <>();
	
	public DeleteFrame(String title) {
		super(title);
		
		
		JButton buttonDelete = new JButton("Delete");
		JButton buttonBack = new JButton("Back");
		JTextField text = new JTextField ("sssss");
		text.setFont(new Font (Font.DIALOG,Font.BOLD,15) );
		Container c = getContentPane();
		JPanel Bpanel = new JPanel ();
		
		Bpanel.setLayout(new GridBagLayout());
		GridBagConstraints overall  = new GridBagConstraints();
		
		overall .fill = GridBagConstraints.HORIZONTAL;
		overall.weightx = 0.5;
		overall .ipady = (int) (ylength*0.3);  
		overall .gridx = 0;
		overall .gridy = 0;
		Bpanel.add(buttonBack, overall);

		overall .fill = GridBagConstraints.HORIZONTAL;
		overall .weightx = 0.5;
		overall .ipady = (int) (ylength*0.3);  
		overall .gridx = 1;
		overall .gridy = 0;
		Bpanel.add(buttonDelete, overall );

		overall .fill = GridBagConstraints.HORIZONTAL;
		overall .ipady = (int) (ylength*0.7);
		overall .weightx = 0.0;
		overall .gridwidth = 2;
		overall .gridx = 0;
		overall .gridy = 1;
		Bpanel.add(text, overall);
		
		c.add(Bpanel);
		
		buttonBack.addActionListener(new ActionListener() {// when the button is pressed close view frame and open the main frame
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.deleteFrame.setVisible(false);
				DatabaseGUI.frame.setVisible(true);
			}
		});
		
		buttonDelete.addActionListener(new ActionListener() {// when the button is pressed close view frame and open the main frame
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					String input = text.getText();
					read();
					if (input.matches("[0-9]") && input.length() <= 5 ) {
						if (search(Integer.parseInt(input)) == true) {
							write();
							MainFrame.deleteFrame.setVisible(false);
							DatabaseGUI.frame.setVisible(true);
						} else {
							text.setText("Input not vaild please try again");
						}
					}else {
						text.setText("Input not vaild please try again");
					}
				} catch (IOException E) {
					System.out.println("**************");
				}
			}
		});
		
	}
	
	public static void read () throws IOException {
        FileReader fr = new FileReader("Database");
		BufferedReader BrDF = new BufferedReader(fr);
		data = new ArrayList <>();
        String temps = BrDF.readLine();
        while(temps != null) {
        	data.add(temps);
        	temps = BrDF.readLine();
        }
        BrDF.close();
	}
	
	public static boolean search (int id) {
		String temps;
        for(int i = 0; i < data.size(); i++) {
        	temps = data.get(i).substring(0, 5);
        	int tempInt = Integer.parseInt(temps);
        	if (tempInt == id) {
        		data.remove(i);
        		i = data.size();
        		return true;
        	}
        }
        return false;
	}
	
	public static void write () throws IOException {
        FileWriter fw = new FileWriter("Database", false);
        BufferedWriter BwDF = new BufferedWriter(fw);
        for(int i = 0; i < data.size(); i++) {
        	if (i == (data.size()-1)) {
        		BwDF.write(data.get(i));
        	}else {
        		BwDF.write(data.get(i) + "\n");
        	}
        }
        BwDF.close();
	}
}
