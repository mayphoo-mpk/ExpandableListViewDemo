package mayphoo.mpk.expandablelistviewdemo.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.expandablelistviewdemo.ExpandedMenuModel;
import mayphoo.mpk.expandablelistviewdemo.adapters.ExpandableListAdapter;
import mayphoo.mpk.expandablelistviewdemo.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MenuItemCompat.OnActionExpandListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_search)
    TextView tvSearch;

    @BindView(R.id.fl_frame)
    FrameLayout flContainer;

    @BindView(R.id.pb_searchProgress)
    ProgressBar progressBar;

    @BindView(R.id.rv_search_list)
    RecyclerView rvSearch;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_menu)
    ExpandableListView expandableList;

    ExpandableListAdapter mMenuAdapter;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //add data for expandable list view
        prepareListData();
        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, expandableList);
        //setting list adapter
        expandableList.setAdapter(mMenuAdapter);

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                switch (groupPosition) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }

                //selected check item
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);

                //get the group header
                ExpandedMenuModel headerInfo = listDataHeader.get(groupPosition);
                //get the child info
                String childInfo = listDataChild.get(headerInfo).get(childPosition);
                if (childInfo.equals(getResources().getString(R.string.local_news))) {
                    tvTitle.setText(getResources().getString(R.string.local_news));
                } else if (childInfo.equals(getResources().getString(R.string.international_news))) {
                    tvTitle.setText(getResources().getString(R.string.international_news));
                } else if (childInfo.equals(getResources().getString(R.string.series))) {
                    tvTitle.setText(getResources().getString(R.string.series));
                } else if (childInfo.equals(getResources().getString(R.string.favorite))) {
                    tvTitle.setText(getResources().getString(R.string.favorite));
                } else if (childInfo.equals(getResources().getString(R.string.novel_book))) {
                    tvTitle.setText(getResources().getString(R.string.novel_book));
                } else if (childInfo.equals(getResources().getString(R.string.comedy_book))) {
                    tvTitle.setText(getResources().getString(R.string.comedy_book));
                } else if (childInfo.equals(getResources().getString(R.string.translate_book))) {
                    tvTitle.setText(getResources().getString(R.string.translate_book));
                } else if (childInfo.equals(getResources().getString(R.string.myanmar_music))) {
                    tvTitle.setText(getResources().getString(R.string.myanmar_music));
                } else if (childInfo.equals(getResources().getString(R.string.eng_music))) {
                    tvTitle.setText(getResources().getString(R.string.eng_music));
                }

                drawerLayout.closeDrawers();
                return false;
            }
        });

        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                //selected check item
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition));

                //get the group header
                ExpandedMenuModel headerInfo = listDataHeader.get(groupPosition);

                //if this parent have no child, not close drawers
                if (headerInfo.getIconName().equals( getResources().getString(R.string.nav_home))) {
                    parent.setItemChecked(index, true);
                    tvTitle.setText("Home");
                    drawerLayout.closeDrawers();
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.news))) {
                    parent.setItemChecked(index, true);
                    tvTitle.setText(R.string.news);
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.books))) {
                    tvTitle.setText(R.string.books);
                    parent.setItemChecked(index, true);
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.musics))) {
                    tvTitle.setText(R.string.musics);
                    parent.setItemChecked(index, true);
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.popular))) {
                    tvTitle.setText(R.string.popular);
                    parent.setItemChecked(index, true);
                    drawerLayout.closeDrawers();
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.mrtv))) {
                    tvTitle.setText(R.string.mrtv);
                    parent.setItemChecked(index, true);
                    drawerLayout.closeDrawers();
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.mwd))) {
                    tvTitle.setText(R.string.mwd);
                    parent.setItemChecked(index, true);
                    drawerLayout.closeDrawers();
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.channel7))) {
                    tvTitle.setText(R.string.channel7);
                    parent.setItemChecked(index, true);
                    drawerLayout.closeDrawers();
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.download))) {
                    tvTitle.setText(R.string.download);
                    parent.setItemChecked(index, true);
                    drawerLayout.closeDrawers();
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.youtube))) {
                    tvTitle.setText(R.string.youtube);
                    parent.setItemChecked(index, true);
                    drawerLayout.closeDrawers();
                } else if (headerInfo.getIconName().equals(getResources().getString(R.string.setting))) {
                    tvTitle.setText(R.string.setting);
                    parent.setItemChecked(index, true);
                    drawerLayout.closeDrawers();
                } else {
                    parent.setItemChecked(index, false);
                }

                return false;
            }
        });
        View headerView = navigationView.getHeaderView(0);
        ImageView ivUserIcon = (ImageView) headerView.findViewById(R.id.iv_user_icon);
        ivUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked User Icon!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<ExpandedMenuModel>();
        listDataChild = new HashMap<ExpandedMenuModel, List<String>>();

        ExpandedMenuModel menuHome = new ExpandedMenuModel();
        menuHome.setIconName(getResources().getString(R.string.nav_home));
        menuHome.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuHome);

        ExpandedMenuModel groupMenu = new ExpandedMenuModel();
        groupMenu.setIconName(getResources().getString(R.string.nav_group));
        listDataHeader.add(groupMenu);

        ExpandedMenuModel menuNews = new ExpandedMenuModel();
        menuNews.setIconName(getResources().getString(R.string.news));
        menuNews.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuNews);

        ExpandedMenuModel menuBooks = new ExpandedMenuModel();
        menuBooks.setIconName(getResources().getString(R.string.books));
        menuBooks.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuBooks);

        ExpandedMenuModel menuMusic = new ExpandedMenuModel();
        menuMusic.setIconName(getResources().getString(R.string.musics));
        menuMusic.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuMusic);

        ExpandedMenuModel menuPopular = new ExpandedMenuModel();
        menuPopular.setIconName(getResources().getString(R.string.popular));
        menuPopular.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuPopular);

        ExpandedMenuModel menuMRTV = new ExpandedMenuModel();
        menuMRTV.setIconName(getResources().getString(R.string.mrtv));
        menuMRTV.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuMRTV);

        ExpandedMenuModel menuMWD = new ExpandedMenuModel();
        menuMWD.setIconName(getResources().getString(R.string.mwd));
        menuMWD.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuMWD);

        ExpandedMenuModel menuChannel7 = new ExpandedMenuModel();
        menuChannel7.setIconName(getResources().getString(R.string.channel7));
        menuChannel7.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuChannel7);

        ExpandedMenuModel menuDownload = new ExpandedMenuModel();
        menuDownload.setIconName(getResources().getString(R.string.download));
        menuDownload.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuDownload);

        ExpandedMenuModel menuYouTube = new ExpandedMenuModel();
        menuYouTube.setIconName(getResources().getString(R.string.youtube));
        menuYouTube.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuYouTube);

        ExpandedMenuModel menuSetting = new ExpandedMenuModel();
        menuSetting.setIconName("Setting");
        menuSetting.setIconImg(R.drawable.ic_nav_setting);
        listDataHeader.add(menuSetting);

        List<String> menuNewsSub = new ArrayList<String>();
        menuNewsSub.add(getResources().getString(R.string.local_news));
        menuNewsSub.add(getResources().getString(R.string.international_news));

        List<String> subMenuBook = new ArrayList<String>();
        subMenuBook.add(getResources().getString(R.string.novel_book));
        subMenuBook.add(getResources().getString(R.string.comedy_book));
        subMenuBook.add(getResources().getString(R.string.translate_book));

        List<String> subMenuMusic = new ArrayList<String>();
        subMenuMusic.add(getResources().getString(R.string.myanmar_music));
        subMenuMusic.add(getResources().getString(R.string.eng_music));

        listDataChild.put(listDataHeader.get(2), menuNewsSub);
        listDataChild.put(listDataHeader.get(3), subMenuBook);
        listDataChild.put(listDataHeader.get(4), subMenuMusic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //change text color for search
        EditText searchEditText = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.selector));
        searchEditText.setHintTextColor(getResources().getColor(R.color.selector));

        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0) {
                    tvSearch.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        flContainer.setVisibility(View.INVISIBLE);
        tvSearch.setVisibility(View.VISIBLE);
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        flContainer.setVisibility(View.VISIBLE);
        tvSearch.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        rvSearch.setVisibility(View.GONE);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setCheckable(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


}