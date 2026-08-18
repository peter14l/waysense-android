package com.waysense.app.accessibility

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics

fun Modifier.accessibleContentDescription(description: String): Modifier {
    return this.semantics {
        contentDescription = description
    }
}

fun Modifier.accessibleAnnouncement(description: String): Modifier {
    return this.semantics {
        liveRegion = LiveRegionMode.Assertive
        contentDescription = description
    }
}
