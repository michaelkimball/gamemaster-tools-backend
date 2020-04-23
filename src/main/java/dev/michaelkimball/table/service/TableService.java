package dev.michaelkimball.table.service;

import dev.michaelkimball.table.model.TableDTO;
import dev.michaelkimball.table.repository.TableRepository;
import dev.michaelkimball.table.utilities.TableTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableService {

    private final TableRepository tableRepository;

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<TableDTO> getAllTables() {
        return tableRepository.listAll()
                .stream()
                .map(TableTransformer::transform)
                .collect(Collectors.toList());
    }

    public void addTable(TableDTO newTable) {
        tableRepository.persist(TableTransformer.transform(newTable));
    }

    public void updateTable(String id, TableDTO newTable) {
        Optional.ofNullable(tableRepository.findById(id))
                .orElseThrow(() -> new TableCouldNotBeFoundError(id))
                .withName(newTable.name)
                .withItems(newTable.items)
                .update();
    }

    public void deleteTable(String id) {
        Optional.ofNullable(tableRepository.findById(id))
                .orElseThrow(() -> new TableCouldNotBeFoundError(id))
                .delete();
    }

    private static class TableCouldNotBeFoundError extends RuntimeException {
        private static final String message = "Could not find table with id: %s";

        public TableCouldNotBeFoundError(String id) {
            super(String.format(TableCouldNotBeFoundError.message, id));
        }

        public TableCouldNotBeFoundError(String id, Throwable cause) {
            super(String.format(TableCouldNotBeFoundError.message, id), cause);
        }

        protected TableCouldNotBeFoundError(String id, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(String.format(TableCouldNotBeFoundError.message, id), cause, enableSuppression, writableStackTrace);
        }
    }
}
