package tmp.proc;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс представляет собой односвязный линейный список.
 * Реализует статические методы взаимодействия со списком (с указанием в аргументах списка, над которым будут проводиться операции)
 */
public class SingleLinkedContainer {

  //Длина списка (контейнера)
  private int size;

  //Первый элемент списка
  private Node head;

  //Последний элемент списка
  private Node tail;

  /**
   * Чтение элементов (мудростей) и поочередное добавление их в список
   *
   * @param slc  список, который заполняется из источника
   * @param scan источник с данными об элементах
   */
  public static void in(SingleLinkedContainer slc, Scanner scan) {
    Node newNode;
    while (scan.hasNextLine()) {
      try {
        newNode = new Node();
        newNode.wisdom = Wisdom.in(scan);
        if (newNode.wisdom != null) {
          newNode.next = null;
          if (slc.head == null) {
            slc.head = newNode;
          }
          if (slc.tail != null) {
            slc.tail.next = newNode;
          }
          slc.tail = newNode;
          slc.size++;
            if (Client.arguments.verbose) {
                Client.LOG_OUT.println("+Wisdom");
            }
        }
      } catch (NumberFormatException | NoSuchElementException e) {
          if (Client.arguments.verbose) {
              Client.LOG_OUT.println(e.getMessage());
          }
      }
    }
  }

  /**
   * Сортировка указанного контейнера по возрастанию количество знаков препинания в поле text
   *
   * @param slc сортируемый контейнер
   */
  public static void sort(SingleLinkedContainer slc) {
    int j;
    Node n1;
    Node n2;
    Wisdom temp;
    for (int i = 1; i < slc.size; i++) {
      j = i;
      while (j > 0) {
        n1 = getNode(slc, j);
        n2 = getNode(slc, j - 1);
        if (n1 == null | n2 == null) {
          return;
        }
        if (Wisdom.compare(n1.wisdom, n2.wisdom) >= 0) {
          break;
        } else {
          temp = n1.wisdom;
          n1.wisdom = n2.wisdom;
          n2.wisdom = temp;
        }
        j--;
      }
    }
  }

  /**
   * Вспомогательный для .sort метод, позволяющий получить элемент контейнера по его порядковому
   * номеру
   *
   * @param slc   контейнер, в котором будет искаться элемент
   * @param index порядковый номер (индекс) элемента
   * @return элемент, соответствующий порядковому номеру
   */
  private static Node getNode(SingleLinkedContainer slc, int index) {
    if (index < 0 | index > slc.size - 1) {
      return null;
    }
    Node node = slc.head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node;
  }

  /**
   * Перебирает попарно все элементы контейнера и выводит информацию о типах мудростей, к которым
   * они принадлежат
   *
   * @param slc контейнер, элементы которого будут перебираться
   * @param pw  источник, в который будет осуществляться запись
   */
  public static void iteratePairs(SingleLinkedContainer slc, PrintWriter pw) {
    WisdomUnion first, second;
    Node firstNode, secondNode;
    String firstWisdomType = "";
    for (int i = 0; i < slc.size - 1; i++) {
      firstNode = SingleLinkedContainer.getNode(slc, i);
      if (firstNode != null) {
        first = Wisdom.getTypedWisdom(firstNode.wisdom);
      } else {
        break;
      }
      if (first instanceof Aphorism) {
        firstWisdomType = "Aphorism and ";
      } else if (first instanceof Proverb) {
        firstWisdomType = "Proverb and ";
      } else if (first instanceof Riddle) {
        firstWisdomType = "Riddle and ";
      }
      for (int j = i + 1; j < slc.size; j++) {
        secondNode = SingleLinkedContainer.getNode(slc, j);
        if (secondNode != null) {
          second = Wisdom.getTypedWisdom(secondNode.wisdom);
        } else {
          break;
        }
        pw.print(firstWisdomType);
        if (second instanceof Aphorism) {
          pw.println("Aphorism");
        } else if (second instanceof Proverb) {
          pw.println("Proverb");
        } else if (second instanceof Riddle) {
          pw.println("Riddle");
        }
      }
    }
  }

  /**
   * Очистка контейнера (списка из мудростей) - удаление первой, последней позиций и обнуление длины
   * этого списка.
   *
   * @param slc контейнер, который нужно очистить
   */
  public static void clear(SingleLinkedContainer slc) {
    slc.size = 0;
    slc.head = null;
    slc.tail = null;
  }

  public static int getSize(SingleLinkedContainer slc) {
    return slc.size;
  }

  public static Node getHead(SingleLinkedContainer slc) {
    return slc.head;
  }

  public static Node getTail(SingleLinkedContainer slc) {
    return slc.tail;
  }

  /**
   * Осуществляется вывод всех элементов из списка при помощи вызова статического метода вывода
   * информации у Wisdom класс. Перебор производится благодаря вспомогательному классу Node,
   * представляющего один элемент списка
   *
   * @param slc список, элементы которого нужно вывести
   * @param pw  куда осуществляется вывод
   */
  public static void out(SingleLinkedContainer slc, PrintWriter pw) {
    Node tempNode = slc.head;
    for (int i = 0; i < slc.size; i++) {
      pw.print((i + 1) + ": ");
      Wisdom.out(tempNode.wisdom, pw);
      pw.println();
      tempNode = tempNode.next;
    }
  }

  /**
   * Функция, действующая аналогично методы out, однако выводит только мудрости первого типа (Афоризмы)
   *
   * @param slc контейнер, из которого будут выводиться элементы
   * @param pw источник для записи информации об элементах
   */
  public static void outFirstType(SingleLinkedContainer slc, PrintWriter pw) {
    Node tempNode = slc.head;
    if (tempNode == null) {
      return;
    }
    for (int i = 0; i < slc.size; i++) {
      if (Wisdom.getTypedWisdom(tempNode.wisdom) instanceof Aphorism) {
        pw.print((i + 1) + ": ");
        Wisdom.out(tempNode.wisdom, pw);
        pw.println();
      }
      tempNode = tempNode.next;
    }
  }
}

/**
 * Вспомогательный класс для реализации односвязного списка.
 * Из экземпляров данного класса создаются элементы списка.
 */
class Node {

  //Экземпляр мудрости
  Wisdom wisdom;

  //Указатель на следующий элемент контейнера (односвязного списка)
  Node next;
}