package com.example.mobile_hw2.screens.main


class MainRepository {
    fun getList(): List<MainItem> {
        return listOf(
            MainItem(
                id = "1",
                title = "Golden Retriever",
                subtitle = "Friendly and intelligent",
                imageUrl = "https://images.dog.ceo/breeds/retriever-golden/n02099601_100.jpg"
            ),
            MainItem(
                id = "2",
                title = "German Shepherd",
                subtitle = "Confident and smart",
                imageUrl = "https://images.dog.ceo/breeds/retriever-golden/n02099601_100.jpg"
            ),
            MainItem(
                id = "3",
                title = "Labrador",
                subtitle = "Playful and loyal",
                imageUrl = "https://images.dog.ceo/breeds/retriever-golden/n02099601_100.jpg"
            )
        )
    }
}