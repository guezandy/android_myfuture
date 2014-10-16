package com.myfuture;


import com.myfuture.model.GeneralInfo;
import com.myfuture.model.Message;
import com.myfuture.model.Room;
import com.myfuture.model.Slot;
import com.myfuture.model.Speaker;
import com.myfuture.model.Talk;
import com.myfuture.model.VideoPostModel;


//import com.myfuture.R;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.ParseAnalytics;

import android.content.Context;
import android.content.SharedPreferences;

import android.app.Application;
import android.util.Log;

public class MyFutureApplication extends Application {
    private final static String TAG = MyFutureApplication.class.getSimpleName();
    public static final boolean APPDEBUG = true;

    // Debugging tag for the application
    public static final String APPTAG = "Video Map";
    // Key for saving the search distance preference
    private static final String KEY_SEARCH_DISTANCE = "searchDistance";

    private static SharedPreferences preferences;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");

        /*
         * In this tutorial, we'll subclass ParseObject for convenience to
         * create and modify Meal objects
         */
        ParseObject.registerSubclass(Talk.class);
        ParseObject.registerSubclass(Slot.class);
        ParseObject.registerSubclass(Speaker.class);
        ParseObject.registerSubclass(Message.class);
        ParseObject.registerSubclass(Room.class);
        ParseObject.registerSubclass(GeneralInfo.class);
        ParseObject.registerSubclass(VideoPostModel.class);
        preferences = getSharedPreferences("com.myfuture", Context.MODE_PRIVATE);

        /*
         * Fill in this section with your Parse credentials
         */
        Parse.initialize(this, "o0cDKxRFbug9W7zxFDnxt51RFpL4ZjnhRTfxrXe5", "aawUbxHRMhkGJZRpEbm9tdjXkv4du5DLYzrFzQuk");
        //ParseFacebookUtils.initialize(getString(R.string.facebook_id));

        /*
         * This app lets an anonymous user create and save photos of meals
         * they've eaten. An anonymous user is a user that can be created
         * without a username and password but still has all of the same
         * capabilities as any other ParseUser.
         *
         * After logging out, an anonymous user is abandoned, and its data is no
         * longer accessible. In your own app, you can convert anonymous users
         * to regular users so that data persists.
         *
         * Learn more about the ParseUser class:
         * https://www.parse.com/docs/android_guide#users
         */
        ParseUser.enableAutomaticUser();

        /*
         * For more information on app security and Parse ACL:
         * https://www.parse.com/docs/android_guide#security-recommendations
         */
        ParseACL defaultACL = new ParseACL();

        /*
         * If you would like all objects to be private by default, remove this
         * line
         */
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        PushService.setDefaultPushCallback(this, MainActivity.class);


    }

    public static float getSearchDistance() {
        return 250; //TODO: fix this
    }

    public static void setSearchDistance(float value) {
        preferences.edit().putFloat(KEY_SEARCH_DISTANCE, value).commit();
    }

}