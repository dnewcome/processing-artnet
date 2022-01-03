import ch.bildspur.artnet.*;
import processing.core.PApplet;

public class MySketch extends PApplet {

  ArtNetClient artnet;
  byte[] dmxData = new byte[512];

  public void settings() {
    size(500, 500);
    artnet = new ArtNetClient(null);
    artnet.start();
  }

  public void draw() {
    background(64);
    ellipse(250, 250, 20, 20);
    loadPixels();
    byte r = (byte) red(pixels[500 * mouseY + mouseX]);
    System.out.println(r);
    dmxData[0] = r;
    artnet.unicastDmx("127.0.0.1", 0, 0, dmxData);
    artnet.unicastDmx("127.0.0.1", 0, 1, dmxData);
  }

  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[] {"MySketch"};
    PApplet.main(appletArgs);
  }
}
