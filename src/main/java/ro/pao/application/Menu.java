package ro.pao.application;

import ro.pao.model.*;
import ro.pao.model.enums.Invatare;
import ro.pao.model.enums.MaterieObligatorie;
import ro.pao.repository.impl.*;
import ro.pao.service.*;
//import ro.pao.service.ScoalaService;
//import ro.pao.service.SemestruService;
import ro.pao.service.impl.*;
//import ro.pao.service.impl.ScoalaServiceImpl;
//import ro.pao.service.impl.SemestruServiceImpl;

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

    private final AdresaService adresaService = new AdresaServiceImpl(new AdresaRepositoryImpl());

    private final ElevService elevService = new ElevServiceImpl(new ElevRepositoryImpl());

    private final SemestruService semestruService = new SemestruServiceImpl(new SemestruRepositoryImpl());

    private final CatalogService catalogService = new CatalogServiceImpl(new CatalogRepositoryImpl());

    private final ProfesorService profesorService = new ProfesorServiceImpl(new ProfesorRepositoryImpl());

    public static Menu getInstance() {
        return (INSTANCE == null ? new Menu() : INSTANCE);
    }

    private Menu() {}

    public void intro() {
        String intro = """
                Catalog
                """;

        System.out.println(intro);

        System.out.println("ADRESE\n");

        System.out.println("Inainte de adaugare: ");

        if (adresaService.getAll().isEmpty()) {
            System.out.println("Nu exista nicio adresa in baza de date.");
        }
        else {
            adresaService.getAll().forEach(element -> System.out.println(element));
        }

        Adresa adresa = Adresa.builder()
                .id(UUID.randomUUID())
                .judet("Arges")
                .localitate("Pitesti")
                .strada("Armand Calinescu")
                .numar(14)
                .build();

        Adresa adresa2 = Adresa.builder()
                .id(UUID.randomUUID())
                .judet("Arges")
                .localitate("Pitesti")
                .strada("Ion C. Bratianu")
                .numar(14)
                .build();

        adresaService.addFromGivenList(List.of(adresa, adresa2));

        System.out.println("Dupa adaugare:\n");

        if (adresaService.getAll().isEmpty()) {
            System.out.println("Nu exista nicio adresa in baza de date.");
        }
        else {
            adresaService.getAll().forEach(element -> System.out.println(element));
        }


        System.out.println("ELEVI\n");

        System.out.println("Inainte de adaugare:\n");

        if(elevService.getAll().isEmpty()){
            System.out.println("Nu exista niciun elev in baza de date.");
        }
        else {
            elevService.getAll().forEach(element -> System.out.println(element));
        }

        List<Adresa> adrese = adresaService.getAll();

        List<Elev> elevi = List.of(
                Elev.builder()
                        .nrMatricol(UUID.randomUUID())
                        .nume("Andrei")
                        .prenume("Bianca")
                        .cnp("6020322******")
                        .dataNastere(LocalDate.of(2002, 3, 22))
                        .adresa(adrese.get(0))
                        .build(),
                Elev.builder()
                        .nrMatricol(UUID.randomUUID())
                        .nume("Popescu")
                        .prenume("Ion")
                        .cnp("*************")
                        .dataNastere(LocalDate.of(2006, 2, 2))
                        .adresa(adrese.get(0))
                        .build(),
                Elev.builder()
                        .nrMatricol(UUID.randomUUID())
                        .nume("Popescu")
                        .prenume("Valentina")
                        .cnp("*************")
                        .dataNastere(LocalDate.of(2007, 3, 3))
                        .adresa(adrese.get(0))
                        .build()
        );

        elevService.addAllFromGivenList(elevi);

        System.out.println("Dupa adaugare:\n");

        elevService.getAll().forEach(element -> System.out.println(element));

        elevService.deleteById(elevi.get(0).getNrMatricol());

        elevi = elevService.getAll();

        System.out.println("Dupa stergere:\n");

        elevService.getAll().forEach(element -> System.out.println(element));

        elevService.updatePrenumeById(elevi.get(0).getNrMatricol(), Elev.builder()
                .prenume("Constantin")
                .build());

        System.out.println("Dupa modificarea prenumelui primului elev:\n");

        elevService.getAll().forEach(element -> System.out.println(element));


        //Semestre

        List<Semestru> semestre = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            semestre.add(Semestru.builder()
                            .id(UUID.randomUUID())
                            .build());
        }

        semestruService.addAllFromGivenList(semestre);

        System.out.println("\nPROFESORI\n");

        List<Profesor> profesori = List.of(
                Profesor.builder()
                        .id(UUID.randomUUID())
                        .nume("Badea")
                        .prenume("Ioana")
                        .cnp("*************")
                        .dataNastere(LocalDate.of(1987, 10, 11))
                        .build(),
                Profesor.builder()
                        .id(UUID.randomUUID())
                        .nume("NumeProfesor1")
                        .prenume("PrenumeProfesor1")
                        .cnp("*************")
                        .dataNastere(LocalDate.of(1987, 10, 11))
                        .build(),
                Profesor.builder()
                        .id(UUID.randomUUID())
                        .nume("NumeProfesor2")
                        .prenume("PrenumeProfesor2")
                        .cnp("*************")
                        .dataNastere(LocalDate.of(1987, 10, 11))
                        .build(),
                Profesor.builder()
                        .id(UUID.randomUUID())
                        .nume("NumeProfesor3")
                        .prenume("PrenumeProfesor3")
                        .cnp("*************")
                        .dataNastere(LocalDate.of(1987, 10, 11))
                        .build()
        );

        System.out.println("Lista profesorilor inainte de adaugare:\n");

        if(profesorService.getAll().isEmpty()){
            System.out.println("Nu exista niciun profesor in baza de date.");
        }
        else {
            profesorService.getAll().forEach(element -> System.out.println(element));
        }

        profesorService.addAllFromGivenList(profesori);

        System.out.println("Lista profesorilor dupa adaugare:\n");

        profesorService.getAll().forEach(element -> System.out.println(element));

        profesorService.deleteById(profesori.get(profesori.size()-1).getId());
        profesori = profesorService.getAll();

        System.out.println("Lista profesorilor dupa stergerea ultimului profesor:\n");
        if(profesorService.getAll().isEmpty()){
            System.out.println("Nu exista niciun profesor in baza de date.");
        }
        else {
            profesorService.getAll().forEach(element -> System.out.println(element));
        }

        if (!profesori.isEmpty()) {
            profesorService.updateById(profesori.get(profesori.size()-1).getId(), Profesor.builder().nume("AltNume").build());
            System.out.println("Lista profesorilor dupa modificarea numelui ultimului profesor:\n");
            profesorService.getAll().forEach(element -> System.out.println(element));

        }

        Catalog catalog = Catalog.builder()
                .id(UUID.randomUUID())
                .clasa(10)
                .semestru1(semestruService.getAllFromList().get(0))
                .semestru2(semestruService.getAllFromList().get(1))
                .build();

        System.out.println("Lista cataloagelor inainte de adaugare:\n");
        if (catalogService.getAll().isEmpty()){
            System.out.println("Nu exista niciun catalog in baza de date.");
        }
        else {
            catalogService.getAll().forEach(element -> System.out.println(element));
        }

        catalogService.add(catalog);

        System.out.println("Lista cataloagelor dupa adaugare:\n");
        catalogService.getAll().forEach(element -> System.out.println(element));

        elevService.getAll().forEach(e -> elevService.deleteById(e.getNrMatricol()));
        adresaService.getAll().forEach(a -> adresaService.deleteById(a.getId()));





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
