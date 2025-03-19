package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligFast extends Ordination {
    private Dosis[] dosis = new ArrayList<>().toArray(new Dosis[4]);

    public DagligFast(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public Dosis opretDosis(LocalTime tid, double antal) {
        for (int i = 0; i < dosis.length; i++) {
            if (dosis[i] != null && dosis[i].getTid().equals(tid)) {
                dosis[i].setAntal(dosis[i].getAntal() + antal);
                return dosis[i];
            }
        }

        for (int i = 0; i < dosis.length; i++) {
            if (dosis[i] == null) {
                dosis[i] = new Dosis(tid, antal);
                return dosis[i];
            }
        }
        return null;
    }

    @Override
    public double samletDosis() {
        double result = 0;

        for (int i = 0; i < (ChronoUnit.DAYS.between(getStartDato(), getSlutDato())) + 1; i++) {
            for (Dosis dosi : dosis) {
                if (dosi != null) {
                    result += dosi.getAntal();
                }
            }
        }
        return result;
    }

    @Override
    public double doegnDosis() {
        return samletDosis() / antalDage();
    }

    @Override
    public String getType() {
        return getLaegemiddel().getEnhed();
    }

    public Dosis[] getDoser() {
        return dosis;
    }
}
