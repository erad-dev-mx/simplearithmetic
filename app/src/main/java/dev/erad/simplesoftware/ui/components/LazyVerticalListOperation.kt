package dev.erad.simplesoftware.ui.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import dev.erad.simplesoftware.model.DrawableStringModel
import dev.erad.simplesoftware.ui.theme.Dimens
import dev.erad.simplesoftware.ui.theme.SimpleArithmeticTheme
import dev.erad.simplesoftware.ui.theme.shapes
import dev.erad.simplesoftware.viewmodel.ArithmeticViewModel

@Composable
fun OperationCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = onClick
                )
                .padding(Dimens.dp16),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.size(Dimens.dp40)
            )
            Text(
                stringResource(id = text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier.padding(start = Dimens.dp16),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun LazyVerticalListOperation(
    modifier: Modifier,
    viewModel: ArithmeticViewModel,
    onOperationSelected: (DrawableStringModel) -> Unit
) {
    val operations by viewModel.operations.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.dp16),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp8)
    ) {
        items(operations) { operation ->
            OperationCard(
                drawable = operation.drawable,
                text = operation.text,
                modifier = modifier,
                onClick = { onOperationSelected(operation) }
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LazyVerticalListOperationPreview(modifier: Modifier = Modifier) {
    SimpleArithmeticTheme {
        LazyVerticalListOperation(
            modifier = modifier,
            viewModel = ArithmeticViewModel(),
            onOperationSelected = { }
        )
    }
}