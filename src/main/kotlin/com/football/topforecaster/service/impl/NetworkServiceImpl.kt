package com.football.topforecaster.service.impl

import com.football.topforecaster.service.NetworkService
import mu.KLogging
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class NetworkServiceImpl: NetworkService {

    companion object: KLogging()

    override fun getDocument(url: String, timeout: Int): Document {
        try {
            return Jsoup.connect(url)
                    .maxBodySize(0)
                    .timeout(timeout)
                    .get()
        } catch (e: IOException) {
            logger.error(e) { "Fail to get document by url: $url" }
            throw e
        }
    }

}
