package ro.pao.mapper;

import ro.pao.model.Profesor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfesorMapper {
    private static final ProfesorMapper INSTANCE = new ProfesorMapper();

    private ProfesorMapper(){
    }

    public Optional<Profesor> mapToProfesor (ResultSet resultSet) throws SQLException{
        if (resultSet.next()){
            return Optional.of(
                    Profesor.builder()
                            .id(UUID.fromString(resultSet.getString(1)))
                            .nume(resultSet.getString(2))
                            .prenume(resultSet.getString(3))
                            .cnp(resultSet.getString(4))
                            .dataNastere(resultSet.getDate(5).toLocalDate())
                            .build()
            );
        }
        else {
            return Optional.empty();
        }
    }

    public List<Profesor> mapToProfesorList(ResultSet resultSet) throws SQLException{
        List<Profesor> listaProfesori = new ArrayList<>();
        while(resultSet.next()){
            listaProfesori.add(
                    Profesor.builder()
                    .id(UUID.fromString(resultSet.getString(1)))
                    .nume(resultSet.getString(2))
                    .prenume(resultSet.getString(3))
                    .cnp(resultSet.getString(4))
                    .dataNastere(resultSet.getDate(5).toLocalDate())
                    .build());
        }
        return listaProfesori;
    }
}
