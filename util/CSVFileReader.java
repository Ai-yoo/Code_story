package util;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IDEA
 *
 * @author duzhentong
 * @Date 2018/6/24
 * @Time 19:05
 */
public class CSVFileReader {
    public static ArrayList<Node> readFile(){
        BufferedReader reader = null;
        ArrayList<Node> list = new ArrayList<Node>();
        File file = null;
        InputStreamReader isr = null;
        try {
            file = new File("E:\\IntelliJ IDEA\\libsvmtest\\src\\main\\resources\\training-inspur.csv");
            isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            reader = new BufferedReader(isr);
            reader.readLine();
            String line = null;
            while((line=reader.readLine())!=null){
                int begin = line.indexOf(",");
                int end = line.lastIndexOf(",");
                int id = Integer.parseInt(line.substring(0, begin));
                String comment = line.substring(begin + 1, end);
                int classes = Integer.parseInt(line.substring(end + 1, line.length()));
                Node node = new Node(id, comment, classes, null);
                list.add(node);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Node n : list) {
            System.out.println(n.toString());
        }
        return list;
    }


}
