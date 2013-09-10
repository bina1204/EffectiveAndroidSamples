package com.gsbina.android.dashclocksample;

import android.content.Intent;
import android.net.Uri;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

public class SampleExtension extends DashClockExtension {

	@Override
	protected void onUpdateData(int reason) {
		ExtensionData data = new ExtensionData();
		data.visible(true);
		data.icon(R.drawable.ic_launcher);
		data.status("Hello");
		data.expandedTitle("Hello, world!");
		data.expandedBody("This is an example.");
		Uri uri = Uri.parse("http://www.google.com");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		data.clickIntent(intent);
		publishUpdate(data);
	}

}
