package booster.co.nz.fundsinvestinvestigation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import booster.co.nz.fundsinvestinvestigation.common.Utility;
import booster.co.nz.fundsinvestinvestigation.fragments.UsageFragment;
import booster.co.nz.fundsinvestinvestigation.fragments.InvestTypeFragment;
import booster.co.nz.fundsinvestinvestigation.fragments.QuestionnaireFragment;
import booster.co.nz.fundsinvestinvestigation.fragments.QuestionnaireResultFragment;
import booster.co.nz.fundsinvestinvestigation.fragments.SubmitFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        QuestionnaireResultFragment.OnFragmentInteractionListener{

    private String SCORE ="";
    private boolean isSubmitFragmentAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isSubmitFragmentAvailable =false;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        UsageFragment defaultFragment = new UsageFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.layout_for_fragment,defaultFragment,defaultFragment.getTag()).commit();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        String investType ="";
        switch(id){
            case R.id.nav_defensive:
                investType ="Defensive";
                break;
            case R.id.nav_conservative:
                investType = "Conservative";
                break;
            case R.id.nav_balanced:
                investType = "Balanced";
                break;
            case R.id.nav_balanced_growth:
                investType = "Balanced Growth";
                break;
            case R.id.nav_growth:
                investType = "Growth";
                break;
            case R.id.nav_aggressive_growth:
                investType = "Aggressive Growth";
                break;
            case R.id.nav_usage:
                UsageFragment usageFragment = new UsageFragment();
                manager.beginTransaction().replace(R.id.layout_for_fragment,usageFragment,usageFragment.getTag()).commit();
                break;
            case R.id.nav_questionnaire:
                QuestionnaireFragment questionnaireFragment = new QuestionnaireFragment();
                manager.beginTransaction().replace(R.id.layout_for_fragment,questionnaireFragment,questionnaireFragment.getTag()).commit();
                break;
            case R.id.nav_submit:
                if(!isSubmitFragmentAvailable){
                    Utility.alertSimpleMessage(this,"Please finish the questionnaire first, then submit");
                }else{
                    SubmitFragment submitFragment = SubmitFragment.newInstance(SCORE);
                    manager.beginTransaction().replace(R.id.layout_for_fragment,submitFragment,submitFragment.getTag()).commit();
                }

                break;

        }

        if((id !=R.id.nav_questionnaire) && (id !=R.id.nav_submit) &&(id!=R.id.nav_usage)){
            InvestTypeFragment investTypeFragment = InvestTypeFragment.newInstance(investType);
            manager.beginTransaction().replace(R.id.layout_for_fragment,investTypeFragment,investTypeFragment.getTag()).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String score) {
        SCORE = score;
        isSubmitFragmentAvailable = true;
    }
}
