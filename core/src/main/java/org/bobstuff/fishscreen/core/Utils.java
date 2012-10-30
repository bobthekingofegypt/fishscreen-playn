package org.bobstuff.fishscreen.core;

import java.util.Random;

/**
 * @author bob
 *
 */
public class Utils {
	public static Random random = new Random(System.currentTimeMillis());

	public static int randomInt(int min, int max) {
		return random.nextInt(max) + min;
	}

	public static float randomFloat(float min, float max) {
		return random.nextFloat() * (max-min) + min;
	}
}
