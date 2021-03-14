/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenLightPreview() {
    MyTheme {
        HomeScreen()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        HomeScreen()
    }
}

enum class NavItems(val title: String, val vector: ImageVector) {
    HOME("HOME", Icons.Default.Spa),
    PROFILE("PROFILE", Icons.Default.AccountCircle)
}

@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(NavItems.HOME) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                backgroundColor = MaterialTheme.colors.onBackground
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "play",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.background
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            Surface(
                elevation = bottomNavigationElevation,
                color = MaterialTheme.colors.background
            ) {
                BottomNavigation(
                    modifier = Modifier.navigationBarsPadding(),
                    elevation = 0.dp,
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    NavItems.values().forEach { item ->
                        val selected = selectedTab == item
                        BottomNavigationItem(
                            selected = selected,
                            onClick = { selectedTab = item },
                            label = { Text(item.title, style = MaterialTheme.typography.caption) },
                            icon = { Icon(item.vector, item.title, Modifier.size(18.dp)) }
                        )
                    }
                }
            }
        },
        content = { contentPadding ->
            val fabSizeHalf = 56.dp / 2
            HomeContent(contentPadding.add(bottom = fabSizeHalf + 8.dp))
        }
    )
}

@Composable
fun HomeContent(contentPadding: PaddingValues) {
    LazyColumn(contentPadding = contentPadding) {
        item {
            var searchText by remember { mutableStateOf("") }
            Spacer(Modifier.height(56.dp))
            TextField(
                value = searchText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(screenPadding),
                onValueChange = { searchText = it },
                placeholder = {
                    Text("Search")
                },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(18.dp)
                    )
                }
            )
        }
        item {
            FavoriteCollection()
        }
        item {
            Spacer(Modifier.height(8.dp))
            RoundRow(title = "ALIGN YOUR BODY", imageList = BodyImages.values().toList())
        }
        item {
            Spacer(Modifier.height(8.dp))
            RoundRow(title = "ALIGN YOUR MIND", imageList = MindImages.values().toList())
        }
    }
}

@Composable
fun FavoriteCollection() {
    Text(
        "FAVORITE COLLECTIONS",
        Modifier
            .paddingFromBaseline(top = 40.dp)
            .padding(screenPadding),
        style = MaterialTheme.typography.h2
    )
    Spacer(Modifier.height(8.dp))
    LazyRow(contentPadding = screenPadding, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(FavoriteImages.values().toList().chunked(2)) { imageColumn ->
            Column {
                imageColumn.forEachIndexed { index, image ->
                    if (index > 0) {
                        Spacer(Modifier.height(8.dp))
                    }
                    FavoriteItem(image)
                }
            }
        }
    }
}

@Composable
fun FavoriteItem(image: FavoriteImages) {
    Surface(
        modifier = Modifier
            .width(192.dp)
            .height(56.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = image.resId), image.title)
            Text(
                text = image.title,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@Composable
fun RoundRow(title: String, imageList: List<Images>) {
    Text(
        title,
        Modifier
            .paddingFromBaseline(top = 40.dp)
            .padding(screenPadding),
        style = MaterialTheme.typography.h2
    )
    Spacer(Modifier.height(8.dp))
    LazyRow(contentPadding = screenPadding, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(imageList) { image ->
            RoundItem(image)
        }
    }
}

@Composable
fun RoundItem(image: Images) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = image.resId),
            contentDescription = image.title,
            Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = image.title,
            modifier = Modifier.paddingFromBaseline(24.dp),
            style = MaterialTheme.typography.h3
        )
    }
}

fun PaddingValues.add(start: Dp = 0.dp, top: Dp = 0.dp, end: Dp = 0.dp, bottom: Dp = 0.dp): PaddingValues {
    return object : PaddingValues {
        override fun calculateLeftPadding(layoutDirection: LayoutDirection) =
            this@add.calculateLeftPadding(layoutDirection) +
                if (layoutDirection == LayoutDirection.Ltr) start else end
        override fun calculateTopPadding(): Dp =
            this@add.calculateTopPadding() + top
        override fun calculateRightPadding(layoutDirection: LayoutDirection) =
            this@add.calculateRightPadding(layoutDirection) +
                if (layoutDirection == LayoutDirection.Ltr) end else start
        override fun calculateBottomPadding() =
            this@add.calculateBottomPadding() + bottom
    }
}
