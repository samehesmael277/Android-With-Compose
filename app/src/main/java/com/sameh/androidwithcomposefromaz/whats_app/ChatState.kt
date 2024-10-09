package com.sameh.androidwithcomposefromaz.whats_app

import com.sameh.androidwithcomposefromaz.R

data class ChatState(
    val detailData: List<ChatDetailData> =
        listOf(
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Mohamed",
                message = "Hi how are you my friend",
                messageNumber = "2",
                numberAppearance = true
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Ali",
                message = "Good morning",
                messageNumber = "1",
                numberAppearance = false
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Sara",
                message = "Let's meet tomorrow",
                messageNumber = "3",
                numberAppearance = true
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Mohamed",
                message = "Hi how are you my friend",
                messageNumber = "2",
                numberAppearance = true
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Ali",
                message = "Good morning",
                messageNumber = "1",
                numberAppearance = false
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Sara",
                message = "Let's meet tomorrow",
                messageNumber = "3",
                numberAppearance = true
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Mohamed",
                message = "Hi how are you my friend",
                messageNumber = "2",
                numberAppearance = true
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Ali",
                message = "Good morning",
                messageNumber = "1",
                numberAppearance = false
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Sara",
                message = "Let's meet tomorrow",
                messageNumber = "3",
                numberAppearance = true
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Mohamed",
                message = "Hi how are you my friend",
                messageNumber = "2",
                numberAppearance = true
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Ali",
                message = "Good morning",
                messageNumber = "1",
                numberAppearance = false
            ),
            ChatDetailData(
                imageContact = R.drawable.ic_launcher_foreground,
                name = "Sara",
                message = "Let's meet tomorrow",
                messageNumber = "3",
                numberAppearance = true
            ),
        )
)

data class ChatDetailData(
    val imageContact: Int,
    val name: String,
    val message: String,
    val messageNumber: String,
    val numberAppearance: Boolean
)
