import { Card, Paper, ButtonBase } from "@mui/material";
import './Courses.css';
import ApplicationForm from './ApplicationForm';
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { addCourseInfo } from "../courseSlice";

const Courses = ({courses}) => {
    const dispatch = useDispatch();

    const navigate = useNavigate();

    const handleClick = (course) => {
        
            dispatch(addCourseInfo(course));
            navigate('/application');
        }

    //const list = ['IT Skills', 'CV/Resume writing', 'Interview', 'Aptitude tests']; 
    
    return <div>
        <div><h1>Courses Offered</h1></div>
        
            <Card variant="outlined" className="courses-container">
                <div className="courses-container-box">
                {(courses?.length>0 && courses.map((course) => {
                    return (
                        <ButtonBase onClick={() => handleClick(course) }>
                            <Paper className="course-container">
                            <h4>{course.courseName}</h4>
                            </Paper>
                        </ButtonBase>
                        
                    );
                }))}
                </div>
            </Card>
            
    </div>
}

export default Courses;