// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google() // Required to fetch the Google Services plugin
        mavenCentral() // For general dependencies
        //maven { url "https://jitpack.io" }
        maven {
            setUrl("https://jitpack.io") // JitPack repository
        }

    }
    dependencies {
        // Add the Google Services classpath here
        classpath("com.google.gms:google-services:4.4.2") // Make sure this version matches your project requirements

    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false

}

