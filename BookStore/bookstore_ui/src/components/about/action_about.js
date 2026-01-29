import { URLs } from "../../constants";

export const InfoAbout = async () => {
  return fetch(`${URLs}about/getAllAbout`)
    .then((response) => response.json())
    .catch((error) => {
      console.error("Error fetching data from API:", error);
      return [];
    });
};
