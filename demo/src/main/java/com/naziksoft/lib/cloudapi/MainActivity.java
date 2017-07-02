package com.naziksoft.lib.cloudapi;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.naziksoft.lib.dropbox.DropboxHelper;
import com.naziksoft.lib.entity.CloudUser;
import com.naziksoft.lib.utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //    dpNvxIWC5DAAAAAAAAAADSLhtZy2weu2HaiOIAeXOMaPwUCOjQIto0MVmkt-pGqW
//    d7t6y0cc721u2nr

        DropboxHelper helper = new DropboxHelper(
                "dpNvxIWC5DAAAAAAAAAADSLhtZy2weu2HaiOIAeXOMaPwUCOjQIto0MVmkt-pGqW",
                "d7t6y0cc721u2nr",
                new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what){
                            case Constants.HANDLER_USER:
                                CloudUser user = (CloudUser) msg.obj;
                                ((TextView) findViewById(R.id.text)).setText(user.getUserEmail());
                                break;
                        }
                    }
                }
        );

        helper.auth(this);
        helper.getUser();
    }
}
