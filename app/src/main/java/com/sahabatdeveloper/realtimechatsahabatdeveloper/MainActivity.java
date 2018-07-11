package com.sahabatdeveloper.realtimechatsahabatdeveloper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView{
    private static final int DARI = 12;
    private static final int KE = 14;
    public static final String DARI_USERNAME = "sahabatdev";
    private static final String KE_USERNAME = "adi";

//    private static final int DARI = 14;
//    private static final int KE = 12;
//    public static final String DARI_USERNAME = "adi";
//    private static final String KE_USERNAME = "sahabatdev";
    RemoteMessage remoteMessage;
    RecyclerView rvChat;
    TextInputEditText etPesan;
    FloatingActionButton fabSend;
    List<ChatMessage> listChat = new ArrayList<>();
    private ChatAdapter chatAdapter;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MyFirebaseMessagingService.isHandled = true;
            remoteMessage = MyFirebaseMessagingService.remoteMessage;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listChat.add(new ChatMessage(null,KE_USERNAME,DARI_USERNAME,remoteMessage.getNotification().getBody()));
                    chatAdapter = new ChatAdapter(listChat,MainActivity.this);
                    rvChat.setAdapter(chatAdapter);
                    rvChat.scrollToPosition(listChat.size()-1);
                    chatAdapter.notifyDataSetChanged();
//                    tvNama.setText("JUDUL : "+remoteMessage.getNotification().getTitle()+"\nBODY : "+remoteMessage.getNotification().getBody());
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic(String.valueOf(DARI));
        setContentView(R.layout.activity_main);

        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent i = new Intent();
            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + getPackageName()));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(i);
        }*/

        etPesan = (TextInputEditText)findViewById(R.id.et_pesan);
        fabSend = (FloatingActionButton)findViewById(R.id.fab_send);
        
        rvChat = (RecyclerView) findViewById(R.id.rv_chat);
        rvChat.setHasFixedSize(true);
        rvChat.setLayoutManager(new LinearLayoutManager(this));

        rvChat.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v,
                                       int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if ( bottom < oldBottom) {
                    rvChat.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rvChat.scrollToPosition(listChat.size()-1);
                        }
                    }, 100);
                }
            }
        });
        
        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSendChat(etPesan.getText().toString());
            }
        });

        onGetDataChat();

        Log.d("ALAN","alan");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(MyFirebaseMessagingService.INTENT_ACTION_HANDLEGCM));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    public void onGetDataChat() {
        MyClient.getClient().getDataChat(DARI,KE).enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus()){
                        listChat = response.body().getData();
                        chatAdapter= new ChatAdapter(response.body().getData(),MainActivity.this);
                        rvChat.setAdapter(chatAdapter);
                        rvChat.smoothScrollToPosition(listChat.size()-1);
                        chatAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSendChat(String message) {
        listChat.add(new ChatMessage(null,DARI_USERNAME,null,message));
        chatAdapter = new ChatAdapter(listChat,MainActivity.this);
        rvChat.setAdapter(chatAdapter);
        rvChat.scrollToPosition(listChat.size()-1);
        chatAdapter.notifyDataSetChanged();
        etPesan.setText("");
        MyClient.getClient().sendMessage(DARI,KE,message).enqueue(new Callback<ModelSend>() {
            @Override
            public void onResponse(Call<ModelSend> call, Response<ModelSend> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus()){

                    }
                }
            }

            @Override
            public void onFailure(Call<ModelSend> call, Throwable t) {

            }
        });
    }
}
