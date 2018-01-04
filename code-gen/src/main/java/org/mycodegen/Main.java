package org.mycodegen;

import java.io.FileInputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        List mds = null;
        if (args.length > 0)
            mds = ModelParser.parse(new FileInputStream(args[0]));
        else
            mds = ModelParser.parse(Main.class.getResourceAsStream("/models.xml"));
        GeneratorHelper.generator(mds);
    }
}
