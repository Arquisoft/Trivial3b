import game.Category;
import models.Estadistica;
import models.Player;
import play.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");

        if(Player.get("admin") == null) {
            Player p0 = new Player("admin", "admin");
            p0.save();
            Player p1 = new Player("usuario1", "usuario1");
            p1.save();
            Player p2 = new Player("usuario2", "usuario2");
            p2.save();
            Player p3 = new Player("usuario3", "usuario3");
            p3.save();
            Estadistica e1 = new Estadistica(p1.getId(), "P0001", Category.DEPORTES.toString(), 2, 1);
            e1.save();
            Estadistica e2 = new Estadistica(p1.getId(), "P0002", Category.GEOGRAFIA.toString(), 0, 1);
            e2.save();
            Estadistica e3 = new Estadistica(p1.getId(), "P0003", Category.HISTORIA.toString(), 1, 3);
            e3.save();
            Estadistica e4 = new Estadistica(p2.getId(), "P0001", Category.DEPORTES.toString(), 0, 0);
            e4.save();
            Estadistica e5 = new Estadistica(p2.getId(), "P0002", Category.GEOGRAFIA.toString(), 2, 1);
            e5.save();
            Estadistica e6 = new Estadistica(p2.getId(), "P0003", Category.HISTORIA.toString(), 1, 1);
            e6.save();
            Estadistica e7 = new Estadistica(p3.getId(), "P0001", Category.DEPORTES.toString(), 4, 0);
            e7.save();
            Estadistica e8 = new Estadistica(p3.getId(), "P0002", Category.GEOGRAFIA.toString(), 1, 2);
            e8.save();
            Estadistica e9 = new Estadistica(p3.getId(), "P0003", Category.HISTORIA.toString(), 0, 0);
            e9.save();
        }
    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}
