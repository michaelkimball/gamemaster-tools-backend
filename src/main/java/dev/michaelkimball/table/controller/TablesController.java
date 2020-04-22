package dev.michaelkimball.table.controller;

import dev.michaelkimball.table.model.Table;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/tables")
public class TablesController {

    @GetMapping
    public List<Table> getAllTables() {
        return Table.listAll();
    }

    @PostMapping()
    @Transactional
    public void addTable(Table table) {
        Table.persist(table);
    }

    @PutMapping("/{id}")
    @Transactional
    public void updateTable(@PathVariable("id") String id, Table table){
        Table.update(table);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteTable(@PathVariable("id") String id){
        Table.delete("id", id);
    }

}