package tmp.proc;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Wisdom {

  //Текст мудрости
  private String text;

  //Субъективная оценка мудрости
  private byte rate;

  //Тип мудрости, содержащий все соответствующие поля
  private WisdomUnion typedWisdom;

  /**
   * Создает мудрость указанного типа и заполняет все её поля, выводя null либо исключение в случае
   * проблем с входными данными
   *
   * @param scan источник для характеристик мудрости
   * @return созданную мудрость с заполненными полями, null - если тип мудрости пустая строка
   * @throws NumberFormatException  если указаны неправильные значения полей мудрости либо её типа
   * @throws NoSuchElementException если не удается прочитать строку, либо она пустая
   */
  public static Wisdom in(Scanner scan) throws NumberFormatException, NoSuchElementException {
    Wisdom wisdom = new Wisdom();
    String line = scan.nextLine().strip();
    if (line.isBlank()) {
      return null;
    }
    Wisdom.Type type;
    try {
      type = Wisdom.Type.values()[Integer.parseInt(line) - 1];
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      for (int i = 0; i < 3; i++) {
        if (scan.hasNextLine()) {
          scan.nextLine();
        }
      }
      throw new NumberFormatException("Incorrect wisdom type. Expected: [1 - " +
          (Wisdom.Type.values().length) + "]. Received: " + line);
    }
    StringBuilder linesToRead = new StringBuilder();
    for (int i = 0; i < type.numOfFields; i++) {
      linesToRead.append(scan.nextLine()).append("\n");
    }
    scan = new Scanner(linesToRead.toString());
    switch (type) {
      case APHORISM -> wisdom.typedWisdom = Aphorism.in(scan);
      case PROVERB -> wisdom.typedWisdom = Proverb.in(scan);
      case RIDDLE -> wisdom.typedWisdom = Riddle.in(scan);
    }
    Wisdom.inText(wisdom, scan);
    Wisdom.inRate(wisdom, scan);
    return wisdom;
  }

  /**
   * Заполняет поле text у мудрости
   *
   * @param wisdom мудрость, в которой будет заполнено поле
   * @param scan   источник с текстом
   * @throws NoSuchElementException если не удается прочитать строку, либо она пустая/только из
   *                                пробелов
   */
  public static void inText(Wisdom wisdom, Scanner scan) throws NoSuchElementException {
    String line = scan.nextLine();
    if (line.isBlank()) {
      throw new NoSuchElementException("Wisdom text must be at least lone symbol length.");
    } else {
      wisdom.text = line;
    }
  }

  /**
   * Заполняет поле rate у мудрости
   *
   * @param wisdom мудрость, в которой будет заполнено поле
   * @param scan   источник с рейтингом
   * @throws NumberFormatException  входное значение не является числом, либо не входит в допустимый
   *                                интервал
   * @throws NoSuchElementException не удается прочитать строку с рейтингом
   */
  public static void inRate(Wisdom wisdom, Scanner scan)
      throws NumberFormatException, NoSuchElementException {
    String line = scan.nextLine();
    try {
      wisdom.rate = Byte.parseByte(line);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Incorrect rate input: Expected [1 - 10]. Received: " + line);
    }
    if (wisdom.rate < 1 | wisdom.rate > 10) {
      wisdom.rate = 0;
      throw new NumberFormatException("Incorrect rate value: Expected [1 - 10]. Received: " + line);
    }
  }

  /**
   * Вывод информации обо всех полях мудрости
   *
   * @param wisdom мудрость, поля которой будут выводиться
   * @param pw     источник, куда будет производиться вывод информации
   */
  public static void out(Wisdom wisdom, PrintWriter pw) {
    if (wisdom.typedWisdom instanceof Aphorism) {
      pw.print("Aphorism: " + wisdom.text + ". ");
      Aphorism.out((Aphorism) wisdom.typedWisdom, pw);
    } else if (wisdom.typedWisdom instanceof Proverb) {
      pw.print("Proverb: " + wisdom.text + ". ");
      Proverb.out((Proverb) wisdom.typedWisdom, pw);
    } else if (wisdom.typedWisdom instanceof Riddle) {
      pw.print("Riddle: " + wisdom.text + ". ");
      Riddle.out((Riddle) wisdom.typedWisdom, pw);
    }
    pw.print("Rating score: " + wisdom.rate);
  }

  /**
   * Подсчет количество знаков препинания в поле text
   *
   * @param wisdom мудрость, в которой будет производиться подсчет
   * @return целочисленное количество знаков препинания
   */
  public static int countPunctuationMarks(Wisdom wisdom) {
    String punMarks = "!.,:;'-?\"";
    return (int) wisdom.text.chars().filter((x) -> punMarks.indexOf(x) != -1).count();
  }

  /**
   * Сравнение двух мудростей по признаку количества знаков препинания в их полях text
   *
   * @param w1 первая мудрость
   * @param w2 вторая мудрость
   * @return 0 - количество знаков препинания равно, меньше 0 - у первого меньше знаков, больше 0 -
   * у второго меньше знаков
   */
  public static int compare(Wisdom w1, Wisdom w2) {
    return Wisdom.countPunctuationMarks(w1) - Wisdom.countPunctuationMarks(w2);
  }

  public static WisdomUnion getTypedWisdom(Wisdom wisdom) {
    return wisdom.typedWisdom;
  }

  public static byte getRate(Wisdom wisdom) {
    return wisdom.rate;
  }

  public static String getText(Wisdom wisdom) {
    return wisdom.text;
  }

  enum Type {
    APHORISM(3), PROVERB(3), RIDDLE(3);
    private final int numOfFields;

    Type(int numOfFields) {
      this.numOfFields = numOfFields;
    }
  }
}
