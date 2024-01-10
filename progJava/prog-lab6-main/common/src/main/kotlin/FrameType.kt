import kotlinx.serialization.Serializable

/**
 * Enum represents possible types of the frames
 */
@Serializable
enum class FrameType {
    COMMAND_REQUEST, LIST_OF_COMMANDS_REQUEST, COMMAND_RESPONSE, LIST_OF_COMMANDS_RESPONSE
}