package com.gwh.math.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import com.gwh.math.R;
import com.gwh.math.ui.widgets.game.ShuduView;
import com.vip.base.activity.BaseActivity;

import static com.vip.base.util.StatusBarKt.immersive;

public class Game2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        ShuduView sView=new ShuduView(this);
//        setContentView(sView);
        setContentView(R.layout.activity_game);

      //  ShuduView viewById = findViewById(R.id.view_shudu);


        ShuduView viewShuDu = findViewById(R.id.view_shudu);
        Button btAgain = findViewById(R.id.bt_again);

        btAgain.setOnClickListener(v -> {
            viewShuDu.play();
        });

    }



    @Override
    public void initData() {
        immersive(this,0,true);

        hintActionBar();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main,menu);
//        return true;
//    }
}