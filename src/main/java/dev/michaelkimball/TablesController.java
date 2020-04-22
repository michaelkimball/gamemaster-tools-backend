package dev.michaelkimball;

import dev.michaelkimball.table.model.Table;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addTable(Table table) {
        Table.persist(table);
        return ResponseEntity.ok("Table Saved.");
    }


}