# lab6
Разделить программу из лабораторной работы №5 на клиентский и серверный модули. Серверный модуль должен осуществлять выполнение команд по управлению коллекцией. Клиентский модуль должен в интерактивном режиме считывать команды, передавать их для выполнения на сервер и выводить результаты выполнения.

*Необходимо выполнить следующие требования:*
- Операции обработки объектов коллекции должны быть реализованы с помощью Stream API с использованием лямбда-выражений.
- Объекты между клиентом и сервером должны передаваться в сериализованном виде.
- Объекты в коллекции, передаваемой клиенту, должны быть отсортированы по названию
- Клиент должен корректно обрабатывать временную недоступность сервера.
- Обмен данными между клиентом и сервером должен осуществляться по протоколу TCP
- Для обмена данными на сервере необходимо использовать сетевой канал
- Для обмена данными на клиенте необходимо использовать потоки ввода-вывода
- Сетевые каналы должны использоваться в неблокирующем режиме.

*Обязанности серверного приложения:*
1. Работа с файлом, хранящим коллекцию.
2. Управление коллекцией объектов.
3. Назначение автоматически генерируемых полей объектов в коллекции.
4. Ожидание подключений и запросов от клиента.
5. Обработка полученных запросов (команд).
6. Сохранение коллекции в файл при завершении работы приложения.
7. Сохранение коллекции в файл при исполнении специальной команды, доступной только серверу (клиент такую команду отправить не может).

*Серверное приложение должно состоять из следующих модулей (реализованных в виде одного или нескольких классов):*
1. Модуль приёма подключений.
2. Модуль чтения запроса.
3. Модуль обработки полученных команд.
4. Модуль отправки ответов клиенту.
5. Сервер должен работать в однопоточном режиме.

*Обязанности клиентского приложения:*
1. Чтение команд из консоли.
2. Валидация вводимых данных.
3. Сериализация введённой команды и её аргументов.
4. Отправка полученной команды и её аргументов на сервер.
5. Обработка ответа от сервера (вывод результата исполнения команды в консоль).
6. Команду save из клиентского приложения необходимо убрать.
7. Команда exit завершает работу клиентского приложения.

**Важно! Команды и их аргументы должны представлять из себя объекты классов. Недопустим обмен "простыми" строками. Так, для команды add или её аналога необходимо сформировать объект, содержащий тип команды и объект, который должен храниться в вашей коллекции.**

*Дополнительное задание:* 
Реализовать логирование различных этапов работы сервера (начало работы, получение нового подключения, получение нового запроса, отправка ответа и т.п.) с помощью Java Util Logging
