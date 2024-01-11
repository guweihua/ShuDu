package com.gwh.math.ui.widgets.game;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.gwh.math.R;

public class KeyDialog  extends Dialog {

    //用来存放代表对话框中按钮的对象
    private final View keys[]=new View[10];
    private final int used[];
    private ShuduView myView;
    //构造函数的第二个参数当中保存着当前单元格已经使用过的数字
    public KeyDialog(Context context, int[] used, ShuduView myView) {
        super(context);
        this.used=used;
        this.myView=myView;
    }
    //当一个Dialog第一次显示的时候，会调用Oncreate方法
    @Override
    protected void onCreate(Bundle savedInstanceStatE) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceStatE);
        //设置标题
        setTitle("KeyDialog");
        //用于为该Dialog设置布局文件
        setContentView(R.layout.keypad);
        findViews();

        //遍历整个used数组
//        for(int i=0;i<used.length;i++) {
//            if(used[i]!=0) {
//                keys[used[i]-1].setVisibility(View.INVISIBLE);
//            }
//        }
        setListeners();
    }

    private void findViews() {
        keys[0]=findViewById(R.id.keypad_1);
        keys[1]=findViewById(R.id.keypad_2);
        keys[2]=findViewById(R.id.keypad_3);
        keys[3]=findViewById(R.id.keypad_4);
        keys[4]=findViewById(R.id.keypad_5);
        keys[5]=findViewById(R.id.keypad_6);
        keys[6]=findViewById(R.id.keypad_7);
        keys[7]=findViewById(R.id.keypad_8);
        keys[8]=findViewById(R.id.keypad_9);

       // AppCompatImageView viewById = findViewById(R.id.ic_cachu);
        keys[9]=findViewById(R.id.ic_cachu);

//        viewById.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                returnResult(0);
//            }
//        });
    }
    //@L_468_17@myView对象，刷新整个九宫格显示的数据
    private void returnResult(int tilE) {
        myView.setSELEctedTile(tilE);
        //取消对话框显示
        dismiss();
    }

    private void setListeners(){
        //遍历整个keys数组
        for(int i=0;i<keys.length;i++) {
            final int t=i+1;
            keys[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (t==10){
                        returnResult(0);
                    }else {
                        returnResult(t);
                    }

                }
            });
        }
    }
}
