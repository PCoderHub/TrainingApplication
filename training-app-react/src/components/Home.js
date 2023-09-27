import React from "react";
import Courses from "./Courses";
import Header from "./Header";

const Home = ({courses}) => {
    console.log(courses);
    return <div>
        <Header/>
        <Courses courses={courses}/>
    </div>
}

export default Home;