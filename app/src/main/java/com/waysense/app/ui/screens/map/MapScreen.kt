package com.waysense.app.ui.screens.map

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.waysense.app.ui.components.WaySenseNavigationInstruction
import com.waysense.app.ui.theme.WayDimens

@Composable
fun MapScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WayDimens.ScreenPadding),
    ) {
        Spacer(modifier = Modifier.height(WayDimens.Space16))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack, modifier = Modifier.semantics { contentDescription = "Go back" }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Route overview",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.semantics { heading() },
            )
        }
        Spacer(modifier = Modifier.height(WayDimens.Space16))

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .semantics { contentDescription = "Map showing route from Park Street Metro to Esplanade Metro Station. Decorative only, see route steps below." },
        ) {
            val roadColor = Color(0xFFD0D0D0)
            val routeColor = Color(0xFF3157D5)
            val stationColor = Color(0xFF4E5F92)
            val userColor = Color(0xFFBA1A1A)

            drawLine(roadColor, Offset(50f, 180f), Offset(50f, 20f), strokeWidth = 8f)
            drawLine(roadColor, Offset(50f, 20f), Offset(350f, 20f), strokeWidth = 8f)
            drawLine(roadColor, Offset(350f, 20f), Offset(350f, 180f), strokeWidth = 8f)

            val path = Path().apply {
                moveTo(100f, 160f)
                lineTo(100f, 60f)
                lineTo(300f, 60f)
            }
            drawPath(path, routeColor, style = Stroke(width = 5f))

            drawCircle(userColor, 12f, Offset(100f, 160f))
            drawCircle(stationColor, 12f, Offset(100f, 60f))
            drawCircle(stationColor, 12f, Offset(300f, 60f))

            drawContext.canvas.nativeCanvas.apply {
                val paint = android.graphics.Paint().apply {
                    textSize = 32f
                    color = android.graphics.Color.BLACK
                    isAntiAlias = true
                }
                drawText("You", 110f, 170f, paint)
                drawText("Park St", 50f, 50f, paint)
                drawText("Esplanade", 260f, 50f, paint)
            }
        }

        Spacer(modifier = Modifier.height(WayDimens.Space24))
        Text(
            text = "Route steps",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space12))

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            val steps = listOf(
                "1. Walk 450 metres to Park Street Metro.",
                "2. Enter through accessible Gate 2.",
                "3. Take the Blue Line toward Dakshineswar.",
                "4. Exit at Esplanade.",
                "5. Use Exit 3 elevator.",
            )
            steps.forEach { step ->
                WaySenseNavigationInstruction(
                    instruction = step,
                    distanceMeters = 0,
                    landmark = null,
                    modifier = Modifier.padding(bottom = WayDimens.Space8),
                )
            }
        }
    }
}
