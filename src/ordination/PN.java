package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    private ArrayList<LocalDate> datoerGivet = new ArrayList<>();
    private ArrayList<LocalDate> unikkeDage = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder) {
        super(startDato, slutDato);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givetDato
     * Returnerer true hvis givetDato er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givetDato ignoreres
     *
     * @param givetDato
     * @return
     */
    public boolean givDosis(LocalDate givetDato) {
        if (givetDato.isBefore(getSlutDato().plusDays(1)) && givetDato.isAfter(getStartDato().minusDays(1))) {
            datoerGivet.add(givetDato);
            return true;
        }
        return false;
    }

    public double doegnDosis() {
        if (datoerGivet.isEmpty()) {
            return 0;
        } else {
            int antalDage = 0;
            unikkeDage.clear();
            for (LocalDate dato : datoerGivet) {
                if (!unikkeDage.contains(dato)) {
                    unikkeDage.add(dato);
                    antalDage++;
                }
            }
            if (antalDage == 1) {
                return samletDosis();
            } else {
                return samletDosis() / ChronoUnit.DAYS.between(datoerGivet.getFirst(), datoerGivet.getLast());
            }
        }
    }
        @Override
        public String getType () {
            return getLaegemiddel().getEnhed();
        }


        public double samletDosis () {
            return antalEnheder * datoerGivet.size();
        }

        /**
         * Returnerer antal gange ordinationen er anvendt
         *
         * @return
         */
        public int getAntalGangeGivet () {
            return datoerGivet.size();
        }

        public double getAntalEnheder () {
            return antalEnheder;
        }

    }
