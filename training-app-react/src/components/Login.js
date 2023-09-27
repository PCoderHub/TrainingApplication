import React, { useState } from "react";
import { TextField, Card, Button } from "@mui/material";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { addUserInfo } from "../userSlice";
import './Login.css';

const Login = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        const user = {
            email: email,
            password: password,
        };
        try {
             const response = await api.post('/api/login', user);
             console.log(response);

        }catch(error) {
            console.log(error);
        }
        dispatch(addUserInfo(user));
        navigate("/");
    }

    return <div>
         <Card className="form">
            <h1>Login</h1>
            <TextField className="text-field" id="outlined-basic" label="Email" variant="outlined" name="email" value={email} onChange={(event) => {setEmail(event.target.value)}}/>
            <TextField className="text-field" id="outlined-basic" label="Password" variant="outlined" name="password" value={password} onChange={(event) => {setPassword(event.target.value)}} />
            <Button className="button" onClick={handleLogin} variant="contained">Login</Button>
        </Card>
    </div>
}

export default Login;