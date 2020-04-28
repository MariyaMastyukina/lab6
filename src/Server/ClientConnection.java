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
    public ClientConnection(Integer PORT) throws IOException {
        ssc=ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(PORT));
        selector=Selector.open();
        ssc.register(selector,SelectionKey.OP_ACCEPT);
    }
    public Selector getSelector(){
        return selector;
    }
    public void acceptConnection() throws IOException {
        channel=ssc.accept();
        channel.configureBlocking(false);
        channel.register(selector,SelectionKey.OP_WRITE);
    }
    public SocketChannel getChannel(){
        return channel;
    }
}
