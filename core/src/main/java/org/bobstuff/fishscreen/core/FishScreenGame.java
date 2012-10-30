package org.bobstuff.fishscreen.core;

import playn.core.Game;
import playn.core.PlayN;
import tripleplay.game.ScreenStack;


public class FishScreenGame implements Game {
	
	@Override 
	public void init () {
        director.push(new LoadingScreen(director));
    }

    @Override 
    public void update (float delta) {
        director.update(delta);
    }

    @Override 
    public void paint (float alpha) {
        director.paint(alpha);
    }

    @Override 
    public int updateRate () {
        return 0;
    }

    protected final ScreenStack director = new ScreenStack() {
        @Override protected void handleError (RuntimeException error) {
            PlayN.log().warn("Screen failure", error);
        }
        @Override protected Transition defaultPushTransition () {
            return ScreenStack.NOOP;
        }
        @Override protected Transition defaultPopTransition () {
            return ScreenStack.NOOP;
        }
    };
}
