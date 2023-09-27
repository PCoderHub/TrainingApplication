import './App.css';
import api from './api/axiosConfig';
import { useEffect, useState } from 'react';
import Layout from './components/Layout';
import { Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import ApplicationForm from './components/ApplicationForm';
import MyCourses from './components/MyCourses';
import SignUp from './components/SignUp';
import Login from './components/Login';

function App() {

  const [courses, setCourses] = useState();
  

  const getCourses = async () => {
    try{
      const response = await api.get("/api/courses");
      console.log(response.data);
      setCourses(response.data);
    }
    catch (err){
      console.log(err);
    }
  }

  const getApplications = async () => {
    try{
      const response = await api.get("/api/applications");
      console.log(response.data);
      setApplications(response.data);
    }
    catch (err){
      console.log(err);
    }
  }

  useEffect(() => {
    getCourses();
  }, []);

  return (
    <div className="App">
      
      <Routes>
        <Route path="/" element={<Layout/>}>
          <Route path="/" element={<Home courses={courses}/>}></Route>
          <Route path="/register" element={<SignUp/>}></Route>
          <Route path="/application" element={<ApplicationForm />}></Route>
          <Route path="/mycourses" element={<MyCourses courses={courses}/>}></Route>
          <Route path="/login" element={<Login/>}></Route>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
