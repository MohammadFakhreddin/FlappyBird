package co.radx.mfakhreddin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import co.radx.mfakhreddin.states.GameStateManager;
import co.radx.mfakhreddin.states.MenuState;

public class FlappyBird extends ApplicationAdapter {
    public static final int WIDTH = 480;
    public static final int HEIHT = 800;

    public static final String TITLE = "Flappy Bird";
    private GameStateManager gsm;
    private SpriteBatch batch;
    private Texture img;

    private Music music;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MenuState(gsm));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
//        music.play();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        music.dispose();
    }
}
