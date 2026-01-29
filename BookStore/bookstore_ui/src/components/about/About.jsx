import React, { useState, useEffect } from "react";
import "./about.css";
import { InfoAbout } from "./action_about";
import { error } from "jquery";

const About = () => {
  const [allAbout, setAllAbout] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await InfoAbout();
        setAllAbout(data);
      } catch (error) {
        console.error("Error fetching data from API: ", error);
      }
    };

    fetchData();
  }, []);

  return (
    <section className="container">
      {allAbout.map((aboutItem, index) => (
        <div key={index} className="about-container">
          <div className="about-description">
            <h3>{aboutItem.name}</h3>
            <p>{aboutItem.email}</p>
            <p>{aboutItem.phone}</p>
            <p>{aboutItem.desc}</p>
          </div>
          <div className="about-image">
            <img src={require(`../../assets/${aboutItem.image}`)} alt="" />
          </div>
        </div>
      ))}
    </section>
  );
};

export default About;
