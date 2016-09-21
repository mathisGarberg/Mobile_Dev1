# GeoLocator

Description

The MainActivity.java is the first file to be initiated. This file displays a total of three views; 2 x TextViews and a Button. When the user presses the button, a OnClick method is triggered in the MainActivity.java file. This method uses an Intent to communicate with the MapsActivity file. The MapsActivity-file responds by launching its activity.

The MapsActivity consists of a fragment and a button, where the Google Maps API v2 is used to embed a map into the activity’s fragment. In the MapsActivity.java file, I use GoogleAPIClient to add the following API’s:

* LocationServices.API

* Places.GEO_DATA_API

* Places.PLACE_DETECTION_API

The LocationServices.API is used to get the latitude, longitude and altitude of the device current position, while the Places.GEO_DATA_API is called with this information as input to get the place name in text (Geocoder function).

The “location summary”-button allows the user to see a summary of their current location. When the user clicks the button, an intent sends them to the SummaryActivity. The summary displays the following information; latitude, longitude, altitude, temperature and the city, which the user is located in. Latitude, longitude and altitude is all fetched through the Google Maps API. To get the temperature, I intended to use the OpenWeatherMap API. The weather-information is passed through a JSON object file. Behind the scenes, SQLite is used to store location data in a table. The purpose of this table is to store the last known location of the user.

Note: all the tutorials I’ve used in the project is linked below.

Individual assessment

I’m currently in my 3rd year of Web Development. I have no previous knowledge of Object Oriented Programming (OOP), besides some minor Object Oriented tasks in PHP and JavaScript. This made the task of creating an Android application with Java very challenging. I spent the first week, figuring out how the language was structured and how the Android Studio UI worked – watching a bunch of tutorials. The task was overwhelming, and I had to set aside all of my other classes to get it done in time. At the end, I had managed to create the three activities, get the current position in a map, get the location data and the place name in text, and built the structure of the SQLite database. I did not success in getting the temperature from the OpenWeatherMap. At the final day, I got problem with the Gradle toolkit after an upgrade of the tool. This problem prevented me to run the code and I could therefore not do any final adjustments of the code. However, I have learned a lot about developing for the Android Platform.

Storing data

In my application, I use SQLite to store the location summary. I did initially intend to store the data when the onDestory()-method was called. After a bit of research, I found out that was not a good idea. The system can in extreme cases kill off your application, without calling the onDestroy() method. The solution is to store the data onStop() instead. The app is then no longer visible, and should release all resources that aren’t needed by the user.

When the Geolocator application is started and Find my location button is pressed, the getLocatorData() function in the MyDBHandler class is called from the onCreate() function in the MapsActivity class. The getLocatorData() function fetch the last locator data from the SQLite database. If no data is found in the database, default data is returned from the function.

Native- and web Apps

When you think of applications, you’re probably thinking of native apps. These applications are installed onto your device through a distribution mechanism like Apples App Store or Android Market. Since this distribution platform have to approve the app before distributing them to the end-users, these applications are often more secure and safer to use. The process of getting the app approved by the distribution platform can prove to be tedious and time-consuming task for the developers. But it ensures a certain quality on the app market.

The app I developed in Android Studio is a native application. The app is language-specific for the Android OS, being programmed in Java. This makes the application run faster, exploiting the processor and other core functionality of the device. Since the app are developed for a specific device, it takes full advantage of some of the built in features of the device – like the location sensor. The problem with native apps is that you need to code a different version of the app for the different operating systems, which makes them distinct. As a result, native apps will not run properly on all kind of devices. If you are building an application for Android using Java. You will have to re-program the entire application, to make it accessible for the iOS-users.

The main benefit of using a native app is that it doesn’t require an internet connection to run its features. Some applications may want to pull some content from the internet. The application I made uses web resources to fetch information. This information is crucial for the application, to run properly. There are still applications that’s not internet-dependent.

