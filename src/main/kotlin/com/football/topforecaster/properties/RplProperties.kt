package com.football.topforecaster.properties

import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@Component
@ConfigurationProperties(prefix = "rpl")
@Validated
class RplProperties {
    @NotNull
    open var currentSeason = 2018
}
