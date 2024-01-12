package com.github.savrov.gradle.sample.client.mobile.android.system

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.savrov.gradle.sample.client.mobile.shared.system.koin
import com.github.savrov.gradle.sample.client.mobile.shared.system.viewController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        koin()
        viewController()
    }
}