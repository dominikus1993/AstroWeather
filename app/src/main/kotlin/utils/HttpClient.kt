package utils

import com.google.api.client.http.javanet.NetHttpTransport
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by domin_000 on 29.05.2016.
 */
class HttpClient{
    companion object{
        fun buildRequest(url:String, method: String): NetHttpTransport {
            var urlConnection:HttpURLConnection? = null;

            try{
                val url = URL(url)
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = method
                urlConnection.setRequestProperty("Content-Type",
                        "application/json");
                urlConnection.setRequestProperty()
            }
        }
    }
}