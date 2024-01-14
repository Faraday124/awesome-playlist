import React from "react";
import { fireEvent, render, screen } from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import SongsContainer from "./SongsContainer.js";

const mockSongs = [
  {
    id: 1,
    title: "Song1",
    artist: "Artist1",
    album: "album",
    songLength: "3:41",
    year: 1999,
    genre: "pop",
  },
  {
    id: 2,
    title: "Song2",
    artist: "Artist2",
    album: "album",
    songLength: "3:41",
    year: 1999,
    genre: "pop",
  },
];

const mockHandleSelectSong = jest.fn();
const mockHandleOrder = jest.fn();

const renderComponent = (songs) => {
  render(
    <SongsContainer
      handleSelectSong={mockHandleSelectSong}
      songs={songs}
      panelTitle="Test Panel"
      handleOrder={mockHandleOrder}
    />,
  );
};

describe("SongsContainer Component", () => {
  it("renders without crashing", () => {
    renderComponent(mockSongs);
    expect(screen.getByText("Test Panel")).toBeTruthy();
  });

  it("renders songs correctly", () => {
    renderComponent(mockSongs);
    expect(screen.getByText("Song1")).toBeTruthy();
    expect(screen.getByText("Song2")).toBeTruthy();
  });

  it("handles song selection", () => {
    renderComponent(mockSongs);
    fireEvent.click(screen.getByText("Song1"));
    expect(mockHandleSelectSong).toHaveBeenCalledWith(1);
  });

  it("handles song order by title", () => {
    renderComponent(mockSongs);
    fireEvent.click(screen.getByText("Abc"));
    expect(mockHandleOrder).toHaveBeenCalledWith(expect.any(Function));
  });

  it("handles song order by year", () => {
    renderComponent(mockSongs);
    fireEvent.click(screen.getByText("Year"));
    expect(mockHandleOrder).toHaveBeenCalledWith(expect.any(Function));
  });
});

