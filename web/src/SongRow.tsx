import  React from "react";
import axios from 'axios';
import SongsContainerProps from "./SongsContainer";
interface SongRowProps {
    id: number,
    title: string,
    artist: string,
    handleSelectSong: (id: number) => void
}

const SongRow = ({ id, title, artist, handleSelectSong } : SongRowProps ) => {

    return <>
    <button onClick={() => handleSelectSong(id)} className="song-row">
        <div className="song-title"> {title}</div>
        <div className="artist-name">{artist}</div>

    </button>
    </>

}

export default SongRow;