package ro.pao.application;

import ro.pao.model.*;
import ro.pao.model.enums.Invatare;
import ro.pao.model.enums.MaterieObligatorie;
import ro.pao.service.ExampleService;
import ro.pao.service.ScoalaService;
import ro.pao.service.SemestruService;
import ro.pao.service.impl.ExampleServiceImpl;
import ro.pao.service.impl.ScoalaServiceImpl;
import ro.pao.service.impl.SemestruServiceImpl;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

/**
 * In Meniu se fac operatiile care pot lua informatii din toate dintre servicile definite.
 * De exemplu, avem definit mai jos `private final ExampleService exampleService = new ExampleServiceImpl();`
 *
 * In cazul in care aveam definit mai multe servicii, la fel, faceam o initializare a serviciile si astfel apelam metode din serviciu.
 */
public class Menu {

    private static Menu INSTANCE;

    //private final ExampleService exampleService = new ExampleServiceImpl();

    private final ScoalaService scoalaService = new ScoalaServiceImpl();

    private final SemestruService semestruService = new SemestruServiceImpl();

    public static Menu getInstance() {
        return (INSTANCE == null ? new Menu() : INSTANCE);
    }

    private Menu() {}

    public void intro() {
        String intro = """
                Catalog
                """;

        System.out.println(intro);


//        Elev elev = Elev.builder()
//                .nume("Andrei")
//                .prenume("Bianca")
//                .cnp("")
//                .adresa(new Adresa("Arges", "Pitesti", "Strada", null, null, null))
//                .stilInvatare(Invatare.PRACTIC)
//                .dataNastere(LocalDate.of(2002, 3, 22))
//                .nrMatricol(UUID.randomUUID()).build();
//
//        Scoala scoala = Scoala.builder()
//                .denumire("CNICB Pitesti")
//                .profesori(new ArrayList<>())
//                .adresa(new Adresa("Arges", "Pitesti", "Armand Calinescu", 14, null, null))
//                .build();
//
//
//        Scoala scoala2 = Scoala.builder()
//                .denumire("Scoala")
//                .adresa(Adresa.builder()
//                        .judet("Arges")
//                        .localitate("Pitesti")
//                        .strada("Strada0")
//                        .numar(100)
//                        .build())
//                .profesori(new ArrayList<>())
//                .build();
//
//        Profesor profesor = Profesor.builder()
//                .nume("NumeProf")
//                .prenume("PrenumeProf")
//                .disciplineObligatorii(new ArrayList<>())
//                .build();
//
//        ArrayList<MaterieObligatorie> materiiPredate = new ArrayList<>();
//        materiiPredate.add(MaterieObligatorie.MATE);
//        profesor.setDisciplineObligatorii(materiiPredate);
//
//        ArrayList<Profesor> profesori = new ArrayList<>();
//        profesori.add(profesor);
//
//        scoala2.setProfesori(profesori);
//
//        Semestru semestrul1 = Semestru.builder()
//                .medieSemestriala(new HashMap<>())
//                .teze(new TreeMap<>())
//                .situatieObligatorii(new TreeMap<>())
//                .situatieOptionale(new TreeMap<>())
//                .absenteObligatorii(new TreeMap<>())
//                .absenteOptionale(new TreeMap<>())
//                .build();
//
//        Semestru semestrul2 = Semestru.builder()
//                .medieSemestriala(new HashMap<>())
//                .teze(new TreeMap<>())
//                .situatieObligatorii(new TreeMap<>())
//                .situatieOptionale(new TreeMap<>())
//                .absenteObligatorii(new TreeMap<>())
//                .absenteOptionale(new TreeMap<>())
//                .build();
//
//
//
//
//        Catalog catalog = Catalog.builder()
//                .scoala(scoala)
//                .clasa(5)
//                .literaClasa('c')
//                .elevi(new HashMap<UUID, Elev>())
//                .profesoriClasa(profesori)
//                .materiiObligatorii(materiiPredate)
//                .semestru1(semestrul1)
//                .semestru2(semestrul2)
//                .build();
//
//        System.out.println("Inainte de adaugare: " + scoalaService.listeazaLista() + "\n\n");
//
//        scoalaService.adaugaScoala(scoala);
//
//        scoalaService.adaugaScoli(List.of(
//                Scoala.builder()
//                        .denumire("Scoala7")
//                        .adresa(
//                                Adresa.builder()
//                                        .judet("Arges")
//                                        .localitate("Pitesti")
//                                        .strada("Strada2")
//                                        .build()
//                        )
//                        .profesori(new ArrayList<>())
//                        .build(),
//                Scoala.builder()
//                        .denumire("Scoala5")
//                        .adresa(
//                                Adresa.builder()
//                                        .judet("Arges")
//                                        .localitate("Pitesti")
//                                        .strada("Strada3")
//                                        .build()
//                        )
//                        .profesori(new ArrayList<>())
//                        .build()
//        ));
//
//        System.out.println("Dupa adaugare: " + scoalaService.listeazaLista() + "\n\n");
//
//        System.out.println("Lista scolilor inainte de sortare: ");
//        System.out.println(scoalaService.listeazaLista() + "\n\n");
//        scoalaService.sorteazaNume();
//        System.out.println("Lista scolilor dupa sortare: ");
//        System.out.println(scoalaService.listeazaLista() + "\n\n");
//
//        scoalaService.stergeScoala(scoala);
//
//        System.out.println("Dupa stergere: " + scoalaService.listeazaLista() + "\n\n");
//
//        System.out.println("Adaugam obiectul sters anterior din nou");
//        scoalaService.adaugaScoala(scoala);
//        System.out.println(scoalaService.listeazaLista() + "\n\n");
//
//        System.out.println("Modificam acest obiect");
//
//        scoalaService.modificaScoala(scoala, scoala2);
//        System.out.println("Dupa modificare: " + scoalaService.listeazaLista() + "\n\n");
//
//
//        System.out.println("Catalog " + catalog + "\n\n");
//
//        System.out.println("Adaugam elev in catalog");
//        semestruService.adaugaElev(catalog, elev);
//        System.out.println("Elevi: " + catalog.getElevi() + "\n\n");
//        System.out.println("Situatie elevi primul semestru: " + catalog.getSemestru1().getSituatieObligatorii() + "\n\n");
//
//        System.out.println("Punem elevului/elevei adaugat nota 10 la Matematica pe primul semestru");
//        semestruService.adaugaNotaObl(catalog, 1, MaterieObligatorie.MATE, new Nota(10, LocalDate.now()), elev);
//        System.out.println("Nota elevului " + elev.getNume() + " " + elev.getPrenume() + " este " + catalog.getSemestru1()
//                .getSituatieObligatorii()
//                .get(elev)
//                .get(MaterieObligatorie.MATE)
//                .get(0) + "\n\n");
//
//        ArrayList<LocalDate> absente = new ArrayList<>();
//        absente.add(LocalDate.now());
//        absente.add(LocalDate.of(2023, 3, 30));
//        Map<MaterieObligatorie, ArrayList<LocalDate>> absenteMaterii = new TreeMap<>();
//        absenteMaterii.put(MaterieObligatorie.MATE, absente);
//
//        System.out.println("Adaugam doua absente la matematica acestui elev");
//        catalog.getSemestru1().getAbsenteObligatorii().get(elev).put(MaterieObligatorie.MATE, absente);
//        System.out.println("Absentele elevilui/elevei la matematica pe primul semestru sunt: " + catalog.getSemestru1()
//                .getAbsenteObligatorii()
//                .get(elev)
//                .put(MaterieObligatorie.MATE, absente) + "\n\n");
//        System.out.println("Numarul absentelor de pe primul semestru al elevului/elevei este: " +
//                semestruService.numaraAbsente(catalog.getSemestru1()).get(elev) + "\n\n");
//
//        System.out.println("Stergem elevul din catalog");
//        semestruService.stergeElev(catalog, elev);
//        System.out.println("Elevii din catalog dupa stergere: " + catalog.getElevi() + "\n\n");
//        System.out.println("Situatia scoalara a elevilor de pe primul semestru dupa stergere: " + catalog.getSemestru1().getSituatieObligatorii() + "\n\n");
//




    }
}
