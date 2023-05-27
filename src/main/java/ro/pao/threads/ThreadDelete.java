package ro.pao.threads;

import lombok.AllArgsConstructor;
import ro.pao.model.Elev;
import ro.pao.repository.impl.ElevRepositoryImpl;
import ro.pao.service.ElevService;
import ro.pao.service.impl.ElevServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;



@AllArgsConstructor
public class ThreadDelete implements Runnable{

    private final Logger logger = Logger.getGlobal();

    private static final ElevService elevService = new ElevServiceImpl(new ElevRepositoryImpl());
    private Integer nr;
    @Override
    public void run() {

        List<Elev> elevi = elevService.getAll();

        if(nr < elevi.size()){
            for(int i = 0; i < nr; i++){
                synchronized (this){
                elevService.deleteById(elevService.getAll().get(i).getNrMatricol());
                logger.log(Level.WARNING, "Un elev a fost modificat");
            }}
        }
    }
}
