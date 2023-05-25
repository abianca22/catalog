package ro.pao.mapper;

import ro.pao.model.Catalog;
import ro.pao.model.Semestru;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CatalogMapper {
    private static final CatalogMapper INSTANCE = new CatalogMapper();

    private CatalogMapper(){
    }

    public static CatalogMapper getInstance() {
        return INSTANCE;
    }

    public Optional<Catalog> mapToCatalog (ResultSet resultSet, Semestru semestru1, Semestru semestru2) throws SQLException{
        if (resultSet.next()) {
            return Optional.of(
                    Catalog.builder()
                            .id(UUID.fromString(resultSet.getString(1)))
                            .clasa(resultSet.getInt(2))
                            .literaClasa(resultSet.getString(3).charAt(0))
                            .semestru1(semestru1)
                            .semestru2(semestru2)
                            .build()
            );
        }
        else {
            return Optional.empty();
        }
    }

    public List<Catalog> mapToCatalogList (ResultSet resultSet, Semestru semestru1, Semestru semestru2) throws SQLException{
        List<Catalog> listaCataloage = new ArrayList<>();
        while (resultSet.next()) {
           listaCataloage.add(
                    Catalog.builder()
                            .id(UUID.fromString(resultSet.getString(1)))
                            .clasa(resultSet.getInt(2))
                            .literaClasa(resultSet.getString(3).charAt(0))
                            .semestru1(semestru1)
                            .semestru2(semestru2)
                            .build()
            );
        }
        return listaCataloage;
    }
}
