package ro.pao.mapper;

import ro.pao.exceptions.IdNotFound;
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

public class ElevMapper implements Mapper<Elev> {
    private static final ElevMapper INSTANCE = new ElevMapper();

    private ElevMapper(){
    }

    public static ElevMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Elev> mapToObjectList(ResultSet resultSet) throws SQLException {
        List<Elev> listaElevi = new ArrayList<>();
        while (resultSet.next()) {
            listaElevi.add(
                    Elev.builder()
                            .nrMatricol(UUID.fromString(resultSet.getString(1)))
                            .nume(resultSet.getString(2))
                            .prenume(resultSet.getString(3))
                            .cnp(resultSet.getString(4))
                            .dataNastere(resultSet.getDate(5).toLocalDate())
                            .adresa(new Adresa(UUID.fromString(resultSet.getString("id_adresa")),
                                    resultSet.getString("judet"),
                                    resultSet.getString("localitate"),
                                    resultSet.getString("strada"),
                                    resultSet.getInt("numar"),
                                    null,
                                    null))
                            .build()
            );
        }

        return listaElevi;
    }

    @Override
    public Optional<Elev> mapToObject(ResultSet resultSet) throws SQLException, IdNotFound {
        boolean exista = false;
        if (resultSet.next()) {
            exista = true;
            return Optional.of(
                    Elev.builder()
                            .nrMatricol(UUID.fromString(resultSet.getString(1)))
                            .nume(resultSet.getString(2))
                            .prenume(resultSet.getString(3))
                            .cnp(resultSet.getString((4)))
                            .dataNastere(resultSet.getDate(5).toLocalDate())
                            .adresa(new Adresa(UUID.fromString(resultSet.getString("id_adresa")),
                                    resultSet.getString("judet"),
                                    resultSet.getString("localitate"),
                                    resultSet.getString("strada"),
                                    resultSet.getInt("numar"),
                                    null,
                                    null))
                            .build()
            );
        }
        if(!exista){
            throw new IdNotFound("Nu a fost gasit niciun elev cu acest numar matricol!");
        }
        return Optional.empty();
    }

    public Optional<Elev> mapToElev(ResultSet resultSet) throws SQLException, IdNotFound{
        return this.mapToObject(resultSet);
    }

    public List<Elev> mapToElevList (ResultSet resultSet) throws SQLException{
        return this.mapToObjectList(resultSet);
    }
}

