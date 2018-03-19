package mayphoo.mpk.expandablelistviewdemo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import mayphoo.mpk.expandablelistviewdemo.ExpandedMenuModel;
import mayphoo.mpk.expandablelistviewdemo.R;


/**
 * Created by Dell on 11/28/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<ExpandedMenuModel> mListDataHeader; //header titles

    //child data in format of header title, child title
    private HashMap<ExpandedMenuModel, List<String>> mListDataChild;
    ExpandableListView expandList;

    public ExpandableListAdapter(Context mContext, List<ExpandedMenuModel> mListDataHeader, HashMap<ExpandedMenuModel, List<String>> mListDataChild, ExpandableListView expandList) {
        this.mContext = mContext;
        this.mListDataHeader = mListDataHeader;
        this.mListDataChild = mListDataChild;
        this.expandList = expandList;
    }

    @Override
    public int getGroupCount() {
        int i = mListDataHeader.size();
        Log.d("Header Group count", String.valueOf(i));
        return this.mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childCount = 0;
        //test for position
        switch (groupPosition){
            case 2:
                //expands with the size of it's sub menu items
                childCount = this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).size();
                break;
            case 3:
                //expands with the size of it's sub menu items
                childCount = this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).size();
                break;
            case 4:
                //expands with the size of it's sub menu items
                childCount = this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).size();
                break;
            default: break;
            //list does not expand
        }

        //childCount = this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).size();
        return childCount;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.d("Child",mListDataChild.get(this.mListDataHeader.get(groupPosition))
                .get(childPosition).toString());
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ExpandedMenuModel headerTitle = (ExpandedMenuModel) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listheader, null);
        }

        TextView headerText = convertView.findViewById(R.id.tv_header_menu);
        ImageView headerIcon = convertView.findViewById(R.id.iv_header_icon);
        ImageView indicator = convertView.findViewById(R.id.iv_indicator_red);

        headerText.setText(headerTitle.getIconName());
        headerIcon.setImageResource(headerTitle.getIconImg());

        if(getChildrenCount(groupPosition) == 0) {
            indicator.setVisibility(View.INVISIBLE);
        } else {
            indicator.setVisibility(View.VISIBLE);
            indicator.setImageResource(isExpanded ? R.drawable.ic_arrow_up_red_24dp : R.drawable.ic_arrow_down_red_24dp);
        }

        View divider = convertView.findViewById(R.id.divider);
        switch (groupPosition){
            case 0:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.VISIBLE);
                break;
            case 1:
                headerIcon.setVisibility(View.GONE);
                divider.setVisibility(View.INVISIBLE);
                break;
            case 2:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.INVISIBLE);
                break;
            case 3:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.INVISIBLE);
                break;
            case 4:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.INVISIBLE);
                break;
            case 5:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.INVISIBLE);
                break;
            case 6:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.INVISIBLE);
                break;
            case 7:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.VISIBLE);
                break;
            case 8:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.INVISIBLE);
                break;
            case 9:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.INVISIBLE);
                break;
            case 10:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.INVISIBLE);
                break;
            case 11:
                headerIcon.setVisibility(View.VISIBLE);
                divider.setVisibility(View.INVISIBLE);
                break;
            default: break;
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        switch (groupPosition){
            case 2:
                if(convertView == null){
                    LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.listsubmenu, null);
                }

                TextView subMenuMovieText = convertView.findViewById(R.id.tv_sub_menu);
                subMenuMovieText.setText(childText);
                break;
            case 3:
                if(convertView == null){
                    LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.listsubmenu, null);
                }

                TextView subMenuEntertainmentText = convertView.findViewById(R.id.tv_sub_menu);
                subMenuEntertainmentText.setText(childText);
                break;
            case 4:
                if(convertView == null){
                    LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.listsubmenu, null);
                }

                TextView subMenuInfoText = convertView.findViewById(R.id.tv_sub_menu);
                subMenuInfoText.setText(childText);
                break;

            default: break;
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}