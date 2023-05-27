package ro.pao.mapper;

import ro.pao.exceptions.IdNotFound;
import ro.pao.exceptions.NoObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class MapperTemplate<T> {
    public abstract Optional<T> mapToObject (ResultSet resultSet) throws IdNotFound, SQLException;

    public abstract List<T> mapToObjectList(ResultSet resultSet) throws NoObject, IdNotFound, SQLException;
}
