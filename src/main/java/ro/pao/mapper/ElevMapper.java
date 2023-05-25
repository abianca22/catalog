package ro.pao.mapper;

import ro.pao.model.Adresa;
import ro.pao.model.Elev;
import ro.pao.model.ExampleClass;
import ro.pao.model.enums.EnumExample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ElevMapper {
    private static final ElevMapper INSTANCE = new ElevMapper();

    private ElevMapper(){
    }

    public static ElevMapper getInstance() {
        return INSTANCE;
    }


    public Optional<Elev> mapToElev(ResultSet resultSet, Adresa adresa) throws SQLException{
        if (resultSet.next()) {
            return Optional.of(
                    Elev.builder()
                            .nrMatricol(UUID.fromString(resultSet.getString(1)))
                            .nume(resultSet.getString(2))
                            .prenume(resultSet.getString(3))
                            .cnp(resultSet.getString((4)))
                            .dataNastere(resultSet.getDate(5).toLocalDate())
                            .adresa(adresa)
                            .build()
            );
        }
        else {
            return Optional.empty();
        }
    }
    public List<Elev> mapToElevList(ResultSet resultSet, Adresa adresa) throws SQLException {
        List<Elev> listaElevi = new ArrayList<>();
        while (resultSet.next()) {
            listaElevi.add(
                    Elev.builder()
                            .nrMatricol(UUID.fromString(resultSet.getString(1)))
                            .nume(resultSet.getString(2))
                            .prenume(resultSet.getString(3))
                            .cnp(resultSet.getString(4))
                            .dataNastere(resultSet.getDate(5).toLocalDate())
                            .adresa(adresa)
                            .build()
            );
        }

        return listaElevi;
    }
}

