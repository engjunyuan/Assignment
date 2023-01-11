import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class GivenDatex
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LocalDateTime startDate = LocalDateTime.of(2022, 6, 1, 0, 0,0,0);
        LocalDateTime endDate = LocalDateTime.of(2022, 6, 1, 8, 59,0,0);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String read;
        int jobCreated=0;
        int jobEnded=0;

        try {
            BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\User\\OneDrive - Universiti Malaya\\WIX 1002 Fundamentals of Programming\\Sem 1 Assignment\\extracted_log"));
            int stat0=0, stat1=0, stat2=0,otherstat=0;
            int status;

            while ((read = input.readLine()) != null) {
                String[]arr = read.split(" ");
                String timestampString = read.substring(1, 24);
                LocalDateTime timestamp = LocalDateTime.parse(timestampString, format);

                if (timestamp.isAfter(startDate) && timestamp.isBefore(endDate)) {
                    if (read.contains("sched: Allocate")) {
                        jobCreated++;
                    } //else if (read.contains("_job_complete")&& read.contains("done")) {
                        //jobEnded++;
                    }
                    else if (read.contains("_job_complete")&& read.contains("WEXITSTATUS")) {
                        status = Integer.parseInt(arr[4]);
                    if(status==0)
                        stat0++;
                    else if(status==1)
                        stat1++;
                    else if(status==2)
                        stat2++;
                    else
                        otherstat++;
                }
            }
            input.close();
            System.out.println("Number of jobs created and ended within "+startDate +" - "+ endDate + " are "+ jobCreated + " jobs and " + jobEnded + " jobs respectively.");
            System.out.println("Number of jobs done with WEXITSTATUS 0 is " + stat0 +" jobs");
            System.out.println("Number of jobs done with WEXITSTATUS 1 is " + stat1 +" jobs");
            System.out.println("Number of jobs done with WEXITSTATUS 2 is " + stat2 +" jobs");
            System.out.println("Number of jobs done with WEXITSTATUS other than 0,1 and 2 is " + otherstat +" jobs");

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
