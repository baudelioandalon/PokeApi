package com.testexample.utils.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

class GRestEngine {

    companion object {

        fun getRestEngine(baseUrl: String? = null): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = getUnsafeOkHttpClient().addInterceptor(interceptor)
            client.readTimeout(60, TimeUnit.SECONDS)
            client.connectTimeout(60, TimeUnit.SECONDS)

            return if (baseUrl != null) {
                Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build()
            } else {
                Retrofit.Builder()
                    .baseUrl("https://google.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build()
            }

        }

        fun getRestEngineOAuth(baseUrl: String? = null): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = getUnsafeOkHttpClient().addInterceptor(interceptor)

            client.readTimeout(60, TimeUnit.SECONDS)
            client.connectTimeout(60, TimeUnit.SECONDS)

            return if (baseUrl != null) {
                Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build()
            } else {
                Retrofit.Builder()
                    .baseUrl("https://google.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build()
            }

        }

        fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
            return try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

                // Install the all-trusting trust manager
                val sslContext =
                    SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(
                    sslSocketFactory,
                    trustAllCerts[0] as X509TrustManager
                )
                builder.hostnameVerifier(object : HostnameVerifier {
                    override fun verify(
                        hostname: String?,
                        session: SSLSession?
                    ): Boolean {
                        return true
                    }
                })
                builder
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

}