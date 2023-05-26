package ro.pao.repository.impl;

import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.AdresaMapper;
import ro.pao.model.Adresa;
import ro.pao.model.ExampleClass;
import ro.pao.repository.AdresaRepository;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AdresaRepositoryImpl implements AdresaRepository {

    private static final AdresaMapper adresaMapper = AdresaMapper.getInstance();

    @Override
    public Optional<Adresa> getAdresaById(UUID id){
        String selectSql = "SELECT * FROM adresa WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
             preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return adresaMapper.mapToAdresa(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void deleteAdresaById(UUID id) {
        String deleteSql = "DELETE FROM adresa WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)){
            preparedStatement.setString(1, id.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStradaById(UUID id, Adresa newAdresa) {
        String updateSql = "UPDATE adresa SET strada=? WHERE id=?";

        try(Connection connection = DatabaseConfiguration.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql)){
            preparedStatement.setString(1, newAdresa.getStrada());
            preparedStatement.setString(2, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNumarById(UUID id, Adresa newAdresa) {
        String updateSql = "UPDATE adresa SET numar=? WHERE id=?";

        try(Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql)){
            preparedStatement.setInt(1, newAdresa.getNumar());
            preparedStatement.setString(2, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewAdresa(Adresa adresa) {
        String insertSql = "INSERT INTO adresa VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){
            preparedStatement.setString(1, adresa.getId().toString());
            preparedStatement.setString(2, adresa.getJudet());
            preparedStatement.setString(3, adresa.getLocalitate());
            if (adresa.getStrada() !=  null) {
                preparedStatement.setString(4, adresa.getStrada());
            }
            else {
                preparedStatement.setNull(4, Types.VARCHAR, null);
            }
            preparedStatement.setInt(5, adresa.getNumar());
            if (adresa.getCodPostal() != null) {
                preparedStatement.setInt(6, adresa.getCodPostal());
            }
            else {
                preparedStatement.setNull(6, Types.BIGINT, null);

            }

            if(adresa.getTara() != null) {
                preparedStatement.setString(7, adresa.getTara());
            }
            else {
                preparedStatement.setNull(7, Types.VARCHAR, null);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Adresa> getAll() {
        String selectSql = "SELECT * FROM adresa";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            return adresaMapper.mapToAdresaList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();

    }

    @Override
    public void addAllFromGivenList(List<Adresa> listaAdrese){
        listaAdrese.forEach(this::addNewAdresa);
    }
}
