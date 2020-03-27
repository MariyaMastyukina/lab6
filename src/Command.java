

import java.io.IOException;
import java.util.Scanner;
/**
 * {@code Command} содержит команду выполнения команд
 */
public interface Command {
    void execute(String option, Scanner scanner,String type) throws IOException;
}
