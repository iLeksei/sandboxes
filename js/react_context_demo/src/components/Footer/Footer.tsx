import React, {ReactElement, useContext} from "react";
import User from "../../entities/User";

import "./Footer.css";
import {PostsContext, PostsDispatchContext} from "../../context/UsersContext";

interface IProps {
}

export const Footer = (props: IProps): ReactElement => {
    const usersState = useContext(PostsContext);
    const dispatch = useContext(PostsDispatchContext);

    return (
        <div className="footer__container">
            <div className="footer__counter">
                Count: {usersState?.users?.length || 0}
            </div>
            <div className="footer__selected-user">
                Selected user: {usersState?.selectedUser?.id || ""}
            </div>
        </div>
    );
}