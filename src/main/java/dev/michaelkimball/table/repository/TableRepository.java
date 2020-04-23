package dev.michaelkimball.table.repository;

import dev.michaelkimball.table.model.Table;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends PanacheMongoRepositoryBase<Table, String> {
}
