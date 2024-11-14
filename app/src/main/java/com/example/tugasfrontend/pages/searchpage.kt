package com.example.pathtoindo_mobileapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.tugasfrontend.data.Travel
import com.example.tugasfrontend.data.datatravel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "DISCOVER YOUR PATH",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF008DDA),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFF7EEDD)
            ),
            modifier = Modifier.height(70.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        MyListSearch(navController = navController, modifier = Modifier.padding(horizontal = 8.dp))
    }
}

@Composable
fun MyListSearch(navController: NavHostController, modifier: Modifier = Modifier) {
    val searchQuery = remember { mutableStateOf("") }
    val searchResults = remember { mutableStateOf(datatravel.travels) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { query ->
                searchQuery.value = query
                searchResults.value = if (query.isBlank()) {
                    datatravel.travels
                } else {
                    datatravel.travels.filter {
                        it.name.contains(query, ignoreCase = true)
                    }
                }
            },
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
                .height(66.dp),
            placeholder = {
                Text(
                    text = "Search...",
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                )
            },
            label = {
                Text(
                    text = "Search",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Gray
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (searchResults.value.isEmpty()) {
            Text(
                text = "Wisata tidak ditemukan",
                style = TextStyle(color = Color.Gray, fontSize = 14.sp),
                modifier = Modifier.padding(8.dp)
            )
        } else {
            TravelGrid(items = searchResults.value, navController = navController, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun TravelGrid(items: List<Travel>, navController: NavHostController, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(items) { travel ->
            TravelItem(travel = travel, navController = navController)
        }
    }
}

@Composable
fun TravelItem(travel: Travel, navController: NavHostController, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clickable { navController.navigate("detail/${travel.id}") },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            val imagePainter = rememberAsyncImagePainter(model = travel.imageUrl)
            Image(
                painter = imagePainter,
                contentDescription = "Travel Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = travel.name,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFF41C9E2), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = travel.category,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
