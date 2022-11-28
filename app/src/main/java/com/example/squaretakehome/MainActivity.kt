package com.example.squaretakehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.squaretakehome.employee.Action
import com.example.squaretakehome.employee.EmployeeViewModel
import com.example.squaretakehome.employee.State
import com.example.squaretakehome.ui.theme.SquareTakeHomeTheme
import com.example.squaretakehome.util.Lce
import com.example.squaretakehome.util.LceSection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: EmployeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SquareTakeHomeTheme {
                val state = viewModel.state.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EmployeeListSection(
                        state = state.value,
                        onRefresh = { viewModel.performAction(Action.RefreshList) })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmployeeListSection(state: State, onRefresh: () -> Unit) {
    val pullRefreshState = rememberPullRefreshState(state.isRefreshing, { onRefresh() })

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {

        LceSection(state = state.employeeList,
            loading = { BlockingCircularProgressIndicator(isShowing = true) },
            error = {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        if (!state.isRefreshing)
                            Text(
                                modifier = Modifier.fillMaxSize(),
                                text = (state.employeeList as Lce.Error).exception.localizedMessage
                                    ?: "error please try to refresh again again"
                            )
                    }
                }
            }) { employees ->
            if (!state.isRefreshing) {

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items = employees) { employee ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            elevation = 10.dp
                        ) {
                            Column(
                                modifier = Modifier.padding(15.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                AsyncImage(
                                    model = employee.photoUrlLarge,
                                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                                    contentDescription = "employee large image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(120.dp)
                                )
                                Text("name: " + employee.fullName)
                                Text("emailAddress: " + employee.emailAddress)
                                Text("phoneNumber: " + employee.phoneNumber)
                                Text("employeeType: " + employee.employeeType.desc)
                                Text("biography: " + employee.biography)
                                Text("team: " + employee.team)
                                AsyncImage(
                                    model = employee.photoUrlSmall,
                                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                                    contentDescription = "employee large small",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.clip(CircleShape)
                                )
                            }

                        }
                    }
                }
            }
        }
        PullRefreshIndicator(
            state.isRefreshing,
            pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

    }
}

// CircularProgressIndicator that blocks user input while loading
@Composable
private fun BlockingCircularProgressIndicator(
    isShowing: Boolean,
    modifier: Modifier = Modifier
) {
    if (isShowing) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {},
                ),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}