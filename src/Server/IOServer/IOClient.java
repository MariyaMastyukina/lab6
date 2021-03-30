package Server.IOServer;

import CollectWorker;
import Server.Launch.ControlUnit;
import Server.Launch.Launch.CollectWorker;

import java.io.*;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Класс, работающтй с каналом ввода/вывода
 */
public class IOClient implements IOInterfaceChannel {
    SocketChannel socketChannel;
    public IOClient(SocketChannel socketChannel){
        this.socketChannel=socketChannel;
    }
    /**
     * Функция записи строки в в буфер
     * @param str-строка
     */
    @Override
    public void writeln(String str) throws IOException {
        ByteBuffer byteBuffer=ByteBuffer.wrap((str+"\n").getBytes());
        socketChannel.write(byteBuffer);
    }
    /**
     * Функция записи сериализованного объекта в канал
     * @param obj-заданный объект
     */
    @Override
    public void writeObj(Object obj) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        socketChannel.write(ByteBuffer.wrap(baos.toByteArray()));
        //Обертываем байтовый массив в буфер.
    }
    /**
     * Функция чтения сериализованного объекта
     * @return  получение сериализованного объекта и его десериализация
     */
    @Override
    public Object readObj() throws IOException, ClassNotFoundException {
        //Выделяем новый буфер байта
        ByteBuffer byteBuffer=ByteBuffer.allocate(5*1024);
        try{
            socketChannel.read(byteBuffer);
            return new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array())).readObject();
        }
        catch(IOException e){
            socketChannel.close();
            new CollectWorker().clearList();
            new ControlUnit().clearListCommand();
            throw new ConnectException("Соединение с клиентом разорвано :(");

        }
    }
    /**
     * Функция закрытия канала
     */
    @Override
    public void close() throws IOException {
        socketChannel.close();
    }
}
