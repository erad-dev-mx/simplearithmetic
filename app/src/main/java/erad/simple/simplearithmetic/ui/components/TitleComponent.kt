package erad.simple.simplearithmetic.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import erad.simple.simplearithmetic.ui.theme.Dimens
import erad.simple.simplearithmetic.ui.theme.SimpleArithmeticTheme

@Composable
fun TitleComponent(modifier: Modifier = Modifier, titleResId: Int) {
    Column {
        Text(
            stringResource(titleResId),
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier.paddingFromBaseline(bottom = Dimens.dp24)
        )
    }
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TitleComponentPreview(modifier: Modifier = Modifier) {
    SimpleArithmeticTheme {
        TitleComponent(
            titleResId = erad.simple.simplearithmetic.R.string.title_greetings,
            modifier = modifier
        )
    }
}