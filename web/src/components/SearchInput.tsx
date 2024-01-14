import React, { ChangeEvent, useEffect, useState } from "react";
import axios from "axios";
import { SERVER_URL } from "../Utils";

interface SearchInputProps {
  label: string;
}

const SearchInput: React.FC<SearchInputProps> = ({ label }) => {
  const [inputValue, setInputValue] = useState<string>("");

  useEffect(() => {
    if (inputValue?.length > 3) {
      axios
        .get(`${SERVER_URL}songs}`) //TODO
        .then((response) => console.log(response.data));
    }
  }, [inputValue]);
  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const value = event.target.value;
    setInputValue(value);
    console.log(`User input: ${value}`);
  };

  return (
    <div>
      <label>{label}</label>
      <input type="text" value={inputValue} onChange={handleInputChange} />
    </div>
  );
};

export default SearchInput;
