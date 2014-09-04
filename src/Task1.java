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

    private static ArrayList<Point> point = new ArrayList<>();

    public static void main(String... args) {

        //количество агрументов (координат точек на плоскости) должно быть четным
        if (args.length == 0 || args.length % 2 != 0) {
            System.err.println("Error: The number of arguments must be even and greater than 0");
            System.exit(1);
        }

        for (int i = 0; i < args.length; i += 2) {
            point.add(new Point(Integer.parseInt(args[i]), Integer.parseInt(args[i + 1])));
        }

        for (Point item : point) {
            System.out.println(item.x + " " + item.y);
        }

    }

}
