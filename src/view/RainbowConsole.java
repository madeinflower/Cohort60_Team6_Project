package view;

// Стилизация оформления для вывода в консоль
public class RainbowConsole {

    public static final String RESET = "\u001B[0m";      // Сброс цвета - белый
    public static final String PRIMARY = "\u001B[36m";   // Голубой (заглавный)
    public static final String ACCENT = "\u001B[32m";    // Зеленый (акцент)
    public static final String ERROR = "\u001B[31m";     // Красный (ошибки)
    public static final String WARNING = "\u001B[33m";   // Желтый (предупреждения)

    public static void prnt(String text, int style) {
        switch (style) {
            case 1 -> System.out.println("   " + PRIMARY + text + RESET); // Голубой (заглавный)
            case 2 -> System.out.println("   " + ACCENT + text + RESET); // Зеленый (акцент)
            case 3 -> System.out.println("   " + WARNING + text + RESET); // Желтый (предупреждения)
            case 4 -> System.out.println("   " + ERROR + text + RESET); // Красный (ошибки)
            default -> System.out.println("   " + text); // 0 — обычный белый текст
        }
    }
}
