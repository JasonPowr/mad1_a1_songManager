package songmanager.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import songmanager.console.helpers.*
import java.util.*

val JSON_FILE = "songs.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<SongModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class JSONSongStorage : SongStore {

    var songs = mutableListOf<SongModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun create(song: SongModel) {
        song.id = generateRandomId()
        songs.add(song)
        serialize()
    }

    override fun listAll(): MutableList<SongModel> {
        return songs
    }

    override fun updateSong(updatedSong: SongModel, songToUpdate: SongModel) {
        if (updatedSong.artist.isNotEmpty()) {
            songToUpdate.artist = updatedSong.artist
        }

        if(updatedSong.title.isNotEmpty()){
            songToUpdate.title = updatedSong.title
        }

        if(updatedSong.duration != 0.0){
            songToUpdate.duration = updatedSong.duration
        }

        if(updatedSong.releaseYear != 1111){
            songToUpdate.releaseYear = updatedSong.releaseYear
        }

        if(songToUpdate.wonAward != updatedSong.wonAward){
            songToUpdate.wonAward = updatedSong.wonAward
        }

        serialize()
    }

    override fun findSongInJSON(songName: String): SongModel? {
        return songs.find { song -> song.title == songName }
    }

    override fun removeSong(songForRemoval: SongModel) {
        songs.remove(songForRemoval)
        serialize()
    }

    override fun filterByArtistsName(artistName: String): List<SongModel> {
        return songs.filter { songs -> songs.artist == artistName}
    }

    override fun sortBy(): List<SongModel> {
        return songs.sortedBy { songs -> songs.releaseYear }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(songs, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        songs = Gson().fromJson(jsonString, listType)
    }
}