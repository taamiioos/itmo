package di
import FileManager
import commands.CommandHistory
import data.MusicBand
import org.koin.dsl.module
import serialize.Serializer
import utils.*
import utils.StorageManager
import serialize.SerializeManager

val gatewayModule = module {
    factory<Saver<LinkedHashMap<Int, MusicBand>>> {
        FileSaver("save.txt", serializer = get(), fileManager = get())
    }
    factory<Serializer<LinkedHashMap<Int, MusicBand>>> {
        SerializeManager()
    }

    factory {
        FileManager()
    }
    single {
        CommandHistory()
    }
    single<Storage<LinkedHashMap<Int, MusicBand>, Int, MusicBand>> {
        StorageManager()
    }
    single {
        CommandManager()
    }
 }