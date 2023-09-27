import React, { useState } from "react";
import { Card, TextField, Button } from "@mui/material";
import api from './../api/axiosConfig';
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { addUserInfo } from "../userSlice";
import './Login.css';

const SignUp = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSignup = async (e) => {
        e.preventDefault();
        const user = {
            email: email,
            password: password,
        };
        try {
             const response = await api.post('/api/signup', user);
             console.log(response);

        }catch(error) {
            console.log(error);
        }
        dispatch(addUserInfo(user));
        navigate("/");
    }

    return <div>
        <Card className="form">
            <h1>Sign Up</h1>
            <TextField className="text-field" id="outlined-basic" label="Email" variant="outlined" name="email" value={email} onChange={(event) => {setEmail(event.target.value)}}/>
            <TextField className="text-field" id="outlined-basic" label="Password" variant="outlined" name="password" value={password} onChange={(event) => {setPassword(event.target.value)}} />
            <Button className="button" onClick={handleSignup} variant="contained" >Sign Up</Button>
        </Card>
    </div>
}

export default SignUp;