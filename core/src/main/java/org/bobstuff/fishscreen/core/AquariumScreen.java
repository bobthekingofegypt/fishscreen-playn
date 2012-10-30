package org.bobstuff.fishscreen.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import playn.core.Graphics;
import playn.core.Image;
import playn.core.ImmediateLayer;
import playn.core.PlayN;
import playn.core.Surface;
import playn.core.SurfaceLayer;
import tripleplay.game.UIScreen;
import tripleplay.ui.Background;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AbsoluteLayout;

/**
 * @author bob
 *
 */
public class AquariumScreen extends UIScreen {

	public static int width;
	public static int height;
	
	protected List<Fish> onScreenFish = new ArrayList<Fish>();
	
	private ControlsPanel controlsPanel;
	
	@Override
	public void wasAdded() {
		width = (int)(graphics().width() * graphics().scaleFactor());
		height = graphics().height();
		
        SurfaceLayer backgroundSurfaceLayer = 
		        		graphics().createSurfaceLayer(width, height);
        graphics().rootLayer().add(backgroundSurfaceLayer);
        float scaleX = PlayN.graphics().width() / (float) 981;
    	float scaleY = PlayN.graphics().height() / (float) 767;
    	
    	backgroundSurfaceLayer.setScale(scaleX, scaleY);
        backgroundSurfaceLayer.surface().drawImage(ImageCache.background, 0, 0);
        
        Root root = iface.createRoot(new AbsoluteLayout(), SimpleStyles.newSheet());
        root.addStyles(Style.BACKGROUND.is(Background.roundRect(0xAA000000, 10)));
        root.setSize(360, 70);
        root.layer.setTranslation(width - 400, height - 100);
        
        controlsPanel = new ControlsPanel(this);
        root.add(AbsoluteLayout.at(controlsPanel, 0,0));
        layer.add(root.layer);
		
        ImmediateLayer fishLayer = 
        		graphics().createImmediateLayer(width, height, 
        				new ImmediateLayer.Renderer() {
        	@Override
        	public void render(Surface surface) {
        		surface.setFillColor(0x00000000);
        		surface.fillRect(0, 0, graphics().width(), graphics().height());
        		

        		for (Fish fish : onScreenFish) {
        			surface.drawImage(fish.getImage(), 
        							  fish.getPosX(), 
        							  fish.getPosY(), 
        							  fish.getWidth(), 
        							  fish.getHeight());
        				
        		}
        	}
        });
		graphics().rootLayer().add(fishLayer);
		
		setNumberOfFish(10);
	}
	
	protected Graphics graphics() {
		return PlayN.graphics();
	}

	private void addFish() {
		int numberOfFish = onScreenFish.size();
		Image[] right = null;
		Image[] left = null;
		switch (numberOfFish%3) {
		case 0:
			right = ImageCache.redFishRight;
			left = ImageCache.redFishLeft;
			break;
		case 1:
			right = ImageCache.orangeFishRight;
			left = ImageCache.orangeFishLeft;
			break;
		case 2:
			right = ImageCache.greenFishRight;
			left = ImageCache.greenFishLeft;
			break;
		}
		
		Fish fish = new Fish(right, left);
		fish.positionInBounds(width, height);
		onScreenFish.add(fish);
	}
	
	public void setNumberOfFish(int count) {
		controlsPanel.setNumberOfFish(count);
		if (onScreenFish.size() < count) {
			for (int i=onScreenFish.size(); i<count; ++i) {
				addFish();
			}
		} else if (onScreenFish.size() > count) {
			for (int i=onScreenFish.size(); i>count; --i) {
				onScreenFish.remove(0);
			}
		}
		
		float scale = 1.0f;
		if (count == 100) {
			scale = 0.6f;
		} else if (count == 1000) {
			scale = 0.4f;
		}
		
		for (Fish fish : onScreenFish) {
			fish.setScale(scale);
		}
	}

	@Override
	public void update(float delta) {
		width = graphics().width();
		height = graphics().height();
		
		elapsedTime = elapsedTime + (int)delta;
		
		calculFps();
		
		for (Fish fish : onScreenFish) {
			fish.updateInBounds(width, height);
		}
		
		Collections.sort(onScreenFish, new Comparator<Fish>() {
			@Override
            public int compare(Fish fish1, Fish fish2) {
				if (fish1.getDepth() < fish2.getDepth()) {
					return -1;
				} else if (fish1.getDepth() > fish2.getDepth()) {  
		            return 1;
				} 
				
				return 0;
            }
		});
	}
	
	private int elapsedTime;
	private int lastFpsCount;
	private int fps = 0;
	
	private void calculFps() {
		long duration = elapsedTime - lastFpsCount;
		if(duration < 1000) {
			fps++;
		} else {
			controlsPanel.setFps(fps);
			fps = 0;
			lastFpsCount = elapsedTime;
		}
	}
}
