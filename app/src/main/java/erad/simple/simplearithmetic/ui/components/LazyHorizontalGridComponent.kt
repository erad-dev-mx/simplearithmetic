package erad.simple.simplearithmetic.ui.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import erad.simple.simplearithmetic.ui.theme.Dimens
import erad.simple.simplearithmetic.ui.theme.SimpleArithmeticTheme
import erad.simple.simplearithmetic.viewmodel.ArithmeticViewModel

@Composable
fun ImportantPeopleCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .width(Dimens.dp250)
            .height(Dimens.dp56)
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current,
                onClick = onClick
            ),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxHeight()
                    .width(Dimens.dp80)
            )
            Text(
                stringResource(id = text),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier
                    .padding(horizontal = Dimens.dp16)
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ImportantPeopleHorizontalGrid(
    modifier: Modifier = Modifier,
    viewModel: ArithmeticViewModel
) {
    val importantPeople by viewModel.importantPeople.collectAsState()
    val selectedPerson by viewModel.selectedPerson.collectAsState()

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(end = Dimens.dp16),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp8),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp8),
        modifier = modifier.height(Dimens.dp180)
    ) {
        items(importantPeople) { item ->
            ImportantPeopleCard(
                drawable = item.drawable,
                text = item.text,
                onClick = { viewModel.selectPerson(item) }
            )
        }
    }

    selectedPerson?.let { person ->
        val descriptionResourceId = viewModel.getDescriptionResourceId(person.text)

        ImportantPeopleBottomSheet(
            drawable = person.drawable,
            title = person.text,
            description = descriptionResourceId,
            onDismiss = { viewModel.clearSelection() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportantPeopleBottomSheet(
    @DrawableRes drawable: Int,
    @StringRes title: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.dp24, vertical = Dimens.dp24),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(Dimens.dp120)
                    .padding(bottom = Dimens.dp16)
            )

            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = Dimens.dp16)
            )

            Text(
                text = stringResource(description),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = Dimens.dp24)
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LazyHorizontalGridPreview(modifier: Modifier = Modifier) {
    SimpleArithmeticTheme {
        ImportantPeopleHorizontalGrid(
            modifier = modifier,
            viewModel = ArithmeticViewModel()
        )
    }
}