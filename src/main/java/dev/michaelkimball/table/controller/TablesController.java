package dev.michaelkimball.table.controller;

import dev.michaelkimball.table.model.TableDTO;
import dev.michaelkimball.table.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/tables")
public class TablesController {

    private final TableService tableService;

    @Autowired
    public TablesController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public List<TableDTO> getAllTables() {
        return tableService.getAllTables();
    }

    @PostMapping()
    @Transactional
    public void addTable(TableDTO table) {
        tableService.addTable(table);
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