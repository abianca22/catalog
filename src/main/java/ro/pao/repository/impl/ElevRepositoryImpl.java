package ro.pao.repository.impl;

import ro.pao.application.csv.CsvWriter;
import ro.pao.config.DatabaseConfiguration;
import ro.pao.exceptions.IdNotFound;
import ro.pao.mapper.AdresaMapper;
import ro.pao.mapper.ElevMapper;
import ro.pao.model.Adresa;
import ro.pao.model.Elev;
import ro.pao.model.ExampleClass;
import ro.pao.repository.AdresaRepository;
import ro.pao.repository.ElevRepository;

import javax.xml.transform.Result;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ElevRepositoryImpl implements ElevRepository {

    private static final ElevMapper elevMapper = ElevMapper.getInstance();

    private static final AdresaMapper adresaMapper = AdresaMapper.getInstance();

    private Logger logger = Logger.getLogger(ElevRepositoryImpl.class.getName());

    @Override
    public Optional<Elev> getElevById(UUID id) throws IdNotFound{
        String selectSql = "SELECT * FROM elev e, adresa a WHERE e.id_adresa = a.id and e.nr_matricol=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            return elevMapper.mapToElev(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void deleteElevById(UUID id){
        String deleteSql = "DELETE FROM elev WHERE nr_matricol=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)){
            preparedStatement.setString(1, id.toString());
            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost sterse " + nr + " linii.");

            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{LocalDate.now().toString(), "delete", "elev"});

            Path path = Path.of("audit.csv");
            try {
                writeToCsv(lines, path);
            } catch(Exception e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNumeById(UUID id, Elev newElev){
        String updateSql = "UPDATE elev SET nume=? WHERE nr_matricol=?";

        try(Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql)){
            preparedStatement.setString(1, newElev.getNume());
            preparedStatement.setString(2, id.toString());

            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost actualizate " + nr + " linii.");

            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{LocalDate.now().toString(), "update", "elev"});

            Path path = Path.of("audit.csv");
            try {
                writeToCsv(lines, path);
            } catch(Exception e){
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePrenumeById(UUID id, Elev newElev){
        String updateSql = "UPDATE elev SET prenume=? WHERE nr_matricol=?";

        try(Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql)){
            preparedStatement.setString(1, newElev.getPrenume());
            preparedStatement.setString(2, id.toString());

            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost actualizate " + nr + " linii.");

            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{LocalDate.now().toString(), "update", "elev"});

            Path path = Path.of("audit.csv");
            try {
                writeToCsv(lines, path);
            } catch(Exception e){
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewElev(Elev elev) {
        String insertSql = "INSERT INTO elev VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){
            preparedStatement.setString(1, elev.getNrMatricol().toString());
            preparedStatement.setString(2, elev.getNume());
            preparedStatement.setString(3, elev.getPrenume());
            preparedStatement.setString(4, elev.getCnp());
            preparedStatement.setDate(5, Date.valueOf(elev.getDataNastere()));
            if (elev.getStilInvatare() != null)
            {
                preparedStatement.setString(6, elev.getStilInvatare().toString());
            }
            else {
                preparedStatement.setNull(6, Types.VARCHAR, null);
            }
            preparedStatement.setString(7, elev.getAdresa().getId().toString());

            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost inserate " + nr + " linii.");

            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{LocalDate.now().toString(), "insert", "elev"});

            Path path = Path.of("audit.csv");
            try {
                writeToCsv(lines, path);
            } catch(Exception e){
                e.printStackTrace();
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addAllFromGivenList(List<Elev> elevi){
        elevi.forEach(this::addNewElev);
    }

    @Override
    public List<Elev> getAll() {
        String selectSql = "SELECT * FROM elev e, adresa a WHERE e.id_adresa = a.id";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            return elevMapper.mapToElevList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();

    }


    private void writeToCsv(List<String[]> lines, Path allLinesPath) throws Exception {
        // Suppose you have a list of String[] arrays representing rows in a CSV file, like this:

        // To write this data to a CSV file using CsvWriter, you can write the following code:

        try {
            CsvWriter csvWriter = CsvWriter.getInstance();

           // Path allLinesPath = Paths.get("all_lines.csv");
            //String allLinesContents = csvWriter.executeAllLines(lines);
            String allLinesContents = csvWriter.writeAllLines(lines, allLinesPath);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
