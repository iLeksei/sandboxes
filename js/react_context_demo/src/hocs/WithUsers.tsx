import {ReactElement, useContext, ComponentType, ComponentClass, FunctionComponent} from "react";
import {UsersContext, UsersDispatchContext} from "../context/UsersContext";
import Post from "../entities/User";

interface HocProps {
    usersState: { users: Post[] | null, selectedUser: Post | null },
    usersDispatch: any,
}

export function WithUsers<P extends HocProps>(Component: ComponentType<P>): ReactElement<P> {
    const state = useContext(UsersContext);
    const dispatch = useContext(UsersDispatchContext);
    return <Component usersState={state} usersDispatch={dispatch} />
}