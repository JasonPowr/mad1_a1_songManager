package songmanager.console.models

interface SongStore {
    fun create(songModel: SongModel)
    fun listAll(): MutableList<SongModel>
    fun updateSong(updatedSong:SongModel,songToUpdate: SongModel)
    fun findSongInJSON(songName: String):SongModel?
    fun removeSong(songForRemoval: SongModel)
    fun filterByArtistsName(artistName: String): List<SongModel>
    fun sortBy() : List<SongModel>
}