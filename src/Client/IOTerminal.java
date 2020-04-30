package Client;

import Client.IOInterface;

import java.io.IOException;
import java.io.*;

public class IOTerminal implements IOInterface {
    private Writer writer;
    private transient InputStream in;
    private transient OutputStream out;
    private transient BufferedReader bufferedReader;
    public IOTerminal(InputStream in, OutputStream out) {
        this.in=in;
        this.out = out;
        writer=new OutputStreamWriter(out);
        bufferedReader = new BufferedReader(new InputStreamReader(in));
    }

    @Override
    public void writeln(String str) throws IOException {
        writer.write(str+"\n");
        writer.flush();
    }

    @Override
    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public boolean hasNextLine() throws IOException {
        return bufferedReader.ready();
    }


    @Override
    public boolean ready() throws IOException {
        return bufferedReader.ready();
    }

    @Override
    public void writeObj(Object obj) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        //записываем сериализованный объект в буфер байтов, отправляем
        ObjectOutputStream oos=new ObjectOutputStream(baos);
        oos.writeObject(obj);
        BufferedOutputStream bos=new BufferedOutputStream(out);
        bos.write(baos.toByteArray(),0,baos.toByteArray().length);
        bos.flush();
        oos.flush();
    }

    @Override
    public Object readObj() throws IOException, ClassNotFoundException {
        //счиытваем сериализованный объект, десериализуем
        ObjectInputStream ois=new ObjectInputStream(in);
        return ois.readObject();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}