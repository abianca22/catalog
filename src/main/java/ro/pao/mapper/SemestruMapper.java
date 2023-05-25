package ro.pao.mapper;

import ro.pao.model.Semestru;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SemestruMapper {
    private static final SemestruMapper INSTANCE = new SemestruMapper();

    private SemestruMapper(){
    }

    public Optional<Semestru> mapToSemetru (ResultSet resultSet) throws SQLException{
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

    public List<Semestru> mapToSemetruList (ResultSet resultSet) throws SQLException{
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
}
