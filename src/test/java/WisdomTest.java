import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.proc.Aphorism;
import tmp.proc.Wisdom;

@DisplayName("Wisdom tests")
public class WisdomTest {

  String input;
  Wisdom w1;

  StringWriter str;

  @BeforeEach
  void w1Init() {
    w1 = null;
    str = new StringWriter();
  }

  @Test
  void setGetCorrectRateTest() {
    w1 = new Wisdom();
    byte[] expectedResults = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    byte[] actualResults = new byte[expectedResults.length];
    for (int i = 0; i < expectedResults.length; i++) {
      Wisdom.inRate(w1, new Scanner(String.valueOf(i + 1)));
      actualResults[i] = Wisdom.getRate(w1);
    }
    assertArrayEquals(expectedResults, actualResults);
  }

  @Test
  void setGetIncorrectRateLowerThanMinTest() {
    input = "-5";
    Wisdom w1 = new Wisdom();
    assertThrows(NumberFormatException.class, () -> Wisdom.inRate(w1, new Scanner(input)));
  }

  @Test
  void setGetIncorrectRateHigherThanMaxTest() {
    input = "15";
    Wisdom w1 = new Wisdom();
    assertThrows(NumberFormatException.class, () -> Wisdom.inRate(w1, new Scanner(input)));
  }

  @Test
  void setGetIncorrectRateNotNumberTest() {
    input = "rate";
    Wisdom w1 = new Wisdom();
    assertThrows(NumberFormatException.class, () -> Wisdom.inRate(w1, new Scanner(input)));
  }

  @Test
  void setCorrectRateTest() {
    input = "5";
    w1 = new Wisdom();
    Wisdom.inRate(w1, new Scanner(input));
    assertEquals(5, Wisdom.getRate(w1));
  }

  @Test
  void setCorrectTextTest() {
    input = "h e l l o";
    w1 = new Wisdom();
    Wisdom.inText(w1, new Scanner(input));
    assertEquals(input, Wisdom.getText(w1));
  }

  @Test
  void setIncorrectBlankTextTest() {
    input = "        ";
    w1 = new Wisdom();
    assertThrows(NoSuchElementException.class, () -> Wisdom.inText(w1, new Scanner(input)));
  }


  @Test
  void setCorrectDataParamsTest() {
    input = "author";
    assertDoesNotThrow(() -> Aphorism.in(new Scanner(input)));
  }

  @Test
  void setIncorrectEmptyDataParamsTest() {
    input = "";
    assertThrows(NoSuchElementException.class, () -> Aphorism.in(new Scanner(input)));
  }

  @Test
  void setIncorrectBlankDataParamsTest() {
    input = " \t \r  ";
    assertThrows(NoSuchElementException.class, () -> Aphorism.in(new Scanner(input)));
  }

  @Test
  void compareToMethodTest() {
    Wisdom riddle = new Wisdom();
    Wisdom aphorism = new Wisdom();
    Wisdom proverb = new Wisdom();
    Wisdom.inText(riddle, new Scanner("one ! punctuation mark"));
    Wisdom.inText(aphorism, new Scanner("two :: punctuation marks"));
    Wisdom.inText(proverb, new Scanner("zero punctuation marks"));
    assertTrue(Wisdom.compare(riddle, aphorism) < 0);
    assertTrue(Wisdom.compare(riddle, proverb) > 0);
    Wisdom.inText(proverb, new Scanner("one ! punctuation marks"));
    assertEquals(0, Wisdom.compare(riddle, proverb));
  }
}
