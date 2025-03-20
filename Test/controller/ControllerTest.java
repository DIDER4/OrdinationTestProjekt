package controller;

import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;
    private Storage storage;

    @BeforeEach
    void setUp() {
        controller = Controller.getTestController();
        storage = controller.getStorage();
        controller.createSomeObjects();
    }

    @Test
    void testOpretPNOrdination() {
        Patient patient = storage.getAllPatienter().get(0);
        Laegemiddel laegemiddel = storage.getAllLaegemidler().get(0);
        LocalDate startDato = LocalDate.of(2021, 1, 1);
        LocalDate slutDato = LocalDate.of(2021, 1, 12);
        double antal = 2.0;

        PN pn = controller.opretPNOrdination(startDato, slutDato, patient, laegemiddel, antal);

        assertNotNull(pn);
        assertEquals(startDato, pn.getStartDato());
        assertEquals(slutDato, pn.getSlutDato());
        assertEquals(antal, pn.getAntalEnheder(), 0.01); // Muligvis anden variable end pn.getAntalEnheder()
        assertEquals(laegemiddel, pn.getLaegemiddel());
    }

    @Test
    void testOpretDagligFastOrdination() {
        Patient patient = storage.getAllPatienter().get(1);
        Laegemiddel laegemiddel = storage.getAllLaegemidler().get(1);
        LocalDate startDato = LocalDate.of(2021, 1, 10);
        LocalDate slutDato = LocalDate.of(2021, 1, 12);
        double morgenAntal = 2.0;
        double middagAntal = 0.0;
        double aftenAntal = 1.0;
        double natAntal = 0.0;

        DagligFast dagligFast = controller.opretDagligFastOrdination(startDato, slutDato, patient, laegemiddel, morgenAntal, middagAntal, aftenAntal, natAntal);

        assertNotNull(dagligFast);
        assertEquals(startDato, dagligFast.getStartDato());
        assertEquals(slutDato, dagligFast.getSlutDato());
        assertEquals(laegemiddel, dagligFast.getLaegemiddel());
    }

    @Test
    void testOpretDagligSkaevOrdination() {
        Patient patient = storage.getAllPatienter().get(1);
        Laegemiddel laegemiddel = storage.getAllLaegemidler().get(2);
        LocalDate startDato = LocalDate.of(2021, 1, 23);
        LocalDate slutDato = LocalDate.of(2021, 1, 24);
        LocalTime[] klokkeSlet = {LocalTime.of(12, 0), LocalTime.of(12, 40), LocalTime.of(16, 0), LocalTime.of(18, 45)};
        double[] antalEnheder = {0.5, 1, 2.5, 3};

        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDato, slutDato, patient, laegemiddel, klokkeSlet, antalEnheder);

        assertNotNull(dagligSkaev);
        assertEquals(startDato, dagligSkaev.getStartDato());
        assertEquals(slutDato, dagligSkaev.getSlutDato());
        assertEquals(laegemiddel, dagligSkaev.getLaegemiddel());
    }

    @Test
    void testAnbefaletDosisPrDoegn() {
        Patient patient = storage.getAllPatienter().get(0);
        Laegemiddel laegemiddel = storage.getAllLaegemidler().get(0);

        double dosis = controller.anbefaletDosisPrDoegn(patient, laegemiddel);

        assertTrue(dosis == 9.51);
    }

    @Test
    void testAntalOrdinationerPrVaegtPrLaegemiddel() {
        double vaegtStart = 60.0;
        double vaegtSlut = 90.0;
        Laegemiddel laegemiddel = storage.getAllLaegemidler().get(0);

        int antal = controller.antalOrdinationerPrVaegtPrLaegemiddel(vaegtStart, vaegtSlut, laegemiddel);

        assertTrue(antal == 1);
    }
}