package cn.itcreator.android.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.itcreator.android.reader.paramter.Constant;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;

public class PictureBrowser extends Activity {
	
	private static final int BACK = Menu.FIRST ;
	private ImageSwitcher mSwitcher;
	private List<String> mThumbIds = new ArrayList<String>();

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.picturebrowser);
		mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
		Log.d("create ", "mSwitcher is :" + mSwitcher);
		mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		addImageToList(Constant.FILE_PATH);
		Gallery g = (Gallery) findViewById(R.id.gallery);
		mSwitcher.setBackgroundDrawable(Drawable
				.createFromPath(Constant.FILE_PATH));

		g.setAdapter(new ImageAdapter(this));

		g.setOnItemSelectedListener(x);
	
	}

	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, BACK, 1, getString(R.string.back)).setIcon(R.drawable.uponelevel);
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case	BACK:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Select a picture event
	 */
	private OnItemSelectedListener x = new AdapterView.OnItemSelectedListener() {

		
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String path = mThumbIds.get(arg2);
			Log.d("onItemSelected", path);
			mSwitcher.setBackgroundDrawable(Drawable.createFromPath(path));
			System.gc();
		}

		
		public void onNothingSelected(AdapterView<?> arg0) {

		}

	};

	/**
	 * add the file path to list
	 * 
	 * @param filePath
	 *            the file path or a directory
	 */
	private void addImageToList(String filePath) {
		String tag = "addImageToList";
		File f = new File(filePath);
		if (f.isFile()) {
			f = f.getParentFile();
		}
		Log.d(tag, "start get the list files");
		File[] fs = f.listFiles();
		int length = fs.length;
		String[] imageEnds = getResources().getStringArray(R.array.imageEnds);
		for (int i = 0; i < length; i++) {
			String path = fs[i].getAbsolutePath();
			Log.d("file path is :", path);
			if (checkEnds(path, imageEnds)) {
				mThumbIds.add(path);
			}
		}
		f = null;
		fs = null;
		System.gc();
	}

	/**
	 * Check the string ends
	 * 
	 * @param checkItsEnd
	 * @param fileEndings
	 * @return
	 */
	private boolean checkEnds(String checkItsEnd, String[] fileEndings) {
		for (String aEnd : fileEndings) {
			if (checkItsEnd.endsWith(aEnd))
				return true;
		}
		return false;
	}

	public class ImageAdapter extends BaseAdapter {
		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return mThumbIds.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView i = new ImageView(mContext);
			i
					.setImageDrawable(Drawable.createFromPath(mThumbIds
							.get(position)));
			i.setAdjustViewBounds(true);
			i.setLayoutParams(new Gallery.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			return i;
		}

		private Context mContext;

	}

	
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

		}
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

		}
		if (getResources().getConfiguration().keyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {
		}
		if (getResources().getConfiguration().keyboardHidden == Configuration.KEYBOARDHIDDEN_YES) {
		}
	}
}
