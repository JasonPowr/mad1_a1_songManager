package songmanager.console.models

data class SongModel(
    var id: Long = 0,
    var artist: String = "",
    var title: String = "",
    var duration: Double = 0.00,
    var releaseYear: Int = 1111,
    var wonAward: Boolean = false,
)