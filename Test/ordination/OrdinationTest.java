package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdinationTest {

    Ordination ordination;

    @BeforeEach
    void setUp(){
        ordination = new DagligFast(LocalDate.of(2025, 03, 19), LocalDate.of(2025, 03, 25));
    }

    @Test
    void setLaegemiddelTest() {
        Laegemiddel laegemiddel = new Laegemiddel("medicin", 20, 40, 60, "ml");
        ordination.setLaegemiddel(laegemiddel);

        Laegemiddel expected = laegemiddel;
        Laegemiddel actual = ordination.getLaegemiddel();

        assertEquals(expected, actual);
    }

    @Test
    void removeLaegemiddelTest() {
        Laegemiddel laegemiddel = new Laegemiddel("medicin", 20, 40, 60, "ml");
        ordination.setLaegemiddel(laegemiddel);
        ordination.removeLaegemiddel();

        Laegemiddel expected = null;
        Laegemiddel actual = ordination.getLaegemiddel();

        assertEquals(expected, actual);
    }

    @Test
    void getLaegemiddelTest() {
        Laegemiddel laegemiddel = new Laegemiddel("medicin", 20, 40, 60, "ml");
        ordination.setLaegemiddel(laegemiddel);

        Laegemiddel expected = laegemiddel;
        Laegemiddel actual = ordination.getLaegemiddel();

        assertEquals(expected, actual);
    }

    @Test
    void getStartDatoTest() {
        LocalDate expected = LocalDate.of(2025, 03, 19);
        LocalDate actual = ordination.getStartDato();

        assertEquals(expected, actual);
    }

    @Test
    void getSlutDatoTest() {
        LocalDate expected = LocalDate.of(2025, 03, 25);
        LocalDate actual = ordination.getSlutDato();

        assertEquals(expected, actual);
    }

    @Test
    void antalDageTest() {
        int expected = 7;
        int actual = ordination.antalDage();

        assertEquals(expected, actual);
    }

    @Test
    void testToStringTest() {
        String expected = "2025-03-19";
        String actual = ordination.toString();

        assertEquals(expected, actual);
    }
}