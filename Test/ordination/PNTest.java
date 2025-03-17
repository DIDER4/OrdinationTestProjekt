package ordination;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class PNTest {
    private PN pn;

    @BeforeEach
    void setUp(){
        pn = new PN(LocalDate.of(2025,03,17),LocalDate.of(2025,03,25),30);
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
    void doegnDosisTest() {
        double expected = 18;

        pn.setLaegemiddel(new Laegemiddel("noget",20,50,100,"ml"));
        pn.givDosis(LocalDate.of(2025,03,19));
        pn.givDosis(LocalDate.of(2025,03,20));
        pn.givDosis(LocalDate.of(2025,03,24));

        double actual = pn.doegnDosis();

        assertEquals(expected,actual,0.1);
    }

    @org.junit.jupiter.api.Test
    void getType() {
    }

    @org.junit.jupiter.api.Test
    void samletDosis() {
    }

    @org.junit.jupiter.api.Test
    void getAntalGangeGivet() {
    }

    @org.junit.jupiter.api.Test
    void getAntalEnheder() {
    }
}