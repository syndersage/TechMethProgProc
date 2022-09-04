package tmp.proc;

import java.io.PrintWriter;
import java.util.Scanner;

public class Aphorism implements WisdomUnion {
    //Автор афоризма
    private String author;

    /**
     * Создает экземпляр Aphorism и заполняет его поля, результат возвращает
     *
     * @param scan источник данных для полей класса
     * @return созданный экземпляр Aphorism
     */
    public static Aphorism in(Scanner scan) {
        Aphorism aphorism = new Aphorism();
        aphorism.author = scan.nextLine();
        return aphorism;
    }

    /**
     * Вывод информации о полях, относящихся к этому типу мудрости (Афоризм)
     *
     * @param aphorism экземпляр пословицы, у которой считываются параметры
     * @param pw источник, в который будет производиться вывод информации
     */
    public static void out(Aphorism aphorism, PrintWriter pw) {
        pw.print("Author: " + aphorism.author + ". ");
    }
}
