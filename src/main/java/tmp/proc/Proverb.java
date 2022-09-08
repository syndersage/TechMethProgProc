package tmp.proc;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс является описанием одного из типов мудрости - пословицы.
 * В классе находятся только поля, относящиеся к пословице, все общие поля располагаются в Wisdom.
 * Описываются методы, которые работают исключительно с собственными полями.
 */
public class Proverb implements WisdomUnion {

  //Страна пословицы
  private String country;

  /**
   * Создает экземпляр Proverb и заполняет его поля, результат возвращает
   *
   * @param scan источник данных для полей класса
   * @return созданный экземпляр Proverb
   */
  public static Proverb in(Scanner scan) throws NoSuchElementException {
    Proverb proverb = new Proverb();
    String line = scan.nextLine();
    if (line.isBlank()) {
      throw new NoSuchElementException("Country must be at least one symbol length.");
    }
    proverb.country = line;
    return proverb;
  }

  /**
   * Вывод информации о полях, относящихся к этому типу мудрости (Пословица)
   *
   * @param proverb экземпляр пословицы, у которой считываются параметры
   * @param pw      источник, в который будет производиться вывод информации
   */
  public static void out(Proverb proverb, PrintWriter pw) {
    pw.print("From: " + proverb.country + ". ");
  }
}
