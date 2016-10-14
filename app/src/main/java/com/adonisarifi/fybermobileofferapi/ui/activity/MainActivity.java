package com.adonisarifi.fybermobileofferapi.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adonisarifi.fybermobileofferapi.R;
import com.adonisarifi.fybermobileofferapi.model.Offers;
import com.adonisarifi.fybermobileofferapi.model.ResultResponsOffer;
import com.adonisarifi.fybermobileofferapi.ui.adapters.OfferAdapter;
import com.adonisarifi.fybermobileofferapi.ui.view.OfferContract;
import com.adonisarifi.fybermobileofferapi.ui.view.OfferPresenter;
import com.adonisarifi.fybermobileofferapi.util.Constants;
import com.adonisarifi.fybermobileofferapi.util.ImageViewFit;
import com.adonisarifi.fybermobileofferapi.util.SupportMethod;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OfferContract.View, OfferAdapter.ItemClickListenerOffer, OfferAdapter.DataObserverListener {


    @Bind(R.id.recycler_view_offers)
    RecyclerView recycler_view_offers;
    @Bind(R.id.progressBar_loading)
    ProgressBar progressBar_loading;
    @Bind(R.id.textView_comments_empty)
    TextView textView_comments_empty;

    private OfferAdapter offerAdapter;

    private ProgressDialog progressDialog;

    private OfferPresenter offerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();


        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_offers) {
            //  fragment = new FyberOfferFragment();
        } else if (id == R.id.nav_setings) {

        } else if (id == R.id.nav_change_api_data) {
            openActivityChangeApiData();
        }

        if (fragment != null) {
            //for this version I don't use any fragment
            //fragmentManager.beginTransaction().replace(R.id.container_main, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showProgresDialog(String message) {
        progressBar_loading.setVisibility(View.VISIBLE);
    }


    private void hideProgressDialog() {
        progressBar_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void setupView() {
        ButterKnife.bind(this);
        setupDrawerLayout();
        setupRecyclerView();
        Bundle b = getIntent().getExtras();
        ResultResponsOffer resultResponsOffer = b.getParcelable(Constants.RESULT_RESPONS_PARCABLE_KEY);
        offerPresenter = new OfferPresenter(this);
        offerPresenter.getOffer(resultResponsOffer);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            showProgresDialog(getString(R.string.please_wait));
        } else {
            hideProgressDialog();
        }
    }

    @Override
    public void showOffer(Offers offer) {
        offerAdapter.addItem(offer);
    }

    @Override
    public void showLoadingOffersError() {

    }

    @Override
    public void showNoOffers() {

    }

    @Override
    public void setupRecyclerView() {
        offerAdapter = new OfferAdapter(this, this, this);
        recycler_view_offers.setAdapter(offerAdapter);
        recycler_view_offers.setLayoutManager(new LinearLayoutManager(this));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recycler_view_offers.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public void setupDrawerLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void showOfferDetails(final Offers offers) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_offer_detail, null);
        ImageView imageView_close = (ImageView) mView.findViewById(R.id.imageView_dialog_close);

        ImageViewFit imageViewFit = (ImageViewFit) mView.findViewById(R.id.imageView_thumbnail_dialog);
        TextView textView_dialog_des = (TextView) mView.findViewById(R.id.textView_offer_dialog_desc);
        TextView textView_dialog_title = (TextView) mView.findViewById(R.id.textView_offer_dialog_title);
        Button button_getcoins = (Button) mView.findViewById(R.id.button_dialog_get_coins);
        button_getcoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SupportMethod.openUrl(offers.getLink()));
            }
        });
        textView_dialog_title.setText(offers.getTitle());
        textView_dialog_des.setText(offers.getTeaser());
        Glide.with(this).load(offers.getThumbnail().getHires()).into(imageViewFit);
        AlertDialog.Builder alertdialogOfferDetails = new AlertDialog.Builder(this);
        alertdialogOfferDetails.setView(mView);
        final AlertDialog alertDialog = alertdialogOfferDetails.create();
        imageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    public void openActivityChangeApiData() {
        Intent intent = new Intent(this, InputApiDataActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void setPresenter(OfferContract.Presenter presenter) {

    }

    @Override
    public void onClickOffer(Offers offer) {
        showOfferDetails(offer);
    }

    @Override
    public void onDataObserver(int adapterSize) {
        if (adapterSize < 1) {
            textView_comments_empty.setVisibility(View.VISIBLE);
        } else {
            textView_comments_empty.setVisibility(View.INVISIBLE);
        }
    }
}
