package org.bobstuff.fishscreen.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import org.bobstuff.fishscreen.core.FishScreenGame;

public class FishScreenGameJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.graphics().setSize(981, 767);
    platform.assets().setPathPrefix("org/bobstuff/fishscreen/resources");
    PlayN.run(new FishScreenGame());
  }
}
