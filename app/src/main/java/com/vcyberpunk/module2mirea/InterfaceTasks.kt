package com.vcyberpunk.module2mirea

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke

private val ModuleBlue = Color(0xFF355C9A)
private val SoftGray = Color(0xFFE6E6E6)

@Composable
fun InterfaceTasksScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { Text("Практические задания 1–7", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 16.dp)) }
        item {
            TaskCard("Задания 1–2. Приветствие и предпросмотр") {
                Hello("Семён Стасюк", Modifier.fillMaxWidth())
                Hello(null, Modifier.fillMaxWidth())
            }
        }
        item { TaskCard("Задание 3. Оформление текста") { TextSamples() } }
        item { TaskCard("Задание 4. Кнопка") { Task4Button() } }
        item { Task5InitialsCard() }
        item {
            TaskCard("Задание 6. Векторное изображение") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Task6OriginalCircle()
                    Task6TintedOval()
                }
            }
        }
        item { TaskCard("Задание 7. Контактные карточки") { ContactLayouts() } }
        item { Spacer(Modifier.height(8.dp)) }
    }
}

@Composable
private fun TaskCard(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(title, style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
            content()
        }
    }
}

@Composable
fun Hello(name: String?, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = name ?: "Имя не задано",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 4.dp),
        )
    }
}

@Preview(name = "Портретный экран", device = "spec:width=411dp,height=891dp,dpi=420", showSystemUi = true, showBackground = true)
@Composable
fun HelloPreviewPortrait() {
    Hello("Семён Стасюк", Modifier.fillMaxSize())
}

@Preview(name = "Альбомный экран", device = "spec:width=891dp,height=411dp,dpi=420", showSystemUi = true, showBackground = true)
@Composable
fun HelloPreviewLandscape() {
    Hello("Семён Стасюк", Modifier.fillMaxSize())
}

@Preview(name = "Круглый экран 200 dp", device = "spec:width=200dp,height=200dp,dpi=420,isRound=true", showSystemUi = true, showBackground = true, backgroundColor = 0xFF303030)
@Composable
fun HelloPreviewRound() {
    Hello("Семён Стасюк", Modifier.fillMaxSize().background(Color.Yellow))
}

@Preview(showBackground = true)
@Composable
fun TextSamples() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = stringResource(R.string.task_3_text),
            color = Color.Green,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = stringResource(R.string.task_3_text),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = stringResource(R.string.task_3_text),
            color = Color.Black,
            fontSize = 24.sp,
            textDecoration = TextDecoration.Underline,
            style = TextStyle(textIndent = TextIndent(firstLine = 48.sp)),
            modifier = Modifier.fillMaxWidth().background(Color.Green),
        )
    }
}

@Composable
fun Task4Button(onClick: () -> Unit = {}) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color.Gray),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black),
        onClick = onClick,
    ) {
        Text("Нажми на меня")
    }
}

@Composable
fun InitialsBadge(initials: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().height(100.dp).background(Color.Gray),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier.padding(vertical = 16.dp).fillMaxHeight().width(68.dp).clip(CircleShape).background(Color(0xFFFF9800)),
            contentAlignment = Alignment.Center,
        ) {
            Text(initials, color = Color.Black)
        }
    }
}

@Composable
private fun Task5InitialsCard() {
    TaskCard("Задание 5. Круг с инициалами") {
        InitialsBadge("СС")
    }
}

@Composable
fun Task6OriginalCircle() {
    Box(
        modifier = Modifier.size(width = 240.dp, height = 120.dp).background(Color.Black),
        contentAlignment = Alignment.TopEnd,
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = "Круг оранжевого цвета",
        )
    }
}

@Composable
fun Task6TintedOval() {
    Box(
        modifier = Modifier.size(width = 240.dp, height = 120.dp).background(Color.Blue),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.circle),
            colorFilter = ColorFilter.tint(Color(0xFF8B00FF)),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Фиолетовый растянутый круг",
        )
    }
}

private data class Contact(
    val firstName: String,
    val patronymic: String,
    val lastName: String,
    val phone: String,
    val address: String,
)

private val sampleContacts = listOf(
    Contact("Евгений", "Андреевич", "Лукашин", "+7 495 495 95 95", "Москва, 3-я улица Строителей, д. 25"),
    Contact("Василий", "Егорович", "Кузякин", "—", "Ивановская область, деревня Крутово, д. 4"),
    Contact("Людмила", "Прокофьевна", "Калугина", "+7 495 788 78 78", "Москва, Большая Никитская, д. 43"),
)

@Composable
fun ContactLayouts() {
    val first = sampleContacts.first()
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Column(Modifier.fillMaxWidth()) {
            ContactText(first)
        }
        sampleContacts.forEach { contact ->
            Column(
                modifier = Modifier.fillMaxWidth().background(SoftGray).padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                ContactText(contact, textAlign = TextAlign.Center)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().background(SoftGray).padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                ContactText(first)
            }
            Spacer(Modifier.width(12.dp))
            Image(
                painter = painterResource(R.drawable.contact_avatar),
                contentDescription = "Изображение контакта",
                modifier = Modifier.size(64.dp).clip(CircleShape).border(1.dp, Color.White, CircleShape),
            )
        }
    }
}

@Composable
private fun ContactText(contact: Contact, textAlign: TextAlign = TextAlign.Start) {
    Text("Имя: ${contact.firstName}", textAlign = textAlign)
    Text("Отчество: ${contact.patronymic}", textAlign = textAlign)
    Text("Фамилия: ${contact.lastName}", textAlign = textAlign)
    Text("Мобильный телефон: ${contact.phone}", textAlign = textAlign)
    Text("Адрес: ${contact.address}", textAlign = textAlign)
}
