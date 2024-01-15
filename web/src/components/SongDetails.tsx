import React from "react";
import axios from "axios";
import RemoveIcon from "../assets/remove.svg";
import AddIcon from "../assets/add.svg";
import { SERVER_URL } from "../Utils";
import { SelectedSong } from "../interfaces";

interface SongDetailsProps {
  selectedSong?: SelectedSong;
  onUpdate: (value: ((prevState: number) => number) | number) => void;
  playlist: unknown;
}

const SongDetails = ({
  selectedSong,
  onUpdate,
  playlist,
}: SongDetailsProps) => {
  const handleRemoveSong = async (id: number) => {
    await axios.delete(`${SERVER_URL}playlists/${playlist}/songs/${id}`);
    onUpdate(Date.now());
  };
  const handleAddSong = async (id: number) => {
    await axios.put(`${SERVER_URL}playlists/${playlist}/songs/${id}`);
    onUpdate(Date.now());
  };

  if (!selectedSong) {
    return (
      <div className="song-details">
        <img
          src={`public/assets/headphones.png`}
          alt="Album Cover"
          className="intro-cover"
        />
      </div>
    );
  }

  const random = Math.floor(Math.random() * 2);
  return (
    <>
      <div className="song-details">
        <img
          src={`public/assets/${random}.jpeg`}
          alt="Album Cover"
          className="album-cover"
        />
        <h2 className="cool-title">{selectedSong.title}</h2>
        <p className="artist">{selectedSong.artist}</p>
        <p className="duration">Duration: {selectedSong.songLength}</p>
        <p className="genre">Genre: {selectedSong.genre}</p>
        <p className="year">Year: {selectedSong.year}</p>
      {selectedSong.isOnPlaylist && (
        <button
          className="remove-song"
          onClick={() => handleRemoveSong(selectedSong.id)}
        >
          <img src={RemoveIcon} />
        </button>
      )}
      {!selectedSong.isOnPlaylist && (
        <button
          className="add-song"
          onClick={() => handleAddSong(selectedSong.id)}
        >
          <img src={AddIcon} style={{ fill: "white" }} />
        </button>
      )}
      </div>
    </>
  );
};

export default SongDetails;
