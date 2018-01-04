package com.origin.gener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateBean {
    private Connection connection = null;
    static String url;
    static String username;
    static String password;
    static String rt = "\r\t";   //回车换行
    String SQLTables = "show tables";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("static-access")
    public void setMysqlInfo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * 获取数据库的链接
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 获取当前数据库的全部表名
     *
     * @return
     * @throws SQLException
     */
    public List<String> getTables() throws SQLException {
        Connection con = this.getConnection();
        PreparedStatement ps = con.prepareStatement(SQLTables);
        ResultSet rs = ps.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            String tableName = rs.getString(1);
            list.add(tableName);
        }
        rs.close();
        ps.close();
        con.close();
        return list;
    }

    /**
     * 查询表的字段，封装成List
     *
     * @param dbName    -数据库名
     * @param tableName -表名
     * @return
     * @throws SQLException
     */
    public List<ColumnData> getColumnDatas(String dbName, String tableName) throws SQLException {
        String SQLColumns = "select distinct COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT from information_schema.columns WHERE table_schema = '" + dbName + "' AND table_name =  '" + tableName + "' ";

        Connection con = this.getConnection();
        PreparedStatement ps = con.prepareStatement(SQLColumns);
        List<ColumnData> columnList = new ArrayList<ColumnData>();
        ResultSet rs = ps.executeQuery();
        StringBuffer str = new StringBuffer();
        StringBuffer getset = new StringBuffer();
        while (rs.next()) {
            String name = rs.getString(1);
            String type = rs.getString(2);
            String comment = rs.getString(3);
            type = this.getType(type);

            ColumnData cd = new ColumnData();
            cd.setColumnName(name);
            cd.setDataType(type);
            cd.setColumnComment(comment);
            columnList.add(cd);
        }
        argv = str.toString();
        method = getset.toString();
        rs.close();
        ps.close();
        con.close();
        return columnList;
    }

    /**
     * 查询表字段关联/外键
     *
     * @param dbName
     * @param tableName
     * @param
     * @throws SQLException
     */
    public List<ReferencedData> getReferencedColumnDatas(String dbName, String tableName) throws SQLException {
        String SQLColumns = "select table_name,column_name,referenced_table_name,referenced_column_name from information_schema.key_column_usage WHERE table_schema = '" + dbName + "' AND table_name" +
                " =  '" + tableName + "' AND position_in_unique_constraint = 1";
        Connection con = this.getConnection();
        PreparedStatement ps = con.prepareStatement(SQLColumns);
        List<ReferencedData> referencedData = new ArrayList<ReferencedData>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String table_name = rs.getString(1);
            String column_name = rs.getString(2);
            String referenced_table_name = rs.getString(3);
            String referenced_column_name = rs.getString(4);

            ReferencedData rd = new ReferencedData();
            rd.setTableName(table_name);
            rd.setColumnNmae(column_name);
            rd.setReferencedTableName(referenced_table_name);
            rd.setReferencedColumnName(referenced_column_name);

            referencedData.add(rd);
        }
        rs.close();
        ps.close();
        con.close();
        return referencedData;
    }

    private String method;
    private String argv;

    /**
     * 生成实体Bean 的属性和set,get方法
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    public String getBeanFeilds(String dbName, String tableName) throws SQLException {
        List<ColumnData> dataList = getColumnDatas(dbName, tableName);
        List<ReferencedData> refDataList = getReferencedColumnDatas(dbName, tableName);
        StringBuffer str = new StringBuffer();
        StringBuffer getset = new StringBuffer();
        for (ColumnData d : dataList) {
            String name = d.getPropertyName();//d.getColumnName();
            String type = d.getDataType();
            String comment = d.getColumnComment();
//			type=this.getType(type);
            String maxChar = name.substring(0, 1).toUpperCase();
            str.append("\r\t").append("private ").append(type + " ").append(name).append(";//   ").append(comment);
            String method = maxChar + name.substring(1, name.length());

            getset.append("\r\t").append("@Column(name=\"" + d.getColumnName() + "\")");
            getset.append("\r\t").append("public ").append(type + " ").append("get" + method + "() {\r\t");
            getset.append("    return this.").append(name).append(";\r\t}");
            getset.append("\r\t").append("public void ").append("set" + method + "(" + type + " " + name + ") {\r\t");
            getset.append("    this." + name + "=").append(name).append(";\r\t}");
        }
        if (refDataList.size() > 0) {
            str.append("\r\t");
            for (ReferencedData rd : refDataList) {
                String type = rd.getPropertyDName() + "Entity"; //this.getTablesNameToClassName(rd.getReferencedTableName());
                String name = rd.getPropertyName();//this.getColumnNameToClassName(rd.getReferencedTableName());
                String comment = "";
                String maxChar = name.substring(0, 1).toUpperCase();
                String method = maxChar + name.substring(1, name.length());
                str.append("\r\t").append("private ").append(type + " ").append(name).append(";//   ").append(comment);
                getset.append("\r\t").append("public ").append(type + " ").append("get" + method + "() {\r\t");
                getset.append("    return this.").append(name).append(";\r\t}");
                getset.append("\r\t").append("public void ").append("set" + method + "(" + type + " " + name + ") {\r\t");
                getset.append("    this." + name + "=").append(name).append(";\r\t}");
            }
        }

        argv = str.toString();
        method = getset.toString();
        return argv + "\r\t" + method;
    }

    /**
     * 生成实体Model 的属性和set,get方法
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    public String getModelFeilds(String dbName, String tableName) throws SQLException {
        List<ColumnData> dataList = getColumnDatas(dbName, tableName);
        List<ReferencedData> refDataList = getReferencedColumnDatas(dbName, tableName);
        StringBuffer str = new StringBuffer();
        StringBuffer getset = new StringBuffer();
        for (ColumnData d : dataList) {
            String name = d.getPropertyName();//d.getColumnName();
            String type = d.getDataType();
            String comment = d.getColumnComment();
//			type=this.getType(type);
            String maxChar = name.substring(0, 1).toUpperCase();
            str.append("\r\t").append("private ").append(type + " ").append(name).append(";//   ").append(comment);
            String method = maxChar + name.substring(1, name.length());

            //getset.append("\r\t").append("@Column(name=\""+d.getColumnName()+"\")");
            getset.append("\r\t").append("public ").append(type + " ").append("get" + method + "() {\r\t");
            getset.append("    return this.").append(name).append(";\r\t}");
            getset.append("\r\t").append("public void ").append("set" + method + "(" + type + " " + name + ") {\r\t");
            getset.append("    this." + name + "=").append(name).append(";\r\t}");
        }
        if (refDataList.size() > 0) {
            str.append("\r\t");
            for (ReferencedData rd : refDataList) {
                String type = rd.getPropertyDName() + "Entity"; //this.getTablesNameToClassName(rd.getReferencedTableName());
                String name = rd.getPropertyName();//this.getColumnNameToClassName(rd.getReferencedTableName());
                String comment = "";
                String maxChar = name.substring(0, 1).toUpperCase();
                String method = maxChar + name.substring(1, name.length());
                str.append("\r\t").append("private ").append(type + " ").append(name).append(";//   ").append(comment);
                getset.append("\r\t").append("public ").append(type + " ").append("get" + method + "() {\r\t");
                getset.append("    return this.").append(name).append(";\r\t}");
                getset.append("\r\t").append("public void ").append("set" + method + "(" + type + " " + name + ") {\r\t");
                getset.append("    this." + name + "=").append(name).append(";\r\t}");
            }
        }

        argv = str.toString();
        method = getset.toString();
        return argv + "\r\t" + method;
    }

    //    public List<Map<String,String>> getColumnsMap(String tableName) throws SQLException{
//    	String SQLColumns="select distinct COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT from information_schema.columns WHERE  table_schema = 'ssi' and table_name =  '"+tableName+"' ";
////    	String SQLColumns="desc "+tableName;
//    	Connection con=this.getConnection();
//    	PreparedStatement ps=con.prepareStatement(SQLColumns);
//    	List<Map<String,String>> listMap=new ArrayList<Map<String,String>>();
//        ResultSet rs=ps.executeQuery();
//        while(rs.next()){
//        	Map<String,String> columnsMap=new HashMap<String, String>();
////        	String name = rs.getString(1);
////			String type = rs.getString(2);
////			String comment = rs.getString(3);
//			
//			
//			String name = rs.getString(1);
//			String type = rs.getString(2);
//			String comment = rs.getString(3);
//			type=this.getType(type);
//			columnsMap.put("COLUMN_NAME", name);
//			columnsMap.put("DATA_TYPE", type);
//			columnsMap.put("COLUMN_COMMENT", comment);
//			listMap.add(columnsMap);
//        }
//		rs.close();
//		ps.close();
//		con.close();
//		return listMap;
//    }
    public String getType(String type) {
        type = type.toLowerCase();
        if ("char".equals(type) || "varchar".equals(type) || "mediumtext".equals(type) || "text".equals(type)) {
            return "String";
        } else if ("int".equals(type) || "mediumint".equals(type) || "tinyint".equals(type)) {
            return "Integer";
        } else if ("float".equals(type)) {
            return "Float";
        } else if ("double".equals(type)) {
            return "Double";
        } else if ("bigint".equals(type)) {
            return "Long";
        } else if ("decimal".equals(type)) {
            return "BigDecimal";
        } else if ("bit".equals(type)) {
            return "Boolean";
        } else if ("timestamp".equals(type) || "date".equals(type) || "datetime".equals(type)) {
            return "Date";
            //return "java.sql.Timestamp";
        }
        return null;
    }

    public void getPackage(int type, String createPath, String content, String packageName, String className, String extendsClassName, String... importName) throws Exception {
        if (null == packageName) {
            packageName = "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(packageName).append(";\r");
        sb.append("\r");
        for (int i = 0; i < importName.length; i++) {
            sb.append("import ").append(importName[i]).append(";\r");
        }
        sb.append("\r");
        sb.append("/**\r *  entity. @author wolf Date:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r */");
        sb.append("\r");
        sb.append("\rpublic class ").append(className);
        if (null != extendsClassName) {
            sb.append(" extends ").append(extendsClassName);
        }
        if (type == 1) { //bean
            sb.append(" ").append("implements java.io.Serializable {\r");
        } else {
            sb.append(" {\r");
        }
        sb.append("\r\t");
        sb.append("private static final long serialVersionUID = 1L;\r\t");
        String temp = className.substring(0, 1).toLowerCase();
        temp += className.substring(1, className.length());
        if (type == 1) {
            sb.append("private " + className + " " + temp + "; // entity ");
        }
        sb.append(content);
        sb.append("\r}");
        System.out.println(sb.toString());
        this.createFile(createPath, "", sb.toString());
    }

    /**
     * 表名转换成类名 每个首字母大写
     *
     * @param tableName 表名
     * @return
     */
    public String getTablesNameToClassName(String tableName) {
        String[] split = tableName.split("_");
        if (split.length > 1) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < split.length; i++) {
                String tempTableName = split[i].substring(0, 1).toUpperCase() + split[i].substring(1, split[i].length());
                sb.append(tempTableName);
            }
            System.out.println(sb.toString());
            return sb.toString();
        } else {
            String tempTables = split[0].substring(0, 1).toUpperCase() + split[0].substring(1, split[0].length());
            return tempTables;
        }
    }

    /**
     * 格式化处理列名字如goods_id 处理为goodsId
     *
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

    /**
     * @param path
     * @param fileName
     * @param str
     * @throws IOException
     */
    public void createFile(String path, String fileName, String str) throws IOException {
        FileWriter writer = new FileWriter(new File(path + fileName));
        writer.write(new String(str.getBytes("utf-8")));
        writer.flush();
        writer.close();
    }

    static String selectStr = "select ";
    static String from = " from ";

    /**
     * 生成sql语句
     *
     * @param dbName
     * @param tableName
     * @return
     * @throws Exception
     */
    public Map<String, Object> getAutoCreateSql(String dbName, String tableName) throws Exception {
        Map<String, Object> sqlMap = new HashMap<String, Object>();
        List<ColumnData> columnDatas = getColumnDatas(dbName, tableName);
        List<ReferencedData> referDatas = getReferencedColumnDatas(dbName, tableName);
        String columns = this.getColumnSplit(columnDatas);
        String propertys = this.getPropertySplit(columnDatas);
        String[] columnList = getColumnList(columns);  //表所有字段
        String[] propertList = getColumnList(propertys); //表字段对应实体属性名
        String columnFields = getColumnFields(columns); //表所有字段 按","隔开
        String queryColumnFields = getColumnFields(getQueryColumnSplit(columnDatas));//查询用的字段列表
        String insert = "insert into " + tableName + "(" + columns.replaceAll("\\|", ",") + ")\n\t\tvalues(#{" + propertys.replaceAll("\\|", "},#{") + "})";
        String addBatch = getAddBatchSql(tableName, columnList, propertList);
        String update = getUpdateSql(tableName, columnList, propertList);
        String updateSelective = getUpdateSelectiveSql(tableName, columnDatas);
        String selectById = getSelectByIdSql(tableName, columnList, propertList, referDatas);
        String delete = getDeleteSql(tableName, columnList, propertList);
        String deleteBatch = getDelBatchSql(tableName, columnList, propertList);

        sqlMap.put("columnList", columnList);
        sqlMap.put("priKey", columnList[0]);//主KEY
        sqlMap.put("properKey", propertList[0]);//实体属性名
        sqlMap.put("columnFields", columnFields);
        sqlMap.put("queryColumnFields", queryColumnFields);
        sqlMap.put("insert", insert.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
        sqlMap.put("addBatch", addBatch);
        sqlMap.put("update", update.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
        sqlMap.put("delete", delete);
        sqlMap.put("delBatchSql", deleteBatch);
        //sqlMap.put("delBatchSql2", getDelBatchSql2(tableName, columnList, propertList, referDatas));
        sqlMap.put("updateSelective", updateSelective);
        sqlMap.put("selectById", selectById);
        sqlMap.put("findListSql", getSelectByList(tableName, columnList, propertList, referDatas));
        sqlMap.put("findByAll", getSelectByAllSql(tableName, columnList, propertList, referDatas));
        sqlMap.put("findOneSql", getSelectOneSql(tableName, columnList, propertList, referDatas));
        sqlMap.put("queryByCount", getQueryByCountSql(tableName, columnList, propertList, referDatas));

        return sqlMap;
    }

    /**
     * delete
     *
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getDeleteSql(String tableName, String[] columnsList, String[] propertList) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("delete from ").append(tableName).append("\n\t\twhere ");
        sb.append(columnsList[0]).append(" = #{").append(propertList[0]).append("}");
        return sb.toString();
    }

    /**
     * addBatch 批量增加
     *
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getAddBatchSql(String tableName, String[] columnsList, String[] propertList) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into ").append(tableName).append("(\n\t\t\t");
        for (int i = 0; i < columnsList.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(columnsList[i]);
        }
        sb.append("\n\t\t) values\n\t\t");
        sb.append("<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\n\t\t\t(");
        for (int i = 0; i < columnsList.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append("#{item.").append(propertList[i]).append("}");
        }
        sb.append(")\n\t\t</foreach> ");
        return sb.toString();
    }

    /**
     * addBatch 批量增加
     *
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getDelBatchSql(String tableName, String[] columnsList, String[] propertList) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("delete from ").append(tableName).append("\n\t\t");
        sb.append("<where>\n\t\t\t").append(columnsList[0]).append(" in\n\t\t\t");
        sb.append("<foreach item=\"item\" index=\"index\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">\n\t\t\t\t");
        sb.append("#{item}");
        sb.append("\n\t\t\t</foreach>\n\t\t");
        sb.append("</where>");
        return sb.toString();
    }

    //别名
    private String[] tableAliases = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

    /**
     * 根据id查询
     *
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getSelectByIdSql(String tableName, String[] columnsList, String[] propertList, List<ReferencedData> referDatas) throws SQLException {
        StringBuffer sb = new StringBuffer(this.getSelectByAllSql(tableName, columnsList, propertList, referDatas));
        sb.append("\n\t\tWHERE ");
        sb.append(tableAliases[0]).append(".").append(columnsList[0]).append(" = #{").append(propertList[0]).append("}");
        return sb.toString();
    }

    /**
     * 查询符合条件的记录数
     *
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getQueryByCountSql(String tableName, String[] columnsList, String[] propertList, List<ReferencedData> referDatas) throws SQLException {
        StringBuffer sb = new StringBuffer(this.getSelectByAllSql(tableName, columnsList, propertList, referDatas));
        int index = sb.toString().indexOf("from");
        return "select count(1) " + sb.substring(index);
    }

    /**
     * 批量删除
     *
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getDelBatchSql2(String tableName, String[] columnsList, String[] propertList, List<ReferencedData> referDatas) throws SQLException {
        StringBuffer sb = new StringBuffer(this.getSelectByAllSql(tableName, columnsList, propertList, referDatas));
        int index = sb.toString().indexOf("from");
        return "delete " + sb.substring(index);
    }

    /**
     * 查询符合记录的第一条
     *
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getSelectOneSql(String tableName, String[] columnsList, String[] propertList, List<ReferencedData> referDatas) throws SQLException {
        StringBuffer sb = new StringBuffer(this.getSelectByAllSql(tableName, columnsList, propertList, referDatas));
        sb.append("\n\t\tLIMIT 0,1 ");
        return sb.toString();
    }

    /**
     * 查询所有SQL
     *
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getSelectByAllSql(String tableName, String[] columnsList, String[] propertList, List<ReferencedData> referDatas) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("select \n\t\t\t <include refid=\"Base_Column_List\" /> \n\t\t").append("from ").append(tableName).append(" ").append(tableAliases[0]);//.append("\n\t\t\t");
        if (referDatas.size() > 0) {
            for (int i = 0, size = referDatas.size(); i < size; i++) {
                ReferencedData refer = referDatas.get(i);
                sb.append("\n\t\t\t");
                sb.append("left join ");
                sb.append(refer.getReferencedTableName());
                sb.append(" ");
                sb.append(tableAliases[i + 1]);
                sb.append(" on(");
                sb.append(tableAliases[0]).append(".").append(refer.getColumnNmae()).append(" = ").append(tableAliases[i + 1]).append(".").append(refer.getReferencedColumnName()).append(")");
            }
        }
        return sb.toString();
    }

    /**
     * 查询记录
     *
     * @param tableName
     * @param columnsList
     * @param propertList
     * @param referDatas
     * @return
     */
    public String getSelectByList(String tableName, String[] columnsList, String[] propertList, List<ReferencedData> referDatas) {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ").append(tableName).append(" ").append(tableAliases[0]);
        if (referDatas.size() > 0) {
            for (int i = 0, size = referDatas.size(); i < size; i++) {
                ReferencedData refer = referDatas.get(i);
                sb.append("\n\t\t\t");
                sb.append("left join ");
                sb.append(refer.getReferencedTableName());
                sb.append(" ");
                sb.append(tableAliases[i + 1]);
                sb.append(" on(");
                sb.append(tableAliases[0]).append(".").append(refer.getColumnNmae()).append(" = ").append(tableAliases[i + 1]).append(".").append(refer.getReferencedColumnName()).append(")");
            }
        }
        return sb.toString();
    }

    /**
     * 获取所有的字段，并按","分割
     *
     * @param columns
     * @return
     * @throws SQLException
     */
    public String getColumnFields(String columns) throws SQLException {
        String fields = columns;
        if (fields != null && !"".equals(fields)) {
            fields = fields.replaceAll("[|]", ",");
        }
        return fields;
    }

    /**
     * @param columns
     * @return
     * @throws SQLException
     */
    public String[] getColumnList(String columns) throws SQLException {
        String[] columnList = columns.split("[|]");
        return columnList;
    }

    /**
     * 修改记录
     *
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getUpdateSql(String tableName, String[] columnsList, String[] propertList) throws SQLException {
        StringBuffer sb = new StringBuffer();

        for (int i = 1; i < columnsList.length; i++) {
            String column = columnsList[i];
            if ("CREATETIME".equals(column.toUpperCase()))
                continue;

            if ("UPDATETIME".equals(column.toUpperCase()))
                sb.append(column + "=now()");
            else
                sb.append(column + "=#{" + propertList[i] + "}");
            //最后一个字段不需要","
            if ((i + 1) < columnsList.length) {
                sb.append(",");
            }
        }
        String update = "update " + tableName + " \n\t\tset " + sb.toString() + " \n\t\twhere " + columnsList[0] + "=#{" + propertList[0] + "}";
        return update;
    }

    public String getUpdateSelectiveSql(String tableName, List<ColumnData> columnList) throws SQLException {
        StringBuffer sb = new StringBuffer();
        ColumnData cd = columnList.get(0); //获取第一条记录，主键
        sb.append("\t\t<trim  suffixOverrides=\",\" >\n");
        for (int i = 1; i < columnList.size(); i++) {
            ColumnData data = columnList.get(i);
            String columnName = data.getColumnName();
            String propertyName = data.getPropertyName();
            sb.append("\t\t\t<if test=\"").append(propertyName).append(" != null");
            //String 还要判断是否为空，
            //if("String" == data.getDataType()){
            //	sb.append(" and ").append(propertyName).append(" != ''");
            //}
            sb.append(" \"> ");
            sb.append(columnName + "=#{" + propertyName + "},");
            sb.append("</if>\n");
        }
        sb.append("\t\t</trim>");
        String update = "update " + tableName + " set \n" + sb.toString() + "\n\t\twhere " + cd.getColumnName() + "=#{" + cd.getPropertyName() + "}";
        return update;
    }


    /**
     * 获取所有列名字
     *
     * @param columnList
     * @return
     * @throws SQLException
     */
    public String getColumnSplit(List<ColumnData> columnList) throws SQLException {
        StringBuffer commonColumns = new StringBuffer();
        for (ColumnData data : columnList) {
            commonColumns.append(data.getColumnName() + "|");
        }
        return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
    }

    /**
     * 获取查询字段列表
     *
     * @param columnList
     * @return
     */
    private String getQueryColumnSplit(List<ColumnData> columnList) {
        StringBuffer commonColumns = new StringBuffer();
        for (ColumnData data : columnList) {
            commonColumns.append(tableAliases[0] + "." + data.getColumnName() + "|");
        }
        return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
    }

    /**
     * 获取实体属性名字
     *
     * @param columnList
     * @return
     * @throws SQLException
     */
    public String getPropertySplit(List<ColumnData> columnList) throws SQLException {
        StringBuffer commonColumns = new StringBuffer();
        for (ColumnData data : columnList) {
            commonColumns.append(data.getPropertyName() + "|");
        }
        return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
    }
}
