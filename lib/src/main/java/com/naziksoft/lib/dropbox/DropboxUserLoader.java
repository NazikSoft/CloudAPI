package com.naziksoft.lib.dropbox;

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import com.naziksoft.lib.entity.CloudAPI;
import com.naziksoft.lib.entity.CloudUser;

/**
 * Created by nazar on 02.07.17.
 */

class DropboxUserLoader extends AsyncTask<Void, Void, CloudUser> {
    private Exception exception;
    private DropboxCallback callback;
    private DbxClientV2 client;
    private CloudUser user;

    interface DropboxCallback {
        void onComplete(CloudUser user);
        void onError(Exception e);
    }

    DropboxUserLoader(DbxClientV2 client,DropboxCallback callback){
        this.client = client;
        this.callback = callback;
    }

    @Override
    protected CloudUser doInBackground(Void... params) {
        try {
            FullAccount account = client.users().getCurrentAccount();
             user = new CloudUser(
                    account.getAccountId(),
                    CloudAPI.Dropbox,
                    account.getName().getDisplayName(),
                    account.getEmail(),
                    account.getProfilePhotoUrl());
        } catch (DbxException e) {
            exception = e;
        }
        return user;
    }

    @Override
    protected void onPostExecute(CloudUser user) {
        if (exception == null){
            callback.onComplete(user);
        } else {
            callback.onError(exception);
        }
    }
}
