package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.channels.*;

/**
 * Класс, отвечающий за соединение сервера с клиентом
 */
public class ClientConnection {
    ServerSocketChannel ssc;
    Selector selector;
    SocketChannel channel;
    /**
     * Конструктор соединения с клиентом
     * @param PORT-порт
     */
    public ClientConnection(Integer PORT) throws IOException {
        ssc=ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(PORT));
        selector=Selector.open();
        ssc.register(selector,SelectionKey.OP_ACCEPT);
    }
    /**
     * Функция получения селектора
     * @return селектор
     */
    public Selector getSelector(){
        return selector;
    }
    /**
     * Функция подтверждения соединения с клиентом
     */
    public void acceptConnection() throws IOException {
        channel=ssc.accept();
        channel.configureBlocking(false);
        channel.register(selector,SelectionKey.OP_WRITE);
    }
    /**
     * Функция получения текущего канала
     * @return канал
     */
    public SocketChannel getChannel(){
        return channel;
    }
}
