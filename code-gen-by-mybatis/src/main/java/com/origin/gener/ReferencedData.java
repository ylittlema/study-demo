package com.origin.gener;

/**
 * 表字段关联/外键信息
 * @author coocaapc
 *
 */
public class ReferencedData {

	private String tableName;
	private String columnNmae;
	private String referencedTableName; //关联表名
	private String referencedColumnName; //关联字段名
	private String propertyName;
	private String propertyDName;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnNmae() {
		return columnNmae;
	}
	public void setColumnNmae(String columnNmae) {
		this.columnNmae = columnNmae;
	}
	public String getReferencedTableName() {
		return referencedTableName;
	}
	public void setReferencedTableName(String referencedTableName) {
		this.setPropertyName(getColumnNameToClassName(referencedTableName));
		this.setPropertyDName(getTablesNameToClassName(referencedTableName));
		this.referencedTableName = referencedTableName;
	}
	public String getReferencedColumnName() {
		return referencedColumnName;
	}
	public void setReferencedColumnName(String referencedColumnName) {
		this.referencedColumnName = referencedColumnName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyDName() {
		return propertyDName;
	}
	public void setPropertyDName(String propertyDName) {
		this.propertyDName = propertyDName;
	}
	
	/**
     * 格式化处理列名字如goods_id 处理为goodsId
     * @param columnName
     * @return
     */
    public String getColumnNameToClassName(String columnName){
    	String [] split=columnName.split("_");
    	if(split.length>1){
    		StringBuffer sb=new StringBuffer();
    		sb.append(split[0]);
            for(int i=1;i<split.length;i++){
            	String tempTableName=split[i].substring(0, 1).toUpperCase()+split[i].substring(1, split[i].length());
                sb.append(tempTableName);
            }
            return sb.toString();
    	}else{
    		//String tempTables=split[0].substring(0, 1).toUpperCase()+split[0].substring(1, split[0].length());
    		return columnName;
    	}
    }
    
    /**
     * 
     * <br>
     * <b>功能：</b>表名转换成类名 每_首字母大写<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2011-12-21 <br>
     * @param tableName
     * @return
     */
    public String getTablesNameToClassName(String tableName){
    	String [] split=tableName.split("_");
    	if(split.length>1){
    		StringBuffer sb=new StringBuffer();
            for(int i=0;i<split.length;i++){
            	String tempTableName=split[i].substring(0, 1).toUpperCase()+split[i].substring(1, split[i].length());
                sb.append(tempTableName);
            }
            System.out.println(sb.toString());
            return sb.toString();
    	}else{
    		String tempTables=split[0].substring(0, 1).toUpperCase()+split[0].substring(1, split[0].length());
    		return tempTables;
    	}
    }
}
