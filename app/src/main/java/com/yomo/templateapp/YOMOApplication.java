package com.yomo.templateapp;

import com.yomo.templateapp.utils.FontUtils;

/**
 * Created by mmi on 08.09.2017.
 */

public class YOMOApplication extends android.app.Application {
	@Override
	public void onCreate() {
		super.onCreate();
		FontUtils.init(getApplicationContext());
	}
}
