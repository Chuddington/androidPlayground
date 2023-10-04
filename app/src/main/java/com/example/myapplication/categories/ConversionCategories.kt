package com.example.myapplication.categories

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ConversionCategories(
    categories: Array<ConversionCategory>,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    ConstraintLayout(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
    ) {
        val categoryRefs = mutableListOf<ConstrainedLayoutReference>()

        categories.forEachIndexed { index, category ->
            ConversionCategoryButton(navController, category, categoryRefs, index, categories)
        }
    }
}

@Composable
private fun ConstraintLayoutScope.ConversionCategoryButton(
    navController: NavHostController,
    category: ConversionCategory,
    categoryRefs: MutableList<ConstrainedLayoutReference>,
    index: Int,
    categories: Array<ConversionCategory>,
) {
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun ConversionCategoriesPreview() {
    MyApplicationTheme {
        ConversionCategories(ConversionCategory.values())
    }
}
