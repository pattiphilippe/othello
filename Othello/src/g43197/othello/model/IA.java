package g43197.othello.model;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philippe
 */
public class IA extends Player implements Strategy {

    private ScheduledExecutorService scheduler;

    public IA(Color color) {
        super(color);
        this.name = "IA";
    }

    @Override
    public void play(Facade game) {
        List<Coordinates> accessibles = game.getAccessibles();

        try {
            //        scheduler = new ScheduledThreadPoolExecutor(1);
//        scheduler.schedule(Thread.currentThread(), 5, TimeUnit.SECONDS);
            System.out.println("sleeping");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!accessibles.isEmpty()) {
            System.out.println("ia put piece");
            game.putPiece(accessibles.get(random(accessibles.size())));
        } else {
            boolean stop = false;
            for (int i = 0; i < 8 || !stop; i++) {
                for (int j = 0; j < 8 || !stop; j++) {
                    if (game.getPiece(new Coordinates(i, j)) == null) {
                        stop = true;
                        game.putWall(new Coordinates(i, j));
                        System.out.println("ia put wall");
                    }
                }
            }
        }
    }

    private int random(int size) {
        return (int) (Math.random() * size);
    }

}
