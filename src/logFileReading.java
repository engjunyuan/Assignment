import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class logFileReading{
    public static void main(String[] args)
    {
        try {
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\User\\OneDrive - Universiti Malaya\\WIX 1002 Fundamentals of Programming\\Sem 1 Assignment\\extracted_log"));
            String output;
            Pattern pattern = Pattern.compile("done");

            int i = 0;
            while (input.hasNextLine()) {

                output = input.nextLine();
               Matcher matcher;
                matcher = pattern.matcher(output);
                boolean found = matcher.find();
                if (found )
                    i++;
                }
                input.close();
                System.out.println("Number of completed jobs: " + i);
            }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
// System.out.println(output);
               /* Pattern pattern = Pattern.compile("JobId=(\\d+)\\s+NodeList=(\\w+)\\s+#CPUs=(\\d+)");
                Matcher matcher = pattern.matcher(output);
                if (matcher.find()) {
                    int jobId = Integer.parseInt(matcher.group(1));
                    String nodeList = matcher.group(2);
                    int numCPUs = Integer.parseInt(matcher.group(3));
                    // Do something with the extracted information
                    System.out.println("JobId: " + jobId + ", NodeList: " + nodeList + ", #CPUs: " + numCPUs);*/
