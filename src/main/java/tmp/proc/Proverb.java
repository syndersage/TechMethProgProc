package tmp.proc;

import java.io.PrintWriter;
import java.util.Scanner;

public class Proverb implements WisdomUnion {
    //Страна пословицы
    private String country;

    /**
     * Создает экземпляр Proverb и заполняет его поля, результат возвращает
     *
     * @param scan источник данных для полей класса
     * @return созданный экземпляр Proverb
     */
    public static Proverb in(Scanner scan) {
        Proverb proverb = new Proverb();
        proverb.country = scan.nextLine();
        return proverb;
    }

    /**
     * Вывод информации о полях, относящихся к этому типу мудрости (Пословица)
     *
     * @param proverb экземпляр пословицы, у которой считываются параметры
     * @param pw источник, в который будет производиться вывод информации
     */
    public static void out(Proverb proverb, PrintWriter pw) {
        pw.print("From: " + proverb.country + ". ");
    }
}
