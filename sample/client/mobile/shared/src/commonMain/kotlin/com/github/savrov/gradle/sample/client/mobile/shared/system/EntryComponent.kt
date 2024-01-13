package com.github.savrov.gradle.sample.client.mobile.shared.system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.github.savrov.gradle.sample.client.mobile.shared.ext.roundedCornerSize
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryIntent
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryLabel
import com.github.savrov.gradle.sample.client.mobile.shared.model.EntryState
import com.github.savrov.gradle.sample.client.mobile.shared.presentation.EntryStore
import kotlinx.coroutines.launch
import org.koin.core.Koin

@Composable
@Deprecated("remove this component")
fun EntryComponent(
    koin: Koin,
) {
    val coroutineScope = rememberCoroutineScope()
    val store = koin.get<EntryStore>()
    val state by store.states.collectAsState(initial = EntryState())
    val label by store.labels.collectAsState(initial = null)
    EntryComponent(
        state = state,
        label = label,
        onSignIn = { username, password ->
            coroutineScope.launch { store.accept(EntryIntent.SignInWithBasicAuth(username, password)) }
        },
        onSignInWithGithub = {
            coroutineScope.launch { store.accept(EntryIntent.SignInWithGithub) }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EntryComponent(
    state: EntryState,
    label: EntryLabel?,
    modifier: Modifier = Modifier,
    onSignIn: (String, String) -> Unit = { _, _ -> },
    onSignInWithGithub: () -> Unit = {},
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipPartiallyExpanded = true,
            initialValue = SheetValue.Expanded,
        ),
    )
    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerSize, topEnd = roundedCornerSize),
        sheetSwipeEnabled = false,
        sheetDragHandle = {},
        sheetContent = {
            SignInComponent(
                state = state,
                onSignIn = onSignIn,
                onSignInWithGithub = onSignInWithGithub,
            )
        },
        content = {
            BackgroundComponent()
        }
    )
    LaunchedEffect(label) {
        val isExpanded = (label as? EntryLabel.UpdateBottomSheetState)?.isExpanded ?: return@LaunchedEffect
        if (isExpanded) {
            scaffoldState.bottomSheetState.expand()
        } else {
            scaffoldState.bottomSheetState.hide()
        }
    }
}

@Composable
private fun SignInComponent(
    state: EntryState,
    onSignIn: (String, String) -> Unit,
    onSignInWithGithub: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "Sign in",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Divider(modifier = Modifier.padding(bottom = 8.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = username,
            onValueChange = { username = it },
            label = { Text("Enter your username") },
            enabled = state.isBasicAuthEnabled,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter your password") },
            enabled = state.isBasicAuthEnabled,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = state.isBasicAuthEnabled,
            onClick = { onSignIn(username, password) }
        ) {
            Text("Sign in")
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Divider(modifier = Modifier.weight(1f))
            Text(
                text = "OR",
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
            )
            Divider(modifier = Modifier.weight(1f))
        }
        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onSignInWithGithub() }
        ) {
            Text("Sign in with GitHub")
        }
    }
}

@Composable
private fun BackgroundComponent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    )
}