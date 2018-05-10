/*******************************************************************************
 * Copyright (c) 2001, 2007 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/cpl-v10.html
 *
 * Contributors:
 *     Flemming N. Larsen
 *     - Initial implementation
 *******************************************************************************/
package robocode.render;


import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import robocode.io.FileUtil;
import robocode.io.Logger;
import robocode.util.LogUtil;
import static robocode.util.Utils.isNear;


/**
 * Image utility
 *
 * @author Flemming N. Larsen (original)
 */
public class ImageUtil {

	/**
	 * Returns an image resource.
	 *
	 * @param filename the filename of the image to load
	 * @return the loaded image
	 */
	public static Image getImage(String filename) {
		
		URL url = null;
		try {
		//	 url = ClassLoader.class.getResource(filename);
			if(filename.startsWith("/"))
				filename = filename.substring(1);
			url = new URL(FileUtil.getUrl(),filename);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (url == null) {
			Logger.log("Could not load image because of invalid filename: " + filename);
			LogUtil.log("Could not load image because of invalid filename: " + filename);
			return null;
		}

		try {
			return ImageIO.read(url);
		} catch (Exception e) {
			Logger.log("Could not load image: " + filename);
			LogUtil.log("Could not load image: " + filename);
			return null;
		}
		//return null;
	}

	/**
	 * Create a copy of an robot image into a coloured robot image. The colors of the
	 * input image are changed into the input color, but with the same lumination.
	 *
	 * @param img the source image
	 * @param color the new color that substitutes the old color(s) in the source image
	 * @return a new image
	 */
	public static Image createColouredRobotImage(Image img, Color color) {
		return (color == null)
				? img
				: (img == null)
						? null
						: Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), new ColorFilter(color)));
	}

	/**
	 * A color filter used for changing colors into another color.
	 *
	 * @author Flemming N. Larsen
	 */
	private static class ColorFilter extends RGBImageFilter {
		private float[] hsl;

		public ColorFilter(Color color) {
			hsl = fromRGBtoHSL(color.getRed(), color.getGreen(), color.getBlue());
		}

		@Override
		public int filterRGB(int x, int y, int argb) {
			int r = (argb >> 16) & 0xff;
			int g = (argb >> 8) & 0xff;
			int b = argb & 0xff;

			float[] HSL = fromRGBtoHSL(r, g, b);

			if (HSL[1] > 0) {
				float L = Math.min(1, (hsl[2] + HSL[2]) / 2 + hsl[2] / 7);

				return argb & 0xff000000 | fromHSLtoRGB(hsl[0], hsl[1], L);
			}
			return argb;
		}
	}

	private static float[] fromRGBtoHSL(int r, int g, int b) {
		float R = (float) r / 255;
		float G = (float) g / 255;
		float B = (float) b / 255;

		float min = Math.min(Math.min(R, G), B); // Min. value of RGB
		float max = Math.max(Math.max(R, G), B); // Max. value of RGB
		float delta = max - min; // Delta RGB value

		float L = (max + min) / 2;

		float H, S;

		if (delta == 0) { // This is a gray, no chroma...
			H = 0;
			S = 0;
		} else { // Chromatic data...
			if (L < 0.5f) {
				S = delta / (max + min);
			} else {
				S = delta / (2 - max - min);
			}

			float deltaR = (((max - R) / 6) + (delta / 2)) / delta;
			float deltaG = (((max - G) / 6) + (delta / 2)) / delta;
			float deltaB = (((max - B) / 6) + (delta / 2)) / delta;

			if (isNear(R, max)) {
				H = deltaB - deltaG;
			} else if (isNear(G, max)) {
				H = (1f / 3) + deltaR - deltaB;
			} else {
				H = (2f / 3) + deltaG - deltaR;
			}

			if (H < 0) {
				H++;
			}
			if (H > 1) {
				H--;
			}
		}
		return new float[] { H, S, L };
	}

	private static int fromHSLtoRGB(float h, float s, float l) {
		float m2 = (l <= 0.5f) ? (l * (s + 1)) : (l + s - l * s);
		float m1 = 2 * l - m2;

		int r = (int) (255 * fromHUEtoRGB(m1, m2, h + (1f / 3)));
		int g = (int) (255 * fromHUEtoRGB(m1, m2, h));
		int b = (int) (255 * fromHUEtoRGB(m1, m2, h - (1f / 3)));

		return (((r << 8) | g) << 8) | b;
	}

	private static float fromHUEtoRGB(float m1, float m2, float h) {
		if (h < 0) {
			h++;
		}
		if (h > 1) {
			h--;
		}
		if ((h * 6) < 1) {
			return m1 + (m2 - m1) * h * 6;
		}
		if ((h * 2) < 1) {
			return m2;
		}
		if ((h * 3) < 2) {
			return m1 + (m2 - m1) * ((2f / 3) - h) * 6;
		}
		return m1;
	}
}
