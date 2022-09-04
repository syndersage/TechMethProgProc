package tmp.proc;

import java.io.PrintWriter;
import java.util.Scanner;

public class Riddle implements WisdomUnion {
    //Ответ на загадку
    private String answer;

    /**
     * Создает экземпляр Riddle и заполняет его поля, результат возвращает
     *
     * @param scan источник данных для полей класса
     * @return созданный экземпляр Riddle
     */
    public static Riddle in(Scanner scan) {
        Riddle riddle = new Riddle();
        riddle.answer = scan.nextLine();
        return riddle;
    }

    /**
     * Вывод информации о полях, относящихся к этому типу мудрости (Загадка)
     *
     * @param riddle экземпляр пословицы, у которой считываются параметры
     * @param pw источник, в который будет производиться вывод информации
     */
    public static void out(Riddle riddle, PrintWriter pw) {
        pw.print("Answer: " + riddle.answer + ". ");
    }
}
