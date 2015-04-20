import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import game.GameFactory;
import game.GameService;
import game.GameServiceImpl;
import models.Player;
import static org.junit.Assert.*;

import org.junit.Test;

import play.libs.F.Callback;
import play.test.TestBrowser;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("<html>");
              //  browser.$("Registrarse").click();
              //  browser.click("Registrarse");
            //    assertThat(browser.url()).isEqualTo("http://localhost:3333/register");
            }
        });
    }

    @Test
    public void testGameService() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {

              GameService game = GameFactory.newGameService(1);
              assertNull(game.getCurrentTurnPlayer());
          	
          	for(int i=1;i<7;i++){
          			assertTrue(game.addPlayer(new Player("jugador"+i,"jugador"+i)));
          			assertEquals(i, game.getPlayers().size());
          	}
          	
          	assertFalse(game.addPlayer(new Player("error","error")));
          	
          	assertEquals(game.getPlayers().get(0), game.getCurrentTurnPlayer());
          	
            }
        });
    }
    
}
