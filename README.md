# Huli Kalendaryo Android

## Introduction
The goal of this project is to provide a simple application where the user can collect event data from multiple calendars into one calendar that can be shared with the right people.

** This is the project's Android frontend part, [here](https://github.com/greenfox-academy/huli-kalendaryo-backend) you can find the link to its backend repository.**

## Setting up the environment
To run the application's on your phone or with the help of an emulator you need to have the following things set up:

1. Ask for the google_services.json file, which you should place to the [app folder](https://github.com/green-fox-academy/huli-kalendaryo-android/tree/dev/app)

2. Ask for the `debug.keystore` file, which you need to copy into the .android folder:
* Linux/OSX: ~/.android
* Windows: Users/"yourusername"/.android

3. You should set up the following environmental variables:
* CLIENT\_ID: this is created in the Google API Console to validate the application (ask for the exact CLIENT_ID)
* BASE\_URL\_BACKEND: IP of your backend server
