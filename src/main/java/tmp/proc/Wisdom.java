package tmp.proc;

import java.io.PrintWriter;
import java.util.Scanner;

public class Wisdom {
    enum Type {
        APHORISM, PROVERB, RIDDLE
    }

    private String text;

    private WisdomUnion typedWisdom;

    public static Wisdom in(Scanner scan) {
        Wisdom wisdom = new Wisdom();
        wisdom.typedWisdom = WisdomUnion.in(scan);
        wisdom.text = scan.nextLine();
        return wisdom;
    }

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
    }
}
