import React, { useState } from 'react';
import { useEffect, useRef } from 'react';
import api from './../api/axiosConfig';
import { useNavigate, useParams } from 'react-router-dom';
import { Select ,Card, TextField, Button, MenuItem, InputLabel, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import './ApplicationForm.css';
import { addApplicationInfo } from '../applicationSlicer';
import { addEditedFalse } from '../editSlice';

const ApplicationForm = () => {

    const isEdit = useSelector(                        //to fetch if isEdit is true from state
        (state) => state?.edit.isEdit   
    );
    const appData = useSelector(                       //to fetch application details from state for editinf
        (state) => state?.edit.app
    );

    const userData = useSelector(                      //to fetch user details from state
        (state) => state?.user?.user
    );

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [module, setModule] = useState('');

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleChange = (event) => {
        setModule(event.target.value);
    }

    const courseDetails = useSelector(                  //to fetch courseDetails from state
        (state) => state?.course?.course
    );
    //console.log(courseDetails);
    const handleSubmit = async (e) => {
        e.preventDefault();
        const applicationForm = {
            traineeName: name,
            email: userData.email,
            course: courseDetails.courseName,
            module: module,
        };

        api.post(`/api/applications/${applicationForm.email}`, applicationForm).then(           //POST api call to post a new application form
            response => {
                console.log(response);
            }
        ).catch(
            error => {
                console.log(error);
            }
        );
        dispatch(addApplicationInfo(applicationForm));
        navigate(-1);
    }

    const handleEdit = async (e) => {
        e.preventDefault();
        console.log("edited balues", name, userData)
        const editedApplication = {
            traineeName: appData.traineeName,
            email: userData.email,
            course: appData.course,
            module: module,
        };

        try{
            const response = api.put(`/api/applications/${editedApplication.email}`, editedApplication);         //PUT api call
            console.log(response?.data);
        } catch(error) {
            console.log(error);
        }
        dispatch(addEditedFalse);
        navigate(-1);
    }

    return <div>
        <Card variant="outlined" className='application-container'>
            {!isEdit ? <div><h1>{courseDetails.courseName}</h1>
            <TextField id="outlined-basic" label="Full Name" variant="outlined" value={name} onChange={(event) => {setName(event.target.value)}} />
            <TextField id="outlined-basic" label="Email" variant="outlined" value={userData.email} onChange={(event) => {setEmail(event.target.value)}}/>
            <TextField disabled id="outlined-basic" label="Course" variant="outlined" defaultValue={courseDetails.courseName} />
            <div className='application-box'>
            <FormControl fullWidth>
            <FormLabel id="moduleId">Select a Module</FormLabel>
            
                <RadioGroup aria-labelledby="moduleId" value={module} name="radio-buttons-group" onChange={handleChange}>
                {(courseDetails?.modules)?.length>0 && (courseDetails?.modules).map((module) => {
                    return (
                        <FormControlLabel value={module} control={<Radio />} label={module} />
                    );
                })}
                </RadioGroup> 
            </FormControl>
            <Button className='application-box' variant="contained" onClick={handleSubmit}>Submit</Button>
            </div></div> : <div>
            <h1>{appData.course}</h1>
            <TextField id="outlined-basic" label="Full Name" variant="outlined" value={appData.traineeName} onChange={(event) => {setName(event.target.value)}} />
            <TextField id="outlined-basic" label="Email" variant="outlined" value={appData.email} onChange={(event) => {setEmail(event.target.value)}}/>
            <TextField disabled id="outlined-basic" label="Course" variant="outlined" defaultValue={appData.course} />
            <TextField id="outlined-basic" label="Module" variant="outlined" defaultValue={appData.module} onChange={(event) => {setModule(event.target.value)}} />
            <div className='application-box'>
            <Button className='application-box' variant="contained" onClick={handleEdit}>Submit</Button>
            </div>
                </div>}
        </Card>
    </div>
}

export default ApplicationForm;