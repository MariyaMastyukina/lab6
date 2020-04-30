package Server.Commands;

import Server.Collection.*;
import Server.IOInterface;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
/**
 * Класс команды update-обновление элемента по id
 */
public class UpdateCommand implements Command {
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public UpdateCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("update",this);
        this.coll=collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param filename- имя файла с командами
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, String filename, List<String> args, IOInterface io) throws IOException {
        try {
            int index = coll.getElementById(Integer.parseInt(option));
            coll.removeElement(index);
            if (args != null) {
                coll.addElement(new City(args));
                io.writeln("Команда update выполнена. Значение элемента коллекции с id " + Integer.parseInt(option) + " обновлено, введите команду \"show\", чтобы увидеть содержимое коллекции");
            } else {
                File file = new File(filename);
                if (file.canRead()) {
                    FileReader filereader = new FileReader(file);
                    Scanner scanner = new Scanner(filereader);
                    String name = scanner.nextLine();
                    args.add(name);
                    Boolean checker1 = true;
                    Float x = 0F;
                    while (checker1) {
                        try {
                            x = Float.parseFloat(scanner.nextLine());
                            if (x > -375F) {
                                checker1 = false;
                            } else {
                                x = 0F;
                                checker1 = false;
                            }
                        } catch (NumberFormatException e) {
                            x = 0F;
                            checker1 = false;
                        }
                    }
                    args.add(x.toString());
                    Boolean checker2 = true;
                    Integer y = 0;
                    while (checker2) {
                        try {
                            y = Integer.parseInt(scanner.nextLine());
                            if (y > -966) {
                                checker2 = false;
                            } else {
                                y = 0;
                                checker2 = false;
                            }
                        } catch (NumberFormatException e) {
                            y = 0;
                            checker2 = false;
                        }
                    }
                    args.add(y.toString());
                    Boolean checker3 = true;
                    Double area = 0.0;
                    while (checker3) {
                        try {
                            area = Double.parseDouble(scanner.nextLine());
                            if (area > 0) {
                                checker3 = false;
                            } else {
                                area = 1.0;
                                checker3 = false;
                            }
                        } catch (NumberFormatException e) {
                            area = 1.0;
                            checker3 = false;
                        }
                    }
                    args.add(area.toString());
                    Boolean checker4 = true;
                    Integer population = 0;
                    while (checker4) {
                        try {
                            population = Integer.parseInt(scanner.nextLine());
                            if (population > 0) {
                                checker4 = false;
                            } else {
                                population = 1;
                                checker4 = false;
                            }
                        } catch (NumberFormatException e) {
                            population = 1;
                            checker4 = false;
                        }
                    }
                    args.add(population.toString());
                    Boolean checker5 = true;
                    Integer metersAboveSeaLevel = 0;
                    while (checker5) {
                        try {
                            metersAboveSeaLevel = Integer.parseInt(scanner.nextLine());
                            checker5 = false;
                        } catch (NumberFormatException e) {
                            metersAboveSeaLevel = 0;
                            checker5 = false;
                        }
                    }
                    args.add(metersAboveSeaLevel.toString());
                    Boolean checker6 = true;
                    Boolean capital = null;
                    while (checker6) {
                        try {
                            String str = scanner.nextLine();
                            if (str.equals("true") || str.equals("false")) {
                                capital = Boolean.parseBoolean(str);
                                checker6 = false;
                            } else {
                                capital = false;
                                checker6 = false;
                            }
                        } catch (NumberFormatException e) {
                            capital = false;
                            checker6 = false;
                        }
                    }
                    args.add(capital.toString());
                    Boolean checker7 = true;
                    Climate climate = null;
                    while (checker7) {
                        String clim = scanner.nextLine();
                        if (clim.toUpperCase().equals("HUMIDCONTINENTAL")) {
                            climate = Climate.HUMIDCONTINENTAL;
                            checker7 = false;
                        } else if (clim.toUpperCase().equals("SUBARCTIC")) {
                            climate = Climate.SUBARCTIC;
                            checker7 = false;
                        } else if (clim.toUpperCase().equals("TUNDRA")) {
                            climate = Climate.TUNDRA;
                            checker7 = false;
                        } else {
                            climate = Climate.HUMIDCONTINENTAL;
                            checker7 = false;
                        }
                    }
                    args.add(climate.toString());
                    Boolean checker8 = true;
                    Government government = null;
                    while (checker8) {
                        String gov = scanner.nextLine();
                        if (gov.toUpperCase().equals("CORPORATOCRACY")) {
                            government = Government.CORPORATOCRACY;
                            checker8 = false;
                        } else if (gov.toUpperCase().equals("MERITOCRACY")) {
                            government = Government.MERITOCRACY;
                            checker8 = false;
                        } else if (gov.toUpperCase().equals("OLIGARCHY")) {
                            government = Government.OLIGARCHY;
                            checker8 = false;
                        } else {
                            government = Government.CORPORATOCRACY;
                            checker8 = false;
                        }
                    }
                    args.add(government.toString());
                    Boolean checker9 = true;
                    String nameGovernor = null;
                    while (checker9) {
                        String line = scanner.nextLine();
                        char[] symbols = line.toLowerCase().toCharArray();
                        Boolean checkernamt1 = true;
                        String validationName = "abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
                        for (char c : symbols) {
                            if (validationName.indexOf(c) == -1) {
                                checkernamt1 = false;
                            }
                        }
                        if (checkernamt1) {
                            checker9 = false;
                            nameGovernor = line;
                        } else {
                            nameGovernor = "";
                            checker9 = false;
                        }
                    }
                    args.add(nameGovernor);
                    coll.addElement(new City(args));
                    io.writeln("Команда update выполнена. Значение элемента коллекции с id " + Integer.parseInt(option) + " обновлено, введите команду \"show\", чтобы увидеть содержимое коллекции");
                }
                else{
                    io.writeln("Команда update не выполнена. Измените права на чтение файла");
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
            if (filename!=null) {
                File file = new File(filename);
                if (file.canRead()) {
                    FileReader filereader = new FileReader(file);
                    Scanner scanner = new Scanner(filereader);
                    for (int i = 0; i < 10; i++) {
                        if (scanner.hasNextLine()) {
                            scanner.nextLine();
                        }
                    }
                    io.writeln("Команда update не выполнена. Не введено id в файле " + filename);
                }
                else
                {
                    io.writeln("Команда update не выполнена. Не введено id в файле " + filename+ " и права файла на чтение не верны");

                }
            }
            else {
                io.writeln("Команда update не выполнена. Введите id");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
