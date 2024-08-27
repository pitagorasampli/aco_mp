import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import br.com.ampli.complementary_activities_multiplatform.theme.AppTypography
import br.com.ampli.complementary_activities_multiplatform.theme.darkScheme
import br.com.ampli.complementary_activities_multiplatform.theme.lightScheme

@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkScheme
    } else {
        lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}