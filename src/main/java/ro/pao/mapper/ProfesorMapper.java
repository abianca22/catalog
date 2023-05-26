package ro.pao.mapper;

import ro.pao.model.Profesor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfesorMapper implements Mapper<Profesor> {
    private static final ProfesorMapper INSTANCE = new ProfesorMapper();

    private ProfesorMapper(){
    }

    public static ProfesorMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<Profesor> mapToObject (ResultSet resultSet) throws SQLException{
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

    @Override
    public List<Profesor> mapToObjectList(ResultSet resultSet) throws SQLException{
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

    public Optional<Profesor> mapToProfesor(ResultSet resultSet) throws SQLException{
        return this.mapToProfesor(resultSet);
    }

    public List<Profesor> mapToProfesorList(ResultSet resultSet) throws SQLException{
        return this.mapToObjectList(resultSet);
    }
}
