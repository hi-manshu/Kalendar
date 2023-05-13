package com.himanshoe.kalendar.util

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

/**
 * Annotation used to declare multiple previews for a Composable function or a Composable function
 * that has different variations.
 */
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, fontScale = 2F)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, fontScale = 2F)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, fontScale = 3F)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, fontScale = 3F)
annotation class MultiplePreviews
