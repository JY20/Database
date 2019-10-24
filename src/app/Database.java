package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Database {

    static ArrayList<String>[] database;

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
        /// output += ",";
        return output;
    }
}