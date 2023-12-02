package org.example;

///Класс для тестирования времени выполнения
public class SpeedTest {

    /**
     * Получить время выполнения функции
     *
     * @param procedure Функция время выполнения которой необходмо узнать
     * @return Время выполнения функции
     */
    public static long getExecutedTime(Procedure procedure) {
        long startTime = System.currentTimeMillis();

        procedure.apply();

        return System.currentTimeMillis() - startTime;
    }

    @FunctionalInterface
    public interface Procedure {
        void apply();
    }
}
