package hust.soict.hedspi.garbage;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {

    public static void main(String[] args) {
        String filename = "D:\\Data\\data_learn\\ki20242\\LapTrinhHuongDoiTuong\\Lab\\OOP_20242\\Lab3\\OtherProjects\\src\\large_text_file.txt";
        byte[] inputBytes = { 0 };
        long startTime, endTime;

        try {
            inputBytes = Files.readAllBytes(Paths.get(filename));
             System.out.println("Read file successfully. Size: " + inputBytes.length + " bytes.");

            startTime = System.currentTimeMillis();
            StringBuilder outputStringBuilder = new StringBuilder(inputBytes.length);
            for (byte b : inputBytes) {
                outputStringBuilder.append((char)b);
            }
            String finalString = outputStringBuilder.toString();
            endTime = System.currentTimeMillis();

            System.out.println("\nNoGarbage (using StringBuilder):");
            System.out.printf("Time taken: %d ms%n", (endTime - startTime));


        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
            System.err.println("Please ensure the file exists and the path is correct.");
            e.printStackTrace();
        }
         catch (OutOfMemoryError e) {
             System.err.println("OutOfMemoryError! The file might be extremely large even for StringBuilder.");
             e.printStackTrace();
        }
    }
}