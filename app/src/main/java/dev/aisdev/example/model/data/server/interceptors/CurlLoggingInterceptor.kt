package dev.aisdev.example.model.data.server.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor.Logger
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

private val UTF8 = Charset.forName("UTF-8")

class CurlLoggingInterceptor @JvmOverloads constructor(private val logger: Logger = Logger.DEFAULT) : Interceptor {

    private var curlOptions: String? = null

    /** Set any additional curl command options (see 'curl --menu_help').  */
    fun setCurlOptions(curlOptions: String) {
        this.curlOptions = curlOptions
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var compressed = false
        var curlCmd = "curl"

        curlOptions?.let { curlCmd += " $it" }
        curlCmd += " -X ${request.method}"

        val headers = request.headers

        var i = 0
        val count = headers.size
        while (i < count) {
            val name = headers.name(i)
            val value = headers.value(i)
            if ("Accept-Encoding".equals(name, ignoreCase = true) && "gzip".equals(value, ignoreCase = true)) {
                compressed = true
            }
            curlCmd += " -H \"$name: $value\""
            i++
        }

        request.body?.let { reqBody ->
            val buffer = Buffer()
            reqBody.writeTo(buffer)
            var charset = UTF8
            reqBody.contentType()?.charset(UTF8)?.let { charset = it }
            curlCmd += " --data $'" + buffer.readString(charset).replace("\n", "\\n") + "'"
        }

        curlCmd += (if (compressed) " --compressed " else " ") + request.url

        logger.log("╭--- cURL (${request.url})")
        logger.log(curlCmd)
        logger.log("╰--- (ic_copy_non_active and paste the above line to a terminal)")

        return chain.proceed(request)
    }

}