package com.football.topforecaster.service

import org.jsoup.nodes.Document

interface NetworkService {
    fun getDocument(url: String, timeout: Int): Document
}
