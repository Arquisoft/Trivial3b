import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;
import static play.mvc.Http.Status.OK;

import org.junit.Test;

import play.mvc.Result;
import controllers.Application;
import play.test.FakeRequest;

/**
 * 
 * Simple (JUnit) tests that can call all parts of a play app. If you are
 * interested in mocking a whole application, see the wiki for more details.
 * 
 */
public class ApplicationTest {

	@Test
	public void simpleCheck() {
		int a = 1 + 1;
		assertThat(a).isEqualTo(2);
	}

	@Test
	public void testLogin() {
		Result result = Application.showLogin();
		assertThat(status(result)).isEqualTo(OK);
		assertThat(charset(result)).isEqualTo("utf-8");
		assertThat(contentAsString(result)).contains("Bienvenido");
	}

//	@Test
//	public void testCallLogin() {
//		Result result = callAction(
//				controllers.routes.ref.Application.showLogin(),
//				new FakeRequest(GET, "/"));
//		assertThat(status(result)).isEqualTo(OK);
//	}

	@Test
	public void testRegister() {
		Result result = Application.showRegister();
		assertThat(status(result)).isEqualTo(OK);
		assertThat(charset(result)).isEqualTo("utf-8");
		assertThat(contentAsString(result)).contains("Únete");
	}

	@Test
	public void testCallRegister() {
		Result result = callAction(
				controllers.routes.ref.Application.showRegister(),
				new FakeRequest(GET, "/register"));
		assertThat(status(result)).isEqualTo(OK);
	}

}
