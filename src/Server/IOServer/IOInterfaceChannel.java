package Server.IOServer;

import java.io.IOException;
/**
 * {@code IOInterfaceChannel} содержит команды канала ввода/вывода
 */
public interface IOInterfaceChannel {
    /**
     * Функция записи строки в канал
     * @param str-строка
     */
    void writeln(String str) throws IOException;
    /**
     * Функция записи сериализованного объекта в канал
     * @param obj-заданный объект
     */
    void writeObj(Object obj) throws IOException;
    /**
     * Функция чтения сериализованного объекта
     * @return  получение сериализованного объекта и его десериализация
     */
    Object readObj() throws IOException, ClassNotFoundException;
    /**
     * Функция закрытия канала
     */
    void close() throws IOException;
}
