package com.novetrics.beautifulnavigation;

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

import com.google.android.material.navigation.NavigationView;
import com.novetrics.beautifulnavigation.fragments.ChangePasswordFragment;
import com.novetrics.beautifulnavigation.fragments.ContactusFragment;
import com.novetrics.beautifulnavigation.fragments.DashboardFragment;
import com.novetrics.beautifulnavigation.fragments.NotificationFragment;
import com.novetrics.beautifulnavigation.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
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

        builder.setCancelable(true);
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.alert_logout);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();

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