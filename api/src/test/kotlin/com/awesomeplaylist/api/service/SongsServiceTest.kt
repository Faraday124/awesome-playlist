package com.awesomeplaylist.api.service

import com.awesomeplaylist.api.model.Song
import com.awesomeplaylist.api.repository.SongRepository
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Sort

@SpringBootTest
class SongsServiceTests {

    @Mock
    private lateinit var songRepository: SongRepository

    @InjectMocks
    private lateinit var songsService: SongsService

    companion object {
        val SONGS = listOf(
                Song(id = 1, artist = "AAA", title = "AAA Test Song", album = "xx", year = 2001, songLength = "2:59", genre = "pop"),
                Song(id = 2, artist = "ZZZ", title = "ZZZ Test Song", album = "xx", year = 1999, songLength = "2:59", genre = "pop")
        )
    }

    @Test
    fun `should return all songs`() {
        `when`(songRepository.findAll()).thenReturn(SONGS)

        val result = songsService.getAllSongs()

        assert(result == SONGS)
        verify(songRepository).findAll()
    }

    @Test
    fun `should return all sorted all songs by title`() {
        val sortParameter = "title"
        val sortDirection = "DESC"

        `when`(songRepository.findAll(Sort.by(Sort.Direction.fromString(sortDirection), sortParameter)))
                .thenReturn(SONGS)

        val result = songsService.getAllSongsSorted(sortParameter, sortDirection)

        assert(result == SONGS)
        verify(songRepository).findAll(Sort.by(Sort.Direction.fromString(sortDirection), sortParameter))
    }
}
