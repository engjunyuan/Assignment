import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class GivenDatex
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LocalDateTime startDate = LocalDateTime.of(2022, 6, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 6, 1, 8, 59);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String read;
        int jobCreated=0;
        int jobEnded=0;

        try {
            BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\User\\OneDrive - Universiti Malaya\\WIX 1002 Fundamentals of Programming\\Sem 1 Assignment\\extracted_log"));
            int stat0=0, stat1=0, stat2=0;
            int status;

            while ((read = input.readLine()) != null) {
                String[]line = read.split(" ");
                String timestampString = read.substring(1, 24);
                LocalDateTime timestamp = LocalDateTime.parse(timestampString, format);

                if (timestamp.isAfter(startDate) && timestamp.isBefore(endDate)) {
                    if (read.contains("sched: Allocate")) {
                        jobCreated++;
                    } //else if (read.contains("_job_complete")&& read.contains("done")) {
                        //jobEnded++;
                    }
                    else if (read.contains("_job_complete")&& read.contains("WEXITSTATUS")) {
                        status = Integer.parseInt(Arrays.toString(line[3].split(" ")));
                }
            }
            input.close();
            System.out.println(jobEnded +" "+ jobCreated);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
