package tmp.proc;

import java.io.PrintWriter;
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
            newNode = new Node();
            newNode.wisdom = Wisdom.in(scan);
            newNode.next = null;
            if (slc.head == null) {
                slc.head = newNode;
            }
            if (slc.tail != null) {
                slc.tail.next = newNode;
            }
            slc.tail = newNode;
            slc.size++;
        }
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