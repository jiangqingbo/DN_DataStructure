package com.huyunit.dn.datastructure.lesson7_20181210;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.huyunit.dn.datastructure.R;

import static com.huyunit.dn.datastructure.lesson7_20181210.MapUtils.endCol;
import static com.huyunit.dn.datastructure.lesson7_20181210.MapUtils.endRow;
import static com.huyunit.dn.datastructure.lesson7_20181210.MapUtils.map;
import static com.huyunit.dn.datastructure.lesson7_20181210.MapUtils.result;
import static com.huyunit.dn.datastructure.lesson7_20181210.MapUtils.startCol;
import static com.huyunit.dn.datastructure.lesson7_20181210.MapUtils.startRow;
import static com.huyunit.dn.datastructure.lesson7_20181210.MapUtils.touchFlag;

public class Lesson7Activity extends AppCompatActivity {

    ShowMapView showMapView;

    Handler handler=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        handler=new Handler(Looper.getMainLooper());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_7);
        showMapView = (ShowMapView) findViewById(R.id.show);
    }

    /**
     * 计算
     * @param view
     */
    public void cal(View view) {
        MapInfo info = new MapInfo(map, map[0].length, map.length, new Node( startCol, startRow), new Node(endCol, endRow));
        Log.i("jett","start="+startRow+" "+startCol);
        Log.i("jett","end="+endRow+" "+endCol);
        new Astar().start(info);
        new MoveThread(showMapView).start();

    }

    /**
     * 清除，重新来
     * @param view
     */
    public void reset(View view){
        MapUtils.path=null;
        MapUtils.result.clear();
        touchFlag=0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j]==2){
                    map[i][j]=0;
                }
            }
        }
        showMapView.invalidate();

    }

    class MoveThread extends Thread{
        ShowMapView showMapView;
        public MoveThread(ShowMapView showMapView){
            this.showMapView=showMapView;
        }
        @Override
        public void run() {
            while(result.size()>0){
                Log.i("jett",result.size()+"");
                Node node= result.pop();
                map[node.coord.y][node.coord.x]=2;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        showMapView.invalidate();
                    }
                });

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map[node.coord.y][node.coord.x] = 0;

            }
            MapUtils.touchFlag=0;
        }
    }

}
