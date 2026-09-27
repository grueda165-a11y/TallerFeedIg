package com.gustavorueda.feedinstagram.model

// Data class Story
data class Story(
    val id: Int,
    val username: String,
    val profileImageUrl: String,
    val hasSeen: Boolean = false
)