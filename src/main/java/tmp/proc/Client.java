package tmp.proc;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client {
    public static PrintStream logOut = System.out;
    public static Args arguments = new Args();
    public static void main(String[] args) {
        JCommander jCmd = JCommander.newBuilder().addObject(arguments).build();
        jCmd.setProgramName("Client");
        try {
            jCmd.parse(args);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            jCmd.usage();
            return;
        }
        if (arguments.help) {
            jCmd.usage();
            return;
        }
        SingleLinkedContainer slc = new SingleLinkedContainer();
        try (Scanner scan = new Scanner(arguments.inPath);
        PrintWriter pw = new PrintWriter(arguments.outPath.toString())) {
            SingleLinkedContainer.in(slc, scan);
            pw.println("Filled container.\r\n\r\nContainer contains " + SingleLinkedContainer.getSize(slc) + " elements.");
            if (arguments.sort) {
                SingleLinkedContainer.sort(slc);
            }
            SingleLinkedContainer.out(slc, pw);
            SingleLinkedContainer.clear(slc);
            pw.println("\r\nEmpty container.\r\nContainer contains " + SingleLinkedContainer.getSize(slc) + " elements.");
            SingleLinkedContainer.out(slc, pw);
        } catch (IOException e) {
            System.out.println("Input/output error: " + e.getMessage());
        }
    }
}
