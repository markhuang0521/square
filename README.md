## Build tools & versions used
    compileSdk 33
    minSdk 26
    targetSdk 33
    compose_version = '1.3.0'
    hilt_version = '2.42'
    kotlin_version = '1.7.10'
    coroutines_version = '1.6.4'
    httplogging_version = '4.9.2'
    implementation "com.squareup.moshi:moshi:1.14.0"
    testImplementation "io.mockk:mockk-android:1.13.2"
    testImplementation "com.google.truth:truth:1.1.3"


## Steps to run the app
simply download the zip and wait until all dependencies are installed and then run the app.

## What areas of the app did you focus on?
architecture, this is the best MVVM pattern app I could build right now with hilt, retrofit, moshi and jetpack compose.
in turn of feature the ability to show loading progress as well as refresh data was the top of my mind.

## What was the reason for your focus? What problems were you trying to solve?
once set up with a clean pattern , any addidtional features that we need to build will be extremly stright forward and fast.
when working in a large code base with so many feature/dependencies so it's quite important to decouple them into their own module  

## How long did you spend on this project?
6 hours

## Did you make any trade-offs for this project? What would you have done differently with more time?
I spend all my time to set up the hilt and making data models/ repository for the api calls so I did bare minimum in turn of UI design and testing.
with more time would definitely work on those two areas a bit more.

## What do you think is the weakest part of your project?
testing

## Did you copy any code or dependencies? Please make sure to attribute them here!
baseRepo from geekforgeeks
LCE pattern from Instacart

## Is there any other information you’d like us to know?
in all honesty, I'm quite satisfied with this project given the time constraint 
so even if I didn’t make it to onsite this was still a great exercise for me Thanks!
