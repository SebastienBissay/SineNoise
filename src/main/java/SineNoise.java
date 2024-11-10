import processing.core.PApplet;
import processing.core.PVector;

import static parameters.Parameters.*;
import static save.SaveUtil.saveSketch;

public class SineNoise extends PApplet {
    public static void main(String[] args) {
        PApplet.main(SineNoise.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        noFill();
        noLoop();
    }

    @Override
    public void draw() {
        for (int k = 0; k < NUMBER_OF_ITERATIONS; k++) {
            PVector p =
                    new PVector(width / 2f, height / 2f)
                            .add(PVector.fromAngle(random(TWO_PI)).mult(sq(random(1)) * MAX_RADIUS));
            for (int l = 0; l < MAX_LENGTH; l++) {
                point(p.x, p.y);
                float angle = noise(sin(noise(p.x * NOISE_SCALE, p.y * NOISE_SCALE)) / NOISE_SCALE,
                        cos(noise(p.y * NOISE_SCALE, p.x * NOISE_SCALE)) / NOISE_SCALE);
                PVector v = PVector.fromAngle(TWO_PI * angle);
                p.add(v);
                if (p.x < 0 || p.x > width || p.y < 0 || p.y > height) break;
            }
        }
        saveSketch(this);
    }
}
