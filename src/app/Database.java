package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Database {

    private ArrayList<String>[] database;

    String[] columnTitles;
    int largestID = 0;
    int lastID = 0;

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
            File file = new File("Database");
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                rawInputToArray(line);
                // largestID++;
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
        row = row.substring(6);
        String[] values = row.split(",");
        addRow(values);
    }

    public void addRow(String[] data) {
        database[0].add(getStringID(largestID + 1));
        for (int i = 1; i < database.length; i++) {
            database[i].add(data[i - 1]); // Add Data
        }
        largestID++;
    }

    public void updateRow(int id, String[] data) {
        database[0].set(id, getStringID(id));
        for (int i = 1; i < database.length; i++) {
            database[i].set(id, data[i - 1]);
        }
    }

    public String[] getRow(int id) {
        String[] out = new String[database.length];
        for (int i = 0; i < database.length; i++) {
            out[i] = database[i].get(id);
        }
        return out;
    }

    public String getRowString(int id) {
        String out = "";
        String[] arr = getRow(id);
        for (int i = 0; i < database.length; i++) {
            out += arr[i];
            if (!(i == database.length - 1)) {
                out += ",";
            }
        }
        return out;
    }

    public String getStringID(int id) {
        String output = "";

        String out = "" + id;

        int digits = 5 - out.length();
        for (int i = 0; i < digits; i++) {
            output += "0";
        }
        output += out;
        return output;
    }
}