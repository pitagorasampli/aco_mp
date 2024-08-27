package br.com.ampli.complementary_activities_multiplatform.aco

import AppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class ComplementaryActivitiesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ComplementaryActivitiesHomeScreen()
            }
        }
    }

}