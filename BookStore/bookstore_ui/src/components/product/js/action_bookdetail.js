import { URLs } from "../../../constants";


export const getBookDetail = (token,id) => {
    const headers = new Headers();
    console.log(token)
    headers.append("Authorization", token);
  
    return fetch(`${URLs}books/getBookDetail?id=${id}`, {
      headers: headers,
    })
      .then((response) => response.json())
      .catch((error) => {
        console.error("Error fetching data from API:", error);
        return [];
      });
  };