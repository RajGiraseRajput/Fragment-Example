package com.example.fragmentexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private static final String ROOT_FRAGMENT_TAG = "root_fragment";
    Button btnFragA, btnFragB,btnFragC;
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnFragA = findViewById(R.id.btnFragA);
        btnFragB = findViewById(R.id.btnFragB);
        btnFragC = findViewById(R.id.btnFragC);

        // default loaded fragment
        loadFragment(AFragment.getInstance("Ramanujan",11),0);

        btnFragA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                loadFragment(new AFragment(),1);
                loadFragment(AFragment.getInstance("Ramanujan",11),1);
            }
        });

        btnFragB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new BFragment(),1);
            }
        });

        btnFragC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CFragment(),1);
            }
        });

    }

    public void loadFragment(Fragment fragment, int flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // send data through Bundle

//        Bundle bundle = new Bundle();
//        bundle.putString("arg1","Ram");
//        bundle.putInt("arg2",20);

//        fragment.setArguments(bundle);

        if (flag == 0) {
            ft.add(R.id.container, fragment);
            fm.popBackStack(ROOT_FRAGMENT_TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.addToBackStack(ROOT_FRAGMENT_TAG);
        }else {
            ft.replace(R.id.container,fragment);
            ft.addToBackStack(null);
        }

        ft.commit();

    }

    public void callFromFragment(){
        Log.d("inAct","From Fragment");
    }

}