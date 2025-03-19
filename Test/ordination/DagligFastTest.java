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
    void opretDosisTest_Success(){
       LocalTime tid = LocalTime.of(8,0);
       double antal = 2.0;

       Dosis actual = dagligFast.opretDosis(tid, antal);

       assertNotNull(actual);
       assertEquals(tid, actual.getTid());
       assertEquals(antal, actual.getAntal());

  }

  @Test
    void opretDosisTest_Fail(){
      // Arrange
      double antal = 2.0;

      // Act
      Dosis dosis = dagligFast.opretDosis(null, antal);

      // Assert
      assertNull(dosis.getTid());
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
