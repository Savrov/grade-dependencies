package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.runtime.Composable
import org.koin.core.Koin

@Composable
fun ProjectsComponent(
    koin: Koin,
) {

}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProjectsComponent(
    state: ProjectsState,
    label: ProjectsLabel?,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit = {},
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipPartiallyExpanded = true,
            initialValue = SheetValue.Hidden,
        ),
    )
    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerSize, topEnd = roundedCornerSize),
        sheetContent = {
            ProjectSingleComponent(
                state = state,
            )
        },
        content = {
            ProjectListComponent(
                state = state,
                onSelect = onSelect,
            )
        }
    )
    LaunchedEffect(label) {
        val isExpanded = (label as? ProjectsLabel.UpdateBottomSheetState)?.isExpanded ?: return@LaunchedEffect
        if (isExpanded) {
            scaffoldState.bottomSheetState.expand()
        } else {
            scaffoldState.bottomSheetState.hide()
        }
    }
}
*/