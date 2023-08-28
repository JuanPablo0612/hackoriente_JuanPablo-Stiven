package com.conectaedu.android.ui.course

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.conectaedu.android.R
import com.conectaedu.android.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseScreen(
    viewModel: CourseViewModel = hiltViewModel(),
    areaId: String,
    courseId: String,
    navController: NavController
) {
    val uiState = viewModel.uiState
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LaunchedEffect(key1 = true) {
        viewModel.init(areaId, courseId)
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = uiState.course.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            if (uiState.currentUser.admin) {
                ExtendedFloatingActionButton(
                    text = { Text(text = stringResource(id = R.string.course_study_group_add_button)) },
                    icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
                    onClick = { viewModel.onShowAddStudyGroupDialogChanged() }
                )
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(
                    id = R.string.course_description,
                    uiState.course.description
                ), fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = stringResource(id = R.string.course_subtitle), fontSize = 20.sp)

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = uiState.studyGroups,
                    key = { it.id },
                    contentType = { "studyGroup" }
                ) { studyGroup ->
                    StudyGroupCard(
                        studyGroup = studyGroup,
                        onClick = { navController.navigate("${Screen.StudyGroup.route}/${uiState.areaId}/${uiState.courseId}/${studyGroup.id}") }
                    )
                }
            }
        }
    }

    if (uiState.showAddStudyGroupDialog) {
        AddCourseDialog(
            onAddClick = { name, description -> viewModel.onAddStudyGroup(name, description) },
            onClose = { viewModel.onShowAddStudyGroupDialogChanged() })
    }
}