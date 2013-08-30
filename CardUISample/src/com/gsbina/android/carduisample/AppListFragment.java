package com.gsbina.android.carduisample;

import java.util.List;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AppListFragment extends ListFragment {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final FragmentActivity activity = getActivity();
		PackageManager pm = activity.getPackageManager();
		List<PackageInfo> packageInfoList = pm
				.getInstalledPackages(PackageManager.GET_ACTIVITIES);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
				R.layout.list_item_card, android.R.id.text1);

		if (packageInfoList != null) {
			for (PackageInfo info : packageInfoList) {
				adapter.add(info.applicationInfo.loadLabel(pm).toString());
			}
		}
		
		int padding = (int) (getResources().getDisplayMetrics().density * 8);
		ListView listView = getListView();
		listView.setPadding(padding, 0, padding, 0);
		listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
		
		LayoutInflater inflater = LayoutInflater.from(activity);
		View header = inflater.inflate(R.layout.list_header_footer, listView, false);
		View footer = inflater.inflate(R.layout.list_header_footer, listView, false);
		listView.addHeaderView(header, null, false);
		listView.addFooterView(footer, null, false);
		
		listView.setDivider(null);
		
		setListAdapter(adapter);
	}
}
