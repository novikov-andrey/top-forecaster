package com.football.topforecaster.properties

import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@Component
@ConfigurationProperties(prefix = "championat-client")
@Validated
open class ChampionatClientProperties {
    @NotNull
    open var url = ""
    @NotNull
    open var rplCalendarLink = ""
    @NotNull
    open var timeout = 60000
}
