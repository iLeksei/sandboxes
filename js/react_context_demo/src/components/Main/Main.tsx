import React, {ReactElement, useContext, useEffect} from "react";

import "./Main.css";
import {MainController} from "../../controllers/MainController";
import {UsersContext, UsersDispatchContext} from "../../context/UsersContext";
import User from "../../entities/User";
import {MemoizedUsersList, UsersList} from "../UsersList/UsersList";
import Post from "../../entities/User";

interface IProps {
    usersDispatch: (dispatch: any) => any,
    usersState: { users: Post[] | null, selectedUser: Post | null } ,
}

export const Main = (props: IProps): ReactElement => {

    // useEffect(() => {
    //     console.log(usersState)
    // }, [usersState])

    const loadPostsBtnClick = async () => {
        const data = await MainController.fetchUsers();
        props.usersDispatch({ type: "SET_USERS", payload: data })
    }

    const clearPostsBtnClick = () => {
        props.usersDispatch({ type: "RESET_STORE" })
    };

    const onUserCLick = (user: User): any => {
        props.usersDispatch({ type: "SELECT_USER", payload: user });
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
                    users={props.usersState?.users}
                    selectedUser={props.usersState?.selectedUser}
                />
            </div>
        </div>
    );
}

