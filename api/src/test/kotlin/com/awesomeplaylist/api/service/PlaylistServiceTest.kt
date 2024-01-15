package com.awesomeplaylist.api.service

import com.awesomeplaylist.api.model.Playlist
import com.awesomeplaylist.api.model.Song
import com.awesomeplaylist.api.repository.PlaylistRepository
import com.awesomeplaylist.api.repository.SongRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import java.util.Optional

@SpringBootTest
class PlaylistServiceTest {

    @Mock
    private lateinit var playlistRepository: PlaylistRepository

    @Mock
    private lateinit var songRepository: SongRepository

    @InjectMocks
    private lateinit var playlistService: PlaylistService

    @Test
    fun `should return playlist by id`() {
        val playlistId = 1L
        val expectedPlaylist = Playlist(id = playlistId, userId = 1L, playlistName = "Test Playlist")

        `when`(playlistRepository.findById(playlistId)).thenReturn(Optional.of(expectedPlaylist))

        val result = playlistService.getPlaylistById(playlistId)

        assertEquals(expectedPlaylist, result)
    }

    @Test
    fun `should return playlist by user id`() {
        val userId = 123L
        val expectedPlaylist = Playlist(userId = userId, playlistName = "Test Playlist")

        `when`(playlistRepository.findByUserId(userId)).thenReturn(expectedPlaylist)

        val result = playlistService.getPlaylistByUserId(userId)

        assertEquals(expectedPlaylist, result)
    }

    @Test
    fun `should add song to a playlist`() {
        val playlistId = 1L
        val songId = 10L
        val playlist = Playlist(id = playlistId, userId = 1L, playlistName = "Test Playlist")
        val song = Song(id = songId, artist = "Dummy", title = "Test Song", album = "xx", year = 1999, songLength = "2:59", genre = "pop")

        `when`(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist))
        `when`(songRepository.findById(songId)).thenReturn(Optional.of(song))

        playlistService.addSongToPlaylist(playlistId, songId)

        assert(playlist.songs.contains(song))
    }

    @Test
    fun `should remove song from a playlist`() {
        val playlistId = 1L
        val songId = 10L
        val playlist = Playlist(id = playlistId, userId = 1L, playlistName = "Test Playlist")
        val song = Song(id = songId, artist = "Dummy", title = "Test Song", album = "xx", year = 1999, songLength = "2:59", genre = "pop")

        `when`(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist))
        `when`(songRepository.findById(songId)).thenReturn(Optional.of(song))

        playlistService.removeSongFromPlaylist(playlistId, songId)

        assert(!playlist.songs.contains(song))
    }
}
