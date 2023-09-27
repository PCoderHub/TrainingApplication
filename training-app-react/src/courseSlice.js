import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    course: {},
};

const courseSlice = createSlice({
    name: 'course',
    initialState,
    reducers: {
        addCourseInfo: (state, action) => {
            state.course = {...action?.payload}
            console.log(state.course);
        },
    },
});

export const {addCourseInfo} = courseSlice.actions;
export default courseSlice.reducer;