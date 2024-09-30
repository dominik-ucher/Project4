package HotellBookingSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import HotellBookingSystem.*;

public class Hotell {
    private List<Rom> ledigeRom;


    public Hotell(List<Rom> rom, List<Bestilling> bestilling, int antallpersoner, Date startdato, Date sluttdato) throws ParseException {
        this.ledigeRom = new ArrayList<Rom>();
        this.ledigeRom.addAll(getLedigerom(rom, bestilling, antallpersoner, startdato, sluttdato));
    }

    public List<Rom> getLedigerom(List<Rom> rom, List<Bestilling> bestilling, int antallpersoner, Date startdato, Date sluttdato) throws ParseException {
        List<Rom> ledigeRom = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
        for (Rom roms : rom) {
            boolean isBooked = false;
            for (Bestilling bestillinger : bestilling) {
                if (roms.getRomnr() == bestillinger.getRomnr()) {
                    Date startdato2 = dateFormat.parse(bestillinger.getStartdato());
                    Date sluttdato2 = dateFormat.parse(bestillinger.getEndedato());
                    if (bestillinger.overlapsWith(startdato2, sluttdato2, startdato, sluttdato)) {
                        isBooked = true;
                        break;
                    }
                }
            }
            if (!isBooked && roms.getAntallpersoner() == antallpersoner && !ledigeRom.contains(roms)) {
                ledigeRom.add(roms);
            }
        }
    
        return ledigeRom;
    }

}
