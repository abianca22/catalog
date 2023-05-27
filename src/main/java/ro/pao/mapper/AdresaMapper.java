package ro.pao.mapper;

import ro.pao.exceptions.NoObject;
import ro.pao.model.Adresa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class AdresaMapper extends MapperTemplate<Adresa> {
    private static final AdresaMapper INSTANCE = new AdresaMapper();

    private Logger logger = Logger.getLogger(AdresaMapper.class.getName());
    private AdresaMapper(){
    }

    public static AdresaMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Adresa> mapToObject (ResultSet resultSet) throws  SQLException {
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
        return Optional.empty();
    }

    @Override
    public List<Adresa> mapToObjectList(ResultSet resultSet) throws SQLException, NoObject{
        List<Adresa> listaAdrese = new ArrayList<>();
        int nr = 0;

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
            nr ++;
        }

        if (nr == 0){
            throw new NoObject("Cererea nu a returnat niciun rezultat!");
        }
        return listaAdrese;
    }

    public Optional<Adresa> mapToAdresa(ResultSet resultSet) throws  SQLException{
        return INSTANCE.mapToObject(resultSet);
    }

    public List<Adresa> mapToAdresaList(ResultSet resultSet) throws SQLException, NoObject{
        return INSTANCE.mapToObjectList(resultSet);
    }
}
