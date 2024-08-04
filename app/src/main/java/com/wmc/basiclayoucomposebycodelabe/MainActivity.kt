package com.wmc.basiclayoucomposebycodelabe

import android.graphics.drawable.Icon
import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wmc.basiclayoucomposebycodelabe.ui.theme.BasicLayouComposeByCodeLabeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicLayouComposeByCodeLabeTheme {
                Scaffold (bottomBar = { BottomNavigation() }){ paddingValues ->
                    HomeScreen(Modifier.padding(paddingValues))

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    BasicLayouComposeByCodeLabeTheme {
        SearchBar(modifier = Modifier.padding(8.dp))
    }

}

@Composable
fun AlignYourBodyElement(modifier: Modifier = Modifier,
                         @DrawableRes drawable: Int,
                         @StringRes text: Int) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape),
        )
        //Image(R.drawable.ab1_inversions, contentDescription = null)
        Text(text = stringResource(text) ,
            modifier=Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium,

        )

    }
}

@Composable
fun AlignYourBodySection(modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
        items(alignYourBodyData){ item->
            AlignYourBodyElement(drawable =item.drawable , text = item.text)
        }

    }

}


@Composable
fun FavoriteCollectionsCard(modifier: Modifier, drawable: Int, text: Int) {
    Surface(modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.width(255.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(painter = painterResource(id = drawable), contentDescription =null,
                modifier =Modifier.size(80.dp),
                contentScale = ContentScale.Crop)
            Text(text = stringResource(id = text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
                )
        }

    }
}

@Composable
fun FavoriteCollectionSection(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(rows = GridCells.Fixed(2),
        modifier = modifier.height(168.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
        items(favoriteCollectionsData){item->
            FavoriteCollectionsCard(modifier = modifier, drawable = item.drawable, text =item.text )
        }


    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
                      Icon(Icons.Default.Search, contentDescription =null )
                      },
        modifier= modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        placeholder = {
            Text(text = "Search")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor =  MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        )

    )
}

@Composable
fun HomeSection(@StringRes text: Int,content:@Composable ()->Unit,modifier: Modifier = Modifier) {
    Column (modifier){
        Text(text = stringResource(id = text) , modifier = modifier
            .paddingFromBaseline(bottom = 16.dp, top = 40.dp)
            .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium)
        content()
    }

}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column (modifier =modifier.verticalScroll(rememberScrollState())){
        Spacer(modifier = Modifier.padding(16.dp))
        SearchBar(modifier = Modifier.padding(8.dp))
        HomeSection(R.string.AlignYourBody, {
            AlignYourBodySection(
                modifier = Modifier.padding()
            )
        })
        HomeSection(R.string.FavoriteCollections, {
            FavoriteCollectionSection(
            )
        })
        Spacer(modifier = Modifier.padding(16.dp))
    }

}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    BottomAppBar(modifier, containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
           androidx.compose.material3.Icon(imageVector = Icons.Default.Home, contentDescription = null)
        },
            label = {
                Text(text = "Home")
            }

        )
         NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
           androidx.compose.material3.Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
        },
            label = {
                Text(text = "Account")
            }

        )


    }

}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    BasicLayouComposeByCodeLabeTheme {
        AlignYourBodyElement(
            modifier = Modifier.padding(8.dp),
            drawable = R.drawable.ab1_inversions,
            text = R.string.ab1_inversions
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsCard() {
    BasicLayouComposeByCodeLabeTheme {
        FavoriteCollectionsCard(
            modifier = Modifier.padding(8.dp),
            drawable = R.drawable.ab1_inversions,
            text = R.string.ab1_inversions
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodySectionPreview() {
    BasicLayouComposeByCodeLabeTheme {
        AlignYourBodySection(
            modifier = Modifier.padding(),
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsCardPreview() {
    BasicLayouComposeByCodeLabeTheme {
        FavoriteCollectionSection()
    }

}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    BasicLayouComposeByCodeLabeTheme {
        HomeScreen()
    }

}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavPreview() {
    BasicLayouComposeByCodeLabeTheme {
        BottomNavigation()
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    BasicLayouComposeByCodeLabeTheme {
        HomeScreen()
    }

}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FullScreenPreview() {

        FullScreen()

}

@Composable
fun FullScreen(){
    BasicLayouComposeByCodeLabeTheme {
        Scaffold (bottomBar = { BottomNavigation() }){ paddingValues ->
            HomeScreen(Modifier.padding(paddingValues))

        }
    }

}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.fc1_short_mantras to R.string.ab2_quick_yoga,
    R.drawable.ab1_inversions to R.string.ab3_stretching,
    R.drawable.fc1_short_mantras to R.string.ab4_tabata,
    R.drawable.ab1_inversions to R.string.ab5_hiit,
    R.drawable.fc1_short_mantras to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.ab1_inversions to R.string.fc2_nature_meditations,
    R.drawable.fc1_short_mantras to R.string.fc3_stress_and_anxiety,
    R.drawable.ab1_inversions to R.string.fc4_self_massage,
    R.drawable.fc1_short_mantras to R.string.fc5_overwhelmed,
    R.drawable.ab1_inversions to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

