package com.conectaedu.android.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conectaedu.android.R
import com.conectaedu.android.domain.model.Area

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaCard(
    area: Area,
    onClick: () -> Unit,
    showRegisterButton: Boolean = false,
    onRegisterClick: () -> Unit = {}
) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = area.name,
                fontSize = 24.sp,
                modifier = Modifier.weight(1f)
            )

            if (showRegisterButton) {
                OutlinedButton(onClick = onRegisterClick) {
                    Text(text = stringResource(id = R.string.common_register_button))
                }
            }
        }
    }
}