package dev.erad.simplesoftware.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.erad.simplesoftware.R
import dev.erad.simplesoftware.ui.theme.Dimens
import dev.erad.simplesoftware.ui.theme.SimpleArithmeticTheme

@Composable
fun SubtitleComponent(
    @StringRes text: Int,
    modifier: Modifier
) {
    Text(
        stringResource(id = text),
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier.paddingFromBaseline(bottom = Dimens.dp24)
    )
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SubtitleComponentPreview(modifier: Modifier = Modifier) {
    SimpleArithmeticTheme {
        SubtitleComponent(text = R.string.subtitle_important_people, modifier = modifier)
    }
}