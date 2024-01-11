// import React, {useEffect, useState} from "react";
// import axios from 'axios';
// import SongRow from "./SongRow";
// import PlaylistIcon from "./assets/playlist-icon.svg";
//
// interface Song {
//     id: number,
//     title: string,
//     artist: string,
// }
//
// interface PlaylistContainerProps {
//     handleSelectSong: (id: number) => void
// }
// const PlaylistContainer = ({ handleSelectSong}: PlaylistContainerProps) => {
//
//     const [songs, setSongs] = useState<Song[]>([]);
//
//     useEffect(() => {
//         axios.get('http://127.0.0.1:8080/playlists/1')
//             .then((response) => setSongs(response.data.songs));
//
//     }, []); //TODO refereh after adding and drop the tables on init script
//     if (!songs) {
//         return null;
//     }
//     const Title = () => (
//         <div className="title-container">
//             <img src={PlaylistIcon} style={{ width: '35px', height: '35px', marginBottom: '20px' }} alt='mySvgImage' />
//             <h1 className="cool-title">My Playlist</h1>
//             <div className="line"></div>
//         </div>
//     );
//
//     return (
//         <>
//             <Title/>
//             <div className="song-list-container">
//                 <div className="list-group">
//                     {songs.map((song) => <SongRow key={song.id} id={song.id} title={song.title} artist={song.artist} handleSelectSong={handleSelectSong}></SongRow>)}
//                 </div>
//             </div>
//         </>
//     )
// };
//
//
// export default PlaylistContainer;