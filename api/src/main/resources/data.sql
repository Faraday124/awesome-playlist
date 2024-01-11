DELETE FROM playlist_song;
DELETE FROM playlists;
DELETE FROM songs;

INSERT INTO songs (title, artist, album, year, song_length, genre)
VALUES ('Bohemian Rhapsody', 'Queen', 'A Night at the Opera', 1975, '5:55', 'Rock'),
       ('Like a Rolling Stone', 'Bob Dylan', 'Highway 61 Revisited', 1965, '6:13', 'Folk Rock'),
       ('Imagine', 'John Lennon', 'Imagine', 1971, '3:03', 'Soft Rock'),
       ('What''s Going On', 'Marvin Gaye', 'What''s Going On', 1971, '3:53', 'Soul'),
       ('Hey Jude', 'The Beatles', 'The Beatles', 1968, '7:11', 'Rock'),
       ('Billie Jean', 'Michael Jackson', 'Thriller', 1983, '4:54', 'Pop'),
       ('I Want to Hold Your Hand', 'The Beatles', 'Meet the Beatles!', 1963, '2:26', 'Rock'),
       ('I Can''t Get No Satisfaction', 'The Rolling Stones', 'Out of Our Heads', 1965, '3:43', 'Rock'),
       ('Like a Virgin', 'Madonna', 'Like a Virgin', 1984, '3:08', 'Pop'),
       ('Superstition', 'Stevie Wonder', 'Talking Book', 1972, '4:26', 'Funk'),
       ('Purple Haze', 'Jimi Hendrix', 'Are You Experienced', 1967, '2:51', 'Rock'),
       ('Stairway to Heaven', 'Led Zeppelin', 'Led Zeppelin IV', 1971, '8:02', 'Rock'),
       ('What a Wonderful World', 'Louis Armstrong', 'What a Wonderful World', 1967, '2:21', 'Jazz'),
       ('I Will Always Love You', 'Whitney Houston', 'The Bodyguard: Original Soundtrack Album', 1992, '4:31', 'R&B'),
       ('Hotel California', 'Eagles', 'Hotel California', 1976, '6:31', 'Rock'),
       ('Smells Like Teen Spirit', 'Nirvana', 'Nevermind', 1991, '5:01', 'Grunge'),
       ('Let It Be', 'The Beatles', 'Let It Be', 1970, '4:03', 'Rock'),
       ('What a Feeling', 'Irene Cara', 'Flashdance: Original Soundtrack from the Motion Picture', 1983, '3:55', 'Pop'),
       ('Someone Like You', 'Adele', '21', 2011, '4:45', 'Pop'),
       ('Sweet Child o'' Mine', 'Guns N'' Roses', 'Appetite for Destruction', 1987, '5:56', 'Rock');


INSERT INTO playlists (user_id, playlist_name) VALUES (1, "Awesome Playlist");
INSERT INTO playlist_song (playlist_id, song_id) VALUES ((SELECT id FROM playlists WHERE user_id = 1), (SELECT id FROM songs WHERE title = 'Bohemian Rhapsody'));
INSERT INTO playlist_song (playlist_id, song_id) VALUES ((SELECT id FROM playlists WHERE user_id = 1), (SELECT id FROM songs WHERE title = 'Smells Like Teen Spirit'));