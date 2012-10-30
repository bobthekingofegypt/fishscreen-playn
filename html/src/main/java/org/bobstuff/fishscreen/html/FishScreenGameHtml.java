package org.bobstuff.fishscreen.html;

import org.bobstuff.fishscreen.core.FishScreenGame;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

public class FishScreenGameHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("fishscreen/");
    
    PlayN.graphics().setSize(
			PlayN.graphics().screenWidth(),
			PlayN.graphics().screenHeight()
	);
					
    PlayN.run(new FishScreenGame());
  }
}
