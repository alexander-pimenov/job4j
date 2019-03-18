package ru.job4j.condition;
/**
 * @author Alexander Pimenov (pimalex1978@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DummyBot {
    /**
     * Программа "Глупый бот".
     * Он умеет отвечать только на три Фразы.
     * @param question Вопрос от клиента.
     * @return Ответ.
     */
    public String answer(String question) {
        String rsl = "Это ставит меня в тупик. Задайте другой вопрос.";
        if ("Привет, Бот.".equals(question)) { //Идет проверка известен ли боту этот вопрос "Привет, бот."
            //и он знает, как на него ответить.
            // заменил rsl = ... на правильный ответ rsl = "Привет, умник.".
            rsl = "Привет, умник.";
        } else if ("Пока.".equals(question)) { // заменил ... на проверку, известен ли боту этот
            // вопрос и он знает, как на него ответить.
            // заменил rsl = ... на правильный ответ rsl = "До скорой встречи.".
            rsl = "До скорой встречи.";
        }
        return rsl;
    }
}
