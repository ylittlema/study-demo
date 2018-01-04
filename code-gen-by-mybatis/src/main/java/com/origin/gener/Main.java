package com.origin.gener;

import org.apache.velocity.VelocityContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    //数据库配置文件
//    private static ResourceBundle res = ResourceBundle.getBundle("DataSourceConfig.properties");
    //数据库配置信息
    private static String url = "jdbc:mysql://172.20.132.117:3306/homepage_resource_315";//res.getString("gpt.url");
    private static String username ="root";// res.getString("gpt.username");
    private static String passWord = "coocaa123";// res.getString("gpt.password");

    //项目跟路径，此处修改为你的项目路径
    private static String rootPath = "D:\\temp_code\\video-order_";

    //项目包路径，根据实际情况修改
    private static String pckBasePath = "com\\coocaa\\fire\\video\\core\\videobase\\";

    public static void main(String[] args) {
        // 数据库名
        String dbName = "homepage_resource_315";
        // 表名
        String[] tables = {"video_content_corner_relation"};
        // 对应表的中文注释
        String[] codes = {"内容角标关联关系信息表"};
        // 主键方式0自动生成，1采用UUID
        Integer[] keyModes = {0};
        for (int i = 0; i < tables.length; i++) {
            System.out.println("正在生成:" + tables[i] + " " + codes[i]);
            createSSICode(dbName, tables[i], codes[i], keyModes[i]);
        }
    }

    /**
     * 生成SSI代码
     *
     * @param dbName    数据库名
     * @param tableName 表名
     * @param codeName  中文注释
     */
    public static void createSSICode(String dbName, String tableName, String codeName, Integer primaryKeyMode) {
        CreateBean createBean = new CreateBean();
        createBean.setMysqlInfo(url, username, passWord);
        //类名，非全类名
        String className = createBean.getTablesNameToClassName(tableName);

        //首字母小写的类名
        String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
        //根路径
        String srcPath = rootPath + "src\\";
        //包名
        String shorPath = "com\\coocaa\\";
        //包路径
        String pckPath = srcPath + shorPath;

        //页面路径，放到WEB-INF下面是为了不让手动输入路径访问jsp页面，起到安全作用
        String webPath = rootPath + "WebRoot\\jsp\\sys\\";

        //java,xml文件名称
        String modelPath = "model\\" + className + "Model.java";
        String beanPath = "entity\\" + className + "Entity.java";

        String mapperPath = "mapper\\" + className + "Mapper.java";

        String servicePath = "service\\" + className + "Service.java";

        String controllerPath = "action\\" + className + "Action.java";

        String sqlMapperPath = "mybatis\\" + className + "Mapper.xml";

        //jsp页面路径
        String pageAddPath = lowerName + "\\" + lowerName + "Add.ftl";
        String pageEditPath = lowerName + "\\" + lowerName + "Edit.ftl";
        String pageListPath = lowerName + "\\" + lowerName + "View.ftl";

        //js路径
        String jsAddPath = lowerName + "\\" + lowerName + "Add.js";
        String jsEditPath = lowerName + "\\" + lowerName + "Edit.js";
        String jsListPath = lowerName + "\\" + lowerName + "View.js";

        VelocityContext context = new VelocityContext();
        context.put("systemDate", getCurDateStr());

        context.put("className", className);
        context.put("lowerName", lowerName);

        context.put("codeName", codeName); //表的中文注释

        context.put("tableName", tableName); //表名
        context.put("packPath", pckBasePath.replace("\\", "."));  //包名
        context.put("shorPath", shorPath.replace("\\", "."));

        context.put("primaryKeyMode", primaryKeyMode); //主键生成方式


        String entityFullName = pckBasePath + beanPath;
        String modelFullName = pckBasePath + modelPath;
        //javaBean的全类名
        context.put("entityName", entityFullName.substring(0, entityFullName.lastIndexOf(".")).replace("\\", "."));
        context.put("modelFullName", modelFullName.substring(0, modelFullName.lastIndexOf(".")).replace("\\", "."));

        /******************************生成bean字段*********************************/
        try {
            context.put("feilds", createBean.getBeanFeilds(dbName, tableName)); //生成bean
            context.put("modelFeilds", createBean.getModelFeilds(dbName, tableName)); //生成bean
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*******************************生成sql语句**********************************/
        try {
            Map<String, Object> sqlMap = createBean.getAutoCreateSql(dbName, tableName);
            context.put("columnDatas", createBean.getColumnDatas(dbName, tableName)); //生成bean
            context.put("ReferDatas", createBean.getReferencedColumnDatas(dbName, tableName)); //关联/外键
            context.put("SQL", sqlMap);
            context.put("tableKey", sqlMap.get("properKey"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //-------------------生成文件代码---------------------/
        CommonPageParser.WriterPage(context, "TempBean.java.ftl", pckPath, beanPath);  //生成实体Bean
        CommonPageParser.WriterPage(context, "TempModel.java.ftl", pckPath, modelPath); //生成Model
        CommonPageParser.WriterPage(context, "TempMapper.java.ftl", pckPath, mapperPath); //生成MybatisMapper接口 相当于Dao
        CommonPageParser.WriterPage(context, "TempService.java.ftl", pckPath, servicePath);//生成Service
        CommonPageParser.WriterPage(context, "TempMapper.xml", pckPath, sqlMapperPath);//生成Mybatis xml配置文件
        CommonPageParser.WriterPage(context, "TempController.java.ftl", pckPath, controllerPath);//生成Controller 相当于接口
        //		生JSP页面，如果不需要可以注释掉
        context.put("basePath", "${e:basePath()}");
        CommonPageParser.WriterPage(context, "TempView.ftl", webPath, pageListPath);//生成Controller 相当于接口
        CommonPageParser.WriterPage(context, "TempAdd.ftl", webPath, pageAddPath);//生成Controller 相当于接口
        CommonPageParser.WriterPage(context, "TempEdit.ftl", webPath, pageEditPath);//生成Controller 相当于接口
//		CommonPageParser.WriterPage(context, "TempEdit.jsp",webPath, pageEditPath);//生成Controller 相当于接口

        //生成js文件
        CommonPageParser.WriterPage(context, "TempAdd.js", webPath, jsAddPath);//
        CommonPageParser.WriterPage(context, "TempEdit.js", webPath, jsEditPath);//
        CommonPageParser.WriterPage(context, "TempView.js", webPath, jsListPath);//

        /*************************修改xml文件*****************************/
        WolfXmlUtil xml = new WolfXmlUtil();
        Map<String, String> attrMap = new HashMap<String, String>();
        try {
            /**   引入到mybatis-config.xml 配置文件 */
//			attrMap.clear();
//			attrMap.put("resource","com/yiya/mybatis/"+className+"Mapper.xml");
//		    xml.getAddNode(rootPath+"conf/mybatis-config.xml", "/configuration/mappers", "mapper", attrMap, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取项目的路径
     *
     * @return
     */
    public static String getRootPath() {
        String rootPath = "";
        try {
            File file = new File(CommonPageParser.class.getResource("/").getFile());
            rootPath = file.getParentFile().getParentFile().getParent() + "\\";
            rootPath = java.net.URLDecoder.decode(rootPath, "utf-8");
            System.out.println(rootPath);
            return rootPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootPath;
    }

    public static String getCurDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
