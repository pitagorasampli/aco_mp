import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import br.com.ampli.aco.theme.AppTypography
import br.com.ampli.aco.theme.darkScheme
import br.com.ampli.aco.theme.lightScheme

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