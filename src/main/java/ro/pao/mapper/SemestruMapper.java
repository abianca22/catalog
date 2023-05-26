package ro.pao.mapper;

import ro.pao.model.Semestru;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SemestruMapper implements Mapper<Semestru> {
    private static final SemestruMapper INSTANCE = new SemestruMapper();

    private SemestruMapper(){
    }

    public static SemestruMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Semestru> mapToObject (ResultSet resultSet) throws SQLException{
        if(resultSet.next()){
            return Optional.of(
                    Semestru.builder()
                            .id(UUID.fromString(resultSet.getString(1)))
                            .build()
            );
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public List<Semestru> mapToObjectList (ResultSet resultSet) throws SQLException{
        List<Semestru> listaSemestre = new ArrayList<>();
        while (resultSet.next()){
             listaSemestre.add(
                    Semestru.builder()
                            .id(UUID.fromString(resultSet.getString(1)))
                            .build()
            );
        }
        return listaSemestre;
    }

    public Optional<Semestru> mapToSemestru (ResultSet resultSet) throws SQLException{
        return this.mapToObject(resultSet);
    }

    public List<Semestru> mapToSemestruList (ResultSet resultSet) throws SQLException{
        return this.mapToObjectList(resultSet);
    }
}
