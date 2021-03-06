package com.hkm.toolbarlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hkm.advancedtoolbar.V3.LayoutAsset;
import com.hkm.advancedtoolbar.V3.LiveIcon;
import com.hkm.advancedtoolbar.V3.layout.CLayO;
import com.hkm.advancedtoolbar.V3.TopBarManager;
import com.hkm.advancedtoolbar.V3.layout.SimpleSearchCallBack;
import com.hkm.advancedtoolbar.V3.layout.commonSearchBarMgr;
import com.hkm.advancedtoolbar.materialsearch.MaterialSearchView;

/**
 * Created by hesk on 16/7/15.
 */
public class TopBarManagerExampleFull extends AppCompatActivity implements CLayO.OnInteract {
    private ActionBar actionbar;
    private TopBarManager worker;
    private LiveIcon dynamic_icon;
    private Toolbar toolbar;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = (MaterialSearchView) findViewById(R.id.cmarterialsearch);
        searchView.setVoiceSearch(true);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG).show();
                // searchSubmission(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        try {
            worker = TopBarManager.Builder.with(this)
                    .companyLogo(R.drawable.starz_logo)
                    //  .searchView(LayoutAsset.classic_3)
                    /*.searchBarEvents(new SimpleSearchCallBack() {
                        @Override
                        public void onKeySearchStartConfirm(String text) {
                            Log.d("start", text);
                        }

                        @Override
                        public void onKeySearchLetter(String text) {
                            Log.d("start", text);
                        }

                        @Override
                        public void onRestoreToNormal(ActionBar toolbar) {
                            worker.showBack();
                        }

                        @Override
                        public void onPressSearchButton(ActionBar toolbar) {

                        }
                    })
                    .searchCancalIconColorId(R.color.amber_400)
                    .searchCancelColorId(R.color.amber_400)*/
                    .burgerIcon(R.mipmap.ic_action_share)
                    .setLiveIcon(R.layout.dynamic_icon_p, R.mipmap.crossmp)
                    .setOnCustomItemClickListener(this)
                    .setCustomMainBar(LayoutAsset.i_logo_ir)
                    .overrideIcons(R.mipmap.cross_grey, R.mipmap.cross_mi, R.mipmap.crossmp)
                    .build(toolbar);
            //.externalLayoutOutToolBar(R.layout.topbarlayout)
            dynamic_icon = worker.getDynamicIcon();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        // dynamic_icon.onOptionItemInit(menu, R.id.dynamic);
        return true;
    }

    private int u = 2;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idt = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (idt == R.id.action_search) {
            // worker.triggerfromSearchIcon(item);
            worker.triggerForCustomExternalCustomView();
            return true;
        } else if (idt == R.id.custombar) {
         //   Intent g = new Intent(this, CustomActionBar.class);
         //   startActivity(g);
        } else if (idt == R.id.dynamic) {
            dynamic_icon.update(item, u++);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnClick(int resId) {
        if (resId == R.id.i_kl1) {
           // worker.triggerfromSearchIcon(null);
            searchView.showSearch();
        }
    }
}
