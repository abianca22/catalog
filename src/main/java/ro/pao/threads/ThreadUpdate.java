package ro.pao.threads;

import lombok.AllArgsConstructor;
import ro.pao.model.Adresa;
import ro.pao.model.Elev;
import ro.pao.repository.impl.AdresaRepositoryImpl;
import ro.pao.repository.impl.ElevRepositoryImpl;
import ro.pao.service.AdresaService;
import ro.pao.service.ElevService;
import ro.pao.service.impl.AdresaServiceImpl;
import ro.pao.service.impl.ElevServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
public class ThreadUpdate implements Runnable{

    private final Logger logger = Logger.getGlobal();

    private Integer nrUpdates;
    private static final ElevService elevService = new ElevServiceImpl(new ElevRepositoryImpl());
    @Override
    public void run() {

        List<Elev> elevi = elevService.getAll();
        if (nrUpdates < elevi.size()) {
            for (Integer i = 0; i < nrUpdates; i++){
                synchronized (this) {
                elevService.updateNumeById(elevService.getAll().get(i).getNrMatricol(), Elev.builder()
                        .nume("NumeElev" + i)
                        .build());
                logger.log(Level.WARNING, "S-a actualizat numele unui elev");
            }}
        }
    }
}
