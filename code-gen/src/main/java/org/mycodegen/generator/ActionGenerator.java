package org.mycodegen.generator;

import org.mycodegen.ModelDefinition;

import java.util.HashMap;
import java.util.Map;

public class ActionGenerator extends AbstractGenerator {

    public void generateOneByOne(ModelDefinition md) {
        String templateFileName = "action/ModelAction.java.ftl";
        Map propMap = new HashMap();
        propMap.put("modelDefinition", md);
        String fileName = md.getClassName() + "Action.java";
        generate(templateFileName, propMap, "src/" + package2path(md.getPackageName()) + "/action/" + fileName);
        templateFileName = "action/ModelAction.properties.ftl";
        fileName = md.getClassName() + "Action.properties";
        generate(templateFileName, propMap, "src/" + package2path(md.getPackageName()) + "/action/" + fileName);
        templateFileName = "action/ModelAction_zh_CN.properties.ftl";
        fileName = md.getClassName() + "Action_zh_CN.properties";
        generate(templateFileName, propMap, "src/" + package2path(md.getPackageName()) + "/action/" + fileName);
        templateFileName = "action/ModelAction-validation.xml.ftl";
        if (md.getRequireds().size() > 0) {
            fileName = md.getClassName() + "Action-" + uncapFirst(md.getClassName()) + "-validation.xml";
            generate(templateFileName, propMap, "src/" + package2path(md.getPackageName()) + "/action/" + fileName);
        }
    }
}
