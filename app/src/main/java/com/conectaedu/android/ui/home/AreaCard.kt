package com.conectaedu.android.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conectaedu.android.domain.model.Area

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaCard(area: Area, onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = area.name, fontSize = 24.sp, modifier = Modifier.padding(10.dp))
    }
}