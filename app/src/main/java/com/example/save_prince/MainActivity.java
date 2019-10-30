package com.example.save_prince;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    public MediaPlayer mp;
    public SoundPool sp;
    int sound_beep_alert = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        //배경음
        mp = MediaPlayer.create(mContext, R.raw.bgm); // 배경
        mp.setLooping(true);
        mp.setVolume(0.5f,0.5f); // 볼륨 설정
        mp.start();

        //효과음
        sp = new SoundPool(20, AudioManager.STREAM_MUSIC,0); // 효과음 soundpool
        sound_beep_alert = sp.load(mContext, R.raw.bubble,1);

        Button bon = (Button)findViewById(R.id.button);
        Button bon2 = (Button)findViewById(R.id.howto);



        bon.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                sp.play(sound_beep_alert, 0.4f, 0.4f, 0, 0, 1f); // 효과음

                Intent intent = new Intent(MainActivity.this, com.Nak_ta.SAVE_PRINCE.UnityPlayerActivity.class);
                startActivity(intent);
            }
        });

        bon2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                sp.play(sound_beep_alert, 0.4f, 0.4f, 0, 0, 1f); // 효과음

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog, null, false);
                builder.setView(view);

                final Button ButtonSubmit = (Button) view.findViewById(R.id.OkButton);
                final AlertDialog dialog = builder.create();
                dialog.show();

                ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                    // 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로
                    public void onClick(View v) {
                        sp.play(sound_beep_alert, 0.4f, 0.4f, 0, 0, 1f); // 효과음
                        dialog.dismiss();    }
                });

            }
        });

    }

    protected  void onRestart(){
        super.onRestart();
        mp.start();
    }

    protected  void onStop(){
        super.onStop();
        mp.pause();
    }
}
