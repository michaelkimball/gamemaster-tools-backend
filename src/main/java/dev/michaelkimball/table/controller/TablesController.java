package dev.michaelkimball.table.controller;

import dev.michaelkimball.table.model.Item;
import dev.michaelkimball.table.model.TableDTO;
import dev.michaelkimball.table.model.TableImportRequest;
import dev.michaelkimball.table.model.TableSearchResult;
import dev.michaelkimball.table.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/tables")
public class TablesController {

    private final TableService tableService;

    @Autowired
    public TablesController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public TableSearchResult getAllTables(@RequestParam("page") int pageNumber, @RequestParam("name") String name) {
        return tableService.getAllTables(pageNumber, name);
    }

    @PostMapping()
    @Transactional
    public void addTable(TableDTO table) {
        tableService.addTable(table);
    }

    @PostMapping("/import")
    @Transactional
    public void addTables(TableImportRequest tableImport) {
        tableImport.tables.stream().peek(table -> table.items = IntStream.range(0, table.items.size())
                .mapToObj(index -> table.items.get(index).withPosition(index + 1).withWeight(1))
                .collect(Collectors.toList())).forEach(tableService::addTable);
    }

    @PutMapping("/{id}")
    @Transactional
    public void updateTable(@PathVariable("id") String id, TableDTO table){
        tableService.updateTable(id, table);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteTable(@PathVariable("id") String id){
        tableService.deleteTable(id);
    }

}