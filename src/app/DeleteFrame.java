package app;

import java.awt.Container;
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

public class DeleteFrame  extends JFrame {
	private static final long serialVersionUID = 1L;

	public DeleteFrame(String title) {
		super(title);
		
		
		JButton buttonDelete = new JButton("Delete");
		JButton buttonBack = new JButton("Back");
		GridLayout Blayout = new GridLayout(2, 1, 10,10);
		JPanel Bpanel = new JPanel ();
		Bpanel.setLayout(Blayout);
		Bpanel.add(buttonDelete);
		Bpanel.add(buttonBack);
		Container c = getContentPane();
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
					find(12);
				} catch (IOException E) {
					System.out.println("**************");
				}
			}
		});
		
	}
	
	public static void find (int id) throws IOException {
        FileReader fr = new FileReader("Database");
		BufferedReader BrDF = new BufferedReader(fr);
        FileWriter fw = new FileWriter("Database", true);
        BufferedWriter BwDF = new BufferedWriter(fw);
        ArrayList<String> data = new ArrayList <>();
        
        String temps = BrDF.readLine();
        while(temps != null) {
        	data.add(temps);
        	temps = BrDF.readLine();
        }
        BrDF.close();
        for(int i = 0; i < data.size(); i++) {
        	temps = data.get(i).substring(0, 5);
        	int tempInt = Integer.parseInt(temps);
        	if (tempInt == id) {
        		data.remove(i);
        	}
        }
        
        
	}
}
