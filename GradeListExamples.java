import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradeListExamples {
  @Rule
  public Timeout timeout = new Timeout(4000);

  @Test
  public void testFilterLength0() {
    List<String> testInput = Arrays.asList();
    assertEquals(Arrays.asList(), ListExamples.filter(testInput, new StringChecker() {
        public boolean checkString(String s) {
            return true;
        }
    }));
  }

  @Test
  public void testFilterLength1() {
    List<String> testInput = Arrays.asList("Hello");
    assertEquals(Arrays.asList("Hello"), ListExamples.filter(testInput, new StringChecker() {
        public boolean checkString(String s) {
            return true;
        }
    }));
  }

  @Test
  public void testFilterLength2() {
    List<String> testInput = Arrays.asList("Hello", "World");
    assertEquals(Arrays.asList("Hello"), ListExamples.filter(testInput, new StringChecker() {
        public boolean checkString(String s) {
            return !s.equals("World");
        }
    }));
  }

  @Test
  public void testFilterLength3() {
    List<String> testInput = Arrays.asList("Hello", "World", "Hello");
    assertEquals(Arrays.asList(), ListExamples.filter(testInput, new StringChecker() {
        public boolean checkString(String s) {
            return false;
        }
    }));
  }

  @Test
  public void testFilterLength4() {
    List<String> testInput = Arrays.asList("Hello", "World", "Hello", "World");
    assertEquals(Arrays.asList("World", "World"), ListExamples.filter(testInput, new StringChecker() {
        public boolean checkString(String s) {
            return !s.equals("Hello");
        }
    }));
  }

  @Test
  public void testFilterLength5() {
    List<String> testInput = Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog");
    assertEquals(Arrays.asList("The", "quick", "brown", "fox", "jumps", "over", "the", "dog"), ListExamples.filter(testInput, new StringChecker() {
        public boolean checkString(String s) {
            return !s.equals("lazy");
        }
    }));
  }

  @Test
  public void testFilterLength6() {
    List<String> testInput = Arrays.asList("AX", "B", "CX", "D", "E");
    assertEquals(Arrays.asList("B", "D", "E"), ListExamples.filter(testInput, new StringChecker() {
        public boolean checkString(String s) {
            return !s.contains("X");
        }
    }));
  }

  @Test
  public void testMerge1() {
    assertEquals(Arrays.asList(), ListExamples.merge(
        Arrays.asList(),
        Arrays.asList()
    ));
  }

  @Test
  public void testMerge2() {
    assertEquals(Arrays.asList("A"), ListExamples.merge(
        Arrays.asList("A"),
        Arrays.asList()
    ));
  }

  @Test
  public void testMerge3() {
    assertEquals(Arrays.asList("B"), ListExamples.merge(
        Arrays.asList(),
        Arrays.asList("B")
    ));
  }

  @Test
  public void testMerge4() {
    assertEquals(Arrays.asList("A", "B"), ListExamples.merge(
        Arrays.asList("A"),
        Arrays.asList("B")
    ));
  }

  @Test
  public void testMerge5() {
    assertEquals(Arrays.asList("A", "C", "E", "E", "F", "G"), ListExamples.merge(
        Arrays.asList("A", "C", "E"),
        Arrays.asList("E", "F", "G")
    ));
  }
}