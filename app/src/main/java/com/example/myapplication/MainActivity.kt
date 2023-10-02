package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainActivityRouter(rememberNavController())
            }
        }
    }
}

@Composable
fun ConversionCategories(
    categories: Array<ConversionCategory>,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val categoryRefs = mutableListOf<ConstrainedLayoutReference>()

    ConstraintLayout(
        modifier = modifier,
    ) {
        categories.forEachIndexed { index, category ->
            val (categoryRef) = createRefs()
            categoryRefs.add(categoryRef)
            Button(
                onClick = {
                    navController.navigate("/convert/${category.name}")
                },
                modifier = Modifier.constrainAs(categoryRef) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(
                        if (index == 0) {
                            parent.top
                        } else {
                            categoryRefs[index - 1].top
                        },
                    )
                    if (index == categories.size - 1) {
                        bottom.linkTo(parent.bottom)
                    }
                },
            ) {
                Text(text = category.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        ConversionCategories(ConversionCategory.values())
    }
}
