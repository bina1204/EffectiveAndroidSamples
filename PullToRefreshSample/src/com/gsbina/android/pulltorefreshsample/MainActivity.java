package com.gsbina.android.pulltorefreshsample;

import java.util.Arrays;
import java.util.LinkedList;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends ListActivity {

	private static final String[] INITIAL_LIST = {
			"最初に", "表示される", "リストの", "項目で", "あります",
	};

	private PullToRefreshListView mPullToRefreshListView;
	private LinkedList<String> mItemList;
	private ArrayAdapter<String> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

		mPullToRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						new GetDataTask().execute();

					}
				});

		mItemList = new LinkedList<String>();
		mItemList.addAll(Arrays.asList(INITIAL_LIST));
		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mItemList);
		ListView actualListView = mPullToRefreshListView.getRefreshableView();
		actualListView.setAdapter(mAdapter);
	}

	private class GetDataTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			mItemList.addFirst("追加された項目");
			mAdapter.notifyDataSetChanged();
			mPullToRefreshListView.onRefreshComplete();
			super.onPostExecute(result);
		}

	}
}
