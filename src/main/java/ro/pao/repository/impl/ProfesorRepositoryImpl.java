package ro.pao.repository.impl;

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
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfesorRepositoryImpl implements ProfesorRepository{

    private static final ProfesorMapper profesorMapper = ProfesorMapper.getInstance();

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
            preparedStatement.executeUpdate();
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

            preparedStatement.executeUpdate();
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

            preparedStatement.executeUpdate();
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
}
