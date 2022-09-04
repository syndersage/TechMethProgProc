package tmp.proc;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
     * @param slc список, который заполняется из источника
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
                    System.out.println("+Wisdom");
                }
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println(e.getMessage());
            }

        }
    }

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
                if (Wisdom.compare(n1.wisdom, n2.wisdom) > 0) {
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
     * Очистка контейнера (списка из мудростей) - удаление первой, последней позиций и обнуление длины этого списка.
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

    /**
     * Осуществляется вывод всех элементов из списка при помощи вызова статического метода
     * вывода информации у Wisdom класс.
     * Перебор производится благодаря вспомогательному классу Node, представляющего один элемент списка
     *
     * @param slc список, элементы которого нужно вывести
     * @param pw куда осуществляется вывод
     */
    public static void out(SingleLinkedContainer slc, PrintWriter pw) {
        Node tempNode = slc.head;
        for (int i = 0; i < slc.size; i++) {
            Wisdom.out(tempNode.wisdom, pw);
            pw.println();
            tempNode = tempNode.next;
        }
    }
}

//Вспомогательный класс для реализации односвязного списка
class Node {
    //Экземпляр мудрости
    Wisdom wisdom;

    //Указатель на следующий элемент контейнера (односвязного списка)
    Node next;
}