You can think of web apps like any other website on the internet. Cause that’s what it really is – a website. The difference is that they are designed for the smaller handheld devices with a touch interface. You access the web apps through a browser like Google Chrome or Safari, and use a URL to enter the requested site. The code works cross-platform. This will make it easier for the users to share the app with other people, who might be interested. The code is interpreted different by every browser, but this isn’t too much of an issue nowadays. The main issue is that you need internet connectivity to run the application. The operation speed is also dependent on the Wi-Fi connection in your current location. You can’t access the built in technology of the device either, although some features like tap-to-call and Google maps are accessible through the browser.

The main advantage with mobile web apps is the cost-effective development of the application. Especially considering you don’t need to program a different version of the application for the various platforms. So if you want a cost-efficient application – a web app is probably your safest bet.

Further extensions

The app could be extended in a couple of different ways, depending on what you want to achieve. The application uses your current location, and provide a summary of it. You could perhaps access the built-in camera functionality to take pictures of your current location and use it as background in the summary view. The picture must also be stored in the SQLite database.

In the MapsActivity there could be a button that trigged a filter function. When activated, the user could use the filter to choose which information to show in the summary view. The following filter choices could for example be available:

* General information about the place (ex. from Wikipedia)

* The 10 last local news

* Landmarks and sights

* Shops

* Restaurants

* Public transport

You could also integrate a social network between family and friends on your contact list. Perhaps you can upload the picture with the location summary to Instagram. This would allow your friends to comment on your picture and rate it. To make the users frequently use the application, the app could have some sort of rewards. “Uploaded 10 location pictures to Instagram”.

You could also take the principles of geocaching to create some sort of treasure hunt. You get a list of all the historical landmarks in your location. You have to be in that exact location. Then you get a series of quiz questions related to the monument. You’ll get a bronze, silver or gold trophy dependent on how many of the question you got right. You have to be in the exact location of the landmark to be able to answer the questions. This will be inside of a 10-meter radius. There is a leaderboard for every location. Maybe you’re out if you answer one of the questions wrong instead. The language of the application is adjusted after you’re settings on the device. You can also implement some sort system that lets friends challenge each other on the same quiz. That will work in much of the same way Kahoot does. Whoever is the last man standing after each person answer the same questions are the winner. This will also promote the cultural aspect, and bring more attendance to tourist attractions around in Norway.

Links used in the project: * Android Activity Lifecycle http://www.javatpoint.com/android-life-cycle-of-activity * Google map API https://developers.google.com/maps/documentation/android-api/start and https://developers.google.com/maps/documentation/android-api/location * Accessing Google APIs https://developers.google.com/android/guides/api-client#Starting https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderApi

* Making you App Location-Aware https://developer.android.com/training/location/index.html https://developer.android.com/training/location/retrieve-current.html#setup

* The Beginner’s Guide to Location in Android http://blog.teamtreehouse.com/beginners-guide-location-android

* Android – Google Maps Tutorial http://www.tutorialspoint.com/android/android_google_maps.htm

* Android Intents – Tutorial http://www.vogella.com/tutorials/AndroidIntent/article.html#data-transfer-between-activities http://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-on-android

* Places API for Android https://developers.google.com/places/android-api/placepicker http://www.truiton.com/2015/04/using-new-google-places-api-android/ and http://www.programcreek.com/java-api-examples/index.php?api=com.google.android.gms.maps.model.LatLngBounds

* Geocoder https://developer.android.com/reference/android/location/Geocoder.html

* Displaying a Location Address – Geocoder https://developer.android.com/training/location/display-address.html and http://stackoverflow.com/questions/2296377/how-to-get-city-name-from-latitude-and-longitude-coordinates-in-google-maps

* Create a Weather App on Android https://code.tutsplus.com/tutorials/create-a-weather-app-on-android--cms-21587 and http://androstock.com/tutorials/create-a-weather-app-on-android-android-studio.html

* Android – SQLite Database Tutorial http://www.tutorialspoint.com/android/android_sqlite_database.htm

* Saving Data in SQL Databases https://developer.android.com/training/basics/data-storage/databases.html and http://www.tutorialspoint.com/android/android_sqlite_database.htm

* SQL Database tutorial – YouTube https://www.youtube.com/watch?v=GAyvtK4cWLA#t=96.079069

* Android App Development for Beginners Playlist https://www.youtube.com/watch?v=QAbQgLGKd3Y&list=PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl