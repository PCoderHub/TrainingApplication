import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    application: {},
};

const applicationSlicer = createSlice({
    name: 'application',
    initialState,
    reducers: {
        addApplicationInfo: (state, action) => {
            state.application = {...action?.payload}
            console.log(state.application);
        },
    },
});

export const {addApplicationInfo} = applicationSlicer.actions;
export default applicationSlicer.reducer;