package org.bobstuff.fishscreen.core;


import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.Graphics;
import playn.core.ImmediateLayer;
import playn.core.PlayN;
import playn.core.Surface;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;

/**
 * @author bob
 *
 */
public class LoadingScreen extends UIScreen implements ImmediateLayer.Renderer {
	
	private static int LOADING_BAR_WIDTH = 400;
	private static int LOADING_BAR_HEIGHT = 80;
	private static int LOADING_BAR_PADDING = 6;
	
	protected int percentageComplete;
	
	private ScreenStack director;
	private CanvasImage background;
	
	public LoadingScreen(ScreenStack director) {
		this.director = director;
	}
	
	@Override
	public void wasAdded () {
        super.wasAdded();
        
        Graphics graphics = PlayN.graphics();
        
        background = graphics.createImage(width(), height());
        Canvas backgroundCanvas = background.canvas();
        backgroundCanvas.setFillColor(0xff000000);
        backgroundCanvas.fillRect(0, 0, width(), height());
        
        layer.add(graphics.createImmediateLayer((int)width(), (int)height(), this));
        
        ProgressAssetWatcher.Listener assetListener = new ProgressAssetWatcher.Listener() {		
			@Override
			public void progress(int percentage) {
				percentageComplete = percentage;
			}

			@Override
			public void error(Throwable e) {
				PlayN.log().error("Failed to load resource", e);
			}

			@Override
			public void done() {
				start();
			}
		};
		
		ProgressAssetWatcher assetWatcher = new ProgressAssetWatcher(assetListener);
		ImageCache.loadImages(PlayN.assets(), assetWatcher);
		assetWatcher.start();
    }
	
	public void start() {
		director.replace(new AquariumScreen());
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
	}
	
	/* (non-Javadoc)
     * @see playn.core.ImmediateLayer.Renderer#render(playn.core.Surface)
     */
    @Override
    public void render(Surface surface) {
        surface.drawImage(background, 0, 0);
        
        int startingX = (int)((width() / 2) - (LOADING_BAR_WIDTH/2));
        int startingY = (int)((height() / 2) - (LOADING_BAR_HEIGHT/2));
        
        int innerWidth = (LOADING_BAR_WIDTH - (2*LOADING_BAR_PADDING));
        int innerHeight = (LOADING_BAR_HEIGHT - (2*LOADING_BAR_PADDING));
        
        int progress = (int)((percentageComplete/100f) * innerWidth);
        
        surface.setFillColor(0xffffffff);
        
        surface.fillRect(startingX, startingY, LOADING_BAR_WIDTH, LOADING_BAR_HEIGHT);
        surface.setFillColor(0xff000000);
        surface.fillRect(startingX + LOADING_BAR_PADDING + progress, startingY+LOADING_BAR_PADDING, innerWidth - progress, innerHeight);
    }

}
