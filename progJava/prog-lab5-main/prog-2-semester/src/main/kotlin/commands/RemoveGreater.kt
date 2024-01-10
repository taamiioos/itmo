package commands

import data.MusicBand
import utils.ArgumentType
import utils.CommandResult

/**
 * The command removes from the collection all items that exceed the specified.
 *
 * The loop and condition are used to validate the key.
 */
class RemoveGreater : UndoableCommand() {
    override fun getDescription(): String = "remove_greater : удалить из коллекции все элементы, превышающие заданный"

    override fun execute(args: ArrayList<Any>): CommandResult {
        val userElement = args[0] as MusicBand
        storage.getCollection { userElement < value }
            .forEach {
                previousPair.add(it.key to it.value)
                storage.removeKey(it.key)
            }
        return CommandResult.Success("Remove_greater")
    }

    override fun undo(): CommandResult {
        previousPair.forEach { (key, value) ->
            storage.insert(key, value!!)
        }
        previousPair.clear()
        return CommandResult.Success("Undo Remove_greater")
    }

    override fun getArgumentTypes(): Array<ArgumentType> = arrayOf(ArgumentType.MUSIC_BAND)
}
