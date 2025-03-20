package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {
    private ArrayList<Dosis> dosis = new ArrayList<>();

    public DagligSkaev(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public Dosis opretDosis(LocalTime tid, double antal) {
        Dosis newDosis = new Dosis(tid, antal);
        dosis.add(newDosis);

        return newDosis;
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
        if (dosis.size() <= 0){
            return 0;
        }
        else if (dosis.size() == 1){
            return dosis.getFirst().getAntal();
        }
        return samletDosis() / (ChronoUnit.DAYS.between(getStartDato(), getSlutDato()) + 1);
    }

    @Override
    public String getType() {
        return getLaegemiddel().getEnhed();
    }

    public ArrayList<Dosis> getDoser() {
        return dosis;
    }
}
