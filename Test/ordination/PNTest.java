package ordination;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class PNTest {
    private PN pn;

    @BeforeEach
    void setUp(){
        pn = new PN(LocalDate.of(2025,03,17),LocalDate.of(2025,03,25),30);
        pn.setLaegemiddel(new Laegemiddel("noget",20,50,100,"ml"));
    }

    @org.junit.jupiter.api.Test
    void givDosisTest_True() {
        boolean expected = true;
        boolean actual = pn.givDosis(LocalDate.of(2025,03,20));

        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void givDosisTest_False() {
        boolean expected = false;
        boolean actual = pn.givDosis(LocalDate.of(2025,03,26));

        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void givDosisTest_Graense1() {
        boolean expected = true;
        boolean actual = pn.givDosis(LocalDate.of(2025,03,17));

        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void givDosisTest_Graense2() {
        boolean expected = false;
        boolean actual = pn.givDosis(LocalDate.of(2025,03,16));

        assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void doegnDosisTest_Over1Dosis() {
        pn.givDosis(LocalDate.of(2025,03,19));
        pn.givDosis(LocalDate.of(2025,03,20));
        pn.givDosis(LocalDate.of(2025,03,24));

        double expected = 18;
        double actual = pn.doegnDosis();

        assertEquals(expected,actual,0.1);
    }

    @org.junit.jupiter.api.Test
    void doegnDosisTest_1Dosis() {
        pn.givDosis(LocalDate.of(2025,03,24));

        double expected = 30;
        double actual = pn.doegnDosis();

        assertEquals(expected,actual,0.1);
    }

    @org.junit.jupiter.api.Test
    void doegnDosisTest_Under1Dosis() {
        double expected = 0;
        double actual = pn.doegnDosis();

        assertEquals(expected,actual,0.1);
    }

    @org.junit.jupiter.api.Test
    void getTypeTest() {
        String expected = "ml";
        String actual = pn.getType();

        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void samletDosis() {
        pn.givDosis(LocalDate.of(2025,03,19));
        pn.givDosis(LocalDate.of(2025,03,20));
        pn.givDosis(LocalDate.of(2025,03,24));

        double expected = 90;
        double actual = pn.samletDosis();

        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getAntalGangeGivet() {
        pn.givDosis(LocalDate.of(2025,03,19));
        pn.givDosis(LocalDate.of(2025,03,20));
        pn.givDosis(LocalDate.of(2025,03,24));

        int expected = 3;
        int actual = pn.getAntalGangeGivet();

        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getAntalEnheder() {
        double expected = 30;
        double actual = pn.getAntalEnheder();

        assertEquals(expected, actual);
    }
}