package ro.pao.repository.impl;

import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.AdresaMapper;
import ro.pao.mapper.ElevMapper;
import ro.pao.model.Adresa;
import ro.pao.model.Elev;
import ro.pao.model.ExampleClass;
import ro.pao.repository.AdresaRepository;
import ro.pao.repository.ElevRepository;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ElevRepositoryImpl implements ElevRepository {

    private static final ElevMapper elevMapper = ElevMapper.getInstance();

    private static final AdresaMapper adresaMapper = AdresaMapper.getInstance();

    @Override
    public Optional<Elev> getElevById(UUID id){
        String selectSql = "SELECT * FROM elev e, adresa a WHERE e.id_adresa = a.id";

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
    public void deleteElevById(UUID id) {
        String deleteSql = "DELETE FROM elev WHERE nr_matricol=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)){
            preparedStatement.setString(1, id.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNumeById(UUID id, Elev newElev) {
        String updateSql = "UPDATE elev SET nume=? WHERE nr_matricol=?";

        try(Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql)){
            preparedStatement.setString(1, newElev.getNume());
            preparedStatement.setString(2, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePrenumeById(UUID id, Elev newElev) {
        String updateSql = "UPDATE elev SET prenume=? WHERE nr_matricol=?";

        try(Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql)){
            preparedStatement.setString(1, newElev.getPrenume());
            preparedStatement.setString(2, id.toString());

            preparedStatement.executeUpdate();
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

            preparedStatement.executeUpdate();
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


}
