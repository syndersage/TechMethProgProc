package tmp.proc;

import java.util.Scanner;

public interface WisdomUnion {
    /**
     * Создает мудрость определенного типа на основании прочитанной информации,
     * заполняет параметры относящиеся к этому типу, после чего возвращает результат
     *
     * @param scan источник данных
     * @return мудрость прочитанного типа если параметры указаны правильно, иначе null
     */
    static WisdomUnion in(Scanner scan) {
        Wisdom.Type type  = Wisdom.Type.values()[Integer.parseInt(scan.nextLine()) - 1];
        WisdomUnion typedWisdom = null;
        switch (type) {
            case APHORISM -> typedWisdom = Aphorism.in(scan);
            case PROVERB -> typedWisdom = Proverb.in(scan);
            case RIDDLE -> typedWisdom = Riddle.in(scan);
        }
        return typedWisdom;
    }
}
