package com.nvr.android.channel.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nvr.android.channel.ChannelInfo;
import com.nvr.android.channel.WalleChannelReader;
import com.meituan.android.walle.sample.R;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.read_channel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                readChannel();
            }
        });

    }

    private void readChannel() {
        final TextView tv = (TextView) findViewById(R.id.tv_channel);
        final long        startTime   = System.currentTimeMillis();
        final ChannelInfo channelInfo = WalleChannelReader.getChannelInfo(this.getApplicationContext());
        if (channelInfo != null) {
            tv.setText(channelInfo.getChannel() + "\n" + channelInfo.getExtraInfo());
        }
        Toast.makeText(this, "ChannelReader takes " + (System.currentTimeMillis() - startTime) + " milliseconds", Toast.LENGTH_SHORT).show();
    }
}
