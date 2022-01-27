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
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.novetrics.beautifulnavigation.fragments.ChangePasswordFragment;
import com.novetrics.beautifulnavigation.fragments.ContactusFragment;
import com.novetrics.beautifulnavigation.fragments.DashboardFragment;
import com.novetrics.beautifulnavigation.fragments.NotificationFragment;
import com.novetrics.beautifulnavigation.fragments.ProfileFragment;

public class MainActivity extends BaseActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    String firstname;
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

        // Logic to load the starting destination when the activity is first created.
        if (savedInstanceState == null) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame,new DashboardFragment()).commit();
        }
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                System.out.println("## id "+id);
                Fragment fragment=null;
                switch (id)
                {
                    case R.id.dashboard:
                        fragment=new DashboardFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.profile:
                        fragment=new ProfileFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.notification:
                        fragment=new NotificationFragment();
                        loadFragment(fragment);
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
                    case R.id.logout:
                        logout_dialog();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void logout_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MyDialogTheme);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.app_name);
        alertDialogBuilder.setIcon(R.drawable.basket);
        alertDialogBuilder.setMessage(R.string.alert_logout);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                prefManager.connectDB();
                prefManager.setBoolean("isLogin",false);
                prefManager.closeDB();
                Toast.makeText(getApplicationContext(), "Logout Successfully", Toast.LENGTH_LONG).show();
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
}