package cn.itcreator.android.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.itcreator.android.reader.paramter.Constant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class ImageBrowser extends ListActivity {

	private static final int CIRC_SCREEN = Menu.FIRST+1;
	/** exit system menu id */
	private static final int EXIT = Menu.FIRST;

	/** delete file dialog id */
	private static final int DELETEFILE = 2;

	/** display file mode */
	private enum DISPLAYMODE {
		ABSOLUTE, RELATIVE;
	}

	private final DISPLAYMODE mDisplayMode = DISPLAYMODE.RELATIVE;
	private List<IconText> mDirectoryList = new ArrayList<IconText>();
	private File mCurrentDirectory = new File("/sdcard/");

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setTheme(android.R.style.Theme_Black);
		browseToSdCard();
		this.setSelection(0);
		this.getListView().setOnItemLongClickListener(onItemLongClickListener);
	}

	/**
	 * This function browses to the sdcard directory of the file-system.
	 */
	private void browseToSdCard() {
		browseToWhere(new File("/sdcard/"));
	}

	/**
	 * This function browses up one level according to the field:
	 * currentDirectory
	 */
	private void upOneLevel() {
		/** forbidden visit the root directory */
		if (!this.mCurrentDirectory.getParent().equals("/"))
			this.browseToWhere(this.mCurrentDirectory.getParentFile());
	}

	private void browseToWhere(final File aDirectory) {
		if (this.mDisplayMode == DISPLAYMODE.RELATIVE)
			this.setTitle(aDirectory.getAbsolutePath() + " - "
					+ getString(R.string.changebg));
		if (aDirectory.isDirectory()) {
			this.mCurrentDirectory = aDirectory;
			fill(aDirectory.listFiles());
		} else {
			OnClickListener okButtonListener = new OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {

				}
			};
			OnClickListener cancelButtonListener = new OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
				}
			};
		}
	}

	private void fill(File[] files) {
		this.mDirectoryList.clear();

		/** Add the "." == "current directory" */
		this.mDirectoryList.add(new IconText(getString(R.string.current_dir),
				getResources().getDrawable(R.drawable.folder32)));
		this.mDirectoryList.add(new IconText(getString(R.string.ad),
				getResources().getDrawable(R.drawable.folder32)));
		/** and the ".." == 'Up one level' */
		/** forbidden visit root */
		if (!this.mCurrentDirectory.getParent().equals("/"))
			this.mDirectoryList.add(new IconText(
					getString(R.string.up_one_level), getResources()
							.getDrawable(R.drawable.uponelevel)));

		Drawable currentIcon = null;
		for (File currentFile : files) {
			if (currentFile.isDirectory()) {
				currentIcon = getResources().getDrawable(R.drawable.folder32);
			} else {
				String fileName = currentFile.getName();

				if (checkEnds(fileName, getResources().getStringArray(
						R.array.textEnds))) {
					currentIcon = getResources().getDrawable(R.drawable.text32);
				}
				if (checkEnds(fileName, getResources().getStringArray(
						R.array.imageEnds))) {
					currentIcon = getResources().getDrawable(R.drawable.image32);
				}
			}
			switch (this.mDisplayMode) {
			case ABSOLUTE:
				this.mDirectoryList.add(new IconText(currentFile.getPath(),
						currentIcon));
				break;
			case RELATIVE:

				int currentPathStringLenght = this.mCurrentDirectory
						.getAbsolutePath().length();
				this.mDirectoryList.add(new IconText(currentFile
						.getAbsolutePath().substring(currentPathStringLenght),
						currentIcon));
				break;
			}
		}
		Collections.sort(this.mDirectoryList);
		IconTextListAdapter iconTextListAdapter = new IconTextListAdapter(this);
		iconTextListAdapter.setListItems(this.mDirectoryList);
		this.setListAdapter(iconTextListAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		int selectionRowID = (int) id;
		String selectedFileString = this.mDirectoryList.get(selectionRowID)
				.getText();

		if (selectedFileString.equals(getString(R.string.current_dir))) {
			// Refresh
			this.browseToWhere(this.mCurrentDirectory);
		} else if (selectedFileString.equals(getString(R.string.up_one_level))) {
			this.upOneLevel();

		} else {
			File file = null;
			switch (this.mDisplayMode) {
			case RELATIVE:
				file = new File(this.mCurrentDirectory.getAbsolutePath()
						+ this.mDirectoryList.get(selectionRowID).getText());
				if (file.isFile()) {
					// return the image path
					Intent mIntent = new Intent(getApplicationContext(),
							TxtActivity.class);
					Constant.IMAGE_PATH = file.getAbsolutePath();
					//startActivity(mIntent);
					setResult(RESULT_OK, mIntent);
					finish();
				}
				break;
			case ABSOLUTE:
				file = new File(this.mDirectoryList.get(selectionRowID)
						.getText());
				if (file.isFile()) {
					// return the image path
					Intent mIntent = new Intent(getApplicationContext(),
							TxtActivity.class);
					Constant.IMAGE_PATH = file.getAbsolutePath();
					//startActivity(mIntent);
					setResult(RESULT_OK, mIntent);
					finish();
				}
				break;
			}
			if (file != null && file.isDirectory())
				this.browseToWhere(file);
		}
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

	private OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long id) {
			Constant.FILE_PATH = mCurrentDirectory.getAbsolutePath()
					+ mDirectoryList.get((int) id).getText();
			showDialog(DELETEFILE);
			return true;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, EXIT, 1, R.string.back).setShortcut('3', 'a').setIcon(
				R.drawable.uponelevel);
		menu.add(1, CIRC_SCREEN, 0, R.string.circumgyrate).setShortcut('3', 'c').setIcon(
				R.drawable.circscreen);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DELETEFILE:
			return new AlertDialog.Builder(ImageBrowser.this).setTitle(
					getString(R.string.suredelete)).setMessage(
					getString(R.string.deletefile)).setPositiveButton(
					R.string.sure, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							File f = new File(Constant.FILE_PATH);
							f.delete();
							browseToWhere(mCurrentDirectory);
						}
					}).setNeutralButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					}).create();

		default:
			return null;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case CIRC_SCREEN:
			circumgyrateScreen();
			return true;
		case EXIT:// exit system
			this.finish();
			return true;
		default:
			break;
		}
		return false;
	}
	/**
	 * ��ת��Ļ
	 */
	private void circumgyrateScreen(){
		if(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
			//����Ǻ����Ļ�������Ϊ��ͨģʽ
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			
		}else{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
	}
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			this.browseToWhere(this.mCurrentDirectory);
    	}
    	if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
    		this.browseToWhere(this.mCurrentDirectory);
    	}
    	if(getResources().getConfiguration().keyboardHidden == Configuration.KEYBOARDHIDDEN_NO){
    		this.browseToWhere(this.mCurrentDirectory);
    	}
    	if(getResources().getConfiguration().keyboardHidden == Configuration.KEYBOARDHIDDEN_YES){
    		this.browseToWhere(this.mCurrentDirectory);
    	}
		
	}
	
}