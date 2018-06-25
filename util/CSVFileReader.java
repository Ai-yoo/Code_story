package util;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IDEA
 *
 * @author duzhentong
 * @Date 2018/6/25
 * @Time 9:10
 */
public class CSVFileReader {
    public static void formatFile() {
        String filepath = "D:\\testfiles\\a.txt";
        BufferedReader reader = null;
        ArrayList<Node> list = new ArrayList<Node>();
        File file = null;
        InputStreamReader isr = null;
        String[] str = null;
        FileOutputStream fos = null;
        try {
            file = new File(filepath);
            isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            reader = new BufferedReader(isr);
            String line = null;
            line = reader.readLine();
            str = line.split(".");
            for (String s : str) {
                System.out.println(s);
                char ch = s.charAt(0);
                if (ch == '0') {
                    s.replaceFirst("^0", "\r\n");
                }
            }
            String result = str.toString();
            byte[] bytes = result.getBytes();
            fos = new FileOutputStream(filepath);
            fos.write(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                isr.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        formatFile();
    }

}
