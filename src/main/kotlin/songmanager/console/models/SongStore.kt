package songmanager.console.models

interface SongStore {
    fun create(songModel: SongModel)
    fun listAll(): MutableList<SongModel>
}