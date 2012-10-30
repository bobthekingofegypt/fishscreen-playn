package org.bobstuff.fishscreen.core;

import playn.core.Image;

/**
 * @author bob
 *
 */
public class Fish {
	public static int LEFT = 0;
	public static int RIGHT = 1;
	public static int DOWN = 0;
	public static int UP = 1;
	public static int FRONT = 0;
	public static int BACK = 1;
	
	private float width = 300;
	private float height = 250;
	
	private float speed_x = Utils.randomInt(3, 8);
	private float speed_y = Utils.randomInt(3, 8);
	private float speed_z = 0.002f;
	
	private int xDirection = Utils.randomInt(0, 2);
	private int yDirection = Utils.randomInt(0, 2);
	private int zDirection = Utils.randomInt(0, 2);
	
	private float scale = 1.0f;
	
	private Image[] rightImages;
	private Image[] leftImages;
	
	private Image currentImage;
	private int currentFrame;
	private int numberOfFrames;
	
	private int xPosition;
	private int yPosition;
	private float depth = 0.5f;
	
	public Fish(Image[] rightImages, Image[] leftImages) {
		this.rightImages = rightImages;
		this.leftImages = leftImages;
		
		this.numberOfFrames = rightImages.length;
	}
	
	public void positionInBounds(int boundsWidth, int boundsHeight) {
		xPosition = Utils.randomInt(0, boundsWidth - (int)getWidth());
		yPosition = Utils.randomInt(0, boundsHeight - (int)getHeight());
		depth = Utils.randomFloat(0, 1);
	}
	
	public void updateInBounds(int boundsWidth, int boundsHeight) {
		currentFrame = currentFrame + 1;
		if (currentFrame == numberOfFrames) {
			currentFrame = 0;
		}
		
		if(xDirection == RIGHT) {
			currentImage = rightImages[currentFrame];
		} else if (xDirection == LEFT) {
			currentImage = leftImages[currentFrame];
		}
		
		if(xPosition < 0 && xDirection == LEFT) {
			xDirection = RIGHT;
		} else if(xPosition > (boundsWidth - getWidth()) && xDirection == RIGHT) {
			xDirection = LEFT;
		}
		
		if(yPosition < 0 && yDirection == UP) {
			yDirection = DOWN;
		} else if(yPosition > (boundsHeight - getHeight()) && yDirection == DOWN) {
			yDirection = UP;
		}

		if(depth < 0.1 && zDirection == BACK) {
			zDirection = FRONT;
		} else if(depth > 1 && zDirection == FRONT) {
			zDirection = BACK;
		}
		
		if (xDirection == RIGHT) {
			xPosition += speed_x;
		} else if (xDirection == LEFT) {
			xPosition -= speed_x;
		}
		
		if (yDirection == DOWN) {
			yPosition += speed_y;
		} else if (yDirection == UP) {
			yPosition -= speed_y;
		}
		
		if(zDirection == BACK) {
			depth -= speed_z;
		} else if (zDirection == FRONT) {
			depth += speed_z;
		}
	}
	
	public float getPosX() {
		return xPosition;
	}
	
	public float getPosY() {
		return yPosition;
	}
	
	public Image getImage() {
		return currentImage;
	}

	public float getWidth() {
		return width * depth * scale;
	}
	
	public float getHeight() {
		return height * depth * scale;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getDepth() {
		return depth;
	}
	
}
