

import java.util.Scanner;
/**
 * Класс команды add-добавление объекта в коллекцию
 */
public class AddCommand implements Command {
    CollectWorker coll;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param p-          переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public AddCommand(ControlUnit p, CollectWorker collection) {
        p.addCommand("add", this);
        this.coll = collection;
    }

    /**
     * Функция выполнения команды
     *
     * @param option-  параметр команды
     * @param scanner- поток
     * @param type     -тип потока
     */
    @Override
    public void execute(String option, Scanner scanner, String type) {
        System.out.println("Выполняется команда добавления объекта.\n" + "Чтобы добавить объект в коллекцию, следуйте инструкции ниже:");
        System.out.println("Введите название города. Пример: Москва");
        String name = scanner.nextLine();
        System.out.println("Введите координату города X(число должно быть больше -375). Пример: 9000000.75");
        Boolean checker1 = true;
        Float x = 0F;
        while (checker1) {
            try {
                x = Float.parseFloat(scanner.nextLine());
                if (x > -375F) {
                    checker1 = false;
                } else {
                    if (type.equals("console")) {
                        System.out.println("Значение не может быть меньше или равно -375, введите координату города X еще раз");
                    }
                    if (type.equals("file")) {
                        System.out.println("Значение не может быть меньше или равно -375, координата города X по умолчанию устанавливается 0");
                        x = 0F;
                        checker1 = false;
                    }
                }
            } catch (NumberFormatException e) {
                if (type.equals("console")) {
                    System.out.println("Формат введенного значения неверный, введите значение еще раз");
                }
                if (type.equals("file")) {
                    System.out.println("Формат введенного значения неверный, координата Х по умолчанию устанавливается 0");
                    x = 0F;
                    checker1 = false;
                }
            }
        }
            System.out.println("Введите координату города Y(число должно быть больше -966). Пример: 8000000");
            Boolean checker2 = true;
            Integer y = 0;
            while (checker2) {
                try {
                    y = Integer.parseInt(scanner.nextLine());
                    if (y > -966) {
                        checker2 = false;
                    } else {
                        if (type.equals("console")){
                            System.out.println("Значение не может быть меньше или равно -966, введите координату города Y еще раз");
                        }
                        if(type.equals("file")){
                            System.out.println("Значение не может быть меньше или равно -966, координата города Y по умолчанию устанавливается 0");
                            y=0;
                            checker2=false;
                        }
                    }
                } catch (NumberFormatException e) {
                    if (type.equals("console")){
                        System.out.println("Формат введенного значения неверный, введите значение еще раз");
                    }
                    if(type.equals("file")){
                        System.out.println("Формат введенного значения неверный, координата Y по умолчанию устанавливается 0");
                        y=0;
                        checker2=false;
                    }
                }
            }
            System.out.println("Введите площадь города(число должно быть больше 0). Пример: 900.8");
            Boolean checker3 = true;
            Double area = 0.0;
            while (checker3) {
                try {
                    area = Double.parseDouble(scanner.nextLine());
                    if (area > 0) {
                        checker3 = false;
                    } else {
                        if (type.equals("console")){
                            System.out.println("Значение не может быть отрицательным или равным 0, введите площадь города еще раз");
                        }
                        if(type.equals("file")){
                            System.out.println("Значение не может быть отрицательным или равным 0, площадь города по умолчанию устанавливается 1.0");
                            area=1.0;
                            checker3=false;
                        }
                    }
                } catch (NumberFormatException e) {
                    if (type.equals("console")){
                        System.out.println("Формат введенного значения неверный, введите значение еще раз");
                    }
                    if(type.equals("file")){
                        System.out.println("Формат введенного значения неверный, площадь города по умолчанию устанавливается 1.0");
                        area=1.0;
                        checker3=false;
                    }
                }
            }
            System.out.println("Введите население города(число должно быть больше 0). Пример: 10000000");
            Boolean checker4 = true;
            Integer population = 0;
            while (checker4) {
                try {
                    population = Integer.parseInt(scanner.nextLine());
                    if (population > 0) {
                        checker4 = false;
                    } else {
                        if (type.equals("console")){
                            System.out.println("Значение не может быть отрицательным или равным 0, введите население города еще раз");
                        }
                        if(type.equals("file")){
                            System.out.println("Значение не может быть отрицательным или равным 0, население города по умолчанию устанавливается 1");
                            population=1;
                            checker4=false;
                        }
                    }
                } catch (NumberFormatException e) {
                    if (type.equals("console")){
                        System.out.println("Формат введенного значения неверный, введите значение еще раз");
                    }
                    if(type.equals("file")){
                        System.out.println("Формат введенного значения неверный, население города по умолчанию устанавливается 1");
                        population=1;
                        checker4=false;
                    }
                }
            }
            System.out.println("Введите на скольких метрах над уровнем моря находится город. Пример: -180");
            Boolean checker5 = true;
            Integer metersAboveSeaLevel = 0;
            while (checker5) {
                try {
                    metersAboveSeaLevel = Integer.parseInt(scanner.nextLine());
                    checker5 = false;
                } catch (NumberFormatException e) {
                    if (type.equals("console")){
                        System.out.println("Формат введенного значения неверный, введите значение еще раз");
                    }
                    if(type.equals("file")){
                        System.out.println("Формат введенного значения неверный, значению метров над уровнем моря по умолчанию устанавливается 0");
                        metersAboveSeaLevel=0;
                        checker5=false;
                    }
                }
            }
            System.out.println("Введите true, если город является столицей, иначе введите false");
            Boolean checker6 = true;
            Boolean capital = null;
            while (checker6) {
                try {
                    String str = scanner.nextLine();
                    if (str.equals("true") || str.equals("false")) {
                        capital = Boolean.parseBoolean(str);
                        checker6 = false;
                    } else {
                        if (type.equals("console")){
                            System.out.println("Введенное значение некорректно, введите значение еще раз");
                        }
                        if(type.equals("file")){
                            System.out.println("Введенное значение некорректно, значение capital по умолчанию устанавливается false");
                            capital=false;
                            checker6=false;
                        }
                    }
                } catch (NumberFormatException e) {
                    if (type.equals("console")){
                        System.out.println("Формат введенного значения неверный, введите значение еще раз");
                    }
                    if(type.equals("file")){
                        System.out.println("Формат введенного значения неверный, значение capital по умолчанию устанавливается false");
                        capital=false;
                        checker6=false;
                    }
                }
            }
            System.out.println("Введите климат города. Выберете из этого: \n" + "HUMIDCONTINENTAL\n" + "SUBARCTIC\n" + "TUNDRA");
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
                    if (type.equals("console")){
                        System.out.println("Введенное значение некорректно, введите климат города еще раз");
                    }
                    if(type.equals("file")){
                        System.out.println("Введенное значение некорректно, климат по умолчанию устанавливается HUMIDCONTINENTAL");
                        climate=Climate.HUMIDCONTINENTAL;
                        checker7=false;
                    }
                }
            }
            System.out.println("Введите правительство города. Выберите из этого: \n" + "CORPORATOCRACY\n" + "MERITOCRACY\n" + "OLIGARCHY");
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
                    if (type.equals("console")){
                        System.out.println("Введенное значение некорректно, введите правительство города еще раз");
                    }
                    if(type.equals("file")){
                        System.out.println("Введенное значение некорректно, правительство города по умолчанию устанавливается CORPORATOCRACY");
                        government = Government.CORPORATOCRACY;
                        checker8=false;
                    }
                }
            }
            System.out.println("Введите фамилию губернатора города. Пример: Собянин");
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
                    if (type.equals("console")){
                        System.out.println("Введенное имя записано некорректно. Введите имя еще раз");
                    }
                    if(type.equals("file")){
                        System.out.println("Введенное имя записано некорректно. Имя не вводится");
                        nameGovernor="";
                        checker9=false;
                    }
                }
            }
            coll.addElement(new City(name, new Coordinates(x, y), area, population, metersAboveSeaLevel, capital, climate, government, new Human(nameGovernor)));
            System.out.println("Элемент добавлен в коллекцию");
        }
    }
