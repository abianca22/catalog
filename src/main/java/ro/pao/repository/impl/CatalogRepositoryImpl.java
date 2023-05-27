package ro.pao.repository.impl;

import ro.pao.application.csv.CsvWriter;
import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.CatalogMapper;
import ro.pao.mapper.SemestruMapper;
import ro.pao.model.Catalog;
import ro.pao.model.Semestru;
import ro.pao.repository.CatalogRepository;
import ro.pao.repository.SemestruRepository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatalogRepositoryImpl implements CatalogRepository {

    private static final CatalogMapper catalogMapper = CatalogMapper.getInstance();

    private Logger logger = Logger.getLogger(CatalogRepositoryImpl.class.getName());
    @Override
    public Optional<Catalog> getCatalogById(UUID id){
        String selectSql = "SELECT * FROM catalog, semestru where id=? and (id_semestru1=id or id_semestru2=id)";
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){

            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            return catalogMapper.mapToCatalog(resultSet);

        } catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deleteCatalogById(UUID id){
        String deleteSql = "DELETE FROM catalog WHERE id=?";

        try(Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)){

            preparedStatement.setString(1, id.toString());
            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost sterse " + nr + " linii.");

            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{"Au fost sterse " + nr + " linii din catalog."});

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
    public void updateClasaById(UUID id, Catalog catalog){
        String updateSql = "UPDATE catalog SET clasa=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement =  connection.prepareStatement(updateSql)){

            preparedStatement.setInt(1, catalog.getClasa());
            preparedStatement.setString(2, id.toString());

            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost actualizate " + nr + " linii.");
            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{"Au fost actualizate " + nr + " linii in catalog."});

            Path path = Path.of("audit.csv");
            try {
                writeToCsv(lines, path);
            } catch(Exception e){
                e.printStackTrace();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addNewCatalog(Catalog catalog){
        String insertSql = "INSERT INTO catalog(id, clasa, id_semestru1, id_semestru2) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){

            preparedStatement.setString(1, catalog.getId().toString());
            preparedStatement.setInt(2, catalog.getClasa());
            preparedStatement.setString(3, catalog.getSemestru1().getId().toString());
            preparedStatement.setString(4, catalog.getSemestru2().getId().toString());
            int nr = preparedStatement.executeUpdate();
            logger.info("Au fost inserate " + nr + " linii.");

            List<String[]> lines = new ArrayList<>();
            lines.add(new String[]{"Au fost inserate " + nr + " linii in catalog."});

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
    public List<Catalog> getAll(){
        String selectSql = "SELECT * FROM catalog";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){

            ResultSet resultSet = preparedStatement.executeQuery();

            return catalogMapper.mapToCatalogList(resultSet);

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

