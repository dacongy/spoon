package spoon.test.filters;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import spoon.Launcher;
import spoon.compiler.SpoonResourceHelper;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.RegexFilter;

public class FilterTest {

	Factory factory;

	@Before
	public void setup() throws Exception {
		Launcher spoon = new Launcher();
		factory = spoon.createFactory();
		spoon.createCompiler(
				factory,
				SpoonResourceHelper
						.resources("./src/test/java/spoon/test/filters/Foo.java"))
				.build();
	}

	@Test
	public void testFilters() throws Exception {

		CtClass<?> foo = factory.Package().get("spoon.test.filters")
				.getType("Foo");
		assertEquals("Foo", foo.getSimpleName());

		List<CtExpression<?>> expressions = foo
				.getElements(new RegexFilter<CtExpression<?>>(".* = .*"));

		assertEquals(2, expressions.size());

	}

}
