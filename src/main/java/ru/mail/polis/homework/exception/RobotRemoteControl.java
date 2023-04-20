package ru.mail.polis.homework.exception;

import java.util.ArrayList;

/**
 * Задание: Нужно создать свою мини библиотеку, с удаленным роботом и пультом управления.
 * Каждый класс оценивается отдельно
 *
 * Пункт управления роботами. Через него управляются все роботы
 *
 * 4 тугрика
 */
public class RobotRemoteControl {

    private final RobotConnectionManager connectionManager = new ConnectionManager(new ArrayList<>());

    /**
     * Метод должен открыть соединение и отправить робота в указанную точку. При неудаче - повторить действие еще 2 раза,
     * Если это не удалось, то прокинуть эту ошибку на уровень выше.
     * Попытка считается успешной, если соединение открылось и вызвался метод moveRobotTo без исключений.
     */
    public void moveTo(int robotId, int toX, int toY) throws RobotConnectionException {
        int numOfTDisconnects = 0;
        for (; numOfTDisconnects < 3; numOfTDisconnects++) {
            try(
                    RobotConnection connection = connectionManager.getConnection(robotId)
            ) {
                    connection.moveRobotTo(toX, toY);
            } catch (RobotConnectionException e) {
                if (numOfTDisconnects == 2) {
                    throw e;
                }
            }
        }
    }

}
