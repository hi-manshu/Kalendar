import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.kalendarkit.component.day.config.KalendarDayConfig
import com.himanshoe.kalendarkit.component.day.config.KalendarDayDefaults
import com.himanshoe.kalendarkit.config.KalendarConfigDefaults
import com.himanshoe.kalendarkit.config.KalendarConfigs
import com.himanshoe.kalendarkit.model.KalendarDay
import com.himanshoe.kalendarkit.model.KalendarEvent
import com.himanshoe.kalendarkit.model.KalendarType
import com.himanshoe.kalendarkit.ui.oceanic.KalendarOceanic

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    kalendarType: KalendarType = KalendarType.Oceanic,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
    kalendarConfigs: KalendarConfigs = KalendarConfigDefaults.kalendarConfigDefaults()

) {
    when (kalendarType) {
        KalendarType.Oceanic -> KalendarOceanic(
            modifier = modifier.wrapContentHeight(),
            kalendarEvents = kalendarEvents,
            onCurrentDayClick = onCurrentDayClick,
            kalendarDayConfig = kalendarDayConfig,
            kalendarConfigs = kalendarConfigs
        )
        KalendarType.Firey -> {

        }
    }
}

@Composable
@Preview
private fun KalendarPreview() {
    Kalendar()
}
