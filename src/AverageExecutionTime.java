import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//Average execution time of the jobs submitted to UMHPC.
public class AverageExecutionTime {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\User\\OneDrive - Universiti Malaya\\WIX 1002 Fundamentals of Programming\\Sem 1 Assignment\\extracted_log"));

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");


            String line;
            while ((line = input.nextLine()) != null) {
                if (line.contains("_slurm_rpc_submit_batch_job")) {
                    String timestampString = line.substring(1, 24);
                    LocalDateTime timestamp = LocalDateTime.parse(timestampString, format);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
