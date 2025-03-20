package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {
    private DagligSkaev dagligSkaev;

    @BeforeEach
    void setUp(){
        dagligSkaev = new DagligSkaev(LocalDate.of(2025,03,19), LocalDate.of(2025,03,25));
        dagligSkaev.setLaegemiddel(new Laegemiddel("Medicin", 20, 40, 60, "ml"));
    }

    @Test
    void opretDosisTest() {
        Dosis expected = new Dosis(LocalTime.of(12,00), 20);
        Dosis actual = dagligSkaev.opretDosis(LocalTime.of(12,00),20);

        assertEquals(expected.getAntal() ,actual.getAntal());
        assertEquals(expected.getTid(), actual.getTid());
    }

    @Test
    void samletDosisTest() {
        dagligSkaev.opretDosis(LocalTime.of(8,00),20);
        dagligSkaev.opretDosis(LocalTime.of(12,00),20);
        dagligSkaev.opretDosis(LocalTime.of(18,00),20);

        double expected = 420;
        double actual = dagligSkaev.samletDosis();

        assertEquals(expected, actual);
    }

    @Test
    void doegnDosisTest_Over1Dosis() {
        dagligSkaev.opretDosis(LocalTime.of(8,00),20);
        dagligSkaev.opretDosis(LocalTime.of(12,00),20);
        dagligSkaev.opretDosis(LocalTime.of(18,00),20);

        double expected = 60;
        double actual = dagligSkaev.doegnDosis();

        assertEquals(expected,actual,0.1);
    }

    @Test
    void doegnDosisTest_1Dosis() {
        dagligSkaev.opretDosis(LocalTime.of(12,00),20);

        double expected = 20;
        double actual = dagligSkaev.doegnDosis();

        assertEquals(expected,actual,0.1);
    }

    @Test
    void doegnDosisTest_Under1Dosis() {
        double expected = 0;
        double actual = dagligSkaev.doegnDosis();

        assertEquals(expected,actual,0.1);
    }

    @Test
    void getTypeTest() {
        String expected = "ml";
        String actual = dagligSkaev.getType();

        assertEquals(expected, actual);
    }

    @Test
    void getDoserTest() {
        ArrayList<Dosis> expected = new ArrayList<>();
        expected.add(new Dosis(LocalTime.of(8,00), 20));
        expected.add(new Dosis(LocalTime.of(12,00),20));
        expected.add(new Dosis(LocalTime.of(18,00),20));

        dagligSkaev.opretDosis(LocalTime.of(8,00),20);
        dagligSkaev.opretDosis(LocalTime.of(12,00),20);
        dagligSkaev.opretDosis(LocalTime.of(18,00),20);
        ArrayList<Dosis> actual = dagligSkaev.getDoser();

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getTid(), actual.get(i).getTid());
            assertEquals(expected.get(i).getAntal(), actual.get(i).getAntal());
        }

    }
}