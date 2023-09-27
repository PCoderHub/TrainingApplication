import { Button, Card, Paper } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { addApplicationInfo } from "../applicationSlicer";
import { addCourseInfo } from "../courseSlice";
import { addAppInfo, addEditedTrue } from "../editSlice";
import api from './../api/axiosConfig';

const MyCourses = ({courses}) => {

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [applications, setApplications] = useState();

    const getApplications = async () => {
        try{
          const response = await api.get("/api/applications");         //GET call to get all applications
          console.log(response.data);
          setApplications(response.data);
        }
        catch (err){
          console.log(err);
        }
      }

    useEffect(() => {
        getApplications();
    }, []);
    
    const userData = useSelector(
        (state) => state?.user.user
    );
    console.log(userData);
    const appList = applications?.length>0 && applications?.map((app) => {
        if(app?.email === userData?.email)
         return app;
    });
    console.log(appList);

    const handleClick = (item) => {
        const course = courses?.length>0 && courses?.map((cours) => {
            if(cours.courseName === item.course)
            return cours;
        });
        dispatch(addCourseInfo(course));
        dispatch(addEditedTrue());
        dispatch(addAppInfo(item));
        navigate("/application");
    }

    const handleDelete = (item) => {
        console.log(item);
        api.delete(`/api/applications/${item?.email}/${item.course}`).then(response => {      //DELETE call to delete all applications
            getApplications();
            console.log(response);
        }).catch(error => console.log(error));
    }

    return <div>
        <Card>
            {appList?.length>0 && appList?.map((item) => {
                if(item != undefined)
                return(
                <Paper>
                    <h4>{item?.course}</h4>
                    <Button onClick={() => {handleClick(item)}}>Edit</Button>
                    <Button onClick={() => {handleDelete(item)}}>Delete</Button>
                </Paper>
                );
            })}
        </Card>
    </div>
}

export default MyCourses;