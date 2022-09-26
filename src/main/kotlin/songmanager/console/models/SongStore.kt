package songmanager.console.models

interface SongStore {
    fun create(songModel: SongModel)
}