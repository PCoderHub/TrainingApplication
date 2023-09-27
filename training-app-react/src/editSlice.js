import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    isEdit: false,
    app: {}
};

const editSlice = createSlice({
    name: 'edit',
    initialState,
    reducers: {
        addEditedTrue: (state) => {
            state.isEdit = true
            console.log(state.isEdit)
        },
        addEditedFalse: (state) => {
            state.isEdit = false
        },
        addAppInfo: (state, action) => {
            state.app = {...action?.payload}
            console.log(state.app)
        }
    },
});

export const {addEditedTrue, addEditedFalse, addAppInfo} = editSlice.actions;
export default editSlice.reducer;