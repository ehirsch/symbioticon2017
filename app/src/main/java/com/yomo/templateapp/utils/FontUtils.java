package com.yomo.templateapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by mmi on 08.09.2017.
 */

public class FontUtils {

	private Typeface typeRegular;
	private Typeface typeSemiBold;
	private Typeface typeLight;

	public enum Type {
		REGULAR,
		SEMI_BOLD,
		LIGHT
	}

	private static FontUtils instance;

	public static FontUtils getInstance() {
		return instance;
	}

	private FontUtils(Context context) {
		typeRegular = Typeface.createFromAsset(context.getAssets(), "fonts/sofia_pro_regular.otf");
		typeSemiBold = Typeface.createFromAsset(context.getAssets(), "fonts/sofia_pro_semi_bold.otf");
		typeLight = Typeface.createFromAsset(context.getAssets(), "fonts/sofia_pro_light.otf");
	}

	public static void init(Context context) {
		instance = new FontUtils(context);
	}

	public void applyYOMOFont(TextView view) {
		applyYOMOFont(view, Type.REGULAR);
	}

	public void applyYOMOFont(TextView view, Type type) {
		Typeface typeface;
		switch (type) {
			case REGULAR:
				typeface = typeRegular;
				break;
			case SEMI_BOLD:
				typeface = typeSemiBold;
				break;
			case LIGHT:
				typeface = typeLight;
				break;
			default:
				typeface = typeRegular;
		}
		view.setTypeface(typeface);
	}
}
