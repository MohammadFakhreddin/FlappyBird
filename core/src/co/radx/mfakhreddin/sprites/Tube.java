package co.radx.mfakhreddin.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;


/**
 * Created by M.Fakhreddin on 07/07/2017.
 */

public class Tube {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 120;
    private static final int LOWEST_OPENNING = 120;
    public static final int WIDTH = 52;

    private Vector2 posTopTube, posBotTube;
    private Random random;
    private Texture topTube, bottomTube;
    private Rectangle boundsTop, boundsBot;


    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }


    public Tube(float x, Texture topTube, Texture bottomTube) {
//        topTube = new Texture("toptube.png");
//        bottomTube = new Texture("bottomtube.png");
        this.topTube = topTube;
        this.bottomTube = bottomTube;
        random = new Random();
        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENNING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public void reposition(float x) {
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENNING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
}
