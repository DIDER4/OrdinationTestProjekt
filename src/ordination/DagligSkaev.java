package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
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
        for (Dosis dosi : dosis) {
            if (dosi != null){
                result += dosi.getAntal();
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

    public ArrayList<Dosis> getDoser() {
        return dosis;
    }
}
