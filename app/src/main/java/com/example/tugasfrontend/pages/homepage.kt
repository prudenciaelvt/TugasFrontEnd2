package com.example.tugasfrontend.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.tugasfrontend.R
import com.example.tugasfrontend.data.Berita
import com.example.tugasfrontend.data.databerita
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "PATH TO INDO",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF008DDA),
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
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    HomePager(modifier = Modifier.padding(horizontal = 8.dp))
                }
                item {
                    NewsList(beritas = databerita.beritas, modifier = Modifier.padding(horizontal = 8.dp))
                }
            }
        }
        ,
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePager(modifier: Modifier = Modifier) {
    val travelPage = listOf(
        R.drawable.nusapenida,
        R.drawable.warinding,
        R.drawable.gilirawangan,
        R.drawable.tamannasional,
        R.drawable.bunaken,
    )

    val pagerState = rememberPagerState()

    Box(modifier = modifier.fillMaxWidth().height(250.dp)) {
        HorizontalPager(
            count = travelPage.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(16.dp), clip = true)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = travelPage[page]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(vertical = 2.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun NewsTravel(berita: Berita, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = berita.imageUrl),
            contentDescription = "Image News",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(14.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = berita.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = berita.description,
                fontSize = 11.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun NewsList(beritas: List<Berita>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        beritas.forEach { berita ->
            NewsTravel(berita = berita)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
