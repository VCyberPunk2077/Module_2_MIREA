package com.vcyberpunk.module2mirea

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Cart(val products: List<Product>)

data class Product(val id: Int, val name: String, val price: Int)

private val nextProductPrices = listOf(100, 150, 256, 199)

@Composable
fun ShoppingCartScreen(modifier: Modifier = Modifier) {
    var cart by remember { mutableStateOf(Cart(emptyList())) }
    val totalSum = cart.products.sumOf(Product::price)
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Корзина", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        Text("Товаров на сумму: $totalSum рублей", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(cart.products, key = Product::id) { product ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                ) {
                    Row(Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(product.name)
                        Text("${product.price} рублей")
                    }
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val nextId = cart.products.size
                val product = Product(
                    id = nextId,
                    name = "Товар #${nextId + 1}",
                    price = nextProductPrices[nextId % nextProductPrices.size],
                )
                val updatedProducts = cart.products + product
                val updatedTotal = updatedProducts.sumOf(Product::price)
                cart = Cart(updatedProducts)
                if (totalSum <= 500 && updatedTotal > 500) {
                    Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
                }
            },
        ) {
            Text("Добавить товар")
        }
        if (cart.products.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { cart = Cart(cart.products.dropLast(1)) },
            ) {
                Text("Удалить товар")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingCartPreview() {
    ShoppingCartScreen()
}
