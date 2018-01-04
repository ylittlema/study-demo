package org.mycodegen;

import java.util.ArrayList;
import java.util.List;

import org.mycodegen.generator.ActionGenerator;
import org.mycodegen.generator.BaseCodeGenerator;
import org.mycodegen.generator.DAOGenerator;
import org.mycodegen.generator.Generator;
import org.mycodegen.generator.ModelGenerator;
import org.mycodegen.generator.ServiceGenerator;
import org.mycodegen.generator.SpringGenerator;
import org.mycodegen.generator.ViewGenerator;
import org.mycodegen.generator.XworkGenerator;

public class GeneratorHelper {
	public static List getAllGenerator() {
		List generators = new ArrayList();
		generators.add(new BaseCodeGenerator());
		generators.add(new ModelGenerator());
		generators.add(new DAOGenerator());
		generators.add(new ServiceGenerator());
		generators.add(new ActionGenerator());
		generators.add(new XworkGenerator());
		generators.add(new ViewGenerator());
		generators.add(new SpringGenerator());
		return generators;
	}

	public static void generator(List modelDefinitions) {
		List generators = getAllGenerator();
		for (int i = 0; i < generators.size(); i++) {
			Generator generator = (Generator) generators.get(i);
			generator.generate(modelDefinitions);
		}
	}
}
