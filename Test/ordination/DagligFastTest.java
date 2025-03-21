package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {
    private DagligFast dagligFast;

    @BeforeEach
    void setUp() {
        dagligFast = new DagligFast(LocalDate.of(2025, 03, 17), LocalDate.of(2025, 03, 25));
    }
  @Test
    void opretDosisTest(){
       Dosis expected = new Dosis(LocalTime.of(8,00), 2);

       Dosis actual = dagligFast.opretDosis(LocalTime.of(8,00), 2);

       assertNotNull(actual);
       assertEquals(expected, actual.getTid());
       assertEquals(expected, actual.getAntal());
  }
    @Test
    void opretDosisTest_Over1(){
       dagligFast.opretDosis(LocalTime.of(8,00), 2);
       dagligFast.opretDosis(LocalTime.of(12,00), 1);
       dagligFast.opretDosis(LocalTime.of(20,00), 2);

       double expected = 5;
       double actual = dagligFast.doegnDosis();

       assertEquals(expected, actual, 0.1);
    }

  @Test
    void opretDosisTest_Under1(){
      double expected = 0;
      double actual = dagligFast.doegnDosis();

      assertEquals(expected, actual, 0.1);
  }

  @Test
    void samletDosisTest(){
    dagligFast = new DagligFast(LocalDate.of(2025, 03, 17), LocalDate.of(2025, 03, 19));
    dagligFast.opretDosis(LocalTime.of(8,0), 2.0);
    dagligFast.opretDosis(LocalTime.of(12,0), 1.0);
    dagligFast.opretDosis(LocalTime.of(18,0), 2.0);

    double samletDosis = dagligFast.samletDosis();

    assertEquals(15, samletDosis);
    }
}
