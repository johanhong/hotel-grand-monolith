package hotel;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.docs.Documenter.CanvasOptions;
import org.springframework.modulith.docs.Documenter.DiagramOptions;
import org.springframework.modulith.docs.Documenter.DiagramOptions.DiagramStyle;

class ModularityTests {

	ApplicationModules modules = ApplicationModules.of(Application.class);

	@Test
	void verifyModularity() {

		// print basic module information with direct module dependencies
		System.out.println(modules.toString());

		/*
		verify modules, with following rules:
			No cycles on the application module
			Efferent module access via API packages only
			Explicitly allowed application module dependencies only
		 */
		modules.verify();
	}

	@Test
	void renderDocumentation() throws Exception {

		var canvasOptions = CanvasOptions.defaults()

		// --> Optionally enable linking of JavaDoc
		// .withApiBase("https://foobar.something")

		;

		var docOptions = DiagramOptions.defaults()
				.withStyle(DiagramStyle.UML);

		new Documenter(modules) //
				.writeDocumentation(docOptions, canvasOptions);
	}
}
