package ro.pao.mapper;

import ro.pao.model.Adresa;
import ro.pao.model.Profesor;

import java.io.LineNumberInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AdresaMapper implements Mapper<Adresa> {
    private static final AdresaMapper INSTANCE = new AdresaMapper();

    private AdresaMapper(){
    }

    public static AdresaMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Adresa> mapToObject (ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return Optional.of(
                   Adresa.builder()
                           .id(UUID.fromString(resultSet.getString(1)))
                           .judet(resultSet.getString(2))
                           .localitate(resultSet.getString(3))
                           .strada(resultSet.getString(4))
                           .numar(resultSet.getInt(5))
                           .codPostal(resultSet.getInt(6))
                           .tara(resultSet.getString(7))
                           .build()
            );
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public List<Adresa> mapToObjectList(ResultSet resultSet) throws SQLException{
        List<Adresa> listaAdrese = new ArrayList<>();
        while(resultSet.next()){
            listaAdrese.add(
                    Adresa.builder()
                            .id(UUID.fromString(resultSet.getString(1)))
                            .judet(resultSet.getString(2))
                            .localitate(resultSet.getString(3))
                            .strada(resultSet.getString(4))
                            .numar(resultSet.getInt(5))
                            .codPostal(resultSet.getInt(6))
                            .tara(resultSet.getString(7))
                            .build());
        }
        return listaAdrese;
    }

    public Optional<Adresa> mapToAdresa(ResultSet resultSet) throws SQLException{
        return this.mapToObject(resultSet);
    }

    public List<Adresa> mapToAdresaList(ResultSet resultSet) throws SQLException{
        return this.mapToObjectList(resultSet);
    }
}
