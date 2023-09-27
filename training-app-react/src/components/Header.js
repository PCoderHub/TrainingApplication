import React from "react";
import { Button } from "@mui/material";
import './Header.css';
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

const Header = () => {
    const userData = useSelector(
        (state) => state?.user.user
    );
    const navigate = useNavigate();
    const handleClick = () => {
        navigate("/mycourses");
    }

    return <div className="header">
            {userData==undefined ? <div>
                <Button variant="text">Login</Button>
                <Button variant="text">SignUp</Button></div>
             : <div><Button variant="text" onClick={handleClick}>MyCourses</Button>
                    <Button variant="text">Logout</Button>
             </div>}

       
    </div>
}

export default Header;