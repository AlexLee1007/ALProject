package com.alexe.www.http

import com.alexe.base.helper.ALog
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.StringBuilder

/**
 * @package: com.alexe.www.http
 * @author: Alex Lee
 * @date: 2021/5/8 15:54
 * @des: <b>此处描述当前类</b>
 */
class HttpLogger : HttpLoggingInterceptor.Logger {

    private val mMessage = StringBuffer()

    override fun log(message: String) {
        var msg: String = message
        if (message.startsWith("--> POST") || message.startsWith("--> GET")) {
            mMessage.setLength(0)
        }
        if (message.startsWith("{") && message.endsWith("}")
            || (message.startsWith("[") && message.endsWith("]"))
        ) {
            msg = formatJson(decodeUnicode(message))
        }
        mMessage.append(msg.plus("\n"))
        if (message.startsWith("<-- END HTTP")) {
            ALog.d(mMessage.toString())
        }
    }

    private fun formatJson(json: String): String {
        val sb = StringBuilder()
        var last: Char?
        var current = '\u0000'
        var indent = 0
        for (i in 0 until json.length) {
            last = current
            current = json[i]
            when (current) {
                //遇到{ [换行，且下一行缩进
                '{', '[' -> {
                    sb.append(current)
                    indent++
                    addIndentBlank(sb, indent)
                }
                //遇到} ]换行，当前行缩进
                '}', ']' -> {
                    indent--
                    addIndentBlank(sb, indent)
                    sb.append(current)
                }
                //遇到,换行
                ',' -> {
                    sb.append(current)
                    if (last != '\\') {
                        addIndentBlank(sb, indent)
                    }
                }
                else -> sb.append(current)
            }
        }
        return sb.toString()
    }

    private fun addIndentBlank(sb: StringBuilder, indent: Int) {
        for (i in 0 until indent) {
            sb.append('\t')
        }
    }

    private fun decodeUnicode(theString: String): String {
        var aChar: Char
        val len = theString.length
        val outBuffer = StringBuffer(len)
        var index = 0
        while (index < len) {
            aChar = theString[index++]
            if (aChar == '\\') {
                aChar = theString[index++]
                if (aChar == 'u') {
                    var value = 0
                    for (i in 0..3) {
                        aChar = theString[index++]
                        when (aChar) {
                            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> (value shl 4) + aChar.toInt() - '0'.toInt()
                            'a', 'b', 'c', 'd', 'e', 'f' -> (value shl 4) + 10 + aChar.toInt() - 'a'.toInt()
                            'A', 'B', 'C', 'D', 'E', 'F' -> (value shl 4) + 10 + aChar.toInt() - 'A'.toInt()
                            else -> throw  IllegalArgumentException("Malformed \\uxxxx  encoding.")
                        }
                    }
                    outBuffer.append(value.toChar())
                } else {
                    if (aChar == 't') aChar = '\t' else if (aChar == 'r') aChar = '\r'
                    else if (aChar == 'n') aChar = '\n' else if (aChar == 'f') aChar = '\u000C'
                    outBuffer.append(aChar)
                }
            } else outBuffer.append(aChar)
        }
        return outBuffer.toString()
    }

    companion object {
        private const val TAG = "HttpLogger"
    }

}