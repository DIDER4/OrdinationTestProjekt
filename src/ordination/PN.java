package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination{

    private double antalEnheder;
    private ArrayList<LocalDate> datoerGivet = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder) {
        super(startDato, slutDato);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givetDato
     * Returnerer true hvis givetDato er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givetDato ignoreres
     * @param givetDato
     * @return
     */
    public boolean givDosis(LocalDate givetDato) {
        if (givetDato.isBefore(getSlutDato()) && givetDato.isAfter(getStartDato())){
            datoerGivet.add(givetDato);
            return true;
        }
        return false;   
    }

    public double doegnDosis() {
        return (antalEnheder * datoerGivet.size()) / ChronoUnit.DAYS.between(datoerGivet.getFirst(), datoerGivet.getLast());
    }

    @Override
    public String getType() {
        return getLaegemiddel().getEnhed();
    }


    public double samletDosis() {
        return antalEnheder * ;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        // TODO
        return-1;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
