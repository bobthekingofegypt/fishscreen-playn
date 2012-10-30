package org.bobstuff.fishscreen.core;

import playn.core.Assets;
import playn.core.Image;

/**
 * @author bob
 *
 */
public class ImageCache {
	
	private static int FRAME_COUNT = 16;
	public static Image background;
	public static Image[] redFishRight = new Image[FRAME_COUNT];
	public static Image[] redFishLeft = new Image[FRAME_COUNT];
	public static Image[] orangeFishRight = new Image[FRAME_COUNT];
	public static Image[] orangeFishLeft = new Image[FRAME_COUNT];
	public static Image[] greenFishRight = new Image[FRAME_COUNT];
	public static Image[] greenFishLeft = new Image[FRAME_COUNT];
	
	public static void loadImages(Assets assets, ProgressAssetWatcher assetWatcher) {
		background = assets.getImage("images/background.jpg");
		assetWatcher.add(background);
		
		
		for(int i = 1; i <= FRAME_COUNT; i++) {
			redFishRight[i-1] = assets.getImage("images/fish/red_fish_right_"+i+".png");
			assetWatcher.add(redFishRight[i-1]);
			
			redFishLeft[i-1] = assets.getImage("images/fish/red_fish_left_"+i+".png");
			assetWatcher.add(redFishLeft[i-1]);
		}
		
		for(int i = 1; i <= FRAME_COUNT; i++) {
			orangeFishRight[i-1] = assets.getImage("images/fish/orange_fish_right_"+i+".png");
			assetWatcher.add(orangeFishRight[i-1]);
			
			orangeFishLeft[i-1] = assets.getImage("images/fish/orange_fish_left_"+i+".png");
			assetWatcher.add(orangeFishLeft[i-1]);
		}
		
		for(int i = 1; i <= FRAME_COUNT; i++) {
			greenFishRight[i-1] = assets.getImage("images/fish/green_fish_right_"+i+".png");
			assetWatcher.add(greenFishRight[i-1]);
			
			greenFishLeft[i-1] = assets.getImage("images/fish/green_fish_left_"+i+".png");
			assetWatcher.add(greenFishLeft[i-1]);
		}
	}
	
}
