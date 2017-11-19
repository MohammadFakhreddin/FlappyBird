package co.radx.mfakhreddin.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import co.radx.mfakhreddin.FlappyBird;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = FlappyBird.WIDTH;
        config.height = FlappyBird.HEIHT;
        new LwjglApplication(new FlappyBird(), config);
    }
}
