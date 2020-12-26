package ru.job4j.design.ocp;

import java.util.List;

/*
 * Наследование можно использовать только при устойчивой иерархии классов,
 * всегда подставляйте «is A» между сущностями, чтобы проверить можно ли
 * наследоваться. Если не уверены, то лучше использовать интерфейсы,
 * потому что они дают гибкость. Также помните, что при наследовании
 * наследуется состояние объектов, что не всегда удобно, нужно и допустимо.
 */
public class FiguresImplemetation {

    private interface Drawable {
        String draw();
    }

    private static class Triangle implements Drawable {

        @Override
        public String draw() {
            StringBuilder builder = new StringBuilder();
            builder.append("   ").append("/\\").append("   ").append(System.lineSeparator());
            builder.append("  ").append("/").append("  ").append("\\").append("  ").append(System.lineSeparator());
            builder.append(" /").append("____").append("\\ ").append(System.lineSeparator());
            return builder.toString();
        }
    }

    private static class Rectangle implements Drawable {

        @Override
        public String draw() {
            StringBuilder builder = new StringBuilder();
            builder.append("----------").append(System.lineSeparator());
            builder.append("|        |").append(System.lineSeparator());
            builder.append("|        |").append(System.lineSeparator());
            builder.append("----------").append(System.lineSeparator());
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        List<Drawable> figures = List.of(new Triangle(), new Rectangle());
        figures.forEach(f -> System.out.println(f.draw()));
    }
}
