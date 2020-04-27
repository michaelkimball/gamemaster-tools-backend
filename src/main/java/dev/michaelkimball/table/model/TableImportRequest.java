package dev.michaelkimball.table.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TableImportRequest {
    public List<TableDTO> tables;
}
