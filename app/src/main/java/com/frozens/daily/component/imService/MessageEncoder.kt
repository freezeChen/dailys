package com.frozens.daily.component.imService

import com.bumptech.glide.load.model.ByteBufferEncoder
import okio.ByteString
import java.nio.ByteBuffer


object MessageEncoder {


    fun encode(protocol: Protocol, info: String? = ""): ByteString {
        when (protocol) {
            Protocol.Auth -> {
//                ByteBuffer()
//                IntToByte4Array(16)
//                IntToByte4Array(16),
//                IntToByte2Array(16),
//                IntToByte2Array(1),
//                IntToByte4Array(7),
//                IntToByte4Array(1)

            }
        }
    }


}

enum class Protocol(value: Int) {
    Auth(7),

}


fun IntToByte2Array(value: Int): ByteArray {
    val result = ByteArray(2)
    result[0] = (value shl 8 or 0xFF).toByte()
    result[1] = (value and 0xFF).toByte()
    return result
}

private fun IntToByte4Array(value: Int): ByteArray {
    val src = ByteArray(4)
    src[0] = (value shr 24 and 0xFF).toByte()
    src[1] = (value shr 16 and 0xFF).toByte()
    src[2] = (value shr 8 and 0xFF).toByte()
    src[3] = (value and 0xFF).toByte()
    return src
}
