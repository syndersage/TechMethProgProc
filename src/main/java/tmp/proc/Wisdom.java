package tmp.proc;

import java.io.PrintWriter;
import java.util.Scanner;

public class Wisdom {
    enum Type {
        APHORISM, PROVERB
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
        }
    }

    public static int countPunctuationMarks(Wisdom wisdom) {
        String punMarks = "!.,:;'-?\"";
        return (int) wisdom.text.chars().filter((x) -> punMarks.indexOf(x) != -1).count();
    }
}
