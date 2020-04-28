package Server;

import java.io.*;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class IOClient implements IOInterface {
    SocketChannel socketChannel;
    public IOClient(SocketChannel socketChannel){
        this.socketChannel=socketChannel;
    }

    @Override
    public void writeln(String str) throws IOException {
        ByteBuffer byteBuffer=ByteBuffer.wrap((str+"\n").getBytes());
        socketChannel.write(byteBuffer);
    }

    @Override
    public String readLine() throws IOException {
        return null;
    }

    @Override
    public boolean hasNextLine() throws IOException {
        return false;
    }

    @Override
    public boolean ready() throws IOException {
        return false;
    }

    @Override
    public void writeObj(Object obj) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        socketChannel.write(ByteBuffer.wrap(baos.toByteArray()));
    }

    @Override
    public Object readObj() throws IOException, ClassNotFoundException {
        ByteBuffer byteBuffer=ByteBuffer.allocate(5*1024);
        try{
            socketChannel.read(byteBuffer);
            return new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array())).readObject();
        }
        catch(IOException e){
            socketChannel.close();
            throw new ConnectException("Соединение с клиентом разорвано :(");
        }
    }

    @Override
    public void close() throws IOException {

    }
}
