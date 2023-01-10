import java.util.HashMap;
import java.util.HashSet;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class zhiyangtesting {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<String>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<Data> ls = new ArrayList<Data>();

        String regex = "user='(?<UserName>[A-Za-z0-9._]*)'";
        // String message = "user='ABCDEFG'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
//		System.out.println(matcher.matches());
//		System.out.println(message.matches(regex));
//		System.out.println(matcher.group("UserName"));
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\OneDrive - Universiti Malaya\\WIX 1002 Fundamentals of Programming\\Sem 1 Assignment\\extracted_log"));
            String line = "";
            String name = "";
            while((line = reader.readLine()) != null) {
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    if (set.add(name = matcher.group("UserName"))) {
                        map.put(name, 1);
                    } else {
                        map.put(name, map.get(name) + 1);
                    }
                }
            }
            reader.close();

            for(Map.Entry<String, Integer> entry : map.entrySet())
                ls.add(new Data(entry.getKey(), entry.getValue()));

            Collections.sort(ls);
            int count = 0;

            for(Data dt : ls)
                System.out.printf("%3d | Name: %-15s | Count: %2d\n", ++count, dt.name, dt.count);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class Data implements Comparable<Data> {
    String name;
    int count;

    Data(String name, int count) {
        this.name = name;
        this.count = count;
    }
    @Override
    public int compareTo(Data another) {
        return -1 * (this.count - another.count);
    }
}
