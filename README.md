# Get Started with GoTrip on Android
## Add GoTrip to your app <a href="https://github.com/gomapae/GoTripSdkDemo">Demo</a>
1. In the buildscript.repositories section of your top-level build.gradle file, make sure GoMap's Maven repo is listed:
```fortran
buildscript {
    repositories {
        maven {
            url 'http://188.116.29.5:8081/repository/gomap-sdk/'
        }
        // ...
    }
}
```

2. In the dependencies section of your app-level build.gradle file, add the GoTrip SDK for Android, and optionally, the Places static library and its required dependencies:
```fortran
dependencies {
    implementation 'com.gomap.android:gotrip:1.0.0'
}
```

3. Sync your Gradle project.

4. Add permission
```fortran
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```

5. If you support GoTrip, add meta data to AndroidManifest.xml that specify the identifying information required by each provider:
```fortran
    <meta-data
        android:name="com.gomap.gotrip.key"
        android:value="[Your App Key]" />
    <meta-data
        android:name="com.gomap.gotrip.secret"
        android:value="[Your App Secret]" />
```

6. Update your ProGuard rules to keep GoTrip SDK for Android classes when code shrinking and obfuscating is enabled in release builds.
```fortran
-keep,allowoptimization class com.godrive.** { *; }
```

7. Initialize the GoTrip
```fortran
    //unique id, you can use user's id or device's id
    GoDriveSDK.getInstance().init(context, GoDriverConfig.getInstance().setUserId("user id  result -> {

    }, true);
```

8. Get driver id of Trip
If a user has signed in successfully you can get their driver id at any point with the getDriverId method.
```fortran
    // unique id for trip
    String driverId = GoDriveSDK.getInstance().getDriverId();
```

9. Start Trip
```fortran
    GoDriveSDK.getInstance().startTrip();
```

10. Upload Gps
```fortran
    GoDriveSDK.getInstance().onLocationUpdate(location);
```

11. End Trip
```fortran
    GoDriveSDK.getInstance().endTrip();
```

## Result Codes
Name| Code | Message
:-------------| :----------------: | :-----------------:
SUCCESS | 100000 | success 
DRIVER_ALREADY_EXISTS | 100001 | driver already exists 
FAILED | 500000 | failed 
URER_ID_NULL | 500001 | user id is empty 
INIT_UN | 500002 | please init GoDrive sdk 
META_DATA_NOT_FOUND | 500003 | app key or app secret was not found 
META_DATA_EMPTY | 500004 | app key or app secret was empty 
META_DATA_CHECK_FAILED | 500005 | app key or app secret check failed 
GPS_CHECK | 500006 | check gps 
GPS_CHECK_SUCCESS | 500007 | location of GPS check success 
GPS_CHECK_FAILED | 500008 | location of GPS check failed 
START_TRIP_START_SERVICE | 500009 | start trip and start service 
CREATE_DRIVER | 500010 | create driver 
CREATE_DRIVER_SUCCESS | 500011 | create driver success 
CREATE_DRIVER_FAILED | 500012 | create driver falied