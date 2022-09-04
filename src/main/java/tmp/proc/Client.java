package tmp.proc;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        SingleLinkedContainer slc = new SingleLinkedContainer();
        try (Scanner scan = new Scanner(Path.of("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgProc\\IO\\in.txt"));
        PrintWriter pw = new PrintWriter("F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgProc\\IO\\out.txt")) {
            SingleLinkedContainer.in(slc, scan);
            SingleLinkedContainer.out(slc, pw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
