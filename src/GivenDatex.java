import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class GivenDatex
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("PLease input you desired time range in the format of \"yyyy-MM-dd'T'HH:mm:ss.SSS\"");
        System.out.print("Start Time: ");
        String[]startinput = sc.nextLine().split("-");
        int[] x = new int[startinput.length];
        for (int i = 0; i < startinput.length; i++)
            x[i] = Integer.parseInt(startinput[i]);

        System.out.print("End Date: ");
       String[]endinput = sc.next().split("-");   //2022, 6, 1, 8, 59,0,0
        int[] y = new int[endinput.length];
        for (int i = 0; i < endinput.length; i++)
            y[i] = Integer.parseInt(endinput[i]);
// 2022, 6, 1, 0, 00,0,0
        // 2022, 8, 1, 23, 59,0,0
        LocalDateTime startDate = LocalDateTime.of(x[0], x[1], x[2], x[3], x[4],x[5],x[6]);
        LocalDateTime endDate = LocalDateTime.of(y[0], y[1], y[2], y[3], y[4],y[5],y[6]);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String read;
        int jobCreated=0;
        int jobEnded=0;

        try {
            BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\User\\OneDrive - Universiti Malaya\\WIX 1002 Fundamentals of Programming\\Sem 1 Assignment\\extracted_log"));
            int stat0 = 0, stat1 = 0, stat2 = 0, otherstat = 0;
            int status;

            while ((read = input.readLine()) != null) {
                String[] arr = read.split(" ");
                String timestampString = read.substring(1, 24);
                LocalDateTime timestamp = LocalDateTime.parse(timestampString, format);

                if (timestamp.isAfter(startDate) && timestamp.isBefore(endDate)) {
                    if (read.contains("sched: Allocate")) {
                        jobCreated++;
                    } else if (read.contains("_job_complete") && read.contains("done")) {
                        jobEnded++;
                    } else if (read.contains("_job_complete") && read.contains("WEXITSTATUS")) {
                        status = Integer.parseInt(arr[4]);
                        if (status == 0)
                            stat0++;
                        else if (status == 1)
                            stat1++;
                        else if (status == 2)
                            stat2++;
                        else
                            otherstat++;
                    }
                }
            }
            input.close();
            System.out.println("Number of jobs created and ended within " + startDate + " - " + endDate + " are " + jobCreated + " jobs and " + jobEnded + " jobs respectively.");
            System.out.println("Number of jobs done with WEXITSTATUS 0 is " + stat0 + " jobs");
            System.out.println("Number of jobs done with WEXITSTATUS 1 is " + stat1 + " jobs");
            System.out.println("Number of jobs done with WEXITSTATUS 2 is " + stat2 + " jobs");
            System.out.println("Number of jobs done with WEXITSTATUS other than 0,1 and 2 is " + otherstat + " jobs");
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
