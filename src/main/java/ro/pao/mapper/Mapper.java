package ro.pao.mapper;

import ro.pao.model.Adresa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Mapper<T> {
    Optional<T> mapToObject (ResultSet resultSet) throws SQLException;

    List<T> mapToObjectList(ResultSet resultSet) throws SQLException;
}
