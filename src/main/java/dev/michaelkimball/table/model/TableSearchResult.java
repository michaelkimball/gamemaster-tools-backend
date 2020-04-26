package dev.michaelkimball.table.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TableSearchResult {
    public List<TableDTO> results;
    public int pageSize;
    public int totalCount;
    public TableSearchResult withResults(List<TableDTO> results){
        this.results = results;
        return this;
    }
    public TableSearchResult withPageSize(int pageSize){
        this.pageSize = pageSize;
        return this;
    }
    public TableSearchResult withTotalCount(int totalCount){
        this.totalCount = totalCount;
        return this;
    }
}
