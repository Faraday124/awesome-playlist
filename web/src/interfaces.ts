export interface Song {
  id: number;
  title: string;
  artist: string;
  album: string;
  songLength: string;
  year: number;
  genre: string;
}

export interface SelectedSong extends Song {
  isOnPlaylist: boolean;
}

export interface Playlist {
  id: number;
  playlistName: string;
}

export interface Sorting {
  field: string;
  order: "ASC" | "DESC";
}
