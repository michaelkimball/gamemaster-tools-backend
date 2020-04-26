package dev.michaelkimball.table.service;

import dev.michaelkimball.table.model.Table;
import dev.michaelkimball.table.model.TableDTO;
import dev.michaelkimball.table.model.TableSearchResult;
import dev.michaelkimball.table.repository.TableRepository;
import dev.michaelkimball.table.utilities.TableTransformer;
import io.quarkus.mongodb.panache.PanacheQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableService {
    public static final int PAGE_SIZE = 6;
    private final TableRepository tableRepository;
    private final Logger log = LoggerFactory.getLogger(TableService.class);

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public TableSearchResult getAllTables(int pageNumber, String name){
        PanacheQuery<Table> query;
        if(Objects.isNull(name) || name.isBlank()){
            query = tableRepository.findAll();
            return getAllTables(query, pageNumber);
        }
        query = tableRepository.find("name LIKE ?1", name);
        return getAllTables(query, pageNumber);
    }

    public TableSearchResult getAllTables(PanacheQuery<Table> query, int pageNumber) {
        int totalPages = (int) Math.ceil((double)query.count() / PAGE_SIZE) - 1;
        if(pageNumber > totalPages){
            throw new PageSearchedExceedsTotalPagesException(pageNumber, totalPages + 1);
        }
        return new TableSearchResult()
                .withResults(query
                    .list()
                    .stream()
                    .skip(pageNumber * PAGE_SIZE)
                    .limit(PAGE_SIZE)
                    .map(TableTransformer::transform)
                    .collect(Collectors.toList()))
                .withPageSize(PAGE_SIZE)
                .withTotalCount((int)query.count());
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

    private static class PageSearchedExceedsTotalPagesException extends RuntimeException {
        private static final String message = "%d exceeds total pages of tables %d";

        public PageSearchedExceedsTotalPagesException(int pageNumber, int totalPages) {
            super(String.format(PageSearchedExceedsTotalPagesException.message, pageNumber, totalPages));
        }
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
