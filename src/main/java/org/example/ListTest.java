package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

///Класс для тестирования времени выполнения встроенных функций в интерфейсе List
public class ListTest {
    ///Лист на котором будут проходить тесты
    private final List<Object> list;
    private final Random random = new Random(System.currentTimeMillis());

    public ListTest(List<Object> list) {
        this.list = list;
    }

    /**
     * Запустить расчёт расчёта времени выполнения втроенных функций (add, remove, get, set)
     *
     * @param start Начало промежутка
     * @param end   Конец промежутка
     * @param delta Шаг промежутка
     */
    public void test(long start, long end, long delta) {
        final int repeat = 100;
        testFunction(size -> getAverage(repeat, this::add, size), "Add", start, end, delta);
        testFunction(size -> getAverage(repeat, value -> remove(value, 10), size), "Remove 10%", start, end, delta);
        testFunction(size -> getAverage(repeat, value -> get(value, 10), size), "Get 10%", start, end, delta);
        testFunction(size -> getAverage(repeat, value -> set(value, 10), size), "Set 10%", start, end, delta);
    }

    /**
     * Получить среднее значение оператора
     *
     * @param repeat   Колличество повторений
     * @param operator Оператор для усреднения
     * @param value    Аргумент оператора
     * @return Среднее значение оператора
     */
    private long getAverage(int repeat, UnaryOperator<Long> operator, long value) {
        long time = 0;
        for (int i = 0; i < repeat; i++) {
            time += operator.apply(value);
        }
        return time / 100;
    }

    /**
     * Тестирование функции и вывод информации о тестировании
     *
     * @param operator     Функция для тестирования
     * @param functionName Название функции
     * @param start        Начало промежутка
     * @param end          Конец промежутка
     * @param delta        Шаг промежутка
     */
    private void testFunction(UnaryOperator<Long> operator, String functionName, long start, long end, long delta) {
        System.out.println(functionName + " in " + list.getClass().getName() + " :");

        int offset = (int) (Math.log10(end) + 1);

        ArrayList<Object> headers = TablePrinter.getHeaders(start, end, delta);
        headers.add(0, "Size");
        TablePrinter.printLine(headers, offset);
        System.out.println();

        ArrayList<Object> values = TablePrinter.getValues(operator, start, end, delta);
        values.add(0, "Time");
        TablePrinter.printLine(values, offset);
        System.out.println();
    }

    private long add(long count) {
        list.clear();

        long executedTime = 0;
        for (long i = 0; i < count; i++)
            executedTime += SpeedTest.getExecutedTime(() -> list.add(new Object()));

        return executedTime;
    }

    /**
     * Получить данные из списка с замером времени
     *
     * @param count   Размер списка
     * @param percent Процент полученных данных
     * @return Время выполнеия получения
     */
    private long get(long count, int percent) {
        setSize(count);

        long executedTime = 0;
        for (long i = 0; i < count * percent / 100; i++) {
            int index = random.nextInt(list.size());
            executedTime += SpeedTest.getExecutedTime(() -> list.get(index));
        }

        return executedTime;
    }

    /**
     * Заменить данные в список с замером времени
     *
     * @param count   Размер списка
     * @param percent Процент заменённых данных
     * @return Время выполнения замены
     */
    private long set(long count, int percent) {
        setSize(count);

        long executedTime = 0;
        for (long i = 0; i < count * percent / 100; i++) {
            int index = random.nextInt(list.size());
            executedTime += SpeedTest.getExecutedTime(() -> list.set(index, null));
        }

        return executedTime;
    }

    /**
     * Удалить данные из списка с замером времени
     *
     * @param count   Размер списка
     * @param percent Процент удалённых данных
     * @return Время выполнения удаления
     */
    private long remove(long count, int percent) {
        setSize(count);

        long executedTime = 0;
        for (long i = 0; i < count * percent / 100; i++) {
            int index = random.nextInt(list.size());
            executedTime += SpeedTest.getExecutedTime(() -> list.remove(index));
        }

        return executedTime;
    }

    /**
     * Установить размер списка
     *
     * @param size Размер списка
     */
    private void setSize(long size) {
        list.clear();
        for (long i = 0; i < size; i++)
            list.add(null);
    }
}
