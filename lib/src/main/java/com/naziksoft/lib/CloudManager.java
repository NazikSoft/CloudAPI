package com.naziksoft.lib;

import android.content.Context;
import android.os.Handler;

import com.naziksoft.lib.entity.CloudAPI;
import com.naziksoft.lib.dropbox.DropboxHelper;
import com.naziksoft.lib.entity.CloudHelper;
import com.naziksoft.lib.googledrive.GoogleDriveHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nazar on 01.07.17.
 */

public class CloudManager {
    private Handler handler;
    private Map<CloudAPI, CloudHelper> helpers = new HashMap<>();

    public CloudManager(Context c, CloudAPI api, String clientId, String token) {
        switch (api) {
            case Dropbox:
                DropboxHelper dropboxHelper = new DropboxHelper(token, clientId, handler);
                helpers.put(api, dropboxHelper);
                dropboxHelper.auth(c);
                break;

            case GoogleDrive:
                GoogleDriveHelper driveHelper = new GoogleDriveHelper();
                helpers.put(api, driveHelper);
                break;
        }

    }

/*
    public static void login() {
        if (api == CloudAPI.Dropbox) {
            DropboxHelper dropboxHelper = new DropboxHelper(token, clientId, );
        }
    }
*/
/*
    private CloudUser
            user = new CloudUser(
            account.getAccountId(),
            CloudAPI.Dropbox,
            account.getName().getDisplayName(),
            account.getEmail(),
            account.getProfilePhotoUrl()
    );
*/

}
