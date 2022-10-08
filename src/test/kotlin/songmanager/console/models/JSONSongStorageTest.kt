package songmanager.console.models

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class JSONSongStorageTest {
    var JSONSongs = JSONSongStorage()
    var song1 = SongModel()
    var song2 = SongModel()
    var song3 = SongModel()

    @BeforeEach
    fun setUp() {
        song1.id = 1110
        song1.artist = "winnie buck"
        song1.title = "supportal"
        song1.duration = 5.26
        song1.releaseYear = 1969
        song1.wonAward = false

        song2.id = 57516
        song2.artist = "mccoy franco"
        song2.title = "zillactic"
        song2.duration =  1.57
        song2.releaseYear = 1950
        song2.wonAward = true

        song3.id = 47625
        song3.artist = "mooney stephenson"
        song3.title = "zerbina"
        song3.duration = 7.44
        song3.releaseYear = 2017
        song3.wonAward = false

    }

    @AfterEach
    fun tearDown() {
        JSONSongs.removeSong(song1)
        JSONSongs.removeSong(song2)
        JSONSongs.removeSong(song3)
    }


    @Test
    fun create() {
        val numberBeforeCreate = JSONSongs.songs.size
        JSONSongs.create(song1)
        JSONSongs.create(song2)
        JSONSongs.create(song3)
        assert(numberBeforeCreate < JSONSongs.songs.size)
    }

    @Test
    fun removeSong() {
        val numberBeforeCreate = JSONSongs.songs.size
        JSONSongs.create(song1)
        JSONSongs.create(song2)
        JSONSongs.create(song3)
        assert(numberBeforeCreate < JSONSongs.songs.size)

        val numberBeforeRemove = JSONSongs.songs.size
        JSONSongs.removeSong(song1)
        JSONSongs.removeSong(song2)
        JSONSongs.removeSong(song3)
        assert(numberBeforeRemove > JSONSongs.songs.size)
    }

    @Test
    fun listAll() {
        val numberBeforeCreate = JSONSongs.songs.size
        JSONSongs.create(song1)
        JSONSongs.create(song2)
        JSONSongs.create(song3)
        assert(numberBeforeCreate < JSONSongs.songs.size)

        val list = JSONSongs.listAll()
        assertEquals(list.size, JSONSongs.songs.size)
    }

    @Test
    fun updateSong() {
        val numberBeforeCreate = JSONSongs.songs.size
        JSONSongs.create(song1)
        assert(numberBeforeCreate < JSONSongs.songs.size)

        val updatededSong = SongModel()
        updatededSong.id = 1110
        updatededSong.artist = "updated winnie buck"
        updatededSong.title = "updated supportal"
        updatededSong.duration = 5.26
        updatededSong.releaseYear = 1969
        updatededSong.wonAward = false

        JSONSongs.updateSong(updatededSong, song1)
        assertEquals(song1.artist,updatededSong.artist)
        assertEquals(song1.title,updatededSong.title)
        JSONSongs.removeSong(updatededSong)
    }

    @Test
    fun findSongsInJSON() {
        val numberBeforeCreate = JSONSongs.songs.size
        JSONSongs.create(song1)
        assert(numberBeforeCreate < JSONSongs.songs.size)

        val similarSong = SongModel()
        similarSong.id = 345934
        similarSong.artist = "winnie buck jr"
        similarSong.title = "supportal magic"
        similarSong.duration = 2.23
        similarSong.releaseYear = 2008
        similarSong.wonAward = true

        JSONSongs.create(similarSong)
        val list = JSONSongs.findSongsInJSON("supportal")

        assertEquals(list.size,2)
        JSONSongs.removeSong(similarSong)
    }

    @Test
    fun findSongInJSON() {
        val numberBeforeCreate = JSONSongs.songs.size
        JSONSongs.create(song1)
        assert(numberBeforeCreate < JSONSongs.songs.size)

        assertEquals(song1,JSONSongs.findSongInJSON(song1.title))
    }


    @Test
    fun filterByArtistsName() {
        val numberBeforeCreate = JSONSongs.songs.size
        JSONSongs.create(song1)
        JSONSongs.create(song2)
        JSONSongs.create(song3)
        assert(numberBeforeCreate < JSONSongs.songs.size)

        val sameArtistSong = SongModel()
        sameArtistSong.id = 345934
        sameArtistSong.artist = song1.artist
        sameArtistSong.title = "supportal magic"
        sameArtistSong.duration = 2.23
        sameArtistSong.releaseYear = 2008
        sameArtistSong.wonAward = true

        JSONSongs.create(sameArtistSong)
        val list = JSONSongs.filterByArtistsName(song1.artist)

        assertEquals(list.size,2)
        JSONSongs.removeSong(sameArtistSong)
    }

    @Test
    fun sortBy() {
        val numberBeforeCreate = JSONSongs.songs.size
        JSONSongs.create(song1)
        JSONSongs.create(song2)
        JSONSongs.create(song3)
        assert(numberBeforeCreate < JSONSongs.songs.size)

        assert(song1.releaseYear >= song2.releaseYear)
        assert(song3.releaseYear >= song2.releaseYear)

        val list = JSONSongs.sortBy()
        assert(list[0].releaseYear <= list[1].releaseYear)
        assert(list[1].releaseYear <= list[2].releaseYear)
    }
}