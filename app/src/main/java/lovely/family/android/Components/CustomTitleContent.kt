package lovely.family.android.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lovely.family.android.Components.Theme.LabelColor
import lovely.family.android.Components.Theme.SfPro400

@Composable
fun CustomTitleContent(title: String, content: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(title, style = SfPro400.copy(fontSize = 12.sp, color = LabelColor))
        Spacer(modifier = Modifier.height(4.dp))
        Text(content, style = SfPro400)
    }
}