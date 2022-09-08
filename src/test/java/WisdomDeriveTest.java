import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tmp.proc.Wisdom;

/**
 * Класс содержит методы для тестирования всех типов мудростей на корректное заполнение и чтение их полей.
 */
@DisplayName("Aphorism, Proverb, Riddle, ... tests")
public class WisdomDeriveTest {

  Wisdom w1;
  String input;

  Scanner scan;

  @BeforeEach
  void w1Init() {
    w1 = null;
  }

  @Test
  void validRiddle() {
    input = "3\nanswer\ntext\n5";
    scan = new Scanner(input);
    assertDoesNotThrow(() -> Wisdom.in(scan));
  }

  @Test
  void invalidRateRiddle() {
    input = "3\nanswer\ntext\n15";
    scan = new Scanner(input);
    assertThrows(NumberFormatException.class, () -> Wisdom.in(scan));
  }

  @Test
  void invalidEmptyAnswerRiddle() {
    input = "3\n \ntext\n15";
    scan = new Scanner(input);
    assertThrows(NoSuchElementException.class, () -> Wisdom.in(scan));
  }

  @Test
  void invalidEmptyTextRiddle() {
    input = "3\nanswer\n\n5";
    scan = new Scanner(input);
    assertThrows(NoSuchElementException.class, () -> Wisdom.in(scan));
  }
}
