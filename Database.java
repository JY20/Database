import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Database {

    static ArrayList<String>[] database;

    String[] columnTitles;
    int largestID = 0;

    public Database(String[] _columnTitles) {
        columnTitles = _columnTitles;
        database = new ArrayList[columnTitles.length];
    }

    public void setup() throws IOException { // Initialize and set column titles to top
        for (int i = 0; i < database.length; i++) {
            database[i] = new ArrayList<String>();
            database[i].add(0, columnTitles[i]);
        }
        initializeWithExistingData();
    }

    public void initializeWithExistingData() throws IOException {
        BufferedReader reader = null;

        try {
            File file = new File("DatabaseGUI\\Database");
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                rawInputToArray(line);
                largestID++;
                // System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void rawInputToArray(String row) {
        String[] values = row.split(",");
        addRow(values);
    }

    public void addRow(String[] data) {
        for (int i = 0; i < database.length; i++) {
            if (i >= data.length) {
                database[i].add("");
            } else {
                database[i].add(data[i]);
            }
        }
        largestID++;
    }

    public void updateRow(int id, String[] data) {
        for (int i = 0; i < database.length; i++) {
            if (i >= data.length) {
                database[i].set(id, "");
            } else {
                database[i].set(id, data[i]);
            }
        }
    }

    public void print() {
        for (int i = 0; i < database.length; i++) {
            System.out.println();
        }
    }
}