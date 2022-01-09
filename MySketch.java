import ch.bildspur.artnet.*;
import processing.core.PApplet;

public class MySketch extends PApplet {

  ArtNetClient artnet;
  byte[] dmxData = new byte[512];

  String[] ips = {
    "192.168.1.8",
    "192.168.1.25"
  };

  public void setup() {
    frameRate(30);
  }
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
    dmxData[0] = r;

    for(int j = 56; j < dmxData.length; j = j + 3) {
        dmxData[j] = r;
        dmxData[j+1] = (byte)255;
        dmxData[j+2] = (byte)255;
    }

    for(int i = 0; i < ips.length; i++) {
        artnet.unicastDmx(ips[i], 0, 0, dmxData);
    }
  }

  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[] {"MySketch"};
    PApplet.main(appletArgs);
  }
}
