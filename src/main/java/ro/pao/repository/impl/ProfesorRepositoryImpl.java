package ro.pao.repository.impl;

import ro.pao.application.csv.CsvWriter;
import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.AdresaMapper;
import ro.pao.mapper.ElevMapper;
import ro.pao.mapper.ProfesorMapper;
import ro.pao.model.Adresa;
import ro.pao.model.Elev;
import ro.pao.model.ExampleClass;
import ro.pao.model.Profesor;
import ro.pao.repository.AdresaRepository;
import ro.pao.repository.ElevRepository;
import ro.pao.repository.ProfesorRepository;

import javax.xml.transform.Result;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfesorRepositoryImpl implements ProfesorRepository{

    private static final ProfesorMapper profesorMapper = ProfesorMapper.getInstance();

    private Logger logger = Logger.getLogger(ProfesorRepositoryImpl.class.getName());
    @Override
    public Optional<Profesor> getProfById(UUID id){
        String selectSql = "SELECT * FROM profesor WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            return profesorMapper.mapToProfesor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void deleteProfById(UUID id) {
        String deleteSql = "DELETE FROM profesor WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)){
            preparedStatement.setString(1, id.toString());
            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost sterse " + nr + " linii.");

            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{"Au fost sterse " + nr + " linii din profesor."});

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
    public void updateProfById(UUID id, Profesor newProfesor) {
        String updateSql = "UPDATE profesor SET nume=? WHERE id=?";

        try(Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql)){
            preparedStatement.setString(1, newProfesor.getNume());
            preparedStatement.setString(2, id.toString());

            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost actualizate " + nr + " linii.");

            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{"Au fost actualizate " + nr + " linii in profesor."});
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
    public void addNewProf(Profesor profesor) {
        String insertSql = "INSERT INTO profesor VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){
            preparedStatement.setString(1, profesor.getId().toString());
            preparedStatement.setString(2, profesor.getNume());
            preparedStatement.setString(3, profesor.getPrenume());
            preparedStatement.setString(4, profesor.getCnp());
            preparedStatement.setDate(5, Date.valueOf(profesor.getDataNastere()));

            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost inserate " + nr + " linii.");

            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{"Au fost inserate " + nr + " linii in profesor."});

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
    public List<Profesor> getAll() {
        String selectSql = "SELECT * FROM profesor";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            return profesorMapper.mapToProfesorList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();

    }

    @Override
    public void addAllFromGivenList(List<Profesor> profesori){
        profesori.forEach(this::addNewProf);
    }
    private void writeToCsv(List<String[]> lines, Path allLinesPath) throws Exception {
        // Suppose you have a list of String[] arrays representing rows in a CSV file, like this:

        // To write this data to a CSV file using CsvWriter, you can write the following code:

        try {
            CsvWriter csvWriter = CsvWriter.getInstance();

            //Path allLinesPath = Paths.get("all_lines.csv");
            //String allLinesContents = csvWriter.executeAllLines(lines);
            String allLinesContents = csvWriter.writeAllLines(lines, allLinesPath);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
