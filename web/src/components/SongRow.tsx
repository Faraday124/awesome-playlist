import React from "react";

interface SongRowProps {
  id: number;
  title: string;
  artist: string;
  handleSelectSong: (id: number) => void;
}

const SongRow = ({ id, title, artist, handleSelectSong }: SongRowProps) => {
  return (
    <>
      <button onClick={() => handleSelectSong(id)} className="song-row">
        <div className="song-title"> {title}</div>
        <div className="artist-name">{artist}</div>
      </button>
    </>
  );
};

export default SongRow;
