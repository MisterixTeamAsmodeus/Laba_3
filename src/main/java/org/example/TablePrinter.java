package org.example;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

///Класс для отрисовки и получения данных в виде таблицы
public class TablePrinter {
    public static ArrayList<Object> getHeaders(long start, long end, long delta) {
        ArrayList<Object> headers = new ArrayList<>();

        for (long i = start; i <= end; i += delta)
            headers.add(i);
        return headers;
    }

    /**
     * Получить данные для отображения
     *
     * @param operator Оператор который возвращает данные которые будет необходимо отобразить
     * @param start    Начало промежутка
     * @param end      Конец промежутка
     * @param delta    Шаг промежутка
     * @return
     */
    public static ArrayList<Object> getValues(UnaryOperator<Long> operator, long start, long end, long delta) {
        ArrayList<Object> values = new ArrayList<>();

        for (long i = start; i <= end; i += delta)
            values.add(operator.apply(i));

        return values;
    }

    /**
     * Вывести строку в виде строки в таблице
     *
     * @param values Данные для отображения
     * @param offset Максимальная ширина столбца
     */
    public static void printLine(ArrayList<Object> values, int offset) {
        System.out.print("| ");
        for (Object value : values)
            System.out.printf("%-" + offset + "s | ", value);
    }
}
