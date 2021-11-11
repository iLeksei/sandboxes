import React, {ReactElement, useContext, useEffect} from "react";

import "./Main.css";
import {MainController} from "../../controllers/MainController";
import {PostsContext, PostsDispatchContext} from "../../context/WithPosts";
import User from "../../entities/User";
import {MemoizedUsersList, UsersList} from "../UsersList/UsersList";

interface IProps {
}

export const Main = (props: IProps): ReactElement => {
    const usersState = useContext(PostsContext);
    const dispatch = useContext(PostsDispatchContext);

    // useEffect(() => {
    //     console.log(usersState)
    // }, [usersState])

    const loadPostsBtnClick = async () => {
        const data = await MainController.fetchUsers();
        dispatch({ type: "SET_USERS", payload: data })
        console.log(usersState);
    }

    const clearPostsBtnClick = () => {
        dispatch({ type: "RESET_STORE" })
    };

    const onUserCLick = (user: User): any => {
        dispatch({ type: "SELECT_USER", payload: user });
    }


    return (
        <div className="main__container">
            <div className="main__heading">
                <h4>Hello, Hola, Hi!</h4>
            </div>
            <div className="main__buttons-container">
                <button
                    id="load-data-btn"
                    className="main__load-data-btn"
                    onClick={loadPostsBtnClick}
                >
                    Load users
                </button>
                <button
                    id="clear-data-btn"
                    className="main__clear-data-btn"
                    onClick={clearPostsBtnClick}
                >
                    Clear list
                </button>
            </div>
            <div>
                <UsersList
                    onUserClick={onUserCLick}
                    users={usersState?.users}
                    selectedUser={usersState?.selectedUser}
                />
            </div>
        </div>
    );
}

