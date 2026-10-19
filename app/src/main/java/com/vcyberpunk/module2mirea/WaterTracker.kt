package com.vcyberpunk.module2mirea

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val trackerPrimary = Color(0xFF415F91)
private val trackerSecondary = Color(0xFF565F71)

@Composable
fun WaterTracker(modifier: Modifier = Modifier) {
    var waterCount by rememberSaveable { mutableIntStateOf(0) }
    var successfulDays by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Трекер воды", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = trackerPrimary)
        Spacer(Modifier.height(32.dp))
        Text("$waterCount мл", fontSize = 44.sp, fontWeight = FontWeight.Bold, color = trackerSecondary)
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { waterCount += 250 },
            colors = ButtonDefaults.buttonColors(containerColor = trackerPrimary),
        ) {
            Text("+250 мл", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                successfulDays = if (waterCount >= 1500) successfulDays + 1 else 0
                waterCount = 0
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        ) {
            Text("Завершить день")
        }
        Spacer(Modifier.height(20.dp))
        Text("Дней подряд с нормой: $successfulDays", fontSize = 18.sp, color = trackerSecondary)
    }
}

@Preview(showBackground = true)
@Composable
private fun WaterTrackerPreview() {
    WaterTracker()
}
