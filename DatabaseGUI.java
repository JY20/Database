import javax.swing.*;
import javax.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;

public class DatabaseGUI {
    static Scanner scan = new Scanner(System.in);
    static FileWriter fw;
    static FileReader fr;
    static BufferedWriter Bw;
    static BufferedReader Br;
    static String tempS = ""; // Temporary string
    static String PreviousS = ""; // Previous line string
    static int ID = 0;// Id of the new line or the old one
    static int idSize = 5;// The size of the id
    static int Options = 0;//
    static int size = 7; // Amount of values
    static String ChangeLine = ""; // line thats getting changed
    static String[] OLineParts = new String[size]; // Each seperate value in new row
    static String[] OriginalParts = new String[size]; // Each seperate value in old row
    static ArrayList<String> OtherLines = new ArrayList<String>(); // All other lines
    static String[] NewInfo = new String[] { "Jimmy Yan", "21", "Math", "Chemistry", "jajja", "Com tech",
            "Com Science" }; // new information
    static String[] Questions = new String[] { "What is your name", "What is your age", "What is your period 1 class",
            "What is your period 2 class", "What is your period 3 class", "What is your period 4 class",
            "What is your period 5 class" }; // questions

    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                JFrame frame = new MainFrame("Database");
                frame.setSize(500, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        /*
        try {
            PreviousS = "";
            setup();
            fr = new FileReader("Database");
            Br = new BufferedReader(fr);
            fw = new FileWriter("Database", true);
            Bw = new BufferedWriter(fw);
            Options = 2;
            if (Options == 2) {
                say("ID");
                String temmmpS = scan.nextLine();
                exceptions(temmmpS);
            } else if (Options == 1) {
                AddInfo();
            }
            say("\nFinish");

        } catch (IOException e) {
            say("&&&&&&&&&&&&&&&&&&&&&");
        }
*/
    }

    public static void say(String x) {
        System.out.println(x);
    }

    public static void say(int x) {
        System.out.println(x);
    }

    public static int id(BufferedReader x) throws IOException {
        String TS = x.readLine();
        String PS = "";
        int id = 0;
        while (TS != null) {
            PS = TS;
            PS = PS.substring(0, idSize);
            if (Integer.parseInt(PS) > id) {
                id = Integer.parseInt(PS);
            }
            TS = x.readLine();
        }
        return id;
    }

    public static void AddInfo() throws IOException {
        ID = id(Br);
        PreviousS += "\n" + String.format("%0" + idSize + "d", (ID + 1));
        for (int i = 0; i < Questions.length; i++) {
            //say(Questions[i]);
            //tempS = scan.nextLine();
            NewInfo[i] = NewInfo[i].replaceAll(",", "");
            if (NewInfo[i] != null) {
                PreviousS += NewInfo[i] + ",";
            }
        }
        Bw.write(PreviousS);
        if (Bw != null) {
            Bw.close();
        }
    }

    public static void Copy(String ID, BufferedReader x) throws IOException {
        String teS = x.readLine();
        String id = teS.substring(0, idSize);
        String PrS = "";
        while (teS != null) {
            PrS = teS;
            id = PrS.substring(0, idSize);
            if (!id.equals(ID)) {
                OtherLines.add(PrS);
            } else {
                ChangeLine = PrS;
            }
            teS = x.readLine();
        }
        x.close();
    }

    public static void parts(BufferedReader x) throws IOException {
        String teS = ChangeLine;
        String PrS = "";
        while (teS != null) {
            PrS = teS;
            int lineSize = PrS.length() - idSize;
            int WP = 0;
            String teteS = "";
            for (int i = 0; i < lineSize; i++) {
                teS = PrS.substring(idSize + i, idSize + (i + 1));
                if (teS.equals(",")) {
                    OLineParts[WP] = teteS;
                    OriginalParts[WP] = teteS;
                    WP++;
                    teteS = "";
                } else {
                    teteS += teS;
                }
            }
            teS = null;
        }
    }

    public static void ChangeInfo(String id, String[] x) throws IOException {
        Copy(id, Br);
        parts(Br);
        String teS = "";
        for (int i = 0; i < OriginalParts.length; i++) {
            say(OriginalParts[i]);
        }
        for (int i = 0; i < x.length; i++) {
            if (!OLineParts[i].equals(x[i])) {
                OLineParts[i] = x[i];
            }
        }
        teS += id;
        for (int i = 0; i < OLineParts.length; i++) {
            teS += OLineParts[i] + ",";
        }
        OtherLines.add(teS);

        fw = new FileWriter("Database", false);
        Bw = new BufferedWriter(fw);

        Bw.write(OtherLines.get(0));
        for (int i = 1; i < OtherLines.size(); i++) {
            Bw.write("\n" + OtherLines.get(i));
        }
        Bw.close();
    }

    public static void setup() {
        OLineParts = new String[size];
        OtherLines = new ArrayList<String>();
    }

    public static void exceptions(String id) throws IOException {
        if (!id.matches("[0-9]")) {
            say("ID not vaild please try again");
            id = scan.nextLine();
            exceptions(id);
        } else {
            ChangeInfo(id, NewInfo);
        }
    }
}