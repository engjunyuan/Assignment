import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Average execution time of the jobs submitted to UMHPC.
public class AverageExecutionTime {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader input = new BufferedReader(new FileReader("Job_Created.txt"));
        BufferedReader read_job_done = new BufferedReader(new FileReader("Job_Completed.txt"));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

        String line, linedone;
        int temp_ID=0, temp_done_ID;
        LocalDateTime initial_time = null;
        LocalDateTime final_time = null;
        Duration duration = Duration.ZERO;
        Duration d_total = Duration.ZERO;
        Duration average_time = Duration.ZERO;

        Pattern pattern = Pattern.compile("(\\[\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\]) _slurm_rpc_submit_batch_job: JobId=(\\d+) InitPrio=(\\d+) usec=(\\d+)");
        int i=0;
        try {

            while ((line = input.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                //String timestampString = line.substring(1, 24);
                //LocalDateTime timestamp = LocalDateTime.parse(timestampString, format);
                if(matcher.find())
                //if(line.contains("_slurm_rpc_submit_batch_job: JobId="))
                    {
                    //initial_time = LocalDateTime.parse(line.substring(1, 24));
                    //temp_ID = Integer.parseInt(line.split(" ")[2].split("=")[1]);
                    initial_time = LocalDateTime.parse((matcher.group(1).substring(1,24)));
                    temp_ID = Integer.parseInt(matcher.group(2));
                    }
               // if (line.contains("_slurm_rpc_submit_batch_job")) {
               //     initial_time = LocalDateTime.parse(line.substring(1, 24));

               //     temp_ID = Integer.parseInt(arr[2].split("=")[1]);
                //    System.out.println(temp_ID);
                    while ((linedone = read_job_done.readLine()) != null) {
                        String timestampStringdone = linedone.substring(1, 24);
                        temp_done_ID = (Integer.parseInt((linedone.split(" ")[2]).split("=")[1]));
                        if (temp_done_ID == temp_ID) {
                            final_time = LocalDateTime.parse(timestampStringdone);
                            i++;
                            break;
                        }
                    }
                read_job_done = new BufferedReader(new FileReader("Job_Completed.txt"));
                //read_job_done.reset();
                if(d_total !=null && initial_time !=null && final_time!=null)
                    d_total = (duration.between(initial_time, final_time)).plus(d_total);
                else
                    d_total = d_total;

                }


            //if(d_total!=null)
            average_time = d_total.dividedBy(i);
            System.out.println("Average execution time of the jobs submitted to UMHPC: " + average_time);
            String isoDuration = String.valueOf(average_time);
            Duration answer = Duration.parse(isoDuration);
            System.out.println("Hours: " + answer.toHours());
            System.out.println("Minutes: " + answer.toMinutes());
            System.out.println("Seconds: " + answer.getSeconds());
            System.out.println("Nanoseconds: " + answer.getNano());
            read_job_done.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

