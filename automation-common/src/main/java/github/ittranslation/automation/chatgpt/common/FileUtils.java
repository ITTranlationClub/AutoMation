package github.ittranslation.automation.chatgpt.common;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @program: clawerlesson
 * @description: 读写文件工具类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-14 17:40
 **/
public class FileUtils {

    static String htmlSuffix = ".txt";
    static String mdSuffix = ".md";

    static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm-SS");

    public static void writeMDFile(String dirName, String fileName, String content) {
        writeToFile(dirName, fileName + mdSuffix, content);
    }

    public static void writeHTMLFile(String dirName, String fileName, String content) {
        writeToFile(dirName, fileName + htmlSuffix, content);
    }

    public static void writeToFile(String fileName, String content) {
        String time = DATE_FORMAT.format(Calendar.getInstance().getTime());
        writeToFile(time, fileName, content);
    }

    /**
     * 根据 文件夹\文件名\文件内容
     * @param dirName 文件夹名称
     * @param fileName 文件名
     * @param content 文件内容
     */
    public static void writeToFile(String dirName, String fileName, String content) {
        File dirFile = null;
        dirFile = new File("c:\\" + dirName);
        if (!(dirFile.exists()) && !(dirFile.isDirectory())) {
            boolean creatDir = dirFile.mkdirs();
            if (creatDir) {
                System.out.println("creat file " + dirName + fileName + " succeed.");
//                    System.out.println(" ok:创建文件夹成功！ ");
            } else {
                System.out.println("an error occur when creat file "+ dirName + fileName + ".");
//                    System.out.println(" err:创建文件夹失败！ ");
            }
        }
        String fullPath = dirFile + "/" + fileName;
        write(fullPath, content);
    }

    /**
     * 写文件
     * @param path
     * @param content
     */
    public static boolean write(String path, String content) {
        String s = new String();
        String s1 = new String();
        BufferedWriter output = null;
        try {
            File f = new File(path);
            if (f.exists()) {
            } else {
                System.out.println("文件不存在，正在创建...");
                if (f.createNewFile()) {
                    System.out.println("文件创建成功！");
                } else {
                    System.out.println("文件创建失败！");
                }
            }
//            BufferedReader input = new BufferedReader(new FileReader(f));
//            while ((s = input.readLine()) != null) {
//                s1 += s + "\n";
//            }
//            input.close();
            s1 += content;
            output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
