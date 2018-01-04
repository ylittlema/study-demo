package com.origin.gener;

/**
 * 表字段类
 */
public class ColumnData {

    private String columnName;    //表中列名称
    private String propertyName;  //数据库字段转成实体类属性的名字
    private String dataType;      //字段类型
    private String columnComment;  //字段备注

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.propertyName = getColumnNameToClassName(columnName);
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * 将表中的列名称 转换为类的字段名称
     * @param columnName
     * @return
     */
    public String getColumnNameToClassName(String columnName) {
        String[] split = columnName.split("_");
        if (split.length > 1) {
            StringBuffer sb = new StringBuffer();
            sb.append(split[0]);
            for (int i = 1; i < split.length; i++) {
                String tempTableName = split[i].substring(0, 1).toUpperCase() + split[i].substring(1, split[i].length());
                sb.append(tempTableName);
            }
            return sb.toString();
        } else {
            //String tempTables=split[0].substring(0, 1).toUpperCase()+split[0].substring(1, split[0].length());
            return columnName;
        }
    }
}
