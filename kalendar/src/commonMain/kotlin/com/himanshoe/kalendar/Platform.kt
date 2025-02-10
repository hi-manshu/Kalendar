package com.himanshoe.kalendar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform