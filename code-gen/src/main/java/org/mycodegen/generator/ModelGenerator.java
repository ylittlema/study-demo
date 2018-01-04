package org.mycodegen.generator;

import org.mycodegen.ModelDefinition;

import java.util.HashMap;
import java.util.Map;

public class ModelGenerator extends AbstractGenerator {

    public void generateOneByOne(ModelDefinition md) {

        String templateFileName = "model/Model.java.ftl";

        Map propMap = new HashMap();
        propMap.put("modelDefinition", md);

        String fileName = md.getClassName() + ".java";

        generate(templateFileName, propMap, "src/" + package2path(md.getPackageName()) + "/model/" + fileName);

        templateFileName = "model/Model.hbm.xml.ftl";

        fileName = md.getClassName() + ".hbm.xml";

        generate(templateFileName, propMap, "src/" + package2path(md.getPackageName()) + "/model/" + fileName);
    }
}
