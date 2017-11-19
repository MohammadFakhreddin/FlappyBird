package co.radx.mfakhreddin.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import co.radx.mfakhreddin.FlappyBird;

/**
 * Created by M.Fakhreddin on 07/07/2017.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture(Gdx.files.internal("bg.png"));
        playBtn = new Texture(Gdx.files.internal("playbtn.png"));
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIHT / 2);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }


    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
