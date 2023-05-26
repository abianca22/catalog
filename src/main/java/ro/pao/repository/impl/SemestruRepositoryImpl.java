package ro.pao.repository.impl;

import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.SemestruMapper;
import ro.pao.model.Semestru;
import ro.pao.repository.SemestruRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class SemestruRepositoryImpl implements SemestruRepository {

   private static final SemestruMapper semestruMapper = SemestruMapper.getInstance();
    @Override
    public Optional<Semestru> getSemestruById(UUID id){
        String selectSql = "SELECT * FROM semestru where id=?";
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){

            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            return semestruMapper.mapToSemestru(resultSet);

        } catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deleteSemestruById(UUID id){
        String deleteSql = "DELETE FROM semestru WHERE id=?";

        try(Connection connection = DatabaseConfiguration.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)){

            preparedStatement.setString(1, id.toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSemestruById(UUID id, Semestru newSemestru){
        String updateSql = "UPDATE semestru SET id=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
        PreparedStatement preparedStatement =  connection.prepareStatement(updateSql)){

            preparedStatement.setString(1, id.toString());
            preparedStatement.setString(2, newSemestru.getId().toString());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addNewSemestru(Semestru semestru){
        String insertSql = "INSERT INTO semestru VALUES (?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){

            preparedStatement.setString(1, semestru.getId().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Semestru> getAll(){
        String selectSql = "SELECT * FROM semestru";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){

            ResultSet resultSet = preparedStatement.executeQuery();

            return semestruMapper.mapToSemestruList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public void addAllFromGivenList(List<Semestru> semestre){
        semestre.forEach(this::addNewSemestru);
    }
}
