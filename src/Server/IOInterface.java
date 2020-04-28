package Server;

import java.io.IOException;

public interface IOInterface {
    void writeln(String str) throws IOException;
    String readLine() throws IOException;
    boolean hasNextLine() throws IOException;
    boolean ready() throws IOException;
    void writeObj(Object obj) throws IOException;
    Object readObj() throws IOException, ClassNotFoundException;
    void close() throws IOException;
}
