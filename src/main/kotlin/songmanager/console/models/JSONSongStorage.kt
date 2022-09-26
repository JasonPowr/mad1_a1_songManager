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

class JSONSongStorage : SongStore{

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

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(songs, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        songs = Gson().fromJson(jsonString, listType)
    }
}