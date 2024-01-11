package com.gwh.math.ui.widgets.game;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gwh.math.R;
import com.gwh.math.ui.activity.GameActivity;
import com.gwh.math.ui.activity.MainActivity;
import org.jetbrains.annotations.NotNull;

public class ShuduView extends View {

    private float width;
    private float height;
    int SELEctedX;
    int SELEctedY;
    private Game game=new Game();

    public ShuduView(Context context) {
        super(context);
    }

    public ShuduView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShuduView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh) {
        // TODO Auto-generated method stub
        this.width=w/9f;
        this.height=h/9f;
        super.onSizeChanged(w,h,oldw,oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        //绘制背景颜色
        Paint BACkground_paint = new Paint();
//        BACkground_paint.setColor(getResources().getColor(R.color.shudu_BACkground));
        BACkground_paint.setColor(Color.TRANSPARENT);
        canvas.drawRect(0,0,getWidth(),getHeight(),BACkground_paint);

        Paint white=new Paint();
        white.setColor(getResources().getColor(R.color.shudu_hilite));

        Paint light=new Paint();
        light.setColor(getResources().getColor(R.color.shudu_light));

        Paint dark=new Paint();
        dark.setColor(getResources().getColor(R.color.shudu_dark));


        for(int i=0;i<9;i++) {
            //画出横向的线
            canvas.drawLine(0,0,getWidth(),i*height,light);
            canvas.drawLine(0,i*height,getWidth(),i*height+1,white);
            //画出纵向的线
            canvas.drawLine( 0,i*width,0,getHeight(),light);
            canvas.drawLine( i*width,0,i*width,getHeight(),white);
        }
        for(int i=0;i<9;i++) {
            if(i%3!=0) {
                continue;
            }
            canvas.drawLine(0,i*height,getWidth(),i*height+1,dark);
            canvas.drawLine(i*width,0,i*width,getHeight(),dark);
            //不知道效果
           // canvas.drawLine( i*width+1,i*width+1,i*width,i*width,white);
        }

        Paint number_paint=new Paint();
        number_paint.setColor(Color.BLACK);
        //number_paint.setStyle(Paint.Style.stroke);
        number_paint.setTextSize(height*0.75f);
        number_paint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fm=number_paint.getFontMetrics();
        float x=width/2;
        float y=height/2-(fm.ascent+fm.descent)/2;



        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                canvas.drawText(game.getTileString(i,j),width*i+x,height*j+y,number_paint);
            }
        }

        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if(event.getAction()!=MotionEvent.ACTION_DOWN)
        {
            return super.onTouchEvent(event);
        }
        SELEctedX=(int)(event.getX()/width);
        SELEctedY=(int)(event.getY()/height);

        int used[]=game.getUsedTileByCoor(SELEctedX,SELEctedY);
        int sum=0;
        int sumnumber=0;
        sumnumber=game.sum(game.getSudoku());

        String s = game.sudoku+"";

        LogUtils.d("onTouchEvent","sumnumber:"+s);
        LogUtils.d("onTouchEvent","sumnumber:"+sumnumber);

        boolean isOk = true;

        for (int i:game.sudoku){
            if (i==0){
                isOk = false;
            }
        }




        if(isOk){
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setMessage("success!")
                    .setPositiveButton("Exit",new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.cancel();

                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        } else {
            for(int i=0;i<used.length;i++){
                sum+=used[i];
            }
            if(sum==45){
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setMessage("No number!")
                        .setNegativeButton("Exit",new DialogInterface.OnClickListener()  {

                            @Override
                            public void onClick(DialogInterface arg0,int arg1) {
                                arg0.cancel();

                            }
                        });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
            else {
                StringBuffer sb=new StringBuffer();
                for(int i=0;i<used.length;i++)
                {
                    sb.append(used[i]);
//              System.out.println(used[i]);
                }
                KeyDialog keyDialog= new KeyDialog(getContext(),used,this);
                keyDialog.show();
            }
        }

      /*StringBuffer sb=new StringBuffer();
      for(int i=0;i<used.length;i++)
      {
          sb.append(used[i]);
//          System.out.println(used[i]);
      }*/

//      //生成一个LayoutInflater对象
//      LayoutInflater layoutInflater=LayoutInflater.from(this.getContext());
//      //使用LayoutInflater对象根据一个布局文件，生成一个View
//      View layoutView=layoutInflater.inflate(R.layout.dialog,null);
//      //生成好的TextView当中，取出响应的控件
//      TextView textView=(TextView)layoutView.findViewById(R.id.usedTextId);
//      //设置Textview内容
//      textView.setText(sb.toString());
//      //生成一个对话框的Builder对象
//      AlertDialog.builder builder=new AlertDialog.builder(this.getContext());
//      //设置对话框的布局
//      builder.setView(layoutView);
//      //创建一个对话框
//      AlertDialog dialog=builder.create();
//      //显示对话框
//      dialog.show();
      /* KeyDialog keyDialog= new KeyDialog(getContext(),this);
      keyDialog.show();*/
        return true;
    }


    public void setSELEctedTile(int tilE) {
        // TODO Auto-generated method stub
        if(game.setTileIfValid(SELEctedX,SELEctedY,tilE)){
            invalidate();
        }
        boolean isOk = true;
        for (int i:game.sudoku){
            if (i==0){
                isOk = false;
            }
        }

        if (isOk){
            calBack.a("1");

            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setMessage("success!")
                    .setPositiveButton("Ensure",new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            calBack.a("2");
                            arg0.cancel();
                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }

    }

    public void play(){
        game.againGame();
        invalidate();
    }


    GameActivity.CalBack calBack;

    public void setCallBack(@NotNull GameActivity.CalBack calBack) {
        this.calBack = calBack;
    }
}
