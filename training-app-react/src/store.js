import { configureStore, combineReducers } from "@reduxjs/toolkit";
import applicationReducer from "./applicationSlicer";
import courseReducer from './courseSlice';
import editSlice from "./editSlice";
import userReducer from './userSlice';

const rootReducer = combineReducers({
    course: courseReducer,
    user: userReducer,
    application: applicationReducer,
    edit: editSlice,
});

export default configureStore({
    reducer: rootReducer,
});