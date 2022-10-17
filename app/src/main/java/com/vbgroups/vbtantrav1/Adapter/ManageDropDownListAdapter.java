package com.vbgroups.vbtantrav1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vbgroups.vbtantrav1.Admin.ManageProjects1;
import com.vbgroups.vbtantrav1.R;

import java.util.ArrayList;

public class ManageDropDownListAdapter extends BaseAdapter {

	private ArrayList<String> mListItems;
	private LayoutInflater mInflater;
	private TextView mSelectedItems;
	private static int selectedCount = 0;
	private static String firstSelected = "";
	private ViewHolder holder;
	private static String selected = "";	//shortened selected values representation

	public static String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		ManageDropDownListAdapter.selected = selected;
	}

	public ManageDropDownListAdapter(Context context, ArrayList<String> items,
                                     TextView tv) {
		mListItems = new ArrayList<String>();
		mListItems.addAll(items);
		mInflater = LayoutInflater.from(context);
		mSelectedItems = tv;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.drop_down_list_row, null);
			holder = new ViewHolder();
			holder.tv = (TextView) convertView.findViewById(R.id.SelectOption);
			holder.chkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv.setText(mListItems.get(position));

		final int position1 = position;

		//whenever the checkbox is clicked the selected values textview is updated with new selected values
		holder.chkbox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setText(position1);
			}
		});

		if(ManageProjects1.checkSelected[position])
			holder.chkbox.setChecked(true);
		else
			holder.chkbox.setChecked(false);
		return convertView;
	}


	/*
	 * Function which updates the selected values display and information(checkSelected[])
	 * */
	private void setText(int position1){
		if (!ManageProjects1.checkSelected[position1]) {
			ManageProjects1.checkSelected[position1] = true;
			selectedCount++;
		} else {
			ManageProjects1.checkSelected[position1] = false;
			selectedCount--;
		}

		if (selectedCount == 0) {
			mSelectedItems.setText("");
		}
		else if (selectedCount == 1) {
			for (int i = 0; i < ManageProjects1.checkSelected.length; i++) {
				if (ManageProjects1.checkSelected[i] == true) {
					firstSelected = mListItems.get(i);
					break;
				}
			}
			mSelectedItems.setText(firstSelected);
			setSelected(firstSelected);
		} else if (selectedCount > 1) {
			for (int i = 0; i < ManageProjects1.checkSelected.length; i++) {
				if (ManageProjects1.checkSelected[i] == true) {
					firstSelected = mListItems.get(i);
					break;
				}
			}
			mSelectedItems.setText(firstSelected + " & " + " more");
			setSelected(firstSelected + " & " + " more");
		}
	}

	private class ViewHolder {
		TextView tv;
		CheckBox chkbox;
	}
}
