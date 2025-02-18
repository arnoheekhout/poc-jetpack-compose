package com.example.mydemo.mockdata

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String
)

val mockUsers = listOf(
    User(
        id = "USR123",
        name = "John Doe",
        email = "john.doe@example.com",
        phoneNumber = "+32 123 45 67 89"
    ),
    User(
        id = "USR124",
        name = "Emma Janssens",
        email = "emma.janssens@example.com",
        phoneNumber = "+32 123 45 67 90"
    ),
    User(
        id = "USR125",
        name = "Lucas Peeters",
        email = "lucas.peeters@example.com",
        phoneNumber = "+32 123 45 67 91"
    ),
    User(
        id = "USR126",
        name = "Sophie Maes",
        email = "sophie.maes@example.com",
        phoneNumber = "+32 123 45 67 92"
    )
)