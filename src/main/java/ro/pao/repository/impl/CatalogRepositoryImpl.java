package ro.pao.repository.impl;

import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.CatalogMapper;
import ro.pao.mapper.SemestruMapper;
import ro.pao.model.Catalog;
import ro.pao.model.Semestru;
import ro.pao.repository.CatalogRepository;
import ro.pao.repository.SemestruRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class CatalogRepositoryImpl implements CatalogRepository {

    private static final CatalogMapper catalogMapper = CatalogMapper.getInstance();
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
            preparedStatement.executeUpdate();

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

            preparedStatement.executeUpdate();

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
            preparedStatement.executeUpdate();

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
}

