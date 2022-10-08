package songmanager.console.views

import songmanager.console.helpers.*
import songmanager.console.models.SongModel
import kotlin.time.Duration.Companion.minutes

class songView {

    fun mainMenu(): Int {
        println("$black|----------------------------------------$black|")
        println("$black|               ${purple}Main Menu$reset                $black|")
        println("$black|----------------------------------------$black|")
        println("$black|$purple 1$reset. Add a Song                          $black|")
        println("$black|$purple 2$reset. List all Songs                      $black|")
        println("$black|$purple 3$reset. Update a Song                       $black|")
        println("$black|$purple 4$reset. Delete a Song                       $black|")
        println("$black|----------------------------------------$black|")
        println("$black|$purple 5$reset. Search all Songs                    $black|")
        println("$black|$purple 6$reset. Filter by Artist                    $black|")
        println("$black|$purple 7$reset. Calculate total length of Playlist  $black|")
        println("$black|$purple 8$reset. Sort by Year                        $black|")
        println("$black|----------------------------------------$black|")
        println("$black|$purple 0$reset. Exit                                $black|")
        println("$black|----------------------------------------|")
        println()
        println("|$reset Please enter an option: ")

        val input = readLine()!!
        return if (input.toIntOrNull() != null && input.isNotEmpty())
            input.toInt()
        else
            -1
    }

    fun addSong(): SongModel {
        val newSong = SongModel()
        println()

        do {
            println("Please enter the title of the Song:")
            newSong.title = readLine()!!
        } while (!validateString(newSong.title))

        do {
            println("Please enter the name of the Artist:")
            newSong.artist = readLine()!!
        } while (!validateString(newSong.artist))

        var input = ""
        do {
            println("Please enter the duration of the Song i.e Double(x.xx):")
            input = readLine()!!
        } while (!validateDouble(input) || !isTimeValid(input))
        newSong.duration = input.toDouble()


        do {
            println("Please enter the Year the Song was Released i.e Int(xxxx):")
            input = readLine()!!
        } while (!validateInt(input))
        newSong.releaseYear = input.toInt()

        do {
            println("Did the Song ever win an award? i.e Boolean(true,false):")
            input = readLine()!!
            input = handleMisInput(input)
        } while (!validateBool(input))
        newSong.wonAward = input.toBoolean()

        listSongs(mutableListOf(newSong))
        return newSong
    }

    fun listSongs(songs: List<SongModel>) {
        for (song in songs) {
            println("Title: "+song.title+", Artist: "+song.artist+", Duration: "+song.duration.minutes+", Release Year: "+song.releaseYear+", Won award?: " + song.wonAward)
            println("$black---------------------------------------------------------------------------------------------------------------$reset")
        }
        println()
        println("Press Enter to Continue....")
        readln()
    }

    fun findSong(useCase: String): String {
        var songName: String
        do {
            println(useCase)
            songName = readLine()!!
        } while (!validateString(songName))

        return songName
    }

    fun updateSong(): Int {
        println()
        println("$black|--------------------------------------$black|")
        println("$black| ${purple}What Value would you like to update?$black|")
        println("$black|--------------------------------------$black|")
        println("$black|$purple 1$reset. Title                             $black|")
        println("$black|$purple 2$reset. Artist                            $black|")
        println("$black|$purple 3$reset. Duration                          $black|")
        println("$black|$purple 4$reset. Release Year                      $black|")
        println("$black|$purple 5$reset. Award Won                         $black|")
        println("$black|--------------------------------------$black|")
        println("$black|$purple 0$reset. Return to Main Menu               $black|")
        println("$black|--------------------------------------$black|")
        println()
        println("|$reset Please enter an option: ")

        val input = readLine()!!
        return if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -1
    }

    fun deleteSong() {
        println("Song Successfully Deleted....")

        println()
        println("Press Enter to Continue....")
        readln()
    }

    fun filterByArtistsName(songs: List<SongModel>): String {
        println("Current Artists in the System...")
        println()
        for (song in songs) {
            println(song.artist)
        }

        println()
        println("Please enter the name of the artist you would like to filter by")
        return readLine()!!
    }

}

/**         References
//https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/pad-start.html
//https://discuss.kotlinlang.org/t/printing-in-colors/22492
//https://json-generator.com/
 **/