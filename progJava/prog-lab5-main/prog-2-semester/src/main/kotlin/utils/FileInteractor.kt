package utils

import commands.Command
import data.Album
import data.Coordinates
import data.MusicBand
import data.MusicGenre
import exceptions.CommandFileException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Executes commands from the file
 *
 * @param interactor delegates to this most of the methods
 * @param storage used to work with collection
 * @param lines have all lines from the command file
 * @throws [CommandFileException] if exception occurred
 */
class FileInteractor(
    private val interactor: Interactor,
    private val storage: Storage<LinkedHashMap<Int, MusicBand>, Int, MusicBand>,
    private val lines: List<String>
) : KoinComponent, Interactor by interactor, Validator {
    var index = 0
    var lastArgument: String? = null
    val commandManager: CommandManager by inject()

    /**
     * Starts executing commands from file
     */
    override fun start() {
        while (hasNext()) {
            interact(next())
        }
    }

    override fun exit() {
        interactor.exit()
        index = lines.count()
    }

    private fun interact(stringCommand: String) {
        val input = stringCommand.split(" ")
        if (input.count() > 2) {
            throw CommandFileException("Слишком много аргументов в строке")
        }

        try {
            val command = commandManager.getCommand(input[0])
            lastArgument = if (input.count() == 2) input[1] else null
            executeCommand(command)
        } catch (e: CommandFileException) {
            throw e
        } catch (e: Throwable) {
            throw CommandFileException(e.message)
        }
    }

    override fun getArgs(command: Command): ArrayList<Any> {
        val args = arrayListOf<Any>()
        command.getArgumentTypes().forEach {
            args.add(
                when (it) {
                    ArgumentType.INT -> getInt()
                    ArgumentType.STRING -> getString()
                    ArgumentType.GENRE -> getGenre()
                    ArgumentType.MUSIC_BAND -> getMusicBand()
                }
            )
        }
        return args
    }

    override fun executeCommand(command: Command) {
        val args = getArgs(command)
        when (val result = command.execute(args)) {
            is CommandResult.Failure -> throw result.throwable
            is CommandResult.Success -> {}
        }
    }

    override fun getString(): String = lastArgument ?: throw CommandFileException("Нет аргумента")

    override fun getInt(): Int = lastArgument?.toIntOrNull() ?: throw CommandFileException("Не Int")

    override fun getGenre(): MusicGenre =
        lastArgument?.let { MusicGenre.valueOfOrNull(it) } ?: throw CommandFileException("Не MusicGenre")

    override fun getMusicBand(): MusicBand {
        val name = next()
        val coordinates = Coordinates(
            next().toFloatOrNull() ?: throw CommandFileException("Не Float"),
            next().toDoubleOrNull() ?: throw CommandFileException("Не Double")
        )
        val numberOfParticipants = next().toIntOrNull() ?: throw CommandFileException("Не Int")
        val albumsCount = next().toLongOrNull()
        val description = next()
        val genre = MusicGenre.valueOfOrNull(next()) ?: throw CommandFileException("Не MusicGenre`")
        var album: Album? = null
        val albumName = next()
        if (albumName.isNotEmpty()) {
            val albumLength = next().toLongOrNull() ?: throw CommandFileException("Не Long")
            album = Album(albumName, albumLength)
        }
        return MusicBand(
            name = name,
            coordinates = coordinates,
            numberOfParticipants = numberOfParticipants,
            albumsCount = albumsCount,
            description = description,
            genre = genre,
            bestAlbum = album
        )
    }

    /**
     * @return next line of the command file
     */
    fun next(): String {
        if (hasNext()) return lines[index++]
        throw CommandFileException("Недостаточно строк")
    }

    /**
     * Check if there is next line of the command file
     */
    fun hasNext(): Boolean = (index < lines.count())
}