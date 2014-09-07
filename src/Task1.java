import java.awt.Point;
import java.util.ArrayList;

/**
 * 1. Выпуклая оболочка
 * Дан набор точек на плоскости. Постройте минимальную выпуклую оболочку для данного набора.
 * Каждая точка имеет номер, равный порядковому номеру точки во входных данных (начиная с 1).
 * Минимальную выпуклую оболочку можно описать как последовательность точек на пересечении
 * её границ (начальная точка может быть выбрана произвольно).
 *
 * Пример входных данных:
 * 2 3
 * 4 4
 * 3 7
 * 6 5
 * 7 2
 *
 * Пример выходных данных:
 * 1 3 4 5
 * */
public class Task1 {

    public static void main(String... args) {

        Task1 instance = new Task1();

        //количество входных агрументов (координат точек на плоскости) должно быть четным
        try {
            if (args.length == 0 || args.length % 2 != 0) {
                throw new ArgumentException("Error: The number of arguments " +
                        "must be even and greater than 0");
            }
        } catch (ArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        //заполняем список точек входными данными
        ArrayList<Point> pointsList = new ArrayList<>();
        for (int i = 0; i < args.length; i += 2) {
            try {
                pointsList.add(new Point(Integer.parseInt(args[i]), Integer.parseInt(args[i + 1])));
            } catch (NumberFormatException e) {
                System.err.println("Error: All arguments must be an integer");
                System.exit(1);
            }
        }

        //вычисляем минимальную выпуклую оболочку (МВО) и выводим результат
        ArrayList<Integer> resultList = instance.jarvisMarch(pointsList);
        for (Integer item : resultList) {
            //по условиям задачи порядковые номера точек начинаются с 1, а не с 0,
            //поэтому увеличиваем выводимые индексы точек на 1
            System.out.print(++item + " ");
        }
    }

    /*
     * Алгоритм Джарвиса
     */
    private ArrayList<Integer> jarvisMarch(ArrayList<Point> points) {

        ArrayList<Integer> result = new ArrayList<>();

        //создаем рабочий список с порядковыми номерами координат точек
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            indexes.add(i);
        }

        // 1. Находим точку с наименьшей x-координатой (самая левая точка из множества),
        //    которая будет гарантированно входить в МВО,
        //    и записываем ее индекс в начало рабочего списка
        for (int i = 1; i < points.size(); i++) {
            if (points.get(indexes.get(i)).x < points.get(indexes.get(0)).x) {
                //меняем индексы местами
                indexes.set(i, indexes.set(0, indexes.get(i)));
            }
        }

        // 2. Строим МВО
        //    Начиная со стартовой точки, рекурсивно ищем самую левую точку относительно текущей,
        //    добавляя каждую такую точку в итоговый список, который и будет содержать МВО
        result.add(indexes.get(0));     //добавляем индекс стартоврй точки в начало итогового списка
        indexes.add(indexes.remove(0)); //переносим индекс стартовой точки в конец рабочего списка
        while (true) {
            int left = 0;
            for (int i = 1; i < indexes.size(); i ++) {
                if (isLeft(points.get(result.get(result.size() - 1)),
                        points.get(indexes.get(left)),
                        points.get(indexes.get(i)))
                        > 0) {
                    left = i;
                }
            }
            //если найденная точка является стартовой
            if (indexes.get(left).intValue() == result.get(0).intValue()) {
                break;  //то прекращаем поиск
            } else {    //иначе добавляем индекс этой точки в итоговый список и удаляем его из рабочего списка
                result.add(indexes.remove(left));
            }
        }
        return result;
    }

    /*
     * Метод возвращает значение > 0, если точка C находится левее луча AB
     *                           < 0, если точка C находится правее луча AB
     *                             0, если точки A, B и C лежат на одной прямой
     */
    private int isLeft(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - b.y) - (b.y - a.y) * (c.x - b.x);
    }

    private static class ArgumentException extends RuntimeException {
        ArgumentException(String message) {
            super(message);
        }
    }
}
