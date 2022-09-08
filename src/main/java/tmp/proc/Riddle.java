package tmp.proc;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс является описанием одного из типов мудрости - загадки.
 * В классе находятся только поля, относящиеся к загадке, все общие поля располагаются в Wisdom.
 * Описываются методы, которые работают исключительно с собственными полями.
 */
public class Riddle implements WisdomUnion {

  //Ответ на загадку
  private String answer;

  /**
   * Создает экземпляр Riddle и заполняет его поля, результат возвращает
   *
   * @param scan источник данных для полей класса
   * @return созданный экземпляр Riddle
   */
  public static Riddle in(Scanner scan) throws NoSuchElementException {
    Riddle riddle = new Riddle();
    String line = scan.nextLine();
    if (line.isBlank()) {
      throw new NoSuchElementException("Riddle must be at least one symbol length.");
    }
    riddle.answer = line;
    return riddle;
  }

  /**
   * Вывод информации о полях, относящихся к этому типу мудрости (Загадка)
   *
   * @param riddle экземпляр пословицы, у которой считываются параметры
   * @param pw     источник, в который будет производиться вывод информации
   */
  public static void out(Riddle riddle, PrintWriter pw) {
    pw.print("Answer: " + riddle.answer + ". ");
  }
}
