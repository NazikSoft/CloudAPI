package com.naziksoft.lib.dropbox;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.naziksoft.lib.entity.CloudHelper;
import com.naziksoft.lib.entity.CloudUser;
import com.naziksoft.lib.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar on 01.07.17.
 */

public class DropboxHelper extends CloudHelper{
    private String accessToken = "token_from_dropbox_api";
    private String appKey = "client_id";
    public static final String ROOT = "";
    private Handler handler;
    private DbxClientV2 client;

//    dpNvxIWC5DAAAAAAAAAADSLhtZy2weu2HaiOIAeXOMaPwUCOjQIto0MVmkt-pGqW
//    d7t6y0cc721u2nr

    public DropboxHelper(String token, String appKey, Handler handlerForCallback) {
        accessToken = token;
        this.appKey = appKey;
        handler = handlerForCallback;
        init();
    }

    private void init() {
        // init Dropbox client
        DbxRequestConfig config = new DbxRequestConfig(appKey);
        client = new DbxClientV2(config, accessToken);
    }

    public void auth(Context context) {
        Auth.startOAuth2Authentication(context, appKey);
    }

    public void getFilesFrom(String path) throws DbxException {
        List<String> filesList = new ArrayList<>();
        ListFolderResult result = client.files().listFolder(path);
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                filesList.add(metadata.getPathLower());
            }

            //  if true, then there are more entries available
            if (!result.getHasMore()) {
                break;
            }
            result = client.files().listFolderContinue(result.getCursor());
        }
        Message message = new Message();
        message.what = Constants.HANDLER_FILES;
        message.obj = filesList;
        handler.sendMessage(message);
    }

    public void getUser() {
        new DropboxUserLoader(client, new DropboxUserLoader.DropboxCallback() {
            @Override
            public void onComplete(CloudUser user) {
                Message message = new Message();
                message.what = Constants.HANDLER_USER;
                message.obj = user;
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                handler.sendEmptyMessage(Constants.HANDLER_ERROR);
            }
        }).execute();
    }
}
