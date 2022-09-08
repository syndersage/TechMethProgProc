import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.proc.Client;
import tmp.proc.SingleLinkedContainer;

/**
 * Класс содержит методы для тестирования класса SingleLinkedContainer на корректность работы его методов.
 */
@DisplayName("SingleLinkedContainer tests")
public class ContainerTest {

  SingleLinkedContainer slc = new SingleLinkedContainer();

  StringWriter output = new StringWriter();

  String input;

  @BeforeEach
  public void initEach() {
    output = new StringWriter();
    SingleLinkedContainer.clear(slc);
  }

  @Test
  void emptyListTest() {
    assertEquals(0, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void emptyInputTest() {
    String str = "";
    SingleLinkedContainer.in(slc, new Scanner(str));
    assertEquals(0, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void addElementTest() {
    input = "1\nauthor\ntext\n5";
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(1, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void clearListTest() {
    input = "1\nauthor\ntext\n5";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.clear(slc);
    assertEquals(0, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void clearList2Test() {
    input = "1\nauthor\ntext\n5\n2\ncountry\ntext2\n1\nauthor2\ntext3";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.clear(slc);
    assertEquals(0, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void clearListFieldsTest() {
    input = "1\nauthor\ntext\n2\ncountry\ntext2\n1\nauthor2\ntext3";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.clear(slc);
    assertNull(SingleLinkedContainer.getHead(slc));
  }

  @Test
  void clearListFields2Test() {
    input = "1\nauthor\ntext\n2\ncountry\ntext2\n1\nauthor2\ntext3";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.clear(slc);
    assertNull(SingleLinkedContainer.getTail(slc));
  }

  @Test
  void addTwoElementsTest() {
    input = "1\nauthor\ntext\n5\n2\ncountry\ntext\n10";
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(2, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void addThreeElementsTest() {
    input = "1\nauthor\ntext\n5\n2\ncountry\ntext2\n10\n1\nauthor2\ntext3\n1";
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(3, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void emptyLinesTest() {
    input = "\n\n\n1\nauthor\ntext\n5\n2\ncountry\ntext\n10";
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(2, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void spacesInWisdomTypeLineTest() {
    input = "  \t1\r\nauthor\ntext\n5\n2\ncountry\ntext\n10";
    Client.arguments.verbose = true;
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(2, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void spacesInWisdomTypeLine2Test() {
    input = " \r 1               \nauthor\ntext\n5\n2\ncountry\ntext\n10";
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(2, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void incorrectWisdomTypeTest() {
    input = "\n\n\n4\nauthor\ntext\n5\n2\ncountry\ntext\n10";
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(1, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void emptyLineBetweenTypeAndTextTest() {
    input = "1\n\ntext\n5\n2\ncountry\ntext\n10";
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(1, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void oneEmptyTextTest() {
    input = "1\nauthor\n\n5";
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(0, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void twoEmptyTextAndRateTest() {
    input = "1\nauthor\n\n\n3\nanswer\ntext\n8"; //символ "2" будет являться текстом для первой мудрости
    SingleLinkedContainer.in(slc, new Scanner(input));
    assertEquals(1, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void nonExistingFileTest() {
    Path file = Path.of("ghdfjghjkdfgujdf");
    NoSuchFileException exception = assertThrows(NoSuchFileException.class,
        () -> SingleLinkedContainer.in(slc, new Scanner(file)));
    assertEquals("ghdfjghjkdfgujdf", exception.getMessage());
  }

  @Test
  void existingFileTest() {
    Path file = Path.of(
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ContainerTestData\\in");
    assertDoesNotThrow(() -> SingleLinkedContainer.in(slc, new Scanner(file)));
  }

  @Test
  void addToListFromFileTest() throws IOException {
    Path file = Path.of(
        "F:\\Пользователи\\Pavel\\IdeaProjects\\TechMethProgOOP\\src\\test\\java\\ContainerTestData\\in");
    Files.writeString(file, "1\nauthor\ntext\n5\n2\ncountry\ntext\n7");
    SingleLinkedContainer.in(slc, new Scanner(file));
    assertEquals(2, SingleLinkedContainer.getSize(slc));
  }

  @Test
  void stringInputStringOutputTest() {
    input = "1\nauthor\ntext\n2";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), "1: Aphorism: text. By: author. Rating score: 2\r\n");
  }

  @Test
  void sortOneNodeTest() {
    input = "1\nauthor\ntext\n2";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), "1: Aphorism: text. By: author. Rating score: 2\r\n");
  }

  @Test
  void sortAlreadyCorrectOrderOfTwoNodesTest() {
    input = "1\nauthor\ntext\n2\n2\ncountry\ntext!2\n8";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        1: Aphorism: text. By: author. Rating score: 2\r
        2: Proverb: text!2. From: country. Rating score: 8\r
        """);
  }

  @Test
  void sortTwoSameCompareValueNodesTest() {
    input = "1\nauthor\ntext\n2\n2\ncountry\ntext2\n8";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        1: Aphorism: text. By: author. Rating score: 2\r
        2: Proverb: text2. From: country. Rating score: 8\r
        """);
  }

  @Test
  void sortIncorrectOrderOfTwoNodesTest() {
    input = "1\nauthor\ntext\n2\n2\ncountry\ntext2\n8";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        1: Aphorism: text. By: author. Rating score: 2\r
        2: Proverb: text2. From: country. Rating score: 8\r
        """);
  }

  @Test
  void sortFiveElementsTest() {
    input = "1\nauthor\nte!!xt\n2\n2\ncountry\ntext2\n8\n3\nanswer\nte!!!!!xt2\n6\n2\ncountry\nte!xt2\n8\n1\nauthor\nte!!!xt2\n9";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        1: Proverb: text2. From: country. Rating score: 8\r
        2: Proverb: te!xt2. From: country. Rating score: 8\r
        3: Aphorism: te!!xt. By: author. Rating score: 2\r
        4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
        5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
        """);
  }

  @Test
  void emptyLinesAtBeginningTest() {
    input = "\n\n\n\n\n\n\n\n1\nauthor\nte!!xt\n2\n2\ncountry\ntext2\n8\n3\nanswer\nte!!!!!xt2\n6\n2\ncountry\nte!xt2\n8\n1\nauthor\nte!!!xt2\n9";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        1: Proverb: text2. From: country. Rating score: 8\r
        2: Proverb: te!xt2. From: country. Rating score: 8\r
        3: Aphorism: te!!xt. By: author. Rating score: 2\r
        4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
        5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
        """);
  }

  @Test
  void emptyLinesAtStartAndBetweenWisdomElementsTest() {
    input = "\n\n\n\n\n\n\n\n1\nauthor\nte!!xt\n2\n\n\n\n\n2\ncountry\ntext2\n8\n3\nanswer\nte!!!!!xt2\n6\n\n\n\n\n2\ncountry\nte!xt2\n8\n\n\n1\nauthor\nte!!!xt2\n9";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        1: Proverb: text2. From: country. Rating score: 8\r
        2: Proverb: te!xt2. From: country. Rating score: 8\r
        3: Aphorism: te!!xt. By: author. Rating score: 2\r
        4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
        5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
        """);
  }

  @Test
  void emptyLinesAtTheEndTest() {
    input = "1\nauthor\nte!!xt\n2\n2\ncountry\ntext2\n8\n3\nanswer\nte!!!!!xt2\n6\n2\ncountry\nte!xt2\n8\n1\nauthor\nte!!!xt2\n9\n\n\n\n\n    ";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        1: Proverb: text2. From: country. Rating score: 8\r
        2: Proverb: te!xt2. From: country. Rating score: 8\r
        3: Aphorism: te!!xt. By: author. Rating score: 2\r
        4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
        5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
        """);
  }

  @Test
  void blankLinesBeforeAfterBetweenWisdomElementsTest() {
    input = "    \n     1\nauthor\nte!!xt\n2\n2\ncountry\ntext2\n8\n\t\r\t\r3\nanswer\nte!!!!!xt2\n6\n2\ncountry\nte!xt2\n8\n  \t \n \r    1\nauthor\nte!!!xt2\n9\n   \r\r\r   \r\n\n\t\n\n    ";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        1: Proverb: text2. From: country. Rating score: 8\r
        2: Proverb: te!xt2. From: country. Rating score: 8\r
        3: Aphorism: te!!xt. By: author. Rating score: 2\r
        4: Aphorism: te!!!xt2. By: author. Rating score: 9\r
        5: Riddle: te!!!!!xt2. Answer: answer. Rating score: 6\r
        """);
  }

  @Test
  void sortEmptyTest() {
    input = "";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.sort(slc);
    SingleLinkedContainer.out(slc, new PrintWriter(output));
    assertEquals(output.toString(), "");
  }

  @Test
  void outFirstTypeTest() {
    input = """
        2
        country1
        text!!!1
        4
        1
        author1
        tex
        10
        2
        country2
        texttttttt
        3
        3
        answer1
        text1!
        8""";
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.outFirstType(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        2: Aphorism: tex. By: author1. Rating score: 10\r
        """);
  }

  @Test
  void outFirstTypeEmptyTest() {
    input = """
        2
        country1
        text!!!1
        4
        """;
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.outFirstType(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        """);
  }

  @Test
  void outFirstType2Test() {
    input = """
        1
        country1
        text1
        4
        2
        country1
        text1
        4
        1
        country2
        text1
        4
        1
        country3
        text1
        4
        3
        country1
        text1
        4
        """;
    SingleLinkedContainer.in(slc, new Scanner(input));
    SingleLinkedContainer.outFirstType(slc, new PrintWriter(output));
    assertEquals(output.toString(), """
        1: Aphorism: text1. By: country1. Rating score: 4\r
        3: Aphorism: text1. By: country2. Rating score: 4\r
        4: Aphorism: text1. By: country3. Rating score: 4\r
        """);
  }
}
