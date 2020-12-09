package ru.job4j.colorsccheme;

/**
 * В классе набор ANSI цветов для подсветки сообщений разных
 * потоков в примерах по мультипоточности.
 * Источник:
 * http://pueblo.sourceforge.net/doc/manual/ansi_color_codes.html
 */
public class ColorScheme {
    //ANSI COLOR SCHEME
    public static final String RESET = "\u001B[0m"; //сброс настроек; очищает все цвета и стили (до белого на черном)
    public static final String BLACK = "\u001B[30m"; //установить цвет переднего плана на черный
    public static final String RED = "\u001B[31m"; //установить красный цвет переднего плана
    public static final String GREEN = "\u001B[32m"; //установить цвет переднего плана на зеленый
    public static final String YELLOW = "\u001B[33m"; //установить желтый цвет переднего плана
    public static final String BLUE = "\u001B[34m"; //установить синий цвет переднего плана
    public static final String PURPLE = "\u001B[35m"; //установить цвет переднего плана на пурпурный (фиолетовый)
    public static final String CYAN = "\u001B[36m"; //установить цвет переднего плана на голубой
    public static final String WHITE = "\u001B[37m"; //установить белый цвет переднего плана
}
