package com.novetrics.beautifulnavigation;

import static com.novetrics.beautifulnavigation.BaseActivity.prefManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.novetrics.beautifulnavigation.fragments.AboutusFragment;
import com.novetrics.beautifulnavigation.fragments.ChangePasswordFragment;
import com.novetrics.beautifulnavigation.fragments.ContactusFragment;
import com.novetrics.beautifulnavigation.fragments.DashboardFragment;
import com.novetrics.beautifulnavigation.fragments.DictionaryFragment;
import com.novetrics.beautifulnavigation.fragments.GalleryFragment;
import com.novetrics.beautifulnavigation.fragments.LibraryFragment;
import com.novetrics.beautifulnavigation.fragments.MemberFragment;
import com.novetrics.beautifulnavigation.fragments.NotificationFragment;
import com.novetrics.beautifulnavigation.fragments.ProfileFragment;

public class MainActivity extends BaseActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    String firstname;
    TabLayout tabLayout;
    FrameLayout simpleFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        prefManager.connectDB();
        firstname = prefManager.getString("firstname");
        prefManager.closeDB();
        Toast.makeText(MainActivity.this, "welcome : "+firstname, Toast.LENGTH_SHORT).show();

        // get the reference of FrameLayout and TabLayout
        simpleFrameLayout = (FrameLayout) findViewById(R.id.frame);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
        //title tabs
        title_tabs();
        //toolbar menus

        if (savedInstanceState == null)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame,new DashboardFragment()).commit();
        }
        // perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new DashboardFragment();
                        loadFragment(fragment);
                        break;
                    case 1:
                        fragment = new LibraryFragment();
                        loadFragment(fragment);
                        break;
                    case 2:
                        fragment = new GalleryFragment();
                        loadFragment(fragment);
                        break;
        }
        // Logic to load the starting destination when the activity is first created.

//////////////////////////////////////////////////////////////////////////////////////////////////////////////


        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                System.out.println("## id "+id);
                Fragment fragment=null;
                switch (id)
                {
                    case R.id.members:
                        fragment=new MemberFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.dictionary:
                        fragment=new DictionaryFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.profile:
                        fragment=new ProfileFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.call:
                        Toast.makeText(MainActivity.this, "calling to head", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.change_password:
                        fragment=new ChangePasswordFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.shapre_app:
                        Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,R.string.app_name);
                        String app_url = "https://play.google.com/store/apps/details?id=com.icat.armaloft";
                        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                        startActivity(Intent.createChooser(shareIntent, "Share by"));
                        break;
                    case R.id.conntact_us:
                        fragment=new ContactusFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.about_us:
                        fragment=new AboutusFragment();
                        loadFragment(fragment);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

   @Override
   public void onTabUnselected(TabLayout.Tab tab) {
   }

   @Override
   public void onTabReselected(TabLayout.Tab tab) {

   }
   });
    }
    // title tabs like home,libary and gallery
    private void title_tabs() {
        // Create a new Tab named "First"
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("HOme"); // set the Text for the first Tab
        firstTab.setIcon(R.drawable.ic_baseline_home_24); // set an icon for the
// first tab
        tabLayout.addTab(firstTab); // add  the tab at in the TabLayout
// Create a new Tab named "Second"
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Library"); // set the Text for the second Tab
        secondTab.setIcon(R.drawable.ic_library_books); // set an icon for the second tab
        tabLayout.addTab(secondTab); // add  the tab  in the TabLayout
// Create a new Tab named "Third"
        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Gallery"); // set the Text for the first Tab
        thirdTab.setIcon(R.drawable.ic_gallery); // set an icon for the first tab
        tabLayout.addTab(thirdTab); // add  the tab at in the TabLayout

    }

    private void logout_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MyDialogTheme);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle(R.string.app_name);
        alertDialogBuilder.setIcon(R.drawable.logo);
        alertDialogBuilder.setMessage(R.string.alert_logout);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                prefManager.connectDB();
                prefManager.setBoolean("isLogin",false);
                prefManager.closeDB();
//                Toast.makeText(getApplicationContext(), "Logout Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void loadFragment(Fragment fragment) {

        System.out.println("fragment loaded ");
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }

    //menus
    // Activity's overrided method used to set the menu file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // Activity's overrided method used to perform click events on menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
// Display menu item's title by using a Toast.
//        if (id == R.id.action_settings) {
//            Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_SHORT).show();
//            return true;
//        } else
      if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_logout)
        {
            logout_dialog();
            return true;
        }else if (id == R.id.action_notification)
        {
          Fragment fragment=new NotificationFragment();
          loadFragment(fragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}