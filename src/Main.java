
import java.io.*;
/**
 * @author Мастюкина Мария
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        if(filename.length()!=1) {
            try {
                File file = new File(filename);
                if (!file.canRead()) {
                    file.setReadable(true);
                }
                if (!file.canWrite()) {
                    file.setWritable(true);
                }
                if (!file.canExecute()) {
                    file.setExecutable(true);
                }
                Launch pusk = new Launch(file);
                pusk.beginProgramm();
            } catch (FileNotFoundException e) {
                System.out.println("Такого файла не существует");
            }
        }
        else{
            System.out.println("Введите имя файла командной строке");
        }
    }
}

