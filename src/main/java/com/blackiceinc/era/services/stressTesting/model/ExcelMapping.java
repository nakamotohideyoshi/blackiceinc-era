package com.blackiceinc.era.services.stresstesting.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExcelMapping {

    private String column;
    private String row;
    private String group;
    private String value;

    public ExcelMapping(String column, String row, String group, String value) {
        this.column = column;
        this.row = row;
        this.group = group;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExcelMapping{" +
                "column='" + column + '\'' +
                ", row='" + row + '\'' +
                ", group='" + group + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
