import React from "react";
import SongRow from "./SongRow";
import MusicIcon from "../assets/music-icon.svg";
import SortIcon from "../assets/sort-icon.svg";
import { Song, Sorting } from "../interfaces";

interface SongsContainerProps {
  handleSelectSong: (id: number) => void;
  songs?: Song[];
  panelTitle: string;
  handleOrder: (value: (prevState?: Sorting) => Sorting) => void;
}

const SongsContainer = ({
  handleSelectSong,
  songs,
  panelTitle,
  handleOrder,
}: SongsContainerProps) => {
  if (!songs) {
    return null;
  }

  const handleSongsOrder = (sortField: string) => {
    handleOrder((prevSorting?: Sorting) => {
      if (prevSorting?.order === "ASC") {
        return { field: sortField, order: "DESC" };
      }
      return { field: sortField, order: "ASC" };
    });
  };

  const Title = () => (
    <div className="title-container">
      <img
        src={MusicIcon}
        style={{ width: "30px", height: "30px", marginBottom: "20px" }}
        alt="mySvgImage"
      />
      <h1 className="cool-title">{panelTitle}</h1>
      <button
        className={"walkman-button"}
        onClick={() => handleSongsOrder("title")}
      >
        {" "}
        Abc
        <img src={SortIcon} />
      </button>
      <button
        className={"walkman-button"}
        onClick={() => handleSongsOrder("year")}
      >
        {" "}
        Year
        <img src={SortIcon} />
      </button>
      {/*<SearchInput label="Search by artist"></SearchInput> TODO */}
      <div className="line"></div>
    </div>
  );

  return (
    <>
      <Title />
      <div className="song-list-container">
        <div className="list-group">
          {songs.map((song) => (
            <SongRow
              key={song.id}
              id={song.id}
              title={song.title}
              artist={song.artist}
              handleSelectSong={handleSelectSong}
            ></SongRow>
          ))}
        </div>
      </div>
    </>
  );
};

export default SongsContainer;
