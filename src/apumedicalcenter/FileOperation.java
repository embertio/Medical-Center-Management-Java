/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apumedicalcenter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sheng Jie
 */
public class FileOperation {
    // static helpers (copy your existing implementations here)
    public static String getNextPrimaryKeyFromFile(String fileName, String prefix) {
        int lastPrimaryKey = 0;
        boolean separatorFound = false;

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // here we’re not using separator lines, just check id directly:
                if (line.startsWith(prefix)) {
                    // e.g. "S001|IC|Name|..."
                    String idPart = line.split("\\|")[0]; // "S001"
                    String numberPart = idPart.replaceAll("\\D+", ""); // "001"
                    if (!numberPart.isEmpty()) {
                        int number = Integer.parseInt(numberPart);
                        if (number > lastPrimaryKey) {
                            lastPrimaryKey = number;
                        }
                    }
                }
            }
        } catch (IOException e) {
            // File does not exist or cannot be read — start fresh from 0
        }

        int nextKeyNumber = lastPrimaryKey + 1;
        String formattedNumber = String.format("%03d", nextKeyNumber);
        return prefix + formattedNumber; // e.g., "S001"
    }

    public static String getCurrentPrimaryKeyFromFile(String fileName, String prefix) {
        String nextKey = getNextPrimaryKeyFromFile(fileName, prefix);

        // Extract numeric part
        String numberPart = nextKey.replaceAll("\\D+", ""); // removes non-digits
        int currentNumber = Integer.parseInt(numberPart) - 1;

        if (currentNumber <= 0) {
            currentNumber = 0;
        }

        String formattedNumber = String.format("%03d", currentNumber);
        return prefix + formattedNumber;
    }
    
     public static DefaultTableModel loadFileToTableModel(String fileName) {
        DefaultTableModel model = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                // file empty: create a blank model
                return new DefaultTableModel();
            }

            // first line = header
            String[] columnNames = headerLine.split("\\|");

            // create model with header
            model = new DefaultTableModel(columnNames, 0);

            // add remaining rows as data
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split("\\|");
                model.addRow(data);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new DefaultTableModel();
        }

        return model;
    }

    public static boolean deleteRecordById(String fileName, String primaryKeyValue) {
        List<String> lines = new ArrayList<>();
        boolean objectFound = false;

        // Read all lines
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split by |
                String[] parts = line.split("\\|");
                if (parts.length > 0 && parts[0].equals(primaryKeyValue)) {
                    // This is the record to delete — skip adding
                    objectFound = true;
                } else {
                    lines.add(line); // keep other lines
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }

        // Rewrite file without the deleted record
        if (objectFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
                return false;
            }
        }

        return objectFound;
    }
    
     public static DefaultTableModel loadFilteredTableModelById(String fileName, String id) {
        String[] columns = {"ID", "IC", "Name", "Gender", "DOB", "Contact"}; // adjust columns
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 6 && parts[0].equalsIgnoreCase(id)) {
                    // add this row to model
                    model.addRow(parts);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return model;
    }
     
    public static boolean editObjectById(String fileName, String primaryKeyValue, String newRecord) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(primaryKeyValue + "|")) {
                    // found line with this ID — replace
                    lines.add(newRecord);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // rewrite file with updated content
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
