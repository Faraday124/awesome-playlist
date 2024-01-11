import React from "react";
import SongRow from "./SongRow";
import MusicIcon from "./assets/music-icon.svg";
import SortIcon from "./assets/sort-icon.svg";
import {Song, Sorting} from "./App";

interface SongsContainerProps {
    handleSelectSong: (id: number) => void,
    songs?: Song[],
    title: string,
    handleOrder: (value: (((prevState?: Sorting) => Sorting))) => void
}

const SongsContainer = ({handleSelectSong, songs, title, handleOrder}: SongsContainerProps) => {

    if (!songs) {
        return null;
    }

    const handleSongsOrder = (sortField: string) => {
        handleOrder((prevSorting?: Sorting) => {
            if (prevSorting?.order === "ASC") {
                return {field: sortField, order: "DESC"};
            }
            return {field: sortField, order: 'ASC'};
        });
    }
    const Title = () => (
        <div className="title-container">
            <img src={MusicIcon} style={{width: '30px', height: '30px', marginBottom: '20px'}} alt='mySvgImage'/>
            <h1 className="cool-title">{title}</h1>
            <button onClick={() => handleSongsOrder("title")} style={{background: "red"}}> Abc<img
                src={SortIcon}/></button>
            <button onClick={() => handleSongsOrder("year")} style={{background: "red"}}> Year<img
                src={SortIcon}/></button>
            <div className="line"></div>
        </div>
    );

    return (
        <>
            <Title/>
            <div className="song-list-container">
                <div className="list-group">
                    {songs.map((song) => <SongRow key={song.id} id={song.id} title={song.title} artist={song.artist}
                                                  handleSelectSong={handleSelectSong}></SongRow>)}
                </div>
            </div>
        </>
    )
};


export default SongsContainer;