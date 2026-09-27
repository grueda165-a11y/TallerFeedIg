package com.gustavorueda.feedinstagram.ui.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gustavorueda.feedinstagram.data.DataSource
import com.gustavorueda.feedinstagram.ui.components.PostCard
import com.gustavorueda.feedinstagram.ui.components.StoriesRow

@Composable
fun FeedScreen() {
    // 1 y 2. Obtenemos las publicaciones e historias usando remember
    val posts = remember { DataSource.getPosts() }
    val stories = remember { DataSource.getStories() }

    Scaffold(
        // 3. Parámetro topBar del Scaffold
        topBar = { InstagramTopBar() }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            state = rememberLazyListState()
        ) {
            /* --- Las stories van como un item único dentro del LazyColumn --- */
            // 4. Usamos item (no items) para el bloque de historias
            item(key = "stories_row") {
                StoriesRow(stories = stories)
                HorizontalDivider()
            }

            /* --- Los posts se iteran con items --- */
            items(
                items = posts,
                // 5. Clave única basada en el id del post
                key = { post -> post.id }
            ) { post ->
                PostCard(
                    post = post,
                    onLikeClick = { likedPost ->
                        // Acción al presionar el botón de Me Gusta
                    }
                )
            }
        }
    }
}

/* --- TOP BAR estilo Instagram --- */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstagramTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Instagram",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                fontFamily = FontFamily.Cursive
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Notificaciones")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.Send, contentDescription = "Mensajes")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}