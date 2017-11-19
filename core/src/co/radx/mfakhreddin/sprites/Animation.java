package co.radx.mfakhreddin.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by M.Fakhreddin on 07/07/2017.
 */

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frameAt;

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        this.maxFrameTime = cycleTime / frameCount;
        frameAt = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frameAt++;
            currentFrameTime = 0;
        }
        if (frameAt >= frameCount) {
            frameAt = 0;
        }
    }

    public TextureRegion getFrame() {
        return frames.get(frameAt);
    }
}
