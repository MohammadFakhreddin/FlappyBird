package co.radx.mfakhreddin.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import co.radx.mfakhreddin.FlappyBird;
import co.radx.mfakhreddin.sprites.Bird;
import co.radx.mfakhreddin.sprites.Tube;

/**
 * Created by M.Fakhreddin on 07/07/2017.
 */

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Bird bird;
    private Texture bg;
    private Texture toptube;
    private Texture bottomTube;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 100);
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIHT / 2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();
        toptube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        ground = new Texture("ground.png");

        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(camera.position.x - camera.viewportWidth / 2 + ground.getWidth(), GROUND_Y_OFFSET);

        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube((i + 1) * (TUBE_SPACING) + Tube.WIDTH, toptube, bottomTube));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;
        for (int i = 0; i < tubes.size; i++) {
            if (camera.position.x - (camera.viewportWidth / 2) > tubes.get(i).getPosTopTube().x + tubes.get(i).getTopTube().getWidth()) {
                tubes.get(i).reposition(tubes.get(i).getPosTopTube().x + (Tube.WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }
            if (tubes.get(i).collides(bird.getBounds())) {
//                gsm.set(new PlayState(gsm));
                onLoose();
            }
        }
        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
//            gsm.set(new PlayState(gsm));
            onLoose();
        }
        camera.update();
    }

    public void onLoose() {
        gsm.set(new MenuState(gsm));
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, camera.position.x - (camera.viewportWidth) / 2, 0);
        spriteBatch.draw(bird.getFrame(), bird.getPosition().x, bird.getPosition().y);
        for (int i = 0; i < tubes.size; i++) {
            spriteBatch.draw(tubes.get(i).getTopTube(), tubes.get(i).getPosTopTube().x, tubes.get(i).getPosTopTube().y);
            spriteBatch.draw(tubes.get(i).getBottomTube(), tubes.get(i).getPosBotTube().x, tubes.get(i).getPosBotTube().y);
        }
        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        toptube.dispose();
        bottomTube.dispose();
        bg.dispose();
    }

    private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
