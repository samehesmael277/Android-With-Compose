package com.sameh.androidwithcomposefromaz.whats_app

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WhatsAppScreen(
    navController: NavHostController,
    viewModel: WhatsAppViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(true) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ChatsEffect.NavtoDetails -> {
                    Log.e(
                        "WhatsAppScreen",
                        "Navigating to ChatDetails with name: ${effect.name}, id: ${effect.id}"
                    )
                    // navController.navigate("ChatDetails/${effect.name}/${effect.id}")
                }
            }
        }
    }

    val onIntent: (ChatIntent) -> Unit = { intent ->
        scope.launch {
            viewModel.channel.send(intent)
        }
    }

    val pageState = rememberPagerState(initialPage = 0) { 3 }

    val lazyListState = rememberLazyListState()
    var isScrollingDown by remember { mutableStateOf(false) }

    // Track the scroll position
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemScrollOffset }
            .collect { scrollOffset ->
                isScrollingDown = scrollOffset > 0
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        androidx.compose.animation.AnimatedVisibility(
            visible = isScrollingDown.not(),
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(
                animationSpec = tween(durationMillis = 300)
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(
                animationSpec = tween(durationMillis = 300)
            )
        ) {
            WhatsAppHeader()
        }

        WhatsAppTabs(pageState)

        HorizontalPager(
            state = pageState,
            modifier = Modifier.fillMaxSize()
        ) { currentPage ->
            when (currentPage) {
                0 -> Chats(
                    onIntent = {
                        onIntent(it)
                    },
                    state,
                    lazyListState
                )

                1 -> Calls()
                2 -> Contacts()
            }
        }
    }
}

@Composable
fun WhatsAppHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Green)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Whatsapp")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Message,
                contentDescription = ""
            )
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = ""
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WhatsAppTabs(
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Green)
            .padding(top = 16.dp),
    ) {
        HeaderTitle(
            "Chats", pagerState.currentPage == 0
        ) {
            scope.launch {
                pagerState.animateScrollToPage(0)
            }
        }
        HeaderTitle(
            "Call", pagerState.currentPage == 1
        ) {
            scope.launch {
                pagerState.animateScrollToPage(1)
            }
        }
        HeaderTitle(
            "Contacts", pagerState.currentPage == 2
        ) {
            scope.launch {
                pagerState.animateScrollToPage(2)
            }
        }
    }
}

@Composable
fun RowScope.HeaderTitle(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .weight(1F)
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Spacer(Modifier.height(8.dp))
        if (isSelected)
            Card(
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                HorizontalDivider(
                    thickness = 6.dp,
                    color = Color.White
                )
            }
    }
}

@Composable
fun Chats(
    onIntent: (ChatIntent) -> Unit,
    state: ChatState,
    scrollState: LazyListState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        state = scrollState
    ) {
        items(state.detailData) { chat ->
            ChatDetails(
                imageContact = chat.imageContact,
                name = chat.name,
                massage = chat.message,
                massageNumber = chat.messageNumber,
                numberAppearance = chat.numberAppearance,
                onClick = {
                    val chatIndex = state.detailData.indexOf(chat)
                    if (chatIndex != -1) {
                        onIntent(ChatIntent.onClickItem(chat.name, chatIndex))
                    }
                }
            )
        }
    }
}

@Composable
fun ChatDetails(
    imageContact: Int,
    name: String,
    massage: String,
    massageNumber: String,
    numberAppearance: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .size(90.dp)
                    .padding(10.dp)
                    .clip(shape = CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = CircleShape
                    ),
                painter = painterResource(id = imageContact), contentDescription = ""
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = massage,
                    maxLines = 1,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(1.dp))
            Column(
                modifier = Modifier
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "12/4/2024",
                    maxLines = 1,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                if (numberAppearance) {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(shape = RoundedCornerShape(100.dp))
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = CircleShape
                            )
                            .background(color = Color.Green),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = massageNumber,
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }
            }

        }
    }
}

@Composable
fun Calls() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 1..100) {
            Text(
                "call: $i",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun Contacts() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 1..100) {
            Text(
                "contact: $i",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}