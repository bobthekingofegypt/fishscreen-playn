package org.bobstuff.fishscreen.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import org.bobstuff.fishscreen.core.FishScreenGame;

public class FishScreenGameActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("org/bobstuff/fishscreen/resources");
    PlayN.run(new FishScreenGame());
  }
}
