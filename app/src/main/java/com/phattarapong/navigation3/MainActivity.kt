package com.phattarapong.navigation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.phattarapong.navigation3.navigation.Destination
import com.phattarapong.navigation3.ui.EditProfileScreen
import com.phattarapong.navigation3.ui.ProductDetailScreen
import com.phattarapong.navigation3.ui.ProductListScreen
import com.phattarapong.navigation3.ui.theme.Navigation3Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val backStack = rememberNavBackStack(Destination.ProductList)
            Navigation3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = backStack,
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                        entryDecorators = listOf(
                            // Add the default decorators for managing scenes and saving state
                            rememberSceneSetupNavEntryDecorator(),
                            rememberSavedStateNavEntryDecorator(),
                            // Then add the view model store decorator
                            rememberViewModelStoreNavEntryDecorator()
                        ),
                        entryProvider = entryProvider {
                            entry<Destination.ProductList> {
                                ProductListScreen(
                                    navigateProductDetail = { id ->
                                        backStack.add(Destination.ProductDetail(id))
                                    }
                                )
                            }

                            entry<Destination.ProductDetail> { key ->
                                ProductDetailScreen(
                                    id = key.id,
                                    navigateEditProduct = { id ->
                                        backStack.add(Destination.EditProduct(id))
                                    }
                                )
                            }

                            entry<Destination.EditProduct> { key ->
                                EditProfileScreen(
                                    id = key.id
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
