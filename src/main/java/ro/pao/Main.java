package ro.pao;

import ro.pao.application.Menu;
import ro.pao.model.*;
import ro.pao.model.enums.Invatare;
import ro.pao.model.enums.MaterieObligatorie;
import ro.pao.model.enums.MaterieOptionala;
import ro.pao.service.SemestruService;
import ro.pao.service.impl.SemestruServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            Menu menu = Menu.getInstance();

            menu.intro();

            if ("exit".equals(scanner.next())) {
                break;
            }
        }

//        Elev elev = new Elev(new RolElev());
//        elev.printRole();
//
//        Profesor prof = new Profesor(new RolProfesor());
//        prof.printRole();


/*
        Catalog catalog = new Catalog();
        Map<UUID, Elev> elevi = new HashMap<>();
        Elev elev = Elev.builder()
                .nume("Andrei")
                .prenume("Bianca")
                .cnp("")
                .adresa(new Adresa())
                .stilInvatare(Invatare.PRACTIC)
                .nrMatricol(UUID.randomUUID())
                .build();
        ArrayList<MaterieObligatorie> materii = new ArrayList<>();
        materii.add(MaterieObligatorie.MATE);
        catalog.setMateriiObligatorii(materii);
        SemestruService semestruService = new SemestruServiceImpl();
        semestruService.adaugaElev(catalog, elev);
        System.out.println(catalog.getElevi());
        System.out.println(catalog.getMateriiObligatorii());
        semestruService.adaugaNotaObl(catalog, 1, MaterieObligatorie.MATE, new Nota(10, LocalDate.now()), elev);
        System.out.println(catalog.getSemestru1().getSituatieObligatorii());
*/


    }

}
