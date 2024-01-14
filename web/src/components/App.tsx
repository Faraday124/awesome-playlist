import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.css";
import SongsContainer from "./SongsContainer";
import SongDetails from "./SongDetails";
import axios from "axios";
import { SERVER_URL, USER_ID } from "../Utils";
import { Playlist, SelectedSong, Song, Sorting } from "../interfaces";

function App() {
  const [selectedSong, setSelectedSong] = useState<SelectedSong | undefined>(
    undefined,
  );
  const [songs, setSongs] = useState<Song[]>([]);
  const [playlistSongs, setPlaylistSongs] = useState<Song[]>([]);
  const [lastUpdate, setLastUpdate] = useState(Date.now());
  const [songsOrder, setSongsOrder] = useState<Sorting | undefined>(undefined);
  const [playlistSongsOrder, setPlaylistSongsOrder] = useState<
    Sorting | undefined
  >(undefined);
  const [playlist, setPlaylist] = useState<Playlist | undefined>(undefined);
  // const [filterByTitle, setFilterByTitle] = useState<string>(""); //TODO

  useEffect(() => {
    axios
      .get(`${SERVER_URL}users/${USER_ID}/playlists`)
      .then((response) => setPlaylist(response.data))
      .catch((error) => {
        console.error("Error fetching playlist:", error);
      });
  }, []);

  useEffect(() => {
    let sorting = "";
    if (songsOrder?.field) {
      sorting = `?sortParameter=${songsOrder.field}&sortDirection=${songsOrder.order}`;
    }
    axios
      .get(`${SERVER_URL}songs${sorting}`)
      .then((response) => setSongs(response.data));
  }, [songsOrder]);

  useEffect(() => {
    if (!playlist) {
      return;
    }
    let sorting = "";
    if (playlistSongsOrder?.field) {
      sorting = `?sortParameter=${playlistSongsOrder.field}&sortDirection=${playlistSongsOrder.order}`;
    }
    axios
      .get(`${SERVER_URL}playlists/${playlist.id}/songs${sorting}`)
      .then((response) => setPlaylistSongs(response.data));
    setSelectedSong(undefined);
  }, [lastUpdate, playlistSongsOrder, playlist]);

  const handleSelectASong = async (id: number) => {
    try {
      const response = await axios.get<Song>(
        `${SERVER_URL}songs/${id}/details`,
      );

      const selectedSong = response.data;

      setSelectedSong({
        ...selectedSong,
        isOnPlaylist: isOnPlaylist(selectedSong),
      });

      await axios.get(`${SERVER_URL}songs/${id}/details`);
    } catch (error) {
      console.error("Error fetching song details:", error);
    }
  };
  const isOnPlaylist = (song: Song) =>
    playlistSongs.map((s) => s.id).includes(song.id) || false;

  return (
    <div className="container">
      <div className="left-section">
        <SongsContainer
          handleSelectSong={handleSelectASong}
          handleOrder={setSongsOrder}
          songs={songs}
          panelTitle={"Songs catalogue"}
        />
      </div>
      <div className="middle-section">
        <SongDetails
          selectedSong={selectedSong}
          onUpdate={setLastUpdate}
          playlist={playlist?.id}
        />
      </div>
      <div className="right-section">
        <SongsContainer
          handleSelectSong={handleSelectASong}
          handleOrder={setPlaylistSongsOrder}
          songs={playlistSongs}
          panelTitle={"My Playlist"}
        />
      </div>
    </div>
  );
}

export default App;
