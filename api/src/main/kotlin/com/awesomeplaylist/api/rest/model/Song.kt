package com.awesomeplaylist.api.rest.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "songs")
data class Song(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(name = "title")
        var title: String,

        @Column(name = "artist")
        var artist: String,

        @Column(name = "album")
        var album: String,

        @Column(name = "year")
        var year: Long,

        @Column(name = "song_length")
        var songLength: String,

        @Column(name = "genre")
        var genre: String,
        )