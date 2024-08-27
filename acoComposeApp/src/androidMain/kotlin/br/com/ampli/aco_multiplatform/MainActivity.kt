package br.com.ampli.aco_multiplatform

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import br.com.ampli.complementary_activities_multiplatform.aco.ComplementaryActivitiesActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(2000L)
            val intent = Intent(this@MainActivity, ComplementaryActivitiesActivity::class.java)
            startActivity(intent)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}