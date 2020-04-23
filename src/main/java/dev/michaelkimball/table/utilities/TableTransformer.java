package dev.michaelkimball.table.utilities;

import dev.michaelkimball.table.model.Table;
import dev.michaelkimball.table.model.TableDTO;

public class TableTransformer {
    public static Table transform(TableDTO newTable) {
        return new Table()
                .withId(newTable.id)
                .withName(newTable.name)
                .withItems(newTable.items);
    }
    public static TableDTO transform(Table newTable){
        return new TableDTO()
                .withId(newTable.getId())
                .withName(newTable.getName())
                .withItems(newTable.getItems());
    }
}
